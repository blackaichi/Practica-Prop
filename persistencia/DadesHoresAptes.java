package persistencia;

import java.util.*;

import domini.classes.PlaEstudis;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesHoresAptes extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesHoresAptes instancia = new DadesHoresAptes();
	
	/**
	 * Creadora buida
	 */
	private DadesHoresAptes() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesHoresAptes getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta unes HoresAptes
	 * @param ha hores aptes que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de les HoresAptes
	 */
	public void exportaHoresAptes(Map<Integer, boolean[]> franja) {
		String str = "HoresAptes".concat(endl);
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
		str = str.concat(endl).concat("END HA").concat(endl);
	}

	public String importaHoresAptes(String path, PlaEstudis pe) {
		
		return null;
	}
}
