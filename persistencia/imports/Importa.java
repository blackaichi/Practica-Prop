package persistencia.imports;

import java.io.*;
import java.util.*;

public class Importa {
	static String importa() throws IOException {
		Scanner reader = new Scanner(System.in);
		System.out.print("Quin fitxer vols obrir? (escriu el path absolut acabat en /)");
		String path = reader.next();
		reader.close();
		File file = new File(path);
	    BufferedReader br = new BufferedReader(new FileReader(file)); 
	    String str = "", aux; 
	    while ((aux = br.readLine()) != "END") {
	    	str.concat(aux + "\n"); 
	    }
	    br.close();
	    return str;
	}
}
