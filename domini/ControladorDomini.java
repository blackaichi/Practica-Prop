package domini;

import domini.classes.*;
import java.util.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 * @autor hector.morales.carnice@est.fib.upc.edu
 *
 */
public final class ControladorDomini {

	static public HashSet<String> campusPresents(){
		return Campus.getKeys();
	}
	
	static public HashSet<String> plansEstudisPresents(){
		return PlaEstudis.getKeys();
	}
	
	static public String CrearCampus(String campus) {
		try {
			Campus.newCampus(campus);
			
		} catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String EliminarCampus(String campus) {
		Campus.eliminarCampus(campus);
		return null;
	}
	
	static public String ModificarCampus(String campus, String nom, String autor) {
		try {
			Campus toUpdate = Campus.getCampus(campus);
			
			int checker = 0;
			if((nom != null && (checker = toUpdate.setNom(nom)) != 0) ||
			   (autor != null && (checker = toUpdate.setAutor(autor)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
		
	}
	
	static public String CrearAula(String campus, String aula, int capacitat) {
		try {			
			Campus.getCampus(campus).altaAula(aula, capacitat);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String EliminarAula(String campus, String aula) {
		if(Campus.getCampus(campus) == null) return "El campus no existeix";
		Campus.getCampus(campus).baixaAula(aula);
		return null;
	}
	
	static public String ModificarAula(String campus, String aula, String nom, int capacitat) {
		try {
			Aula toUpdate = Campus.getCampus(campus).getAula(aula);
			
			int checker = 0;
			if((nom != null && (checker = toUpdate.setNom(nom)) != 0) ||
			   (capacitat > 0 && (checker = toUpdate.setCapacitat(capacitat)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
		
	}
	
	static public String CrearPlaEstudis(String plaEstudis) {
		try {
			PlaEstudis.newPlaEstudis(plaEstudis);
			
		} catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String EliminaPlaEstudis(String plaEstudis) {
		PlaEstudis.eliminaPlaEstudis(plaEstudis);
		return null;
	}
	
	static public String ModificarPlaEstudis(String plaEstudis, String nom, String autor) {
		try {
			PlaEstudis toUpdate = PlaEstudis.getPlaEstudis(plaEstudis);
			
			int checker = 0;
			if((nom != null && (checker = toUpdate.setNom(nom)) != 0) ||
			   (autor != null && (checker = toUpdate.setAutor(autor)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
		
	}
	
	static public String CrearAssignatura(String plaEstudis, String assignatura) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).altaAssignatura(assignatura);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	static public String EliminarAssignatura(String plaEstudis, String assignatura) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).baixaAssignatura(assignatura);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	static public String ModificarAssginatura(String plaEstudis, String assignatura, String nom) {
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
	
	static public String CrearGrup(String plaEstudis, String assignatura, int grup, int capacitat) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaGrup(grup, capacitat, "MT");
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String EliminarGrup(String plaEstudis, String assignatura, int grup) {
		PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaGrup(grup);
		return null;
	}
	
	static public String ModificarGrup(String plaEstudis, String assignatura, int grup, int numero, int places, String franja) {
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
	
	static public String CrearSubGrup(String plaEstudis, String assignatura, int grup, int subGrup, int places, boolean force) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).altaSubGrup(subGrup, places, force);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String EliminaSubGrup(String plaEstudis, String assignatura, int grup, int subGrup) {
		PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).baixaSubGrup(subGrup);
		return null;
	}
	
	static public String ModificarSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, int numero, int places, boolean incr) {
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
	
	static public String CrearSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaSessioG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String EliminaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaSessioG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	static public String ModificarSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> material) {
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
	
	static public String CrearSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaSessioSG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String EliminaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaSessioSG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String ModificarSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> material) {
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
	
	static public String AssignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores).assignaSessio(grup);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	static public String DesassignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		try {
			SessioGAssignada sessio = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSessio(tipus, hores);
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores).desassignaSessio(sessio);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String AssignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores).assignaSessio(grup, subgrup);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String DesassignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		try {
			SessioSGAssignada sessio = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).getSessio(tipus, hores);
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores).desassignaSessio(sessio);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String HoresAptes(String plaEstudis, String assignatura, int grup, int subgrup, Map<Integer, int[]> franja, boolean apte, boolean force) {
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

	static public String SetSolapamentAssig(String plaEstudis, String assignatura, String assigToRegister, boolean permet) {
		try {
			Assignatura assig = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assigToRegister);
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).setSolapament(assig, permet);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String SetSolapamentGrup(String plaEstudis, String assignatura, int grup, String assigToRegister, int numToRegister, boolean permet) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).setSolapament(assigToRegister, numToRegister, permet);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	static public String SetSolapamentSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, String assigToRegister, int numToRegister, boolean permet) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).setSolapament(assigToRegister, numToRegister, permet);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
}
