package presentacio;

import java.util.*;

import domini.ControladorDomini;
import domini.tools.Estructura;
import persistencia.ControladorPersistencia;
import presentacio.vistes.*;
import utils.Pair;

/**
 * 
 * @author adria.manero@est.fib.upc.edu
 *
 */
public class ControladorPresentacio {

	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  INSTANCIA  /////////////////////////////////////
	
	private static ControladorPresentacio current;
	
	/**
	 * Retorna la instancia corrent del controlador.
	 * @return Instancia de la classe.
	 */
	public static ControladorPresentacio getInstance() {
		if(current == null) current = new ControladorPresentacio();
		return current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////  ACCIONS  ////////////////////////////////////
	/**
	 * Indica el total d'horaris que hi ha generats.
	 * @param plaEstudis IDentifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @return Un enter superior o igual 0.
	 */
	public int getNHoraris(String plaEstudis, String campus) {
		return ControladorDomini.getInstance().getNHoraris(plaEstudis, campus);
	}
	
	/**
	 * Retorna la informació rellevant de tots els segments situats en un dia i hora concrets.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param dia Indica el dia.
	 * @param hora Indica la hora.
	 * @param iter Indica sobre quina iteració del total d'horaris s'ha de treballar.
	 * @return Un set amb la info, o buit.
	 */
	public HashSet<ArrayList<String>> getSegments(String plaEstudis, String campus, int dia, int hora, int iter){
		return ControladorDomini.getInstance().getSegments(plaEstudis, campus, dia, hora, iter);
	}
	
	/**
	 * Retorna tot els conjunts de grup / subgrup presents en una ssignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica a l'assignatura. 
	 * @return Un map buit o ple.
	 */
	public Map<Integer, HashSet<Integer>> getConjunts(String plaEstudis, String assignatura){
		return ControladorDomini.getInstance().getConjunts(plaEstudis, assignatura);
	}
	
	/**
	 * Retorna tots els conjunts existents dins d'un pla d'estudis.
	 * @param plaEstudis IDentifica al pla d'estudis.
	 * @return Un map ple o buit.
	 */
	public Map<String, HashSet<Integer>> getConjunts(String plaEstudis){
		return ControladorDomini.getInstance().getConjunts(plaEstudis);
	}
	
	/**
	 * Proporciona tot el registre de solapaments de l'objecte indicat.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica a l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @return Un map ple o buit.
	 */
	public Map<String, HashSet<Integer>> getDisjuntes(String plaEstudis, String assignatura, int grup, int subgrup){
		return ControladorDomini.getInstance().getDisjuntes(plaEstudis, assignatura, grup, subgrup);
	}
	
	/**
	 * Proporciona totes les assignacins d'una sessió
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param sessioGrup Indica si és o no una sessio de grup.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<Integer> getAssignades(String plaEstudis, String assignatura, String tipus, int hores, boolean sessioGrup){
		return ControladorDomini.getInstance().getAssignades(plaEstudis, assignatura, tipus, hores, sessioGrup);
	}
	
	/**
	 * Retorna tots els solapaments registrats.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @return Un map amb contingut o sense.
	 */
	public Map<String, HashSet<Integer>> getSolapaments(String plaEstudis, String assignatura, int grup, int subgrup){
		return ControladorDomini.getInstance().getSolapaments(plaEstudis, assignatura, grup, subgrup);
	}
	
	/**
	 * Retorna tots el campus existents.
	 * @return  Un set amb contingut o sense.
	 */
	public HashSet<String> getAllCampus() {
		return ControladorDomini.getInstance().campusPresents();
	}
	
	/**
	 * Retorna tota la informació important d'un campus.
	 * @param campus Identifica al campus.
	 * @return  Un string amb contingut o sense.
	 */
	public String GetMainCampusData(String campus) {
		return ControladorDomini.getInstance().GetMainCampusData(campus);
	}
	
	/**
	 * Retorna totes les aules presents en un campus.
	 * @param campus Identifica al campus.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> getAllAules(String campus) {
		return ControladorDomini.getInstance().aulesPresents(campus);
	}
	
	/**
	 * Retorna tota la informació import d'una aula.
	 * @param campus Identifica al campus.
	 * @param aula Identifica al aula.
	 * @return  Una llista amb contingut o sense.
	 */
	public ArrayList<String> GetMainAulaData(String campus, String aula) {
		return ControladorDomini.getInstance().GetMainAulaData(campus, aula);
	}
	
	/**
	 * Retorna tots els plans d'estudis existents.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> getAllPlaEstudis() {
		return ControladorDomini.getInstance().plansEstudisPresents();
	}
	
	/**
	 * Retorna la informació primordial d'un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @return Un string amb contingut o sense.
	 */
	public String GetMainPlaEstudisData(String plaEstudis) {
		return ControladorDomini.getInstance().GetMainPlaEstudisData(plaEstudis);
	}
	
	/**
	 * Retorna el total d'assignatures existents en un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> getAllAssignatures(String plaEstudis) {
		return ControladorDomini.getInstance().assignaturesPresents(plaEstudis);
	}
	
	/**
	 * Retorna el total del grups existents en una ssignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @return  Un hashset amb contingut o sense.
	 */
	public HashSet<String> getAllGrups(String plaEstudis, String assignatura){
		return ControladorDomini.getInstance().grupsPresents(plaEstudis, assignatura);
	}
	
	/**
	 * Retorna tota la informació primordial d'un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @return Una list amb contingut o sense.
	 */
	public ArrayList<String> GetMainGrupData(String plaEstudis, String assignatura, int grup) {
		return ControladorDomini.getInstance().GetMainGrupData(plaEstudis, assignatura, grup);
	}
	
	/**
	 * Retorna totes les sessions existents.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> getAllSessions(String plaEstudis, String assignatura){
		return ControladorDomini.getInstance().sessionsPresents(plaEstudis, assignatura);
	}
	
	/**
	 * Retorna tota la informació primordial d'una sessió.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param deGrup Indica si la sessio es de grup.
	 * @return Un hashset amb contingut o sense.
	 */
	public ArrayList<String> GetMainSessioData(String plaEstudis, String assignatura, String tipus, int hores, boolean deGrup) {
		return ControladorDomini.getInstance().GetMainSessioData(plaEstudis, assignatura, tipus, hores, deGrup);
	}
	
	/**
	 * Retorna tots els subgrups existents en un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> getAllSubGrups(String plaEstudis, String assignatura, int grup){
		return ControladorDomini.getInstance().subgrupsPresents(plaEstudis, assignatura, grup);
	}
	
	/**
	 * Retorna tota la informació primordial d'un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @return Una list amb contingut o sense.
	 */
	public ArrayList<String> GetMainSubGrupData(String plaEstudis, String assignatura, int grup, int subgrup) {
		return ControladorDomini.getInstance().GetMainSubGrupData(plaEstudis, assignatura, grup, subgrup);
	}
	
	/**
	 * Retorna el mapa d'hores aptes.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @return Un map amb contingut o sense.
	 */
	public Map<Integer, boolean[]> getHorizon(String plaEstudis, String assignatura, int grup, int subgrup){
		return ControladorDomini.getInstance().getHorizon(plaEstudis, assignatura, grup, subgrup);
	}
	
	/**
	 * Retorna el marge que compon l'horari.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param iter Indica sobre quina iteració treballar.
	 * @return Un pair de pairs amb contingut o sense.
	 */
	public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> getMarginHorari(String plaEstudis, String campus, int iter){
		return ControladorDomini.getInstance().getMarginHorari(plaEstudis, campus, iter);
	}
	
	/**
	 * Genera n horaris.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param nhoraris Indica el total d'horaris a generar.
	 * @param flags Proporciona els flags a tenir en compte
	 * @param purge Indica si es vol purgar els horaris antics o no.
	 * @return Total d'horaris generats.
	 */
	public int generarHorari(String plaEstudis, String campus, int nhoraris, HashSet<String> flags, boolean purge) {
		String warn = ControladorDomini.getInstance().generarHorari(plaEstudis, campus, nhoraris, flags, purge);
		
		try {
			int total = Integer.parseInt(warn);
			if(total < nhoraris) Main.getInstance().showWarning("Horaris insuficients", "Amb la configuració actual s'ha generat un total de: ".concat(String.valueOf(total)).concat(" horaris."));
			
			return total;
		}
		catch(NumberFormatException e) {
			Main.getInstance().showWarning("Error en la generació.", "Vaja! No s'ha pogut generar l'horari correctament.");
			return 0;
		}
	}
	
	/**
	 * Dona d'alta un campus.
	 * @param campus Identifica al campus.
	 */
	public void CrearCampus(String campus) {
		String s = ControladorDomini.getInstance().CrearCampus(campus);
		if(s != null) Main.getInstance().showWarning("Error crear Campus",s);
	}
	
	/**
	 * Esborra un campus
	 * @param campus Identifica al campus.
	 */
	public void EliminarCampus(String campus) {
		ControladorDomini.getInstance().EliminarCampus(campus);
	}
	
	/**
	 * Modifica un campus
	 * @param campus Identifica al campus.
	 * @param nom Nou nom del campus.
	 * @param autor Nom de l'autor del campus.
	 */
	public void ModificarCampus(String campus, String nom, String autor) {
		String s = ControladorDomini.getInstance().ModificarCampus(campus,nom,autor);
		if(s != null) Main.getInstance().showWarning("Error modificar de Campus",s);
	}
	
	/**
	 * Dona d'alta una aula
	 * @param campus Identifica al campus.
	 * @param aula Identifica l'aula.
	 * @param capacitat Indica la capacitat de l'aula.
	 */
	public void CrearAula(String campus, String aula, int capacitat) {
		String s = ControladorDomini.getInstance().CrearAula(campus,aula,capacitat);
		if(s != null) Main.getInstance().showWarning("Error crear Aula",s);
	}
	
	/**
	 * Esborra una aula.
	 * @param campus Identifica al campus.
	 * @param aula Identifica l'aula.
	 */
	public void EliminarAula(String campus, String aula) {
		String s = ControladorDomini.getInstance().EliminarAula(campus,aula);
		if(s != null) Main.getInstance().showWarning("Error eliminar Aula",s);
	}
	
	/**
	 * Modifica una aula.
	 * @param campus Identifica al campus.
	 * @param aula Identifica l'aula.
	 * @param nom Nou nom de l'aula.
	 * @param capacitat Nova capacitat de l'aula.
	 * @param equip Equip present a l'aula.
	 */
	public void ModificarAula(String campus, String aula, String nom, int capacitat, HashSet<String> equip) {
		String s = ControladorDomini.getInstance().ModificarAula(campus,aula,nom,capacitat, equip);
		if(s != null) Main.getInstance().showWarning("Error modificar Aula",s);
	}
	
	/**
	 * Dona d'alta un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 */
	public void CrearPlaEstudis(String plaEstudis) {
		String s = ControladorDomini.getInstance().CrearPlaEstudis(plaEstudis);
		if(s != null) Main.getInstance().showWarning("Error crear Pla d'Estudis",s);
	}
	
	/**
	 * Esborra un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 */
	public void EliminaPlaEstudis(String plaEstudis) {
		ControladorDomini.getInstance().EliminaPlaEstudis(plaEstudis);
	}
	
	/**
	 * Modifica un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param nom Nou nom del pla d'estudis.
	 * @param autor Nom de l'autor del pla d'estudis.
	 * @param lectiu Horari lectiu del pla.
	 * @param rang Rang horari del pla.
	 */
	public void ModificarPlaEstudis(String plaEstudis, String nom, String autor, Map<Integer, boolean[]> lectiu, int[] rang) {
		String s = ControladorDomini.getInstance().ModificarPlaEstudis(plaEstudis,nom,autor, lectiu, rang);
		if(s != null) Main.getInstance().showWarning("Error modificar Pla d'Estudis",s);
		
	}
	
	/**
	 * Dona d'alta una assignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 */
	public void CrearAssignatura(String plaEstudis, String assignatura) {
		String s = ControladorDomini.getInstance().CrearAssignatura(plaEstudis,assignatura);
		if(s != null) Main.getInstance().showWarning("Error crear Assignatura",s);
	}

	/**
	 * Esborra una assignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 */
	public void EliminarAssignatura(String plaEstudis, String assignatura) {
		String s = ControladorDomini.getInstance().EliminarAssignatura(plaEstudis,assignatura);
		if(s != null) Main.getInstance().showWarning("Error eliminar Assignatura",s);
	}

	/**
	 * Modifica una assignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param nom Nou nom de l'assignatura.
	 */
	public void ModificarAssginatura(String plaEstudis, String assignatura, String nom) {
		String s = ControladorDomini.getInstance().ModificarAssignatura(plaEstudis,assignatura,nom);
		if(s != null) Main.getInstance().showWarning("Error modificar Assignatura",s);
	}
	
	/**
	 * Dona d'alta un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param capacitat Capacitat del grup.
	 */
	public void CrearGrup(String plaEstudis, String assignatura, int grup, int capacitat) {
		String s = ControladorDomini.getInstance().CrearGrup(plaEstudis,assignatura,grup,capacitat);
		if(s != null) Main.getInstance().showWarning("Error crear Grup",s);
	}
	
	/**
	 * Esborra un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 */
	public void EliminarGrup(String plaEstudis, String assignatura, int grup) {
		ControladorDomini.getInstance().EliminarGrup(plaEstudis,assignatura,grup);
	}
	
	/**
	 * Modifica un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param numero Identifica al grup.
	 * @param places Noves places del grup.
	 * @param franja Nova franja del grup.
	 */
	public void ModificarGrup(String plaEstudis, String assignatura, int grup, int numero, int places, String franja) {
		String s = ControladorDomini.getInstance().ModificarGrup(plaEstudis,assignatura,grup,numero,places,franja);
		if(s != null) Main.getInstance().showWarning("Error modificar Grup",s);
		
	}
	
	/**
	 * Dona d'alta un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subGrup Identifica al subgrup.
	 * @param places Places del subgrup
	 * @param force Indica si cal adaptar el grup del subgrup a les places solicitades.
	 */
	public void CrearSubGrup(String plaEstudis, String assignatura, int grup, int subGrup, int places, boolean force) {
		String s = ControladorDomini.getInstance().CrearSubGrup(plaEstudis,assignatura,grup,subGrup,places,force);
		if(s != null) Main.getInstance().showWarning("Error crear SubGrup",s);
	}
	
	/**
	 * Esborra un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al subgrup.
	 * @param subGrup Identifica al subgrup.
	 */
	public void EliminaSubGrup(String plaEstudis, String assignatura, int grup, int subGrup) {
		ControladorDomini.getInstance().EliminaSubGrup(plaEstudis,assignatura,grup,subGrup);
	}
	
	/**
	 * Modifica un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @param numero  Identifica al subgrup.
	 * @param places Nova quantitat de places.
	 * @param incr Indica si cal incrementar el total de places del grup.
	 */
	public void ModificarSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, int numero, int places, boolean incr) {
		String s = ControladorDomini.getInstance().ModificarSubGrup(plaEstudis,assignatura,grup,subgrup,numero,places,incr);
		if(s != null) Main.getInstance().showWarning("Error modificar SubGrup",s);
	}
	
