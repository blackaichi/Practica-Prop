package presentacio;

import java.util.*;

import domini.ControladorDomini;
import persistencia.ControladorPersistencia;
import presentacio.vistes.*;
import utils.Estructura;
import utils.Pair;

/**
 * 
 * @author adria.manero@est.fib.upc.edu
 *
 */
public class ControladorPresentacio {

	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  INSTANCIA  /////////////////////////////////////
	
	private static ControladorPresentacio current;
	
	public static ControladorPresentacio getInstance() {
		if(current == null) current = new ControladorPresentacio();
		return current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////  ACCIONS  ////////////////////////////////////
	
	public HashSet<String> getAllCampus() {
		return ControladorDomini.getInstance().campusPresents();
	}
	
	public String GetMainCampusData(String campus) {
		return ControladorDomini.getInstance().GetMainCampusData(campus);
	}
	
	public HashSet<String> getAllAules(String campus) {
		return ControladorDomini.getInstance().aulesPresents(campus);
	}
	
	public ArrayList<String> GetMainAulaData(String campus, String aula) {
		return ControladorDomini.getInstance().GetMainAulaData(campus, aula);
	}
	
	public HashSet<String> getAllPlaEstudis() {
		return ControladorDomini.getInstance().plansEstudisPresents();
	}
	
	public String GetMainPlaEstudisData(String plaEstudis) {
		return ControladorDomini.getInstance().GetMainPlaEstudisData(plaEstudis);
	}
	
	public HashSet<String> getAllAssignatures(String plaEstudis) {
		return ControladorDomini.getInstance().assignaturesPresents(plaEstudis);
	}
	
	public HashSet<String> getAllGrups(String plaEstudis, String assignatura){
		return ControladorDomini.getInstance().grupsPresents(plaEstudis, assignatura);
	}
	
	public ArrayList<String> GetMainGrupData(String plaEstudis, String assignatura, int grup) {
		return ControladorDomini.getInstance().GetMainGrupData(plaEstudis, assignatura, grup);
	}
	
	public HashSet<String> getAllSessions(String plaEstudis, String assignatura){
		return ControladorDomini.getInstance().sessionsPresents(plaEstudis, assignatura);
	}
	
	public ArrayList<String> GetMainSessioData(String plaEstudis, String assignatura, String tipus, int hores, boolean deGrup) {
		return ControladorDomini.getInstance().GetMainSessioData(plaEstudis, assignatura, tipus, hores, deGrup);
	}
	
	public HashSet<String> getAllSubGrups(String plaEstudis, String assignatura, int grup){
		return ControladorDomini.getInstance().subgrupsPresents(plaEstudis, assignatura, grup);
	}
	
	public ArrayList<String> GetMainSubGrupData(String plaEstudis, String assignatura, int grup, int subgrup) {
		return ControladorDomini.getInstance().GetMainSubGrupData(plaEstudis, assignatura, grup, subgrup);
	}
	
	public Map<Integer, boolean[]> getHorizon(String plaEstudis, String assignatura, int grup, int subgrup){
		return ControladorDomini.getInstance().getHorizon(plaEstudis, assignatura, grup, subgrup);
	}
	
	public void CrearCampus(String campus) {
		String s = ControladorDomini.getInstance().CrearCampus(campus);
		if(s != null) Main.getInstance().showWarning("Error crear Campus",s);
	}
	
	public void EliminarCampus(String campus) {
		ControladorDomini.getInstance().EliminarCampus(campus);
	}
	
	public void ModificarCampus(String campus, String nom, String autor) {
		String s = ControladorDomini.getInstance().ModificarCampus(campus,nom,autor);
		if(s != null) Main.getInstance().showWarning("Error modificar de Campus",s);
	}
	
	public void CrearAula(String campus, String aula, int capacitat) {
		String s = ControladorDomini.getInstance().CrearAula(campus,aula,capacitat);
		if(s != null) Main.getInstance().showWarning("Error crear Aula",s);
	}
	
	public void EliminarAula(String campus, String aula) {
		String s = ControladorDomini.getInstance().EliminarAula(campus,aula);
		if(s != null) Main.getInstance().showWarning("Error eliminar Aula",s);
	}
	
	public void ModificarAula(String campus, String aula, String nom, int capacitat, HashSet<String> equip) {
		String s = ControladorDomini.getInstance().ModificarAula(campus,aula,nom,capacitat, equip);
		if(s != null) Main.getInstance().showWarning("Error modificar Aula",s);
	}
	
	public void CrearPlaEstudis(String plaEstudis) {
		String s = ControladorDomini.getInstance().CrearPlaEstudis(plaEstudis);
		if(s != null) Main.getInstance().showWarning("Error crear Pla d'Estudis",s);
	}
	
	public void EliminaPlaEstudis(String plaEstudis) {
		ControladorDomini.getInstance().EliminaPlaEstudis(plaEstudis);
	}
	
	public void ModificarPlaEstudis(String plaEstudis, String nom, String autor, Map<Integer, boolean[]> lectiu, int[] rang) {
		String s = ControladorDomini.getInstance().ModificarPlaEstudis(plaEstudis,nom,autor, lectiu, rang);
		if(s != null) Main.getInstance().showWarning("Error modificar Pla d'Estudis",s);
		
	}
	
	public void CrearAssignatura(String plaEstudis, String assignatura) {
		String s = ControladorDomini.getInstance().CrearAssignatura(plaEstudis,assignatura);
		if(s != null) Main.getInstance().showWarning("Error crear Assignatura",s);
	}

	public void EliminarAssignatura(String plaEstudis, String assignatura) {
		String s = ControladorDomini.getInstance().EliminarAssignatura(plaEstudis,assignatura);
		if(s != null) Main.getInstance().showWarning("Error eliminar Assignatura",s);
	}

	public void ModificarAssginatura(String plaEstudis, String assignatura, String nom) {
		String s = ControladorDomini.getInstance().ModificarAssignatura(plaEstudis,assignatura,nom);
		if(s != null) Main.getInstance().showWarning("Error modificar Assignatura",s);
	}
	
	public void CrearGrup(String plaEstudis, String assignatura, int grup, int capacitat) {
		String s = ControladorDomini.getInstance().CrearGrup(plaEstudis,assignatura,grup,capacitat);
		if(s != null) Main.getInstance().showWarning("Error crear Grup",s);
	}
	
	public void EliminarGrup(String plaEstudis, String assignatura, int grup) {
		ControladorDomini.getInstance().EliminarGrup(plaEstudis,assignatura,grup);
	}
	
	public void ModificarGrup(String plaEstudis, String assignatura, int grup, int numero, int places, String franja) {
		String s = ControladorDomini.getInstance().ModificarGrup(plaEstudis,assignatura,grup,numero,places,franja);
		if(s != null) Main.getInstance().showWarning("Error modificar Grup",s);
		
	}
	
	public void CrearSubGrup(String plaEstudis, String assignatura, int grup, int subGrup, int places, boolean force) {
		String s = ControladorDomini.getInstance().CrearSubGrup(plaEstudis,assignatura,grup,subGrup,places,force);
		if(s != null) Main.getInstance().showWarning("Error crear SubGrup",s);
	}
	
	public void EliminaSubGrup(String plaEstudis, String assignatura, int grup, int subGrup) {
		ControladorDomini.getInstance().EliminaSubGrup(plaEstudis,assignatura,grup,subGrup);
	}
	
	public void ModificarSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, int numero, int places, boolean incr) {
		String s = ControladorDomini.getInstance().ModificarSubGrup(plaEstudis,assignatura,grup,subgrup,numero,places,incr);
		if(s != null) Main.getInstance().showWarning("Error modificar SubGrup",s);
	}
	
