package domini.classes;

import java.util.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public abstract class Sessio {
    /////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Registra l'equip necessari per la sessió
	 */
	private HashSet<String> material;
	
	/**
	 * Quantes hores té cada sessió
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
	
	/**
	 * Nombre de sessions que s'han de fer
	 */
	protected int nsessions;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////

	/**
	 * Creadora de Sessio amb assig i tipus com a paràmetres, per defecte hores = 1 i nsessions = 1
	 * @param assig assignatura a la qual pertany la sessió
	 * @param tipus tipus de la sessió
	 * @throws Exception
	 */
	protected Sessio(Assignatura assig, String tipus) throws Exception {
		ExceptionManager.thrower(setAssignatura(assig));
		ExceptionManager.thrower(setTipus(tipus));
		setHores(1);
		setnsessions(1);
		material = new HashSet<String>();
	}
	
	/**
	 * Creadora de Sessio amb Assignatura, hores i tipus com a paràmetres, per defecte nsessions = 1
	 * @param assig assignatura a la qual pertany la sessió
	 * @param hores nombre d'hores de la sessió que entra l'usuari
	 * @param tipus tipus de sessió que entra l'usuari
	 * @throws Exception
	 */
	protected Sessio(Assignatura assig, int hores, String tipus) throws Exception {
		ExceptionManager.thrower(setAssignatura(assig));
		ExceptionManager.thrower(setTipus(tipus));
		ExceptionManager.thrower(setHores(hores));
		setnsessions(1);
		material = new HashSet<String>();
	}
	
	/**
	 * Creadora de Sessio amb tots els paràmetres
	 * @param assig assignatura a la qual pertany la sessió
	 * @param hores nombre d'hores de la sessió que entra l'usuari
	 * @param tipus tipus de sessió que entra l'usuari
	 * @param nsessions nombre de sessions que es poden assignar
	 * @throws Exception
	 */
	protected Sessio(Assignatura assig, int hores, String tipus, int nsessions) throws Exception {
		ExceptionManager.thrower(setAssignatura(assig));
		ExceptionManager.thrower(setTipus(tipus));
		ExceptionManager.thrower(setHores(hores));
		ExceptionManager.thrower(setnsessions(nsessions));		
		material = new HashSet<String>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna una assignatura a la sessió
	 * @param assignatura assignatura a la qual pertany la sessió
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int setAssignatura(Assignatura assignatura) {
		if (assignatura == null) return 90;
		this.assignatura = assignatura;
		return 0;
	}
		
	/**
	 * Assigna quantes hores té la sessió
	 * @param hores nombre d'hores de la sessió
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public abstract int setHores(int hores);
	
	/**
	 * Assigna de quin tipus es la sessió
	 * @param tipus tipus de la sessió
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int setTipus(String tipus) {
		if (tipus == null || tipus.isEmpty()) return 92;
		else if((this.tipus == null || this.tipus.equals(tipus)) /*&& this.assignatura.checkSessio(tipus, hores)*/) return 93;
		/*S'ha de comprovar que la sessio que s'esta pretenent assignar, sigui nova, o una modificació, no 
		 * solapi amb una altre sessio ja creada!*/
		
		if(!this.tipus.equals(tipus)) {
			char[] chars = tipus.toCharArray();
			for (char c : chars) {
		        if(!Character.isLetter(c)) return 93; // He de ficar el numero que li tocaretornar
		    }
		}
		
		this.tipus = tipus;
		return 0;
	}
	
	/**
	 * Assigna material a la sessió
	 * @param material Tot el material necessari per la sessió
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int setMaterial(HashSet <String> material) {
		if (material == null) return 109;
		this.material = material;
		return 0;
	}
	
	/**
	 * Assigna nsessions a la sessió
	 * @param nsessions nombre de sessions de la sessió
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public abstract int setnsessions(int nsessions);
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre de sessions que es poden assignar
	 * @return el nombre de sessions que es poden assignar
	 */
	public int getnsessions() {
		return nsessions;
	}
	
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
	
	/**
	 * Retorna el material necessari per la sessió en un HashSet de String
	 * @return el material necessari per la sessió
	 */
	public HashSet <String> getMaterial() {
		return material;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Afegeix material a la sessió
	 * @param equip material a afegir
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int addMaterial(String equip) {
		if (equip == null || equip.isEmpty()) return 108;
		if (material.contains(equip)) return 106;
		material.add(equip);
		return 0;
	}
	
	/**
	 * Elimina material a la sessió
	 * @param equip material a eliminar
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int delMaterial(String equip) {
		if (equip == null|| equip.isEmpty()) return 108;
		if (!material.contains(equip)) return 107;
		material.remove(equip);
		return 0;
	}
}
