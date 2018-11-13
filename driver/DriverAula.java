package drivers;

import java.util.*;
import classes.*;


public class DriverAula {
	/**
	 * Funcio main del driver, introdueixes un numero i comprova l'operació correponent
	 * @throws Exception
	 */
	public static void main(String [] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe Aula");
		System.out.print("1-Constructora, 2-Getters/Setters, 3-Add/DeleteEquip");
		System.out.print("Enter an integer: ");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while(n == 1 || n == 2 || n == 3) {
			if (n == 1) checkConstructora();
			else if (n == 2) checkGetSet() ;
			else if (n == 3) checkAddDelEquip();
		}
		reader.close();
	}
	/**
	 * Comprova la constructora
	 * @throws Exception
	 */
	
	public static void checkConstructora() throws Exception {
		System.out.println("Per la constructora necessitem la classe Campus i atributs com el nom i la capacitat de l'aula");
		Campus c = new Campus("Campus Nord");
		Aula a = new Aula(c, "A5101", 30);
		System.out.println("Correcte");
	}
	/**
	 * Comprova els getters i els setters
	 * @throws Exception
	 */
	public static void checkGetSet() throws Exception {
		Campus c = new Campus("Campus Nord");
		Aula a = new Aula(c, "A5101", 30);
		System.out.println("Introdueix la capacitat");
		Scanner reader = new Scanner(System.in);
		int cap = reader.nextInt();
		System.out.println("Introdueix el nom de l'aula");
		String nom = reader.next();
		System.out.println("Introdueix el nom del campus");
		String nomCampus = reader.next();
 		Campus nou = new Campus(nomCampus);
		a.setCapacitat(cap);
		if (a.getCapacitat() == cap) System.out.println("Get/Set capacitat OK");
		else System.out.println("Get/Set capacitat ERROR");
		a.setNom(nom);
		if (a.getNom().equals(nom)) System.out.println("Get/Set nom OK");
		else System.out.println("Get/Set nom ERROR");
		a.setCampus(nou);
		if (a.getCampus().getNom().equals(nomCampus)) System.out.println("Get/Set campus OK");
		else System.out.println("Get/Set campus ERROR");
	}
	/**
	 * Comprova les funcions add i del de l'equip de materials que te cada aula
	 * @throws Exception
	 */
	public static void checkAddDelEquip() throws Exception {
		Campus c = new Campus("Campus Nord");
		Aula a = new Aula(c, "A6001", 40);
		Scanner reader = new Scanner(System.in);
		String s = reader.next();
		int size1 = a.getEquip().size();
		a.afegirEquip(s);
		int size2 = a.getEquip().size();
		a.eliminaEquip(s);
		int size3 = a.getEquip().size();
		if (size3 == size1 && size1 + 1 == size2) System.out.println("AddDelEquip OK");
		else System.out.println("AddDelEquip ERROR");
	}
}

	
