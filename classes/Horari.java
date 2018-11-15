package classes;

import classes.*;
import utils.*;
import java.util.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class Horari {
	/**
	 * Enmagatzema TOTS els horaris generats per un pla d'estudis
	 * únic en un campus concret.
	 * El primer Map és un horari, del qual:
	 * 	El primer Integer senyala:	Dia de la setmana;
	 * 	El segón Integer senyala:	Hora del dia;
	 * 	El Set d'Elements indica:	Totes les sessions per aquell dia, a aquella hora.
	 */
	private HashSet<Map<Integer, Map<Integer, HashSet<Segment>>>> horarisGenerat;

	/**
	 * Referencia al pla d'estudis del qual se'n vol
	 * generar l'horari.
	 */
	private PlaEstudis plaEstudis;
	/**
	 * Referencia el campus al qual es vol aplicar l'horari.
	 */
	private Campus campus;

	/**
	 * Enmagatzema totes les Sessions de Grup que s'han de colocar
	 * al horari.
	 */
	private HashSet<SessioGAssignada> sessionsDeGrup;
	/**
	 * Enmagatzema totes les Sessions de SubGrup que s'han de colocar
	 * a l'horari.
	 */
	private HashSet<SessioSGAssignada> sessionsDeSubGrup;
	/**
	 * Enmagatzema totes les aules del campus.
	 */
	private HashSet<Aula> aulesDelCampus;
	
	//Controlador d'nSessions:
	private Map<SessioGAssignada, Integer> ocurrencies_g;
	private Map<SessioSGAssignada, Integer> ocurrencies_sg;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Selecciona, si hi és, una aula que compleixi tots els requisits de la sessio
	 * indicada. Altrament, retorna qualsevol aula que estigui lliure.
	 * @param sessio Referencia a la sessió a la qual se'n vol assignar una aula.
	 * @param dia Referencia al dia al qual s'ha d'assignar.
	 * @param hora Referenca a l'hora, a la qual s'ha d'assignar.
	 * @return Una Aula que pot o no complir amb tots els requisits.
	 */
	private Aula seleccionaAulaAdient(Map<Integer, Map<Integer, HashSet<Segment>>> horari, SessioGAssignada sessio, int dia, int hora) {
		return this.getMaximaAulaAdient(horari, sessio.getSessioGrup().getMaterial(), sessio.getGrup().getPlaces(), dia, hora, sessio.getSessioGrup().getHores());
	}
	
	/**
	 * Selecciona, si hi és, una aula que compleixi tots els requisits de la sessio
	 * indicada. Altrament, retorna qualsevol aula que estigui lliure.
	 * @param sessio Referencia a la sessió a la qual se'n vol assignar una aula.
	 * @param dia Referencia al dia al qual s'ha d'assignar.
	 * @param hora Referenca a l'hora, a la qual s'ha d'assignar.
	 * @return Una Aula que pot o no complir amb tots els requisits.
	 */
	private Aula seleccionaAulaAdient(Map<Integer, Map<Integer, HashSet<Segment>>> horari, SessioSGAssignada sessio, int dia, int hora) {
		return this.getMaximaAulaAdient(horari, sessio.getSessioSubGrup().getMaterial(), sessio.getSubGrup().getPlaces(), dia, hora, sessio.getSessioSubGrup().getHores());
	}
	
	/**
	 * Retorna true si, i només si, totes les restriccions de Grup es compleixen
	 * per un grup en un dia i hora donats.
	 * @param dia Indica el dia.
	 * @param horaIni Indica l'hora.
	 * @param sessio Referencia al grup.
	 * @return Un booleà.
	 */
	private boolean checkAllRestriccionsDeGrup(Map<Integer, Map<Integer, HashSet<Segment>>> horari, int dia, int horaIni, SessioGAssignada sessio) {
		if(sessio.getGrup().getRestriccioHoresAptes().checkPotFerClasse(dia, horaIni) != 0) return false;
		else for(Segment segment: horari.get(dia).get(horaIni))
			if(sessio.getGrup().getSolapaments().checkPotSolapar(segment.getSessio().first.getGrup(), segment.getSessio().second.getSubGrup()) != 0)
				return false;
			
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
	private boolean checkAllRestriccionsDeSubGrup(Map<Integer, Map<Integer, HashSet<Segment>>> horari, int dia, int horaIni, SessioSGAssignada sessio) {
		//Un subGrup no pot fer classe fora de les hores aptes del seu grup:
		if(sessio.getSubGrup().getRestriccioHoresAptes().checkPotFerClasse(dia, horaIni) != 0 ||
		   sessio.getSubGrup().getGrup().getRestriccioHoresAptes().checkPotFerClasse(dia, horaIni) != 0) return false;
		else for(Segment segment: horari.get(dia).get(horaIni))
			if(sessio.getSubGrup().getSolapaments().checkPotSolapar(segment.getSessio().first.getGrup(), segment.getSessio().second.getSubGrup()) != 0)
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
	private boolean checkAllRestriccionsAssignatura(Map<Integer, Map<Integer, HashSet<Segment>>> horari, int dia, int horaIni, Assignatura assignatura) {
		if(assignatura.getHoresAptes().checkPotFerClasse(dia, horaIni) != 0) return false;
		else for(Segment segment: horari.get(dia).get(horaIni))
			if(assignatura.getSolapaments().checkSolapar(segment.getSessio().first != null? segment.getSessio().first.getGrup().getAssignatura() : segment.getSessio().second.getSessioSubGrup().getAssignatura()) != 0)
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
	private boolean checkAllRestriccionsPerHores(Map<Integer, Map<Integer, HashSet<Segment>>> horari, int dia, int horaIni) {
		Pair<SessioGAssignada, SessioSGAssignada> corrent = this.nextSessio();
		if(corrent == null || corrent.isNull()) return false;
		
		Assignatura assig = corrent.first != null? corrent.first.getGrup().getAssignatura() :
												   corrent.second.getSubGrup().getGrup().getAssignatura();
		int tempsDeSessio = corrent.first != null? corrent.first.getSessioGrup().getHores() :
												   corrent.second.getSessioSubGrup().getHores();
		
		for(int incr = 0; incr < tempsDeSessio; incr++) {
			if((!corrent.fnull() && !checkAllRestriccionsDeGrup(horari, dia, horaIni+incr, corrent.first)) ||
			   (!corrent.snull() && !checkAllRestriccionsDeSubGrup(horari, dia, horaIni+incr, corrent.second)) ||
			    !checkAllRestriccionsAssignatura(horari, dia, horaIni, assig))
				return false;
		}
		
		return true;
	}
	
	private boolean checkNSessions(Sessio sessio, Map<Integer, Map<Integer, HashSet<Segment>>> horari, int dia) {
		
		return true;
	}
	
	/**
	 * Funció recursiva que, en definitiva, genera l'horari adient.
	 * @param index Controla la sessio a processar.
	 * @param GOcup Controla les sessions de grup ocupades.
	 * @param SGOcup Controla les sessions de SubGrup ocupades.
	 * @throws Exception
	 */
	private void backTracking(Map<Integer, Map<Integer, HashSet<Segment>>> horari, int nHoraris) throws Exception {
		if(this.horarisGenerat.size() >= nHoraris) return; // Si ja tenim nhorari, no és continua.
		Pair<SessioGAssignada, SessioSGAssignada> corrent = this.nextSessio();
		
		if(corrent.isNull()) { //si s'ha lograt afegir TOTES les sessions vol dir que tenim un HORARI CANDIDAT!
			this.horarisGenerat.add(horari); //Guardem l'horari obtingut;
			return; //S'hi s'ha arribat al final s'acaba.
		}
		
		for(int dia = 0; dia < 7; dia++) {
			//Si el dia es lectiu.
			if(this.plaEstudis.checkDiaFranja(dia)) for(int hora = 0; hora < 24; hora++) {
				//Si la hora entrada pel dia entrat es lectiu al pla d'Estudis.
				if(this.plaEstudis.getFranjaDia(dia)[hora]) {
					if(this.checkAllRestriccionsPerHores(horari, dia, hora)) {
					
						//AGREGACIÓ:				
						Segment segment;
						if(!corrent.fnull()) segment = new Segment(this, new Data(dia, hora), seleccionaAulaAdient(horari, corrent.first, dia, hora), corrent.first);
						else segment = new Segment(this, new Data(dia, hora), seleccionaAulaAdient(horari, corrent.second, dia, hora), corrent.first);
						
						//CONTROL DE REPETICIONS
						horari.get(dia).get(hora).add(segment);
						this.kill();
						
						//RECURSIVITAT!!! Kernel del backTracking:
						backTracking(horari, nHoraris);
						this.restore(horari, dia, hora, segment.getSessio().first, segment.getSessio().second);
					}
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  GENERADOR  ////////////////////////////////////
	/**
	 * Donat un campus, i un pla d'estudis, genera l'horari corresponent.
	 * @param plaEstudis Referencia al pla d'estudis del qual se n'ha de fer l'horari.
	 * @param campus Referencia el campus sobre el qual s'ha d'aplicar l'horari.
	 * @throws Exception
	 */
	private HashSet<Map<Integer, Map<Integer, HashSet<Segment>>>>
	GENERADOR(PlaEstudis plaEstudis, Campus campus, int nHoraris) throws Exception{
		ExceptionManager.thrower(this.setCampus(campus));
		ExceptionManager.thrower(this.setPlaEstudis(plaEstudis));
		
		Map<Integer, Map<Integer, HashSet<Segment>>> horariCandidat =  new HashMap<>();
		this.backTracking(horariCandidat, nHoraris);
		return this.horarisGenerat;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CREADORA  /////////////////////////////////////
	/**
	 * Constrcutora de la classe Horari.
	 * Degut al seu funcionament no cal assignar-li res.
	 */
	public Horari() {
		this.horarisGenerat = new HashSet<>();
		this.ocurrencies_g = new HashMap<>();
		this.ocurrencies_sg = new HashMap<>();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/**
	 * Assigna un pla d'estudis a horari.
	 * @param plaEstudis Referencia al pla d'estudis.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int setPlaEstudis(PlaEstudis plaEstudis) throws Exception {
		if(plaEstudis == null) return -1; //TODO: El pla d'estudis no pot ser null;
		
		this.plaEstudis = plaEstudis;
		this.sessionsDeGrup = new HashSet<>(this.plaEstudis.getSessionsGrupA());
		this.sessionsDeSubGrup = new HashSet<>(this.plaEstudis.getSessionsSubGrupA());
		return 0;
	}
	
	/**
	 * Assigna un campus a l'horari.
	 * @param campus Referencia al campus de l'horari.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int setCampus(Campus campus) {
		if(campus == null) return -1; //TODO: El campus no pot ser null;
		
		this.campus = campus;
		this.aulesDelCampus = new HashSet<>(this.campus.getAllAules());
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/**
	 * Retorna la sessio situada a la posició index.
	 * Basicament permet treballar amb ambdos HashSets com
	 * si fossin un de sol.
	 * @param index Posicio que es vol cercar.
	 * @return La sessió corresponent.
	 */
	private Pair<SessioGAssignada, SessioSGAssignada> nextSessio() {
		Pair<SessioGAssignada, SessioSGAssignada> element = new Pair<>();
		if(!sessionsDeGrup.isEmpty()) //En cas d'estar accedint a les Sessions de Grup
			element.first = sessionsDeGrup.iterator().next();
		else if(!sessionsDeSubGrup.isEmpty()) //En cas d'accedir a les sessions de SubGrup
			element.second = sessionsDeSubGrup.iterator().next();
		
		return element;
	}
	
	/**
	 * Retorna l'aula que més s'adapta a l'equip indicat. Altrament n'escull una
	 * a l'atzar.
	 * @param equip Set amb tots els equips necesaris.
	 * @param dia Dia en el qual es vol reservar l'aula.
	 * @param hora Hora en la qual es vol reservar l'aula.
	 * @param durada Indica per quantes hores consecutives cal reservar aquella aula.
	 * @return L'aula mes adient.
	 */
	private Aula getMaximaAulaAdient(Map<Integer, Map<Integer, HashSet<Segment>>> horari, HashSet<String> equip, int places, int dia, int hora, int durada) {
		Map<Integer, HashSet<Aula>> aulesCandidates = new HashMap<>();
		for(Aula aula: this.aulesDelCampus)
			if(!this.checkAula(horari, aula, dia, hora, durada) || aula.getCapacitat() < places) { //Si l'aula esta ocupada, es descarta.
				if(aulesCandidates.get(aula.matchEquip(equip)) == null)
					aulesCandidates.put(aula.matchEquip(equip), new HashSet<>());
				
				aulesCandidates.get(aula.matchEquip(equip)).add(aula);
			}
		
		int maximMatch = 0;
		for(int match: aulesCandidates.keySet()) //Selecciona aquella Key que conté més coincidencies.
			if(match > maximMatch) maximMatch = match;
		
		if(maximMatch == equip.size()) return getMinimaAulaAdient(aulesCandidates.get(maximMatch), equip.size());
		return aulesCandidates.get(maximMatch).iterator().next();
	}
	
	/**
	 * Retorna aquella aula pertanyent a aules que, a part de contenir TOT l'equip
	 * indicat, "malgasta" el minim possible d'equip.
	 * @param aules Conjunt d'aules candidades.
	 * @param nEquip Quantitat d'equip necessari.
	 * @return L'aula més adient.
	 */
	private Aula getMinimaAulaAdient(HashSet<Aula> aules, int nEquip) {
		Map<Integer, HashSet<Aula>> aulesCandidates = new HashMap<>();
		for(Aula aula: aules) { //Genera un map registrant, per cada aula, quant material no s'esta usant.
			if(aulesCandidates.get(aula.getEquip().size() - nEquip) == null)
				aulesCandidates.put(aula.getEquip().size() - nEquip, new HashSet<>());
			
			aulesCandidates.get(aula.getEquip().size() - nEquip).add(aula);
		}
		
		int minimMatch = aulesCandidates.keySet().iterator().next(); //Agafem el primer element, sense saber quin es.
		for(int match: aulesCandidates.keySet()) //Selecciona aquella Key que té menys materials sense usar.
			if(match < minimMatch) minimMatch = match;
		
		return aulesCandidates.get(minimMatch).iterator().next();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Elimina l'element dels hashset situal a la posició index.
	 * @param index indica la posicio de l'element a eliminar.
	 */
	private void kill() {
		if(!sessionsDeGrup.isEmpty()) {
			SessioGAssignada sessio = this.sessionsDeGrup.iterator().next();
			this.sessionsDeGrup.remove(sessio);
		}
		else if(!sessionsDeGrup.isEmpty()) {
			SessioSGAssignada sessio = this.sessionsDeSubGrup.iterator().next();
			this.sessionsDeSubGrup.remove(sessio);
		}
	}
	
	/**
	 * Afegeix les sessions al set corresponent. I per tant, degut a que això
	 * significa que no està assignada, la treu del dia i hora en el qual s'ha afegit.
	 * @param horari Referencia a l'horari el qual s'ha de restaurar.
	 * @param dia Indica el dia en el qual s'ha de restaurar.
	 * @param hora Indica l'hora en el dia en la qual s'ha de fer la restauració.
	 * @param sessioG Referencia a una sessioA de tipus grup.
	 * @param sessioSG Referencia a una sessioA de tipus SubGrup
	 */
	private void restore(Map<Integer, Map<Integer, HashSet<Segment>>> horari, int dia, int hora, SessioGAssignada sessioG, SessioSGAssignada sessioSG) {
		if(sessioG != null) this.sessionsDeGrup.add(sessioG);
		if(sessioSG != null) this.sessionsDeSubGrup.add(sessioSG);
		
		horari.get(dia).get(hora).removeIf(segment -> (segment.getSessio().first != null && segment.getSessio().first == sessioG) ||
													  (segment.getSessio().second != null && segment.getSessio().second == sessioSG));
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna true si, i només si, donat un dia i una hora, l'aula indicada
	 * està ocupada.
	 * @param aula Referencia l'aula a comprovar.
	 * @param dia Indica el dia en el qual se'n vol fer la comprovació.
	 * @param hora Indica l'hora en la qual se'n vol fer la comprovació.
	 * @return Un booleà.
	 */
	private boolean checkAula(Map<Integer, Map<Integer, HashSet<Segment>>> horari, Aula aula, int dia, int hora, int durada) {
		if(aula == null) return false;
		for(int incr = 0; incr < durada; incr++) {
			for(Segment segment: horari.get(dia).get(hora+incr)) {
				if(segment.aula.getNom().equals(aula.getNom())) return true;
			}
		}
		
		return false;
	}
}
