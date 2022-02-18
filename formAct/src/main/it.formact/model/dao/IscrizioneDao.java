package model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.IscrizioneEntity;


public class IscrizioneDao implements DaoInterface{
	
private static DataSource ds;
    
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/formactds");
          
		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
                                    
	private static final String TABLE_NAME = "iscrizione";
	
	
		
	
		public void doSave(Object bean) throws SQLException {
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			IscrizioneEntity iscrizione = (IscrizioneEntity) bean;
			
			String insertSQL = "INSERT INTO " + IscrizioneDao.TABLE_NAME
					+ " (STUDENTE, PERCORSOFORMATIVO, GIORNO, ORARIO, METODOPAGAMENTO, DATAPAGAMENTO)"
					+ " VALUES (?, ?, ?, ?, ?, ?)";
			
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, iscrizione.getStudente());
				preparedStatement.setInt(2, iscrizione.getPercorsoFormativo());
				preparedStatement.setString(3, iscrizione.getGiorno());
				preparedStatement.setTime(4, iscrizione.getOrario());
				preparedStatement.setString(5, iscrizione.getMetodoPagamento());
				preparedStatement.setDate(6,iscrizione.getDataPagamento());
				preparedStatement.executeUpdate();
				connection.setAutoCommit(false);
				connection.commit();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
		}
			
		

		
		public boolean doDelete(int studente) throws SQLException {   // eliminazione iscrizione per id studente, si dovrebbe eliminare 
																		//in automatico grazie ai vincoli delete on cascade su chiave esterna studente
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
		
	         
			int result = 0;

			String deleteSQL = "DELETE FROM " + IscrizioneDao.TABLE_NAME + " WHERE STUDENTE = ?";
	        
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, studente);

				result = preparedStatement.executeUpdate();

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return (result != 0);
		}
		

		/**
		 * 
		 * Modificato
		 * 
		 */
		public Object doRetrieveByStudent(int studente) throws SQLException {    // restituisce una entry di iscrizione per id studente, #iscrizione di uno studente#
			Connection connection = null;
			PreparedStatement preparedStatement = null;
            
			
			
			ArrayList<IscrizioneEntity> iscrizioni = new ArrayList<IscrizioneEntity>();
	        
			String selectSQL = "SELECT * FROM " + IscrizioneDao.TABLE_NAME + " WHERE STUDENTE = ?";
			
			if (iscrizioni != null && !iscrizioni.equals("")) {
				selectSQL += " ORDER BY STUDENTE";  
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				preparedStatement.setInt(1, studente);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					IscrizioneEntity bean = new IscrizioneEntity();
					
					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setPercorsoFormativo(rs.getInt("PERCORSOFORMATIVO"));
					bean.setGiorno(rs.getString("GIORNO"));
					bean.setOrario(rs.getTime("ORARIO"));
					bean.setMetodoPagamento(rs.getString("METODOPAGAMENTO"));
					bean.setDataPagamento(rs.getDate("DATAPAGAMENTO"));
		            
					iscrizioni.add(bean);
				    
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return iscrizioni;
		}
		

		
		public ArrayList<IscrizioneEntity> doRetrieveAll() throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			ArrayList<IscrizioneEntity> iscrizioni = new ArrayList<IscrizioneEntity>();

			String selectSQL = "SELECT * FROM " + IscrizioneDao.TABLE_NAME;

			if (iscrizioni != null && !iscrizioni.equals("")) {
				selectSQL += " ORDER BY STUDENTE";  
			}

			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);

				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					
					IscrizioneEntity bean = new IscrizioneEntity();

					bean.setStudente(rs.getInt("STUDENTE"));
					bean.setPercorsoFormativo(rs.getInt("PERCORSOFORMATIVO"));
					bean.setGiorno(rs.getString("GIORNO"));
					bean.setOrario(rs.getTime("ORARIO"));
					bean.setMetodoPagamento(rs.getString("METODOPAGAMENTO"));
					bean.setDataPagamento(rs.getDate("DATAPAGAMENTO"));
					iscrizioni.add(bean);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			return iscrizioni;
		}

		
		
		



		@Override
		public Object doRetrieveByKey(int id) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}
		
		
	}
	
	


