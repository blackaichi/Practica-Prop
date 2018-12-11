package persistencia.imports;

import java.io.*;
import persistencia.ControladorPersistencia;

public class Importa {
	
	protected ControladorPersistencia cp = ControladorPersistencia.getInstancia();
	
	static String importa(String path) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = "";
			String res = "";
			while ((s = br.readLine()) != null) {
				res.concat(s).concat("\n");
			}
			br.close();
			return res;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