	public void CrearSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().CrearSessioGrup(plaEstudis,assignatura,tipus,hores);
		if(s != null) Main.getInstance().showWarning("Error crear SessioGrup",s);
	}
	
	public void EliminaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().EliminaSessioGrup(plaEstudis,assignatura,tipus,hores);
		if(s != null) Main.getInstance().showWarning("Error eliminar SessioGrup",s);
	}
	
	public void ModificarSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> equip) {
		String s = ControladorDomini.getInstance().ModificarSessioGrup(plaEstudis,assignatura,tipus,hores,newTipus,newHores,nsessions,equip);
		if(s != null) Main.getInstance().showWarning("Error modificar SessioSubGrup",s);
	}
	
	public void CrearSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().CrearSessioSubGrup(plaEstudis,assignatura,tipus,hores);
		if(s != null) Main.getInstance().showWarning("Error crear SessioSubGrup",s);
	}
	
	public void EliminaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().EliminaSessioSubGrup(plaEstudis,assignatura,tipus,hores);
		if(s != null) Main.getInstance().showWarning("Error eliminar SessioSubGrup",s);
	}
	
	public void ModificarSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> equip) {
		String s = ControladorDomini.getInstance().ModificarSessioSubGrup(plaEstudis,assignatura,tipus,hores,newTipus,newHores,nsessions,equip);
		if(s != null) Main.getInstance().showWarning("Error modificar SessioSubGrup",s);
	}
	
	public void AssignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		String s = ControladorDomini.getInstance().AssignaSessioGrup(plaEstudis,assignatura,tipus,hores,grup);
		if(s != null) Main.getInstance().showWarning("Error assignar SessioGrup",s);
	}

	public void DesassignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		String s = ControladorDomini.getInstance().DesassignaSessioGrup(plaEstudis,assignatura,tipus,hores,grup);
		if(s != null) Main.getInstance().showWarning("Error desassignar SessioGrup",s);
	}
	
	public void AssignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		String s = ControladorDomini.getInstance().AssignaSessioSubGrup(plaEstudis,assignatura,tipus,hores,grup,subgrup);
		if(s != null) Main.getInstance().showWarning("Error Assignar SessioSubGrup",s);
	}
	
	public void DesassignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		String s = ControladorDomini.getInstance().DesassignaSessioSubGrup(plaEstudis,assignatura,tipus,hores,grup,subgrup);
		if(s != null) Main.getInstance().showWarning("Error Desassignar SessioSubGrup",s);
	}
	
	public void HoresAptes(String plaEstudis, String assignatura, int grup, int subgrup, Map<Integer, int[]> franja, boolean apte, boolean force) {
		String s = ControladorDomini.getInstance().HoresAptes(plaEstudis,assignatura,grup,subgrup,franja,apte,force);
		if(s != null) Main.getInstance().showWarning("Error Hores Aptes",s);
	}

	public void SetSolapamentAssig(String plaEstudis, String assignatura, String assigToRegister, boolean permet) {
		String s = ControladorDomini.getInstance().SetSolapamentAssig(plaEstudis,assignatura,assigToRegister,permet);
		if(s != null) Main.getInstance().showWarning("Error Solapament Assignatura",s);
	}
	
	public void SetSolapamentGrup(String plaEstudis, String assignatura, int grup, String assigToRegister, int numToRegister, boolean permet) {
		String s = ControladorDomini.getInstance().SetSolapamentGrup(plaEstudis,assignatura,grup,assigToRegister,numToRegister,permet);
		if(s != null) Main.getInstance().showWarning("Error Solapament Grup",s);
	}
	
	public void SetSolapamentSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, String assigToRegister, int numToRegister, boolean permet) {
		String s = ControladorDomini.getInstance().SetSolapamentSubGrup(plaEstudis,assignatura,grup,subgrup,assigToRegister,numToRegister,permet);
		if(s != null) Main.getInstance().showWarning("Error Solapament SubGrup",s);
	}


