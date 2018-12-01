package persistencia.exports;

import java.util.*;
import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaPlaEstudis extends Exporta {
	public static String exportaPlaEstudis(PlaEstudis PE, boolean crea) throws Exception {
		String endl = "\n";
		String str = "PlaEstudis".concat(endl);
		str = str.concat(PE.getNom()).concat(endl);
		str = str.concat(PE.getAutor()).concat(endl);
		Map<Integer, boolean[]> franja = PE.getLectiuSetmana();
		boolean[] b;
		for (int i = 0; i < 7; ++i) {
			b = franja.get(i);
			str = str.concat(String.valueOf(i)).concat("(");
			if (b == null) str = str.concat("null");
			else {
				for (boolean p : b) {
					if (p) str = str.concat("t");
					else str = str.concat("f");
				}
			}
			str = str.concat(")");
		}
		str = str.concat(endl);
		HashSet<Assignatura> assigs = PE.getAssignatures();
		str = str.concat(String.valueOf(assigs.size()).concat(endl).concat("{").concat(endl));
		for (Assignatura a : assigs) str = str.concat(ExportaAssignatura.exportaAssignatura(a, false)).concat(endl);
		str = str.concat("}").concat(endl);
		int[] rang = PE.getRangMati();
		str = str.concat("mati: ");
		if (rang[0] == -1 || rang[1] == -1) str = str.concat("null  ");
		else {
			str = str.concat(String.valueOf(rang[0]).concat(" "));
			str = str.concat(String.valueOf(rang[1]).concat(" "));
		}
		rang = PE.getRangTarda();
		str = str.concat("tarda: ");
		if (rang[0] == -1 || rang[1] == -1) str = str.concat("null").concat(endl);
		else {
			str = str.concat(String.valueOf(rang[0]).concat(" "));
			str = str.concat(String.valueOf(rang[1]).concat(endl));
		}
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
