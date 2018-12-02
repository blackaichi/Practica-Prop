package persistencia.exports;

import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaGrup extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaGrup instancia = new ExportaGrup();
	
	/**
	 * Creadora buida
	 */
	private ExportaGrup() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaGrup getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Grup
	 * @param g grup que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del Grup
	 */
	public String exportaGrup(Grup g, boolean crea) {
		String endl = "\n";
		String str = "Grup".concat(endl);
		ExportaSubGrup esg = ExportaSubGrup.getInstancia();
		ExportaHoresAptes eha = ExportaHoresAptes.getInstancia();
		ExportaSolapaments es = ExportaSolapaments.getInstancia();
		str = str.concat(String.valueOf(g.getNumero())).concat(endl);
		str = str.concat(String.valueOf(g.getPlaces())).concat(endl);
		str = str.concat(g.getFranja()).concat(endl);
		HashSet<SubGrup> subGrups = g.getAllSubGrups();
		str = str.concat(String.valueOf(subGrups.size()).concat(endl).concat("{").concat(endl));
		for (SubGrup sg : subGrups) str = str.concat(esg.exportaSubGrup(sg, false)).concat(endl);
		str = str.concat("}").concat(endl);
		str = str.concat(eha.exportaHoresAptes(g.getRestriccioHoresAptes(), false)).concat(endl);
		str = str.concat(es.exportaSolapaments(g.getSolapaments(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
