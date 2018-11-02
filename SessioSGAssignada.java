package classes;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioSGAssignada extends SessioAssignada{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////

	/**
	 * Identifica el subgrup al qual pertany la sessio
	 */
	private SubGrup subGrup;
	
	/**
	 * Identifica la sessio de subgrup a la qual pertany
	 */
	private SessioSubGrup sessioSubGrup;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	* Creadora de SessioSGAssignada sense parametres
	*/
	public SessioSGAssignada() throws Exception {}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	/**
	 * retorna subgrup al qual pertany la sessio del subgrup
	 * @return el subgrup al qual pertany la sessio del subgrup
	 */
	public SubGrup getSubGrup() {
		return subGrup;
	}
	
	/**
	 * retorna la sessio del subgrup al qual pertany
	 * @return sessio subgrup a la qual pertany
	 */
	public SessioSubGrup getSessioSubGrup() {
		return sessioSubGrup;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
}
