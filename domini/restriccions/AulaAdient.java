package domini.restriccions;

import java.util.*;

import domini.classes.*;
import utils.*;

/**
 * Controla l'assignació d'aules.
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class AulaAdient {
	/**
	 * Selecciona, si hi és, una aula que compleixi tots els requisits de la sessio
	 * indicada. Altrament, retorna qualsevol aula que estigui lliure.
	 * @param sessio Referencia a la sessió a la qual se'n vol assignar una aula.
	 * @param dia Referencia al dia al qual s'ha d'assignar.
	 * @param hora Referenca a l'hora, a la qual s'ha d'assignar.
	 * @return Una Aula que pot o no complir amb tots els requisits.
	 */
	static public Aula seleccionaAulaAdient(Estructura horari, HashSet<Aula> aules, SessioGAssignada sessio, int dia, int hora) {
		return getMaximaAulaAdient(horari, aules, sessio.getSessioGrup().getMaterial(), sessio.getGrup().getPlaces(), dia, hora, sessio.getSessioGrup().getHores());
	}
	
	/**
	 * Selecciona, si hi és, una aula que compleixi tots els requisits de la sessio
	 * indicada. Altrament, retorna qualsevol aula que estigui lliure.
	 * @param sessio Referencia a la sessió a la qual se'n vol assignar una aula.
	 * @param dia Referencia al dia al qual s'ha d'assignar.
	 * @param hora Referenca a l'hora, a la qual s'ha d'assignar.
	 * @return Una Aula que pot o no complir amb tots els requisits.
	 */
	static public Aula seleccionaAulaAdient(Estructura horari, HashSet<Aula> aules, SessioSGAssignada sessio, int dia, int hora) {
		return getMaximaAulaAdient(horari, aules, sessio.getSessioSubGrup().getMaterial(), sessio.getSubGrup().getPlaces(), dia, hora, sessio.getSessioSubGrup().getHores());
	}
	
	/**
	 * Retorna l'aula que més s'adapta a l'equip indicat. Altrament n'escull una
	 * a l'atzar.
	 * @param equip Set amb tots els equips necesaris.
	 * @param dia Dia en el qual es vol reservar l'aula.
	 * @param hora Hora en la qual es vol reservar l'aula.
	 * @param durada Indica per quantes hores consecutives cal reservar aquella aula.
	 * @return L'aula mes adient.
	 */
	static private Aula getMaximaAulaAdient(Estructura horari, HashSet<Aula> aules, HashSet<String> equip, int places, int dia, int hora, int durada) {
		Map<Integer, HashSet<Aula>> aulesCandidates = new HashMap<>();
		
		for(Aula aula: aules)
			if(!checkAula(horari, aula, dia, hora, durada) || aula.getCapacitat() < places) { //Si l'aula esta ocupada, es descarta.
				if(aulesCandidates.get(aula.matchEquip(equip)) == null)
					aulesCandidates.put(aula.matchEquip(equip), new HashSet<>());
				
				aulesCandidates.get(aula.matchEquip(equip)).add(aula);
			}
		
		int maximMatch = 0;
		for(int match: aulesCandidates.keySet()) //Selecciona aquella Key que conté més coincidencies.
			if(match > maximMatch) maximMatch = match;
		
		if(aulesCandidates.isEmpty()) return null;
		else return getMinimaAulaAdient(aulesCandidates.get(maximMatch), maximMatch);
	}
	
	/**
	 * Retorna aquella aula pertanyent a aules que, a part de contenir TOT l'equip
	 * indicat, "malgasta" el minim possible d'equip.
	 * @param aules Conjunt d'aules candidades.
	 * @param nEquip Quantitat d'equip necessari.
	 * @return L'aula més adient.
	 */
	static private Aula getMinimaAulaAdient(HashSet<Aula> aules, int nEquip) {
		Map<Integer, HashSet<Aula>> aulesCandidates = new HashMap<>();
		for(Aula aula: aules) { //Genera un map registrant, per cada aula, quant material no s'esta usant.
			if(aulesCandidates.get(aula.getEquip().size() - nEquip) == null)
				aulesCandidates.put(aula.getEquip().size() - nEquip, new HashSet<>());
			
			aulesCandidates.get(aula.getEquip().size() - nEquip).add(aula);
		}
		
		int minimMatch = aulesCandidates.keySet().iterator().next(); //Agafem el primer element, sense saber quin es.
		for(int match: aulesCandidates.keySet()) //Selecciona aquella Key que té menys materials sense usar.
			if(match < minimMatch) minimMatch = match;
		
		return aulesCandidates.get(minimMatch).iterator().next();
	}
	
	/**
	 * Retorna true si, i només si, donat un dia i una hora, l'aula indicada
	 * està ocupada.
	 * @param aula Referencia l'aula a comprovar.
	 * @param dia Indica el dia en el qual se'n vol fer la comprovació.
	 * @param hora Indica l'hora en la qual se'n vol fer la comprovació.
	 * @return Un booleà.
	 */
	static private boolean checkAula(Estructura horari, Aula aula, int dia, int hora, int durada) {
		if(aula == null) return false;
		for(int incr = 0; incr < durada; incr++) {
			for(Segment segment: horari.getAllSegments(dia, hora+incr)) {
				if(segment.getAula().getNom().equals(aula.getNom())) return true;
			}
		}
		
		return false;
	}
}
