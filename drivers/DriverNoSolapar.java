package drivers;

import java.util.*;
import classes.*;
import restriccions.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverNoSolapar {
	public static void main (String [ ] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe SessioGrup");
		System.out.println("1- Constructora1, 2- Constructora2, 3- Constructora3, 4- Setters/Getters, 5- Add/delMaterial, else- Stop");
		System.out.print("Enter an integer: ");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while (n == 1 || n == 2 || n == 3 || n == 4 || n == 5) {
			if (n == 1) {
				PlaEstudis p = new PlaEstudis("fib");
				Assignatura a = new Assignatura(p, "prop", 2, 2);
				
			}
			if (n == 2)	;
			if (n == 3) ;
			if (n == 4) ;
			if (n == 5) ;
		}
		reader.close();
	}
}
