package persistencia.exports;

import java.util.*;

import domini.ControladorDomini;
import domini.classes.*;
import utils.Estructura;

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
	public static void main (String [] args) throws Exception {
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
			System.out.println("1-Exporta PlaEstudis, 2-ExportaCampus, 3-ExportaAula,  4-ExportaAssignatura,");
			System.out.println("5-ExportaGrup, 6-ExportaHorari, 7-ExportaSessioGrup, 8-ExportaSessioSubGrup,");
			System.out.println("9-ExportaSubGrup, 10-ExportaSolapaments, 11-ExportaHoresAptes, 12- ExportaData,");
			System.out.println("13-ExportaSegment");
			System.out.print("Enter an integer: ");
			n = reader.nextInt();
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
	private static void provaHorari() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis pe = PlaEstudis.getPlaEstudis("fib");
		boolean[] franja = new boolean[24];
		for(int i = 0; i < 24; i++) franja[i] = true;
		pe.setLectiu(0, franja);
		
		pe.altaAssignatura("prop"); 
		pe.getAssignatura("prop").altaSessioG("teo", 2);
		pe.getAssignatura("prop").altaSessioSG("lab", 2);
		
		pe.getAssignatura("prop").altaGrup(20, 30, "MT");
		pe.getAssignatura("prop").altaGrup(10, 25, "MT");
		pe.getAssignatura("prop").getGrup(10).altaSubGrup(11, 5, false);
		
		pe.getAssignatura("prop").getSessioG("teo", 2).assignaSessio(10);
		pe.getAssignatura("prop").getSessioSG("lab", 2).assignaSessio(10, 11);
		pe.getAssignatura("prop").getSessioG("teo", 2).assignaSessio(20);

		Campus.newCampus("campus nord");
		Campus c = Campus.getCampus("campus nord");
		c.altaAula("A1E01", 50);
		c.altaAula("A5001", 100);
		c.altaAula("A4201", 60);
		
		HashSet<String> f = new HashSet<>();
		f.add("D_LECTIU"); f.add("H_LECTIU"); //respectar horari lectiu del pla d'estudis
		f.add("G_HAPTES"); f.add("ASSIG_HAPTES"); //respectar hores aptes
		f.add("G_SOLAP"); f.add("ASSIG_SOLAP"); //respecta solapaments
		f.add("S_ALIGN"); //Alinear sessions
		
		int n =  2;
		Horari.getInstance().GENERADOR(pe, c, f, n, true);
		for(Estructura struct : Horari.getInstance().getHoraris(pe.getNom(), c.getNom()))
			Estructura.printHorari(struct);
		ControladorDomini.getInstance().exportaHorari(path);
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
		System.out.print("Indica el nom de l'aula(String): ");
		String nomA = reader.next();
		System.out.print("Indica la capacitat(int): ");
		int capacitat = reader.nextInt();
		System.out.print("Indica l'equip(String separat per comes sense espais): ");
		String equips = reader.next();
		String[] aux = equips.split(",");
		HashSet<String> equip = new HashSet<String>();
		for (String s : aux) {
			equip.add(s);
		}
		aula.exportaAula(path, nomA, capacitat, equip, true);
	}
	
	/**
	 * Comprova l'Export de Campus
	 * @throws Exception
	 */
	private static void provaCampus() throws Exception {
		Campus.newCampus("campus");
		Campus c = Campus.getCampus("campus");
		c.setAutor("eric");
		c.altaAula("a5102", 20);
		c.altaAula("a5101", 10);
		
		/*System.out.print("Indica el nom del campus(String): ");
		String nomC = reader.next();
		System.out.print("Indica l'autor(String): ");
		String nomA = reader.next();
		System.out.print("Indica les aules(String separat per comes sense espais): ");
		String auless = reader.next();
		String[] aux = auless.split(",");
		HashSet<String> aules = new HashSet<String>();
		for (String s : aux) {
			aules.add(s);
		}*/
		//campus.exportaCampus(path, nomC, nomA, aules);
		ControladorDomini.getInstance().exportaCampus(path, "campus");
	}
	
	/**
	 * Comprova l'Export de PlaEstudis
	 * @throws Exception
	 */
	private static void provaPlaEstudis() throws Exception {
		PlaEstudis.newPlaEstudis("pe");
		PlaEstudis pe = PlaEstudis.getPlaEstudis("pe");
		pe.altaAssignatura("PROP");
		pe.altaAssignatura("IES");
		Assignatura a = pe.getAssignatura("PROP");
		Assignatura b = pe.getAssignatura("IES");
		a.altaGrup(10, 20, "M");
		b.altaGrup(20, 10, "T");
		a.altaSessioG("PC", 2);
		ControladorDomini.getInstance().exportaPlaEstudis(path, "pe");
		
		/*
		System.out.print("Indica el campus(String): ");
		String nomC = reader.next();
		System.out.print("Indica l'autor(String): ");
		String nomA = reader.next();
		System.out.print("Indica les aules(String separat per comes sense espais): ");
		String auless = reader.next();
		plaEstudis.exportaPlaEstudis(path, nom, autor, franja, rang, nomassig)*/
	}
}
