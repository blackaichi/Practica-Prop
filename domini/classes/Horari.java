package domini.classes;

import domini.classes.*;
import domini.restriccions.*;
import utils.*;
import java.util.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class Horari {
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  STATIC  /////////////////////////////////////
	/**
	 * Enmagatzema TOTS els horaris candidats donats el nom d'un pla
	 * d'estudis i el d'un campus concrets.
	 */
	static private Map<String, Map<String, HashSet<Estructura>>> HorarisCandidats;
	
	/**
	 * Referencien a l'ultim pla d'estudis i campus pels quals
	 * s'ha generat algun horari.
	 */
	static private PlaEstudis plaEstudis;
	static private Campus campus;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Genera una copia totalment independent del set indicat.
	 * @param toCopy Referencia al set a copiar.
	 * @return Una copia identica.
	 */
	static private HashSet<SessioGAssignada> copySGA(HashSet<SessioGAssignada> toCopy){
		if(toCopy == null) return null;
		
		HashSet<SessioGAssignada> cloned = new HashSet<>();
		for(SessioGAssignada sessio: toCopy) cloned.add(sessio);
		
		return cloned;
	}
	
	/**
	 * Genera una copia totalment independent del set indicat.
	 * @param toCopy Referencia al set a copiar.
	 * @return Una copia identica.
	 */
	static private HashSet<SessioSGAssignada> copySSGA(HashSet<SessioSGAssignada> toCopy){
		if(toCopy == null) return null;
		
		HashSet<SessioSGAssignada> cloned = new HashSet<>();
		for(SessioSGAssignada sessio: toCopy) cloned.add(sessio);
		
		return cloned;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  GENERADOR  ////////////////////////////////////
	/**
	 * Funció recursiva que, en definitiva, genera l'horari adient.
	 * @param index Controla la sessio a processar.
	 * @param GOcup Controla les sessions de grup ocupades.
	 * @param SGOcup Controla les sessions de SubGrup ocupades.
	 * @throws Exception
	 */
	static private void backTracking(Estructura horari, HashSet<SessioGAssignada> sessionsG, HashSet<SessioSGAssignada> sessionsSG,  int nHoraris) throws Exception {
		//Cas base: tants horaris generats com nHoraris senyala: si ja tenim nhorari, no és continua.
		if(HorarisCandidats.get(plaEstudis.getNom()).get(campus.getNom()).size() >= nHoraris) return;
		
		//Obtenció de la sessió que toca colocar:
		Pair<SessioGAssignada, SessioSGAssignada> corrent = nextSessio(sessionsG, sessionsSG);
		
		//Cas base: totes les sessions han sigut assignades, l'horari esta complert:  tenim un HORARI CANDIDAT!
		if(corrent.isNull()) HorarisCandidats.get(plaEstudis.getNom()).get(campus.getNom()).add(horari);
		
		 //Altrament s'ha de trobar una posició adequada per a la sessio:
		else for(int dia = 0; dia < 7; dia++) { //Per cada dia de la setmana:
			for(int hora = 0; hora < 24; hora++){ //Per cada hora del dia:
				Segment segment = new Segment(corrent.first, corrent.second);
				Estructura updatedHorari = horari.getCopy(); //Copia de l'horari SENSE modificar.
				
				if(Horari.tryToCommit(updatedHorari, segment, dia, hora, true, false) == 0) {
					//Si s'ha lograt colocar sense violar cap restricció:
					HashSet<SessioGAssignada> updatedSessionsG = copySGA(sessionsG);
					HashSet<SessioSGAssignada> updatedSessionsSG = copySSGA(sessionsSG);
					
					//RECURSIVITAT!!! Kernel del backTracking:
					kill(updatedSessionsG, updatedSessionsSG); //S'elimina la sessió just assignada.
					backTracking(updatedHorari, updatedSessionsG, updatedSessionsSG, nHoraris);
				}
			}
		}
	}
	
	/**
	 * Donat un campus, i un pla d'estudis, genera l'horari corresponent.
	 * @param plaEstudis Referencia al pla d'estudis del qual se n'ha de fer l'horari.
	 * @param campus Referencia el campus sobre el qual s'ha d'aplicar l'horari.
	 * @throws Exception
	 */
	static public int GENERADOR(PlaEstudis plaEstudis, Campus campus, HashSet<String> flags, int nHoraris, boolean purge) throws Exception{
		int checker;
		if((checker = inicialitzaEntorn(plaEstudis, campus, purge)) != 0) return checker;
		
		HashSet<SessioGAssignada> sessionsDeGrup = new HashSet<>(plaEstudis.getSessionsGrupA());
		HashSet<SessioSGAssignada> sessionsDeSubGrup = new HashSet<>(plaEstudis.getSessionsSubGrupA());
		Estructura esquelet =  new Estructura(plaEstudis, campus);
		esquelet.setFlags(flags);
		
		int totalHoraris = nHoraris + getHoraris(plaEstudis.getNom(), campus.getNom()).size();
		backTracking(esquelet, sessionsDeGrup, sessionsDeSubGrup, totalHoraris);
		return 0;
	}
		
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/**
	 * Inicialitza, si no hi és ja, l'espai al Map d'horari candidats per als
	 * possibles horaris generats.
	 * @param plaEstudis Referencia al pla d'estudis de l'horari.
	 * @param campus Referencia al campus de l'horari.
	 */
	static private int inicialitzaEntorn(PlaEstudis plaEstudis, Campus campus, boolean purge) {
		if(plaEstudis == null) return -1; //TODO: El pla d'estudis no pot ser null;
		else if(campus == null) return -1; //TODO: El campus no pot ser null;
		
		if(HorarisCandidats == null) HorarisCandidats = new HashMap<>();
		if(!HorarisCandidats.containsKey(plaEstudis.getNom())) HorarisCandidats.put(plaEstudis.getNom(), new HashMap<>());
		if(!HorarisCandidats.get(plaEstudis.getNom()).containsKey(campus.getNom()))
			HorarisCandidats.get(plaEstudis.getNom()).put(campus.getNom(), new HashSet<Estructura>());
		
		if(purge) HorarisCandidats.get(plaEstudis.getNom()).get(campus.getNom()).clear();
		Horari.plaEstudis = plaEstudis;
		Horari.campus = campus;
		
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////			
	/**
	 * Retorna, si hi és, aquell horari que correspon al pla d'estudis entrat,
	 * i al campus indicat.
	 * @param plaEstudis Es el nom del pla d'Estudis al qual pertany l'horari.
	 * @param campus Es el nom del campus al qual s'aplica l'horari.
	 * @return Un horari que conté tots els horaris generats per aquest
	 * pla d'estudis i campus indicats.
	 */
	static public HashSet<Estructura> getHoraris(String plaEstudis, String campus) {
		if(plaEstudis == null || campus == null) return null;
		
		if(HorarisCandidats.get(plaEstudis) == null) return null;
		else return HorarisCandidats.get(plaEstudis).get(campus);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Elimina l'element dels hashset situal a la posició index.
	 * @param index indica la posicio de l'element a eliminar.
	 */
	static private void kill(HashSet<SessioGAssignada> sessionsDeGrup, HashSet<SessioSGAssignada> sessionsDeSubGrup) {
		if(!sessionsDeGrup.isEmpty()) {
			SessioGAssignada sessio = sessionsDeGrup.iterator().next();
			sessionsDeGrup.remove(sessio);
		}
		else if(!sessionsDeSubGrup.isEmpty()) {
			SessioSGAssignada sessio = sessionsDeSubGrup.iterator().next();
			sessionsDeSubGrup.remove(sessio);
		}
	}
		
	/**
	 * Intenta colocar el segment indicat, per al seu horari, al dia i hora d'inici indicats.
	 * En cas de poder-ho fer, i per tant no violar cap restricció, és el commit qui
	 * indica si fer la modificació definitiva, o bé simplement ignorar-la.
	 * @param segment Referencia al segment a desplaçar.
	 * @param dia Indica a quin dia de l'horari desplaçar.
	 * @param horaIni Indica, sobre el dia, en quina hora fer el canvi.
	 * @param commit Indica si el canvi s'ha de dur a terme, o si simplement es una comprovació.
	 * @return Retorna 0 si la modificació es possible; altrament retorna el codi d'excepció
	 * que indica quina restricció ha sigut violada.
	 */
	static public int tryToCommit(Estructura horari, Segment segment, int dia, int horaIni, boolean commit, boolean force) throws Exception {
		int checker = 0;
		
		if(horari.getFlagState("ALINEAMENT") && //Si el flag d'alineament està activat:
		  (checker = Alineament.checkAlineament(segment.getSessio().first, segment.getSessio().second, horaIni)) != 0)
			if(!force) return checker;

		if(horari.getFlagState("HORES_APTES") && //Si el flag d'hores aptes està activat:
		  (checker = HoresAptes.checkHoresAptes(segment.getSessio().first, segment.getSessio().second, dia, horaIni)) != 0)
			if(!force) return checker;

		if(horari.getFlagState("SOLAPAMENTS") && //Si el flag de solapaments està activat:
		  (checker = Solapaments.checkSolapament(horari, segment.getSessio().first, segment.getSessio().second, dia, horaIni)) != 0)
			if(!force) return checker;
		
		//RESTRICCIÓ D'AULA ADIENT: és sempre obligatoria
		Aula seleccionada = AulaAdient.seleccionaAulaAdient(horari, campus.getAllAules(), segment.getSessio().first, segment.getSessio().second, dia, horaIni);
		if(seleccionada == null) return -1; //TODO: no hi ha cap aula disponible durant la franja requerida.
		
		if(commit) { //En cas de voler porcedir a assignar la sessio:
			segment.setData(new Data(dia, horaIni));
			segment.setAula(seleccionada);
			segment.setEstructura(horari);
			
			int durada = segment.getSessio().snull()? segment.getSessio().first.getSessioGrup().getHores() : 
													  segment.getSessio().second.getSessioSubGrup().getHores();
			
			for(int incr = 0; incr < durada; incr++) horari.setSegment(segment, dia, horaIni+incr);
		}
		
		return 0;
	}

	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna la sessio situada a la posició relativa index.
	 * Basicament permet treballar amb ambdos HashSets com
	 * si fossin un de sol.
	 * @param index Posicio que es vol cercar.
	 * @return La sessió corresponent.
	 */
	static private Pair<SessioGAssignada, SessioSGAssignada>
	nextSessio(HashSet<SessioGAssignada> sessionsDeGrup, HashSet<SessioSGAssignada> sessionsDeSubGrup) {
		Pair<SessioGAssignada, SessioSGAssignada> element = new Pair<>();
		if(!sessionsDeGrup.isEmpty()) //En cas d'estar accedint a les Sessions de Grup
			element.first = sessionsDeGrup.iterator().next();
		else if(!sessionsDeSubGrup.isEmpty()) //En cas d'accedir a les sessions de SubGrup
			element.second = sessionsDeSubGrup.iterator().next();
		
		return element;
	}
}
