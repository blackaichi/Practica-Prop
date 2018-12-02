package persistencia.exports;

import java.util.*;
import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaCampus extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaCampus instancia = new ExportaCampus();
	
	/**
	 * Creadora buida
	 */
	private ExportaCampus() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaCampus getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Campus
	 * @param c campus que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del Campus
	 */
	public String exportaCampus(Campus c, boolean crea) {
		String endl = "\n";
		String str = "Campus".concat(endl);
		ExportaAula ea = ExportaAula.getInstancia();
		str = str.concat(c.getNom().concat(endl));
		str = str.concat(c.getAutor().concat(endl));
		HashSet<Aula> aules = c.getAllAules();
		str = str.concat(String.valueOf(aules.size()).concat(endl).concat("{").concat(endl));
		for (Aula a : aules) str = str.concat(ea.exportaAula(a, false)).concat(endl);
		str = str.concat("}").concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
