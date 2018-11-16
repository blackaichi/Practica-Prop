package drivers;

import java.util.*;
import classes.*;
import restriccions.*;
/**
 * 
 * @author Aleix LLuch Serra
 *
 */
public class DriverHoresAptesGrupSubGrup {
	
	/**
	 * Funció main del DriverHoresAptesGrupSubGrup, l'administrador introdueix un número 
	 * enter i es comproven les funcions corresponents de la restriccio.
	 * @param args
	 * @throws Exception
	 */
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
	/**
	 * Aquí es comprova la constructora.
	 * @throws Exception
	 */
	public static void CheckConstructora() throws Exception {
		System.out.println("Per la contructora necessitem un objecte Grup i un objecte SubGrup");
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		Grup g = new Grup(a, 10);
		SubGrup sg = new SubGrup(g, 12);
		HoresAptesGrupSubGrup hags = new HoresAptesGrupSubGrup(g, sg);
		System.out.println("Correcte");
	}
	/**
	 * Comprova les funcions públiques de la restricció.
	 * @throws Exception
	 */
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
		Map<Integer, boolean[]> horesaptes = hags.getHoresAptes();
		Map<Integer, boolean[]> horesa = hags.getMascara();
		boolean iguals = horesa.equals(horesaptes);
		
		if (iguals) System.out.println("Restore, getHoresAptes i getMascara OK");
		else System.out.println("Restore, getHoresAptes i getMascara ERROR");;
	}

}

