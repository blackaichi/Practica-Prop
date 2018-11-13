package restriccions;

import java.util.*;
import classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class EquipamentNecessari extends Unaria {
	
	/**
	 * Comprova si hi ha alguna aula adient per la sessió
	 * @param a aules que podem assignar a la sessió
	 * @param sGA sessió que hem d'assignar un aula
	 * @return 
	 */
	public int checkadient(HashSet<Aula> a, SessioGAssignada sGA) {
		if (a == null) return 210; 
		if (sGA == null) return 211; 
		int n = 0;
		HashSet<String> matSessio = sGA.getSessioGrup().getMaterial();
		for (Aula b : a) {
			if (matSessio.containsAll(b.getEquip())) ++n;
		}
		if (n == 0) return 213;
		return -n;
	}
	
	public int checkadient(HashSet<Aula> a, SessioSGAssignada sSGA) {
		if (a == null) return 210; 
		if (sSGA == null) return 212;
		int n = 0;
		HashSet<String> matSessio = sSGA.getSessioSubGrup().getMaterial();
		for (Aula b : a) {
			if (matSessio.containsAll(b.getEquip())) ++n;
		}
		if (n == 0) return 213;
		return -n;
	}
}
