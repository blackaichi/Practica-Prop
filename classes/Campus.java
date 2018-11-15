package classes;

import java.util.*;
import utils.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class Campus {
	/**
	 * Enregistra tots el campus donats d'alta, i que
	 * segueixen presents al sistema.
	 * Aquest static es necessari per evitar l'existencia de múltiples
	 * campus amb la mateixa clau primaria.
	 */
	static private HashSet<Campus> campusCreats;
	
	/**
	 * Identifica al campus.
	 */
	private String nom;
	
	/**
	 * Registra totes les aules presents al campus.
	 */
	private HashSet<Aula> aules;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe Campus.
	 * @param nom Identifica el campus que es pretén crear.
	 * @throws Exception
	 */
	public Campus(String nom) throws Exception{
		if(campusCreats == null) campusCreats = new HashSet<>();
		
		ExceptionManager.thrower(this.setNom(nom));
		this.aules = new HashSet<>();
		
		campusCreats.add(this);
	}	
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/**
	 * Assigna un nom identificatiu al campus.
	 * @param nom Identifica el campus
	 * @throws Exception
	 */
	public int setNom(String nom) {
		if(nom == null) return 130; //El nom del campus no pot ser null.
		else if(this.nom != null && this.nom.equals(nom)) return 1;
		else if(checkCampus(nom)) return 131; //Ja existeix un campus amb aquest nom.
		
		this.nom = nom;
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/**
	 * Retorna el nom que identifica el campus.
	 * @return Un String amb valor no null.
	 */
	public String getNom() {
		return this.getNom();
	}
	
	/**
	 * Retorna un set amb totes las aules del campus.
	 * @return Un set buit, o amb molts elements.
	 */
	public HashSet<Aula> getAllAules(){
		return this.aules;
	}
	
	/**
	 * Retorna, si hi és, l'aula 
	 * @param nom Identifica l'aula que es preten obtenir.
	 * @return Una referencia a aula, és null en cas de que no hi sigui.
	 */
	public Aula getAula(String nom) {
		if(nom == null) return null;
		for(Aula aula: aules)
			if(aula.getNom().equals(nom)) return aula;
		
		return null;
	}
	
	/**
	 * Retorna un set amb tots els campus creats.
	 * @return Un set buit o amb molts elements.
	 */
	static public HashSet<Campus> getAllCampus(){
		return campusCreats;
	}
	
	/**
	 * Retorna, si hi és, el campus identificat pel nom indicat;
	 * altrament retorna null.
	 * @param nom Identifica el campus que és preten obtenir.
	 * @return Una referencia a campus, és null en cas de que no hi sigui.
	 */
	static public Campus getCampus(String nom) {
		if(nom == null) return null;
		for(Campus campus: campusCreats)
			if(campus.getNom().equals(nom)) return campus;
		
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Crea una aula dins del campus corresponent sempre i quan els atributs
	 * entrat compleixin les restriccions d'integritat.
	 * @param nom Identifica l'aula dins del campus.
	 * @param capacitat Descriu la capacitat de l'aula.
	 * @return Excepció codificada en forma d'enter.
	 * @throws Exception rebuda durant la donada d'alta d'una nova aula.
	 */
	public int altaAula(String nom, int capacitat) throws Exception{
		if(nom == null) return 132; //El nom de l'aula no pot ser null;
		else if(this.checkAula(nom)) return 133; //El campus ja conté aquesta aula.
		
		this.aules.add(new Aula(this, nom, capacitat));
		return 0;
	}
	
	/**
	 * Elimina, si hi és, l'aula identificada pel nom del campus corresponent;
	 * altrament no fa res.
	 * @param nom Identifica l'aula dins del campus.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int baixaAula(String nom) {
		if(nom == null) return 132;
		else this.aules.removeIf(item -> item.getNom().equals(nom));
		
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna true si, i només si, existeix una aula identificada per
	 * nom; altrament retorna false.
	 * @param nom Identifica l'aula que es pretén comprovar.
	 * @return Un booleà que representa l'existencia de l'aula.
	 */
	public boolean checkAula(String nom) {
		return this.getAula(nom) != null;
	}
	
	/**
	 * Retorna la quantitat d'aules que te el campus.
	 * @return Un enter superior o igual a 0.
	 */
	public int quantesAules() {
		return this.aules.size();
	}
	
	/**
	 * Retorna true si, i només si, existeix un campus identificat per
	 * nom; altrament retorna false.
	 * @param nom Identifica el campus que es pretén comprovar.
	 * @return Un booleà que representa l'existencia del campus.
	 */
	static public boolean checkCampus(String nom) {
		return getCampus(nom) != null;
	}

	/**
	 * Retorna la quantitat de campus creats fins al moment.
	 * @return Un enter igual o superior a 0.
	 */
	static public int quantsCampus() {
		return campusCreats.size();
	}
}
