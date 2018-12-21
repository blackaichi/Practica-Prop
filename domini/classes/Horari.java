package domini.classes;

import domini.classes.*;
import domini.restriccions.*;
import domini.tools.Estructura;
import domini.tools.Segment;
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
	private static Horari current;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  ATRIBUTS  /////////////////////////////////////
	
	/**
	 * Enmagatzema TOTS els horaris candidats donats el nom d'un pla
	 * d'estudis i el d'un campus concrets.
	 */
	private Map<String, Map<String, HashSet<Estructura>>> HorarisCandidats;
	
	/**
	 * Referencien a l'ultim pla d'estudis i campus pels quals
	 * s'ha generat algun horari.
	 */
	private PlaEstudis plaEstudis;
	private Campus campus;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Genera una copia totalment independent del set indicat.
	 * @param toCopy Referencia al set a copiar.
	 * @return Una copia identica.
	 */
	private HashSet<SessioGAssignada> copySGA(HashSet<SessioGAssignada> toCopy){
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
	private HashSet<SessioSGAssignada> copySSGA(HashSet<SessioSGAssignada> toCopy){
		if(toCopy == null) return null;
		
		HashSet<SessioSGAssignada> cloned = new HashSet<>();
		for(SessioSGAssignada sessio: toCopy) cloned.add(sessio);
		
		return cloned;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////  INSTANCIADORA  //////////////////////////////////
	/**
	 * Constructora de la classe Horari.
	 */
	private Horari() {
		this.HorarisCandidats = new HashMap<>();
	}
	
	/**
	 * Controla que només hi hagia una instancia concurrantment.
	 * @return La instancia d'horari.
	 */
	static public Horari getInstance() {
		if(current == null) current = new Horari();
		return Horari.current;
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
	private void backTracking(Estructura horari, HashSet<SessioGAssignada> sessionsG, HashSet<SessioSGAssignada> sessionsSG, NSessions nSessions, int nHoraris) throws Exception {
		//Cas base: tants horaris generats com nHoraris senyala: si ja tenim nhorari, no és continua.
		if(HorarisCandidats.get(plaEstudis.getNom()).get(campus.getNom()).size() >= nHoraris) return;
		
		//Obtenció de la sessió que toca colocar:
		Pair<SessioGAssignada, SessioSGAssignada> corrent = nextSessio(sessionsG, sessionsSG);
		
		//Cas base: totes les sessions han sigut assignades, l'horari esta complert:  tenim un HORARI CANDIDAT!
		if(corrent.isNull()) HorarisCandidats.get(plaEstudis.getNom()).get(campus.getNom()).add(horari);
		
		 //Altrament s'ha de trobar una posició adequada per a la sessio:
		else for(int dia = 0; dia < 7; dia++) if(!horari.getFlagState("D_LECTIU") || plaEstudis.checkDiaLectiu(dia)){
			for(int hora = 0; hora < 24; hora++) if(!horari.getFlagState("H_LECTIU") || plaEstudis.getLectiuDia(dia)[hora]){
				Segment segment = new Segment(corrent.first, corrent.second);
				Estructura updatedHorari = horari.getCopy(); //Copia de l'horari SENSE modificar.
				NSessions updatedNSessions = nSessions.getCopy(); //Copia de NSessions SENSE modificar.
				
				if(tryToCommit(updatedHorari, updatedNSessions, segment, dia, hora, true, false).isEmpty()) {
					//Si s'ha lograt colocar sense violar cap restricció:
					HashSet<SessioGAssignada> updatedSessionsG = copySGA(sessionsG);
					HashSet<SessioSGAssignada> updatedSessionsSG = copySSGA(sessionsSG);
					
					//RECURSIVITAT!!! Kernel del backTracking:
					kill(updatedSessionsG, updatedSessionsSG, updatedNSessions); //S'elimina la sessió just assignada.
					backTracking(updatedHorari, updatedSessionsG, updatedSessionsSG, updatedNSessions, nHoraris);
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
	public int GENERADOR(PlaEstudis plaEstudis, Campus campus, HashSet<String> flags, int nHoraris, boolean purge) throws Exception{
		int checker;
		if((checker = inicialitzaEntorn(plaEstudis, campus, purge)) != 0) return checker;
		
		NSessions nSessions = new NSessions();
		HashSet<SessioGAssignada> sessionsDeGrup = new HashSet<>(plaEstudis.getSessionsGrupA());
		HashSet<SessioSGAssignada> sessionsDeSubGrup = new HashSet<>(plaEstudis.getSessionsSubGrupA());
		Estructura esquelet =  new Estructura(plaEstudis, campus);
		esquelet.setFlags(flags);
		
		int totalHoraris = nHoraris + getHoraris(plaEstudis.getNom(), campus.getNom()).size();
		backTracking(esquelet, sessionsDeGrup, sessionsDeSubGrup, nSessions, totalHoraris);
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
	private int inicialitzaEntorn(PlaEstudis plaEstudis, Campus campus, boolean purge) {
		if(plaEstudis == null) return 170;
		else if(campus == null) return 171;
		
		if(!HorarisCandidats.containsKey(plaEstudis.getNom())) HorarisCandidats.put(plaEstudis.getNom(), new HashMap<>());
		if(!HorarisCandidats.get(plaEstudis.getNom()).containsKey(campus.getNom()))
			HorarisCandidats.get(plaEstudis.getNom()).put(campus.getNom(), new HashSet<Estructura>());
		
		if(purge) HorarisCandidats.get(plaEstudis.getNom()).get(campus.getNom()).clear();
		this.plaEstudis = plaEstudis;
		this.campus = campus;
		
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
	public HashSet<Estructura> getHoraris(String plaEstudis, String campus) {
		if(plaEstudis == null || campus == null) return null;
		
		if(HorarisCandidats.get(plaEstudis) == null) return null;
		else return HorarisCandidats.get(plaEstudis).get(campus);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////	
	/**
	 * Actualitza al registre d'horaris el nom d'un pla d'estudis.
	 * @param oldPlaEstudis Identifica al pla d'estudis abans de la modificació.
	 * @param newPlaEstudis Identifica al pla d'estudis despres de la modificació.
	 */
	public void actualitzaPlaEst(String oldPlaEstudis, String newPlaEstudis) {
		if(HorarisCandidats.containsKey(oldPlaEstudis)) {
			HorarisCandidats.put(newPlaEstudis, HorarisCandidats.get(oldPlaEstudis));
			HorarisCandidats.remove(oldPlaEstudis);
		}
	}
	
	/**
	 * Actualitza al registre d'horaris el nom d'un campus.
	 * @param oldCampus Identifica al campus abans de la modificació.
	 * @param newCampus Identifica al campus despres de la modificació.
	 */
	public void actualitzaCampus(String oldCampus, String newCampus) {
		for(String key : HorarisCandidats.keySet()) {
			if(HorarisCandidats.get(key).containsKey(oldCampus)) {
				HorarisCandidats.get(key).put(newCampus, HorarisCandidats.get(key).get(oldCampus));
				HorarisCandidats.get(key).remove(oldCampus);
			}
		}
	}
	
	/**
	 * Elimina l'element dels hashset situal a la posició index.
	 * @param index indica la posicio de l'element a eliminar.
	 */
	private void kill(HashSet<SessioGAssignada> sessionsDeGrup, HashSet<SessioSGAssignada> sessionsDeSubGrup, NSessions nSessions) {
		if(!sessionsDeGrup.isEmpty()) {
			SessioGAssignada sessio = sessionsDeGrup.iterator().next();
			if(nSessions.readyToDie(sessio, null)) sessionsDeGrup.remove(sessio);
		}
		else if(!sessionsDeSubGrup.isEmpty()) {
			SessioSGAssignada sessio = sessionsDeSubGrup.iterator().next();
			if(nSessions.readyToDie(null, sessio)) sessionsDeSubGrup.remove(sessio);
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
	public HashSet<Integer> tryToCommit(Estructura horari, NSessions nSessions, Segment segment, int dia, int horaIni, boolean commit, boolean force) throws Exception {
		HashSet<Integer> history = new HashSet<>();
		
		history.add(Alineament.checkAlineament(segment.getSessio().first, segment.getSessio().second, horaIni, horari.getFlags()));

		history.add(HoresAptes.checkHoresAptes(segment.getSessio().first, segment.getSessio().second, dia, horaIni, horari.getFlags()));

		history.add(Solapaments.checkSolapament(horari, segment.getSessio().first, segment.getSessio().second, dia, horaIni));
		
		history.add(nSessions.checkNSessions(segment.getSessio().first, segment.getSessio().second, dia, horari.getFlags(), force));
		
		//RESTRICCIÓ D'AULA ADIENT: és sempre obligatoria
		Aula seleccionada = AulaAdient.seleccionaAulaAdient(horari, campus.getAllAules(), segment.getSessio().first, segment.getSessio().second, dia, horaIni);
		if(seleccionada == null) history.add(261);
		
		history.removeIf(item -> item.intValue() == 0); //Cas base: si tot va bé, unicament conté el 0.
		if(commit && (force || history.isEmpty())) { //En cas de voler porcedir a assignar la sessio:
			segment.setData(new Data(dia, horaIni));
			segment.setAula(seleccionada);
			segment.setEstructura(horari);
			
			int durada = segment.getSessio().snull()? segment.getSessio().first.getSessioGrup().getHores() : 
													  segment.getSessio().second.getSessioSubGrup().getHores();
			
			for(int incr = 0; incr < durada; incr++) horari.setSegment(segment, dia, horaIni+incr);
		}
		
		return history;
	}
	
	/**
	 * Afegeix una estructura buida al map d'horaris.
	 * @param plaEstudis Pla d'estudis del qual serà l'horari.
	 * @param campus Campus del qual serà l'horari.
	 * @return Iteració dins del set on s'ubica l'estructura generada.
	 */
	public int generarEntorn(String plaEstudis, String campus) {
		try {
			inicialitzaEntorn(PlaEstudis.getPlaEstudis(plaEstudis), Campus.getCampus(campus), false);
			Estructura entorn = new Estructura(PlaEstudis.getPlaEstudis(plaEstudis), Campus.getCampus(campus));
			HorarisCandidats.get(plaEstudis).get(campus).add(entorn);
			
			int iter = 0;
			for(Estructura struct : HorarisCandidats.get(plaEstudis).get(campus)) {
				if(struct == entorn) break;
				else iter++;
			}
			
			return ++iter;
		}
		catch(Exception e) {
			return 0;
		}
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