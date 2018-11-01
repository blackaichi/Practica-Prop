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
	 * Quants hores té aquesta sessió
	 */	
	private int hores;
	
	/**
	 * De quin tipus es la sessió
	 */	
	private String tipus;
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes hores té la sessió
	 * @param n: nombre d'hores de la sessió que entra l'usuari
	 * @throws Exception si hora < 1
	 */
	public void setHores(int hores) throws Exception {
		if (hores < 1) throw new Exception("l'hora no pot ser negativa");
		this.hores = hores;
	}
	
	/**
	 * Assigna de quin tipus es la sessió
	 * @param tipus: tipus de sessió que entra l'usuari
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
	
	 /** Retorna el tipus de la sessió
	 * @return tipus de la sessió
	 */
	public String getTipus() {
		return this.tipus;
	}
}
