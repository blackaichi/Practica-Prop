package persistencia;

import java.util.*;

import domini.*;
import domini.classes.*;
import domini.restriccions.*;
import persistencia.exports.*;
import persistencia.imports.*;
import utils.*;

public final class ControladorPersistencia {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	ControladorPersistencia cp = new ControladorPersistencia();
	
	/**
	 * Creadora buida
	 */
	ControladorDomini cd = ControladorDomini.getInstancia();
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public ControladorPersistencia getInstancia() {
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
	public String exportaAssignatura(Assignatura a) {
		try {
			ExportaAssignatura assignatura = ExportaAssignatura.getInstancia();
			assignatura.exportaAssignatura(a, true);
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
	public String exportaAula(Aula a) {
		try {
			ExportaAula aula = ExportaAula.getInstancia();
			aula.exportaAula(a, true);
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
	public String exportaCampus(Campus c) {
		try {
			ExportaCampus campus = ExportaCampus.getInstancia();
			campus.exportaCampus(c, true);
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
	public String exportaData(Data d) {
		try {
			ExportaData data = ExportaData.getInstancia();
			data.exportaData(d, true);
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
	public String exportaGrup(Grup g) {
		try {
			ExportaGrup grup = ExportaGrup.getInstancia();
			grup.exportaGrup(g, true);
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
	public String exportaHorari(HashSet<Estructura> e) {
		try {
			ExportaHorari horari = ExportaHorari.getInstancia();
			horari.exportaHoraris(e, true);
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
	public String exportaHoresAptes(HoresAptes ha) {
		try {
			ExportaHoresAptes horesAptes = ExportaHoresAptes.getInstancia();
			horesAptes.exportaHoresAptes(ha, true);
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
	public String exportaPlaEstudis(PlaEstudis pe) {
		try {
			ExportaPlaEstudis plaEstudis = ExportaPlaEstudis.getInstancia();
			plaEstudis.exportaPlaEstudis(pe, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta una SessioGAssignada
	 * @param sga sessió de grup assignada a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaSessioGAssignada(SessioGAssignada sga) {
		try {
			ExportaSessioGAssignada sessioGAssignada = ExportaSessioGAssignada.getInstancia();
			sessioGAssignada.exportaSessioGAssignada(sga, true);
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
	public String exportaSessioGrup(SessioGrup sg) {
		try {
			ExportaSessioGrup sessioGrup = ExportaSessioGrup.getInstancia();
			sessioGrup.exportaSessioGrup(sg, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	/**
	 * Exporta una SessioSGAssignada
	 * @param ssga sessió de subgrup assignada a exportar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String exportaSessioSGAssignada(SessioSGAssignada ssga) {
		try {
			ExportaSessioSGAssignada sessioSGAssignada = ExportaSessioSGAssignada.getInstancia();
			sessioSGAssignada.exportaSessioSGAssignada(ssga, true);
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
	public String exportaSessioSubGrup(SessioSubGrup ssg) {
		try {
			ExportaSessioSubGrup sessioSubGrup = ExportaSessioSubGrup.getInstancia();
			sessioSubGrup.exportaSessioSubGrup(ssg, true);
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
	public String exportaSubGrup(SubGrup sg) {
		try {
			ExportaSubGrup subGrup = ExportaSubGrup.getInstancia();
			subGrup.exportaSubGrup(sg, true);
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
	public String exportaSolapaments(Solapaments s) {
		try {
			ExportaSolapaments solapaments = ExportaSolapaments.getInstancia();
			solapaments.exportaSolapaments(s, true);
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
	public String importaAula(String path, Campus c) {
		ImportaAula ia = ImportaAula.getInstancia();
		return ia.importaAula(path, c);
	}
	
	/**
	 * Importa un Campus
	 * @param path path del fitxer que volem importar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaCampus(String path) {
		ImportaCampus ic = ImportaCampus.getInstancia();
		return ic.importaCampus(path);
	}
	
	/**
	 * Importa una Data
	 * @param path path del fitxer que volem importar
	 * @return null en cas de cap error, l'error com a String altrament
	 */
	public String importaData(String path) {
		ImportaData id = ImportaData.getInstancia();
		return id.importaData(path);
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
		if ((error = cd.CrearCampus(ipe.getNom())) != null) return error;
		if ((error = cd.ModificarPlaEstudis(ipe.getNom(), null, ipe.getAutor(), ipe.getLectiu(), ipe.getRangDia())) != null) return error;
		for (Assignatura a : ipe.getAssignatures()) {
			if ((error = cd.CrearAssignatura(ipe.getNom(), a.getNom())) != null) return error;
			if ((error = cd.ModificarAssignatura(ipe.getNom(), a.getNom(), null)) != null) return error;
		}
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
}
