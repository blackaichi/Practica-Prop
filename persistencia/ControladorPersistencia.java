package persistencia;

import java.util.*;
import domini.ControladorDomini;
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
	 * Exporta una assignatura
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @param nomAssig nom de l'assignatura
	 * @param sessionsg nom de la sessió de grup
	 * @param sessionssg nom de la sessió subgrup
	 * @param grups nom dels grups
	 * @param horesAptes les hores aptes del pla
	 * @param solapaments els solapaments del pla
	 * @param crea en cas de voler crear el fitxer
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
	 * Exporta un aula
	 * @param path llista amb el que hi havia al fitxer
	 * @param nomAula nom de l'aula
	 * @param capacitat capacitat de l'aula
	 * @param equip equipament de l'aula
	 * @param crea true si es vol truncar el fitxer, false si es vol fer un append
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
	 * Exporta un campus
	 * @param path path del fitxer que volem 
	 * @param nom nom del campus
	 * @param autor autor del campus
	 * @param aules aules que conté el campus
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
	 * Exporta un grup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @param nomAssig nom de l'assignatura
	 * @param numero numero que identifica el grup
	 * @param places places del grup
	 * @param franja franja del grup
	 * @param numsg subgrups que pertanyen al grup
	 * @param horesAptes hores aptes del grup
	 * @param solapaments solapaments del grup
	 * @param crea true si es vol truncar el fitxer, false si es vol fer un append
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
	 * Exporta un horari
	 * @param path path del fitxer que volem 
	 * @param flags flags de l'horari
	 * @param nomC nom del campus al que pertany l'horari
	 * @param nomPE nom del pla d'estudis al que pertany l'horari
	 * @param id identifica l'horari
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
	 * Exporta un pla d'estudis
	 * @param path path del fitxer que volem 
	 * @param nom nom del pla d'estudis
	 * @param autor autor del pla d'estudis
	 * @param franja franja del pla d'estudis
	 * @param rang rang del pla d'estudis
	 * @param nomassig assignatures del pla d'estudis
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
	 * Exporta una sessio de grup
	 * @param path path del fitxer que volem 
	 * @param equip equip de la sessió
	 * @param hores hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre de sessions de la sessió
	 * @param ngrups números dels grups que pertanyen juntament amb la sessió formen una sessió assignada
	 * @param crea true si es vol truncar el fitxer, false si es vol fer un append
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
	 * Exporta una sessió de subgrup
	 * @param path path del fitxer que volem 
	 * @param equip equip de la sessió
	 * @param hores hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre de sessions de la sessió
	 * @param nsubgrups números dels subgrups que pertanyen juntament amb la sessió formen una sessió assignada
	 * @param crea true si es vol truncar el fitxer, false si es vol fer un append
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaSessioSubGrup(String path, HashSet<String> equip,
			int hores, String tipus, int nsessions, HashSet<Pair<Integer, Integer>> nsubgrups, boolean crea) {
		try {
			DadesSessioSubGrup.getInstancia().exportaSessioSubGrup(path, equip, hores, tipus, nsessions, nsubgrups, crea);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta un subgrup
	 * @param path path del fitxer que volem 
	 * @param numero numero del subgrup
	 * @param places places del subgrup
	 * @param horesAptes hores aptes del subgrup
	 * @param solapaments solapaments dels subgrups
	 * @param crea true si es vol truncar el fitxer, false si es vol fer un append
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
	
	/**
	 * Exporta un segment
	 * @param path path del fitxer que volem 
	 * @param nomAula nom de l'aula del segment
	 * @param nomAssig nom de l'assignatura del segment
	 * @param tipus tipus de sessio del segment
	 * @param hores hores de la sessio del segment
	 * @param numg numero de grup del segment
	 * @param numsg numero de subgrup del segment
	 * @param grup true si es un segment de grup, false si es de subgrup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaSegment(String path, String nomAula, String nomAssig, String tipus, int hores,
			int numg, int numsg, boolean grup) {
		try {
			DadesSegment.getInstancia().exportaSegment(path, nomAula, nomAssig, tipus, hores, numg, numsg, grup);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  IMPORTS  //////////////////////////////////////
	
	/**
	 * Importa una assignatura
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaAssignatura(String path, String nomPE) {
		return DadesAssignatura.getInstancia().importaAssignatura(path, nomPE, null);
	}
	
	/**
	 * Importa un aula
	 * @param path llista amb el que hi havia al fitxer
	 * @param nomC nom del campus que pertany l'aula
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaAula(String path, String nomC) {
		return DadesAula.getInstancia().importaAula(path, nomC, null);
	}
	
	/**
	 * Importa un campus
	 * @param path llista amb el que hi havia al fitxer
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaCampus(String path) {
		return DadesCampus.getInstancia().importaCampus(path);
	}
	
	/**
	 * Importa un grup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @param nomA nom de l'assignatura
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaGrup(String path, String nomPE, String nomA) {
		return DadesGrup.getInstancia().importaGrup(path, nomPE, nomA, null);
	}
	
	/**
	 * Importa un horari
	 * @param path path del fitxer que volem 
	 * @param nomC nom del campus
	 * @param nomPE nom del pla d'estudis
	 * @param id identificador de l'horari
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaHorari(String path, String nomC, String nomPE, int id) {
		return DadesHorari.getInstancia().importaHorari(path, nomC, nomPE, id);
	}
	
	/**
	 * Importa un Pla d'estudis
	 * @param path  path del fitxer que volem 
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaPlaEstudis(String path) {
		return DadesPlaEstudis.getInstancia().importaPlaEstudis(path);
	}
	
	/**
	 * Importa una sessió de grup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis
	 * @param nomA nom de l'assignatura
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaSessioGrup(String path, String nomPE, String nomA) {
		return DadesSessioGrup.getInstancia().importaSessioGrup(path, nomPE, nomA, null);
	}
	
	/**
	 * Importa una sessió de subgrup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis 
	 * @param nomA nom de l'assignatura
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaSessioSubGrup(String path, String nomPE, String nomA) {
		return DadesSessioSubGrup.getInstancia().importaSessioSubGrup(path, nomPE, nomA, null);
	}
	
	/**
	 * Importa un subgrup
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis al qual pertany el subgrup
	 * @param nomA nom de l'assignatura a la qual pertany el subgrup
	 * @param grup nom del grup al qual pertany el grup
	 * @return null en cas de estar correcte, sinó l'error
	 */
	public String importaSubGrup(String path, String nomPE, String nomA, int grup) {
		return DadesSubGrup.getInstancia().importaSubGrup(path, nomPE, nomA, grup, null);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  FUNCIONS  /////////////////////////////////////
	
	/**
	 * Demana al controlador de domini que exporti una assignatura
	 * @param path path del fitxer que volem 
	 * @param pla pla d'estudis de l'assignatura
	 * @param assig assignatura que es vol exportar
	 */
	public void getAssignatura(String path, String pla, String assig) {
		cd.exportaAssignatura(path, pla, assig, false);
	}

	/**
	 * Demana al controlador de domini que exporti un aula
	 * @param path path del fitxer que volem 
	 * @param nom nom del campus de l'aula
	 * @param s nom de l'aula
	 */
	public void getAula(String path, String nom, String s) {
		cd.exportaAula(path, s, nom, false);
	}

	/**
	 * Demana al controlador de domini que exporti una assignatura
	 * @param path path del fitxer que volem 
	 * @param nomPE nom del pla d'estudis de la sessió
	 * @param nomAssig nom de l'assignatura de la sessió
	 * @param tipus tipus de la sessió
	 * @param hores hores de la sessió
	 */
	public void getSessionsG(String path, String nomPE, String nomAssig, String tipus, Integer hores) {
		cd.exportaSessioGrup(path, nomPE, nomAssig, tipus, hores, false);
	}
	
	/**
	 * 
	 * @param path
	 * @param nomPE
	 * @param nomAssig
	 * @param tipus
	 * @param hores
	 */
	public void getSessionsSG(String path, String nomPE, String nomAssig, String tipus, int hores) {
		cd.exportaSessioSubGrup(path, nomPE, nomAssig, tipus, hores, false);
	}

	/**
	 * 
	 * @param path
	 * @param nomPE
	 * @param nomAssig
	 * @param g
	 */
	public void getGrups(String path, String nomPE, String nomAssig, int g) {
		cd.exportaGrup(path, g, nomAssig, nomPE, false);
	}

	/**
	 * 
	 * @param path
	 * @param nomPE
	 * @param nomAssig
	 * @param g
	 * @param sg
	 */
	public void getSubGrup(String path, String nomPE, String nomAssig, int g, int sg) {
		cd.exportaSubGrup(path, sg, g, nomAssig, nomPE, false);
	}

	/**
	 * 
	 * @param path
	 * @param dia
	 * @param hora
	 * @param nomC
	 * @param nomPE
	 * @param id
	 */
	public void getSegment(String path, int dia, int hora, String nomC, String nomPE, int id) {
		cd.exportaSegment(path, dia, hora, nomC, nomPE, id);
	}
	
	/**
	 * 
	 * @param nom
	 * @param autor
	 * @param lectiu
	 * @param rangDia
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaPlaEstudisImportat(String nom, String autor, Map<Integer, boolean[]> lectiu, int[] rangDia) {
		if ((error = cd.CrearPlaEstudis(nom)) != null) return error;
		if ((error = cd.ModificarPlaEstudis(nom, null, autor, lectiu, rangDia)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomC
	 * @param autor
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaCampusImportat(String nomC, String autor) {
		if ((error = cd.CrearCampus(nomC)) != null) return error;
		if ((error = cd.ModificarCampus(nomC, null, autor)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomC
	 * @param nomA
	 * @param capacitat
	 * @param equip
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaAulaImportada(String nomC, String nomA, int capacitat, HashSet<String> equip) {
		if ((error = cd.CrearAula(nomC, nomA, capacitat)) != null) return error;
		if ((error = cd.ModificarAula(nomC, nomA, null, -1, equip)) != null) return error;
		return null;
	}
	
	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaAssignaturaImportada(String nomPE, String nomA) {
		if ((error = cd.CrearAssignatura(nomPE, nomA)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param equip
	 * @param hores
	 * @param tipus
	 * @param nsessions
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaSessioGrupImportada(String nomPE, String nomA, HashSet<String> equip, int hores, String tipus,
			int nsessions) {
		if ((error = cd.CrearSessioGrup(nomPE, nomA, tipus, hores)) != null) return error;
		if ((error = cd.ModificarSessioGrup(nomPE, nomA, tipus, hores, null, -1, nsessions, equip)) != null) return error;
		return null;
	}
	
	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param equip
	 * @param hores
	 * @param tipus
	 * @param nsessions
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaSessioSubGrupImportada(String nomPE, String nomA, HashSet<String> equip, int hores, String tipus,
			int nsessions) {
		if ((error = cd.CrearSessioSubGrup(nomPE, nomA, tipus, hores)) != null) return error;
		if ((error = cd.ModificarSessioSubGrup(nomPE, nomA, tipus, hores, null, -1, nsessions, equip)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param grup
	 * @param capacitat
	 * @param franja
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaGrupImportat(String nomPE, String nomA, int grup, int capacitat, String franja) {
		if ((error = cd.CrearGrup(nomPE, nomA, grup, capacitat)) != null) return error;
		if ((error = cd.ModificarGrup(nomPE, nomA, grup, grup, capacitat, franja)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param tipus
	 * @param hores
	 * @param grup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaSessioGrupAImportada(String nomPE, String nomA, String tipus, int hores, int grup) {
		if ((error = cd.AssignaSessioGrup(nomPE, nomA, tipus, hores, grup)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param tipus
	 * @param hores
	 * @param grup
	 * @param subgrup
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaSessioSubGrupAImportada(String nomPE, String nomA, String tipus, int hores, int grup, int subgrup) {
		if ((error = cd.AssignaSessioSubGrup(nomPE, nomA, tipus, hores, grup, subgrup)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param grup
	 * @param subgrup
	 * @param ha
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaHoresAptes(String nomPE, String nomA, int grup, int subgrup, Map<Integer, boolean[]> ha) {
		Map<Integer, int[]> ha2 = new HashMap<Integer, int[]>();
		Map<Integer, int[]> ha3 = new HashMap<Integer, int[]>();
		for (Map.Entry<Integer, boolean[]> entry : ha.entrySet()) {
			List<Integer> lt = new ArrayList<Integer>();
			List<Integer> lf = new ArrayList<Integer>();
			int j = 0;
			if (entry.getValue() != null) {
				for (boolean bb : entry.getValue()) {
					if (bb) lt.add(j++);
					else lf.add(j++);
				}
				int[] b = new int[lt.size()];
				for (int i = 0; i < lt.size(); ++i) {
					b[i] = lt.get(i);
				}
				ha2.put(entry.getKey(), b);
				b = new int[lf.size()];
				for (int i = 0; i < lf.size(); ++i) {
					b[i] = lf.get(i);
				}
				ha3.put(entry.getKey(), b);
			}
			else {
				ha2.put(entry.getKey(), null);
				ha3.put(entry.getKey(), null);
			}
		}
		if ((error = cd.HoresAptes(nomPE, nomA, grup, subgrup, ha2, true, true)) != null) return error;
		if ((error = cd.HoresAptes(nomPE, nomA, grup, subgrup, ha3, false, true)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param solapaments
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaSolapament(String nomPE, String nomA, int grup, int subgrup, HashMap<String, HashSet<Integer>> solapaments) {
		if (grup < 0) {
			for (Map.Entry<String, HashSet<Integer>> entry : solapaments.entrySet()) 
				if ((error = cd.SetSolapamentAssig(nomPE, nomA, entry.getKey(), false)) != null) return error;
		}
		else if (subgrup < 0) {
			for (Map.Entry<String, HashSet<Integer>> entry : solapaments.entrySet()) {
				for (int i : entry.getValue())
					if ((error = cd.SetSolapamentGrup(nomPE, nomA, grup, entry.getKey(), i, false)) != null) return error;
			}
		}
		else {
			for (Map.Entry<String, HashSet<Integer>> entry : solapaments.entrySet()) {
				for (int i : entry.getValue())
					if ((error = cd.SetSolapamentSubGrup(nomPE, nomA, grup, subgrup, entry.getKey(), i, false)) != null) return error;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param grup
	 * @param places
	 * @param franja
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaSubGrupImportat(String nomPE, String nomA, int grup, int places, String franja) {
		if ((error = cd.CrearGrup(nomPE, nomA, grup, places)) != null) return error;
		if ((error = cd.ModificarGrup(nomPE, nomA, grup, -1, places, franja)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param grup
	 * @param numero
	 * @param places
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String creaSubGrupImportat(String nomPE, String nomA, int grup, int numero, int places) {
		if ((error = cd.CrearSubGrup(nomPE, nomA, grup, numero, places, false)) != null) return error; 
		return null;
	}

	/**
	 * 
	 * @param nomC
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String eliminaCampus(String nomC) {
		if ((error = cd.EliminarCampus(nomC)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String eliminaPlaEstudis(String nomPE) {
		if ((error = cd.EliminaPlaEstudis(nomPE)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String eliminaAssignatura(String nomPE, String nomA) {
		if ((error = cd.EliminarAssignatura(nomPE, nomA)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param tipus
	 * @param hores
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String eliminaSessioGrup(String nomPE, String nomA, String tipus, int hores) {
		if ((error = cd.EliminaSessioGrup(nomPE, nomA, tipus, hores)) != null) return error;
		return null;
	}
	
	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param tipus
	 * @param hores
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String eliminaSessioSubGrup(String nomPE, String nomA, String tipus, int hores) {
		if ((error = cd.EliminaSessioSubGrup(nomPE, nomA, tipus, hores)) != null) return error;
		return null;
	}

	/**
	 * 
	 * @param nomPE
	 * @param nomA
	 * @param numero
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String eliminaGrup(String nomPE, String nomA, int numero) {
		if ((error = cd.EliminarGrup(nomPE, nomA, numero)) != null) return error;
		return null;
	}

	public String creaSegmentImportat(String plaEst, String nomC, int dia, int hora, String aula, String nomA,
			String tipus, int hores, int numg, int numsg, int id) {
		if ((error = cd.crearSegment(plaEst, nomC, dia, hora, aula, nomA, tipus, hores, numg, numsg, id)) != null) return error;
		return null;
	}
}
