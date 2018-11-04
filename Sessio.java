package classes;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class Sessio {
    /////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Quantes hores té aquesta sessió
	 */	
	private int hores;
	
	/**
	 * De quin tipus és la sessió
	 */	
	private String tipus;
	
	/**
	 * Assignatura a la qual pertany la sessió
	 */
	protected Assignatura assignatura;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////

	/**
	 * Creadora de Sessio sense paràmetres
	 * @param assig assignatura a la qual pertany la sessió
	 */
	public Sessio(Assignatura assig) throws Exception {
		this.setAssignatura(assig);
		this.hores = 0;
		this.tipus = new String("NAN");
	}
	
	/**
	 * Creadora de Sessio amb paràmetres
	 * @param assig assignatura a la qual pertany la sessió
	 * @param hores nombre d'hores de la sessió que entra l'usuari
	 * @param tipus tipus de sessió que entra l'usuari
	 */
	public Sessio(Assignatura assig, int hores, String tipus) throws Exception {
		this.setAssignatura(assig);
		this.setHores(hores);
		this.setTipus(tipus);
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna una assignatura a la sessió
	 * @param assig assignatura que pertany la sessió
	 */
	public void setAssignatura(Assignatura assig) {
		this.assignatura = assig;
	}
		
	/**
	 * Assigna quantes hores té la sessió
	 * @param hores nombre d'hores de la sessió
	 * @throws Exception si hora < 1
	 */
	public void setHores(int hores) throws Exception {
		if (hores < 1) throw new Exception("l'hora no pot ser negativa ni 0");
		this.hores = hores;
	}
	
	/**
	 * Assigna de quin tipus es la sessió
	 * @param tipus tipus de la sessió
	 */
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre d'hores de la sessió
	 * @return hores de la sessió
	 */
	public int getHores() {
		return this.hores;
	}
	
	 /** 
	 * Retorna el tipus de la sessió
	 * @return tipus de la sessió
	 */
	public String getTipus() {
		return this.tipus;
	}
	
	/**
	 * Retorna l'assignatura a la qual pertany la sessió
	 * @return l'assignatura a la qual pertany la sessió
	 */
	public Assignatura getAssignatura() {
		return assignatura;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
}
