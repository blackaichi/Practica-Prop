package classes;

import java.util.HashSet;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioSubGrup extends Sessio{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////

	/**
	 * Indica el nombre màxim de sessions d'aquest tipus
	 */
	private int nsessions = 0;
	
	/**
	 * Registra totes les sessions de subgrup assignades
	 */
	private HashSet<SessioSGAssignada> sessionsSGA = new HashSet<SessioSGAssignada>();
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////
	
	private void checkSessioAssignada(SubGrup sg, SessioSubGrup ssg) throws Exception {
		for (SessioSGAssignada s : sessionsSGA) {
			if (s.getSubGrup() == sg && s.getSessioSubGrup() == ssg) throw new Exception("No podem afegir una nova sessió de grup assignada si ja existeix una amb mateix subgrup i sessió de subgrup");
		}
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioSubGrup amb assignatura com a paràmetre
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 */
	public SessioSubGrup(Assignatura assig) throws Exception{
		super(assig); // crida a la constructora de Sessio		
		this.sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/**
	 * Creadora de SessioSubGrup amb assignatura i nsessions com a paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 * @param nsessions nombre de sessions de la sessió del subgrup
	 */
	public SessioSubGrup(Assignatura assig, int nsessions) throws Exception{
		super(assig); // crida a la constructora de Sessio
		this.setnsessions(nsessions);		
		this.sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/**
	 * Creadora de SessioSubGrup amb tots els paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre de sessions de la sessió del subgrup
	 */
	public SessioSubGrup(Assignatura assig, int hores, String tipus, int nsessions) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio
		if (hores > assignatura.getHLab()) throw new Exception("La sessió no pot ser de més hores de les que són permeses a l'assignatura per Subgrup");
		this.setnsessions(nsessions);
		this.sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes sessions té el grup
	 * @param n nombre de sessions
	 * @throws Exception si nsessions és 0 o negatiu
	 */
	public void setnsessions(int n) throws Exception {
		if (n < 1) throw new Exception("El nombre de sessions ha de ser més gran que 0");
		if (assignatura.getHLab() < nsessions) throw new Exception("el nombre d'hores és inferior al nombre de sessions");
		this.nsessions = n;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna el nombre de sessions del subgrup
	 * @return el nombre de sessions del subgrup
	 */
	public int getnsessions() {
		return nsessions;
	}
	
	/**
	 * Retorna totes les sessions de subgrup assignades
	 * @return un HashSet amb les sessions de subgrup assignades
	 */
	public HashSet<SessioSGAssignada> getAllSessionsSGA() {
		return sessionsSGA;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Assigna una nova sessió i la guarda al Hashset on tenim les sessions assignades
	 * @param numero numero de subgrup al qual assignarem la sessió 
	 * @throws Exception si ja hi ha un subgrup i sessió iguals als que volem assignar
	 */
	public void assignaSessio(int numerogrup, int numerosubgrup) throws Exception {
		SubGrup subgrup = assignatura.getGrup(numerogrup).getSubGrup(numerosubgrup); // obtenim el subgrup que necessitem
		checkSessioAssignada(subgrup, this);
		SessioSGAssignada sSGA = new SessioSGAssignada(subgrup, this); // creem una sessió assignada
		
		this.sessionsSGA.add(sSGA); // afegim la sessió assignada al nostre hashset
		subgrup.afegeixSessio(sSGA); // demanem a subgrup que també es guardi la sessió assignada
	}
	
	/**
	 * Desassigna la sessió assignada passada per paràmetre
	 * @param sSGA la sessió de subgrup assignada que volem desassignar
	 * @throws Exception quan no podem desassignar la sessgió assignada perquè pertany a assignatures diferents el subgrup i la sessió de subgrup
	 */
	public void desassignaSessio(SessioSGAssignada sSGA) throws Exception{
		if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) throw new Exception("No podem desassignar una sessió assignada si la seva sessió de subgrup i subgrup pertanyen a assignatures diferents");
		SubGrup subgrup = sSGA.getSubGrup();
		
		this.sessionsSGA.remove(sSGA);
		subgrup.eliminaSessio(sSGA);
	}
	
	/**
	 * Afegeix una nova sessió assignada al Hashset que previament ha assignat la classe subgrup
	 * @param sSGA la sessió de subgrup assignada que hem de guardar al Hashset
	 * @throws Exception quan la sessió assignada no pertanyi a la mateixa assignatura que la sessió o si la sessio ja esta assignada
	 */
	void afegirSessio(SessioSGAssignada sSGA) throws Exception {
		if (this.sessionsSGA.contains(sSGA)) throw new Exception("No podem afegir una sessió assignada que ja esta assignada");
		else if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) throw new Exception("No podem afegir una sessió assignada si la seva sessió de subgrup i subgrup pertanyen a assignatures diferents");
		
		this.sessionsSGA.add(sSGA);
	}
	
	/**
	 * Elimina una sessió assignada del Hashset que previament ja ha desassignat la classe subgrup
	 * @param sSGA la sessió del subgrup desassignada que hem d'eliminar del Hashset
	 * @throws Exception quan no existeixi la sessió assignada passada per paràmetre
	 */
	void eliminarSessio(SessioSGAssignada sSGA) throws Exception {
		if (!sessionsSGA.contains(sSGA)) throw new Exception("No podem eliminar una sessió assignada que no esta assignada");
		else if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) throw new Exception("No podem eliminar una sessió assignada si la seva sessió de subgrup i subgrup pertanyen a assignatures diferents");
		
		this.sessionsSGA.remove(sSGA);
	}
}
