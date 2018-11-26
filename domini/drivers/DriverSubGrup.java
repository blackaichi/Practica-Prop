package domini.drivers;

import java.util.*;
import domini.classes.*;
import utils.*;

/**
 * 
 * @author adria.manero@est.fib.upc.edu
 *
 */
public class DriverSubGrup {
	/**
	 * Funció main del driver DriverGrup, l'administrador introdueix un número 
	 * enter i es comproven les funcions corresponents de la classe.
	 * @param args
	 * @throws Exception
	 */
		public static void main (String [] args) throws Exception {
			System.out.println("Benvingut a l'eina de comprovació de la classe DriverSubGrup");		
			int n = 1;
			Scanner reader = null;
			while(n == 1 || n == 2 || n == 3) {
				System.out.println("1-Constructora1, 2-Constructora2, 3-GetSetGrup");
				System.out.println("Enter an integer: ");
				reader = new Scanner(System.in);
				n = reader.nextInt();
				if (n == 1) CheckConstructora1();
				else if (n == 2) CheckConstructora2();
				else if (n == 3) CheckGetSetGrup();
			}
			reader.close();
		}
		/**
		 * Aquí es comprova la primera constructora.
		 * @throws Exception
		 */
		public static void CheckConstructora1() throws Exception {
			System.out.println("Per la primera contructora necessitem un objecte Grup i el numero de SubGrup");
			PlaEstudis pe = new PlaEstudis("Pla");
			Assignatura a = new Assignatura(pe, "BD");
			Grup g = new Grup(a, 20);
			SubGrup sg = new SubGrup(g,1);
			System.out.println("Correcte");
		}
		/**
		 * Aquí es comprova la segona constructora.
		 * @throws Exception
		 */
		public static void CheckConstructora2() throws Exception {
			System.out.println("Per la segona contructora necessitem un objecte Assignatura, el numero de Grup, les places que té"
					+ "i la franja horaria que ocupa");
			PlaEstudis pe = new PlaEstudis("Pla");
			Assignatura a = new Assignatura(pe, "BD");
			int numero = 10;
			int places = 30;
			String franja = "Matí";
			Grup g = new Grup(a, numero, places, franja);
			SubGrup sg = new SubGrup(g,1,25);
			System.out.println("Correcte");
		}
		/**
		 * Comprova el getters i els setters de Grup.
		 * @throws Exception
		 */
		public static void CheckGetSetGrup() throws Exception {
			PlaEstudis pe = new PlaEstudis("Pla");
			Assignatura a = new Assignatura(pe, "BD");
			Scanner reader = new Scanner(System.in);
			int numero = 0;
			int places = 0;
			String franja = "M";
			Grup g = new Grup(a, numero, places, franja);
			SubGrup sg = new SubGrup(g,1,10);
			System.out.println("Introdueix el numero de Subgrup(Enter)");
			numero = reader.nextInt();
			sg.setNumero(numero);
			if (sg.getNumero() == numero) System.out.println("Get/Set numero OK");
			else System.out.println("Get/Set numero ERROR");
			System.out.println("Introdueix el numero de places(Enter)");
			places = reader.nextInt();
			sg.setPlaces(places,false);
			if (sg.getPlaces() == places) System.out.println("Get/Set places OK");
			else System.out.println("Get/Set places ERROR");
			Grup g1 = new Grup(a, 21, 40, franja);
			System.out.println("comprovació GET/SET grup");
			sg.setGrup(g1);
			if (sg.getGrup().equals(g1)) System.out.println("Get/Set grup OK");
			else System.out.println("Get/Set grup ERROR");
			System.out.println("Comprobacio SetHoresAptes");
			Map<Integer, int[]> f = new HashMap<Integer,int[] >();
			int[] temp = {9,12};
			f.put(0, temp);
			
			
			if (sg.setHoresAptes(f, true, true) == 0) System.out.println("Get/Set HoresAptes OK");
			else System.out.println("Get/Set HoresAptes ERROR");

			SubGrup sg1 = new SubGrup(g1,1,10);
			Grup g2 = new Grup(a, 25, 40, franja);
			
			
			if (sg.setSolapament(null, sg1, true) == 0) System.out.println("Get/Set Solapament SG OK");
			else System.out.println("Get/Set Solapament SG ERROR");
			if (sg.setSolapament(g2, null, true) == 0) System.out.println("Get/Set Solapament G OK");
			else System.out.println("Get/Set Solapament G ERROR");

			reader.close();
			
			sg.assignaSessio("teoria", 10);
			sg.assignaSessio("laboratori", 12);
			if (sg.getSessio("laboratori", 10) != null) System.out.println("Get Sessio OK");
			else System.out.println("Get Sessio ERROR");
			if (sg.getSessions().size() == 2) System.out.println("Get Sessions OK");
			else System.out.println("Get Sessions ERROR");
		}

		/**
		 * Comprova les funcions alta i baixa del Grup.
		 * @throws Exception
		 */
}
