package classes;

import java.util.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class ExceptionManager {
	
	static private String[] titles;
	
	public ExceptionManager() {
		if(titles == null) {
			titles = new String[20];
			
			//DECLARACIÓ DE SIGNIFICATS GENERALS 0:9 :
			titles[0] = "NO_ERROR";
					
			//PLA ESTUDIS 10:29 :
			
			//ASSIGNATURA 30:49 :
			
			//GRUP 50: 69:
			
			//SUBGRUP 70:89 :
			
			//SESSIO 90:109 :
			
			//SESSIO ASSIGNADA 110:129 :
						
			//CAMPUS 130:149 :
			
			//AULA 150:169 :
			
			//HORARI 170:189 :
			
			//RESTRICCIONS 190:...
		}
	}
	
	/**
	 * Retorna el significat d'un codi d'excepció
	 * @param index Codi d'excepció
	 * @return Retorna un String que identifica el codi d'excepció.
	 */
	static public String ExceptionToStr(int index) {
		return titles[index];
	}
}
