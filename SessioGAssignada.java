package classes;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioGAssignada extends SessioAssignada{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Identifica el grup al qual pertany la sessio
	 */
	private Grup grup;
	
	/**
	 * Identifica la sessio de grup a la qual pertany
	 */
	private SessioGrup sessioGrup;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	* Creadora de SessioAssignada sense parametres
	*/
	public SessioGAssignada() throws Exception {}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * retorna grup al qual pertany la sessio del grup
	 * @return el grup al qual pertany la sessio del grup
	 */
	public Grup getGrup() {
		return grup;
	}
	
	/**
	 * retorna la sessio del grup al qual pertany
	 * @return sessio grup a la qual pertany
	 */
	public SessioGrup getSessioGrup() {
		return sessioGrup;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
}
