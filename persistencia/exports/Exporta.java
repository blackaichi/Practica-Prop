package persistencia.exports;

import java.io.*;
import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class Exporta {
	
	/**
	 * Exporta a un fitxer el contingut de l'String s
	 * @param s el contingut que volem ficar al fitxer
	 * @return null en cas de cap error, sinó un string amb l'error
	 */
	static String exporta(String s) {
		try {
			/*Scanner reader = new Scanner(System.in);
			System.out.print("On vols guardar el fitxer? (escriu el path absolut acabat en /)");
			String path = reader.next();
			System.out.print("Fica un nom pel fitxer: ");
			path = path + reader.next();
			reader.close();*/
			BufferedWriter writer = new BufferedWriter(new FileWriter("/home/blackaichi/Desktop/codificar_classes/export")); //canviar per path i ja esta
			writer.write(s);
			writer.close();
			System.out.print("Fet!");
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
