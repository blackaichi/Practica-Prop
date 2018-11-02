package classes;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioSubGrup extends Sessio{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Identifica el grup al qual pertany la sessio del grup
	 */
	private SubGrup subGrup;
	
	/**
	 * Totes les sessions de subgrup assignades al seu subgrup
	 */
	private SessioSGAssignada[] sessioSGAssignada;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	* Creadora de SessioSubGrup sense parametres
	*/
	public SessioSubGrup() throws Exception{
		super(); // crida a la constructora de Sessio sense parametres
	}
	
	/**
	* Creadora de SessioSubGrup amb parametres
	* @param hores: nombre d'hores de la sessio que entra l'usuari
	* @param tipus: tipus de sessio que entra l'usuari
	*/
	public SessioSubGrup(int hores, String tipus) throws Exception{
		super(hores, tipus); // crida a la constructora de Sessio amb parametres
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el subgrup al qual pertany la sessio del subgrup
	 * @return subgrup de la sessio
	 */
	public SubGrup getSubGrup() {
		return subGrup;
	}
	
	/**
	 * Retorna totes les sessions de subgrup assignades
	 * @return un arrray amb les sessions de subgrup assignades
	 */
	public SessioSGAssignada[] getAllSessionsSGA() {
		return sessioSGAssignada;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
}
