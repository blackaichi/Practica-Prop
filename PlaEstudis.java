package classes;
import java.util.*;
import utilitats.Pair;
/**
 * 
 * @author adria.maneros@est.fib.upc.edu
 *
 */
public class PlaEstudis {
	
/////////////////////////////////////////////////////////////
////////////////////////Variables //////////////////////////
	
/**
 * Nom  del Pla d'estudis
 */
	private static String nom;

/**
 * Hores de teoria  de l'Assignatura
 */
	Map<Integer, Pair<Integer,Integer>[] > franja;

/**
 * Assignatures que pertanyen al Pla d'Estudis
 */
	HashSet<Assignatura> assignatures;

/////////////////////////////////////////////////////////////
//////////////////////  Constructora  ///////////////////////
	
/**
 * Creadora de Pla d'Estudis amb parametres.
 * @param nom: nom del Pla d'Estudis.
 * @param franja: 
 */
	public PlaEstudis(String nom) throws Exception {
		this.setNom(nom);
		this.franja = new HashMap<Integer, Pair<Integer,Integer>[] >();
	}
	
/**
 * Creadora de Pla d'Estudis sense parametres.
 */
	public PlaEstudis() throws Exception {
		this.nom = new String("NAN");
		this.franja = new HashMap<Integer, Pair<Integer,Integer>[] >();
	}
	
/**
 * Assigna el nom del Pla d'Estudis.
 * @param nom: nom del Pla d'Estudis que entra l'usuari.
 */
	public void setNom(String nom) throws Exception {
		this.nom = nom;
	}
	
/**
 * Assigna la franja del Pla d'Estudis.
 * @param dia: dia de franja que entra l'usuari.
 * @param iniciFranja: Hora d'inici de la franja que entra l'usuari.
 * param finalFranja: Hora final de la franja que entra l'usuari.
 */
	public void setFranja(int dia, int iniciFranja, int finalFranja) throws Exception {
		//puc cridar multiples vegades a setFranja i si dia ja té una assignada hauré de comprovar que la que vull afegir no es solapi i agefirla.
		// integer de dia no pot ser < 0. Franja no pot ser amb int negatiu. Franja no es pot solapar.
	}
	
/////////////////////////////////////////////////////////////
////////////////////////Getters  //////////////////////////
	
/**
 * Retorna el Nom del Pla d'Estudis.
 * @return Nom del Pla d'Estudis.
 */
	public String getNom() {
		return this.nom;
	}
	
/**
 * Retorna la franja del dia indicat del Pla d'Estudis.
 * @return Franja del dia.
 */
	Pair<Integer,Integer>[] getFranjaDia(int dia) {
		//puc cridar multiples vegades a setFranja i si dia ja té una assignada hauré de comprovar que la que vull afegir no es solapi i agefirla.
		// integer de dia no pot ser < 0. Franja no pot ser amb int negatiu. Franja no es pot solapar.
	}
	
/**
 * Retorna la franja la setmana del Pla d'Estudis.
 * @return Franja de la setmana.
 */
	Pair<Integer,Integer>[] getFranjaSetmana() {
		//puc cridar multiples vegades a setFranja i si dia ja té una assignada hauré de comprovar que la que vull afegir no es solapi i agefirla.
		// integer de dia no pot ser < 0. Franja no pot ser amb int negatiu. Franja no es pot solapar.
	}
}
