package domini.restriccions;

import domini.classes.*;
import java.util.*;

/**
 * Controla la restricció d'alineament.
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class Alineament {
	/**
	 * Retorna 0 si, i només si, l'hora especificada és múltiple de la durada
	 * en hores de la sessio corresponent (mai ambdues a la vegada).
	 * @param sessioG Referencia a la sessio de grup.
	 * @param sessioSG Referencia a la sessio de subgrup.
	 * @param hora Indica l'hora que es vol comprovar.
	 * @return Excepció codificada en forma d'enter.
	 */
	static public int checkAlineament(SessioGAssignada sessioG, SessioSGAssignada sessioSG, int hora, HashSet<String> flags) {
		if(!flags.contains("S_ALIGN")) return 0; //Si el flag d'alineament no està activat s'obvia.
		else if(sessioG == null && sessioSG == null) return 270;
		
		//Obtenció de la durada en hores de la sessió:
		int durada = sessioG != null? sessioG.getSessioGrup().getHores() : 
									  sessioSG.getSessioSubGrup().getHores();
		
		return hora%durada == 0? 0 : 271;
	}
}
