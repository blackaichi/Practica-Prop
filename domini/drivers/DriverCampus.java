package drivers;

import java.util.*;
import classes.*;
/**
 * 
 * @author Aleix Lluch Serra
 *
 */
public class DriverCampus {
	/**
	 * Funcio main del driver DriverCampus, introdueixes un numero i comprova l'operació correponent.
	 * @throws Exception
	 */
	public static void main(String [] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe Campus");
		System.out.print("1-Constructora, 2-Getters/Setters, 3-Add/DeleteAula");
		System.out.print("Enter an integer: ");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while(n == 1 || n == 2 || n == 3) {
			if (n == 1) checkConstructora();
			else if (n == 2) checkGetSet() ;
			else if (n == 3) checkAddDelAula();
		}
		reader.close();
	}
	/**
	 * Comprova la constructora.
	 * @throws Exception
	 */
	
	public static void checkConstructora() throws Exception {
		System.out.println("Per la constructora necessitem atributs com el nom");
		Scanner reader = new Scanner(System.in);
		System.out.println("Introdueix el nom del campus(String):");
		String nom = reader.next();
		Campus c = new Campus(nom);
		System.out.println("Correcte");
	}
	/**
	 * Comprova els getters i els setters.
	 * @throws Exception
	 */
	public static void checkGetSet() throws Exception {
		Campus c = new Campus("Campus Nord");
		Aula a = new Aula(c, "A5101", 30);
		System.out.println("Introdueix el nom del campus(String):");
		Scanner reader = new Scanner(System.in);
		String nom = reader.next();
		reader.close();
 		c.setNom(nom);
		if (c.getNom().equals(nom)) System.out.println("Get/Set nom OK");
		else System.out.println("Get/Set nom ERROR");
		
		if (c.getAula("A5101") != null) System.out.println("Get/Set Aula OK");
		else System.out.println("Get/Set Aula ERROR");
		
		if(c.getAllAules().size() == 1) System.out.println("Get/Set AllAules OK");
		else System.out.println("Get/Set AllAules ERROR");
		
		if (Campus.getCampus(nom) != null) System.out.println("Get/Set Campus OK");
		else System.out.println("Get/Set Campus ERROR");
		
		if (Campus.quantsCampus() == 1) System.out.println("Get/Set AllCampus OK");
		else System.out.println("Get/Set AllCampus ERROR");
	}
	/**
	 * Comprova les funcions add i del de les aules que conte cada campus.
	 */
	public static void checkAddDelAula() throws Exception {
		Campus c = new Campus("Campus Nord");
		if (Campus.checkCampus("Campus Nord")) System.out.println("checkCampus OK");
		else System.out.println("checkCampus ERROR");
		int size1 = c.quantesAules();
		c.altaAula("A6001", 40);
		if (c.checkAula("A6001")) System.out.println("checkAula OK");
		else System.out.println("checkAula ERROR");
		int size2 = c.quantesAules();
		c.baixaAula("A6001");
		int size3 = c.quantesAules();
		if (size3 == size1 && size1 + 1 == size2) System.out.println("AddDelEquip OK");
		else System.out.println("AddDelEquip ERROR");
	}
}
