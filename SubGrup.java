package classes;

import java.util.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class SubGrup {
	//Atributs de la classe SubGrup:
	private int numero;
	private int places;
	
	//Enllaços amb les classes corresponents:
	private Grup grup;	//Identifica al grup al qual pertany el SubGrup.
	//private HashSet<SessioSubC> sessions;	//Registra totes les sessions a les quals pertany el subGrup.
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe: SubGrup.
	 * @param grup Senyala a quin Grup pertany el subGrup.
	 * @param numero Identifica al subGrup.
	 * @param places Descriu la capacitat del SubGrup.
	 */
	public SubGrup(Grup grup, int numero, int places) {
		this.grup = grup;
		
		this.numero = numero;
		this.places = places;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/** 
	 * Assigna un numero identificatiu al subGrup.
	 * @param numero Identifica el subGrup. 
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/** 
	 * Assigna una quantitat de places al subGrup.
	 * @param places Indica el nombre de places assignades al subGrup. 
	 */
	public void setPlaces(int places) {
		this.places = places;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/** 
	 * Retorna el número queidentifica al subGrup.
	 * @return Un enter qualsevol.
	 */
	public int getNumero() {
		return numero;
	}
	
	/** 
	 * Retorna la quantitat de places que te assignades el subGrup.
	 * @return Un enter superior o igual a 0.
	 */
	public int getPlaces() {
		return places;
	}
	
	/**
	 * Retorna la classe del Grup al qual pertany el SubGrup.
	 * @return Una classe de tipus Grup.
	 */
	public Grup getGrup() {
		return grup;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Afegeix la quantitat entrada per parametre de places al subGrup si, 
	 * i només si, nplaces > 0; altrament fa l'acció inversa.
	 * @param incr Quantitat de places a afegir.
	 */
	public void obrirPlaces(int nplaces) {
		this.places += nplaces;
	}
	
	/**
	 * Tanca la quantita entrada per paràmetre de places al SubGrup si,
	 * i només si, nplaces > 0; altrament fa l'acció complementaria.
	 * @param nplaces Quantitat de places a tancar.
	 */
	public void tancarPlaces(int nplaces) {
		this.obrirPlaces(-1 * nplaces); //Passem el valor a egatiu per a que resti, enlloc de sumar.
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
}
