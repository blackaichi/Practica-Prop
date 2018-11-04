package classes;

import java.util.HashSet;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioGrup extends Sessio{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////

	/**
	 * Indica el nombre màxim de sessions d'aquest tipus
	 */
	private int nsessions;
	
	/**
	 * Registra totes les sessions de grup assignades
	 */
	private HashSet<SessioGAssignada> sessionsGA;
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////
	
	private void checkSessioAssignada(Grup g, SessioGrup sg) throws Exception {
		for (SessioGAssignada s : sessionsGA) {
			if (s.getGrup() == g && s.getSessioGrup() == sg) throw new Exception("No podem afegir una nova sessió de grup assignada si ja existeix una amb mateix grup i sessió de grup");
		}
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioGrup amb assignatura com a paràmetre
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 */
	public SessioGrup(Assignatura assig) throws Exception{
		super(assig); // crida a la constructora de Sessio 
		this.sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/**
	 * Creadora de SessioGrup amb assignatura i nsessions com a paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param nsessions nombre sessions de la sessió del grup
	 */
	public SessioGrup(Assignatura assig, int nsessions) throws Exception{
		super(assig); // crida a la constructora de Sessio 
		this.setnsessions(nsessions);
		this.sessionsGA = new HashSet<SessioGAssignada>();
	}

	/**
	 * Creadora de SessioGrup amb tots els paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre sessions de la sessió del grup
	 */
	public SessioGrup(Assignatura assig, int hores, String tipus, int nsessions) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio 
		if (hores > assignatura.getHTeo()) throw new Exception("La sessió no pot ser de més hores de les que són permeses a l'assignatura per Grup");
		this.setnsessions(nsessions);
		this.sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes sessions té el grup
	 * @param n nombre de sessions
	 * @throws Exception si nsessions és 0 o negatiu
	 */
	public void setnsessions(int nsessions) throws Exception {
		if (nsessions < 1) throw new Exception("el nombre de sessions ha de ser mes gran que 0");
		if (assignatura.getHTeo() < nsessions) throw new Exception("el nombre d'hores és inferior al nombre de sessions");
		this.nsessions = nsessions;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre de sessions màximes del grup
	 * @return el nombre de sessions màximes del grup
	 */
	public int getnsessions() {
		return nsessions;
	}
	
	/**
	 * Retorna totes les sessions de grup assignades
	 * @return un HashSet amb les sessions de grup assignades
	 */
	public HashSet<SessioGAssignada> getAllSessionsGA() {
		return sessionsGA;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Assigna una nova sessió i la guarda al Hashset on tenim les sessions assignades
	 * @param numero numero de grup al qual assignarem la sessió 
	 * @throws Exception si ja hi ha un grup i sessió iguals als que volem assignar
	 */
	public void assignaSessio(int numero) throws Exception {
		Grup grup = assignatura.getGrup(numero); // obtenim el grup que necessitem
		checkSessioAssignada(grup, this);
		SessioGAssignada sGA = new SessioGAssignada(grup, this); // creem una sessió assignada
		
		this.sessionsGA.add(sGA); // afegim la sessió assignada al nostre hashset
		grup.afegeixSessio(sGA); // demanem a grup que també es guardi la sessió assignada
	}
	
	/**
	 * Desassigna la sessió assignada passada per paràmetre
	 * @param sGA la sessió de grup assignada que volem desassignar
	 * @throws Exception quan no podem desassignar la sessgió assignada perquè pertany a assignatures diferents el grup i la sessió de grup
	 */
	public void desassignaSessio(SessioGAssignada sGA) throws Exception{
		if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) throw new Exception("No podem desassignar una sessió assignada si la seva sessió de grup i grup pertanyen a assignatures diferents");
		Grup grup = sGA.getGrup();
		
		this.sessionsGA.remove(sGA);
		grup.eliminaSessio(sGA);
	}
	
	/**
	 * Afegeix una nova sessió assignada al Hashset que previament ha assignat la classe grup
	 * @param sGA la sessió de grup assignada que hem de guardar al Hashset
	 * @throws Exception quan la sessió assignada no pertanyi a la mateixa assignatura que la sessió o si la sessio ja esta assignada
	 */
	void afegirSessio(SessioGAssignada sGA) throws Exception {
		if (sessionsGA.contains(sGA)) throw new Exception("No podem afegir una sessió assignada que ja esta assignada");
		else if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) throw new Exception("No podem afegir una sessió assignada si la seva sessió de grup i grup pertanyen a assignatures diferents");
		
		this.sessionsGA.add(sGA);
	}
	
	/**
	 * Elimina una sessió assignada del Hashset que previament ja ha desassignat la classe grup
	 * @param sGA la sessió del grup desassignada que hem d'eliminar del Hashset
	 * @throws Exception quan no existeixi la sessió assignada passada per paràmetre
	 */
	void eliminarSessio(SessioGAssignada sGA) throws Exception {
		if (!sessionsGA.contains(sGA)) throw new Exception("No podem eliminar una sessió assignada que no esta assignada");
		else if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) throw new Exception("No podem eliminar una sessió assignada si la seva sessió de grup i grup pertanyen a assignatures diferents");
		
		this.sessionsGA.remove(sGA);
	}
}
