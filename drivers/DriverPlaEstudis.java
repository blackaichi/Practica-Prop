package drivers;
import java.util.*;

import classes.Assignatura;
import classes.PlaEstudis;
import classes.SessioGrup;
	/**
	 * 
	 * @author adria.manero@est.fib.upc.edu
	 *
	 */
public class DriverPlaEstudis {
	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe SessioGrup");
		System.out.println("1- Constructora1, 2- Constructora2, 3- Constructora3, 4- Setters/Getters, 5- Add/delMaterial, else- Stop");
		System.out.print("Enter an integer: ");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while (n == 1 || n == 2 || n == 3 || n == 4 || n == 5) {
			if (n == 1) checkConstructora1();
			if (n == 2) checkSetGet();
			if (n == 3) checkAddDel();
		}
		reader.close();
	}
	
	/**
	 * Comprova la primera constructora 
	 * @throws Exception
	 */
	private static void checkConstructora1() throws Exception {
		System.out.println("Per la constructora 1 necessitarem el nom del Pla d'Estudis.");
		PlaEstudis p = new PlaEstudis("fib");
		System.out.print("Correcte.");
	}
	
	/**
	 * Comprova setters i getters
	 * @throws Exception
	 */
	private static void checkSetGet() throws Exception {
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p,"PROP",3,2);
		HashSet<Assignatura> assigs = new HashSet<Assignatura>();
		assigs.add(a);
		System.out.println("Introdueixi les hores: ");
		Scanner reader = new Scanner(System.in);
		int h = reader.nextInt();
		System.out.println("Introdueixi el tipus: ");
		String t = reader.next();
		System.out.println("Introdueixi el nombre de sessions: ");
		int ns = reader.nextInt();
		s.setAssignatura(a);
		if (p.getNom().equals("prop")) System.out.println("get/setAssignatura OK");
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
}
