package classes;

import java.util.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class SubGrup {
	/**
	 * Identifica al SubGrup dins d'un Grup concret. 
	 */
	private int numero;
	/**
	 * Numera les places disponibles al SubGrup. 
	 */
	private int places;
	
	/**
	 * Identifica el grup al qual pertany el SubGrup.
	 */
	private Grup grup;
	/**
	 * Registra totes les sessions a les quals pertany el subGrup.
	 */
	//private HashSet<SessioSGAssignada> sessions;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe: SubGrup.
	 * @param grup Senyala a quin Grup pertany el subGrup.
	 * @param numero Identifica al subGrup.
	 * @param places Descriu la capacitat del SubGrup.
	 */
	public SubGrup(Grup grup, int numero, int places) throws Exception {
		this.grup = grup;
		
		this.setNumero(numero);
		this.setPlaces(places, false);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/** 
	 * Assigna un numero identificatiu al subGrup.
	 * @param numero Identifica el subGrup. 
	 */
	public void setNumero(int numero) throws Exception {
		if(this.numero == numero) return; //En cas de fer un canvi inutil.
		else if(numero < 0) throw new Exception("Número de subgrup negatiu.");
		
		HashSet<SubGrup> subGrups = grup.getAllSubGrups();
		for(SubGrup sgrup: subGrups) //Cerca d'una coincidencia; si n'hi ha, s'ha de llançar una excepció.
			if(sgrup.getNumero() == numero) throw new Exception("El número de subGrup ja existeix en aquest Grup.");
		
		this.numero = numero;
	}
	
	/** 
	 * Assigna una quantitat de places al subGrup sempre i quan la quantitat de
	 * places no superi les places lliures del grup. Altrament llança una excepció.
	 * @param places Indica el nombre de places assignades al subGrup. 
	 * @param incr Permet que, enlloc de fer saltar una excepció si la quantitat de places nova subruix
	 * del total de places del grup, la diferencia de places necessaries per obrir les places s'incrementi
	 * al total de places del grup.
	 */
	public void setPlaces(int places, boolean incr) throws Exception{
		if(this.places == places) return; //En cas de fer un canvi inutil.
		else if(places < 0) throw new Exception("Nombre negatiu de places.");
		else if(grup.getPlaces() - grup.getPlacesAssignades() - this.places < places)
			throw new Exception("Subgrup amb més places que el Grup");
		
		this.places = places;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/** 
	 * Retorna el número queidentifica al subGrup.
	 * @return Un enter qualsevol.
	 */
	public int getNumero() {
		return numero;
	}
	
	/** 
	 * Retorna la quantitat de places que te assignades el subGrup.
	 * @return Un enter superior o igual a 0.
	 */
	public int getPlaces() {
		return places;
	}
	
	/**
	 * Retorna la classe del Grup al qual pertany el SubGrup.
	 * @return Una classe de tipus Grup.
	 */
	public Grup getGrup() {
		return grup;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Afegeix la quantitat entrada per parametre de places al subGrup si, 
	 * i només si, nplaces > 0 i hi ha suficients places no assignades al
	 * Grup; altrament llança una excepció.
	 * @param incr Quantitat de places a afegir.
	 */
	public void obrirPlaces(int nplaces) throws Exception {
		if(nplaces < 0) throw new Exception("Nombre negatiu de places.");
		else if(grup.getPlaces() - grup.getPlacesAssignades() < nplaces)
			throw new Exception("No hi ha proutes places al grup");
		
		this.places += nplaces;
	}
	
	/**
	 * Tanca la quantita entrada per paràmetre de places al SubGrup si,
	 * i només si, nplaces > 0 i hi ha almenys nplaces obertes al SubGrup;
	 * altrament llança una excepció.
	 * @param nplaces Quantitat positiva de places a tancar.
	 */
	public void tancarPlaces(int nplaces) throws Exception {
		if(nplaces < 0) throw new Exception("Nombre negatiu de places.");
		else if(this.places - nplaces < 0)
			throw new Exception("No hi ha proutes places per tancar.");
		
		this.places -= nplaces;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
}
