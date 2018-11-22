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
	
	/**
	 * Retorna true si, i només si, totes les restriccions de Grup es compleixen
	 * per un grup en un dia i hora donats.
	 * @param horari Referencia sobre quin horari comrovar les restricicons.
	 * @param dia Indica el dia.
	 * @param horaIni Indica l'hora.
	 * @param sessio Referencia al grup.
	 * @return Un booleà.
	 */
	static private boolean checkAllRestriccionsDeGrup(Estructura horari, int dia, int horaIni, SessioGAssignada sessio) {
		if((!sessio.getGrup().enRang(horaIni)) ||
		   (sessio.getGrup().getRestriccioHoresAptes().checkPotFerClasse(dia, horaIni) != 0)) return false;
		
		for(Segment segment: horari.getAllSegments(dia, horaIni)) {
			if((!segment.getSessio().fnull() &&
				sessio.getGrup().getSolapaments().checkPotSolapar(segment.getSessio().first.getGrup().getAssignatura().getNom(), segment.getSessio().first.getGrup().getNumero()) != 0) ||
				(!segment.getSessio().snull() &&
				sessio.getGrup().getSolapaments().checkPotSolapar(segment.getSessio().second.getSubGrup().getGrup().getAssignatura().getNom(), segment.getSessio().second.getSubGrup().getNumero()) != 0))
				return false;
		}
					
		return true;	
	}
	
	/**
	 * Retorna true si, i només si, totes les restriccions de SubGrups es compleixe
	 * per un subgrup en un dia i hora donats.
	 * @param dia Indica el dia.
	 * @param horaIni Indica l'hora.
	 * @param sessio Referencia al grup.
	 * @return Un booleà.
	 */
	static private boolean checkAllRestriccionsDeSubGrup(Estructura horari, int dia, int horaIni, SessioSGAssignada sessio) {		
		//Si un Grup es de mati, per exemple, és logic que tots els seus subGrups també ho son.
		if((!sessio.getSubGrup().enRang(horaIni)) ||
		   (sessio.getSubGrup().getRestriccioHoresAptes().checkPotFerClasse(dia, horaIni) != 0))
			return false;
		
		for(Segment segment: horari.getAllSegments(dia, horaIni))
			if((!segment.getSessio().fnull() &&
				sessio.getSubGrup().getSolapaments().checkPotSolapar(segment.getSessio().first.getGrup().getAssignatura().getNom(), segment.getSessio().first.getGrup().getNumero()) != 0) ||
				(!segment.getSessio().snull() &&
				sessio.getSubGrup().getSolapaments().checkPotSolapar(segment.getSessio().second.getSubGrup().getGrup().getAssignatura().getNom(), segment.getSessio().second.getSubGrup().getNumero()) != 0))
				return false;
		
		return true;
	}
	
	/**
	 * Retorna true si, i només si, totes les restriccions d'assignatura es compleixen;
	 * altrament retorna false;
	 * @param dia Indica el dia.
	 * @param horaIni Indica l'hora.
	 * @param assignatura Referencia a l'assignatura a comprovar.
	 * @return un booleà.
	 */
	static private boolean checkAllRestriccionsAssignatura(Estructura horari, int dia, int horaIni, Assignatura assignatura) {
		if(assignatura.getHoresAptes().checkPotFerClasse(dia, horaIni) != 0) return false;
		else for(Segment segment: horari.getAllSegments(dia, horaIni))
			if((!segment.getSessio().fnull() &&
				assignatura.getSolapaments().checkPotSolapar(segment.getSessio().first.getGrup().getAssignatura().getNom(), segment.getSessio().first.getGrup().getNumero()) != 0) ||
				(!segment.getSessio().snull() &&
				assignatura.getSolapaments().checkPotSolapar(segment.getSessio().second.getSubGrup().getGrup().getAssignatura().getNom(), segment.getSessio().second.getSubGrup().getNumero()) != 0))
				return false;
		
		return true;
	}
	
	/**
	 * Comprova que, donada una hora d'inici, les restriccions es compleixin per totes
	 * les hores que dura la sessió.
	 * @param dia identifica el dia en el que es preten afegir la sessio.
	 * @param horaIni precisa la hora d'inici de la sessio.
	 * @param index Senyala en el set quina sessió és.
	 * @return True, si i només si, les restriccions es compleixen per totes les hores.
	 */
	static private boolean checkAllRestriccionsPerHores(Estructura horari, Pair<SessioGAssignada, SessioSGAssignada> corrent, int dia, int horaIni) {
		if(corrent == null || corrent.isNull()) return false;
		
		Assignatura assig = corrent.first != null? corrent.first.getGrup().getAssignatura() :
												   corrent.second.getSubGrup().getGrup().getAssignatura();
		int tempsDeSessio = corrent.first != null? corrent.first.getSessioGrup().getHores() :
												   corrent.second.getSessioSubGrup().getHores();
		
		//S'ha de comprovar les restriccions per TOTES les hores que dura la sessió:
		for(int incr = 0; incr < tempsDeSessio; incr++) {
			if((!corrent.fnull() && !checkAllRestriccionsDeGrup(horari, dia, horaIni+incr, corrent.first)) ||
			   (!corrent.snull() && !checkAllRestriccionsDeSubGrup(horari, dia, horaIni+incr, corrent.second)) ||
			   (!checkAllRestriccionsAssignatura(horari, dia, horaIni, assig)))
				return false;
		}
		
		return true;
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
		else for(int dia = 0; dia < 7; dia++) {
			if(plaEstudis.checkDiaFranja(dia)) for(int hora = 0; hora < 24; hora++) { //Si el dia es lectiu:
				//TODO: comprovar la franja lectiva per tantes hores com duri la sessio
				if(plaEstudis.getFranjaDia(dia)[hora]) { //Si la hora entrada pel dia entrat es lectiu al pla d'Estudis.
					if(checkAllRestriccionsPerHores(horari, corrent, dia, hora)) {
						
						Segment segment; //Creació del segment corresponent:
						if(!corrent.fnull()) segment = new Segment(new Data(dia, hora), AulaAdient.seleccionaAulaAdient(horari, campus.getAllAules(), corrent.first, dia, hora), corrent.first);
						else segment = new Segment(new Data(dia, hora), AulaAdient.seleccionaAulaAdient(horari,  campus.getAllAules(), corrent.second, dia, hora), corrent.second);
						
						if(segment.getAula() != null) { //En cas de que se li hagi pogut assignar una aula.
							/*Treballant sobre copies, s'evita la necessitat de reinsertar o treure els elements dels
							 * sets i maps, simplement es modifica la copia i s'itera sobre aquesta. Al retornar, es
							 * treballa novament sobre el set original que no s'ha modificat.
							*/
							Estructura updatedHorari = horari.getCopy();
							HashSet<SessioGAssignada> updatedSessionsG = copySGA(sessionsG);
							HashSet<SessioSGAssignada> updatedSessionsSG = copySSGA(sessionsSG);
							kill(updatedSessionsG, updatedSessionsSG); //S'elimina la sessió just assignada.
							
							//Obtenció del total d'hores que ha de durar la sessio:
							int horesTotals = !segment.getSessio().fnull()? segment.getSessio().first.getSessioGrup().getHores() :
																			segment.getSessio().second.getSessioSubGrup().getHores();
							//S'afegeix el segment a tantes hores com dura la sessio.
							for(int incr = 0; incr < horesTotals; incr++) 
								updatedHorari.setSegment(segment, dia, hora+incr);
							
							//RECURSIVITAT!!! Kernel del backTracking:
							backTracking(updatedHorari, updatedSessionsG, updatedSessionsSG, nHoraris);
						}
					}
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
	static public int tryToCommit(Estructura horari, Segment segment, int dia, int horaIni, boolean commit) {
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
