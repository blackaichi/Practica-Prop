package persistencia.exports;

import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioGrup extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSessioGrup instancia = new ExportaSessioGrup();
	
	/**
	 * Creadora buida
	 */
	private ExportaSessioGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSessioGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una SessioGrup
	 * @param sg sessió de grup que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de la SessioGrup
	 */
	public String exportaSessioGrup(SessioGrup sg, boolean crea) {
		String endl = "\n";
		String str = "SessioGrup".concat(endl);
		HashSet<String> equip = sg.getMaterial();
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		str = str.concat(String.valueOf(sg.getHores())).concat(endl);
		str = str.concat(sg.getTipus()).concat(endl);
		str = str.concat(String.valueOf(sg.getnsessions())).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
