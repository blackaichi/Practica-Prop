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
	private HashSet<SessioSGAssignada> sessions;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	public SubGrup(Grup grup) throws NullPointerException{
		this.setGrup(grup);
		
		numero = places = 0;
		sessions = new HashSet<>();
	}
	
	/**
	 * Constructora de la classe: SubGrup.
	 * @param grup Senyala a quin Grup pertany el subGrup.
	 * @param numero Identifica al subGrup.
	 * @param places Descriu la capacitat del SubGrup.
	 */
	public SubGrup(Grup grup, int numero, int places) throws Exception {
		this.setGrup(grup);
		this.setNumero(numero);
		this.setPlaces(places, false);
		
		sessions = new HashSet<>();
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
		else if(grup.checkSubGrup(numero)) throw new Exception("El número de subGrup ja existeix al Grup.");
		
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
	
	/**
	 * Assigna el grup al qual pertany el subGrup.
	 * @param grup Apunta al grup que engloba al subGrup.
	 * @throws NullPointerException
	 */
	public void setGrup(Grup grup) throws NullPointerException{
		if(grup == null) throw new NullPointerException("El subGrup ha de formar part d'un Grup");
		else this.grup = grup;
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
	
	/**
	 * Retorna totes les sessions a les quals està assignat el SubGrup.
	 * @return Un HashSet de SessioSGAssignada buit, o amb múltiples sessions.
	 */
	public HashSet<SessioSGAssignada> getSessions() {
		return sessions;
	}
	
	/**
	 * Retorna, si hi és, la referencia a la sessió del subGrup amb
	 * el tipus i hores corresponents; altrament retorna null.
	 * @param tipus Identifica el tipus de la sessió.
	 * @param hores Identifica la durada en hores de la sessió.
	 * @return Una classe de tipus SessioSGAssignada o un null.
	 */
	public SessioSGAssignada getSessio(String tipus, int hores) {
		for(SessioSGAssignada sessio: sessions)
			if(sessio.getSessioSubGrup().getTipus().equals(tipus) && sessio.getSessioSubGrup().getHores() == hores)
				return sessio;
		
		return null;
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
	
	/**
	 * Assigna un nova sessió al SubGrup i l'enregistra.
	 * @param tipus Identifica el tipus de la sessio
	 * @param hores Identifica el temps de durada de la sessió en hores
	 * @throws Exception
	 */
	public void assignaSessio(String tipus, int hores) throws Exception {
		if(this.checkSessio(tipus, hores)) throw new Exception("El subGrup ja conté una sessió amb el mateix tipus i hores");
		else if(!grup.getAssignatura().checkSessioSG(tipus, hores))
			throw new Exception("L'assignatura del grup al qual pertany el subGrup no te cap sessio de subGrup del tipus indicat.");
	
		SessioSubGrup sessioSubGrup = grup.getAssignatura().getSessioSG(tipus, hores);
		SessioSGAssignada sessio = new SessioSGAssignada(this, sessioSubGrup);
		
		//Enllaç amb la classe SubGrup
		sessions.add(sessio);
		
		//Enllaç amb la classe SessioSubGrup
		sessioSubGrup.afegirSessio(sessio);
	}
	
	/**
	 * Desassigna una sessió del Grup i l'esborra del set, si hi és.
	 * Altrament no fa res.
	 * @param tipus Identifica el tipus de la sessio
	 * @param hores Identifica el temps de durada de la sessió en hores
	 * @throws Exception
	 */
	public void eliminaSessio(String tipus, int hores) {
		SessioSGAssignada sessio = this.getSessio(tipus, hores);
		
		//Elimina la sessio de la classe SubGrup
		sessions.removeIf(item -> item.getSessioSubGrup().getTipus().equals(tipus) &&
								  item.getSessioSubGrup().getHores() == hores);
		
		//Elimina la sessio de la classe SessioSubGrup
		sessio.getSessioSubGrup().eliminarSessio(tipus, hores);
	}
	
	/**
	 * Desassigna aquella sessió del SubGrup igual a la sessió afegida per
	 * paràmetre i l'esborra del set, si hi és.
	 * Altrament no fa res.
	 * @param sessio Referencia a la sessió que és preten esborrar.
	 * @throws Exception
	 */
	public void eliminaSessio(SessioSGAssignada sessio) throws Exception {
		if(sessio.getSubGrup().getNumero() != this.numero) throw new Exception("La sessió no és del SubGrup corresponent");
		else if(sessio.getSubGrup().getGrup().getNumero() != this.getGrup().getNumero())
			throw new Exception("La sessió no és del Grup del SubGrup corresponent.");
		else if(!sessio.getSubGrup().getGrup().getAssignatura().getNom().equals(this.getGrup().getAssignatura().getNom()))
			throw new Exception("La sessió a esborrar no és de la mateixa assignatura que el subGrup.");
		
		this.eliminaSessio(sessio.getSessioSubGrup().getTipus(), sessio.getSessioSubGrup().getHores());
	}
	
	/**
	 * Afegeix una nova sessió al SubGrup si, i només si, compleix les restriccions
	 * d'integritat del subGrup.
	 * @param sessio Referencia a la SessioSGAssignada que es preten afegir.
	 * @throws Exception
	 */
	public void afegeixSessio(SessioSGAssignada sessio) throws Exception{
		if(sessions.contains(sessio)) throw new Exception("El subGrup ja conté aquesta sessió.");
		else if(sessio.getSubGrup().getNumero() != this.numero) throw new Exception("La sessió no és del SubGrup corresponent");
		else if(sessio.getSubGrup().getGrup().getNumero() != this.getGrup().getNumero())
			throw new Exception("La sessió no és del Grup del SubGrup corresponent.");
		else if(!sessio.getSessioSubGrup().getAssignatura().getNom().equals(this.getGrup().getAssignatura().getNom()))
			throw new Exception("La sessió i el grup del subGrup són d'assignatures diferents.");
		/*else if(this.checkSessio(sessio.getSessioSubGrup().getTipus(), sessio.getSessioSubGrup().getHores()))
			throw new Exception("El subGrup ja conté una sessió amb el mateix tipus i hores");
		*/
		sessions.add(sessio);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna el nombre de sessions a les quals esta assignat aquest subgrup.
	 * @return Un enter superior o igual a 0.
	 */
	public int quantesSessions() {
		return sessions.size();
	}
	
	/**
	 * Retorna true si, i només si, el SubGrup està assignat a una sessió
	 * identificada pel tipus i el temps de duració.
	 * Altrament retorna false.
	 * @param tipus Identifica el tipus de la sessió
	 * @param hores Identifica el temps de durada de la sessió en hores
	 * @return Descriu l'existencia d'una sessió del subGrup per una assignatura i
	 * tipus de sessió concreta
	 */
	public boolean checkSessio(String tipus, int hores) {
		return this.getSessio(tipus, hores) != null;
	}
	
	/**
	 * Retorna true si, i només si, el SubGrup està assignat a una sessió del
	 * tipus indicat (Sense tenir en compte la duració)
	 * @param tipus Identifica el tipus de la sessió
	 * @return Un boolea que descriu l'existencia d'una sessió del tipus
	 * corresponent.
	 */
	public boolean checkSessio(String tipus) {
		for(SessioSGAssignada sessio: sessions)
			if(sessio.getSessioSubGrup().getTipus().equals(tipus)) return true;
		
		return false;
	}
}
