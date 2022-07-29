package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;

public class PercorsoFormativoDao implements DaoInterface{
	
private Connection getConnection() throws SQLException{
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			 DataSource ds = (DataSource) envCtx.lookup("jdbc/formactds");
			 return ds.getConnection();
          
		} catch (NamingException e) {
			
			System.out.println("Error:" + e.getMessage());
			throw new SQLException(e);
		}
		
		
		
	}
                                    
	private static final String TABLE_NAME = "percorso_formativo";
	
	
	
	
	
	
	public int doSave(Object bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		PercorsoFormativoEntity percorso = (PercorsoFormativoEntity) bean;
		String insertSQL = "INSERT INTO " + PercorsoFormativoDao.TABLE_NAME
				+ " (IDPERCORSO_FORMATIVO, FORMATORE, NOME, AMBITO, DESCRIZIONE, INDICECONTENUTI, NUMEROLEZIONI, COSTO, VALIDATE)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int id = 0;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
			//id = this.nextId();
			preparedStatement.setInt(1, id);
			preparedStatement.setInt(2, percorso.getId_formatore());
			preparedStatement.setString(3, percorso.getNome());
			preparedStatement.setInt(4, percorso.getCategoria());
			preparedStatement.setString(5, percorso.getDescrizione());
			preparedStatement.setString(6, percorso.getIndice_contenuti());
			preparedStatement.setInt(7, percorso.getNum_lezioni());
			preparedStatement.setDouble(8, percorso.getCosto());
			preparedStatement.setInt(9, percorso.getValidate());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
			if ( rs.next()) {   // generazione nuova chiave primaria
				id = rs.getInt(1);
				
			}
			else { id = 1;//;
			}
			connection.setAutoCommit(false);
			connection.commit();
			
		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		percorso.setId(id);
		return id;
	}
		
	

	
	public boolean doDelete(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
	
         
		int result = 0;

		String deleteSQL = "DELETE FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE IDPERCORSO_FORMATIVO = ?";
        
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);

			result = preparedStatement.executeUpdate();

		} finally {
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return (result != 0);
	}
	

	
	public Object doRetrieveByKey(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
        
		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE IDPERCORSO_FORMATIVO = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, id);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setId(rs.getInt(1));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            bean.setValidate(rs.getInt("VALIDATE"));
	       
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return bean;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveByCosto(Double costo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE NOME = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setDouble(1, costo);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return corsi;
	}
	
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveByString(String str) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
		
        String nome = "%" + str +"%";
		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE NOME LIKE ? ";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, nome);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return corsi;
	}

	
	public ArrayList<PercorsoFormativoEntity> doRetrieveByName(String name) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME + " WHERE NOME = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, name);

		    rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return corsi;
	}
	
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAllByFormatore(String nome, String cognome) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		String selectSQL = "SELECT * "
				+ "FROM PERCORSO_FORMATIVO,FORMATORE "
				+ "WHERE FORMATORE.NOME = ? AND FORMATORE.COGNOME = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, nome);
			preparedStatement.setString(2, cognome);

			 rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
			 	
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return corsi;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAllByCategory(String categoria) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		String selectSQL = "SELECT * "
				+ "FROM PERCORSO_FORMATIVO,CATEGORIA "
				+ "WHERE CATEGORIA.NOME = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, categoria);
		

			 rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		return corsi;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAvailable() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		int disp = 1;
		
		String selectSQL = "SELECT * "
				+ "FROM percorso_formativo,disponibilita "
				+ "WHERE disponibilita.stato = ?";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, disp);

			 rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return corsi;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveByDisponibilita(String giorno) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
        
		int disp = 1;
		
		String selectSQL = "SELECT DISTINCT * "
				+ "FROM percorso_formativo,disponibilita "
				+ "WHERE disponibilita.giornoSettimana = ? AND percorso_formativo.idpercorso_formativo = disponibilita.percorsoFormativo";

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, giorno);

			 rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return corsi;
	}
	
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAll() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<PercorsoFormativoEntity> percorsi = new ArrayList<PercorsoFormativoEntity>();

		String selectSQL = "SELECT * FROM " + PercorsoFormativoDao.TABLE_NAME;

		if (percorsi != null && !percorsi.equals("")) {
			selectSQL += " ORDER BY IDPERCORSO_FORMATIVO";  
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();

				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
				percorsi.add(bean);
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return percorsi;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveAllByParams(String str , String min, String max, String giorno) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		
		ArrayList<PercorsoFormativoEntity> corsi = new ArrayList<PercorsoFormativoEntity>();
		String stringa = "%" + str +"%";
		
		String selectSQL = "";
		selectSQL += "SELECT DISTINCT";
		selectSQL += " percorso_formativo.idpercorso_formativo, percorso_formativo.formatore,"
				+    " percorso_formativo.nome, percorso_formativo.ambito, percorso_formativo.descrizione,"
				+    " percorso_formativo.indiceContenuti, percorso_formativo.numeroLezioni,"
				+    " percorso_formativo.costo";
		
		selectSQL += " FROM percorso_formativo,disponibilita";
		
		selectSQL += " WHERE percorso_formativo.idpercorso_formativo = percorso_formativo.idpercorso_formativo";
		
		if (str != null && !str.equals("")) {
			selectSQL += " AND percorso_formativo.nome LIKE ?";
		}
		if (min != null && !min.equals("")) {
			selectSQL += " AND percorso_formativo.costo >= ?";
		}
		if (max != null && !max.equals("")) {
			selectSQL += " AND percorso_formativo.costo <= ?";
		}
		if (giorno != null && !giorno.equals("")) {
			selectSQL += " AND disponibilita.giornoSettimana = ?";
		}
		
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			
			int i = 1;
			if (str != null && !str.equals("")) {
				preparedStatement.setString(i, stringa);
				i++;
			}
			if (min != null && !min.equals("")) {
				preparedStatement.setDouble(i , Double.parseDouble(min));
				i++;
			}
			if (max != null && !max.equals("")) {
				preparedStatement.setDouble(i, Double.parseDouble(max));
				i++;
			}
			if (giorno != null && !giorno.equals("")) {
				preparedStatement.setString(i, giorno);
				i++;
			}
			
			 rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();
				
				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            corsi.add(bean);
			    
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return corsi;
	}
	
	public ArrayList<PercorsoFormativoEntity> doRetrieveIscrizioniStudente(int idStudente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList<PercorsoFormativoEntity> iscrizioni = new ArrayList<PercorsoFormativoEntity>();

		String selectSQL = "SELECT DISTINCT * FROM percorso_formativo,iscrizione"
								+" WHERE percorso_formativo.idpercorso_formativo = iscrizione.percorso_formativo"
									+ " AND iscrizione.studente = ?";

		if (iscrizioni != null && !iscrizioni.equals("")) {
			selectSQL += " ORDER BY STUDENTE";  
		}

		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(selectSQL , Statement.RETURN_GENERATED_KEYS);
			
			// Problema: questi 2 comandi erano invertiti:
			preparedStatement.setInt(1, idStudente);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				PercorsoFormativoEntity bean = new PercorsoFormativoEntity();

				bean.setId(rs.getInt("IDPERCORSO_FORMATIVO"));
				bean.setId_formatore(rs.getInt("FORMATORE"));
				bean.setNome(rs.getString("NOME"));
				bean.setCategoria(rs.getInt("AMBITO"));
				bean.setDescrizione(rs.getString("DESCRIZIONE"));
			    bean.setIndice_contenuti(rs.getString("INDICECONTENUTI"));
	            bean.setNum_lezioni(rs.getInt("NUMEROLEZIONI"));
	            bean.setCosto(rs.getDouble("COSTO"));
	            iscrizioni.add(bean);
			}

		}  finally {
			closeResultSet(rs);
			closePreparedStatement(preparedStatement);
			closeConnection(connection);
		}
		
		return iscrizioni;
	}

	protected final void closeConnection(Connection c) {
		if( c != null)
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	protected final void closePreparedStatement(PreparedStatement p) {
		if( p != null)
			try {
				p.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	protected final void closeResultSet(ResultSet rs) {
		if( rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
	}


	@Override
	public boolean update(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}

