package autenticazione.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.control.Action;
import controller.control.Service;
import model.dao.FormatoreDao;
import model.dao.StudenteDao;
import model.entity.FormatoreEntity;
import model.entity.StudenteEntity;

/**
 * Service del Controller per la gestione della Login e Logout
 *
 */
public class AutenticazioneService implements Service{
	StudenteDao sDao;
	FormatoreDao fDao;
	Action errorPage = new Action("/formAct/view/autenticazione/Login.jsp", true, true);
	Action homePage = new Action("/formAct/view/index/index.jsp", true, true);
	
	/**
	 * Costruttore di default
	 */
	public AutenticazioneService() {
		fDao = new FormatoreDao();
		sDao = new StudenteDao();
		
	}
	
	@Override
	public Action process(String serviceName, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		// TODO Auto-generated method stub
		if(serviceName.equalsIgnoreCase("LoginService")) {
		try {
			if(checkTrainerLogin(req) || checkStudentLogin(req)){
				//login effettuato con successo torna alla home
				return homePage;
			}else {
				//login fallito
				req.getSession().setAttribute("logError", "true");
				return errorPage;
			}
		}catch(SQLException e) {
			throw new ServletException(e);
		}
		
		}
		if(serviceName.equalsIgnoreCase("LogoutService")) {
			
			//chiamata metodo CanLogOut
			canLogout(req);
		}
		return errorPage;
	}
	
	@Override
	public Action getErrorAction() {
		// TODO Auto-generated method stub
		return errorPage;
	}
	
	public final boolean checkTrainerLogin(HttpServletRequest request) throws SQLException {

		List<FormatoreEntity> allFormatori= fDao.doRetrieveAll();
		String newEmail= request.getParameter("email");
		String newPassword = request.getParameter("password");
		
		
		if(allFormatori.size() != 0){
			for(FormatoreEntity a : allFormatori) {
				 if((a.getEmail().equals(newEmail)) && (a.getPassword().equals(newPassword))) {
					request.getSession().setAttribute("currentId", a.getId());
					request.getSession().setAttribute("validation", "true");
					request.getSession().setAttribute("role", "Formatore");
					return true;
				 }
			}
		}
		return false;
	}

	
	public final boolean checkStudentLogin(HttpServletRequest request) throws SQLException {

		List<StudenteEntity> allStudenti = sDao.doRetrieveAll();
		String newEmail= request.getParameter("email");
		String newPassword = request.getParameter("password");
		boolean isSubscribed = false;
		
		if(allStudenti.size() != 0){
			for(StudenteEntity a : allStudenti) {
				 if((a.getEmail().equals(newEmail)) && (a.getPassword().equals(newPassword))) {
					request.getSession().setAttribute("currentId", a.getId());
					request.getSession().setAttribute("validation", "true");
					request.getSession().setAttribute("role", "Studente");
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean canLogout(HttpServletRequest req) {
		HttpSession session= req.getSession();
		
		if((session != null) && (session.getAttribute("Validation")!=null) && (session.getAttribute("currentId")!=null) )
			{
		
				session.setAttribute("Validation", "false");
				session.removeAttribute("role");
				session.removeAttribute("currentId");
			
				return true;
		
		} else
		return false;
	}

	
}