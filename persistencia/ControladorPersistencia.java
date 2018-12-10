package persistencia;

import java.util.*;

import domini.*;
import domini.classes.*;
import domini.restriccions.*;
import persistencia.exports.*;
import persistencia.imports.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public final class ControladorPersistencia {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ControladorPersistencia cp = new ControladorPersistencia();
	
	private ControladorPersistencia() {}
	
	/**
	 * Instància del controlador del domini
	 */
	private ControladorDomini cd = ControladorDomini.getInstance();
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ControladorPersistencia getInstancia() {
		return cp;
	}
	
	/**
	 * String que conté l'error en cas d'haver
	 */
	private String error;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  EXPORTS  //////////////////////////////////////
	
	/**
	 * Exporta una Assignatura
	 * @param a assignatura a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaAssignatura(String path, String nomPE, String nomAssig, HashSet<Pair<String,Integer>> sessionsg,
			HashSet<Pair<String,Integer>> sessionssg, HashSet<Integer> grups, Map<Integer, boolean[]> horesAptes,
			HashMap<String, HashSet<Integer>> solapaments) {
		try {
			ExportaAssignatura.getInstancia().exportaAssignatura(path, nomPE, 
					nomAssig, sessionsg, sessionssg, grups, horesAptes, solapaments, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta un Aula
	 * @param a aula a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaAula(String path, String nom, int capacitat, HashSet<String> equip) {
		try {
			ExportaAula.getInstancia().exportaAula(path, nom, capacitat, equip, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta un Campus
	 * @param c campus a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaCampus(String path, String nom, String autor, HashSet<String> aules) {
		try {
			ExportaCampus.getInstancia().exportaCampus(path, nom, autor, aules);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta una Data
	 * @param d data a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaData(String path, int dia, int hora) {
		try {
			ExportaData.getInstancia().exportaData(path, dia, hora, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}		
	}
	
	/**
	 * Exporta un Grup
	 * @param g grup a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaGrup(String path, String nomPE, String nomAssig, int numero, int places, String franja, 
			HashSet<Integer> numsg, Map<Integer, boolean[]> horesAptes,	HashMap<String, HashSet<Integer>> solapaments) {
		try {
			ExportaGrup.getInstancia().exportaGrup(path, nomPE, nomAssig, numero, places, franja, numsg, horesAptes, solapaments, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	//TODO
	/**
	 * Exporta un Horari
	 * @param e horari a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaHorari(HashSet<Estructura> e) {
		try {
			ExportaHorari.getInstancia().exportaHoraris(e, true);
			return null;
		}
		catch (Exception ex) {
			return ex.getMessage();
		}
	}
	
	/**
	 * Exporta unes HoresAptes
	 * @param ha hores aptes a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaHoresAptes(String path, Map<Integer, boolean[]> horesAptes) {
		try {
			ExportaHoresAptes.getInstancia().exportaHoresAptes(path, horesAptes, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta un PlaEstudis
	 * @param pe pla d'estudis a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaPlaEstudis(String path, String nom, String autor, Map<Integer, 
			boolean[]> franja, int[] rang, String[] nomassig) {
		try {
			ExportaPlaEstudis.getInstancia().exportaPlaEstudis(path, nom, autor, franja, rang, nomassig);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta una SessioGrup
	 * @param sg sessió de grup a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaSessioGrup(String path, HashSet<String> equip,
			int hores, String tipus, int nsessions, int[] ngrups) {
		try {
			ExportaSessioGrup.getInstancia().exportaSessioGrup(path, equip, hores, tipus, nsessions, ngrups, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}

	/**
	 * Exporta una SessioSubGrup
	 * @param ssg sessió de subgrup a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaSessioSubGrup(String path, HashSet<String> equip,
			int hores, String tipus, int nsessions, int[] nsubgrups) {
		try {
			ExportaSessioSubGrup.getInstancia().exportaSessioSubGrup(path, equip, hores, tipus, nsessions, nsubgrups, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta un SubGrup 
	 * @param sg subgrup a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaSubGrup(String path, int numero, int places, 
			Map<Integer, boolean[]> horesAptes,	HashMap<String, HashSet<Integer>> solapaments) {
		try {
			ExportaSubGrup.getInstancia().exportaSubGrup(path, numero, places, horesAptes, solapaments, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta uns Solapaments
	 * @param s solapaments a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaSolapaments(String path, HashMap<String, HashSet<Integer>> solapaments) {
		try {
			ExportaSolapaments.getInstancia().exportaSolapaments(path, solapaments, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  IMPORTS  //////////////////////////////////////
	
	/**
	 * Importa una Assignatura
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany l'assignatura
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaAssignatura(String path, PlaEstudis pe) {
		ImportaAssignatura ia = ImportaAssignatura.getInstancia();
		return ia.importaAssignatura(path, pe);
	}
	
	/**
	 * Importa un Aula
	 * @param path path del fitxer que volem importar
	 * @param c campus al qual pertany l'aula
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaAula(String path, HashSet<String> nomaules, HashSet<Integer> capacitat,
			HashSet<HashSet<String>> equip) {
		ImportaAula ia = ImportaAula.getInstancia();
		return ia.importaAula(path, nomaules, capacitat, equip);
	}
	
	/**
	 * Importa un Campus
	 * @param path path del fitxer que volem importar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaCampus(String path, String nomC, String autor, HashSet<String> nomaules,
			HashSet<Integer> capacitat, HashSet<HashSet<String>> equip) {
		ImportaCampus ic = ImportaCampus.getInstancia();
		return ic.importaCampus(path, nomC, autor, nomaules, capacitat, equip);
	}
	
	/**
	 * Importa una Data
	 * @param path path del fitxer que volem importar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaData(String path, int dia, int hora) {
		ImportaData id = ImportaData.getInstancia();
		return id.importaData(path, dia, hora);
	}
	
	/**
	 * Importa un Grup
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany el grup
	 * @param a assignatura a la qual pertany el grup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaGrup(String path, PlaEstudis pe, Assignatura a) {
		ImportaGrup ig = ImportaGrup.getInstancia();
		return ig.importaGrup(path, pe, a);
	}
	
	/**
	 * Importa un Horari
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany l'horari
	 * @param c campus al qual pertany l'horari
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaHorari(String path, PlaEstudis pe, Campus c) {
		ImportaHorari ih = ImportaHorari.getInstancia();
		return ih.importaHoraris(path, pe, c);
	}
	
	/**
	 * Importa unes HoresAptes
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertanyen les hores aptes
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaHoresAptes(String path, PlaEstudis pe) {
		ImportaHoresAptes iha = ImportaHoresAptes.getInstancia();
		return iha.importaHoresAptes(path, pe);
	}
	
	/**
	 * Importa un PlaEstudis
	 * @param path path del fitxer que volem importar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaPlaEstudis(String path) {
		ImportaPlaEstudis ipe = ImportaPlaEstudis.getInstancia();
		if ((error = ipe.importaPlaEstudis(path)) != null) return error;
		return null;
	}
	
	/**
	 * Importa una SessioGAssignada
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany la sessió de grup assignada
	 * @param a assignatura a la qual pertany la sessió de grup assignada
	 * @param g grup al qual pertany la sessió de grup assignada
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaSessioGAssignada(String path, PlaEstudis pe, Assignatura a, Grup g) {
		ImportaSessioGAssignada isga = ImportaSessioGAssignada.getInstancia();
		return isga.importaSessioGAssignada(path, pe, a, g);
	}
	
	/**
	 * Importa una SessioGrup
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany la sessió de grup
	 * @param a assignatura a la qual pertany la sessió de grup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaSessioGrup(String path, PlaEstudis pe, Assignatura a) {
		ImportaSessioGrup isg = ImportaSessioGrup.getInstancia();
		return isg.importaSessioGrup(path, pe, a);
	}
	
	/**
	 * Importa una SessioSGAssignada
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany la sessió de subgrup assignada
	 * @param a assignatura a la qual pertant la sessió de subgrup assignada
	 * @param g grup al qual pertany la sessió de subgrup assignada
	 * @param sg subgrup al qual pertany la sessió de subgrup assignada
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaSessioSGAssignada(String path, PlaEstudis pe, Assignatura a, Grup g, SubGrup sg) {
		ImportaSessioSGAssignada issga = ImportaSessioSGAssignada.getInstancia();
		return issga.importaSessioSGAssignada(path, pe, a, g, sg);
	}
	
	/**
	 * Importa una SessioSubGrup
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany la sessió de subgrup
	 * @param a assignatura a la qual pertany la sessió de subgrup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaSessioSubGrup(String path, PlaEstudis pe, Assignatura a) {
		ImportaSessioSubGrup issg = ImportaSessioSubGrup.getInstancia();
		return issg.importaSessioSubGrup(path, pe, a);
	}
	
	/**
	 * Importa un SubGrup
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany el subgrup
	 * @param a assigntura a la qual pertany el subgrup
	 * @param g grup al qual pertany el subgrup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaSubGrup(String path, PlaEstudis pe, Assignatura a, Grup g) {
		ImportaSubGrup isg = ImportaSubGrup.getInstancia();
		return isg.importaSubGrup(path, pe, a, g);
	}
	
	/**
	 * Importa uns Solapaments
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertanyen els solapaments
	 * @param a assignatura a la qual pertanyen els solapaments
	 * @param g grup al qual pertanyen els solapaments
	 * @param sg subgrup al qual pertanyen els solapaments
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaSolapaments(String path, PlaEstudis pe, Assignatura a, Grup g, SubGrup sg) {
		ImportaSolapaments is = ImportaSolapaments.getInstancia();
		return is.importaSolapaments(path, pe, a, g, sg);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  FUNCIONS  /////////////////////////////////////
	
	public String getAssignatura(String path, String pla, String assig) {
		return cd.getExportAssignatura(path, pla, assig);
	}

	public String getAula(String path, String nom, String s) {
		return cd.getExportAula(path, nom, s);
	}

	public String getSessionsG(String path, String nomPE, String nomAssig, String first, Integer second) {
		return cd.getExportSessionsG(path, nomPE, nomAssig, first, second);
	}
	
	public String getSessionsSG(String path, String nomPE, String nomAssig, String first, Integer second) {
		return cd.getExportSessionsSG(path, nomPE, nomAssig, first, second);
	}

	public String getGrups(String path, String nomPE, String nomAssig, int g) {
		return cd.getExportGrups(path, nomPE, nomAssig, g);
	}

	public String getHoresAptes(String path, String nomPE, String nomAssig) {
		return cd.getExportHoresAptes(path, nomPE, nomAssig);
	}

	public String getSolapaments(String path, String nomPE, String nomAssig) {
		return cd.getExportSolapaments(path, nomPE, nomAssig);
	}

	public String getSubGrup(String path, String nomPE, String nomAssig, int numero, int sg) {
		return cd.getExportSubGrup(path, nomPE, nomAssig, numero, sg);
	}

	public String getHoresAptes(String path, String nomPE, String nomAssig, int numero) {
		return getExportHoresAptes(path, nomPE, nomAssig, numero);
	}

	public String getSolapaments(String path, String nomPE, String nomAssig, int numero) {
		return cd.getExportSolapaments(path, nomPE, nomAssig, numero);
	}

	public String getHoresAptes(String path, String nomPE, String nomAssig, int numGrup, int numero) {
		return getExportHoresAptes(path, nomPE, nomAssig, numGrup, numero);
	}

	public String getSolapaments(String path, String nomPE, String nomAssig, int numGrup, int numero) {
		return cd.getExportSolapaments(path, nomPE, nomAssig, numGrup, numero);
	}
	
	public String creaPlaEstudisImportat(String nom, String autor, Map<Integer, boolean[]> lectiu, int[] rangDia) {
		if ((error = cd.CrearPlaEstudis(nom)) != null) return error;
		if ((error = cd.ModificarPlaEstudis(nom, null, autor, lectiu, rangDia)) != null) return error;
		return null;
	}
}
