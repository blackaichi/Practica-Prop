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
		reader.close();
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
		a.afegirEquip("PC");
		if (a.getEquip() != null) System.out.println("Get/Set equip OK");
		else System.out.println("Get/Set equip ERROR");
	}
	/**
	 * Comprova les funcions add i del de l'equip de materials que te cada aula
	 * @throws Exception
	 */
	public static void checkAddDelEquip() throws Exception {
		Campus c = new Campus("Campus Nord");
		Aula a = new Aula(c, "A6001", 40);
		Scanner reader = new Scanner(System.in);
		String equip = reader.next();
		reader.close();
		int size1 = a.quantEquip();
		a.afegirEquip(equip);
		if (a.checkEquip(equip)) System.out.println("CheckEquip OK");
		else System.out.println("CheckEquip ERROR");
		int size2 = a.quantEquip();
		a.eliminaEquip(equip);
		int size3 = a.quantEquip();
		if (size3 == size1 && size1 + 1 == size2) System.out.println("AddDelEquip OK");
		else System.out.println("AddDelEquip ERROR");
		a.resetEquip();
		if (a.quantEquip() == 0) System.out.println("ResetEquip OK");
		else System.out.println("ResetEquip ERROR");
	}
}
