package persistencia.imports;

import java.io.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaData extends Importa {
	
	private static ImportaData instancia = new ImportaData();
	
	private ImportaData() {};
	
	public static ImportaData getInstancia() {
		return instancia;
	}
	
	public String importaData(String path, int dia, int hora) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String s; 
			s = br.readLine();
			if (!s.equals("Data")) {
				br.close();
				return "No es un fitxer amb una data";
			}
			s = br.readLine();
			dia = Integer.valueOf(s);
			s = br.readLine();
			hora = Integer.valueOf(s);
			br.close();
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
