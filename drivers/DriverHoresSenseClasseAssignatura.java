package drivers;

import java.util.*;
import classes.*;
import restriccions.*;

public class DriverHoresSenseClasseAssignatura {
	public static void main (String [] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la restriccio HoresSenseClasseAssignatura");
		System.out.print("1-CheckConstructora, 2-CheckModificadoresiConsultores");
		System.out.print("Enter an integer: ");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while (n == 1 || n == 2) {
			if (n == 1) CheckConstructora();
			else CheckModificadoresiConsultores();
		}
		reader.close();
	}
	
	public static void CheckConstructora() throws Exception {
		System.out.println("Per la contructora necessitem un objecte Assignatura");
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		HoresSenseClasseAssignatura hsca = new HoresSenseClasseAssignatura(a);
		System.out.println("Correcte");
	}
	
	public static void CheckModificadoresiConsultores() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		HoresSenseClasseAssignatura hsca = new HoresSenseClasseAssignatura(a);
		Boolean force = false;
		int dia = 5;
		int hores = 4;
		hsca.prohibirHores(force, dia, hores);
		if (hsca.checkPotFerClasse(5,4) == 1) System.out.println("prohibirHores i checkPotFerClasse OK");
		else System.out.println("prohibirHores i checkPotFerClasse ERROR");
		hsca.permetHores(force, dia, hores);
		if (hsca.checkPotFerClasse(5, 4) == 0) System.out.println("permetHores i checkPotFerClasse OK");
		
		hsca.restore();
		boolean[][] horesaptes = hsca.getHoresAptes();
		boolean[][] horesa = hsca.getMascara();
		boolean trobat = false;
		for (int i=0; i < horesaptes.length; ++i) {
			for (int j = 0; j < horesaptes.length; ++j) {
				if (horesaptes[i][j] != horesa[i][j]) trobat = true;
			}
		}
		if (trobat) System.out.println("Restore, getHoresAptes i getMascara ERROR");
		else System.out.println("Restore, getHoresAptes i getMascara OK");
	}
}
