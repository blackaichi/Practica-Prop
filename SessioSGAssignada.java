package classes;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioSGAssignada extends SessioAssignada{
	/**
	 * Identifica el subgrup al qual pertany la sessió
	 */
	private SubGrup subgrup;
	
	/**
	 * Identifica la sessio de subgrup a la qual pertany
	 */
	private SessioSubGrup sessiosubgrup;
	
	/**
	 * retorna subgrup al qual pertany la sessio del subgrup
	 * @return el subgrup al qual pertany la sessio del subgrup
	 */
	public SubGrup getSubGrup() {
		return subgrup;
	}
	
	/**
	 * retorna la sessio del subgrup al qual pertany
	 * @return sessio subgrup a la qual pertany
	 */
	public SessioSubGrup getSessioSubGrup() {
		return sessiosubgrup;
	}
}
