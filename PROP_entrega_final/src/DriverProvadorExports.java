

import java.util.*;
import domini.ControladorDomini;
import domini.classes.*;
import domini.tools.Estructura;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverProvadorExports {
	
	private static Scanner reader = new Scanner(System.in);
	private static ControladorDomini cd = ControladorDomini.getInstance();
	
	/**
	 * Comprova l'Export que es descomenti
	 * @param args no entra res
	 * @throws Exception
	 */
	public static void main (String [] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació dels Exports");
		System.out.println("1-Exporta PlaEstudis, 2-ExportaCampus, 3-ExportaHorari");			
		System.out.print("Enter an integer: ");
		int n = reader.nextInt();
		while(n == 1 || n == 2 || n == 3) {
			if (n == 1) provaPlaEstudis();
			else if (n == 2) provaCampus();
			else if (n == 3) provaHorari();
			else break;
			System.out.println("1-Exporta PlaEstudis, 2-ExportaCampus, 3-ExportaHorari");			
			System.out.print("Enter an integer: ");
			n = reader.nextInt();
		}
		reader.close();
	}

	/**
	 * Comprova l'Export d'Horari
	 * @throws Exception
	 */
	private static void provaHorari() throws Exception {
		System.out.print("Introdueix el path: ");
		String path = reader.next(); 
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
		cd.exportaHorari(path, c.getNom(), pe.getNom(), 0);
	}
	
	/**
	 * Comprova l'Export de Campus
	 * @throws Exception
	 */
	private static void provaCampus() throws Exception {
		System.out.print("Introdueix el path: ");
		String path = reader.next(); 
		Campus.newCampus("campus");
		Campus c = Campus.getCampus("campus");
		c.setAutor("eric");
		c.altaAula("a5102", 20);
		c.altaAula("a5101", 10);
		cd.exportaCampus(path, "campus");
	}
	
	/**
	 * Comprova l'Export de PlaEstudis
	 * @throws Exception
	 */
	private static void provaPlaEstudis() throws Exception {
		System.out.print("Introdueix el path: ");
		String path = reader.next(); 
		PlaEstudis.newPlaEstudis("pe");
		PlaEstudis pe = PlaEstudis.getPlaEstudis("pe");
		pe.altaAssignatura("PROP");
		pe.altaAssignatura("IES");
		Assignatura a = pe.getAssignatura("PROP");
		Assignatura b = pe.getAssignatura("IES");
		a.altaGrup(10, 20, "M");
		b.altaGrup(20, 10, "T");
		a.altaSessioG("PC", 2);
		cd.exportaPlaEstudis(path, "pe");
		
	}
}
