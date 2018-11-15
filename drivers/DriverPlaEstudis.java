package drivers;

import classes.*;

public class DriverPlaEstudis {

	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació de la classe PlaEstudis");
		PlaEstudis PE1 = new PlaEstudis("UAB");
		System.out.println("Ben creada UAB");
		PlaEstudis PE2 = new PlaEstudis("UPC");
		System.out.println("Ben creada UPC");
		PlaEstudis PE3 = new PlaEstudis("eii");
		System.out.println("Ben creada UDA");
		PlaEstudis PE = new PlaEstudis("fib");
		System.out.println("Ben creada fib");
		
		System.out.println("Benvingut a l'eina de comprovació de la classe SessioGrup");
	}
}
