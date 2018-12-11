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
	
	protected String endl = "\n";
	
	/**
	 * Exporta a un fitxer el contingut de l'String s
	 * @param s el contingut que volem ficar al fitxer
	 * @return null en cas de cap error, sinó un string amb l'error
	 */
	static String exporta(String path, String s, boolean crea) {
		try {
			if (crea) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(path));
				writer.write(s);
				writer.close();
			}
			else {
			    FileWriter fw = new FileWriter(path, true); 
			    fw.write(s);
			    fw.close();
			}
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
