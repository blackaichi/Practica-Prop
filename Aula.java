package classes;

import java.util.*;

/**
 * 
 * @autor correu_fib
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class Aula {
	/**
	 * Identifica l'aula dins d'un capus concret.
	 */
	private String nom;
	/**
	 * Descriu la capacitat de l'aula.
	 */
	private int capacitat;
	/**
	 * Enregistra tot l'equip/material present a l'aula.
	 */
	private HashSet<String> equip;
	
	/**
	 * Referencia al campus al qual pertany l'aula.
	 */
	private Campus campus;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe Aula.
	 * @param campus Referencia al campus al qual pertany l'aula.
	 * @param nom Identifica l'aula dins del campus.
	 * @throws Exception
	 */
	public Aula(Campus campus, String nom, int capacitat) throws Exception {
		ExceptionManager.thrower(this.setCampus(campus));
		ExceptionManager.thrower(this.setNom(nom));
		ExceptionManager.thrower(this.setCapacitat(capacitat));
		
		this.equip = new HashSet<>();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/**
	 * Assigna un campus a l'aula.
	 * @param campus Referencia al campus que es preten assignar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setCampus(Campus campus) {
		if(campus == null) return 150; //Campus no pot ser null
		else if(this.campus != null) { //En cas d'una modificació:
			if(campus.getNom() == this.campus.getNom()) return 1;
			else if(campus.checkAula(this.getNom())) return 151; //El campus ja conté una aula amb la mateixa identificació
		}
		
		this.campus = campus;
		return 0;
	}
	
	/**
	 * Assigna un nom dentificatiu a l'aula.
	 * @param nom Identifica a l'aula. 
	 * @return Excepció en forma d'enter.
	 */
	public int setNom(String nom) {
		if(nom == null) return 152; //El nom no pot ser null
		else if(this.nom != null) { //En cas d'una modificació:
			if(this.nom.equals(nom)) return 1;
			else if(this.campus != null && this.campus.checkAula(nom)) return 151; //El campus ja conté una aula amb la mateixa identificació
		}
		
		this.nom = nom;
		return 0;
	}
	
	/**
	 * Assigna una capacitat a l'aula.
	 * @param capacitat Descriu la capacitat de l'aula.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setCapacitat(int capacitat) {
		if(capacitat <= 0) return 153; //No pot haver capacitat <= 0;
		else if(this.capacitat == capacitat) return 1;
		
		this.capacitat = capacitat;
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/**
	 * Retorna el nom que identifica l'aula.
	 * @return Un string no null i tampoc buit.
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Retorna el campus al qual pertany l'aula.
	 * @return Referencia al campus de l'aula.
	 */
	public Campus getCampus() {
		return this.campus;
	}
	
	/**
	 * Retorna la capacitat de l'aula.
	 * @return Enter superior a 0.
	 */
	public int getCapacitat() {
		return this.capacitat;
	}
	
	/**
	 * Retorna un set amb tot l'equipament de l'aula.
	 * @return Un set d'strings buit o amb molts elements.
	 */
	public HashSet<String> getEquip(){
		return this.equip;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Afegeix, si no hi es, equip al set d'equip de l'aula.
	 * @param equip Descriu l'equip a afegir.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int afegirEquip(String equip) {
		if(equip == null) return 154; //L'equip no pot ser null;
		else if(this.checkEquip(equip.toLowerCase())) return 155; //L'equip ja existeix en aquesta aula.
		
		this.equip.add(equip.toLowerCase());
		return 0;
	}
	
	/**
	 * Esborra, si hi és, l'equip passat per parametre del set d'equips
	 * de l'aula. Altrament no fa res.
	 * @param equip Descriu l'equip a eliminar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int eliminaEquip(String equip) {
		if(equip == null) return 154;
		else this.equip.removeIf(item -> item.equals(equip.toLowerCase()));
		
		return 0;
	}
	
	/**
	 * Buida TOT l'equip de l'aula.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int resetEquip() {
		this.equip.clear();
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna true si, i només si, l'equipament consultat ja existeix
	 * a l'aula. Altrament retorna false.
	 * @param equip Descriu l'equip a consultar.
	 * @return True si hi és, false si no.
	 */
	public boolean checkEquip(String equip) {
		if(equip == null) return false;
		for(String equipament: this.equip)
			if(equipament.equals(equip)) return true;
		
		return false;
	}

	/**
	 * Indica la quantita d'equip que conté l'aula.
	 * @return Un enter superior o igual a 0.
	 */
	public int quantEquip() {
		return this.equip.size();
	}
}
