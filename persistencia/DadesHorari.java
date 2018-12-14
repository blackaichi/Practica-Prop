package persistencia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DadesHorari extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesHorari instancia = new DadesHorari();
	
	/**
	 * Creadora buida
	 */
	private DadesHorari() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesHorari getInstancia() {
		return instancia;
	}
	
	public void exportaHorari(String path, HashSet<String> flags, String nomC, String nomPE, int id) {
		String str = "Horari".concat(endl);
		str = str.concat(nomPE).concat(endl);
		str = str.concat(nomC).concat(endl);
		boolean first = true;
		for (String s : flags) {
			if (first) first = false;
			else str = str.concat(",");
			str = str.concat(s);
		}
		str = str.concat(endl);
		exporta(path, str, true);
		str = "";
		for (int dia = 0; dia < 7; ++dia) {
			exporta(path, String.valueOf(dia).concat(endl), false);
			for (int hora = 0; hora < 24; ++hora) {
				exporta(path, String.valueOf(hora).concat(endl), false);
				cp.getSegment(path, dia, hora, nomC, nomPE, id);
			}
		}
		exporta(path, "END HORARI", false);
	}


	public String importaHoraris(String path, String nomPla, String nomCampus,int id) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String s = "";
			List<String> entrada = new ArrayList<String>();
			while ((s = br.readLine()) != null) {
				entrada.add(s);
			}
			br.close();
			int i = 0;
			if (!entrada.get(i++).equals("Horari")) return "Error al llegir la primera linia del fitxer";
			nomPla = entrada.get(i++);
			nomCampus = entrada.get(i++);
			HashSet<String> flags = new HashSet<String>();
			String[] f = entrada.get(i++).split(",");
			for(String nom : f) 
				flags.add(nom);
			for(int k = 0; k < 7; ++k) {
				for(int j = 0; j < 24; ++j) {
					if (!entrada.get(i++).equals("Segment")) return "Error al llegir Segment";
					List<String> aux;
					aux = entrada.subList(entrada.indexOf("Segment"), entrada.indexOf("END SEGM"));
					if (!entrada.get(i++).equals("buit")) {
						DadesSegment.getInstancia().importaSegment(nomPla, nomCampus, k, j, aux,id);
					}
				}
			}
			
		} catch (Exception e) {
			return e.getMessage();
		}
		return null;
	}
}
