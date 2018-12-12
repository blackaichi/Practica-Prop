package persistencia;

import java.util.*;

import domini.*;
import domini.classes.*;
import domini.restriccions.*;
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
			HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		try {
			DadesAssignatura.getInstancia().exportaAssignatura(path, nomPE, 
					nomAssig, sessionsg, sessionssg, grups, horesAptes, solapaments, crea);
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
	public String exportaAula(String path, String nom, int capacitat, HashSet<String> equip, boolean crea) {
		try {
			DadesAula.getInstancia().exportaAula(path, nom, capacitat, equip, crea);
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
			DadesCampus.getInstancia().exportaCampus(path, nom, autor, aules);
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
			HashSet<Integer> numsg, Map<Integer, boolean[]> horesAptes,	HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		try {
			DadesGrup.getInstancia().exportaGrup(path, nomPE, nomAssig, numero, places, franja, numsg, horesAptes, solapaments, crea);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta un Horari
	 * @param e horari a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaHorari(String path, HashSet<String> flags, String nomC, String nomPE, int id) {
		try {
			DadesHorari.getInstancia().exportaHorari(path, flags, nomC, nomPE, id);
			return null;
		}
		catch (Exception ex) {
			return ex.getMessage();
		}
	}
	
	/**
	 * Exporta un PlaEstudis
	 * @param pe pla d'estudis a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaPlaEstudis(String path, String nom, String autor, Map<Integer, 
			boolean[]> franja, int[] rang, HashSet<String> nomassig) {
		try {
			DadesPlaEstudis.getInstancia().exportaPlaEstudis(path, nom, autor, franja, rang, nomassig);
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
			int hores, String tipus, int nsessions, HashSet<Integer> ngrups, boolean crea) {
		try {
			DadesSessioGrup.getInstancia().exportaSessioGrup(path, equip, hores, tipus, nsessions, ngrups, crea);
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
			int hores, String tipus, int nsessions, HashSet<Integer> nsubgrups, boolean crea) {
		try {
			DadesSessioSubGrup.getInstancia().exportaSessioSubGrup(path, equip, hores, tipus, nsessions, nsubgrups, crea);
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
			Map<Integer, boolean[]> horesAptes,	HashMap<String, HashSet<Integer>> solapaments, boolean crea) {
		try {
			DadesSubGrup.getInstancia().exportaSubGrup(path, numero, places, horesAptes, solapaments, crea);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String exportaSegment(String path, String nomAula, String nomAssig, String tipus,
			int numg, int numsg, boolean grup) {
		try {
			DadesSegment.getInstancia().exportaSegment(path, nomAula, nomAssig, tipus, numg, numsg, grup);
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
		return DadesAssignatura.getInstancia().importaAssignatura(path, pe);
	}
	
	/**
	 * Importa un Aula
	 * @param path path del fitxer que volem importar
	 * @param c campus al qual pertany l'aula
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaAula(String path, String nomC) {
		return DadesAula.getInstancia().importaAula(path, nomC, null);
	}
	
	/**
	 * Importa un Campus
	 * @param path path del fitxer que volem importar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaCampus(String path) {
		return DadesCampus.getInstancia().importaCampus(path);
	}
	
	/**
	 * Importa una Data
	 * @param path path del fitxer que volem importar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaData(String path, int dia, int hora) {
		DadesData id = DadesData.getInstancia();
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
		DadesGrup ig = DadesGrup.getInstancia();
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
		DadesHorari ih = DadesHorari.getInstancia();
		return ih.importaHoraris(path, pe, c);
	}
	
	/**
	 * Importa unes HoresAptes
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertanyen les hores aptes
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaHoresAptes(String path, PlaEstudis pe) {
		DadesHoresAptes iha = DadesHoresAptes.getInstancia();
		return iha.importaHoresAptes(path, pe);
	}
	
	/**
	 * Importa un PlaEstudis
	 * @param path path del fitxer que volem importar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaPlaEstudis(String path) {
		if ((error = DadesPlaEstudis.getInstancia().importaPlaEstudis(path)) != null) return error;
		return null;
	}
	
	/**
	 * Importa una SessioGrup
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany la sessió de grup
	 * @param a assignatura a la qual pertany la sessió de grup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaSessioGrup(String path, PlaEstudis pe, Assignatura a) {
		DadesSessioGrup isg = DadesSessioGrup.getInstancia();
		return isg.importaSessioGrup(path, pe, a);
	}
	
	/**
	 * Importa una SessioSubGrup
	 * @param path path del fitxer que volem importar
	 * @param pe pla d'estudis al qual pertany la sessió de subgrup
	 * @param a assignatura a la qual pertany la sessió de subgrup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaSessioSubGrup(String path, PlaEstudis pe, Assignatura a) {
		DadesSessioSubGrup issg = DadesSessioSubGrup.getInstancia();
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
		DadesSubGrup isg = DadesSubGrup.getInstancia();
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
		DadesSolapaments is = DadesSolapaments.getInstancia();
		return is.importaSolapaments(path, pe, a, g, sg);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  FUNCIONS  /////////////////////////////////////
	
	public void getAssignatura(String path, String pla, String assig) {
		cd.exportaAssignatura(path, pla, assig, false);
	}

	public void getAula(String path, String nom, String s) {
		cd.exportaAula(path, s, nom, false);
	}

	public void getSessionsG(String path, String nomPE, String nomAssig, String tipus, Integer hores) {
		cd.exportaSessioGrup(path, nomPE, nomAssig, tipus, hores, false);
	}
	
	public void getSessionsSG(String path, String nomPE, String nomAssig, String tipus, int hores) {
		cd.exportaSessioSubGrup(path, nomPE, nomAssig, tipus, hores, false);
	}

	public void getGrups(String path, String nomPE, String nomAssig, int g) {
		cd.exportaGrup(path, g, nomAssig, nomPE, false);
	}

	public void getSubGrup(String path, String nomPE, String nomAssig, int g, int sg) {
		cd.exportaSubGrup(path, sg, g, nomAssig, nomPE, false);
	}

	public void getSegment(String path, int dia, int hora, String nomC, String nomPE, int id) {
		cd.exportaSegment(path, dia, hora, nomC, nomPE, id);
	}
	
	public String creaPlaEstudisImportat(String nom, String autor, Map<Integer, boolean[]> lectiu, int[] rangDia) {
		if ((error = cd.CrearPlaEstudis(nom)) != null) return error;
		if ((error = cd.ModificarPlaEstudis(nom, null, autor, lectiu, rangDia)) != null) return error;
		return null;
	}

	public String creaCampusImportat(String nomC, String autor) {
		if ((error = cd.CrearCampus(nomC)) != null) return error;
		if ((error = cd.ModificarCampus(nomC, null, autor)) != null) return error;
		return null;
	}

	public String creaAulaImportada(String nomC, String nomA, int capacitat, HashSet<String> equip) {
		if ((error = cd.CrearAula(nomC, nomA, capacitat)) != null) return error;
		if ((error = cd.ModificarAula(nomC, nomA, null, -1, equip)) != null) return error;
		return null;
	}
	
	public String creaAssignaturaImportada(String nomPE, String nomA) {
		if ((error = cd.CrearAssignatura(nomPE, nomA)) != null) return error;
		return null;
	}

	public String creaSessioGrupImportada(String nomPE, String nomA, HashSet<String> equip, int hores, String tipus,
			int nsessions) {
		if ((error = cd.CrearAula(nomC, nomA, capacitat)) != null) return error;
		if ((error = cd.ModificarAula(nomC, nomA, null, -1, equip)) != null) return error;
		return null;
	}

	public String eliminaCampus(String nomC) {
		if ((error = cd.EliminarCampus(nomC)) != null) return error;
		return null;
	}
}
