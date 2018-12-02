package persistencia.exports;

import java.io.*;
import java.util.*;
import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaAula extends Exporta {
	
	private static ExportaAula instancia = new ExportaAula();
	
	private ExportaAula() {};
	
	public static ExportaAula getInstancia() {
		return instancia;
	}
	
	public String exportaAula(Aula a, boolean crea) throws IOException {
		String endl = "\n";
		String str = "Aula".concat(endl);
		str = str.concat(a.getNom().concat(endl));
		str = str.concat(String.valueOf(a.getCapacitat())).concat(endl);
		HashSet<String> equip = a.getEquip();
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
