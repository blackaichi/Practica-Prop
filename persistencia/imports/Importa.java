package persistencia.imports;

import java.io.*;

public class Importa {
	static String importa(String path) throws IOException {
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
