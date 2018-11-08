package classes;

import java.util.*;

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
	private HashSet<SessioGAssignada> sessionsGA = new HashSet<SessioGAssignada>();
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////
	
	/**
	 * Elimina una sessió de grup assignada del HashSet
	 * @param sGA la sessió de grup assignada que volem esborrar
	 */
	private void eliminaElementHashSet(SessioGAssignada sGA) {
		sessionsGA.removeIf(item -> item.getSessioGrup().getTipus().equals(tipus) &&
							item.getSessioGrup().getHores() == hores);
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioGrup amb assignatura com a paràmetre, per defecte nsessio = 1
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @throws Exception La superclasse llançarà una excepció en cas de 
	 */
	public SessioGrup(Assignatura assig) throws Exception{
		super(assig); // crida a la constructora de Sessio 
		nsessions = 1;
		sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/**
	 * Creadora de SessioGrup amb assignatura i nsessions com a paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param nsessions nombre sessions de la sessió del grup
	 */
	public SessioGrup(Assignatura assig, int nsessions) throws Exception{
		super(assig); // crida a la constructora de Sessio 
		setnsessions(nsessions);
		sessionsGA = new HashSet<SessioGAssignada>();
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
		setnsessions(nsessions);
		sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/**
	 * Creadora de SessioGrup amb tots els paràmetres, per defecte nsessions = 1
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 */
	public SessioGrup(Assignatura assig, int hores, String tipus) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio 
		if (hores > assignatura.getHTeo()) throw new Exception("La sessió no pot ser de més hores de les que són permeses a l'assignatura per Grup");
		setnsessions(1);
		sessionsGA = new HashSet<SessioGAssignada>();
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
	
	/**
	 * Retorna la quantitat de sessions tenim assignades
	 * @return quantitat de sessions assignades
	 */
	public int getnSessionsAssignades() {
		return sessionsGA.size();
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
		SessioGAssignada sGA = new SessioGAssignada(grup, this); // creem una sessió assignada
		
		sessionsGA.add(sGA); // afegim la sessió assignada al nostre hashset
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
		
		eliminaElementHashSet(sGA);
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
		
		sessionsGA.add(sGA);
	}
	
	/**
	 * Elimina una sessió assignada del Hashset que previament ja ha desassignat la classe grup
	 * @param sGA la sessió del grup desassignada que hem d'eliminar del Hashset
	 * @throws Exception quan la sessió assignada te un grup i una sessió de grup amb assignatures diferents
	 */
	void eliminarSessio(SessioGAssignada sGA) throws Exception {
		if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) throw new Exception("No podem eliminar una sessió assignada si la seva sessió de grup i grup pertanyen a assignatures diferents");
		
		eliminaElementHashSet(sGA);
	}
}
