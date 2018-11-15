package drivers;

import java.util.*;
import classes.*;
import restriccions.*;

public class DriverHoresAptesGrupSubGrup {
	
	public static void main (String [] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la restriccio HoresSenseClasseAssignatura");
		System.out.print("1-CheckConstructora, 2-CheckModificadoresiConsultores");
		System.out.print("Enter an integer: ");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while (n == 1 || n == 2 || n == 3) {
			if (n == 1) CheckConstructora();
			else CheckModificadoresiConsultores();
		}
		reader.close();
	}
	
	public static void CheckConstructora() throws Exception {
		System.out.println("Per la contructora necessitem un objecte Grup i un objecte SubGrup");
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		Grup g = new Grup(a, 10);
		SubGrup sg = new SubGrup(g, 12);
		HoresAptesGrupSubGrup hags = new HoresAptesGrupSubGrup(g, sg);
		System.out.println("Correcte");
	}
	
	public static void CheckModificadoresiConsultores() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		Grup g = new Grup(a, 10);
		SubGrup sg = new SubGrup(g, 12);
		HoresAptesGrupSubGrup hags = new HoresAptesGrupSubGrup(g, sg);
		Boolean force = false;
		int dia = 5;
		int hores = 4;
		hags.prohibirHores(force, dia, hores);
		if (hags.checkPotFerClasse(5,4) == 1) System.out.println("prohibirHores i checkPotFerClasse OK");
		else System.out.println("prohibirHores i checkPotFerClasse ERROR");
		hags.permetHores(force, dia, hores);
		if (hags.checkPotFerClasse(5, 4) == 0) System.out.println("permetHores i checkPotFerClasse OK");
		
		hags.restore();
		boolean[][] horesaptes = hags.getHoresAptes();
		boolean[][] horesa = hags.getMascara();
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
