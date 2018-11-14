package classes;

import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class Element {
	
	/////////////////////////////////////////////////////////////
	//////////////////////// Variables //////////////////////////
	
	/**
	 * Aula assignada a l'element
	 */
	Aula aula;
	
	/**
	 * Sessió de grup de l'element
	 */
	SessioGAssignada sessioGrup;
	
	/**
	 * Sessió de subgrup de l'element
	 */
	SessioSGAssignada sessioSubGrup;
	
	/**
	 * Data de l'element
	 */
	Data data;
	
	/**
	 * Horari al qual pertany l'element
	 */
	Horari horari;
	
	/**
	 * HashSet on guardarem les aules
	 */
	HashSet<Aula> aules;
	
	/**
	 * HashSet on guardarem les sessions de grup assignades
	 */
	HashSet<SessioGAssignada> sessionsGrup;
	
	/**
	 * HashSet on guardarem les sessions de subgrup assignades
	 */
	HashSet<SessioGAssignada> sessionsSubGrup;
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de Element amb aula, sessió de grup i data com a paràmetres
	 * @param a aula de l'element
	 * @param sGA sessió de grup de l'element
	 * @param data data de l'element
	 * @throws Exception
	 */
	public Element(Aula a, SessioGAssignada sGA, Data data) throws Exception {
		ExceptionManager.thrower(setAula(a));
		ExceptionManager.thrower(setSessioGAssignada(sGA));
		ExceptionManager.thrower(setData(data));
	}
	
	/**
	 * Creadora de Element amb aula, sessió de subgrup i data com a paràmetres
	 * @param a aula de l'element
	 * @param sSGA sessió de grup de l'element
	 * @param data data de l'element
	 * @throws Exception
	 */
	public Element(Aula a, SessioSGAssignada sSGA, Data data) throws Exception {
		ExceptionManager.thrower(setAula(a));
		ExceptionManager.thrower(setSessioSGAssignada(sSGA));
		ExceptionManager.thrower(setData(data));
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Setters  //////////////////////////
	
	/**
	 * Assigna una data a l'element
	 * @param data data de l'element
	 * @return 0 si s'ha fet correctament, altrament error
	 */
	public int setData(Data data) {
		if (data == null) return 230;
		this.data = data;
		return 0;
	}

	/**
	 * Assigna una sessió de grup a l'element
	 * @param sGA sessió de grup de l'element
	 * @return 0 si s'ha fet correctament, altrament error
	 */
	public int setSessioGAssignada(SessioGAssignada sGA) {
		if (sGA == null) return 231;
		this.sessioGrup = sGA;
		return 0;
	}
	
	/**
	 * Assigna una sessió de subgrup a l'element
	 * @param sSGA sessió de subgrup de l'element
	 * @return 0 si s'ha fet correctament, altrament error
	 */
	public int setSessioSGAssignada(SessioSGAssignada sSGA) {
		if (sSGA == null) return 232;
		this.sessioSubGrup = sSGA;
		return 0;
	}

	/**
	 * Assigna un aula a l'element
	 * @param a aula de l'element
	 * @return 0 si s'ha fet correctament, altrament error
	 */
	public int setAula(Aula a) {
		if (a == null) return 233;
		aula = a;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	////////////////////////  Getters  //////////////////////////
	
	/**
	 * Retorna la data de l'element
	 * @return la data de l'element
	 */
	public Data getData() {
		return data;
	}

	/**
	 * Retorna la sessió de grup assignada de l'element
	 * @return la sessió de grup assignada de l'element
	 */
	public SessioGAssignada getSessioGAssignada() {
		return sessioGrup;
	}
	
	/**
	 * Retorna la sessió de subgrup assignada de l'element
	 * @return la sessió de subgrup assignada de l'element
	 */
	public SessioSGAssignada getSessioSGAssignada() {
		return sessioSubGrup;
	}

	/**
	 * Retorna l'aula de l'element
	 * @return l'aula de l'element
	 */
	public Aula getAula() {
		return aula;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
}
