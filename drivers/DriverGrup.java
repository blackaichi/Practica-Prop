package drivers;

import java.util.*;
import classes.*;


public class DriverGrup {
	/**
	 * Funció main del driver DriverGrup, l'administrador introdueix un número 
	 * enter i es comproven les funcions corresponents de la classe.
	 * @param args
	 * @throws Exception
	 */
		public static void main (String [] args) throws Exception {
			System.out.print("Benvingut a l'eina de comprovació de la classe Grup");
			System.out.print("1-CheckConstructora1, 2-CheckConstructora2, 3-CheckGetSetGrup,  4-CheckModificadoresSubgrup,"
					+ " 5-CheckModificadoresSessio, 6-CheckConsultores");
			System.out.print("Enter an integer: ");
			Scanner reader = new Scanner(System.in);
			int n = reader.nextInt();
			while(n == 1 || n == 2 || n == 3 || n == 4 ||n == 5 ||n == 6) {
				if (n == 1) CheckConstructora1();
				else if (n == 2) CheckConstructora2();
				else if (n == 3) CheckGetSetGrup();
				else if (n == 4) CheckModificadoresSubgrup();
				else if (n == 5) CheckModificadoresSessio();
				else CheckConsultores();
			}
			reader.close();
		}
		/**
		 * Aquí es comprova la primera constructora.
		 * @throws Exception
		 */
		public static void CheckConstructora1() throws Exception {
			System.out.println("Per la primera contructora necessitem un objecte Assignatura i el numero de Grup");
			PlaEstudis pe = new PlaEstudis("Pla");
			Assignatura a = new Assignatura(pe, "BD", 10, 10);
			Grup g = new Grup(a, 20);
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
			Assignatura a = new Assignatura(pe, "BD", 10, 10);
			int numero = 10;
			int places = 30;
			String franja = "Matí";
			Grup g = new Grup(a, numero, places, franja);
			System.out.println("Correcte");
		}
		/**
		 * Comprova el getters i els setters de Grup.
		 * @throws Exception
		 */
		public static void CheckGetSetGrup() throws Exception {
			PlaEstudis pe = new PlaEstudis("Pla");
			Assignatura a = new Assignatura(pe, "BD", 10, 10);
			Scanner reader = new Scanner(System.in);
			int numero = 0;
			int places = 0;
			String franja = "M";
			Grup g = new Grup(a, numero, places, franja);
			System.out.println("Introdueix el numero de grup(Enter)");
			numero = reader.nextInt();
			g.setNumero(numero);
			if (g.getNumero() == numero) System.out.println("Get/Set numero OK");
			else System.out.println("Get/Set numero ERROR");
			System.out.println("Introdueix el numero de places(Enter)");
			places = reader.nextInt();
			g.setPlaces(places);
			if (g.getPlaces() == places) System.out.println("Get/Set places OK");
			else System.out.println("Get/Set places ERROR");
			System.out.println("Introdueix la franja horaria(M,T,MT,NAN)");
			g.setFranja("T");
			if (g.getFranja().equals("Tarda")) System.out.println("Get/Set franja OK");
			else System.out.println("Get/Set franja ERROR");
			System.out.println("Introdueix el nou nom de l'assignatura(String)");
			String nom = reader.next();
			Assignatura a1 = new Assignatura(pe, nom, 10, 10);
			g.setAssignatura(a1);
			if (g.getAssignatura().getNom().equals(nom)) System.out.println("Get/Set Assignatura OK");
			else System.out.println("Get/Set Assignatura ERROR");
			reader.close();
			
			int places1 = 20;
			int places2 = 25;
			int places3 = 22;
			g.altaSubGrup(11, places1, true);
			g.altaSubGrup(12, places2, true);
			g.altaSubGrup(13, places3, true);
			if (g.getSubGrup(11) != null) System.out.println("Get Subgrup OK");
			else System.out.println("Get Subgrup ERROR");
			if (g.getAllSubGrups().size() == 3) System.out.println("Get AllSubGrups OK");
			System.out.println("Get AllSubGrups ERROR");
			if (g.getPlacesAssignades() == (places1 + places2 + places3)) System.out.println("Get PlacesAssignades OK");
			System.out.println("Get PlacesAssignades OK");
			g.assignaSessio("teoria", 10);
			g.assignaSessio("laboratori", 12);
			if (g.getSessio("laboratori", 10) != null) System.out.println("Get Sessio OK");
			else System.out.println("Get Sessio ERROR");
			if (g.getSessions().size() == 2) System.out.println("Get Sessions OK");
			else System.out.println("Get Sessions ERROR");
		}
		/**
		 * Comprova les funcions alta i baixa del Subgrup.
		 * @throws Exception
		 */
		public static void CheckModificadoresSubgrup() throws Exception {
			PlaEstudis pe = new PlaEstudis("Pla");
			Assignatura a = new Assignatura(pe, "BD", 10, 10);
			Grup g = new Grup(a, 20);
			int size1 = g.quantsSubGrups();
			g.altaSubGrup(11, 20, true);
			int size2 = g.quantsSubGrups();
			g.baixaSubGrup(11);
			int size3 = g.quantsSubGrups();
			if (size1 == size3 && size2-1 == size1) System.out.println("AddDelSubGrup i quantsSubGrups OK");
			else System.out.println("AddDelSubGrup i quantsSubGrups ERROR");
			int places1 = g.getPlacesAssignades();
			g.obrirPlaces(1);
			g.tancarPlaces(1);
			int places2 = g.getPlacesAssignades();
			if (places1 == places2) System.out.println("Obrir/TancarPlaces OK");
			else System.out.println("Obrir/TancarPlaces ERROR");
		}
		/**
		 * Comprova les funcions alta i baixa del Grup.
		 * @throws Exception
		 */
		public static void CheckModificadoresSessio() throws Exception {
			PlaEstudis pe = new PlaEstudis("Pla");
			Assignatura a = new Assignatura(pe, "BD", 10, 10);
			Grup g = new Grup(a, 20);
			g.assignaSessio("teoria", 10);
			SessioGAssignada sgat = g.getSessio("teoria", 10);
			int size1 = g.quantesSessions();
			g.eliminaSessio(sgat);
			if (g.checkSessio("teoria", 10)) System.out.println("CheckSessio OK");
			else System.out.println("CheckSessio ERROR");
			int size2 = g.quantesSessions();
			g.afegeixSessio(sgat);
			int size3 = g.quantesSessions();
			g.desassignaSessio(sgat);
			int size4 = g.quantesSessions();
			if (size3 == size1 && size1 - 1 == size2 && size2 == size4) System.out.println("AddDelSessio i quantesSessions OK");
			else System.out.println("AddDelSessio i quantesSessions ERROR");
		}
		/**
		 * Comprova les consultores de Sessio.
		 * @throws Exception
		 */
		public static void CheckConsultores() throws Exception {
			PlaEstudis pe = new PlaEstudis("Pla");
			Assignatura a = new Assignatura(pe, "BD", 10, 10);
			Grup g = new Grup(a, 20);
			g.assignaSessio("teoria", 10);
			if (g.checkSessio("teoria", 10)) System.out.println("CheckSessio OK");
			else System.out.println("CheckSessio ERROR");
			if (g.checkSessio("teoria")) System.out.println("CheckSessio OK");
			else System.out.println("CheckSessio ERROR");
			g.altaSubGrup(11, 20, true);
			if(g.checkSubGrup(11)) System.out.println("CheckSubGrup sessio OK");
			else System.out.println("CheckSubGrup sessio ERROR");

		}
}
