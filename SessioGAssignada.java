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
	 * Identifica el grup al qual pertany la sessió
	 */
	private Grup grup;
	
	/**
	 * Identifica la sessió de grup a la qual pertany
	 */
	private SessioGrup sessioGrup;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioGAssignada amb tots els paràmetres
	 * @param grup grup al qual pertany la sessió assignada
	 * @param sessioG sessió de grup a la qual pertany la sessió assignada
	 */
	public SessioGAssignada(Grup grup, SessioGrup sessioG) throws Exception {
		super();
		setGrup(grup);
		setSessioGrup(sessioG);
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna el grup a la sessió assignada de grup
	 * @param grup grup al qual pertany la sessió assignada
	 */
	public void setGrup(Grup grup) {
		this.grup = grup;
	}
	
	/**
	 * Assigna la sessió de grup a la sessió de grup assignada
	 * @param sessioG sessió de grup a la qual pertany la sessió assignada
	 */
	public void setSessioGrup(SessioGrup sessioG) {
		this.sessioGrup = sessioG;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * retorna grup al qual pertany la sessió del grup
	 * @return el grup al qual pertany la sessió del grup
	 */
	public Grup getGrup() {
		return grup;
	}
	
	/**
	 * retorna la sessió del grup al qual pertany
	 * @return sessió grup a la qual pertany
	 */
	public SessioGrup getSessioGrup() {
		return sessioGrup;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
}
