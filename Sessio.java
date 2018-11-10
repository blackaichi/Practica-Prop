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
	protected int hores;
	
	/**
	 * De quin tipus és la sessió
	 */	
	protected String tipus;
	
	/**
	 * Assignatura a la qual pertany la sessió
	 */
	protected Assignatura assignatura;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////

	/**
	 * Creadora de Sessio amb assig i tipus com a paràmetres, per defecte hores = 1
	 * @param assig assignatura a la qual pertany la sessió
	 */
	public Sessio(Assignatura assig, String tipus) throws Exception {
		int check;
		setHores(1);
		if ((check = setTipus(tipus)) != 0) ExceptionManager.thrower(check);
	}
	
	/**
	 * Creadora de Sessio amb tots els paràmetres
	 * @param assig assignatura a la qual pertany la sessió
	 * @param hores nombre d'hores de la sessió que entra l'usuari
	 * @param tipus tipus de sessió que entra l'usuari
	 */
	public Sessio(Assignatura assig, int hores, String tipus) throws Exception {
		int check;
		if ((check = setAssignatura(assig)) != 0) ExceptionManager.thrower(check);
		if ((check = setHores(hores)) != 0) ExceptionManager.thrower(check);
		if ((check = setTipus(tipus)) != 0) ExceptionManager.thrower(check);
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna una assignatura a la sessió
	 * @param assig assignatura que pertany la sessió
	 * @throws Exception en cas de que el paràmetre assignatura sigui null
	 */
	public int setAssignatura(Assignatura assignatura) {
		if (assignatura == null) return 90;
		this.assignatura = assignatura;
		return 0;
	}
		
	/**
	 * Assigna quantes hores té la sessió
	 * @param hores nombre d'hores de la sessió
	 * @throws Exception si hora < 1
	 */
	public int setHores(int hores) throws Exception {
		if (hores < 1) return 91;
		this.hores = hores;
		return 0;
	}
	
	/**
	 * Assigna de quin tipus es la sessió
	 * @param tipus tipus de la sessió
	 */
	public int setTipus(String tipus) {
		if (tipus == null || tipus.isEmpty()) return 92;
		char[] chars = tipus.toCharArray();
		for (char c : chars) {
	        if(!Character.isLetter(c)) return 93; // He de ficar el numero que li tocaretornar
	    }
		this.tipus = tipus;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre d'hores de la sessió
	 * @return hores de la sessió
	 */
	public int getHores() {
		return hores;
	}
	
	 /** 
	 * Retorna el tipus de la sessió
	 * @return tipus de la sessió
	 */
	public String getTipus() {
		return tipus;
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
