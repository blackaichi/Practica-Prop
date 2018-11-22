package domini.classes;

import utils.*;

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
	 * @throws Exception
	 */
	public SessioGAssignada(Grup grup, SessioGrup sessioG) throws Exception {
		super();
		ExceptionManager.thrower(setGrup(grup));
		ExceptionManager.thrower(setSessioGrup(sessioG));
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna el grup a la sessió assignada de grup
	 * @param grup grup al qual pertany la sessió assignada
	 * @return 0 en cas de fet correctament, altrament error
	 */
	public int setGrup(Grup grup) {
		if (grup == null) return 110;
		if (grup == this.grup) return 1;
		this.grup = grup;
		return 0;
	}
	
	/**
	 * Assigna la sessió de grup a la sessió de grup assignada
	 * @param sessioG sessió de grup a la qual pertany la sessió assignada
	 * @return 0 en cas de fet correctament, altrament error
	 */
	public int setSessioGrup(SessioGrup sessioG) {
		if (sessioG == null) return 111;
		if (sessioGrup == sessioG) return 1;
		sessioGrup = sessioG;
		return 0;
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
