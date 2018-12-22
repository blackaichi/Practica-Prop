package domini.tools;

import java.util.*;
import domini.classes.*;
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
	 * Registra la data en la qual comença la sessió a la qual
	 * pertany el segment.
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
	
	/**
	 * Referencia a l'estructura d'horari a la qual pertany aquest segment.
	 */
	private Estructura struct;
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora senzilla de la classe.
	 * @param sessioG Referencia a la sessio de grup a la qual pertany el segment.
	 * @param sessioSG Referencia a la sessio de subGrup a la qual pertany el segment.
	 * @throws Exception
	 */
	public Segment(SessioGAssignada sessioG, SessioSGAssignada sessioSG) throws Exception {
		ExceptionManager.thrower(this.setSessio(sessioG, sessioSG));
		this.data = null;
		this.aula = null;
	}
	
	/**
	 * Contructora de la classe.
	 * @param horari Referencia a l'horari al qual pertany el segment.
	 * @param data Indica la data al qual pertany el segment.
	 * @param aula Indica l'aula on s'aplica el segment.
	 * @param sessio Indica la sessió que es dua terme en el segment.
	 */
	private Segment(Data data, Aula aula, SessioGAssignada sessioG, SessioSGAssignada sessioSG) throws Exception {
		ExceptionManager.thrower(this.setSessio(sessioG, sessioSG));
		ExceptionManager.thrower(this.setAula(aula));
		ExceptionManager.thrower(this.setData(data));
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/**
	 * Assigna un valor a data.
	 * @param data Referencia la data que cal assginar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setData(Data data) {
		if(data == null) return 222;
		
		this.data = data;
		return 0;
	}
	
	/**
	 * Assigna una aula al segment
	 * @param aula Referencia a l'aula que cal assignar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setAula(Aula aula) {
		if(aula == null) return 262;
		
		this.aula = aula;
		return 0;
	}
	
	/**
	 * Assigna una sessió al segment.
	 * @param sessioG Referencia a la sessio a assginar, o bé es null.
	 * @param sessioSG Referencia a la sessio a assignar, o bé es null.
	 * @return Excepció codificada en forma d'enter.
	 */
	private  int setSessio(SessioGAssignada sessioG, SessioSGAssignada sessioSG) {
		if(sessioG == null && sessioSG == null) return 180;
		else if(sessioG != null && sessioSG != null) return 181;
		
		this.sessioG = sessioG;
		this.sessioSG = sessioSG;
		return 0;
	}
	
	/**
	 * Assigna una estructura al segment.
	 * @param struct Referencia a l'estructura a assignar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setEstructura(Estructura struct) {
		if(struct == null) return 182;
		else if(struct.getPlaEstudis().getNom().equals(sessioG != null? sessioG.getGrup().getAssignatura().getPlaEstudis().getNom() :
																		sessioSG.getSubGrup().getGrup().getAssignatura().getPlaEstudis().getNom()))
			return 183;
		
		this.struct = struct;
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////	
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
	
	/**
	 * Retorna l'estructura de l'horari al qual pertany el segment.
	 * @return Una instancia d'Estructura igual o diferent de null.
	 */
	public Estructura getHorari() {
		return this.struct;
	}
	
	/**
	 * Genera una copia totalment independent del segment entrat.
	 * @param toCopy Segment que es preten copiar.
	 * @return Un segment identic al segment entrat.
	 */
	public Segment getCopy(Estructura horari) {
		Segment clonedSegment = null;
		try {
			clonedSegment = new Segment(data, aula, sessioG, sessioSG);
			clonedSegment.setEstructura(horari);
		}
		catch(Exception e) {
			//TODO: alguna cosa;
		}
		
		return clonedSegment;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna true si, i només si, el segment entrat és, a efectes practics,
	 * igual al segment corresponent. 
	 * @param segment Referencia al segment a comparar.
	 * @return True o False segons si son iguals o no.
	 */
	public boolean equals(Segment segment) {
		if(segment == null) return false;
		
		if(this.data.getDia() == segment.getData().getDia() &&
		   this.data.getHora() == segment.getData().getHora() &&
		   this.aula.getNom().equals(segment.getAula().getNom()) &&
		   this.aula.getCampus().getNom().equals(segment.getAula().getCampus().getNom()) &&
		   this.sessioG == segment.sessioG && this.sessioSG == segment.sessioSG)
			return true;
		
		return false;
	}
}
