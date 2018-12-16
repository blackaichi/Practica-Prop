package domini.tools;

import domini.classes.*;
import utils.ExceptionManager;

import java.util.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class Estructura {
	/**
	 * Encapsula l'estructura de l'horari.
	 */
	private Map<Integer, Map<Integer, HashSet<Segment>>> horari;
	/**
	 * Enmagatzema aquells flags que s'han tingut en compte durant
	 * la generació d'aquest horari. Aquells que no hi apareixen,
	 * han sigut ignorats pel generador.
	 */
	private HashSet<String> flags;
	
	/**
	 * Referencia al pla d'estudis al qual pertany l'horari.
	 */
	private PlaEstudis plaEstudis;
	/**
	 * Referencia al campus al qual s'aplica l'horari.
	 */
	private Campus campus;
		
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Instancia al map d'horari el dia i hora per evitar excepcions de punter null.
	 * @param dia Indica quin dia inicialitzar.
	 * @param hora Indica quina hora del dia inicialitzar.
	 */
	private void inicialitzaData(int dia, int hora) {
		if(horari.get(dia) == null) horari.put(dia, new HashMap<Integer, HashSet<Segment>>());
		if(horari.get(dia).get(hora) == null) horari.get(dia).put(hora, new HashSet<Segment>());
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Contructora de la classe Estructura.
	 */
	public Estructura(PlaEstudis plaEstudis, Campus campus) throws Exception {
		ExceptionManager.thrower(this.setPlaEstudis(plaEstudis));
		ExceptionManager.thrower(this.setCampus(campus));
		this.horari = new HashMap<>();
		this.flags = new HashSet<>();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/**
	 * Assigna un pla d'estudis a horari.
	 * @param plaEstudis Referencia al pla d'estudis.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int setPlaEstudis(PlaEstudis plaEstudis) {
		if(plaEstudis == null) return 172;
		
		this.plaEstudis = plaEstudis;
		return 0;
	}
	
	/**
	 * Assigna un campus a l'horari.
	 * @param campus Referencia al campus de l'horari.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int setCampus(Campus campus) {
		if(campus == null) return 173;
		
		this.campus = campus;
		return 0;
	}
	
	/**
	 * Assigna el segment definit al dia i hora indicats.
	 * @param segment Referencia al segment a inserir.
	 * @param dia Indica el dia en el qual s'ha d'inserir.
	 * @param hora Indica la hora en la qual s'ha d'inserir.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setSegment(Segment segment, int dia, int hora) {
		if(segment == null) return 174;
		else if(dia < 0 || dia > 6) return 175;
		else if(hora < 0 || hora > 23) return 176;
		
		this.inicialitzaData(dia, hora);
		horari.get(dia).get(hora).add(segment);
		segment.setEstructura(this); //linkem l'estructura amb el segment.
		return 0;
	}
	
	/**
	 * Assigna l'estat del flag senyalat per index.
	 * @param index Indica quin flag es vol actuallitzar.
	 * @param state Indica el nou estat del flag.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setFlag(String flag, boolean activar) {
		if(flag == null || flag.isEmpty()) return 177;
		
		if(activar) flags.add(flag.toUpperCase());
		else flags.removeIf(item -> item.equals(flag.toUpperCase()));
		return 0;
	}
	
	/**
	 * Assigna el valor de tots els flags com els passats per parametre.
	 * @param flags Referencia als nous flags.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setFlags(HashSet<String> flags) {
		if(flags == null) return 178;
		
		this.flags.clear();
		for(String flag : flags) this.flags.add(flag);
		
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/**
	 * Retorna el pla d'estudis de l'estructura corresponent.
	 * @return Un pla d'estudis.
	 */
	public PlaEstudis getPlaEstudis() {
		return this.plaEstudis;
	}
	
	/**
	 * Retorna el campus pertinent a l'estructura de l'horari.
	 * @return Un campus.
	 */
	public Campus getCampus() {
		return this.campus;
	}
	
	/**
	 * Retorna tots els segments situats en un dia i hora concrets.
	 * @param dia Indica el dia del qual se'n volen obtenir els segments
	 * @param hora Indica l'hora del dia.
	 * @return Un HashSet buit, o amb molts elements.
	 */
	public HashSet<Segment> getAllSegments(int dia, int hora){
		HashSet<Segment> segments = new HashSet<>();
		
		this.inicialitzaData(dia, hora);
		for(Segment segment : horari.get(dia).get(hora))
			segments.add(segment);
		
		return segments;
	}
	
	/**
	 * Retorna el set de flags actius per aquest horari.
	 * @return Un HashSet buit, o amb molts elements.
	 */
	public HashSet<String> getFlags(){
		return this.flags;
	}
	
	/**
	 * Retorna l'estat del flag indicat per index.
	 * @param index Indica quin flag es vol obtenir.
	 * @return True o false segons si el flag esta activat o no.
	 */
	public boolean getFlagState(String flag) {
		return flags.contains(flag);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Comprova si en un dia concret ja existeix alguna sessió de qualsevol tipus
	 * d'una assignatura per un grup o subgrup concret.
	 * @param assig Identifica l'assignatura de la qual es cerca una sessio.
	 * @param numero Identifica el grup o subgrup del qual es cerca una sessio.
	 * @param dia Identifica en quin dia es cerca la sessio.
	 * @return Un booleà a true, si ja hi ha una sessio per grup i assignatura; false altrament.
	 */
	public boolean containsSessio(String assig, int numero, int dia) {
		if(!horari.containsKey(dia)) return false;
		else for(int hora: horari.get(dia).keySet()) {
			for(Segment segment: horari.get(dia).get(hora)) {
				if(!segment.getSessio().fnull())
					if(segment.getSessio().first.getSessioGrup().getAssignatura().getNom().equals(assig) &&
					   segment.getSessio().first.getGrup().getNumero() == numero) return true;
				
				else if(!segment.getSessio().snull())
					if(segment.getSessio().second.getSessioSubGrup().getAssignatura().getNom().equals(assig) &&
					   segment.getSessio().second.getSubGrup().getNumero() == numero) return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Genera un copia totalment independent de l'estructura 
	 * corresponent.
	 * @return Una Estructura.
	 */
	public Estructura getCopy() {
		try {
			Estructura cloned = new Estructura(plaEstudis, campus);
			cloned.setFlags(this.flags);
			
			for(int dia : horari.keySet())
				for(int hora : horari.get(dia).keySet())
					for(Segment segment : horari.get(dia).get(hora))
						cloned.setSegment(segment.getCopy(cloned), dia, hora);
			
			return cloned;
		}
		catch(Exception e) {
			return null;
		}
	}
	
	/**
	 * PER A TESTOS: Imprimeix per pantalla un horari concret.
	 * @param struct Referencia l'horari a imprimir.
	 */
	static public void printHorari(Estructura struct) {
		System.out.println("-------------------------------------------------------");
		if(struct == null) System.out.println("Ep! L'horari es NULL! :( ");
		else if(struct.horari.isEmpty()) System.out.println("Ep! L'horari es buit! :( ");
		else for(int dia: struct.horari.keySet()) {
			System.out.println("DIA: ".concat(String.valueOf(dia)));
			for(int hora = 0; hora < 24; hora++) if(struct.horari.get(dia).containsKey(hora)) {
				System.out.println("	HORA: ".concat(String.valueOf(hora)));
				for(Segment segment: struct.horari.get(dia).get(hora)) {
					System.out.println("		 ASSSIGNATURA: ".concat(!segment.getSessio().fnull()? segment.getSessio().first.getGrup().getAssignatura().getNom() : 
																									  segment.getSessio().second.getSubGrup().getGrup().getAssignatura().getNom()));
					
					System.out.println("		 Nº GRUP/SUBGRUP: ".concat(String.valueOf(!segment.getSessio().fnull()? segment.getSessio().first.getGrup().getNumero() : 
						  																			  segment.getSessio().second.getSubGrup().getNumero())));
					
					System.out.println("		 SESSIÓ: ".concat(!segment.getSessio().fnull()? segment.getSessio().first.getSessioGrup().getTipus() : 
																								segment.getSessio().second.getSessioSubGrup().getTipus()));
					
					System.out.println("		 AULA: ".concat(segment.getAula().getNom()));
					System.out.println("");
				}
			}
		}
		System.out.println("-------------------------------------------------------");
	}
}
