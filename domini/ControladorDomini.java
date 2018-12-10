package domini;

import domini.classes.*;
import domini.restriccions.*;
import persistencia.ControladorPersistencia;
import persistencia.exports.ExportaSessioSGAssignada;
import persistencia.exports.ExportaSessioSubGrup;
import persistencia.exports.ExportaSolapaments;
import persistencia.exports.ExportaSubGrup;

import java.util.*;
import utils.*;
/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 * @autor hector.morales.carnice@est.fib.upc.edu
 * @author adria.manero@est.fib.upc.edu
 *
 */
public final class ControladorDomini {
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  INSTANCIA  /////////////////////////////////////
	
	private static ControladorDomini current;
	
	public static ControladorDomini getInstance() {
		if(current == null) current = new ControladorDomini();
		return current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  ACCIONS  /////////////////////////////////////
	
	public HashSet<String> campusPresents(){
		return Campus.getKeys();
	}
	
	public HashSet<String> aulesPresents(String campus){
		HashSet<Aula> aules = Campus.getCampus(campus).getAllAules();
		
		HashSet<String> allAules = new HashSet<>();
		for(Aula aula: aules) allAules.add(aula.getNom());
		
		return allAules;
	}
	
	public HashSet<String> plansEstudisPresents(){
		return PlaEstudis.getKeys();
	}
		
	public HashSet<String> assignaturesPresents(String plaEstudis){
		HashSet<Assignatura> assig = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatures();
		
		HashSet<String> allAssig = new HashSet<>();
		for(Assignatura assign: assig) allAssig.add(assign.getNom());
		
		return allAssig;
	}
	
	public String CrearCampus(String campus) {
		try {
			Campus.newCampus(campus);
			
		} catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String EliminarCampus(String campus) {
		Campus.eliminarCampus(campus);
		return null;
	}
	
	public String ModificarCampus(String campus, String nom, String autor) {
		try {
			Campus toUpdate = Campus.getCampus(campus);
			
			int checker = 0;
			if((nom != null && (checker = toUpdate.setNom(nom)) != 0) ||
			   (autor != null && !autor.isEmpty() && (checker = toUpdate.setAutor(autor)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
		
	}
	
	public String CrearAula(String campus, String aula, int capacitat) {
		try {			
			Campus.getCampus(campus).altaAula(aula, capacitat);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String EliminarAula(String campus, String aula) {
		if(Campus.getCampus(campus) == null) return "El campus no existeix";
		Campus.getCampus(campus).baixaAula(aula);
		return null;
	}
	
	public String ModificarAula(String campus, String aula, String nom, int capacitat, HashSet<String> equip) {
		try {
			Aula toUpdate = Campus.getCampus(campus).getAula(aula);
			
			int checker = 0;
			if((nom != null && (checker = toUpdate.setNom(nom)) != 0) ||
			   (capacitat > 0 && (checker = toUpdate.setCapacitat(capacitat)) != 0) ||
			   (equip != null && (checker = toUpdate.setEquip(equip)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
		
	}
	
	public String CrearPlaEstudis(String plaEstudis) {
		try {
			PlaEstudis.newPlaEstudis(plaEstudis);
			
		} catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String EliminaPlaEstudis(String plaEstudis) {
		PlaEstudis.eliminaPlaEstudis(plaEstudis);
		return null;
	}
	
	public String ModificarPlaEstudis(String plaEstudis, String nom, String autor, Map<Integer, boolean[]> lectiu, int[] rangDia) {
		try {
			PlaEstudis toUpdate = PlaEstudis.getPlaEstudis(plaEstudis);
			
			int checker = 0;
			if((nom != null && (checker = toUpdate.setNom(nom)) != 0) ||
			   (autor != null && !autor.isEmpty() && (checker = toUpdate.setAutor(autor)) != 0) ||
			   (lectiu != null && (checker = toUpdate.setLectiu(lectiu)) != 0) ||
			   (rangDia != null && (checker = toUpdate.setRangDia(rangDia)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		return null;
	}
	
	public String CrearAssignatura(String plaEstudis, String assignatura) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).altaAssignatura(assignatura);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	public String EliminarAssignatura(String plaEstudis, String assignatura) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).baixaAssignatura(assignatura);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	public String ModificarAssignatura(String plaEstudis, String assignatura, String nom) {
		try {
			Assignatura toUpdate = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura);
			
			int checker = 0;
			if((nom != null && (checker = toUpdate.setNom(nom)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
		
	}
	
	public String CrearGrup(String plaEstudis, String assignatura, int grup, int capacitat) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaGrup(grup, capacitat, "MT");
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String EliminarGrup(String plaEstudis, String assignatura, int grup) {
		PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaGrup(grup);
		return null;
	}
	
	public String ModificarGrup(String plaEstudis, String assignatura, int grup, int numero, int places, String franja) {
		try {
			Grup toUpdate = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup);
			
			int checker = 0;
			if((numero > 0 && (checker = toUpdate.setNumero(numero)) != 0) ||
			   (places > 0 && (checker = toUpdate.setPlaces(places)) != 0) ||
			   (franja != null && (checker = toUpdate.setFranja(franja)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
		
	}
	
	public String CrearSubGrup(String plaEstudis, String assignatura, int grup, int subGrup, int places, boolean force) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).altaSubGrup(subGrup, places, force);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String EliminaSubGrup(String plaEstudis, String assignatura, int grup, int subGrup) {
		PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).baixaSubGrup(subGrup);
		return null;
	}
	
	public String ModificarSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, int numero, int places, boolean incr) {
		try {
			SubGrup toUpdate = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup);
			
			int checker = 0;
			if((numero > 0 && (checker = toUpdate.setNumero(numero)) != 0) ||
			   (places > 0 && (checker = toUpdate.setPlaces(places, incr)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
		
	}
	
	public String CrearSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaSessioG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String EliminaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaSessioG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	public String ModificarSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> material) {
		try {
			SessioGrup toUpdate = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores);
			
			int checker = 0;
			if((newTipus != null && (checker = toUpdate.setTipus(newTipus)) != 0) ||
			   (newHores > 0 && (checker = toUpdate.setHores(newHores)) != 0) ||
			   (nsessions > 0 && (checker = toUpdate.setnsessions(nsessions)) != 0) ||
			   (material != null && (checker = toUpdate.setMaterial(material)) != 0))
				ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String CrearSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaSessioSG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String EliminaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaSessioSG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String ModificarSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> material) {
		try {
			SessioSubGrup toUpdate = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores);
			
			int checker = 0;
			if((newTipus != null && (checker = toUpdate.setTipus(newTipus)) != 0) ||
			   (newHores > 0 && (checker = toUpdate.setHores(newHores)) != 0) ||
			   (nsessions > 0 && (checker = toUpdate.setnsessions(nsessions)) != 0) ||
			   (material != null && (checker = toUpdate.setMaterial(material)) != 0))
				ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String AssignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores).assignaSessio(grup);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	public String DesassignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		try {
			SessioGAssignada sessio = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSessio(tipus, hores);
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores).desassignaSessio(sessio);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String AssignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores).assignaSessio(grup, subgrup);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String DesassignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		try {
			SessioSGAssignada sessio = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).getSessio(tipus, hores);
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores).desassignaSessio(sessio);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String HoresAptes(String plaEstudis, String assignatura, int grup, int subgrup, Map<Integer, int[]> franja, boolean apte, boolean force) {
		try {
			if(grup == 0 && subgrup == 0)
				PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).setHoresAptes(franja, apte, force);
			else if(subgrup == 0)
				PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).setHoresAptes(franja, apte, force);
			else
				PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).setHoresAptes(franja, apte, force);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	public String SetSolapamentAssig(String plaEstudis, String assignatura, String assigToRegister, boolean permet) {
		try {
			Assignatura assig = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assigToRegister);
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).setSolapament(assig, permet);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String SetSolapamentGrup(String plaEstudis, String assignatura, int grup, String assigToRegister, int numToRegister, boolean permet) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).setSolapament(assigToRegister, numToRegister, permet);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	public String SetSolapamentSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, String assigToRegister, int numToRegister, boolean permet) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).setSolapament(assigToRegister, numToRegister, permet);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
}
////////////////////////////////////////////////////////////////////////////////
///////////////////////////////  FUNCIONS PERSISTENCIA  ////////////////////////

////////////////////////////////////////////////////////////////////////////////
///////////////////////////////  EXPORTS  //////////////////////////////////////
	

	public String exportaAssignatura(String path, String plaEst, String nomA) {
		try {
			Assignatura a = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(nomA);
			
			HashSet<SessioGrup> SG = a.getSessionsG();
			HashSet<SessioSubGrup> SSG = a.getSessionsSG();
			HashSet<Grup> G = a.getGrups();
			
			HashSet<Pair<String,Integer> > sessionsGrup = new HashSet<Pair<String,Integer> >();
			HashSet<Pair<String,Integer> > sessionsSGrup = new HashSet<Pair<String,Integer> >();
			HashSet<Integer> grups = new HashSet<Integer>();

			for (SessioGrup sg : SG) {
				Pair<String,Integer> p = new Pair<String,Integer>();
				p.first = sg.getTipus();
				p.second = sg.getHores();
				sessionsGrup.add(p);
			}
			for (SessioSubGrup ssg : SSG) {
				Pair<String,Integer> p = new Pair<String,Integer>();
				p.first = ssg.getTipus();
				p.second = ssg.getHores();
				sessionsSGrup.add(p);
			}
			for (Grup g : G) {
				grups.add(g.getNumero());
			}
			Map<Integer, boolean[]> horesAptes = a.getHoresAptes().getHoresAptes();
			HashMap<String, HashSet<Integer>> solapaments = a.getSolapaments().getDisjuntes();
			ControladorPersistencia.getInstancia().exportaAssignatura(path,plaEst,nomA,sessionsGrup,sessionsSGrup,grups,horesAptes,solapaments);
			return null;
		}
		catch(Exception e) {
			return e.toString();
		}
		return null;
	}
	
	public String exportaAula(String path, String nomAula, String nomCampus) {
		try {
			Aula a = Campus.getCampus(nomCampus).getAula(nomAula);
			int capacitat = a.getCapacitat();
			HashSet<String> equipament = a.getEquip();
			ControladorPersistencia.getInstancia().exportaAula(path,nomAula,capacitat,equipament);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	public String exportaCampus(String path, String nomC) {
		try {
			Campus c = Campus.getCampus(nomC);

			String autor = c.getAutor();
			HashSet<Aula> aules = c.getAllAules();
			HashSet<String> allAules = new HashSet<String> ();
			
			for(Aula a : aules) {
				allAules.add(a.getNom());
			}
			ControladorPersistencia.getInstancia().exportaCampus(path,nomC,autor,allAules);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
		
	public String exportaGrup(String path,int numero, String assignatura, String plaEst) {
		try {
			Grup g = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(assignatura).getGrup(numero);
			String franja = g.getFranja();
			Integer places = g.getPlaces();
			HashSet<SubGrup> sg = g.getAllSubGrups();
			HashSet<SessioGAssignada> sga = g.getSessions();
			HashSet<Integer> allSubGrups = new HashSet<Integer>();
			for (SubGrup subg : sg) {
				allSubGrups.add(subg.getNumero());
			}
			Map<Integer, boolean[]> horesAptes = g.getRestriccioHoresAptes().getHoresAptes();
			HashMap<String, HashSet<Integer>> solapaments = g.getSolapaments().getDisjuntes();
			ControladorPersistencia.getInstancia().exportaCampus(path,plaEst,assignatura,numero,places,franja,allSubGrups,horesAptes,solapaments);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	public String exportaHorari(String path) { //TODO
		try {
			return null;
		}
		catch (Exception ex) {
			return ex.toString();
		}
	}
	
	public String exportaHoresAptes(String path, String plaEst, String assig, int grup, int subGrup) {
		try {
			HoresAptes ha;
			if (subGrup != 0) ha = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(assig).getGrup(grup).getSubGrup(subGrup).getRestriccioHoresAptes();
			else if (grup != 0) ha = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(assig).getGrup(grup).getRestriccioHoresAptes();
			else ha = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(assig).getHoresAptes();
			Map<Integer,boolean[]> franja = ha.getHoresAptes();
			ControladorPersistencia.getInstancia().exportaHoresAptes(path,franja);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	public String exportaPlaEstudis(String path, String plaEst) {
		try {
			PlaEstudis pe = PlaEstudis.getPlaEstudis(plaEst);
			String autor = pe.getAutor();
			Map<Integer, boolean[] > lectiu = pe.getLectiuSetmana();
			HashSet<Assignatura> assigs = pe.getAssignatures();
			HashSet<String> assignatures = new HashSet<String>();
			for(Assignatura a : assigs) {
				assignatures.add(a.getNom());
			}
			int[] rang = pe.getRang();
			ControladorPersistencia.getInstancia().exportaPlaEstudis(path,plaEst,autor,lectiu,assignatures,rang);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	public String exportaSessioGAssignada(String path, String nomG) { //TODO no se que he de exportar d'aqui
		try {
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	public String exportaSessioGrup(String path, String plaEst, String nomAssig, String tipus, Integer hores) {
		try {
			
			SessioGrup sg = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(nomAssig).getSessioG(tipus,hores);
			Pair<String,Integer> sessionsGrup = new Pair<String,Integer>();
			HashSet<String> equip = sg.getMaterial();
			int nsessions = sg.getnsessions();
			HashSet<SessioGAssignada> sga = sg.getAllSessionsGA();
			HashSet<Integer> ngrups = new HashSet<Integer>();
			for (SessioGAssignada sessio : sga) {
				ngrups.add(sessio.getGrup().getNumero());
			}
			ControladorPersistencia.getInstancia().exportaSessioGrup(path,plaEst,nomAssig,equip,hores,tipus,nsessions,ngrups);
			
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	public String exportaSessioSGAssignada(String path) { //TODO
		try {
			
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	public String exportaSessioSubGrup(String path, String plaEst, String nomAssig, String tipus, Integer hores) {
		try {
			
			SessioSubGrup ssg = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(nomAssig).getSessioSG(tipus, hores);
			HashSet<String> equip = ssg.getMaterial();
			int nsessions = ssg.getnsessions();
			HashSet<SessioSGAssignada> ssga = ssg.getAllSessionsSGA();
			HashSet<Integer> ngrups = new HashSet<Integer>();
			for (SessioSGAssignada sessio : ssga) {
				ngrups.add(sessio.getSubGrup().getNumero());
			}
			ControladorPersistencia.getInstancia().exportaSessioGrup(path,plaEst,nomAssig,equip,hores,tipus,nsessions,ngrups);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	public String exportaSubGrup(String path, int numeroSG,int numeroG, String assignatura, String plaEst) {
		try {
			SubGrup sg = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(assignatura).getGrup(numeroG).getSubGrup(numeroSG);
			Integer places = sg.getPlaces();
			Map<Integer, boolean[]> horesAptes = sg.getRestriccioHoresAptes().getHoresAptes();
			HashMap<String, HashSet<Integer>> solapaments = sg.getSolapaments().getDisjuntes();
			ControladorPersistencia.getInstancia().exportaSubGrup(path,plaEst,assignatura,numeroG,numeroSG,places,horesAptes,solapaments);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	public String exportaSolapaments(Solapaments s) { //TODO
		try {
			
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
}