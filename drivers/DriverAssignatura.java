package drivers;

import java.util.*;
import classes.*;
import utils.*;

public class DriverAssignatura {
	public static void main (String [] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe Assignatura");
		System.out.print("1-CheckConstructora1, 2-CheckConstructora2, 3-CheckGetSetGrup,  4-CheckModificadoresSubgrup,"
				+ "5-CheckModificadoresGrup");
		System.out.print("Enter an integer: ");
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		int n = reader.nextInt();
		while(n == 1 || n == 2 || n == 3 || n == 4 || n == 5 || n == 6) {
			if (n == 1) CheckConstructora1();
			else if (n == 2) CheckConstructora2();
			else if (n == 3) CheckGetSetGrup();
			else if (n == 4) CheckModificadoresGrup();
			else if (n == 5) CheckModificadoresSessioG();
			else if (n == 6) CheckModificadoresSessioSG();
		}
		reader.close();
	}
	
	public static void CheckConstructora1() throws Exception {
		System.out.println("Per la primera contructora necessitem un objecte PlaEstudis, el nom de l'assignatura"
				+ " i el numero d'hores de teoria i laboratori");
		PlaEstudis pe = new PlaEstudis("Pla");
		String nom = "prop";
		int hteo = 10;
		int hlab = 10;
		Assignatura a = new Assignatura(pe, nom, hteo, hlab);
		System.out.println("Correcte");
	}
	public static void CheckConstructora2() throws Exception {
		System.out.println("Per la segona contructora necessitem un objecte PlaEstudis, el nom de l'assignatura");
		PlaEstudis pe = new PlaEstudis("Pla");
		String nom = "prop";
		Assignatura a = new Assignatura(pe, nom);
		System.out.println("Correcte");
	}
	public static void CheckGetSetGrup() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop", 10, 10);
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("Introdueix el nom de l'assignatura");
		String nom = reader.next();
		a.setNom(pe, nom);
		if (a.getNom().equals(nom)) System.out.println("Get/Set nom OK");
		else System.out.println("Get/Set nom OK");
		System.out.println("Introdueix el numero d'hores de teoria");
		int hteo = reader.nextInt();
		a.setHTeo(hteo);
		if (a.getHTeo() == hteo) System.out.println("Get/Set hteo OK");
		else System.out.println("Get/Set hteo OK");
		int hlab = reader.nextInt();
		a.setHLab(hlab);
		if (a.getHLab() == hlab) System.out.println("Get/Set hlab OK");
		System.out.println("Get/Set hlab OK");
		System.out.println("Introdueix el nom del nou PlaEstudis");
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
		Assignatura a = new Assignatura(pe, "prop", 10, 10);
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
		Assignatura a = new Assignatura(pe, "prop", 10, 10);
		int size1 = a.quantsSessionsG();
		a.altaSessioG("teoria", 10);
		if (a.checkSessioG("teoria", 10)) System.out.println("checkSessioG OK");
		else System.out.println("checkSessioG ERROR");
		int size2 = a.quantsSessionsG();
		a.baixaSessioG("teoria", 10);
		int size3 = a.quantsSessionsG();
		if (size3 == size1 && size2 == size1 + 1) System.out.println("AltaBaixaSessionsG OK");
		else System.out.println("AltaBaixaSessionsG ERROR");
	}
	public static void CheckModificadoresSessioSG() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop", 10, 10);
		int size1 = a.quantsSessionsSG();
		a.altaSessioSG("laboratori", 10);
		if (a.checkSessioSG("laboratori", 10)) System.out.println("checkSessioSG OK");
		else System.out.println("checkSessioSG ERROR");
		int size2 = a.quantsSessionsSG();
		a.baixaSessioSG("laboratori", 10);
		int size3 = a.quantsSessionsSG();
		if (size3 == size1 && size2 == size1 + 1) System.out.println("AltaBaixaSessionsSG OK");
		else System.out.println("AltaBaixaSessionsSG ERROR");
	}
}
