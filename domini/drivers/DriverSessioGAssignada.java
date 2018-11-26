package domini.drivers;

import java.util.*;
import domini.classes.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverSessioGAssignada {
	
	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe SessioGAssignada");
		Scanner reader = null;
		int n = 1;
		while (n == 1 || n == 2) {
			System.out.println("1- Constructora, 2- Setters/Getters,  else- Stop");
			System.out.print("Enter an integer: ");
			reader = new Scanner(System.in);
			n = reader.nextInt();
			if (n == 1) checkConstructora();
			if (n == 2)	checkSetGet();
		}
		reader.close();
	}

	/**
	 * Comprova la constructora
	 * @throws Exception
	 */
	private static void checkConstructora() throws Exception {
		System.out.println("Per la constructora necessitarem un Grup i una SessioGrup.");
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		Grup g = new Grup(a, 10);
		String t = "problemes";
		SessioGrup s = new SessioGrup(a, t);
		SessioGAssignada sGA = new SessioGAssignada(g,s);
		System.out.println("Correcte.");
	}

	/**
	 * Comprova setters i getters 
	 * @throws Exception
	 */
	private static void checkSetGet() throws Exception {
		System.out.println("Per la constructora necessitarem un Grup i una SessioGrup.");
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		Assignatura b = new Assignatura(p, "IES");
		Grup g = new Grup(a, 10);
		Grup g2 = new Grup (b, 20);
		SessioGrup s = new SessioGrup(a, "problemes");
		SessioGrup s2 = new SessioGrup(b, "PCs");
		SessioGAssignada sGA = new SessioGAssignada(g,s);
		sGA.setGrup(g2);
		if (sGA.getGrup().getNumero() == g2.getNumero() && sGA.getGrup().getAssignatura().getNom().equals(g2.getAssignatura().getNom())) 
			System.out.println("get/setGrup OK");
		else System.out.println("get/setGrup error");
		sGA.setSessioGrup(s2);
		if (sGA.getSessioGrup().getAssignatura().getNom().equals(s2.getAssignatura().getNom()) && sGA.getSessioGrup().getTipus().equals(s2.getTipus()))
			System.out.println("get/setSessioGrup OK");
		else System.out.println("get/setSessioGrup error");
	}
}
