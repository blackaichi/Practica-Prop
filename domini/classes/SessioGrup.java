package domini.classes;

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
	 * Registra totes les sessions de grup assignades
	 */
	private HashSet<SessioGAssignada> sessionsGA;
	
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
	 * Creadora de SessioGrup amb assignatura i tipus com a paràmetre
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param tipus tipus de la sessió
	 * @throws Exception
	 */
	public SessioGrup(Assignatura assig, String tipus) throws Exception{
		super(assig, tipus); // crida a la constructora de Sessio 
		sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/**
	 * Creadora de SessioGrup amb assignatura, hores i tipus com a paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 * @throws Exception 
	 */
	public SessioGrup(Assignatura assig, int hores, String tipus) throws Exception{
		super(assig, hores, tipus); // crida a la constructora de Sessio 
		sessionsGA = new HashSet<SessioGAssignada>();
	}

	/**
	 * Creadora de SessioGrup amb tots els paràmetres
	 * @param assig l'assignatura a la qual pertany la sessió del grup
	 * @param hores nombre d'hores de la sessió
	 * @param tipus tipus de la sessió
	 * @param nsessions nombre de sessions que es poden assignar
	 * @throws Exception
	 */
	public SessioGrup(Assignatura assig, int hores, String tipus, int nsessions) throws Exception{
		super(assig, hores, tipus, nsessions); // crida a la constructora de Sessio 
		sessionsGA = new HashSet<SessioGAssignada>();
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////

	/**
	 * Assigna quantes sessions té la sessió
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
			//TODO: Rerik as aki tuz mieldas
		}
		
		this.hores = hores;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna totes les sessions de grup assignades
	 * @return un HashSet amb les sessions de grup assignades
	 */
	public HashSet<SessioGAssignada> getAllSessionsGA() {
		return sessionsGA;
	}
	
	/**
	 * Retorna la sessió de grup assignada del grup identificat per numero
	 * @return la sessió assignada o null en cas de no existir
	 */
	public SessioGAssignada getSessioGA(int numero) {
		for(SessioGAssignada sessio: sessionsGA)
			if(sessio.getGrup().getNumero() == numero) return sessio;
		return null;
	}
	
	/**
	 * Retorna la quantitat de sessions que tenim assignades
	 * @return quantitat de sessions assignades
	 */
	public int getnSessionsAssignades() {
		return sessionsGA.size();
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Assigna una nova sessió i la guarda al Hashset on tenim les sessions assignades
	 * @param numero número de grup al qual assignarem la sessió 
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int assignaSessio(int numero) throws Exception {
		Grup grup = assignatura.getGrup(numero); // obtenim el grup que necessitem
		SessioGAssignada sGA = new SessioGAssignada(grup, this); // creem una sessió assignada
		
		int checker;
		if ((checker = grup.afegeixSessio(sGA)) != 0) return checker; 
		sessionsGA.add(sGA); // afegim la sessió assignada al nostre hashset
		return 0;
	}
	
	/**
	 * Desassigna la sessió assignada passada per paràmetre
	 * @param sGA la sessió de grup assignada que volem desassignar
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int desassignaSessio(SessioGAssignada sGA) {
		if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 97;
		Grup grup = sGA.getGrup();
		
		int checker;
		if ((checker = grup.eliminaSessio(sGA)) != 0) return checker;
		eliminaElementHashSet(sGA);
		return 0;
	}
	
	/**
	 * Afegeix una nova sessió assignada al Hashset que previament ha assignat la classe grup
	 * @param sGA la sessió de grup assignada que hem de guardar al Hashset
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int afegirSessio(SessioGAssignada sGA) {
		if (sessionsGA.contains(sGA)) return 98;
		else if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 99;
		
		sessionsGA.add(sGA);
		return 0;
	}
	
	/**
	 * Elimina una sessió assignada del Hashset que previament ja ha desassignat la classe grup
	 * @param sGA la sessió del grup desassignada que hem d'eliminar del Hashset
	 * @return 0 en cas de que no hi hagi error, altrament error
	 */
	public int eliminarSessio(SessioGAssignada sGA) {
		if (!sGA.getGrup().getAssignatura().getNom().equals(assignatura.getNom())) return 100;
		
		eliminaElementHashSet(sGA);
		return 0;
	}
}
