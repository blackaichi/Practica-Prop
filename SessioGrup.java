package classes;

import java.util.HashSet;

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
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioGrup amb assignatura com a paràmetre
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 */
	public SessioGrup(Assignatura a) throws Exception{
		super(a); // crida a la constructora de Sessio sense parametres
		sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/**
	 * Creadora de SessioGrup amb assignatura com a paràmetre
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 */
	public SessioGrup(Assignatura a, int hores, String tipus) throws Exception{
		super(a, hores, tipus); // crida a la constructora de Sessio amb parametres
		sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes sessions té el grup
	 * @param n nombre de sessions
	 * @throws Exception si nsessions és 0 o negatiu
	 */
	public void setnsessions(int n) throws Exception {
		if (n < 1) throw new Exception("el nombre de sessions ha de ser mes gran que 0");
		this.nsessions = n;
	}
	
	/**
	 * Assigna una sessió assignada a les sessions del grup
	 * @param s la sessió assignada
	 */
	public void setSessionsGA(SessioGAssignada s) {
		sessionsGA.add(s);
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre de sessions del grup
	 * @return el nombre de sessions del grup
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
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
}
