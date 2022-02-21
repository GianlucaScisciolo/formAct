package serviziutente.service;

import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.Disponibilit�Dao;
import model.dao.IscrizioneDao;
import model.dao.PercorsoFormativoDao;
import model.entity.Disponibilit�Entity;
import model.entity.IscrizioneEntity;
import model.entity.PercorsoFormativoEntity;

public class AgendaCorsi {
	
	PercorsoFormativoDao daoP = new PercorsoFormativoDao();
	Disponibilit�Dao daoDisp = new Disponibilit�Dao();
	IscrizioneDao daoIsc = new IscrizioneDao();
	ArrayList<IscrizioneEntity> iscrizioni ; 
	ArrayList<Disponibilit�Entity> disps;
	
	
	
	public AgendaCorsi() throws SQLException {
		
		
		
	}
	
	
		
		public ArrayList<Calendario> getLunedi(int studente) throws SQLException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "luned�");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario() , iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
	
		public ArrayList<Calendario> getMartedi(int studente) throws SQLException {
				
				iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "marted�");
				ArrayList<Calendario> c = new ArrayList<Calendario>();
				for( int i=0;i< iscrizioni.size(); i++) {
					 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
					 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
					 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
				}
				
				
				
				return c;
			}
		
		public ArrayList<Calendario> getMercoledi(int studente) throws SQLException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "mercoled�");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
		
		public ArrayList<Calendario> getGiovedi(int studente) throws SQLException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "gioved�");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
		
		public ArrayList<Calendario> getVenerdi(int studente) throws SQLException {
			
			iscrizioni = (ArrayList<IscrizioneEntity>) daoIsc.doRetrieveAllByDay(studente, "venerd�");
			ArrayList<Calendario> c = new ArrayList<Calendario>();
			for( int i=0;i< iscrizioni.size(); i++) {
				 PercorsoFormativoEntity p = new PercorsoFormativoEntity();
				 p =  (PercorsoFormativoEntity) daoP.doRetrieveByKey(iscrizioni.get(i).getPercorsoFormativo());
				 c.add(new Calendario(p.getNome(),iscrizioni.get(i).getOrario(), iscrizioni.get(i).getStudente()));
			}
			
			
			
			return c;
		}
			
	
	

	
					
					
					
					
				}
