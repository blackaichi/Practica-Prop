package persistencia.imports;

import java.io.*;
import java.util.*;

public class Importa {
	static String importa() throws IOException {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String s; 
			s = br.readLine();
			
			br.close();
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
