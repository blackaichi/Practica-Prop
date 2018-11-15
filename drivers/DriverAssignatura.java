package drivers;

import java.util.*;
import classes.*;
import utils.*;
/**
 * 
 * @author Aleix Lluch Serra
 *
 */
public class DriverAssignatura {
	public static void main (String [] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe Assignatura");
		System.out.print("1-CheckConstructora1, 2-CheckConstructora2, 3-CheckGetSetGrup,  4-CheckModificadoresSubgrup,"
				+ "5-CheckModificadoresGrup");
		System.out.print("Enter an integer: ");
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while(n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6) {
			if (n == 1) CheckConstructora();
			else if (n == 2) CheckGetSetGrup();
			else if (n == 3) CheckModificadoresGrup();
			else if (n == 4) CheckModificadoresSessioG();
			else if (n == 5) CheckModificadoresSessioSG();
		}
		reader.close();
	}
	
	public static void CheckConstructora() throws Exception {
		System.out.println("Per la segona contructora necessitem un objecte PlaEstudis, el nom de l'assignatura");
		PlaEstudis pe = new PlaEstudis("Pla");
		String nom = "prop";
		Assignatura assignatura = new Assignatura(pe, nom);
		System.out.println("Correcte");
	}
	public static void CheckGetSetGrup() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Introdueix el nom de l'assignatura(String)");
		String nom = reader.next();
		a.setNom(pe, nom);
		if (a.getNom().equals(nom)) System.out.println("Get/Set nom OK");
		else System.out.println("Get/Set nom ERROR");
		System.out.println("Introdueix el nom del nou PlaEstudis(String)");
		String nouPe = reader.next();
		pe.setNom(nouPe);
		a.setPlaEstudis(pe);
		if (a.getPlaEstudis().getNom() == nouPe) System.out.println("Get/Set PlaEstudis OK");
		System.out.println("Get/Set PlaEstudis ERROR");
		reader.close();
		
		a.altaGrup(10, 60, "Matí");
		if (a.getGrup(10) != null) System.out.println("Get Grup OK");
		else System.out.println("Get Grup OK");
		a.altaSessioG("teoria", 10);
		if (a.getSessioG("teoria", 10) != null) System.out.println("Get SessioG OK");
		else System.out.println("Get SessioG ERROR");
		a.altaSessioSG("laboratori", 10);
		if (a.getSessioSG("laboratori", 10) != null) System.out.println("Get SessioSG OK");
		else System.out.println("Get SessioSG ERROR");
	}
	public static void CheckModificadoresGrup() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		int size1 = a.quantsGrups();
		a.altaGrup(10, 60, "Matí");
		if (a.checkGrup(10)) System.out.println("checkGrup OK");
		else System.out.println("checkGrup ERROR");
		int size2 = a.quantsGrups();
		a.baixaGrup(10);
		int size3 = a.quantsGrups();
		if (size3 == size1 && size1 + 1 == size2) System.out.println("AltaBaixaGrup OK");
		else System.out.println("AltaBaixaGrup ERROR");
		
	}
	public static void CheckModificadoresSessioG() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		int size1 = a.getSessionsG().size();
		a.altaSessioG("teoria", 10);
		if (a.checkSessioG("teoria", 10)) System.out.println("checkSessioG OK");
		else System.out.println("checkSessioG ERROR");
		int size2 = a.getSessionsG().size();
		a.baixaSessioG("teoria", 10);
		int size3 = a.getSessionsG().size();
		if (size3 == size1 && size2 == size1 + 1) System.out.println("AltaBaixaSessionsG OK");
		else System.out.println("AltaBaixaSessionsG ERROR");
	}
	public static void CheckModificadoresSessioSG() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		int size1 = a.getSessionsSG().size();
		a.altaSessioSG("laboratori", 10);
		if (a.checkSessioSG("laboratori", 10)) System.out.println("checkSessioSG OK");
		else System.out.println("checkSessioSG ERROR");
		int size2 = a.getSessionsSG().size();
		a.baixaSessioSG("laboratori", 10);
		int size3 = a.getSessionsSG().size();
		if (size3 == size1 && size2 == size1 + 1) System.out.println("AltaBaixaSessionsSG OK");
		else System.out.println("AltaBaixaSessionsSG ERROR");
	}
}
