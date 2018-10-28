package classes;

import java.util.*;

public class Grup {
	//Atributs de la classe Grup:
	private int numero;
	private int places;
	
	//Franja horaria; només pot obtenir els valors {M,T,MT,NAN}
	private String franja;
	
	//Enllaços amb les classes corresponents:
	private HashSet<SubGrup> subGrups;	//Registra tots els subGrups que pertanyen al Grup.
	//private Assignatura assignatura;	//Registra l'assignatura a la qual pertany el Grup.
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Retorna la suma total de places assignades als subGrups d'un grup.
	 * @return Un enter suerior o igual a 0.
	 */
	private int calculaCapacitatTotal() {
		int calcPlaces = 0;
		for(SubGrup subg: subGrups) calcPlaces += subg.getPlaces();
		
		return calcPlaces;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/** 
	 * Constructora de la classe Grup.
	 * Predeterminadament els parametres de la classe: numero, places, franja;
	 * prenen els valors -1, -1, "NAN" respectivament. Mentre que el conjunt de
	 * subGrups inicialment és buit.
	 */
	public Grup() {
		numero = places = 0;
		franja = new String("NAN");
		
		subGrups = new HashSet<>();
	}
	
	/** 
	 * Constructora de la classe Grup.
	 * @param numero Indentifica el grup. 
	 * @param places Descriu la capacitat del grup.
	 * @param franja Descriu la franja horaria del grup
	 */
	public Grup(int numero, int places, String franja) throws Exception {
		this.setNumero(numero);
		this.setPlaces(places);
		this.setFranja(franja);
		
		subGrups = new HashSet<>();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/** 
	 * Assigna un numero identificatiu al grup.
	 * @param numero Identifica el grup. 
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	/** 
	 * Assigna una quantitat de places al grup.
	 * @param places Indica el nombre de places assignades al grup. 
	 */
	public void setPlaces(int places) throws Exception {
		if(places < 0) throw new Exception("Grup amb capacitat negativa.");
		else if(places < this.getPlacesAssignades()) throw new Exception("Grup amb menys places que places sumen els seus SubGrups.");
		
		this.places = places;
	}
	
	/** 
	 * Assigna una franja horaria al grup.
	 * @param franja Descriu la nova franja horaria del grup.
	 */
	public void setFranja(String franja) throws Exception {
		if(franja.equals("NAN")) this.franja = franja;
		else if(!"MT".contains(franja)) throw new Exception("Franja incorrecte.");
		
		this.franja = franja;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/** 
	 * Retorna el número queidentifica al grup.
	 * @return Un enter qualsevol.
	 */
	public int getNumero() {
		return numero;
	}
	
	/** 
	 * Retorna la quantitat de places que te assignades el grup.
	 * @return Un enter superior o igual a 0.
	 */
	public int getPlaces() {
		return this.places;
	}
	
	/** 
	 * Retorna el nombre de places total sumada entre tots els subGrups del grup.
	 * @return Un enter superior o igual a 0.
	 */
	public int getPlacesAssignades() {
		return calculaCapacitatTotal();
	}
	
	/** 
	 * Retorna un String amb valor "M", "T", "MT" o "NAN" segons si la franja horaria
	 * assignada al grup és de matí, tarda, ambdos, o cap dels dos respectivament.
	 * @return Un String que identifica la franja horaria.
	 */
	public String getFranja() {
		return franja;
	}
	
	/** 
	 * Retorna tot el conjunt de SubGrups creats al Grup.
	 * @return Un HashSet de SubGrups amb tots els subgrups del Grup.
	 */
	public HashSet<SubGrup> getAllSubGrups() {
		return subGrups;
	}
	
	/** 
	 * Retorna, si existeix en el Grup, el SubGrup identificat per número;
	 * altrament retorna null. 
	 * @param numero Descriu el subGrup que es preten obtenir.
	 * @return Un SubGrup amb l'identificació numero pertanyent el Grup.
	 */
	public SubGrup getSubGrup(int numero) {
		for(SubGrup subg: subGrups)
			if(subg.getNumero() == numero) return subg;
		
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Afegeix la quantitat entrada per parametre de places al Grup si, 
	 * i només si, nplaces > 0; altrament fa l'acció inversa.
	 * @param incr Quantitat de places a afegir.
	 */
	public void obrirPlaces(int nplaces) {
		this.places += nplaces;
	}
	
	/**
	 * Tanca la quantita entrada per paràmetre de places al Grup si,
	 * i només si, nplaces > 0; altrament fa l'acció complementaria.
	 * @param nplaces Quantitat de places a tancar.
	 */
	public void tancarPlaces(int nplaces) {
		this.obrirPlaces(-1 * nplaces); //Passem el valor a egatiu per a que resti, enlloc de sumar.
	}
	
	/** 
	 * Crea un nou subGrup dins el grup corresponent sempre i quan els atributs entrats
	 * compleixin amb les especificacions. Altrament llança una excepció.
	 * @param numero Indica el numero que identificarà al subGrup.
	 * @param places Indica quantes places ha de tenir el subGrup.
	 */
	public void altaSubGrup(int numero, int places) throws Exception{
		if(places > this.places) throw new Exception("Subgrup amb més places que el Grup");
		else if((this.places - calculaCapacitatTotal()) < places) throw new Exception("No hi ha proutes places al Grup");
		else if(places < 0) throw new Exception("Subgrup amb capacitat negativa");
		//else if(numero/10 != this.numero) throw new Exception("Sugrup amb numero incorrecte respecte al Grup");
		
		subGrups.add(new SubGrup(this, numero, places));
	}
	
	/**
	 * Elimina el subgrup amb el numero definit, si existeix; altrament no fa res.
	 * @param numero Descriu el grup que es preten eliminar.
	 */
	public void baixaSubGrup(int numero) {
		subGrups.removeIf(item -> item.getNumero() == numero);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/** 
	 * Retorna quants subgrups hi ha en un grup.
	 * @return nombre enter superior o igual a 0.
	 */
	public int quantsSubGrups() {
		return subGrups.size();
	}
	
	/** 
	 * Retorna true si, i només si, existeix un subgrup amb el numero passat per
	 * parametre.
	 * @param numero Descriu el subGrup que es pretén cercar.
	 */
	public boolean checkSubGrup(int numero) {
		for(SubGrup subg: subGrups)
			if(subg.getNumero() == numero) return true;
		
		return false;
	}
}
