package persistencia.exports;

import java.io.*;

import persistencia.ControladorPersistencia;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class Exporta {
	
	protected ControladorPersistencia cp = ControladorPersistencia.getInstancia();
	
	/**
	 * Exporta a un fitxer el contingut de l'String s
	 * @param s el contingut que volem ficar al fitxer
	 * @return null en cas de cap error, sinó un string amb l'error
	 */
	static String exporta(String path, String s) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(s);
			writer.close();
			System.out.println("Exportat correctament");
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
