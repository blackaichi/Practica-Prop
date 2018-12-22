package domini.classes;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class SessioSubGrup extends Sessio{
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Registra totes les sessions de subgrup assignades
	 */
	private HashSet<SessioSGAssignada> sessionsSGA;
	
	/////////////////////////////////////////////////////////////
	/////////////////////////  Privats  /////////////////////////
	
	/**
	 * Elimina una sessió de subGrup assignada del HashSet
	 * @param sSGA la sessió de grup assignada que volem esborrar
	 */
	private void eliminaElementHashSet(SessioSGAssignada sSGA) {
		sessionsSGA.removeIf(item -> item.getSessioSubGrup().getTipus().equals(tipus) &&
							item.getSessioSubGrup().getHores() == hores);
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de SessioSubGrup amb assignatura i tipus com a paràmetre
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 * @param tipus tipus de la sessió 
	 * @throws Exception
	 */
	public SessioSubGrup(Assignatura assig, String tipus) throws Exception{
		super(assig, tipus); // crida a la constructora de Sessio
		this.sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/**
	 * Creadora de SessioSubGrup amb assignatura, hores i tipus com a paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 * @throws Exception
	 */
	public SessioSubGrup(Assignatura assig, int hores, String tipus) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio 
		sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/**
	 * Creadora de SessioSubGrup amb tots els paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del subgrup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre de sessions que es poden assignar
	 * @throws Exception
	 */
	public SessioSubGrup(Assignatura assig, int hores, String tipus, int nsessions) throws Exception{
		super(assig, hores, tipus, nsessions); // crida a la constructora de Sessio 
		sessionsSGA = new HashSet<SessioSGAssignada>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna quantes sessions té el subGrup
	 * @param nsessions nombre de sessions
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	@Override
	public int setnsessions(int nsessions) {
		if (nsessions < 1) return 95;
		this.nsessions = nsessions;
		return 0;
	}
	
	/**
	 * Assigna quantes hores té la sessió
	 * @param hores nombre d'hores de la sessió
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	@Override
	public int setHores(int hores) {
		if (hores < 1) return 91;
		else if(this.hores != hores) {
			HashSet<SessioSubGrup> sg = assignatura.getSessionsSG();
			for (SessioSubGrup s : sg)
				if (s.getHores() == hores && s.getTipus().equals(this.tipus)) return 114;
		}
		
		this.hores = hores;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna totes les sessions de subgrup assignades
	 * @return un HashSet amb les sessions de subgrup assignades
	 */
	public HashSet<SessioSGAssignada> getAllSessionsSGA() {
		return sessionsSGA;
	}
	
	/**
	 * Retorna la sessió de subgrup assignada del subgrup identificat per numero
	 * @return la sessió assignada o null en cas de no existir
	 */
	public SessioSGAssignada getSessioSGA(int numero) {
		for(SessioSGAssignada sessio: sessionsSGA)
			if(sessio.getSubGrup().getNumero() == numero) return sessio;
		return null;
	}
	
	/**
	 * Retorna la quantitat de sessions tenim assignades
	 * @return quantitat de sessions assignades
	 */
	public int getnSessionsAssignades() {
		return sessionsSGA.size();
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Assigna una nova sessió i la guarda al Hashset on tenim les sessions assignades
	 * @param numero número de subgrup al qual assignarem la sessió
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int assignaSessio(int numerogrup, int numerosubgrup) throws Exception {
		SubGrup subgrup = assignatura.getGrup(numerogrup).getSubGrup(numerosubgrup); // obtenim el subgrup que necessitem
		SessioSGAssignada sSGA = new SessioSGAssignada(subgrup, this); // creem una sessió assignada
		
		int checker;
		if ((checker = subgrup.afegeixSessio(sSGA)) != 0) return checker; // demanem a subgrup que també es guardi la sessió assignada
		sessionsSGA.add(sSGA); // afegim la sessió assignada al nostre hashset
		return 0;
	}
	
	/**
	 * Desassigna la sessió assignada passada per paràmetre
	 * @param sSGA la sessió de subgrup assignada que volem desassignar
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int desassignaSessio(SessioSGAssignada sSGA) {
		if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 103;
		SubGrup subgrup = sSGA.getSubGrup();
		
		int checker;
		if ((checker = subgrup.eliminaSessio(sSGA)) != 0) return checker;
		eliminaElementHashSet(sSGA);
		return 0;
	}
	
	/**
	 * Afegeix una nova sessió assignada al Hashset que previament ha assignat la classe subgrup
	 * @param sSGA la sessió de subgrup assignada que hem de guardar al Hashset
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int afegirSessio(SessioSGAssignada sSGA) throws Exception {
		if (this.sessionsSGA.contains(sSGA)) return 98;
		else if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 104;
		
		sessionsSGA.add(sSGA);
		return 0;
	}
	
	/**
	 * Elimina una sessió assignada del Hashset que previament ja ha desassignat la classe subgrup
	 * @param sSGA la sessió del subgrup desassignada que hem d'eliminar del Hashset
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int eliminarSessio(SessioSGAssignada sSGA) {
		if (!sSGA.getSubGrup().getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 105;
		
		eliminaElementHashSet(sSGA);
		return 0;
	}
}
