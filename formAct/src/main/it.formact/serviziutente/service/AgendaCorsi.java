package serviziutente.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;

public class AgendaCorsi {
	
	PercorsoFormativoEntity p;
	ArrayList<PercorsoFormativoEntity> lista;
	ArrayList<IscrizioneEntity> iscrizioniStudente;
	IscrizioneDao daoIsc;
	PercorsoFormativoDao dao;
	
	
	public AgendaCorsi() throws SQLException {
		p = new PercorsoFormativoEntity();
		lista = new ArrayList<PercorsoFormativoEntity>();
		iscrizioniStudente = new ArrayList<IscrizioneEntity>();
		daoIsc = new IscrizioneDao();
		dao = new PercorsoFormativoDao();
		getIscrizioni();
	}
	
	
	public ArrayList<IscrizioneEntity> getIscrizioni() throws SQLException {
		return iscrizioniStudente = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveByStudent(1);
	}
	
	
	
	public ArrayList<PercorsoFormativoEntity> getLunedi() throws SQLException{
		
		ArrayList<PercorsoFormativoEntity> lunedi = new ArrayList<PercorsoFormativoEntity>();
	
		
		for (  int i =0 ; i< iscrizioniStudente.size() ; i++ ) {
			
			if(iscrizioniStudente.get(i).getGiorno().equalsIgnoreCase("luned�")) {
				 lunedi.add((PercorsoFormativoEntity) dao.doRetrieveByKey(iscrizioniStudente.get(i).getPercorsoFormativo()));
				
			}
		}
		
			return lunedi;
		
	
	}
		
		public ArrayList<PercorsoFormativoEntity> getMartedi() throws SQLException{
			
			ArrayList<PercorsoFormativoEntity> martedi = new ArrayList<PercorsoFormativoEntity>();
		
			
			for (  int i =0 ; i< iscrizioniStudente.size() ; i++ ) {
				
				if(iscrizioniStudente.get(i).getGiorno().equalsIgnoreCase("marted�")) {
					 martedi.add((PercorsoFormativoEntity) dao.doRetrieveByKey(iscrizioniStudente.get(i).getPercorsoFormativo()));
					
				}
			}
			return martedi;
			
		}
			
			
			public ArrayList<PercorsoFormativoEntity> getMercoledi() throws SQLException {
				
				ArrayList<PercorsoFormativoEntity> mercoledi = new ArrayList<PercorsoFormativoEntity>();
			
				
				for (  int i =0 ; i< iscrizioniStudente.size() ; i++ ) {
					
					if(iscrizioniStudente.get(i).getGiorno().equalsIgnoreCase("mercoled�")) {
						 mercoledi.add((PercorsoFormativoEntity) dao.doRetrieveByKey(iscrizioniStudente.get(i).getPercorsoFormativo()));
						
					}
				
				}
					return mercoledi;
			}
				
				
				public ArrayList<PercorsoFormativoEntity> getGiovedi() throws SQLException{
					
					ArrayList<PercorsoFormativoEntity> giovedi = new ArrayList<PercorsoFormativoEntity>();
				
					
					for (  int i =0 ; i< iscrizioniStudente.size() ; i++ ) {
						
						if(iscrizioniStudente.get(i).getGiorno().equalsIgnoreCase("gioved�")) {
							 giovedi.add((PercorsoFormativoEntity) dao.doRetrieveByKey(iscrizioniStudente.get(i).getPercorsoFormativo()));
							
						}
					}
					return giovedi;
					
					
				}
					
					
					public ArrayList<PercorsoFormativoEntity> getVenerdi() throws SQLException {
						
						ArrayList<PercorsoFormativoEntity> venerdi = new ArrayList<PercorsoFormativoEntity>();
					
						
						for (  int i =0 ; i< iscrizioniStudente.size() ; i++ ) {
							
							if(iscrizioniStudente.get(i).getGiorno().equalsIgnoreCase("venerd�")) {
								 venerdi.add((PercorsoFormativoEntity) dao.doRetrieveByKey(iscrizioniStudente.get(i).getPercorsoFormativo()));
								
							}
						
						
					}
						return venerdi;
	
						}
					
					
					
					
				}
