package domini;

import domini.classes.*;
import domini.restriccions.*;
import domini.tools.Estructura;
import domini.tools.Segment;
import persistencia.ControladorPersistencia;
import presentacio.vistes.Main;

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
	
	/**
	 * Retorna la instancia corrent del controlador.
	 * @return Instancia de la classe.
	 */
	public static ControladorDomini getInstance() {
		if(current == null) current = new ControladorDomini();
		return current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  ACCIONS  /////////////////////////////////////
	/**
	 * Afegeix una estructura buida al map d'horaris.
	 * @param plaEstudis Pla d'estudis del qual serà l'horari.
	 * @param campus Campus del qual serà l'horari.
	 * @return Iteració dins del set on s'ubica l'estructura generada.
	 */
	public int generarEntorn(String plaEstudis, String campus) {
		return Horari.getInstance().generarEntorn(plaEstudis, campus);
	}
	
	/**
	 * Proporciona un set de tots els fitxer presents en una ubicació conreta.
	 * @param path Direcció de la qual se'n volen obtenir els fitxers.
	 * @return Un set amb contingut o sense.
	 */
	public HashSet<String> fitxersAt(String path){
		return ControladorPersistencia.getInstancia().llistaFitxers(path);
	}
	
	/**
	 * Indica el total d'horaris que hi ha generats.
	 * @param plaEstudis IDentifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @return Un enter superior o igual 0.
	 */
	public int getNHoraris(String plaEstudis, String campus) {
		try {
			return Horari.getInstance().getHoraris(plaEstudis, campus).size();
		}
		catch(Exception e) {
			return 0;
		}
	}
	
	/**
	 * Retorna tot els conjunts de grup / subgrup presents en una ssignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica a l'assignatura. 
	 * @return Un map buit o ple.
	 */
	public Map<Integer, HashSet<Integer>> getConjunts(String plaEstudis, String assignatura){
		try {
			Map<Integer, HashSet<Integer>> conjunts = new HashMap<Integer, HashSet<Integer>>();
			Assignatura assign = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura);
			for(Grup grup: assign.getGrups()) {
				if(!conjunts.containsKey(grup.getNumero())) conjunts.put(grup.getNumero(), new HashSet<Integer>());
				for(SubGrup subgrup: grup.getAllSubGrups()) conjunts.get(grup.getNumero()).add(subgrup.getNumero());
			}
			
			return conjunts;
		}
		catch(Exception e) {
			return new HashMap<Integer, HashSet<Integer>>();
		}
	}
	
	/**
	 * Retorna tots els conjunts existents dins d'un pla d'estudis.
	 * @param plaEstudis IDentifica al pla d'estudis.
	 * @return Un map ple o buit.
	 */
	public Map<String, HashSet<Integer>> getConjunts(String plaEstudis){
		Map<String, HashSet<Integer>> conjunts = new HashMap<String, HashSet<Integer>>();
		try {
			HashSet<Assignatura> assigs = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatures();
			for(Assignatura assig : assigs) {
				if(!conjunts.containsKey(assig.getNom())) conjunts.put(assig.getNom(), new HashSet<Integer>());
				for(Grup grup : assig.getGrups()) {
					conjunts.get(assig.getNom()).add(grup.getNumero());
					for(SubGrup subgrup : grup.getAllSubGrups()) conjunts.get(assig.getNom()).add(subgrup.getNumero());
				}
			}
			
			return conjunts;
		}
		catch(Exception e) {
			return conjunts;
		}
	}
	
	/**
	 * Proporciona tot el registre de solapaments de l'objecte indicat.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica a l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @return Un map ple o buit.
	 */
	public Map<String, HashSet<Integer>> getDisjuntes(String plaEstudis, String assignatura, int grup, int subgrup){
		try {
			if(subgrup > 0) return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).getSolapaments().getDisjuntes();
			else if(grup > 0) return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSolapaments().getDisjuntes();
			else if(assignatura != null) return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSolapaments().getDisjuntes();
			else return new HashMap<String, HashSet<Integer>>();
		}
		catch(Exception  e) {
			return new HashMap<String, HashSet<Integer>>();
		}
	}
	
	/**
	 * Retorna tots els solapaments registrats.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @return Un map amb contingut o sense.
	 */
	public Map<String, HashSet<Integer>> getSolapaments(String plaEstudis, String assignatura, int grup, int subgrup){
		try {
			if(subgrup > 0) return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).getSolapaments().getDisjuntes();
			else if(grup > 0) return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSolapaments().getDisjuntes();
			else return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSolapaments().getDisjuntes();
		}
		catch(Exception e) {
			return new HashMap<>();
		}
	}
	
	/**
	 * Proporciona totes les assignacins d'una sessió
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param sessioGrup Indica si és o no una sessio de grup.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<Integer> getAssignades(String plaEstudis, String assignatura, String tipus, int hores, boolean sessioGrup){
		try {
			HashSet<Integer> assignacions = new HashSet<Integer>();
			if(sessioGrup) {
				HashSet<SessioGAssignada> assigs = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores).getAllSessionsGA();
				for(SessioGAssignada sessio: assigs) assignacions.add(sessio.getGrup().getNumero());
			}
			else {
				HashSet<SessioSGAssignada> assigs = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores).getAllSessionsSGA();
				for(SessioSGAssignada sessio: assigs) assignacions.add(sessio.getSubGrup().getNumero());
			}
			
			return assignacions;
		}
		catch(Exception e) {
			return new HashSet<Integer>();
		}
	}
	
	/**
	 * Retorna tots el campus existents.
	 * @return  Un set amb contingut o sense.
	 */
	public HashSet<String> campusPresents(){
		return Campus.getKeys();
	}
	
	/**
	 * Retorna tota la informació important d'un campus.
	 * @param campus Identifica al campus.
	 * @return  Un string amb contingut o sense.
	 */
	public String GetMainCampusData(String campus) {
		try {
			Campus toGet = Campus.getCampus(campus);
			return toGet.getAutor();
		}
		catch(Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * Retorna totes les aules presents en un campus.
	 * @param campus Identifica al campus.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> aulesPresents(String campus){
		HashSet<Aula> aules = Campus.getCampus(campus).getAllAules();
		
		HashSet<String> allAules = new HashSet<>();
		for(Aula aula: aules) allAules.add(aula.getNom());
		
		return allAules;
	}
	
	/**
	 * Retorna tota la informació import d'una aula.
	 * @param campus Identifica al campus.
	 * @param aula Identifica al aula.
	 * @return  Una llista amb contingut o sense.
	 */
	public ArrayList<String> GetMainAulaData(String campus, String aula) {
		try {
			ArrayList<String> data = new ArrayList<>();
			Aula toGet = Campus.getCampus(campus).getAula(aula);
			
			data.add(String.valueOf(toGet.getCapacitat()));
			for(String equip : toGet.getEquip()) data.add(equip);
			return data;
		
		}
		catch(Exception e) {
			return null;
		}	
	}
	
	/**
	 * Retorna tots els plans d'estudis existents.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> plansEstudisPresents(){
		return PlaEstudis.getKeys();
	}
		
	/**
	 * Retorna la informació primordial d'un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @return Un string amb contingut o sense.
	 */
	public String GetMainPlaEstudisData(String plaEstudis) {
		try {
			PlaEstudis toGet = PlaEstudis.getPlaEstudis(plaEstudis);
			return toGet.getAutor();
		}
		catch(Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * Retorna el total d'assignatures existents en un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> assignaturesPresents(String plaEstudis){
		HashSet<Assignatura> assig = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatures();
		
		HashSet<String> allAssig = new HashSet<>();
		for(Assignatura assign: assig) allAssig.add(assign.getNom());
		
		return allAssig;
	}
	
	/**
	 * Retorna el total del grups existents en una ssignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @return  Un hashset amb contingut o sense.
	 */
	public HashSet<String> grupsPresents(String plaEstudis, String assignatura){
		HashSet<Grup> grups = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrups();
		
		HashSet<String> allGrups = new HashSet<>();
		for(Grup grup: grups) allGrups.add(String.valueOf(grup.getNumero()));
		
		return allGrups;
	}
	
	/**
	 * Retorna tota la informació primordial d'un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @return Una list amb contingut o sense.
	 */
	public ArrayList<String> GetMainGrupData(String plaEstudis, String assignatura, int grup) {
		try {
			ArrayList<String> data = new ArrayList<>();
			Grup toGet = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup);
			
			data.add(String.valueOf(toGet.getPlaces()));
			data.add(String.valueOf(toGet.getFranja()));
			return data;
		
		}
		catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Retorna totes les sessions existents.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> sessionsPresents(String plaEstudis, String assignatura){
		HashSet<SessioGrup> sessionsGrup = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessionsG();
		HashSet<SessioSubGrup> sessionsSubGrup = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessionsSG();
		
		HashSet<String> allSessions = new HashSet<>();
		for(SessioGrup sessio: sessionsGrup) allSessions.add("[G] ".concat(sessio.getTipus()).concat(" - ").concat(String.valueOf(sessio.getHores())));
		for(SessioSubGrup sessio: sessionsSubGrup) allSessions.add("[S] ".concat(sessio.getTipus()).concat(" - ").concat(String.valueOf(sessio.getHores())));
		
		return allSessions;
	}
	
	/**
	 * Retorna tota la informació primordial d'una sessió.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param deGrup Indica si la sessio es de grup.
	 * @return Un hashset amb contingut o sense.
	 */
	public ArrayList<String> GetMainSessioData(String plaEstudis, String assignatura, String tipus, int hores, boolean deGrup) {
		try {
			ArrayList<String> data = new ArrayList<>();
			if(deGrup) {
				SessioGrup toGet = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores);
				data.add(String.valueOf(toGet.getnsessions()));
				for(String equip : toGet.getMaterial()) data.add(equip);
			}
			else {
				SessioSubGrup toGet = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores);
				data.add(String.valueOf(toGet.getnsessions()));
				for(String equip : toGet.getMaterial()) data.add(equip);
			}
			
			return data;
		
		}
		catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Retorna tots els subgrups existents en un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @return Un hashset amb contingut o sense.
	 */
	public HashSet<String> subgrupsPresents(String plaEstudis, String assignatura, int numero){
		HashSet<SubGrup> subgrups = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(numero).getAllSubGrups();
		
		HashSet<String> allSubGrups = new HashSet<>();
		for(SubGrup subgrup: subgrups) allSubGrups.add(String.valueOf(subgrup.getNumero()));
		
		return allSubGrups;
	}
	
	/**
	 * Retorna tota la informació primordial d'un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @return Una list amb contingut o sense.
	 */
	public ArrayList<String> GetMainSubGrupData(String plaEstudis, String assignatura, int grup, int subgrup) {
		try {
			ArrayList<String> data = new ArrayList<>();
			SubGrup toGet = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup);
			
			data.add(String.valueOf(toGet.getPlaces()));
			return data;
		
		}
		catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * Retorna el mapa d'hores aptes.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @return Un map amb contingut o sense.
	 */
	public Map<Integer, boolean[]> getHorizon(String plaEstudis, String assignatura, int grup, int subgrup){
		if(subgrup > 0) return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).getRestriccioHoresAptes().getHoresAptes();
		else if(grup > 0) return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getRestriccioHoresAptes().getHoresAptes();
		else if(assignatura != null) return PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getHoresAptes().getHoresAptes();
		else if(plaEstudis != null) return PlaEstudis.getPlaEstudis(plaEstudis).getLectiuSetmana();
		
		return null;
	}
	
	/**
	 * Retorna el marge que compon l'horari.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param iter Indica sobre quina iteració treballar.
	 * @return Un pair de pairs amb contingut o sense.
	 */
	public Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> getMarginHorari(String plaEstudis, String campus, int iter){
		try {
			Iterator<Estructura> iterator = Horari.getInstance().getHoraris(plaEstudis, campus).iterator();
			while(--iter > 0) iterator.next();
			
			Estructura horari = iterator.next();
			int minx = 99, maxx = -1, miny = 99, maxy = -1;
			
			for(int dia = 0; dia < 7; dia++) {
				for(int hora = 0; hora < 24; hora++) {
					if(!horari.getAllSegments(dia, hora).isEmpty()) {
						minx = Math.min(minx, dia);
						maxx = Math.max(maxx, dia);
						miny = Math.min(miny, hora);
						maxy = Math.max(maxy, hora);
					}
				}
			}
			
			return new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(new Pair<Integer, Integer>(minx, maxx), new Pair<Integer, Integer>(miny, maxy));
		}
		catch(Exception e) {
			return new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(new Pair<Integer, Integer>(), new Pair<Integer, Integer>());
		}
	}
	
	public HashSet<ArrayList<String>> getSegments(String plaEstudis, String campus, int dia, int hora, int iter){
		HashSet<ArrayList<String>> dades = new HashSet<ArrayList<String>>();
		
		try {
			HashSet<Estructura> horaris = Horari.getInstance().getHoraris(plaEstudis, campus);
			if(iter < 1 || iter > horaris.size()) return dades;
			
			Iterator<Estructura> iterator = horaris.iterator();
			while(iterator.hasNext() && 1 < iter--) iterator.next();
			
			Estructura horari = iterator.next();
			HashSet<Segment> segments = horari.getAllSegments(dia, hora);
			
			for(Segment segment : segments) {
				ArrayList<String> list = new ArrayList<String>();
				String assignatura = segment.getSessio().snull()? segment.getSessio().first.getGrup().getAssignatura().getNom() :
																  segment.getSessio().second.getSubGrup().getGrup().getAssignatura().getNom();
				
				String numero = String.valueOf(segment.getSessio().snull()? segment.getSessio().first.getGrup().getNumero() :
														  					segment.getSessio().second.getSubGrup().getNumero());
				
				String sessio = segment.getSessio().snull()? segment.getSessio().first.getSessioGrup().getTipus() : 
															 segment.getSessio().second.getSessioSubGrup().getTipus();
				
				String durada = String.valueOf(segment.getSessio().snull()? segment.getSessio().first.getSessioGrup().getHores() : 
																			segment.getSessio().second.getSessioSubGrup().getHores());
				
				String horaIni = String.valueOf(segment.getData().getHora());
				
				String aula = segment.getAula().getNom();
				
				String conjunt = segment.getSessio().snull()? "G" : "S";
				
				list.add(assignatura);	//Nom de l'assignatura
				list.add(conjunt);		//Identifica si es sessio de grup o subgrup
				list.add(numero);		//Numero id del grup o subgrup
				list.add(sessio);		//Tipus de la sessio
				list.add(horaIni);		//Indica en quina hora comença la sessio
				list.add(aula);			//Aula en la que es du a terme la sessio
				list.add(durada);		//Indica quanta estona dura la sessio
				
				dades.add(list);
			}
		}
		catch(Exception e) {
			return dades;
		}
		
		return dades;
	}
	
	/**
	 * Genera n horaris.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param nhoraris Indica el total d'horaris a generar.
	 * @param flags Proporciona els flags a tenir en compte
	 * @param purge Indica si es vol purgar els horaris antics o no.
	 * @return Total d'horaris generats.
	 */
	public String generarHorari(String plaEstudis, String campus, int nhoraris, HashSet<String> flags, boolean purge) {
		try {
			PlaEstudis pl = PlaEstudis.getPlaEstudis(plaEstudis);
			Campus camp = Campus.getCampus(campus);
			
			Horari.getInstance().GENERADOR(pl, camp, flags, nhoraris, purge);
			return String.valueOf(Horari.getInstance().getHoraris(plaEstudis, campus).size());
		}
		catch(Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * Dona d'alta un campus.
	 * @param campus Identifica al campus.
	 */
	public String CrearCampus(String campus) {
		try {
			Campus.newCampus(campus);
			
		} catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Esborra un campus
	 * @param campus Identifica al campus.
	 */
	public String EliminarCampus(String campus) {
		Campus.eliminarCampus(campus);
		return null;
	}
	
	/**
	 * Modifica un campus
	 * @param campus Identifica al campus.
	 * @param nom Nou nom del campus.
	 * @param autor Nom de l'autor del campus.
	 */
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
	
	/**
	 * Dona d'alta una aula
	 * @param campus Identifica al campus.
	 * @param aula Identifica l'aula.
	 * @param capacitat Indica la capacitat de l'aula.
	 */
	public String CrearAula(String campus, String aula, int capacitat) {
		try {			
			Campus.getCampus(campus).altaAula(aula, capacitat);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Esborra una aula.
	 * @param campus Identifica al campus.
	 * @param aula Identifica l'aula.
	 */
	public String EliminarAula(String campus, String aula) {
		if(Campus.getCampus(campus) == null) return "El campus no existeix";
		Campus.getCampus(campus).baixaAula(aula);
		return null;
	}
	
	/**
	 * Modifica una aula.
	 * @param campus Identifica al campus.
	 * @param aula Identifica l'aula.
	 * @param nom Nou nom de l'aula.
	 * @param capacitat Nova capacitat de l'aula.
	 * @param equip Equip present a l'aula.
	 */
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
	
	/**
	 * Dona d'alta un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 */
	public String CrearPlaEstudis(String plaEstudis) {
		try {
			PlaEstudis.newPlaEstudis(plaEstudis);
		} catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Esborra un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 */
	public String EliminaPlaEstudis(String plaEstudis) {
		PlaEstudis.eliminaPlaEstudis(plaEstudis);
		return null;
	}
	
	/**
	 * Modifica un pla d'estudis.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param nom Nou nom del pla d'estudis.
	 * @param autor Nom de l'autor del pla d'estudis.
	 * @param lectiu Horari lectiu del pla.
	 * @param rang Rang horari del pla.
	 */
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
	
	/**
	 * Dona d'alta una assignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 */
	public String CrearAssignatura(String plaEstudis, String assignatura) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).altaAssignatura(assignatura);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	/**
	 * Esborra una assignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 */
	public String EliminarAssignatura(String plaEstudis, String assignatura) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).baixaAssignatura(assignatura);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	/**
	 * Modifica una assignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param nom Nou nom de l'assignatura.
	 */
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
	
	/**
	 * Dona d'alta un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param capacitat Capacitat del grup.
	 */
	public String CrearGrup(String plaEstudis, String assignatura, int grup, int capacitat) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaGrup(grup, capacitat, "MT");
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Esborra un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 */
	public String EliminarGrup(String plaEstudis, String assignatura, int grup) {
		PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaGrup(grup);
		return null;
	}
	
	/**
	 * Modifica un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param numero Identifica al grup.
	 * @param places Noves places del grup.
	 * @param franja Nova franja del grup.
	 */
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
	
	/**
	 * Dona d'alta un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subGrup Identifica al subgrup.
	 * @param places Places del subgrup
	 * @param force Indica si cal adaptar el grup del subgrup a les places solicitades.
	 */
	public String CrearSubGrup(String plaEstudis, String assignatura, int grup, int subGrup, int places, boolean force) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).altaSubGrup(subGrup, places, force);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Esborra un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al subgrup.
	 * @param subGrup Identifica al subgrup.
	 */
	public String EliminaSubGrup(String plaEstudis, String assignatura, int grup, int subGrup) {
		PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).baixaSubGrup(subGrup);
		return null;
	}
	
	/**
	 * Modifica un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @param numero  Identifica al subgrup.
	 * @param places Nova quantitat de places.
	 * @param incr Indica si cal incrementar el total de places del grup.
	 */
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
	
	/**
	 * Dona d'alta una sessió de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public String CrearSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaSessioG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Esborra una sessio de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public String EliminaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaSessioG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	/**
	 * Modifica una sessio de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param newTipus Nou tipus de la sessió.
	 * @param newHores Nova quantitat d'hores.
	 * @param nsessions Nova quantita d'nsessions.
	 * @param equip Equip necessari a la sessió.
	 */
	public String ModificarSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> material) {
		try {
			SessioGrup toUpdate = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores);
			
			int checker = 0;
			if((newTipus != null && !newTipus.isEmpty() && (checker = toUpdate.setTipus(newTipus)) != 0) ||
			   (newHores > 0 && (checker = toUpdate.setHores(newHores)) != 0) ||
			   (nsessions > 0 && (checker = toUpdate.setnsessions(nsessions)) != 0) ||
			   (material != null && !material.isEmpty() && (checker = toUpdate.setMaterial(material)) != 0))
				return ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Dona d'alta una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public String CrearSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).altaSessioSG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Esborra una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 */
	public String EliminaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).baixaSessioSG(tipus, hores);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Esborra un horari.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param iter Indica sobre quina iteració treballar.
	 */
	public String EliminaHorari(String plaEstudis, String campus, int iter) {
		try {
			int iteration = iter;
			for(Estructura horari : Horari.getInstance().getHoraris(plaEstudis, campus)) {
				if(--iteration == 0) {
					Horari.getInstance().getHoraris(plaEstudis, campus).remove(horari);
					break;
				}
			}
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Modifica una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param newTipus Nou tipus de la sessió.
	 * @param newHores Nova quantitat d'hores.
	 * @param nsessions Nova quantitat d'nsessions.
	 * @param equip Equip necessari per a la sessió.
	 */
	public String ModificarSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, String newTipus, int newHores, int nsessions, HashSet<String> material) {
		try {
			SessioSubGrup toUpdate = PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores);
			
			int checker = 0;
			if((newTipus != null && !newTipus.isEmpty() && (checker = toUpdate.setTipus(newTipus)) != 0) ||
			   (newHores > 0 && (checker = toUpdate.setHores(newHores)) != 0) ||
			   (nsessions > 0 && (checker = toUpdate.setnsessions(nsessions)) != 0) ||
			   (material != null && !material.isEmpty() && (checker = toUpdate.setMaterial(material)) != 0))
				ExceptionManager.getException(checker);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Assigna una sessio de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 */
	public String AssignaSessioGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioG(tipus, hores).assignaSessio(grup);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}

	/**
	 * Desassigna una sessio de grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 */
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
	
	/**
	 * Assigna una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 */
	public String AssignaSessioSubGrup(String plaEstudis, String assignatura, String tipus, int hores, int grup, int subgrup) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getSessioSG(tipus, hores).assignaSessio(grup, subgrup);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Desassigna una sessio de subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param tipus Identifica al tipus de la sessió.
	 * @param hores Identifica a l'hora de la sessió.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 */
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
	
	/**
	 * Estableix les hores aptes.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @param franja Indica la franja.
	 * @param apte Senyala si la franja es apte o no.
	 * @param force Indica si cal forçar la fraja o no.
	 */
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

	/**
	 * Estableix els solapaments d'una ssignatura.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param assigToRegister Identifica l'assignatura a registrar.
	 * @param permet Indica si s'ha de permetre o no el solapament.
	 */
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
	
	/**
	 * Estableix els solpaments d'un grup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param assigToRegister Identifica l'assignatura del grup que es vol registrar.
	 * @param numToRegister Identifica al grup/subgrup que es vol registrar.
	 * @param permet Indica si pot o no solapar-se.
	 */
	public String SetSolapamentGrup(String plaEstudis, String assignatura, int grup, String assigToRegister, int numToRegister, boolean permet) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).setSolapament(assigToRegister, numToRegister, permet);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
	}
	
	/**
	 * Estableix els solapaments d'un subgrup.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param assignatura Identifica l'assignatura.
	 * @param grup Identifica al grup.
	 * @param subgrup Identifica al subgrup.
	 * @param assigToRegister Identifica l'assignatura del grup que es vol registrar.
	 * @param numToRegister Identifica al grup/subgrup que es vol registrar.
	 * @param permet Indica si pot o no solapar-se.
	 */
	public String SetSolapamentSubGrup(String plaEstudis, String assignatura, int grup, int subgrup, String assigToRegister, int numToRegister, boolean permet) {
		try {
			PlaEstudis.getPlaEstudis(plaEstudis).getAssignatura(assignatura).getGrup(grup).getSubGrup(subgrup).setSolapament(assigToRegister, numToRegister, permet);
		}
		catch(Exception e) {
			return e.toString();
		}
		
		return null;
}

	/**
	 * Modifica un horari.
	 * @param plaEstudis Identifica al pla d'estudis.
	 * @param campus Identifica al campus.
	 * @param iter Indica sobre quina iteració treballar.
	 * @param assig Identifica l'assignatura de la sessió a desplaçar.
	 * @param numero Identifica al grup/subgrup de la sessio.
	 * @param dia Indica a quin dia es troba.
	 * @param hora Indica a quina hora es troba la sessió.
	 * @param newDia Indica a quin dia desplaçar la sessió.
	 * @param newHora Indica a quina hora desplaçar.
	 * @param commit Indica si es vol fer el canvi definitiu.
	 * @param force Indica si es vol o no procedir al canvi tot i que sigui incongruent amb les restriccions de l'horari.
	 * @return Un set amb totes les restriccions violades al fer el canvi.
	 */
	public HashSet<String> ModificarHorari(String plaEstudis, String campus, int iter, String assignatura, int numero, int dia, int hora, int newDia, int newHora, boolean commit, boolean force){
		HashSet<String> warning = new HashSet<String>();
		
		try {
			Iterator<Estructura> iterator = Horari.getInstance().getHoraris(plaEstudis, campus).iterator();
			while(--iter > 0) iterator.next();
			
			Estructura horari = iterator.next();
			Segment segment = horari.getSegment(dia, hora, assignatura, numero);
			
			horari.purgeSegment(assignatura, numero, dia, hora);
			NSessions nSessions = NSessions.configure(horari);
			HashSet<Integer> result = Horari.getInstance().tryToCommit(horari, nSessions, segment, newDia, newHora, commit, force);
			if(!commit) Horari.getInstance().tryToCommit(horari, nSessions, segment, dia, hora, true, true);
			
			for(int code : result)
				warning.add(ExceptionManager.getException(code));
			
		}
		catch(Exception e) {
			warning.add(e.toString());
		}
		
		return warning;
	}
	public String crearSegment(String plaEst, String nomC, int dia, int hora, String aula, String nomA, String tipus, int hores, int numg, int numsg, int id) {
		try {
			SessioGAssignada sg = null;
			SessioSGAssignada ssg = null;
			HashSet<String> pe = PlaEstudis.getKeys();
			HashSet<String> c  = Campus.getKeys();
			System.out.println("ei_abans " + numg + " " + numsg);
			for(Grup ng : PlaEstudis.getPlaEstudis(plaEst).getAssignatura(nomA).getGrups()) System.out.print(ng.getNumero() + " ");
			System.out.print("\n");
			if (numsg == -1) {
				PlaEstudis pla = PlaEstudis.getPlaEstudis(plaEst);
				Assignatura ass = pla.getAssignatura(nomA);
				Grup gr = ass.getGrup(numg);
				sg = gr.getSessio(tipus, hores);
				
				//sg = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(nomA).getGrup(numg).getSessio(tipus, hores);
				System.out.println("ei_abans");
			}
			else ssg = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(nomA).getGrup(numg).getSubGrup(numsg).getSessio(tipus, hores);
			Aula a = Campus.getCampus(nomC).getAula(aula);
			Segment s = new Segment(sg, ssg);
			s.setAula(a);
			s.setData(new Data(dia,hora));
			Iterator<Estructura> iterator = Horari.getInstance().getHoraris(plaEst, nomC).iterator();
			int it = id;
			while(--it > 0) iterator.next();
			Estructura horari = iterator.next();
			horari.setSegment(s, dia, hora);
			System.out.println("aaaaa");
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		return null;
}

	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  FUNCIONS PERSISTENCIA  ////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  EXPORTS  //////////////////////////////////////

	/**
	 * Exporta una assignatura
	 * @param path Path on volem exportar l'Assignatura
	 * @param plaEst Nom del pla d'estudis
	 * @param nomA Nom de l'assignatura
	 * @param rec True si es vol truncar el fitxer, false si es vol fer un append
	 * @return
	 */
	public String exportaAssignatura(String path, String plaEst, String nomA, boolean rec) {
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
			
			ControladorPersistencia.getInstancia().exportaAssignatura(path,plaEst,nomA,sessionsGrup,sessionsSGrup,grups,horesAptes,solapaments, rec);
			return null;
		}
		catch(Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * Exporta un Aula
	 * @param path Path on volem exportar l'Aula
	 * @param nomAula Nom de l'aula
	 * @param nomCampus Nom del Campus
	 * @param rec True si es vol truncar el fitxer, false si es vol fer un append
	 * @return
	 */
	public String exportaAula(String path, String nomAula, String nomCampus, boolean rec) {
		try {
			Aula a = Campus.getCampus(nomCampus).getAula(nomAula);
			int capacitat = a.getCapacitat();
			HashSet<String> equipament = a.getEquip();
			ControladorPersistencia.getInstancia().exportaAula(path,nomAula,capacitat,equipament,rec);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * 
	 * @param path Path on volem exportar el Campus
	 * @param nomC Nom del campus
	 * @return
	 */
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
	
	/**
	 * 
	 * @param path Path on volem exportar el Grup
	 * @param numero Numero de grup
	 * @param assignatura Nom de l'assignatura
	 * @param plaEst Nom del Pla d'Estudis
	 * @param rec True si es vol truncar el fitxer, false si es vol fer un append
	 * @return
	 */
	public String exportaGrup(String path,int numero, String assignatura, String plaEst, boolean rec) {
		try {
			Grup g = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(assignatura).getGrup(numero);
			String franja = g.getFranja();
			Integer places = g.getPlaces();
			HashSet<SubGrup> sg = g.getAllSubGrups();
			HashSet<Integer> allSubGrups = new HashSet<Integer>();
			for (SubGrup subg : sg) {
				allSubGrups.add(subg.getNumero());
			}
			Map<Integer, boolean[]> horesAptes = g.getRestriccioHoresAptes().getHoresAptes();
			HashMap<String, HashSet<Integer>> solapaments = g.getSolapaments().getDisjuntes();
			ControladorPersistencia.getInstancia().exportaGrup(path,plaEst,assignatura,numero,places,franja,allSubGrups,horesAptes,solapaments,rec);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * 
	 * @param path Path on volem exportar l'Horari
	 * @param flags Flags corresponents al Horari
	 * @param nomC Nom del campus
	 * @param nomPE Nom del Pla d'Estudis
	 * @param id Iterador per exportar l'horari desitjat.
	 * @return
	 */
	public String exportaHorari(String path, String nomC, String nomPE, int id) {
		try {
			HashSet<Estructura> h = Horari.getInstance().getHoraris(nomPE, nomC);
			int it = id;
			HashSet<String> flags = new HashSet<String>();
			for(Estructura es : h) {
				if(it == 0) flags = es.getFlags();
				--it;
			}
			Campus c = Campus.getCampus(nomC);
			String autorC = c.getAutor();
			HashSet<String> aules = new HashSet<String>();
			HashSet<Aula> a = c.getAllAules();
			for(Aula au : a) {
				aules.add(au.getNom());
			}
			PlaEstudis pe = PlaEstudis.getPlaEstudis(nomPE);
			String autorPE =pe.getAutor();
			Map<Integer, boolean[] > lectiu = pe.getLectiuSetmana();
			HashSet<Assignatura> assigs = pe.getAssignatures();
			HashSet<String> assignatures = new HashSet<String>();
			for(Assignatura as : assigs) {
				assignatures.add(as.getNom());
			}
			int[] rang = pe.getRang();
			ControladorPersistencia.getInstancia().exportaHorari(path,flags,nomC,autorC,aules,nomPE,autorPE,lectiu,rang,assignatures,id);
			return null;
		}
		catch (Exception ex) {
			return ex.toString();
		}
	}
	
	/**
	 * 
	 * @param path Path on volem exportar el Pla d'estudis
	 * @param plaEst nom del Pla d'Estuds
	 * @return
	 */
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
			ControladorPersistencia.getInstancia().exportaPlaEstudis(path,plaEst,autor,lectiu,rang,assignatures);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * 
	 * @param path Path on volem exportar la SessioGrup
	 * @param plaEst Nom del Pla d'Estudis
	 * @param nomAssig Nom de l'Assignatura
	 * @param tipus Tipus de la sessio que volem exportar.
	 * @param hores Hores de la sessio que volem exportar.
	 * @param rec True si es vol truncar el fitxer, false si es vol fer un append
	 * @return
	 */
	public String exportaSessioGrup(String path, String plaEst, String nomAssig, String tipus, Integer hores, boolean rec) {
		try {
			
			SessioGrup sg = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(nomAssig).getSessioG(tipus,hores);
			HashSet<String> equip = sg.getMaterial();
			int nsessions = sg.getnsessions();
			HashSet<SessioGAssignada> sga = sg.getAllSessionsGA();
			HashSet<Integer> ngrups = new HashSet<Integer>();
			for (SessioGAssignada sessio : sga) {
				ngrups.add(sessio.getGrup().getNumero());
			}
			ControladorPersistencia.getInstancia().exportaSessioGrup(path,equip,hores,tipus,nsessions,ngrups,rec);
			
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * 
	 * @param path Path on volem exportar la SessioSubGrup
	 * @param plaEst Nom del Pla d'Estudis
	 * @param nomAssig Nom de l'Assignatura
	 * @param tipus Tipus de la Sessio
	 * @param hores Hores de la Sessio
	 * @param numg numero de grup.
	 * @param rec True si es vol truncar el fitxer, false si es vol fer un append
	 * @return
	 */
	public String exportaSessioSubGrup(String path, String plaEst, String nomAssig, String tipus, Integer hores, boolean rec) {
		try {
			
			SessioSubGrup ssg = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(nomAssig).getSessioSG(tipus, hores);
			HashSet<String> equip = ssg.getMaterial();
			int nsessions = ssg.getnsessions();
			HashSet<SessioSGAssignada> ssga = ssg.getAllSessionsSGA();
			HashSet<Pair<Integer, Integer>> num = new HashSet<Pair<Integer, Integer>>();
			for (SessioSGAssignada sessio : ssga) {
				num.add(new Pair<Integer,Integer> (sessio.getSubGrup().getGrup().getNumero(),sessio.getSubGrup().getNumero()));
			}
			ControladorPersistencia.getInstancia().exportaSessioSubGrup(path,equip,hores,tipus,nsessions,num,rec);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
}
	
	/**
	 * 
	 * @param path Path on volem exportar el Sub Grup
	 * @param numeroSG Numero del Sub Grup
	 * @param numeroG Numero del Grup
	 * @param assignatura Nom de l'Assignatura
	 * @param plaEst Nom del Pla d'Estudis
	 * @param rec True si es vol truncar el fitxer, false si es vol fer un append
	 * @return
	 */
	public String exportaSubGrup(String path, int numeroSG,int numeroG, String assignatura, String plaEst, boolean rec) {
		try {
			SubGrup sg = PlaEstudis.getPlaEstudis(plaEst).getAssignatura(assignatura).getGrup(numeroG).getSubGrup(numeroSG);
			Integer places = sg.getPlaces();
			Map<Integer, boolean[]> horesAptes = sg.getRestriccioHoresAptes().getHoresAptes();
			HashMap<String, HashSet<Integer>> solapaments = sg.getSolapaments().getDisjuntes();
			ControladorPersistencia.getInstancia().exportaSubGrup(path,numeroSG,places,horesAptes,solapaments,rec);
			return null;
		}
		catch (Exception e) {
			return e.toString();
		}
	}
	
	/**
	 * 
	 * @param path Path on volem exportar el Segment
	 * @param dia Dia del Segment que volem exportar
	 * @param hora Hora del Segment que volem exportar
	 * @param nomC Nom del Campus
	 * @param nomPE Nom del Pla d'estudis
	 * @param id Iterador per identificar l'horari correcte
	 * @return
	 */
	public String exportaSegment(String path, int dia, int hora, String nomC, String nomPE, int id) {
		try {
			HashSet<Estructura> h = Horari.getInstance().getHoraris(nomPE, nomC);
			Estructura aux = new Estructura(PlaEstudis.getPlaEstudis(nomPE),Campus.getCampus(nomC));
			String nomAula = null;
			String nomAssig = null;
			String tipus = null;
			int hores = -1;
			
			int it = id;
			for(Estructura e : h) {
				if (it == 0) aux = e;
				it--;
			}
			HashSet<Segment> segment = aux.getAllSegments(dia, hora);
			System.out.println("SIZE: " + segment.size());
			for (Segment s : segment) {
				int numg = 0;
				int numsg = 0;
				nomAula = s.getAula().getNom();
				System.out.println(nomAula);
				boolean grup = s.getSessio().snull();
				System.out.println(grup);
				if (grup) {
					numg = s.getSessio().first.getGrup().getNumero();
					nomAssig = s.getSessio().first.getSessioGrup().getAssignatura().getNom();
					tipus = s.getSessio().first.getSessioGrup().getTipus();
					hores = s.getSessio().first.getSessioGrup().getHores();
				}
				else {
					numsg = s.getSessio().second.getSubGrup().getNumero();
					nomAssig = s.getSessio().second.getSessioSubGrup().getAssignatura().getNom();
					tipus = s.getSessio().second.getSessioSubGrup().getTipus();
					hores = s.getSessio().second.getSessioSubGrup().getHores();
					for(Grup gr : PlaEstudis.getPlaEstudis(nomPE).getAssignatura(nomAssig).getGrups()) 
						for (SubGrup sg : gr.getAllSubGrups())
							if (sg.getNumero() == numsg) numg = gr.getNumero();
				}
				ControladorPersistencia.getInstancia().exportaSegment(path,nomAula,nomAssig,tipus,hores,numg,numsg);
			}
			return null;
		}
		catch (Exception e) {
			System.out.println(e);
			return e.toString();
		}
}

	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  IMPORTS  //////////////////////////////////////
	
	/**
	* Importa una assignatura
	* @param path Path del fitxer que volem 
	* @param nomPE Nom del pla d'estudis
	* @return Null en cas de estar correcte, sinó l'error
	*/
	public String importaAssignatura(String path, String nomPE) {
		return ControladorPersistencia.getInstancia().importaAssignatura(path, nomPE);
	}
	
	/**
	* Importa un aula
	* @param Path llista amb el que hi havia al fitxer
	* @param NomC nom del campus que pertany l'aula
	* @return Null en cas de cap error, l'error com a String altrament
	*/
	public String importaAula(String path, String nomC) {
		return ControladorPersistencia.getInstancia().importaAula(path, nomC);
	}
	
	/**
	* Importa un campus
	* @param Path llista amb el que hi havia al fitxer
	* @return Null en cas de estar correcte, sinó l'error
	*/
	public String importaCampus(String path) {
		return ControladorPersistencia.getInstancia().importaCampus(path);
	}
	
	/**
	* Importa un grup
	* @param path Path del fitxer que volem 
	* @param nomPE Nom del pla d'estudis
	* @param nomA Nom de l'assignatura
	* @return null en cas de estar correcte, sinó l'error
	*/
	public String importaGrup(String path, String nomPE, String nomA) {
		return ControladorPersistencia.getInstancia().importaGrup(path, nomPE, nomA);
	}
	
	/**
	* Importa un horari
	* @param path path del fitxer que volem 
	* @param nomC nom del campus
	* @param nomPE nom del pla d'estudis
	* @param id identificador de l'horari
	* @return null en cas de cap error, l'error com a String altrament
	*/
	public String importaHorari(String path) {
		return ControladorPersistencia.getInstancia().importaHorari(path);
		/*for(Estructura e : Horari.getInstance().getHoraris("fib", "campus nord"))
		Estructura.printHorari(e);*/
	}
	
	/**
	* Importa un Pla d'estudis
	* @param path  path del fitxer que volem 
	* @return null en cas de estar correcte, sinó l'error
	*/
	public String importaPlaEstudis(String path) {
		return ControladorPersistencia.getInstancia().importaPlaEstudis(path);
	}
	
	/**
	* Importa una sessió de grup
	* @param path path del fitxer que volem 
	* @param nomPE nom del pla d'estudis
	* @param nomA nom de l'assignatura
	* @return null en cas de estar correcte, sinó l'error
	*/
	public String importaSessioGrup(String path, String nomPE, String nomA) {
		return ControladorPersistencia.getInstancia().importaSessioGrup(path, nomPE, nomA);
	}
	
	/**
	* Importa una sessió de subgrup
	* @param path path del fitxer que volem 
	* @param nomPE nom del pla d'estudis 
	* @param nomA nom de l'assignatura
	* @return null en cas de estar correcte, sinó l'error
	*/
	public String importaSessioSubGrup(String path, String nomPE, String nomA) {
		return ControladorPersistencia.getInstancia().importaSessioSubGrup(path, nomPE, nomA);
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
		return ControladorPersistencia.getInstancia().importaSubGrup(path, nomPE, nomA, grup);
	}
}