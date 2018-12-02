package persistencia.imports;

import java.io.*;
import domini.classes.*;
import utils.*;

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
	
	public String importaData(String path) {
		try {
			int dia, hora;
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
			Data data = new Data(dia, hora);
			br.close();
			return new Pair<String, Data>(null, data);
		}
		catch (Exception e) {
			return new Pair<String, Data>(e.getMessage(), null);
		}
	}
}
