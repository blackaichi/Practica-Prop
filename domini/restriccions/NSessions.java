package domini.restriccions;

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
	private Map<SessioGAssignada, Integer> ocurrencies_g;
	private Map<SessioSGAssignada, Integer> ocurrencies_sg;
	
	/**
	 * Comprova si en un dia concret ja existeix alguna sessió de qualsevol tipus
	 * d'una assignatura per un grup o subgrup concret.
	 * @param horari Referencia a l'horari en el qual s'ha de fer la cerca.
	 * @param dia Indica el dia en el qual s'ha de fer la cerca.
	 * @param assig Identifica l'assignatura.
	 * @param numero Identifica al grup o subgrup.
	 * @return Un enter != 0, si ja hi ha una sessio per grup i assignatura; 0 altrament.
	 */
	static private int checkIfSessioColocada(Estructura horari, int dia, Assignatura assig, int numero) {
		if(horari == null) return 230;
		else if(assig == null) return 231;
		
		if(horari.containsSessio(assig.getNom(), numero, dia)) return 233;
		else return 0;
	}
	
	/**
	 * Controla la quantitat de vegades que s'ha de fer una sessio.
	 * @param sessio Referencia a la sessió a checkejar.
	 * @param horari Referencia a l'horaria sobre el qual fer la comporvació.
	 * @param dia Dia en qual s'esta checkejant.
	 * @return Excepció codificada en forma d'enter.
	 */
	static private int checkNSessions(Estructura horari, Sessio sessio, int dia) {
		if(!horari.getFlagState("S_NSESSIONS")) return 0;
		
		if(horari == null) return 230;
		else if(sessio == null) return 232;
		else return 0;
	}
}
