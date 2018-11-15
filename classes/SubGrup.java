package classes;

import java.util.*;
import restriccions.*;
import utils.*;

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
	
	/**
	 * Linca el subGrup amb la seva restricció d'hores aptes.
	 */
	private HoresAptesGrupSubGrup horesAptes;
	/**
	 * Linca el subGrup amb la seva restricció de grups i/o
	 * subgrups amb els quals no es pot solapar.
	 */
	private SolapamentsGrupSubGrup disjunts;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Comprova la integritat de la sessió respecte al SubGrup.
	 * @param sessio Referencia la sessió a comprovar.
	 * @throws Exception
	 */
	private int checkSessioAdequada(SessioSGAssignada sessio) {
		if(sessio.getSubGrup().getNumero() != this.numero) return 79;
		else if(sessio.getSubGrup().getGrup().getNumero() != this.getGrup().getNumero()) return 80;
		else if(!sessio.getSubGrup().getGrup().getAssignatura().getNom().equals(this.getGrup().getAssignatura().getNom())) return 83;
		
		return 0;
	}
	
	/**
	 * Desassigna una sessió del Grup i l'esborra del set, si hi és.
	 * Altrament no fa res.
	 * @param tipus Identifica el tipus de la sessio
	 * @param hores Identifica el temps de durada de la sessió en hores
	 * @param unlink Precisa la necessitat de trencar la navegavilitat
	 * de la sessioSubGrup cap a la sessioAssignada, o no.
	 * @throws Exception
	 */
	private int desassignaSessio(String tipus, int hores, boolean unlink) {
		SessioSGAssignada sessio = this.getSessio(tipus, hores);
		
		//Elimina la sessio de la classe SubGrup
		sessions.removeIf(item -> item.getSessioSubGrup().getTipus().equals(tipus) &&
								  item.getSessioSubGrup().getHores() == hores);
		
		//Elimina la sessio de la classe SessioSubGrup
		if(unlink) return sessio.getSessioSubGrup().eliminarSessio(sessio);
		else return 0;
	}
	
	/**
	 * Inicialitza adequadament les restriccions del Grup.
	 * @throws Excepciño rebuda durant la donada d'alta de les restriccions.
	 */
	private void iniRestriccions() throws Exception {
		HoresAptesGrupSubGrup horesApt = new HoresAptesGrupSubGrup(null, this);
		SolapamentsGrupSubGrup disjunts = new SolapamentsGrupSubGrup(null, this);
		
		this.horesAptes = horesApt;
		this.disjunts = disjunts;
		this.setSolapament(this.grup, null, false);
		//Un subgrup no es pot solapar amb el seu grup.
		//És logic, un grup, és el solapament de tots els seus subgrups.
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	public SubGrup(Grup grup, int numero) throws Exception{
		ExceptionManager.thrower(this.setGrup(grup));
		ExceptionManager.thrower(this.setNumero(numero));
		this.iniRestriccions();
		
		places = 0;
		sessions = new HashSet<>();
	}
	
	/**
	 * Constructora de la classe: SubGrup.
	 * @param grup Senyala a quin Grup pertany el subGrup.
	 * @param numero Identifica al subGrup.
	 * @param places Descriu la capacitat del SubGrup.
	 */
	public SubGrup(Grup grup, int numero, int places) throws Exception {
		ExceptionManager.thrower(this.setGrup(grup));
		ExceptionManager.thrower(this.setNumero(numero));
		ExceptionManager.thrower(this.setPlaces(places, false));
		this.iniRestriccions();
		
		sessions = new HashSet<>();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/** 
	 * Assigna un numero identificatiu al subGrup.
	 * @param numero Identifica el subGrup. 
	 * @throws Excepció codificada en forma d'enter.
	 */
	public int setNumero(int numero) {
		if(this.numero == numero) return 1; //En cas de fer un canvi inutil.
		else if(numero <= 0) return 50;
		else for(Assignatura assig: this.grup.getAssignatura().getPlaEstudis().getAssignatures()) {
			if(assig.checkGrup(numero)) return 70; //Alguna assignatura ja conté el numero com id d'un grup.
			else for(Grup grup: assig.getGrups()) //Algun grup ja conté el numero com id d'un subGrup.
				if(grup.checkSubGrup(numero)) return 70;
		}
		
		//En cas d'estar modificant el numero de subGrup, aquest s'ha d'actualitzar en les
		//restriccions de solapament del seu grups:
		if(this.numero != 0) this.grup.getSolapaments().actualitza(null, this, numero);
		
		this.numero = numero;
		return 0;
	}
	
	/** 
	 * Assigna una quantitat de places al subGrup sempre i quan la quantitat de
	 * places no superi les places lliures del grup. Altrament llança una excepció.
	 * @param places Indica el nombre de places assignades al subGrup. 
	 * @param incr Permet que, enlloc de fer saltar una excepció si la quantitat de places nova subruix
	 * del total de places del grup, la diferencia de places necessaries per obrir les places s'incrementi
	 * al total de places del grup.
	 * @throws Excepció codificada en forma d'enter.
	 */
	public int setPlaces(int places, boolean incr){
		if(this.places == places) return 1; //En cas de fer un canvi inutil.
		else if(places < 0) return 71;
		else if(grup.getPlaces() - grup.getPlacesAssignades() - this.places < places) return 72;
		
		this.places = places;
		return 0;
	}
	
	/**
	 * Assigna el grup al qual pertany el subGrup.
	 * @param grup Apunta al grup que engloba al subGrup.
	 * @throws NullPointerException
	 */
	public int setGrup(Grup grup) {
		if(grup == null) return 73;
		
		this.grup = grup;
		return 0;
	}
	
	/**
	 * Assigna la restricció d'hores aptes per aquest subGrup.
	 * @param franja indica per cada dia quines hores poden o no ser assignades.
	 * En cas de que sigui null per defecte s'assignen les hores lectives del
	 * pla d'estudis corresponent.
	 * @param apte Indica si l'acció que es preten fer es permetre o denegar aquelles hores.
	 * @param force Permet forçar l'assignació de la franja encara que aquesta violi en 
	 * part les hores lectives del pla d'estudis.
	 * @return Excepció codificada en forma d'enter.
	 * @throws Exception rebuda al donar d'alta la restricció.
	 */
	public int setHoresAptes(Map<Integer, int[]> franja, boolean apte, boolean force) {
		if(franja == null) return 0;
		else for(Map.Entry<Integer, int[]> iter: franja.entrySet()) {
			int checker = 0;
			if(apte) checker = this.horesAptes.permetHores(force, Integer.valueOf(iter.getKey()), iter.getValue());
			else checker = this.horesAptes.permetHores(force, Integer.valueOf(iter.getKey()), iter.getValue());
			if(checker != 0) return checker;
		}
		
		return 0;
	}
	
	/**
	 * Restringeix la possibilitat de que aquest subGrup es solapi amb el grup
	 * i/o subGrup passats per parametre; sempre i quan aquests compleixin
	 * les restriccions d'integritat globals.
	 * @param grup Referencia el grup a restringir.
	 * @param subGrup Referencia el subGrup a restringir.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setSolapament(Grup grup, SubGrup subGrup, boolean permet) {
		if(grup == null || subGrup == null) return 250; //no poden ser els dos nulls.
		else if(subGrup != null && this.equals(subGrup)) return 252; //Un Subgrup no pot ser disjunt amb si mateix.
		
		return this.disjunts.setSolapament(grup, subGrup, permet);
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
	
	/**
	 * Retorna la restriccio d'hores aptes.
	 * Tot i que en un principi pugui semblar que, aixi com està fet, a un subGrup
	 * se li poden assignar hores aptes que divergeixen de les hores aptes del seu Grup;
	 * a l'hora de crear l'horari el generador te en compte que, donat un subGrup, les seves
	 * hores aptes compleixin també les del seu Grup.
	 * @return Restriccions.
	 */
	public HoresAptesGrupSubGrup getRestriccioHoresAptes() {
		return this.horesAptes;
	}
	
	/**
	 * Retorna la restricció de no solpament.
	 * @return Restricció.
	 */
	public SolapamentsGrupSubGrup getSolapaments() {
		return this.disjunts;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Afegeix la quantitat entrada per parametre de places al subGrup si, 
	 * i només si, nplaces > 0 i hi ha suficients places no assignades al
	 * Grup; altrament llança una excepció.
	 * @param incr Quantitat de places a afegir.
	 */
	public int obrirPlaces(int nplaces) {
		if(nplaces < 0) return 71;
		else if(grup.getPlaces() - grup.getPlacesAssignades() < nplaces) return 74;
		
		this.places += nplaces;
		return 0;
	}
	
	/**
	 * Tanca la quantita entrada per paràmetre de places al SubGrup si,
	 * i només si, nplaces > 0 i hi ha almenys nplaces obertes al SubGrup;
	 * altrament llança una excepció.
	 * @param nplaces Quantitat positiva de places a tancar.
	 */
	public int tancarPlaces(int nplaces) {
		if(nplaces < 0) return 71;
		else if(this.places - nplaces < 0) return 75;
		
		this.places -= nplaces;
		return 0;
	}
	
	/**
	 * Assigna un nova sessió al SubGrup i l'enregistra.
	 * @param tipus Identifica el tipus de la sessio
	 * @param hores Identifica el temps de durada de la sessió en hores
	 * @return Excepció codificada en forma d'enter.
	 * @throws Exception rebuda durant la donada d'alta d'una SessioAssginada,
	 * o bé, durant el linkatge amb sessioSubGrup.
	 */
	public int assignaSessio(String tipus, int hores) throws Exception{
		if(this.checkSessio(tipus, hores)) return 76;
		else if(!grup.getAssignatura().checkSessioSG(tipus, hores)) return 77;
	
		SessioSubGrup sessioSubGrup = grup.getAssignatura().getSessioSG(tipus, hores);
		SessioSGAssignada sessio = new SessioSGAssignada(this, sessioSubGrup);
		
		//Enllaç amb la classe SessioSubGrup
		int checker;
		if((checker = sessioSubGrup.afegirSessio(sessio)) != 0) return checker;
		else sessions.add(sessio); //Enllaç amb la classe SubGrup
		return 0;
	}
	
	/**
	 * Desassigna aquella sessió del SubGrup igual a la sessió afegida per
	 * paràmetre i l'esborra del set, si hi és.
	 * Altrament no fa res.
	 * @param sessio Referencia a la sessió que és preten esborrar.
	 * @throws Exception
	 */
	public int desassignaSessio(SessioSGAssignada sessio) {
		int checker;
		if((checker = this.checkSessioAdequada(sessio)) != 0) return checker;
		else return this.desassignaSessio(sessio.getSessioSubGrup().getTipus(), sessio.getSessioSubGrup().getHores(), true);
	}
	
	/**
	 * Afegeix una nova sessió al SubGrup si, i només si, compleix les restriccions
	 * d'integritat del subGrup.
	 * @param sessio Referencia a la SessioSGAssignada que es preten afegir.
	 * @throws Exception
	 */
	public int afegeixSessio(SessioSGAssignada sessio) {
		if(sessions.contains(sessio)) return 78;
		else if(sessio.getSubGrup().getNumero() != this.numero) return 79;
		else if(sessio.getSubGrup().getGrup().getNumero() != this.getGrup().getNumero()) return 80;
		else if(!sessio.getSessioSubGrup().getAssignatura().getNom().equals(this.getGrup().getAssignatura().getNom())) return 81;
		else if(this.checkSessio(sessio.getSessioSubGrup().getTipus(), sessio.getSessioSubGrup().getHores())) return 82;

		sessions.add(sessio);
		return 0;
	}
	
	/**
	 * Elimina del set la sessio passada per parametre, si hi és.
	 * Altrament no fa res.
	 * @param sessio Referencia a la sessio que es preten esborrar.
	 * @throws Exception
	 */
	public int eliminaSessio(SessioSGAssignada sessio) {
		int checker;
		if((checker = this.checkSessioAdequada(sessio)) != 0) return checker;
		else return this.desassignaSessio(sessio.getSessioSubGrup().getTipus(), sessio.getSessioSubGrup().getHores(), false);
	}
	
	/**
	 * Restaura les hores aptes per defecte.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int resetHoresAptes() {
		if(this.horesAptes == null) return 0;
		else return this.horesAptes.restore();
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

	/**
	 * Retorna true si, i només si, el subGrup entrat es identic al subGrup
	 * en qüestió.
	 * @param subGrup Referencia al subGrup a comparar.
	 * @return Un booleà que representa la similitud entre ambdós.
	 */
	public boolean equals(SubGrup subGrup) {
		return this.getNumero() == subGrup.getNumero() &&
				this.getGrup().equals(subGrup.getGrup());
	}

	/**
	 * Retorna true si, i només si, l'hora es troba dins de la franja horaria
	 * de matí o tarda segons quina tingui assignat el grup del subGrup;
	 * altrament retorna false.
	 * @param hora indica l'hora a comparar.
	 * @return un booleà.
	 */
	public boolean enRang(int hora) {
		return this.getGrup().enRang(hora);
	}
}
