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
	
	/**
	 * 
	 * @param path Path on volem exportar l'horari
	 * @param flags Flags de l'estructura de l'horari que volem exportar
	 * @param nomC Nom del Campus
	 * @param autorC nom del autor del Campus
	 * @param aules Aules del campus
	 * @param nomPE Nom del pla d'estudis
	 * @param autorPE autor del pla d'estudis
	 * @param lectiu hores lectives del pls
	 * @param rang Rang en el que es pot fer classe en un dia
	 * @param assignatures assignatures del pla d'estudis
	 * @param id identificador d'un horari
	 */
	public void exportaHorari(String path, HashSet<String> flags, String nomC,String autorC,
		HashSet<String> aules,String nomPE, String autorPE, Map<Integer, boolean[] > lectiu,
		int[] rang,HashSet<String> assignatures, int id) {
		int aux_1 = path.lastIndexOf('/');
		String name = path.substring(aux_1, path.length());
		File dir = new File(path);
		if (dir.exists()) {
			File[] entrades = dir.listFiles();
			for (File f : entrades) {
				f.delete();
			}
			dir.delete();
		}
		path = path.concat(name);
		dir.mkdir();
		String pathCampus = path.concat("_Campus");
		cp.exportaCampus(pathCampus, nomC, autorC, aules);
		String pathPla = path.concat("_PlaEstudis");
		cp.exportaPlaEstudis(pathPla, nomPE, autorC, lectiu, rang, assignatures);
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
	
	/**
	 * Importa un horari
	 * @param path path del horari que volem importar 
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaHorari(String path) {
		try {
			
			String campus_path = null;
			String plaEstudis_path = null;
			String horari = null;
			File dir = new File(path);
			if (dir.exists()) {
				File[] entrades = dir.listFiles();
				if (entrades.length != 3) return "falta arxius al directori";
				for (File f : entrades) {
					String fil = f.getName();
					if(fil.contains("Campus")) campus_path = f.getAbsolutePath();
					else if(fil.contains("PlaEstudis")) plaEstudis_path = f.getAbsolutePath();
					else horari = f.getAbsolutePath();
					
				}
			}
			dir.mkdir();
			cp.importaCampus(campus_path);
			cp.importaPlaEstudis(plaEstudis_path);
			if (horari == null) return  "no hi ha cap export de horari en aquest path";
			File file = new File(horari);
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String s = "";
			String nomPla = null; 
			String nomCampus = null;
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
			for(String nom : f) flags.add(nom);
			int id = cp.generarEntorn(nomPla, nomCampus, flags);
			for(int k = 0; k < 7; ++k) {
				i++;
				for(int j = 0; j < 24; ++j) {
					i++;
					while(entrada.get(i).equals("Segment")) {
						List<String> aux;
						aux = entrada.subList(i, i+4);
						if(aux.size() != 4) return "error tamany segment ";
						DadesSegment.getInstancia().importaSegment(nomPla, nomCampus, k, j, aux, id);
						i+=4;
					}
				}

			}
			
		} catch (Exception e) {
			e.getMessage();
		}
		return null;
	}
}
