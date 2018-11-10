package classes;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioGrup extends Sessio{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////

	/**
	 * Indica el nombre màxim de sessions d'aquest tipus
	 */
	private int nsessions;
	
	/**
	 * Registra totes les sessions de grup assignades
	 */
	private HashSet<SessioGAssignada> sessionsGA;
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////
	
	/**
	 * Elimina una sessió de grup assignada del HashSet
	 * @param sGA la sessió de grup assignada que volem esborrar
	 */
	private void eliminaElementHashSet(SessioGAssignada sGA) {
		sessionsGA.removeIf(item -> item.getSessioGrup().getTipus().equals(tipus) &&
							item.getSessioGrup().getHores() == hores);
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioGrup amb assignatura i tipus com a paràmetre, per defecte nsessio = 1
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param tipus el tipus de sessió del grup
	 */
	public SessioGrup(Assignatura assig, String tipus) throws Exception{
		super(assig, tipus); // crida a la constructora de Sessio 
		setnsessions(1);
		sessionsGA = new HashSet<SessioGAssignada>();
	}

	/**
	 * Creadora de SessioGrup amb tots els paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre sessions de la sessió del grup
	 */
	public SessioGrup(Assignatura assig, int hores, String tipus, int nsessions) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio 
		if (hores > assignatura.getHTeo()) ExceptionManager.thrower(94);
		int checker;
		if ((checker = setnsessions(nsessions)) != 0) ExceptionManager.thrower(checker);
		sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/**
	 * Creadora de SessioGrup amb assignatura, hores i tipus com a paràmetres, per defecte nsessions = 1
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 */
	public SessioGrup(Assignatura assig, int hores, String tipus) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio 
		if (hores > assignatura.getHTeo()) ExceptionManager.thrower(94);
		setnsessions(1);
		sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes sessions té el grup
	 * @param n nombre de sessions
	 */
	public int setnsessions(int nsessions) {
		if (nsessions < 1) return 95;
		if (assignatura.getHTeo() < nsessions) return 96;
		this.nsessions = nsessions;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre de sessions màximes del grup
	 * @return el nombre de sessions màximes del grup
	 */
	public int getnsessions() {
		return nsessions;
	}
	
	/**
	 * Retorna totes les sessions de grup assignades
	 * @return un HashSet amb les sessions de grup assignades
	 */
	public HashSet<SessioGAssignada> getAllSessionsGA() {
		return sessionsGA;
	}
	
	/**
	 * Retorna la quantitat de sessions tenim assignades
	 * @return quantitat de sessions assignades
	 */
	public int getnSessionsAssignades() {
		return sessionsGA.size();
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Assigna una nova sessió i la guarda al Hashset on tenim les sessions assignades
	 * @param numero numero de grup al qual assignarem la sessió 
	 */
	public void assignaSessio(int numero) {
		Grup grup = assignatura.getGrup(numero); // obtenim el grup que necessitem
		SessioGAssignada sGA = new SessioGAssignada(grup, this); // creem una sessió assignada
		
		sessionsGA.add(sGA); // afegim la sessió assignada al nostre hashset
		int checker;
		if ((checker = grup.afegeixSessio(sGA)) != 0) ExceptionManager.thrower(checker); 
	}
	
	/**
	 * Desassigna la sessió assignada passada per paràmetre
	 * @param sGA la sessió de grup assignada que volem desassignar
	 */
	public int desassignaSessio(SessioGAssignada sGA) {
		if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 97;
		Grup grup = sGA.getGrup();
		
		eliminaElementHashSet(sGA);
		int checker;
		if ((checker = grup.eliminaSessio(sGA)) != 0) ExceptionManager.thrower(checker);
		return 0;
	}
	
	/**
	 * Afegeix una nova sessió assignada al Hashset que previament ha assignat la classe grup
	 * @param sGA la sessió de grup assignada que hem de guardar al Hashset
	 */
	public int afegirSessio(SessioGAssignada sGA) {
		if (sessionsGA.contains(sGA)) return 98;
		else if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 99;
		
		sessionsGA.add(sGA);
		return 0;
	}
	
	/**
	 * Elimina una sessió assignada del Hashset que previament ja ha desassignat la classe grup
	 * @param sGA la sessió del grup desassignada que hem d'eliminar del Hashset
	 */
	public int eliminarSessio(SessioGAssignada sGA) {
		if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 100;
		
		eliminaElementHashSet(sGA);
		return 0;
	}
}
