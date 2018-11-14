package classes;

import classes.*;
import utils.*;
import java.util.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class Horari {
	/**
	 * Enmagatzema els horaris generats per un pla d'estudis
	 * únic en un campus concret.
	 * El primer Map és un horari, del qual:
	 * 	El primer Integer senyala:	Dia de la setmana;
	 * 	El segón Integer senyala:	Hora del dia;
	 * 	El Set d'Elements indica:	Totes les sessions per aquell dia, a aquella hora.
	 */
	private Map<Integer, Map<Integer, HashSet<Element>>> horariGenerat;

	/**
	 * Referencia al pla d'estudis del qual se'n vol
	 * generar l'horari.
	 */
	private PlaEstudis plaEstudis;
	/**
	 * Referencia el campus al qual es vol aplicar l'horari.
	 */
	private Campus campus;

	/**
	 * Enmagatzema totes les Sessions de Grup que s'han de colocar
	 * al horari.
	 */
	private HashSet<SessioGAssignada> sessionsDeGrup;
	/**
	 * Enmagatzema totes les Sessions de SubGrup que s'han de colocar
	 * a l'horari.
	 */
	private HashSet<SessioSGAssignada> sessionsDeSubGrup;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Assigna un pla d'estudis a horari.
	 * @param plaEstudis Referencia al pla d'estudis.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int setPlaEstudis(PlaEstudis plaEstudis) {
		if(plaEstudis == null) return -1; //TODO: El pla d'estudis no pot ser null;
		
		this.plaEstudis = plaEstudis;
		return 0;
	}
	
	/**
	 * Assigna un campus a l'horari.
	 * @param campus Referencia al campus de l'horari.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int setCampus(Campus campus) {
		if(campus == null) return -1; //TODO: El campus no pot ser null;
		
		this.campus = campus;
		return 0;
	}
	
	/**
	 * Retorna la sessio situada a la posició index.
	 * Basicament permet treballar amb ambdos HashSets com
	 * si fossin un de sol.
	 * @param index Posicio que es vol cercar.
	 * @return La sessió corresponent.
	 */
	private Pair<SessioGAssignada, SessioSGAssignada> getSessio(int index) {
		Pair<SessioGAssignada, SessioSGAssignada> element = new Pair<>();
		if(index < sessionsDeGrup.size()) //En cas d'estar accedint a les Sessions de Grup
			for(SessioGAssignada sessio: sessionsDeGrup)
				if(index-- <= 0) element.first = sessio;
		
		else if(index - sessionsDeGrup.size() < sessionsDeSubGrup.size()) //En cas d'accedir a les sessions de SubGrup
			for(SessioSGAssignada sessioS: sessionsDeSubGrup)
				if(index-- <= 0) element.second = sessioS;
		
		return element;
	}
	
	/**
	 * Funció recursiva que, en definitiva, genera l'horari adient.
	 * @param index Controla la sessio a processar.
	 * @param GOcup Controla les sessions de grup ocupades.
	 * @param SGOcup Controla les sessions de SubGrup ocupades.
	 * @throws Exception
	 */
	private void backTracker(int index, HashSet<SessioGAssignada> GOcup, HashSet<SessioSGAssignada> SGOcup) throws Exception {
		//Conté la sessió corrent al index:
		Pair<SessioGAssignada, SessioSGAssignada> corrent = this.getSessio(index);
		
		if(corrent.isNull()) return; //S'hi s'ha arribat al final s'acaba.
		else if(!corrent.fnull() && !GOcup.contains(corrent.first) ||
				!corrent.snull() && !SGOcup.contains(corrent.second)) { 
			
			//En cas de que la sessió corrent no estigui assignada:
			for(int dia = 0; dia < 7; dia++) {
				//Si el dia es lectiu.
				if(this.plaEstudis.checkDiaFranja(dia)) for(int hora = 0; hora < 24;) {
					//Si la hora entrada pel dia entrat es lectiu al pla d'Estudis.
					if(this.plaEstudis.getFranjaDia(dia)[hora]) {
						//...
						//TOTES LES RESTRICCIONS
						//...
						
						//AGREGACIÓ:				
						Element element;
						if(!corrent.fnull()) element = new Element(this.campus, corrent.first, new Data(dia, hora), this);
						else element = new Element(this.campus, corrent.second, new Data(dia, hora), this);
						
						//Control de repeticions
						horariGenerat.get(dia).get(hora).add(element);
						if(corrent.first != null) GOcup.add(corrent.first);
						else SGOcup.add(corrent.second);
						
						//RECURSIVITAT!!! Kernel del backTracking:
						backTracker(index++, GOcup, SGOcup);
						if(this.getSessio(index).first != null) GOcup.remove(this.getSessio(index).first);
						else SGOcup.remove(this.getSessio(index).second);
					}
				}
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  GENERADOR  ////////////////////////////////////
	private void GENERADOR() throws Exception{
		HashSet<SessioGAssignada> sessionsDeGrupOcupades = new HashSet<>();
		HashSet<SessioSGAssignada> sessionsDeSubGrupOcupades = new HashSet<>();
		
		this.backTracker(0, sessionsDeGrupOcupades, sessionsDeSubGrupOcupades);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Construcora de la classe Horari.
	 */
	public Horari(PlaEstudis plaEstudis, Campus campus) throws Exception {
		ExceptionManager.thrower(this.setCampus(campus));
		ExceptionManager.thrower(this.setPlaEstudis(plaEstudis));
		
		this.horariGenerat = new HashMap<>();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
}