////////////////////////////////////////////////////////////////////////////////
///////////////////////////////  FUNCIONS PERSISTENCIA  ////////////////////////
	
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////  EXPORTS  //////////////////////////////////////

	public void exportaAssignatura(String path, String plaEstudis, String assignatura) {
		String s = ControladorDomini.getInstance().exportaAssignatura(path, plaEstudis, assignatura, true);
		if(s != null) Main.getInstance().showWarning("Error exportar Assignatura",s);
	}
	
	public void exportaAula(String path, String campus, String aula) {
		String s = ControladorDomini.getInstance().exportaAula(path, aula, campus, true);
		if(s != null) Main.getInstance().showWarning("Error exportar Aula",s);
	}
	
	public void exportaCampus(String path, String campus) {
		String s = ControladorDomini.getInstance().exportaCampus(path, campus);
		if(s != null) Main.getInstance().showWarning("Error exportar Campus",s);
	}
	
	public void exportaGrup(String path, String plaEstudis, String assignatura, int numero) {
		String s = ControladorDomini.getInstance().exportaGrup(path, numero, assignatura, plaEstudis, true);
		if(s != null) Main.getInstance().showWarning("Error exportar Grup",s);
	}
	
	public void exportaHorari(String path, HashSet<String> flags, String plaEstudis, String campus, int iteracions) {
		String s = ControladorDomini.getInstance().exportaHorari(path, flags, campus, plaEstudis, iteracions);
		if(s != null) Main.getInstance().showWarning("Error exportar Horari",s);
	}
	
	public void exportaPlaEstudis(String path, String plaEstudis) {
		String s = ControladorDomini.getInstance().exportaPlaEstudis(path, plaEstudis);
		if(s != null) Main.getInstance().showWarning("Error exportar PlaEstudis",s);
	}
	
	public void exportaSessioGrup(String path, String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().exportaSessioGrup(path, plaEstudis, assignatura, tipus, hores, true);
		if(s != null) Main.getInstance().showWarning("Error exportar SessioGrup",s);
	}
	
	public void exportaSessioSubGrup(String path, String plaEstudis, String assignatura, String tipus, int hores) {
		String s = ControladorDomini.getInstance().exportaSessioSubGrup(path, plaEstudis, assignatura, tipus, hores, true);
		if(s != null) Main.getInstance().showWarning("Error exportar SessioSubGrup",s);
	}
	
	public void exportaSubGrup(String path, String plaEstudis, String assignatura, int grup, int subgrup) {
		String s = ControladorDomini.getInstance().exportaSubGrup(path, subgrup, grup, assignatura, plaEstudis, true);
		if(s != null) Main.getInstance().showWarning("Error exportar SessioSubGrup",s);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  IMPORTS  //////////////////////////////////////
	/*
	public void importaAssignatura(String path, PlaEstudis pe) {
		String s = ControladorPersistencia.getInstancia().importaAssignatura(path,pe);
		if(s != null) Main.getInstance().showWarning("Error importar Assignatura",s);
	}
	
	public void importaAula(String path, Campus c) {
		String s = ControladorPersistencia.getInstancia().importaAula(path,c);
		if(s != null) Main.getInstance().showWarning("Error importar Aula",s);
	}
	
	public void importaCampus(String path) {
		String s = ControladorPersistencia.getInstancia().importaCampus(path);
		if(s != null) Main.getInstance().showWarning("Error importar Campus",s);	
	}
	
	public void importaData(String path) {
		String s = ControladorPersistencia.getInstancia().importaData(path);
		if(s != null) Main.getInstance().showWarning("Error importar Data",s);
	}
	
	public void importaGrup(String path, PlaEstudis pe, Assignatura a) {
		String s = ControladorPersistencia.getInstancia().importaGrup(path,pe,a);
		if(s != null) Main.getInstance().showWarning("Error importar Grup",s);
	}
	
	public void importaHorari(String path, PlaEstudis pe, Campus c) {
		String s = ControladorPersistencia.getInstancia().importaHorari(path,pe,c);
		if(s != null) Main.getInstance().showWarning("Error importar Horari",s);
	}
	
	public void importaHoresAptes(String path, PlaEstudis pe) {
		String s = ControladorPersistencia.getInstancia().importaHoresAptes(path,pe);
		if(s != null) Main.getInstance().showWarning("Error importar HoresAptes",s);
	}
	
	public void importaPlaEstudis(String path) {
		String s = ControladorPersistencia.getInstancia().importaPlaEstudis(path);
		if(s != null) Main.getInstance().showWarning("Error importar PlaEstudis",s);
	}
		
	public void importaSessioGrup(String path, PlaEstudis pe, Assignatura a) {
		String s = ControladorPersistencia.getInstancia().importaSessioGrup(path,pe,a);
		if(s != null) Main.getInstance().showWarning("Error importar SessioGrup",s);
	}
	
	public void importaSessioSubGrup(String path, PlaEstudis pe, Assignatura a) {
		String s = ControladorPersistencia.getInstancia().importaSessioSubGrup(path,pe,a);
		if(s != null) Main.getInstance().showWarning("Error importar SessioSubGrup",s);
	}
	
	public void importaSubGrup(String path, PlaEstudis pe, Assignatura a, Grup g) {
		String s = ControladorPersistencia.getInstancia().importaSubGrup(path,pe,a,g);
		if(s != null) Main.getInstance().showWarning("Error importar SubGrup",s);
	}
	
	public void importaSolapaments(String path, PlaEstudis pe, Assignatura a, Grup g, SubGrup sg) {
		String s = ControladorPersistencia.getInstancia().importaSolapaments(path,pe,a,g,sg);
		if(s != null) Main.getInstance().showWarning("Error importar Solapaments",s);
	}

	*/
}
