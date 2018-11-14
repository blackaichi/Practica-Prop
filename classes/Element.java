package classes;

import java.util.*;
import restriccions.*;

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
	private Campus campus;
	
	/**
	 * Aules ja ocupades
	 */
	private HashSet<Aula> aulesOcupades;
	
	/**
	 * Sessió de grup de l'element
	 */
	private SessioGAssignada sessioGrup;
	
	/**
	 * Sessió de subgrup de l'element
	 */
	private SessioSGAssignada sessioSubGrup;
	
	/**
	 * Data de l'element
	 */
	private Data data;
	
	/**
	 * Horari al qual pertany l'element
	 */
	private Horari horari;
	
	/**
	 * Equipament necessari
	 */
	private EquipamentNecessari equip;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////

	/**
	 * Retorna les aules no assignades del campus
	 * @return les aules lliures del campus
	 */
	private HashSet<Aula> getAulesLliures() {
		HashSet<Aula> aulesLliures = campus.getAllAules();
		for (Aula ocupada : aulesOcupades) {
			aulesLliures.removeIf(item -> item.getNom().equals(ocupada.getNom()));
		}
		return aulesLliures;
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
	
	/**
	 * Creadora de Element amb aula, sessió de grup i data com a paràmetres
	 * @param a aula de l'element
	 * @param sGA sessió de grup de l'element
	 * @param data data de l'element
	 * @throws Exception
	 */
	public Element(Campus c, SessioGAssignada sGA, Data data, Horari horari) throws Exception {
		ExceptionManager.thrower(setCampus(c));
		ExceptionManager.thrower(setSessioGAssignada(sGA));
		ExceptionManager.thrower(setData(data));
		ExceptionManager.thrower(setHorari(horari));
		aulesOcupades = new HashSet<Aula>();
		equip = new EquipamentNecessari(this);
	}
	
	/**
	 * Creadora de Element amb aula, sessió de subgrup i data com a paràmetres
	 * @param a aula de l'element
	 * @param sSGA sessió de grup de l'element
	 * @param data data de l'element
	 * @throws Exception
	 */
	public Element(Campus c, SessioSGAssignada sSGA, Data data, Horari horari) throws Exception {
		ExceptionManager.thrower(setCampus(c));
		ExceptionManager.thrower(setSessioSGAssignada(sSGA));
		ExceptionManager.thrower(setData(data));
		ExceptionManager.thrower(setHorari(horari));
		aulesOcupades = new HashSet<Aula>();
		equip = new EquipamentNecessari(this);
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
		sessioSubGrup = null;
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
		sessioGrup = null;
		return 0;
	}

	/**
	 * Assigna un aula a l'element
	 * @param a aula de l'element
	 * @return 0 si s'ha fet correctament, altrament error
	 */
	public int setCampus(Campus c) {
		if (c == null) return 233;
		campus = c;
		return 0;
	}
	
	/**
	 * Assigna un horari a l'element
	 * @param horari horari de l'element
	 * @return 0 si s'ha fet correctament, altrament error
	 */
	public int setHorari(Horari horari) {
		if (horari == null) return 234;
		this.horari = horari;
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
	public Campus getCampus() {
		return campus;
	}
	
	/**
	 * Retorna l'horari de l'element
	 * @return l'horari de l'element
	 */
	public Horari getHorari() {
		return horari;
	}
	
	/////////////////////////////////////////////////////////////
	///////////////////////  Funcions  //////////////////////////
	
	/**
	 * Retorna les aules adients per la sessió de grup sGA
	 * @param sGA una sessió de grup assignada
	 * @return les aules adients per la sessió
	 * @throws Exception
	 */
	public HashSet<Aula> getAules(SessioGAssignada sGA) throws Exception {
		return equip.aulesAdients(getAulesLliures(), sGA);
	}
	
	/**
	 * Retorna les aules adients per la sessió de subgrup sSGA
	 * @param sGA una sessió de subgrup assignada
	 * @return les aules adients per la sessió
	 * @throws Exception
	 */
	public HashSet<Aula> getAules(SessioSGAssignada sSGA) throws Exception {
		return equip.aulesAdients(getAulesLliures(), sSGA);
	}
}
