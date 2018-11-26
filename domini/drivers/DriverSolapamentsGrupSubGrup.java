package domini.drivers;

import java.util.*;
import domini.classes.*;
import utils.*;

/**
 * 
 * @author Aleix Lluch Serra
 *
 */
public class DriverSolapamentsGrupSubGrup {
	/**
	 * Funció main del DriverSolapamentsGrupSubGrup, l'administrador introdueix un número 
	 * enter i es comproven les funcions corresponents de la restriccio
	 * @param args
	 * @throws Exception
	 */
	public static void main (String [] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la restriccio DriverSolapamentsGrupSubGrup");
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
	 * Aquí comprovem la constructora
	 * @throws Exception
	 */
	public static void CheckConstructora() throws Exception {
		System.out.println("Per la contructora necessitem un objecte Grup i un objecte SubGrup");
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "BD");
		Grup g = new Grup(a, 20);
		SubGrup sg= new SubGrup(g, 22);
		SolapamentsGrupSubGrup sgs = new SolapamentsGrupSubGrup(g, sg);
		System.out.println("Correcte");
	}
	/**
	 * Aqui comprovem la modificadora i la consultora de DriverSolapamentsGrupSubGrup
	 * @throws Exception
	 */
	public static void CheckModificadoresiConsultores() throws Exception {
		PlaEstudis pe = new PlaEstudis("Pla");
		Assignatura a = new Assignatura(pe, "BD");
		Grup g = new Grup(a, 20);
		SubGrup sg= new SubGrup(g, 22);
		SolapamentsGrupSubGrup sgs = new SolapamentsGrupSubGrup(g, sg);
		if (sgs.setSolapament(g, sg, false) == 0) System.out.println("setSolapamet OK");
		else System.out.println("setSolapamet ERROR");
		if (sgs.checkPotSolapar(g, sg) == 0) System.out.println("checkPotSolapar OK");
		else System.out.println("checkPotSolapar ERROR");
	}
}
