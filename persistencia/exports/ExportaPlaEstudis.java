package persistencia.exports;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaPlaEstudis extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaPlaEstudis instancia = new ExportaPlaEstudis();
	
	/**
	 * Creadora buida
	 */
	private ExportaPlaEstudis() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaPlaEstudis getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un PlaEstudis
	 * @param PE pla d'estudis que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del PlaEstudis
	 */
	public String exportaPlaEstudis(String path, String nom, String autor, Map<Integer, boolean[]> franja,
			int[] rang, String[] nomassig, boolean crea) throws Exception {
		String endl = "\n";
		String str = "PlaEstudis".concat(endl);
		str = str.concat(nom).concat(endl);
		str = str.concat(autor).concat(endl);
		boolean[] b;
		for (int i = 0; i < 7; ++i) {
			b = franja.get(i);
			if (b == null) str = str.concat("n");
			else {
				for (boolean p : b) {
					if (p) str = str.concat("t");
					else str = str.concat("f");
				}
			}
		}
		str = str.concat(endl);
		str = str.concat("mati: ");
		if (rang[0] == -1 || rang[1] == -1) str = str.concat("null ");
		else {
			str = str.concat(String.valueOf(rang[0]).concat(" "));
			str = str.concat(String.valueOf(rang[1]).concat(" "));
		}
		str = str.concat("tarda: ");
		if (rang[2] == -1 || rang[3] == -1) str = str.concat("null").concat(endl);
		else {
			str = str.concat(String.valueOf(rang[0]).concat(" "));
			str = str.concat(String.valueOf(rang[1]).concat(endl));
		}
		str = str.concat(String.valueOf(nomassig.length).concat(endl));
		for (String s : nomassig) {
			str = str.concat(cp.getAssignatura(path, nom, s)).concat(endl);
		}
		str = str.concat("END");
		if (crea) Exporta.exporta(path, str);
		return str;
	}
}
