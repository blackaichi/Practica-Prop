package drivers;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverSessioSubGrup {

	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe SessioSubGrup");
		System.out.println("1- Constructora1, 2- Constructora2, 3- Constructora3, 4- Setters/Getters, 5- Add/delMaterial, else- Stop");
		System.out.print("Enter an integer: ");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while (n == 1 || n == 2 || n == 3 || n == 4 || n == 5) {
			if (n == 1) checkConstructora1();
			if (n == 2)	checkConstructora2();
			if (n == 3) checkConstructora3();
			if (n == 4) checkSetGet();
			if (n == 5) checkAddDel();
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
		Assignatura a = new Assignatura(p, "prop", 2, 2);
		String t = "problemes";
		SessioSubGrup s = new SessioSubGrup(a, t);
		System.out.print("Correcte.");
	}
	
	/**
	 * Comprova la segona constructora
	 * @throws Exception
	 */
	private static void checkConstructora2() throws Exception {
		System.out.println("Per la constructora 2 necessitarem Assignatura, hores," + 
				   		   "tipus de sessió i nombre de sessions.");
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop", 2, 2);
		String t = "problemes";
		int h = 2;
		int ns = 1;
		SessioSubGrup s = new SessioSubGrup(a, h, t, ns);
		System.out.print("Correcte.");
	}
	
	/**
	 * Comprova la tercera constructora
	 * @throws Exception
	 */
	private static void checkConstructora3() throws Exception {
		System.out.println("Per la constructora 2 necessitarem Assignatura, hores i" + 
				   "tipus de sessió.");
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop", 2, 2);
		String t = "problemes";
		int h = 2;
		SessioSubGrup s = new SessioSubGrup(a, h, t);
		System.out.print("Correcte.");
	}
	
	/**
	 * COmprova setters i getters
	 * @throws Exception
	 */
	private static void checkSetGet() throws Exception {
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop", 2, 2);
		Assignatura b = new Assignatura(p, "IES", 2, 2);
		SessioSubGrup s = new SessioSubGrup(b, "null");
		HashSet<String> material = new HashSet<String>();
		material.add("PCs");
		System.out.println("Pels getters he creat una SessioGrup amb Assignatura identificada per nom->prop" +
						   " els altres paràmetres els definirà l'usuari.");
		System.out.println("Introdueixi les hores: ");
		Scanner reader = new Scanner(System.in);
		int h = reader.nextInt();
		System.out.println("Introdueixi el tipus: ");
		String t = reader.next();
		System.out.println("Introdueixi el nombre de sessions: ");
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
		Assignatura a = new Assignatura(p, "prop", 2, 2);
		SessioSubGrup s = new SessioSubGrup(a, "problemes");
		Scanner reader = new Scanner(System.in);
		String m = reader.next();
		int m1 = s.getMaterial().size();
		s.addMaterial(m);
		int m2 = s.getMaterial().size();
		s.delMaterial(m);
		int m3 = s.getMaterial().size();
		if (m3 == m1 && m2 == m1+1) System.out.println("Add/delMaterial OK");
		else System.out.println("Add/delMaterial error");
	}
}
