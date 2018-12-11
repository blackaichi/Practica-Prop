package persistencia;

import java.io.*;

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
	
	public String importaData(String path, int dia, int hora) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String s; 
			s = br.readLine();
			if (!s.equals("Data")) {
				br.close();
				return "No es un fitxer amb una data";
			}
			s = br.readLine();
			dia = Integer.valueOf(s);
			s = br.readLine();
			hora = Integer.valueOf(s);
			br.close();
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
