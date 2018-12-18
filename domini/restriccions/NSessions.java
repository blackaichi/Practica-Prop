package domini.restriccions;

import domini.tools.*;
import domini.classes.*;
import domini.tools.Estructura;
import utils.*;
import java.util.*;

/**
 * Controla l'organització de les nsessions.
 * @author hector.morales.carnice@est.fib.upc
 *
 */
public class NSessions {

	/**
	 * Contenidors d'nSessions:
	 */
	private Map<SessioGAssignada, HashSet<Integer>> ocurrencies_g;
	private Map<SessioSGAssignada, HashSet<Integer>> ocurrencies_sg;
	
	public NSessions() {
		this.ocurrencies_g = new HashMap<>();
		this.ocurrencies_sg = new HashMap<>();
	}
	
	/**
	 * Retorna un nSessions amb la configuració de l'horari entrada.
	 * @param horari Referencia a l'horari.
	 * @return un NSessions configurat segons l'horari.
	 */
	static public NSessions configure(Estructura horari) {
		NSessions scann = new NSessions();
		for(int dia = 0; dia < 7; dia++)
			for(int hora = 0; hora < 24; hora++)
				for(Segment segment : horari.getAllSegments(dia, hora))
					scann.checkNSessions(segment.getSessio().first, segment.getSessio().second, dia, new HashSet<>(), true);
		
		return scann;
	}
	
	/**
	 * Controla la quantitat de vegades que s'ha de fer una sessio.
	 * @param sessio Referencia a la sessió a checkejar.
	 * @param horari Referencia a l'horaria sobre el qual fer la comporvació.
	 * @param dia Dia en qual s'esta checkejant.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int checkNSessions(SessioGAssignada sessioG, SessioSGAssignada sessioSG, int dia, HashSet<String> flags, boolean force) {
		if(sessioG != null) {
			if(!ocurrencies_g.containsKey(sessioG)) 
				ocurrencies_g.put(sessioG, new HashSet<Integer>());
			
			if(!force && flags.contains("S_NSESSIONS") && ocurrencies_g.get(sessioG).contains(dia)) return 233;
			else ocurrencies_g.get(sessioG).add(dia);
			return 0;
		}
		else {
			if(!ocurrencies_sg.containsKey(sessioSG))
				ocurrencies_sg.put(sessioSG, new HashSet<Integer>());
			
			if(!force && flags.contains("S_NSESSIONS") && ocurrencies_sg.get(sessioSG).contains(dia)) return 233;
			else ocurrencies_sg.get(sessioSG).add(dia);
			return 0;
		}
	}

	/**
	 * Indica si una sessio concreta pot ser descartada o no, segons la relació entre les
	 * nsessions de la sessio, i el total de posicions de l'horari on s'ha lograt col·locar.
	 * @param sessioG Referncia a la sessio de grup a comprovar
	 * @param sessioSG Referencia a la sessio de subgrup a comprovar
	 * @return retorna true si, i només si, totes les nsessions de la sessio han estat
	 * col·locades.
	 */
	public boolean readyToDie(SessioGAssignada sessioG, SessioSGAssignada sessioSG) {		
		return sessioG != null? ocurrencies_g.containsKey(sessioG) && ocurrencies_g.get(sessioG).size() == sessioG.getSessioGrup().getnsessions() : 
								ocurrencies_sg.containsKey(sessioSG) && ocurrencies_sg.get(sessioSG).size() == sessioSG.getSessioSubGrup().getnsessions();
	}

	/**
	 * Genera una copia identica i independent del NSessions corrent.
	 * @return Un NSessions amb el contingut copiat de l'actual.
	 */
	public NSessions getCopy() {
		NSessions copy = new NSessions();
		for(SessioGAssignada key : this.ocurrencies_g.keySet()) {
			if(!copy.ocurrencies_g.containsKey(key)) copy.ocurrencies_g.put(key, new HashSet<Integer>());
			copy.ocurrencies_g.get(key).addAll(this.ocurrencies_g.get(key));
		}
		
		for(SessioSGAssignada key : this.ocurrencies_sg.keySet()) {
			if(!copy.ocurrencies_sg.containsKey(key)) copy.ocurrencies_sg.put(key, new HashSet<Integer>());
			copy.ocurrencies_sg.get(key).addAll(this.ocurrencies_sg.get(key));
		}
		
		return copy;
	}
}
