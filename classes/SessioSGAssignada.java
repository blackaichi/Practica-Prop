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
	 * Identifica el subgrup al qual pertany la sessió
	 */
	private SubGrup subGrup;
	
	/**
	 * Identifica la sessió de subgrup a la qual pertany
	 */
	private SessioSubGrup sessioSubGrup;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioSGAssignada amb tots els paràmetres
	 * @param subGrup subgrup al qual pertany la sessió assignada
	 * @param sessioSG sessió de subgrup a la qual pertany la sessió assignada
	 */
	public SessioSGAssignada(SubGrup subGrup, SessioSubGrup sessioSG) throws Exception {
		super();
		ExceptionManager.thrower(setSubGrup(subGrup));
		ExceptionManager.thrower(setSessioSubGrup(sessioSG));
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////

	/**
	 * Assigna el subgrup a la sessió assignada de subgrup
	 * @param subGrup subgrup al qual pertany la sessió assignada
	 * @return 0 en cas de fet correctament, altrament error
	 */
	public int setSubGrup(SubGrup subGrup) {
		if (subGrup == null) return 112;
		if (this.subGrup == subGrup) return 1;
		this.subGrup = subGrup;
		return 0;
	}
	
	/**
	 * Assigna la sessió de subgrup a la sessió de subgrup assignada
	 * @param sessioSG sessió de subgrup a la qual pertany la sessió assignada
	 * @return 0 en cas de fet correctament, altrament error
	 */
	public int setSessioSubGrup(SessioSubGrup sessioSG) {
		if (sessioSG == null) return 113;
		if (sessioSubGrup == sessioSG) return 1;
		sessioSubGrup = sessioSG;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * retorna subgrup al qual pertany la sessió del subgrup
	 * @return el subgrup al qual pertany la sessió del subgrup
	 */
	public SubGrup getSubGrup() {
		return subGrup;
	}
	
	/**
	 * retorna la sessió del subgrup al qual pertany
	 * @return sessió subgrup a la qual pertany
	 */
	public SessioSubGrup getSessioSubGrup() {
		return sessioSubGrup;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
}
