package drivers;

import java.util.*;
import restriccions.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverNoSolaparAssignatura {
	
	/**
	 * Funcio main del driver DriverAula, introdueixes un numero i comprova l'operació correponent.
	 * @throws Exception
	 */
	public static void main(String [] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació de la classe DriverNoSolaparAssignatura");		
		int n = 1;
		Scanner reader = null;
		while(n == 1 || n == 2) {
			System.out.println("1-Constructora, 2-Add / Check, else- stop");
			System.out.print("Enter an integer: ");
			reader = new Scanner(System.in);
			n = reader.nextInt();
			if (n == 1) checkConstructora();
			else if (n == 2) comprovaAddCheck();
		}
		reader.close();
	}
	
	/**
	 * Comprova la primera constructora 
	 * @throws Exception
	 */
	private static void checkConstructora() throws Exception {
		System.out.println("Per la constructora 1 necessitarem Assignatura.");
		PlaEstudis pe = new PlaEstudis("Pla");
		String nom = "prop";
		Assignatura a = new Assignatura(pe, nom);
		NoSolaparAssignatura nsa = new NoSolaparAssignatura(a);
		System.out.println("Correcte.");
	}
	/**
	 * Comprova la primera constructora 
	 * @throws Exception
	 */
	private static void comprovaAddCheck() throws Exception {
		System.out.println("Per la constructora 1 necessitarem Assignatura.");
		PlaEstudis pe = new PlaEstudis("Pla");
		String nom = "prop";
		Assignatura a = new Assignatura(pe, nom);
		String nom1 = "IES";
		Assignatura b = new Assignatura(pe, nom1);
		NoSolaparAssignatura nsa = new NoSolaparAssignatura(a);
		if (nsa.addNoSolapar(b) == 0) System.out.println("addNoSolapar OK");
		else System.out.println("addNoSolapar error");
		if (nsa.checkSolapar(b) == 1) System.out.println("checkSolapar OK");
		else System.out.println("checkSolapar error");
		if (nsa.delNoSolapar(b) == 0) System.out.println("delSolapar OK");
		else System.out.println("checkSolapar error");
		if (nsa.checkSolapar(b) == 0) System.out.println("checkSolapar_Proba2 OK");
		else System.out.println("checkSolapar_Proba2 error");
		System.out.println("Correcte.");
	}
}