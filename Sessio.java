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
	 * Quants hores te aquesta sessio
	 */	
	private int hores;
	
	/**
	 * De quin tipus es la sessio
	 */	
	private String tipus;
	
	/**
	 * Assignatura a la qual pertany la sessio
	 */
	private Assignatura assignatura;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de Sessio amb parametres
	 * @param hores: nombre d'hores de la sessio que entra l'usuari
	 * @param tipus: tipus de sessio que entra l'usuari
	 */
	public Sessio(int hores, String tipus) throws Exception {
		this.setHores(hores);
		this.setTipus(tipus);
	}
	
	/**
	 * Creadora de Sessio sense parametres
	 */
	public Sessio() throws Exception {
		this.hores = 0;
		this.tipus = new String("NAN");
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes hores te la sessio
	 * @param hores: nombre d'hores de la sessio que entra l'usuari
	 * @throws Exception si hora < 1
	 */
	public void setHores(int hores) throws Exception {
		if (hores < 1) throw new Exception("l'hora no pot ser negativa");
		this.hores = hores;
	}
	
	/**
	 * Assigna de quin tipus es la sessio
	 * @param tipus: tipus de sessio que entra l'usuari
	 */
	public void setTipus(String tipus) {
		this.tipus = tipus;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre d'hores de la sessio
	 * @return hores de la sessio
	 */
	public int getHores() {
		return this.hores;
	}
	
	 /** Retorna el tipus de la sessio
	 * @return tipus de la sessio
	 */
	public String getTipus() {
		return this.tipus;
	}
	
	/**
	 * Retorna ll'assignatura a la qual pertany la sessio
	 * @return l'assignatura a la qual pertany la sessio
	 */
	public Assignatura getAssignatura() {
		return assignatura;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Cambia el tipus de la sessio
	 * @param tipus: nou tipus de sessio
	 */
	public void modificaTipus(String tipus) {
		this.tipus = tipus;
	}
	
	/**
	 * Cambia les hores de la sessio
	 * @param hores: nou nombre d'hores
	 * @throws Exception si hora < 1
	 */
	public void modificaHores(int hores) throws Exception {
		if (hores < 1) throw new Exception("l'hora no pot ser negativa");
		this.hores = hores;
	}
}
