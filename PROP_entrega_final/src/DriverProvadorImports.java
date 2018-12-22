

import java.util.*;

import domini.ControladorDomini;
import domini.classes.*;
import domini.tools.Estructura;
import persistencia.DadesCampus;
import persistencia.DadesPlaEstudis;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverProvadorImports {

	private static Scanner reader = new Scanner(System.in);
	private static ControladorDomini cd = ControladorDomini.getInstance();
	
	public static void main (String [] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació dels Imports");
		System.out.println("1-Importa PlaEstudis, 2-ImportaCampus, 3-ImportaHorari");			
		System.out.print("Enter an integer: ");
		int n = reader.nextInt();
		while(n == 1 || n == 2 || n == 3) {
			if (n == 1) provaPlaEstudis();
			else if (n == 2) provaCampus();
			else if (n == 3) provaHorari();
			else break;
			System.out.println("1-Importa PlaEstudis, 2-ImportaCampus, 3-ImportaHorari");			
			System.out.print("Enter an integer: ");
			n = reader.nextInt();
		}
		reader.close();
	}
	
	/**
	 * Comprova l'Import d'Horari
	 * @throws Exception
	 */
	private static void provaHorari() {
		System.out.print("Introdueix el path: ");
		String path = reader.next(); 
		cd.importaHorari(path);
		Campus c = Campus.getCampus("campus nord");
		PlaEstudis pe = PlaEstudis.getPlaEstudis("fib");
		if (c != null) 	System.out.println("Campus Correcte");
		if (pe != null) System.out.println("PlaEstudis Correcte");
		System.out.println("Comprobació Assignacions Horari");
		HashSet<Estructura> h = Horari.getInstance().getHoraris(pe.getNom(), c.getNom());
		if (h != null) System.out.println("El horari té el pla d'estudis i el campus assignats");
		for(Estructura e : h) {
			System.out.print("************* HORARI *****************");
			Estructura.printHorari(e);
			System.out.print("///////////// END HORARI ///////////////");
		}
	}
	
	/**
	 * Comprova l'Import de Campus
	 * @throws Exception
	 */
	private static void provaCampus() {
		System.out.print("Introdueix el path: ");
		String path = reader.next(); 
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
		System.out.print("Introdueix el path: ");
		String path = reader.next(); 
		DadesPlaEstudis.getInstancia().importaPlaEstudis(path);
		PlaEstudis pe = PlaEstudis.getPlaEstudis("pe");
		System.out.println(pe.getNom());
		System.out.println(pe.getAutor());
		System.out.print(pe.getRang());
	}
}
