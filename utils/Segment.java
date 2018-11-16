package utils;

import java.util.*;
import classes.*;
import utils.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 * En resum, la classe fa de "llesca" de l'horari; entent que
 * un "llesca" és una sessioAssignada en una aula i data present
 * al diccionari.
 */
public class Segment {
	/**
	 * Referencia a l'horari del qual es segment.
	 */
	private Map<Integer, Map<Integer, HashSet<Segment>>> horari;
	/**
	 * Registra la data a la qual pertany dins l'horari.
	 */
	private Data data;
	/**
	 * Registra l'aula a la qual es du a terme la sessio.
	 */
	private Aula aula;
	
	/**
	 * Registren la sessio que es dua terme en aquest segment.
	 * Com és obvi, si una es null, l'altre no ho és.
	 */
	private SessioGAssignada sessioG;
	private SessioSGAssignada sessioSG;

	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Contructora de la classe.
	 * @param horari Referencia a l'horari al qual pertany el segment.
	 * @param data Indica la data al qual pertany el segment.
	 * @param aula Indica l'aula on s'aplica el segment.
	 * @param sessio Indica la sessió que es dua terme en el segment.
	 */
	public Segment(Map<Integer, Map<Integer, HashSet<Segment>>> horari, Data data, Aula aula, SessioGAssignada sessio) {
		this.horari = horari;
		this.data = data;
		this.aula = aula;
		
		this.sessioG = sessio;
		this.sessioSG = null;
	}
	
	/**
	 * Contructora de la classe.
	 * @param horari Referencia a l'horari al qual pertany el segment.
	 * @param data Indica la data al qual pertany el segment.
	 * @param aula Indica l'aula on s'aplica el segment.
	 * @param sessio Indica la sessió que es dua terme en el segment.
	 */
	public Segment(Map<Integer, Map<Integer, HashSet<Segment>>> horari, Data data, Aula aula, SessioSGAssignada sessio) {
		this.horari = horari;
		this.data = data;
		this.aula = aula;
		
		this.sessioG = null;
		this.sessioSG = sessio;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/**
	 * Retorna l'horaria al qual pertany el segment.
	 * @return Un horari.
	 */
	public Map<Integer, Map<Integer, HashSet<Segment>>> getHorari() {
		return this.horari;
	}
	
	/**
	 * Retorna la data en la que es du a terme la sessió del segment.
	 * @return Una data.
	 */
	public Data getData() {
		return this.data;
	}
	
	/**
	 * Retorna l'aula a la qual es du a terme la sessió del segment.
	 * @return Una aula.
	 */
	public Aula getAula() {
		return this.aula;
	}
	
	/**
	 * Retorna un pair amb la sessio corresponent diferent de null;
	 * @return Pair de sessions.
	 */
	public Pair<SessioGAssignada, SessioSGAssignada> getSessio(){
		return new Pair<>(this.sessioG, this.sessioSG);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
}
