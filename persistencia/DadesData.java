package persistencia;

import java.io.*;
import java.util.List;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesData extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesData instancia = new DadesData();
	
	/**
	 * Creadora buida
	 */
	private DadesData() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesData getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta una Data
	 * @param d data que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació de la Data
	 */
	public void exportaData(String path, int dia, int hora) {
		String str = "Data".concat(endl);
		str = str.concat(String.valueOf(dia)).concat(endl);
		str = str.concat(String.valueOf(hora)).concat(endl);
		str = str.concat("END DATA").concat(endl);
		exporta(path, str, false);
	}
	
	public String importaData(List<String> f) {
		try {
			if (f.size() != 4) return "error a la data";
			if (!f.get(0).equals("Data") || !f.get(3).equals("END DATA")) return "error a la data";
			int dia = Integer.valueOf(f.get(1));
			int hora = Integer.valueOf(f.get(2));
			cp.creaData(dia, hora);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
