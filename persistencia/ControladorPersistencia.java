package persistencia;

import java.util.*;

import domini.*;
import domini.classes.*;
import domini.restriccions.*;
import persistencia.exports.*;
import persistencia.imports.*;
import utils.*;

public final class ControladorPersistencia {
	
	ControladorPersistencia cp = new ControladorPersistencia();
	
	ControladorDomini cd = ControladorDomini.getInstancia();
	
	public ControladorPersistencia getInstancia() {
		return cp;
	}
	
	private String error;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  EXPORTS  //////////////////////////////////////
	
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
	
	public String exportaSessioSubGrup(SubGrup sg) {
		try {
			ExportaSubGrup subGrup = ExportaSubGrup.getInstancia();
			subGrup.exportaSubGrup(sg, true);
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
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
	
	public String importaAssignatura(String path, PlaEstudis pe) {
		return ImportaAssignatura.importaAssignatura(path, pe);
	}
	
	public String importaAula(String path, Campus c) {
		return ImportaAula.importaAula(path, c);
	}
	
	public String importaCampus(String path) {
		return ImportaCampus.importaCampus(path);
	}
	
	public String importaData(String path) {
		return ImportaData.importaData(path);
	}
	
	public String importaGrup(String path, PlaEstudis pe, Assignatura a) {
		return ImportaGrup.importaGrup(path, pe, a);
	}
	
	public String importaHorari(String path, PlaEstudis pe, Campus c) {
		return ImportaHorari.importaHoraris(path, pe, c);
	}
	
	public String importaHoresAptes(String path, PlaEstudis pe) {
		return ImportaHoresAptes.importaHoresAptes(path, pe);
	}
	
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
	
	public String importaSessioGAssignada(String path, PlaEstudis pe, Assignatura a, Grup g) {
		return ImportaSessioGAssignada.importaSessioGAssignada(path, pe, a, g);
	}
	
	public String importaSessioGrup(String path, PlaEstudis pe, Assignatura a) {
		return ImportaSessioGrup.importaSessioGrup(path, pe, a);
	}
	
	public String importaSessioSGAssignada(String path, PlaEstudis pe, Assignatura a, Grup g, SubGrup sg) {
		return ImportaSessioSGAssignada.importaSessioSGAssignada(path, pe, a, g, sg);
	}
	
	public String importaSessioSubGrup(String path, PlaEstudis pe, Assignatura a) {
		return ImportaSessioSubGrup.importaSessioSubGrup(path, pe, a);
	}
	
	public String importaSubGrup(String path, PlaEstudis pe, Assignatura a, Grup g) {
		return ImportaSubGrup.importaSubGrup(path, pe, a, g);
	}
	
	public String importaSolapaments(String path, PlaEstudis pe, Assignatura a, Grup g, SubGrup sg) {
		return ImportaSolapaments.importaSolapaments(path, pe, a, g, sg);
	}
}
