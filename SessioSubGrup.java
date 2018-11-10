package classes;

import java.util.HashSet;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioSubGrup extends Sessio{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////

	/**
	 * Indica el nombre màxim de sessions d'aquest tipus
	 */
	private int nsessions;
	
	/**
	 * Registra totes les sessions de subgrup assignades
	 */
	private HashSet<SessioSGAssignada> sessionsSGA;
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////
	
	/**
	 * Elimina una sessió de subGrup assignada del HashSet
	 * @param sSGA la sessió de grup assignada que volem esborrar
	 */
	private void eliminaElementHashSet(SessioSGAssignada sSGA) {
		sessionsSGA.removeIf(item -> item.getSessioSubGrup().getTipus().equals(tipus) &&
							item.getSessioSubGrup().getHores() == hores);
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioSubGrup amb assignatura i tipus com a paràmetre, per defecte nsessio = 1
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 * @param tipus el tipus de sessió del subGrup
	 */
	public SessioSubGrup(Assignatura assig, String tipus) throws Exception{
		super(assig, tipus); // crida a la constructora de Sessio		
		setnsessions(1);
		this.sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/**
	 * Creadora de SessioSubGrup amb tots els paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre de sessions de la sessió del subgrup
	 */
	public SessioSubGrup(Assignatura assig, int hores, String tipus, int nsessions) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio 
		if (hores > assignatura.getHLab()) ExceptionManager.thrower(101);
		int checker;
		if ((checker = setnsessions(nsessions)) != 0) ExceptionManager.thrower(checker);
		sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/**
	 * Creadora de SessioSubGrup amb assignatura, hores i tipus com a paràmetres, per defecte nsessions = 1
	 * @param assig l'assignatura a la qual pertany la sessió del subGrup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 */
	public SessioSubGrup(Assignatura assig, int hores, String tipus) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio 
		if (hores > assignatura.getHLab()) ExceptionManager.thrower(101);
		setnsessions(1);
		sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes sessions té el subGrup
	 * @param nsessions nombre de sessions
	 */
	public int setnsessions(int nsessions) throws Exception {
		if (nsessions < 1) return 95;
		if (assignatura.getHLab() < nsessions) return 102;
		this.nsessions = nsessions;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre de sessions del subgrup
	 * @return el nombre de sessions del subgrup
	 */
	public int getnsessions() {
		return nsessions;
	}
	
	/**
	 * Retorna totes les sessions de subgrup assignades
	 * @return un HashSet amb les sessions de subgrup assignades
	 */
	public HashSet<SessioSGAssignada> getAllSessionsSGA() {
		return sessionsSGA;
	}
	
	/**
	 * Retorna la quantitat de sessions tenim assignades
	 * @return quantitat de sessions assignades
	 */
	public int getnSessionsAssignades() {
		return sessionsSGA.size();
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Assigna una nova sessió i la guarda al Hashset on tenim les sessions assignades
	 * @param numero numero de subgrup al qual assignarem la sessió 
	 */
	public void assignaSessio(int numerogrup, int numerosubgrup) {
		SubGrup subgrup = assignatura.getGrup(numerogrup).getSubGrup(numerosubgrup); // obtenim el subgrup que necessitem
		SessioSGAssignada sSGA = new SessioSGAssignada(subgrup, this); // creem una sessió assignada
		
		sessionsSGA.add(sSGA); // afegim la sessió assignada al nostre hashset
		int checker;
		if ((checker = subgrup.afegeixSessio(sSGA)) != 0) ExceptionManager.thrower(checker); // demanem a subgrup que també es guardi la sessió assignada
	}
	
	/**
	 * Desassigna la sessió assignada passada per paràmetre
	 * @param sSGA la sessió de subgrup assignada que volem desassignar
	 */
	public int desassignaSessio(SessioSGAssignada sSGA) {
		if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 103;
		SubGrup subgrup = sSGA.getSubGrup();
		
		eliminaElementHashSet(sSGA);
		int checker;
		if ((checker = subgrup.eliminaSessio(sSGA)) != 0) ExceptionManager.thrower(checker);
		return 0;
	}
	
	/**
	 * Afegeix una nova sessió assignada al Hashset que previament ha assignat la classe subgrup
	 * @param sSGA la sessió de subgrup assignada que hem de guardar al Hashset
	 */
	public int afegirSessio(SessioSGAssignada sSGA) throws Exception {
		if (this.sessionsSGA.contains(sSGA)) return 98;
		else if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 104;
		
		sessionsSGA.add(sSGA);
		return 0;
	}
	
	/**
	 * Elimina una sessió assignada del Hashset que previament ja ha desassignat la classe subgrup
	 * @param sSGA la sessió del subgrup desassignada que hem d'eliminar del Hashset
	 */
	public int eliminarSessio(SessioSGAssignada sSGA) {
		if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 105;
		
		eliminaElementHashSet(sSGA);
		return 0;
	}
}
