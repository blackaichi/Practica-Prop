package classes;

import java.util.*;


public class Campus {
	/**
	 * Identificador del campus on estan situades les aules
	 */
	private String nom_campus;
	
	/**
	 * HashSet que conté tots els campus que han estat creats
	 */
	static private HashSet<Campus> campus;
	
	/**
	 * HashSet que conte les aules que pertanyen al campus
	 */
	private HashSet<Aula> aules;
	
	////////////////////////////
	/////////PRIVADES///////////
	private Boolean comprova_campus(String nom_campus) {
		for (Campus c: campus) if (c.getNomCampus().equals(nom_campus)) return true;
		return false;
	}
	
	private int crida_Excepcions(String excep) {
		if (excep.contentEquals("Campus ja existeix")) return 1;
		else if (excep.contentEquals("No hi han aules")) return 130;
		else if (excep.contentEquals("Ja conté l'aula")) return 131;
		else if (excep.contentEquals("L'aula ja s'ha eliminat o no pertany al campus")) return 132;
		else return 0;
		
	}
	////////////////////////////
	/////////PUBLIQUES///////////
	/**
	 * Creadora de Campus amb parametres
	 * @param nom_campus nom del campus
	 */
	public Campus(String nom_campus) throws Exception {
		if (comprova_campus(nom_campus)) crida_Excepcions("Campus ja existeix"); 
		this.setNomCampus(nom_campus);
		campus.add(this);
		aules = new HashSet<>();
	}
	
	////////////////////////////
	/////////SETTERS///////////
	/**
	 * Assigna el nou objecte campus
	 * @param c nou objecte de tipus campus
	 */
	
	public void setCampus(Campus c) {
		this.nom_campus = c.nom_campus;
		this.aules = c.aules;
	}
	/**
	 * Assigna el nou nom del campus
	 * @param nom_campus nom del campus
	 */
	
	public void setNomCampus(String nom_campus) {
		this.nom_campus = nom_campus;
	}
	/**
	 * Setter de les aules que pertanyen a un campus
	 * @param aules HashSet de les noves aules que pertanyen al campus
	 * @throws Exception si no hi han aules en el HashSet
	 */
	public void setAules(HashSet<Aula> aules) throws Exception {
		if (aules.isEmpty()) crida_Excepcions("No hi han aules");
		this.aules = aules;
	}
	
	////////////////////////////
	/////////GETTERS///////////
	/**
	 * Retorna el Campus
	 * @return el campus on esta situada l'aula
	 */
	
	public Campus getCampus() {
		return this;
	}
	
	/**
	 * Retorna totes les aules que pertanyen al campus
	 * @return el HashSet de les aules
	 */
	public HashSet<Aula> getAules() throws Exception{
		if (aules.isEmpty()) crida_Excepcions("No hi han aules");
		return this.aules;
	}
	
	/**
	 * Retorna una aula que pertany al campus
	 * @param nom_aula
	 * @return objecte aula amb el nom 'id_aula'
	 * @throws Exception si l'aula que estem buscant no existeix
	 */
	
	public Aula getAula(String id_aula) throws Exception {
		if (aules.isEmpty()) crida_Excepcions("No hi han aules");
		for (Aula a: aules) {
			if (a.getIdAula().equals(id_aula)) return a;
		}
		crida_Excepcions("L'aula no existeix");
		return null;
	}

	
	/**
	 * Retorna el nom del campus on esta l'aula
	 * @return nom del campus
	 */
	
	public String getNomCampus() {
		return this.nom_campus;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////////FUNCIONS//////////////////////////
	
	public void afegirAula(Aula a) throws Exception {
		if (aules.contains(a)) crida_Excepcions("Ja conté l'aula");
		this.aules.add(a);
	}
	
	public void eliminarAula(Aula a) throws Exception {
		if (!aules.contains(a)) crida_Excepcions("L'aula ja s'ha eliminat o no pertany al campus");
		else if (aules.isEmpty()) crida_Excepcions("Ja conté l'aula");
		aules.remove(a);
	}
	
	public void modificarAula(String id_aula, int capacitat, HashSet<String> material) throws Exception {
		for (Aula a: aules) {
			if (a.getIdAula().equals(id_aula)) {
				a.setIdAula(id_aula);
				a.setCapacitat(capacitat);
				a.setMaterial(material);
			}
		}
	}

}
