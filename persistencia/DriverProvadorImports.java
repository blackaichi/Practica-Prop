package persistencia;

import java.util.*;

import domini.ControladorDomini;
import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverProvadorImports {
	
	private static String path = "/home/blackaichi/Desktop/codificar_classes/export";
	
	private static String error;
	
	private static DadesPlaEstudis plaEstudis = DadesPlaEstudis.getInstancia();
	private static DadesCampus campus = DadesCampus.getInstancia();
	private static DadesAula aula = DadesAula.getInstancia();
	private static DadesAssignatura assignatura = DadesAssignatura.getInstancia();
	private static DadesData data = DadesData.getInstancia();
	private static DadesGrup grup = DadesGrup.getInstancia();
	private static DadesHorari horari = DadesHorari.getInstancia();
	private static DadesHoresAptes horesAptes = DadesHoresAptes.getInstancia();
	private static DadesSessioGrup sessioGrup = DadesSessioGrup.getInstancia();
	private static DadesSessioSubGrup sessioSubGrup = DadesSessioSubGrup.getInstancia();
	private static DadesSolapaments solapaments = DadesSolapaments.getInstancia();
	private static DadesSubGrup subGrup = DadesSubGrup.getInstancia();
	private static Scanner reader = new Scanner(System.in);
	private static ControladorDomini cd = ControladorDomini.getInstance();
	
	public static void main (String [] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació dels Imports");
		System.out.println("1-ImportaPlaEstudis, 2-ImportaCampus, 3-ImportaAula,  4-ImportaAssignatura,");
		System.out.println("5-ImportaGrup, 6-ImportaHorari, 7-ImportaSessioGrup, 8-ImportaSessioSubGrup,");
		System.out.println("9-ImportaSubGrup, 10-ImportaSolapaments, 11-ImportaHoresAptes, 12- ImportaData,");
		System.out.println("13-ImportaSegment");
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
			System.out.println("1-Importa PlaEstudis, 2-ImportaCampus, 3-ImportaAula,  4-ImportaAssignatura,");
			System.out.println("5-ImportaGrup, 6-ImportaHorari, 7-ImportaSessioGrup, 8-ImportaSessioSubGrup,");
			System.out.println("9-ImportaSubGrup, 10-ImportaSolapaments, 11-ImportaHoresAptes, 12- ImportaData,");
			System.out.println("13-ImportaSegment");
			System.out.print("Enter an integer: ");
			n = reader.nextInt();
		}
		reader.close();
	}
	
	private static void provaSegment() {
		
	}

	/**
	 * Comprova l'Import de SubGrup
	 * @throws Exception
	 */
	private static void provaSubGrup() {
		
	}

	/**
	 * Comprova l'Import de Solapaments
	 * @throws Exception
	 */
	private static void provaSolapaments() {
		
	}

	/**
	 * Comprova l'Import de SessioSubGrup
	 * @throws Exception
	 */
	private static void provaSessioSubGrup() {
		
	}

	/**
	 * Comprova l'Import de SessioGrup
	 * @throws Exception
	 */
	private static void provaSessioGrup() {
		
	}

	/**
	 * Comprova l'Import d'HoresAptes
	 * @throws Exception
	 */
	private static void provaHoresAptes() {
		
	}
	
	/**
	 * Comprova l'Import d'Horari
	 * @throws Exception
	 */
	private static void provaHorari() {
		
	}

	/**
	 * Comprova l'Import de Grup
	 * @throws Exception
	 */
	private static void provaGrup() {
		
	}

	/**
	 * Comprova l'Import de Data
	 * @throws Exception
	 */
	private static void provaData() {
		
	}
	
	/**
	 * Comprova l'Import d'Assignatura
	 * @throws Exception
	 */
	private static void provaAssignatura() throws Exception {
		PlaEstudis.newPlaEstudis("campus");
		PlaEstudis c = PlaEstudis.getPlaEstudis("campus");
		DadesAssignatura.getInstancia().importaAssignatura(path, c.getNom(), null);
		System.out.println(c.getAssignatura("prop").getNom());
	}
	
	/**
	 * Comprova l'Import d'Aula
	 * @throws Exception
	 */
	private static void provaAula() {
		
	}
	
	/**
	 * Comprova l'Import de Campus
	 * @throws Exception
	 */
	private static void provaCampus() {
		DadesCampus.getInstancia().importaCampus(path);
		Campus c = Campus.getCampus("campus");
		System.out.println(c.getNom());
		System.out.println(c.getAutor());
		for (Aula a : c.getAllAules()) {
			System.out.println(a.getNom());
			System.out.println(a.getCapacitat());
			System.out.println(a.getEquip().size());			
		}
	}
	
	/**
	 * Comprova l'Import de PlaEstudis
	 * @throws Exception
	 */
	private static void provaPlaEstudis() {
		DadesPlaEstudis.getInstancia().importaPlaEstudis(path);
		PlaEstudis pe = PlaEstudis.getPlaEstudis("pe");
		System.out.println(pe.getNom());
		System.out.println(pe.getAutor());
		System.out.print(pe.getRang());
	}
}
