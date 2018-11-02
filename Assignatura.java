package classes;
import java.util.*;
/**
 * 
 * @author adria.maneros@est.fib.upc.edu
 *
 */
public class Assignatura {
	
/////////////////////////////////////////////////////////////
////////////////////////Variables //////////////////////////
	
/**
 * Nom  de l'Assignatura
 */
	private String nom;

/**
 * Hores de teoria  de l'Assignatura
 */
	private int hteo;
	
/**
 * Hores de laboratori  de l'Assignatura
 */
	private int hlab;
	
/**
 * Plans d'Estudis que pertanyen a l'Assignatura
 */
	HashSet<PlaEstudis> PlansEstudis;
	
/**
 * Sessions que pertanyen a l'Assignatura
 */
	HashSet<Sessio> Sessions;
	
/**
 * Grups que pertanyen a l'Assignatura
 */
	HashSet<Grup> Grups;

/////////////////////////////////////////////////////////////
//////////////////////  Constructora  ///////////////////////
	
/**
 * Creadora de Assignatura amb parametres.
 * @param nom: nom de l'assignatura.
 * @param hteo: hores de teoria de l'assignatura.
 * @param hlab: hores de laboratori de l'assignatura.
 */
	public Assignatura(String nom, int hteo, int hlab) throws Exception {
		this.setNom(nom);
		this.setHTeo(hteo);
		this.setHLab(hlab);
	}
	
/**
 * Creadora de Assignatura sense parametres.
 */
	public Assignatura() throws Exception {
		this.nom = new String("NAN");
		this.hteo = 0;
		this.hlab = 0;
	}
	
/**
 * Assigna quantes hores de teoria te l'Assignatura.
 * @param hteo: nombre d'hores de teoria de l'Assignatura que entra l'usuari.
 * @throws Exception si hteo < 1.
 */
	public void setNom(String nom) throws Exception {
		this.nom = nom;
	}
	
/**
 * Assigna quantes hores de teoria te l'Assignatura.
 * @param hteo: nombre d'hores de teoria de l'Assignatura que entra l'usuari.
 * @throws Exception si hteo < 1.
 */
	public void setHTeo(int hteo) throws Exception {
		if (hteo < 1) throw new Exception("l'hora no pot ser negativa ni zero");
		this.hteo = hteo;
	}
	
/**
 * Assigna quantes hores de laboratori te l'Assignatura.
 * @param hlab: nombre d'hores de laboratori de l'Assignatura que entra l'usuari.
 * @throws Exception si hlab < 1.
 */
	public void setHLab(int hlab) throws Exception {
		if (hlab < 1) throw new Exception("l'hora no pot ser negativa ni zero");
		this.hlab = hlab;
	}
	
/////////////////////////////////////////////////////////////
////////////////////////Getters  //////////////////////////
	
/**
 * Retorna el Nom de l'Assignatura.
 * @return Nom de l'Assignatura.
 */
	public int getNom() {
		return this.nom;
	}
	
/**
 * Retorna les hores de teoria de l'Assignatura.
 * @return Hores de teoria de l'Assignatura.
 */
	public int getHTeo() {
		return this.hteo;
	}
	
/**
 * Retorna les hores de laboratori de l'Assignatura.
 * @return Hores de laboratori de l'Assignatura.
 */
	public int getHLab() {
		return this.hlab;
	}

/////////////////////////////////////////////////////////////
///////////////////////  Funcions  //////////////////////////
	
/**
 * Cambia el Nom de l'Assignatura.
 * @param tipus: nou Nom de l'Assignatura.
 */
	public void modificaNom(String nom) {
		this.nom = nom;
	}
	
/**
 * Cambia Cambia les hores de teoria de l'Assignatura.
 * @param tipus: nou valor de hores de teoria de l'Assignatura.
 */
	public void modificaHTeo(int hteo) {
		this.hteo = hteo;
	}

/**
 * Cambia Cambia les hores de laboratori de l'Assignatura.
 * @param tipus: nou valor de hores de laboratori de l'Assignatura.
 */
	public void modificaHLab(int hlab) {
		this.hlab = hlab;
	}
	
}

