package drivers;

import java.util.*;
import classes.*;

public class DriverData {
	
	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.print("Benvingut a l'eina de comprovació de la classe SessioGrup");
		int n = 1;
		Scanner reader = null;
		while (n == 1 || n == 2) {
			System.out.println("1- Constructora1, 2- Setters/Getters, else- Stop");
			System.out.print("Enter an integer: ");
			reader = new Scanner(System.in);
			n = reader.nextInt();
			if (n == 1) checkConstructora();
			if (n == 2) checkSetGet();
		}
		reader.close();
	}
	
	/**
	 * Comprova la primera constructora
	 * @throws Exception
	 */
	private static void checkConstructora() throws Exception {
		System.out.println("Per la constructora només necessitarem inicialitzar una data.");
		Data data = new Data(3, 20);
		System.out.println("Correcte.");
	}
	
	/**
	 * Comprova setters i getters
	 * @throws Exception
	 */
	private static void checkSetGet() throws Exception {
		Data data = new Data(3, 20);
		System.out.println("Pels getters he creat una data, els paràmetres els definirà l'usuari.");
		System.out.print("Introdueixi el dia(int): ");
		Scanner reader = new Scanner(System.in);
		int dia = reader.nextInt();
		System.out.print("Introdueixi les hores(int): ");
		int hores = reader.nextInt();
		data.setDia(dia);
		if (data.getDia() == dia) System.out.println("get/setDia OK");
		else System.out.println("get/setDia error");
		data.setHora(hores);
		if (data.getHora() == hores) System.out.println("get/setHora OK");
		else System.out.println("get/setHora error");
	}
}
