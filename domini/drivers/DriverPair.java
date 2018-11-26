package domini.drivers;

import java.util.*;
import domini.classes.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverPair {
	
	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació de la classe Pair");
		Pair p = new Pair();
		System.out.println("Constructora sense paràmetres OK");
		Pair p2 = new Pair(3,2);
		System.out.println("Constructora amb paràmetres OK");
		if (!p.fnull()) System.out.println("Error a la funció fnull");
		if (!p.snull()) System.out.println("Error a la funció snull");
		if (!p.isNull()) System.out.println("Error a la funció isnull");
		System.out.println("Si no ha sortit cap error tot OK");
	}
}
