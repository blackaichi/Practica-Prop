package restriccions;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class EquipamentNecessari {
	
	private Element element;
	
	/**
	 * Retorna les aules adients per la sessió
	 * @param a aules que podem assignar a la sessió
	 * @param sGA sessió que hem d'assignar un aula
	 * @return les aules adients per la sessió
	 * @throws Exception 
	 */
	public HashSet<Aula> aulesadients(HashSet<Aula> a, SessioGAssignada sGA) throws Exception {
		if (a == null) ExceptionManager.thrower(210); 
		if (sGA == null) ExceptionManager.thrower(210);
		HashSet <Aula> aux = a;
		aux.removeIf(item -> !sGA.getSessioGrup().getMaterial().containsAll(item.getEquip()));
		return aux;
	}
	
	/**
	 * Retorna les aules adients per la sessió
	 * @param a aules que podem assignar a la sessió
	 * @param sSGA sessió que hem d'assignar un aula
	 * @return les aules adients per la sessió
	 * @throws Exception
	 */
	public HashSet<Aula> aulesadients(HashSet<Aula> a, SessioSGAssignada sSGA) throws Exception {
		if (a == null) ExceptionManager.thrower(210); 
		if (sSGA == null) ExceptionManager.thrower(212);
		HashSet <Aula> aux = a;
		aux.removeIf(item -> !sSGA.getSessioSubGrup().getMaterial().containsAll(item.getEquip()));
		return aux;
	}
}
