package drivers;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverSessioGrup {
	
	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació de la classe SessioGrup");
		int n = 1;
		Scanner reader = null;
		while (n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6) {
			System.out.println("1- Constructora1, 2- Constructora2, 3- Constructora3, 4- Setters/Getters, 5- Add/delMaterial, 6- Afegir/EliminaSessio, else- Stop");
			System.out.print("Enter an integer: ");
			reader = new Scanner(System.in);
			n = reader.nextInt();
			if (n == 1) checkConstructora1();
			else if (n == 2) checkConstructora2();
			else if (n == 3) checkConstructora3();
			else if (n == 4) checkSetGet();
			else if (n == 5) checkAddDel();
			else if (n == 6) checkAddDelSessio();
		}
		reader.close();
	}
	

	/**
	 * Comprova la primera constructora 
	 * @throws Exception
	 */
	private static void checkConstructora1() throws Exception {
		System.out.println("Per la constructora 1 necessitarem Assignatura i tipus de sessió.");
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		String t = "problemes";
		SessioGrup s = new SessioGrup(a, t);
		System.out.println("Correcte.");
	}
	
	/**
	 * Comprova la segona constructora
	 * @throws Exception
	 */
	private static void checkConstructora2() throws Exception {
		System.out.println("Per la constructora 2 necessitarem Assignatura, hores," + 
				   		   "tipus de sessió i nombre de sessions.");
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		String t = "problemes";
		int h = 2;
		int ns = 1;
		SessioGrup s = new SessioGrup(a, h, t, ns);
		System.out.println("Correcte.");
	}
	
	/**
	 * Comprova la tercera constructora
	 * @throws Exception
	 */
	private static void checkConstructora3() throws Exception {
		System.out.println("Per la constructora 2 necessitarem Assignatura, hores i" + 
				   "tipus de sessió.");
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		String t = "problemes";
		int h = 2;
		SessioGrup s = new SessioGrup(a, h, t);
		System.out.println("Correcte.");
	}
	
	/**
	 * Comprova setters i getters
	 * @throws Exception
	 */
	private static void checkSetGet() throws Exception {
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		Assignatura b = new Assignatura(p, "IES");
		SessioGrup s = new SessioGrup(b, "null");
		HashSet<String> material = new HashSet<String>();
		material.add("PCs");
		System.out.println("Pels getters he creat una SessioGrup amb Assignatura identificada per nom->prop" +
						   " els altres paràmetres els definirà l'usuari.");
		System.out.print("Introdueixi les hores(int): ");
		Scanner reader = new Scanner(System.in);
		int h = reader.nextInt();
		System.out.print("Introdueixi el tipus(String): ");
		String t = reader.next();
		System.out.print("Introdueixi el nombre de sessions(int): ");
		int ns = reader.nextInt();
		s.setAssignatura(a);
		if (s.getAssignatura().getNom().equals("prop")) System.out.println("get/setAssignatura OK");
		else System.out.println("get/setAssignatura error");
		s.setHores(h);
		if (s.getHores() == h) System.out.println("get/setHores OK");
		else System.out.println("get/setHores error");
		s.setTipus(t);
		if (s.getTipus().equals(t)) System.out.println("get/setTipus OK");
		else System.out.println("get/setTipus error");
		s.setnsessions(ns);
		if (s.getnsessions() == ns) System.out.println("get/setnsessions OK");
		else System.out.println("get/setnsessions error");
		s.setMaterial(material);
		if(!(s.getMaterial() == null || material == null) && (s.getMaterial().size() == material.size()) &&
			s.getMaterial().containsAll(material)) System.out.println("get/setMaterial OK");
		else System.out.println("get/setMaterial error");
	}
	
	/**
	 * comprova les funcions de add i del del HashSet
	 * @throws Exception
	 */
	private static void checkAddDel() throws Exception {
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		SessioGrup s = new SessioGrup(a, "problemes");
		Scanner reader = new Scanner(System.in);
		System.out.print("Nom del 1r material(String): ");
		String m = reader.next();
		System.out.print("Nom del 2n material(String): ");
		String n = reader.next();
		int m1 = s.getMaterial().size();
		System.out.println("Size material abans afegir el 1r: " + String.valueOf(m1));
		s.addMaterial(m);
		int n1 = s.getMaterial().size();
		System.out.println("Size material abans afegir el 2n: " + String.valueOf(n1));
		s.addMaterial(n);
		int n2 = s.getMaterial().size();
		System.out.println("Size material despres d'haver afegit tot: " + String.valueOf(n2));
		s.delMaterial(n);
		int m2 = s.getMaterial().size();
		System.out.println("Size material després d'eliminar el primer: " + String.valueOf(m2));
		s.delMaterial(m);
		int m3 = s.getMaterial().size();
		System.out.println("Size material després d'eliminar tot: " + String.valueOf(m3));
		if (m3 == m1 && m2 == m1+1) System.out.println("Add/delMaterial OK");
		else System.out.println("Add/delMaterial error");
	}
	
	/**
	 * Comprova les funcions afegir i elimina sessió
	 * @throws Exception 
	 */
	private static void checkAddDelSessio() throws Exception {
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		SessioGrup s = new SessioGrup(a, "problemes");
		Grup g = new Grup(a, 10);
		SessioGAssignada sGA = new SessioGAssignada(g, s);
		if (s.afegirSessio(sGA) == 0) System.out.println("Correcte afegir");
		if (s.eliminarSessio(sGA) == 0) System.out.println("Correcte eliminar");
	}
}
