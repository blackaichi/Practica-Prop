package classes;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioGrup extends Sessio{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Identifica el grup al qual pertany la sessio del grup
	 */
	private Grup grup;
	
	/**
	 * Totes les sessions de grup assignades al seu grup
	 */
	private SessioGAssignada[] sessioGAssignada;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioGrup sense parametres
	 */
	public SessioGrup() throws Exception{
		super(); // crida a la constructora de Sessio sense parametres
	}
	
	/**
	 * Creadora de SessioGrup amb parametres
	 * @param hores: nombre d'hores de la sessio que entra l'usuari
	 * @param tipus: tipus de sessio que entra l'usuari
	 */
	public SessioGrup(int hores, String tipus) throws Exception{
		super(hores, tipus); // crida a la constructora de Sessio amb parametres
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el grup al qual pertany la sessio del grup
	 * @return grup de la sessio
	 */
	public Grup getGrup() {
		return grup;
	}
	
	/**
	 * Retorna totes les sessions de grup assignades
	 * @return un arrray amb les sessions de grup assignades
	 */
	public SessioGAssignada[] getAllSessionsGA() {
		return sessioGAssignada;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
}
