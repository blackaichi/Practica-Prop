package persistencia.exports;

import java.io.*;
import java.util.*;

public class Exporta {
	static void exporta(String s) throws IOException {
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
	}
}
