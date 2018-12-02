package persistencia.exports;

import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaSessioSubGrup extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSessioSubGrup instancia = new ExportaSessioSubGrup();
	
	/**
	 * Creadora buida
	 */
	private ExportaSessioSubGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSessioSubGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una SessioSubGrup
	 * @param ssg sessió de sub grup que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de la SessioSubGrup
	 */
	public String exportaSessioSubGrup(SessioSubGrup ssg, boolean crea) {
		String endl = "\n";
		String str = "SessioSubGrup".concat(endl);
		HashSet<String> equip = ssg.getMaterial();
		boolean first = true;  
		if (equip.isEmpty()) str = str.concat("noequip");
		for (String s : equip) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		str = str.concat(String.valueOf(ssg.getHores())).concat(endl);
		str = str.concat(ssg.getTipus()).concat(endl);
		str = str.concat(String.valueOf(ssg.getnsessions())).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
