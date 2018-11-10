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
	 * Creadora de SessioSGAssignada sense paràmetres
	 */
	public SessioSGAssignada() throws Exception {
		super();
	}
	
	/**
	 * Creadora de SessioSGAssignada amb tots els paràmetres
	 * @param subGrup subgrup al qual pertany la sessió assignada
	 * @param sessioSG sessió de subgrup a la qual pertany la sessió assignada
	 */
	public SessioSGAssignada(SubGrup subGrup, SessioSubGrup sessioSG) throws Exception {
		super();
		setSubGrup(subGrup);
		setSessioSubGrup(sessioSG);
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////

	/**
	 * Assigna el subgrup a la sessió assignada de subgrup
	 * @param subGrup subgrup al qual pertany la sessió assignada
	 */
	public void setSubGrup(SubGrup subGrup) {
		this.subGrup = subGrup;
	}
	
	/**
	 * Assigna la sessió de subgrup a la sessió de subgrup assignada
	 * @param sessioSG sessió de subgrup a la qual pertany la sessió assignada
	 */
	public void setSessioSubGrup(SessioSubGrup sessioSG) {
		this.sessioSubGrup = sessioSG;
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
