package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverProvadorExports {
	
	private static String path = "/home/blackaichi/Desktop/codificar_classes/export";
	
	/**
	 * Totes les instancies dels exports
	 */
	private static ExportaPlaEstudis plaEstudis = ExportaPlaEstudis.getInstancia();
	private static ExportaCampus campus = ExportaCampus.getInstancia();
	private static ExportaAula aula = ExportaAula.getInstancia();
	private static ExportaAssignatura assignatura = ExportaAssignatura.getInstancia();
	private static ExportaData data = ExportaData.getInstancia();
	private static ExportaGrup grup = ExportaGrup.getInstancia();
	private static ExportaHorari horari = ExportaHorari.getInstancia();
	private static ExportaHoresAptes horesAptes = ExportaHoresAptes.getInstancia();
	private static ExportaSessioGrup sessioGrup = ExportaSessioGrup.getInstancia();
	private static ExportaSessioSubGrup sessioSubGrup = ExportaSessioSubGrup.getInstancia();
	private static ExportaSolapaments solapaments = ExportaSolapaments.getInstancia();
	private static ExportaSubGrup subGrup = ExportaSubGrup.getInstancia();
	private static Scanner reader = new Scanner(System.in);
	
	/**
	 * Comprova l'Export que es descomenti
	 * @param args no entra res
	 * @throws Exception
	 */
	public static void main (String [] args) {
		System.out.println("Benvingut a l'eina de comprovació dels Exports");
		System.out.println("1-Exporta PlaEstudis, 2-ExportaCampus, 3-ExportaAula,  4-ExportaAssignatura,");
		System.out.println("5-ExportaGrup, 6-ExportaHorari, 7-ExportaSessioGrup, 8-ExportaSessioSubGrup,");
		System.out.println("9-ExportaSubGrup, 10-ExportaSolapaments, 11-ExportaHoresAptes, 12- ExportaData,");
		System.out.println("13-ExportaSegment");
		System.out.print("Enter an integer: ");
		int n = reader.nextInt();
		while(n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6 || n == 7 ||
				n == 8 || n == 9 || n == 10 || n == 11 || n == 12 || n == 13) {
			if (n == 1) provaPlaEstudis();
			else if (n == 2) provaCampus();
			else if (n == 3) provaAula();
			else if (n == 4) provaAssignatura();
			else if (n == 5) provaGrup();
			else if (n == 6) provaHorari();
			else if (n == 7) provaSessioGrup();
			else if (n == 8) provaSessioSubGrup();
			else if (n == 9) provaSubGrup();
			else if (n == 10) provaSolapaments();
			else if (n == 11) provaHoresAptes();
			else if (n == 12) provaData();
			else if (n == 13) provaSegment();
			else break;
		}
		reader.close();
	}
	
	private static void provaSegment() {
		
		
	}

	/**
	 * Comprova l'Export de SubGrup
	 * @throws Exception
	 */
	private static void provaSubGrup() {
		
	}

	/**
	 * Comprova l'Export de Solapaments
	 * @throws Exception
	 */
	private static void provaSolapaments() {
		
	}

	/**
	 * Comprova l'Export de SessioSubGrup
	 * @throws Exception
	 */
	private static void provaSessioSubGrup() {
		
	}

	/**
	 * Comprova l'Export de SessioGrup
	 * @throws Exception
	 */
	private static void provaSessioGrup() {
		
	}

	/**
	 * Comprova l'Export d'HoresAptes
	 * @throws Exception
	 */
	private static void provaHoresAptes() {
		
	}
	
	/**
	 * Comprova l'Export d'Horari
	 * @throws Exception
	 */
	private static void provaHorari() {
		
	}

	/**
	 * Comprova l'Export de Grup
	 * @throws Exception
	 */
	private static void provaGrup() {
		
	}

	/**
	 * Comprova l'Export de Data
	 * @throws Exception
	 */
	private static void provaData() {
		
	}
	
	/**
	 * Comprova l'Export d'Assignatura
	 * @throws Exception
	 */
	private static void provaAssignatura() {
		
	}
	
	/**
	 * Comprova l'Export d'Aula
	 * @throws Exception
	 */
	private static void provaAula() {
		HashSet<String> equip = new HashSet<String>();
		equip.add("PCs");
		equip.add("projector");
		aula.exportaAula(path, "A5102", 20, equip, true);
	}
	
	/**
	 * Comprova l'Export de Campus
	 * @throws Exception
	 */
	private static void provaCampus() {
		HashSet<String> aules = new HashSet<String>();
		String s = reader.next();
		aules.add("a5102");
		aules.add("a6102");
		campus.exportaCampus(path, "campus nord", "eric", aules);
	}
	
	/**
	 * Comprova l'Export de PlaEstudis
	 * @throws Exception
	 */
	private static void provaPlaEstudis() {
		plaEstudis.exportaPlaEstudis(path, nom, autor, franja, rang, nomassig)
	}
}
