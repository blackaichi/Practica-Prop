package persistencia;

import java.io.*;
import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public abstract class ExportaImporta {
	
	/**
	 * Instància del controlador de persistència
	 */
	protected ControladorPersistencia cp = ControladorPersistencia.getInstancia();
	
	/**
	 * Papu esto esta chido
	 */
	protected String endl = "\n";
	
	/**
	 * Exporta a un fitxer que trobem a path tot el contingut de l'string s
	 * @param path path del fitxer que volem 
	 * @param s contingut que volem ficar al fitxer
	 * @param crea si es vol truncar el fitxer, false si es vol fer un append
	 * @return null en cas de estar correcte, sinó l'error
	 */
	static protected String exporta(String path, String s, boolean crea) {
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
	
	static public HashSet<String> fitxersPath(String path) {
		File folder = new File(path);
		HashSet<String> res = new HashSet<String>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (!fileEntry.isDirectory()) {
	        	res.add(fileEntry.getName());
	        }
	    }
	    return res;
	}
}
