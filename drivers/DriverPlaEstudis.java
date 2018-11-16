package drivers;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Scanner;

import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverPlaEstudis {

	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació de la classe SessioGrup");
		int n = 1;
		Scanner reader = null;
		while (n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6) {
			System.out.println("1- Constructora, 2- comprova un error de la constructora, 3- Setters/Getters, 4- checkRang, 5- Alta/BaixaAssignatura, else- Stop");
			System.out.print("Enter an integer: ");
			reader = new Scanner(System.in);
			n = reader.nextInt();
			if (n == 1) checkConstructora();
			else if (n == 2) checkConstructoraError();
			else if (n == 3) checkGetSet();
			else if (n == 4) checkRang();
			else if (n == 5) checkAltaBaixaAssignatura();
			else if (n == 6) checkFranja();
		}
		reader.close();
	}

	/**
	 * Comprova la constructora
	 * @throws Exception
	 */
	private static void checkConstructora() throws Exception {
		PlaEstudis pe = new PlaEstudis("fib");
		System.out.println("Correcte.");
	}
	
	/**
	 * Provoca un error per veure si funciona
	 */
	private static void checkConstructoraError() {
		try {
			PlaEstudis pe = new PlaEstudis("");
		}catch(Exception e) {
			if (e.getMessage() == "Nom no pot ser null") System.out.println("Error tractat correctament");
			else System.out.println("Error tractat malament");
		}
	}
	
	private static void checkGetSet() throws Exception {
		PlaEstudis pe = new PlaEstudis("fib");
		System.out.println("Pels setters/getters creem un pla d'estudis");
		System.out.print("Introdueixi el nom(String): ");
		Scanner reader = new Scanner(System.in);
		String nom = reader.next();
		pe.setNom(nom);
		if (pe.getNom().equals(nom)) System.out.println("set/getNom correcte");
		else System.out.println("set/getNom error");
	}
	
	private static void checkRang() throws Exception {
		PlaEstudis pe = new PlaEstudis("fib");
		int[] rang = new int[] {3,6,12,23};
		pe.setRangDia(rang);
		assertArrayEquals(new int[] {3,6}, pe.getRangMati());
		assertArrayEquals(new int[] {12,23}, pe.getRangTarda());
		System.out.println("Correcte.");
	}
	
	private static void checkAltaBaixaAssignatura() throws Exception {
		PlaEstudis pe = new PlaEstudis("fib");
		boolean b = true;
		if (pe.quantesAssignatures() != 0) b = false;
		pe.altaAssignatura("PROP");
		if (pe.quantesAssignatures() != 1) b = false;
		pe.altaAssignatura("IES");
		if (pe.quantesAssignatures() != 2) b = false;
		pe.baixaAssignatura("PROP");
		if (pe.quantesAssignatures() != 1) b = false;
		pe.baixaAssignatura("IES");
		if (pe.quantesAssignatures() != 0) b = false;
		if (b) System.out.println("Correcte.");
		else System.out.println("Error.");
	}
	
	private static void checkFranja() throws Exception {
		PlaEstudis pe = new PlaEstudis("fib");
		boolean b = true;
		int[] i = new int[] {8,20};
		boolean[] ii2 = new boolean[] {false, false, true, false, false, false, false, false,
									   false, false, false, false, false, false, false, false,
									   false, false, false, false, false, false, false, false};
		boolean[] ii = new boolean[] {false, false, false, false, false, false, false, false, 
									  true, true, true, true, true, true, true, true, 
									  true, true, true, true, false, false, false, false};
		pe.setFranja(2, i);
		if (pe.getFranjaDia(2) != ii) b = false;
		pe.setFranja(2, ii2);
		pe.delFranja(2, i);
		if (pe.getFranjaDia(2) != ii2) b = false;
		if (b) System.out.println("Correcte.");
		else System.out.println("Error.");
	}
	
}