	/**
	 * Dona d'alta una sessió de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public void CrearSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().CrearSessioGrup(plaEstudis,assignatura,tipus,hores);
		if(s != null) Main.getInstance().showWarning("Error crear SessioGrup",s);
	}
	
	/**
	 * Esborra una sessio de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public void EliminaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().EliminaSessioGrup(plaEstudis,assignatura,tipus,hores);
		if(s != null) Main.getInstance().showWarning("Error eliminar SessioGrup",s);
	}
	
	/**
	 * Modifica una sessio de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param newTipus Nou tipus de la sessió.
	 * @param newHores Nova quantitat d'hores.
	 * @param nsessions Nova quantita d'nsessions.
	 * @param equip Equip necessari a la sessió.
	 */
	public void ModificarSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> equip) {
		String s = ControladorDomini.getInstance().ModificarSessioGrup(plaEstudis,assignatura,tipus,hores,newTipus,newHores,nsessions,equip);
		if(s != null) Main.getInstance().showWarning("Error modificar SessioSubGrup",s);
	}
	
	/**
	 * Dona d'alta una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public void CrearSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().CrearSessioSubGrup(plaEstudis,assignatura,tipus,hores);
		if(s != null) Main.getInstance().showWarning("Error crear SessioSubGrup",s);
	}
	
	/**
	 * Esborra una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public void EliminaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().EliminaSessioSubGrup(plaEstudis,assignatura,tipus,hores);
		if(s != null) Main.getInstance().showWarning("Error eliminar SessioSubGrup",s);
	}
	
	/**
	 * Esborra un horari.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param iter Indica sobre quina iteració treballar.
	 */
	public void EliminaHorari(String plaEstudis, String campus, int iter) {
		String s = ControladorDomini.getInstance().EliminaHorari(plaEstudis,campus, iter);
		if(s != null) Main.getInstance().showWarning("Error eliminar horari",s);
	}
	
	/**
	 * Modifica una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param newTipus Nou tipus de la sessió.
	 * @param newHores Nova quantitat d'hores.
	 * @param nsessions Nova quantitat d'nsessions.
	 * @param equip Equip necessari per a la sessió.
	 */
	public void ModificarSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> equip) {
		String s = ControladorDomini.getInstance().ModificarSessioSubGrup(plaEstudis,assignatura,tipus,hores,newTipus,newHores,nsessions,equip);
		if(s != null) Main.getInstance().showWarning("Error modificar SessioSubGrup",s);
	}
	
	/**
	 * Assigna una sessio de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 */
	public void AssignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		String s = ControladorDomini.getInstance().AssignaSessioGrup(plaEstudis,assignatura,tipus,hores,grup);
		if(s != null) Main.getInstance().showWarning("Error assignar SessioGrup",s);
	}

	/**
	 * Desassigna una sessio de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 */
	public void DesassignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		String s = ControladorDomini.getInstance().DesassignaSessioGrup(plaEstudis,assignatura,tipus,hores,grup);
		if(s != null) Main.getInstance().showWarning("Error desassignar SessioGrup",s);
	}
	
	/**
	 * Assigna una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 */
	public void AssignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		String s = ControladorDomini.getInstance().AssignaSessioSubGrup(plaEstudis,assignatura,tipus,hores,grup,subgrup);
		if(s != null) Main.getInstance().showWarning("Error Assignar SessioSubGrup",s);
	}
	
	/**
	 * Desassigna una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 */
	public void DesassignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		String s = ControladorDomini.getInstance().DesassignaSessioSubGrup(plaEstudis,assignatura,tipus,hores,grup,subgrup);
		if(s != null) Main.getInstance().showWarning("Error Desassignar SessioSubGrup",s);
	}
	
	/**
	 * Estableix les hores aptes.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @param franja Indica la franja.
	 * @param apte Senyala si la franja es apte o no.
	 * @param force Indica si cal forçar la fraja o no.
	 */
	public void HoresAptes(String plaEstudis, String assignatura, int grup, int subgrup, Map<Integer, int[]> franja, boolean apte, boolean force) {
		String s = ControladorDomini.getInstance().HoresAptes(plaEstudis,assignatura,grup,subgrup,franja,apte,force);
		if(s != null) Main.getInstance().showWarning("Error Hores Aptes",s);
	}

	/**
	 * Estableix els solapaments d'una ssignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param assigToRegister Identifica l'assignatura a registrar.
	 * @param permet Indica si s'ha de permetre o no el solapament.
	 */
	public void SetSolapamentAssig(String plaEstudis, String assignatura, String assigToRegister, boolean permet) {
		String s = ControladorDomini.getInstance().SetSolapamentAssig(plaEstudis,assignatura,assigToRegister,permet);
		if(s != null) Main.getInstance().showWarning("Error Solapament Assignatura",s);
	}
	
	/**
	 * Estableix els solpaments d'un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param assigToRegister Identifica l'assignatura del grup que es vol registrar.
	 * @param numToRegister Identifica al grup/subgrup que es vol registrar.
	 * @param permet Indica si pot o no solapar-se.
	 */
	public void SetSolapamentGrup(String plaEstudis, String assignatura, int grup, String assigToRegister, int numToRegister, boolean permet) {
		String s = ControladorDomini.getInstance().SetSolapamentGrup(plaEstudis,assignatura,grup,assigToRegister,numToRegister,permet);
		if(s != null) Main.getInstance().showWarning("Error Solapament Grup",s);
	}
	
	/**
	 * Estableix els solapaments d'un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @param assigToRegister Identifica l'assignatura del grup que es vol registrar.
	 * @param numToRegister Identifica al grup/subgrup que es vol registrar.
	 * @param permet Indica si pot o no solapar-se.
	 */
	public void SetSolapamentSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, String assigToRegister, int numToRegister, boolean permet) {
		String s = ControladorDomini.getInstance().SetSolapamentSubGrup(plaEstudis,assignatura,grup,subgrup,assigToRegister,numToRegister,permet);
		if(s != null) Main.getInstance().showWarning("Error Solapament SubGrup",s);
	}

	/**
	 * Modifica un horari.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param iter Indica sobre quina iteració treballar.
	 * @param assig Identifica l'assignatura de la sessió a desplaçar.
	 * @param numero Identifica al grup/subgrup de la sessio.
	 * @param dia Indica a quin dia es troba.
	 * @param hora Indica a quina hora es troba la sessió.
	 * @param newDia Indica a quin dia desplaçar la sessió.
	 * @param newHora Indica a quina hora desplaçar.
	 * @param commit Indica si es vol fer el canvi definitiu.
	 * @param force Indica si es vol o no procedir al canvi tot i que sigui incongruent amb les restriccions de l'horari.
	 * @return
	 */
	public HashSet<String> ModificarHorari(String plaEstudis, String campus, int iter, String assig, int numero, int dia, int hora, int newDia, int newHora, boolean commit, boolean force){
		return ControladorDomini.getInstance().ModificarHorari(plaEstudis, campus, iter, assig, numero, dia, hora, newDia, newHora, commit, force);
	}
	
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////  FUNCIONS PERSISTENCIA  ////////////////////////
	
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////  EXPORTS  //////////////////////////////////////
	/**
	 * Exporta una assignatura.
	 * @param path Ubicació a la que fer l'export.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 */
	public void exportaAssignatura(String path, String plaEstudis, String assignatura) {
		String s = ControladorDomini.getInstance().exportaAssignatura(path, plaEstudis, assignatura, true);
		if(s != null) Main.getInstance().showWarning("Error exportar Assignatura",s);
	}
	
	/**
	 * Exporta una aula.
	 * @param path Ubicació a la que fer l'export.
	 * @param campus Identifica al campus.
	 * @param aula Identifica l'aula.
	 */
	public void exportaAula(String path, String campus, String aula) {
		String s = ControladorDomini.getInstance().exportaAula(path, aula, campus, true);
		if(s != null) Main.getInstance().showWarning("Error exportar Aula",s);
	}
	
	/**
	 * Exporta un campus.
	 * @param path Ubicació a la que fer l'export.
	 * @param campus Identifica al campus.
	 */
	public void exportaCampus(String path, String campus) {
		String s = ControladorDomini.getInstance().exportaCampus(path, campus);
		if(s != null) Main.getInstance().showWarning("Error exportar Campus",s);
	}
	
	/**
	 * Exporta un grup
	 * @param path Ubicació a la que fer l'export.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param numero Identifica al grup.
	 */
	public void exportaGrup(String path, String plaEstudis, String assignatura, int numero) {
		String s = ControladorDomini.getInstance().exportaGrup(path, numero, assignatura, plaEstudis, true);
		if(s != null) Main.getInstance().showWarning("Error exportar Grup",s);
	}
	
	/**
	 * Exporta un horari
	 * @param path Ubicació a la que fer l'export.
	 * @param flags Indica els flags utilitzats per a la generació de l'horari.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param iteracions Identifica l'iteració sobre la qual treballar.
	 */
	public void exportaHorari(String path, HashSet<String> flags, String plaEstudis, String campus, int iteracions) {
		String s = ControladorDomini.getInstance().exportaHorari(path, flags, campus, plaEstudis, iteracions);
		if(s != null) Main.getInstance().showWarning("Error exportar Horari",s);
	}
	
	/**
	 * Exporta un pla d'estudis
	 * @param path Ubicació a la que fer l'export.
	 * @param plaEstudis Identifica al pla d'estudis.
	 */
	public void exportaPlaEstudis(String path, String plaEstudis) {
		String s = ControladorDomini.getInstance().exportaPlaEstudis(path, plaEstudis);
		if(s != null) Main.getInstance().showWarning("Error exportar PlaEstudis",s);
	}
	
	/**
	 * Exporta una sessio de grup.
	 * @param path Ubicació a la que fer l'export.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public void exportaSessioGrup(String path, String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().exportaSessioGrup(path, plaEstudis, assignatura, tipus, hores, true);
		if(s != null) Main.getInstance().showWarning("Error exportar SessioGrup",s);
	}
	
	/**
	 * Exporta una sessio de subgrup
	 * @param path Ubicació a la que fer l'export.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 */
	public void exportaSessioSubGrup(String path, String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		String s = ControladorDomini.getInstance().exportaSessioSubGrup(path, plaEstudis, assignatura, tipus, hores, grup, true);
		if(s != null) Main.getInstance().showWarning("Error exportar SessioSubGrup",s);
	}
	
	/**
	 * Exporta un subgrup
	 * @param path Ubicació a la que fer l'export.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 */
	public void exportaSubGrup(String path, String plaEstudis, String assignatura, int grup, int subgrup) {
		String s = ControladorDomini.getInstance().exportaSubGrup(path, subgrup, grup, assignatura, plaEstudis, true);
		if(s != null) Main.getInstance().showWarning("Error exportar SessioSubGrup",s);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  IMPORTS  //////////////////////////////////////
	
	/**
	 * Importa una assignatura
	 * @param path Path del fitxer que volem 
	 * @param nomPE Nom del pla d'estudis
	 * @return Null en cas de estar correcte, sinó l'error
	 */
	public void importaAssignatura(String path, String nomPE) {
		String s = ControladorDomini.getInstance().importaAssignatura(path, nomPE);
		if(s != null) Main.getInstance().showWarning("Error importar Assignatura",s);
	}
	
	/**
	 * Importa un aula
	 * @param Path llista amb el que hi havia al fitxer
	 * @param NomC nom del campus que pertany l'aula
	 * @return Null en cas de cap error, l'error com a String altrament
	 */
	public void importaAula(String path, String nomC) {
		String s = ControladorDomini.getInstance().importaAula(path, nomC);
		if(s != null) Main.getInstance().showWarning("Error importar Aula",s);
	}
	
	/**
	 * Importa un campus
	 * @param Path llista amb el que hi havia al fitxer
	 * @return Null en cas de estar correcte, sinó l'error
	 */
	public void importaCampus(String path) {
		String s = ControladorDomini.getInstance().importaCampus(path);
		if(s != null) Main.getInstance().showWarning("Error importar Campus",s);
	}
	
	/**
	 * Importa un grup
	 * @param path Path del fitxer que volem 
	 * @param nomPE Nom del pla d'estudis
	 * @param nomA Nom de l'assignatura
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public void importaGrup(String path, String nomPE, String nomA) {
		String s = ControladorDomini.getInstance().importaGrup(path, nomPE, nomA);
		if(s != null) Main.getInstance().showWarning("Error importar Grup",s);
	}
	
	/**
	 * Importa un horari
	 * @param path path del fitxer que volem 
	 * @param nomC nom del campus
	 * @param nomPE nom del pla d'estudis
	 * @param id identificador de l'horari
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public void importaHorari(String path, String nomC, String nomPE, int id) {
		String s = ControladorDomini.getInstance().importaHorari(path, nomC, nomPE, id);
		if(s != null) Main.getInstance().showWarning("Error importar Horari",s);
	}
	
	/**
	 * Importa un Pla d'estudis
	 * @param path  path del fitxer que volem 
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public void importaPlaEstudis(String path) {
		String s = ControladorDomini.getInstance().importaPlaEstudis(path);
		if(s != null) Main.getInstance().showWarning("Error importar Pla d'Estudis",s);
	}
	
	/**
	 * Importa una sessió de grup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @param nomA nom de l'assignatura
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public void importaSessioGrup(String path, String nomPE, String nomA) {
		String s = ControladorDomini.getInstance().importaSessioGrup(path, nomPE, nomA);
		if(s != null) Main.getInstance().showWarning("Error importar Sessio Grup",s);
	}

	/**
	 * Importa una sessió de subgrup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis 
	 * @param nomA nom de l'assignatura
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public void importaSessioSubGrup(String path, String nomPE, String nomA) {
		String s = ControladorDomini.getInstance().importaSessioSubGrup(path, nomPE, nomA);
		if(s != null) Main.getInstance().showWarning("Error importar Sessio Sub Grup",s);
	}

	/**
	 * Importa un subgrup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis al qual pertany el subgrup
	 * @param nomA nom de l'assignatura a la qual pertany el subgrup
	 * @param grup nom del grup al qual pertany el grup
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public void importaSubGrup(String path, String nomPE, String nomA, int grup) {
		String s = ControladorDomini.getInstance().importaSubGrup(path, nomPE, nomA, grup);
		if(s != null) Main.getInstance().showWarning("Error importar Sub Grup",s);
	}
}
