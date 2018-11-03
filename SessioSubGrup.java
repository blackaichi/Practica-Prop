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
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioSubGrup amb assignatura com a paràmetre
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 */
	public SessioSubGrup(Assignatura assig) throws Exception{
		super(assig); // crida a la constructora de Sessio
		sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/**
	 * Creadora de SessioSubGrup amb tots els paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 */
	public SessioSubGrup(Assignatura assig, int hores, String tipus) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio
		sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes sessions té el grup
	 * @param n nombre de sessions
	 * @throws Exception si nsessions és 0 o negatiu
	 */
	public void setnsessio(int n) throws Exception {
		if (n < 1) throw new Exception("El nombre de sessions ha de ser més gran que 0");
		this.nsessions = n;
	}
	
	/**
	 * Assigna una sessió assignada a les sessions del subgrup
	 * @param s la sessió assignada
	 */
	public void setSessionsSGA(SessioSGAssignada s) {
		sessionsSGA.add(s);
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
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
}
