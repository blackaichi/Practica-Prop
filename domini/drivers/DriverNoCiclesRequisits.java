package domini.drivers;

import java.util.*;
import domini.classes.*;
import utils.*;

/**
 * 
 * @author Aleix Lluch Serra
 *
 */
public class DriverNoCiclesRequisits {
	/**
	 * Funció main del DriverNoCiclesRequisits, l'administrador introdueix un número 
	 * enter i es comproven les funcions corresponents de la restriccio
	 * @param args
	 * @throws Exception
	 */
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
	/**
	 * Aquí es comprova la constructora
	 * @throws Exception
	 */
	public static void CheckConstructora() throws Exception {
		System.out.println("Per la contructora necessitem un objecte Assignatura");
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		NoCiclesRequisits ncr = new NoCiclesRequisits(a);
		System.out.println("Correcte");
	}
	/**
	 * Es comproven les funcions públiques de la restriccio DriverNoCiclesRequisits
	 * @throws Exception
	 */
	public static void CheckModificadoresiConsultores() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "prop");
		NoCiclesRequisits ncr = new NoCiclesRequisits(a);
		int size1 = ncr.quantsRequisits();
		ncr.afegeixRequisits(a);
		int size2 = ncr.quantsRequisits();
		ncr.treuRequisit(a);
		int size3 = ncr.quantsRequisits();
		if (size1 == size3 && size2 - 1 == size1) System.out.println("Afegeix/TreuRequisits OK");
		else System.out.println("Afegeix/TreuRequisits ERROR");
		
		Assignatura a1 = new Assignatura(pe, "bd");
		ncr.setAssig(a1);
		if (ncr.getAssignatura() == a1) System.out.println("GetSet Assignatura OK");
		else System.out.println("GetSet Assignatura ERROR");
		
		Assignatura a2 = new Assignatura(pe, "idi");
		Assignatura a3 = new Assignatura(pe, "as");
		Assignatura a4 = new Assignatura(pe, "dbd");
		ncr.afegeixRequisits(a);
		ncr.afegeixRequisits(a1);
		ncr.afegeixRequisits(a2);
		ncr.afegeixRequisits(a3);
		ncr.afegeixRequisits(a4);
		if (ncr.isReachable(a, a4)) System.out.println("IsReachable OK");
		else System.out.println("IsReachable ERROR");
	}
}
