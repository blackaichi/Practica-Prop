package drivers;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverSessioSGAssignada {
	
	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe SessioSGAssignada");
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
		SubGrup sg = new SubGrup(g, 11);
		String t = "problemes";
		SessioSubGrup s = new SessioSubGrup(a, t);
		SessioSGAssignada sSGA = new SessioSGAssignada(sg, s);
		System.out.println("Correcte.");
	}

	private static void checkSetGet() throws Exception {
		System.out.println("Per la constructora necessitarem un Grup i una SessioGrup.");
		PlaEstudis p = new PlaEstudis("fib");
		Assignatura a = new Assignatura(p, "prop");
		Assignatura b = new Assignatura(p, "IES");
		Grup g = new Grup(a, 10);
		Grup g2 = new Grup(b, 20);
		SubGrup sg = new SubGrup(g, 11);
		SubGrup sg2 = new SubGrup (g2, 21);
		SessioSubGrup s = new SessioSubGrup(a, "problemes");
		SessioSubGrup s2 = new SessioSubGrup(b, "PCs");
		SessioSGAssignada sSGA = new SessioSGAssignada(sg,s);
		sSGA.setSubGrup(sg2);
		if (sSGA.getSubGrup().getNumero() == sg2.getNumero() && sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(sg2.getGrup().getAssignatura().getNom())) 
			System.out.println("get/setSubGrup OK");
		else System.out.println("get/setSubGrup error");
		sSGA.setSessioSubGrup(s2);
		if (sSGA.getSessioSubGrup().getAssignatura().getNom().equals(sg2.getGrup().getAssignatura().getNom()))
			System.out.println("get/setSessioSubGrup OK");
		else System.out.println("get/setSessioSubGrup error");
	}
}
