package classes;

import java.util.*;

public class Aula {
	
	/**
	 * Identificador de l'aula
	 */
	private String id_aula;
	/**
	 * Capacitat de l'aula
	 */
	private int capacitat;
	/**
	 * Material extra que conte l'aula
	 */
	private HashSet<String> materials;
	
	private Boolean modificat = false;
	
	/**
	 * Campus al que pertany l'aula
	 */
	private Campus campus;
	///////////////////////////////////////////////
	////////////////////////PRIVADES///////////////
	private int comprova_Excepcions(String excep) throws Exception{
		if (excep.contentEquals("La capacitat de l'aula no pot ser 0 o negativa")) return 150;
		else if (excep.contentEquals("No hi ha material")) return 151;
		else if (excep.contentEquals("L'aula ha de formar part d'un campus")) return 152;
		else if (excep.contentEquals("Ja conte el material")) return 153;
		else if (excep.contentEquals("No conte el material")) return 154;
		else return 0;
	}
		
	///////////////////////////////////////////////
	////////////////////////PUBLIQUES///////////////
	
	/**
	 * Creadora de la classe sense parametres
	 * @param a aula assignada
	 * @throws Exception si la capacitat de l'aula es '0' o negativa o be si el material es un cjt buit
	 */
	public Aula(Campus c) throws Exception {
		this.setCampus(c);
		id_aula = new String("");
		capacitat = 0;
		materials = new HashSet<>();
		//aules.add(this);
	}
	
	/**
	 * Creadora de la classe amb parametres
	 * @param id_aula identificador de l'aula
	 * @param capacitat capacitat de l'aula
	 * @param material material que conte l'aula
 	 * @throws Exception si la capacitat de l'aula es '0' o negativa o be si el material es un cjt buit
	 */
	public Aula(String id_aula, int capacitat, HashSet<String> material, Campus c) throws Exception {
		this.setIdAula(id_aula);
		this.setCapacitat(capacitat);
		this.setMaterial(material);
		this.campus.setCampus(c);
		c.afegirAula(this);
		modificat = true;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////////SETTERS///////////////////////////
	/**
	 * Setter de id_aula
	 * @param id_aula nou identificador de l'aula
	 * @throws Exception 
	 */
	public void setIdAula(String id_aula) throws Exception {
		if (modificat) campus.modificarAula(this.getIdAula(), this.getCapacitat(), this.getMaterial());
		this.id_aula = id_aula;
	}
	 /**
	  * Setter de la capacitat
	  * @param capacitat nova capacitat
	  * @throws Exception si la capacitat de l'aula es '0' o negativa
	  */
	public void setCapacitat(int capacitat) throws Exception{
		if (capacitat <= 0) comprova_Excepcions("La capacitat de l'aula no pot ser '0' o negativa");
		else {
			this.capacitat = capacitat;
			if (modificat) campus.modificarAula(this.getIdAula(), this.getCapacitat(), this.getMaterial());
		}
	}
	/**
	 * Setter del cjt. material
	 * @param material 
	 * @throws Exception si el cjt material es buit
	 */
	public void setMaterial(HashSet<String> material) throws Exception {
		if (material.isEmpty()) comprova_Excepcions("No hi ha material");
		else {
			this.materials = material;
			if (modificat) campus.modificarAula(this.getIdAula(), this.getCapacitat(), this.getMaterial());
		}
	}
	
	public void setCampus(Campus c) throws Exception{
		if (c == null)  comprova_Excepcions("L'aula ha de formar part d'un campus");
		campus.eliminarAula(this);
		this.campus = c;
		campus.afegirAula(this);
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////////GETTERS///////////////////////////
	/**
	 * Getter de l'aula
	 * @return objecte aula
	 */
	public Aula getAula() {
		return this;
	}
	
	/**
	 * Getter de id_aula
	 * @return id_aula
	 */
	public String getIdAula() {
		return this.id_aula;
	}
	/**
	 * Getter de la capacitat de l'aula
	 * @return capacitat
	 */
	
	public int getCapacitat() {
		return this.capacitat;
	}
	/**
	 * Getter del cjt. de materials
	 * @return materials
	 */
	public HashSet<String> getMaterial() throws Exception {
		if (materials.isEmpty()) comprova_Excepcions("No hi ha material");
		return this.materials;
	}
	
	
	/**
	 * Getter del campus on pertany l'aula
	 * @return objecte campus
	 */
	public Campus getCampus() {	
		return campus;
	}
	/**
	 * Getter de les aules que pertanyen al campus
	 * @return aules
	 */
	/*
	public HashSet<aula> getAules() {
		return aules;
	}
	*/
	/////////////////////////////////////////////////////////////
	///////////////////////////FUNCIONS//////////////////////////
	/**
	 * Assigna tots els atributs de l'aula
	 * @param a Objecte aula on assignem els atributs
	 * @return el parametre aula
	 */
	public Aula assignaAula(Aula a) {
		if (a.id_aula == this.id_aula) {
			a.capacitat = this.capacitat;
			a.materials = this.materials;
			a.campus = this.campus;
		}
		return a;
	}
	
	/**
	 * Afegir el material 'material' al set de material
	 * @param material string del nou material
	 * @throws Exception si el material ja esta inclos
	 */
	public void afegir_material(String material) throws Exception {
		if (this.materials.contains(material)) comprova_Excepcions("Ja conte el material");
		else this.materials.add(material);
	}
	/**
	 * Esborrar el material 'material' del set de material
	 * @param material string del material a esborrar.
	 * @throws Exception si el material no esta en el cjt.
	 */
	public void esborrar_material(String material) throws Exception {
		if (material.isEmpty()) comprova_Excepcions("No hi ha material");
		else if (!this.materials.contains(material)) comprova_Excepcions("No conte el material");
		else this.materials.remove(material);
	}
	/**
	 * Retorna el numero de materials que conte una aula
	 * @return la quantitat de materials en una aula
	 */
	public int numMaterials() {
		return materials.size();
	}
	
	/**
	 * Indica si l'aula conte el material
	 * @param material material que estem buscant
	 * @return true si l'aula el té, false en cas contrari
	 */
	
	public Boolean conteMaterial(String material) {
		if (material.contains(material)) return true;
		else return false;
	}
}

