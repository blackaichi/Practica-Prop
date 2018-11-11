package classes;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 * @author eric.casanovas@est.fib.upc.edu
 * @author adria.manero@est.fib.upc.edu
 *
 */
public class ExceptionManager {
	
	static private String[] titles;
	
	/*!!!IMPORTANT!!!
	 * 1 - 	ABANS DE COMENÇAR A AFEGIR TITOLS, I PER TANT, MODIFICAR EL FITXER,
	 * 		PREGUNTEU SI ALGÚ L'ESTÀ MODIFICANT I ESPEREU A QUE EN FACI EL COMMIT
	 * 		DEFINITIU AL GIT. LLAVORS, DESCARREGUEU EL NOU FITXER I PODREU PROCEDIR.
	 * 
	 * 2 - 	SI US CAL UN RANG D'EXCEPCIONS MÉS GRAN DEL QUE TENIU ASSIGNAT
	 * 		COMENTEU-HO PEL GRUP DE PROP I DISCUTIREM SOBRE NOUS RANGS
	 * 		(SENSE MODIFICAR ELS QUE JA ESTAN ASSIGNATS).*/
	
	/**
	 * Inicialitza, si cal, l'array d'strings: titles.
	 * Altrament no fa res.
	 */
	static private void ExceptionManagerInitializer() {
		if(titles == null) {
			titles = new String[200];
			
			//DECLARACIONS GENERALS 0:9 :
			titles[0] = "NO_ERROR";
			titles[1] = "MODIFICACIÓ_INUTIL";
			//titles[2] = "INT_NEGATIU";
					
			//PLA ESTUDIS 10:29 :
			
			//ASSIGNATURA 30:49 :
			
			//GRUP 50: 69:
			titles[50] = "Identificació negativa.";
			titles[51] = "El número de Grup ja existeix en aquesta Assignatura.";
			titles[52] = "Grup amb capacitat negativa.";
			titles[53] = "Grup amb menys places que places sumen els seus SubGrups.";
			titles[54] = "Franja incorrecte.";
			titles[55] = "El grup ha de formar part d'una Assignatura.";
			titles[56] = "Nombre negatiu de places.";
			titles[57] = "No hi ha proutes places per tancar.";
			titles[58] = "El subGrup ja existeix en aquest Grup.";
			titles[59] = "Subgrup amb més places que el Grup.";
			titles[60] = "Subgrup amb capacitat negativa.";
			titles[61] = "No hi ha proutes places al Grup";
			titles[62] = "El grup ja conté una sessió amb el mateix tipus i hores";
			titles[63] = "L'assignatura a la qual pertany el grup no te cap sessio de Grup del tipus indicat.";
			titles[64] = "El grup ja conté aquesta sessió.";
			titles[65] = "La sessió no és del Grup corresponent.";
			titles[66] = "La sessió i el grup són d'assignatures diferents.";
			titles[67] = "El grup ja conté una sessió amb el mateix tipus i hores.";
			titles[68] = "La sessió no és del Grup corresponent.";
			titles[69] = "La sessió no és de la mateixa assignatura que el grup.";
			
			//SUBGRUP 70:89 :
			titles[70] = "El número de subGrup ja existeix al Grup.";
			titles[71] = "Nombre negatiu de places.";
			titles[72] = "Subgrup amb més places que el Grup.";
			titles[73] = "El subGrup ha de formar part d'un Grup";
			titles[74] = "No hi ha proutes places al grup";
			titles[75] = "No hi ha proutes places per tancar.";
			titles[76] = "El subGrup ja conté una sessió amb el mateix tipus i hores";
			titles[77] = "L'assignatura del grup al qual pertany el subGrup no te cap sessio de subGrup del tipus indicat.";
			titles[78] = "El subGrup ja conté aquesta sessió.";
			titles[79] = "La sessió no és del SubGrup corresponent.";
			titles[80] = "La sessió no és del Grup del SubGrup corresponent.";
			titles[81] = "La sessió i el grup del subGrup són d'assignatures diferents.";
			titles[82] = "El subGrup ja conté una sessió amb el mateix tipus i hores.";
			titles[83] = "La sessió a esborrar no és de la mateixa assignatura que el subGrup.";
			
			//SESSIO 90:109 :
			titles[90] = "L'assignatura no pot ser nula.";
			titles[91] = "L'hora no pot ser negativa ni 0.";
			titles[92] = "Tipus no pot ser un string buit.";
			titles[93] = "Tipus ha de contindre únicament lletres.";
			titles[94] = "La sessió no pot ser de més hores de les que són permeses a l'assignatura per Grup.";
			titles[95] = "El nombre de sessions ha de ser més gran que 0.";
			titles[96] = "El nombre d'hores del grup és inferior al nombre de sessions.";
			titles[97] = "No podem desassignar una sessió assignada si la seva sessió de grup i grup pertanyen a assignatures diferents.";
			titles[98] = "No podem afegir una sessió assignada que ja esta assignada.";
			titles[99] = "No podem afegir una sessió assignada si la seva sessió de grup i grup pertanyen a assignatures diferents.";
			titles[100] = "No podem eliminar una sessió assignada si la seva sessió de grup i grup pertanyen a assignatures diferents.";
			titles[101] = "La sessió no pot ser de més hores de les que són permeses a l'assignatura per SubGrup.";
			titles[102] = "El nombre d'hores del subgrup és inferior al nombre de sessions.";
			titles[103] = "No podem desassignar una sessió assignada si la seva sessió de subGrup i subGrup pertanyen a assignatures diferents.";
			titles[104] = "No podem afegir una sessió assignada si la seva sessió de subgrup i subgrup pertanyen a assignatures diferents.";
			titles[105] = "No podem eliminar una sessió assignada si la seva sessió de subgrup i subgrup pertanyen a assignatures diferents.";
			
			//SESSIO ASSIGNADA 110:129 :
			titles[110] = ".";
			titles[111] = ".";
			titles[112] = ".";
			titles[113] = ".";
			titles[114] = ".";
			titles[115] = ".";
			titles[116] = ".";
			titles[117] = ".";
			titles[118] = ".";
			titles[119] = ".";
			titles[120] = ".";
			titles[121] = ".";
			titles[122] = ".";
			titles[123] = ".";
			titles[124] = ".";
			titles[125] = ".";
			titles[126] = ".";
			titles[127] = ".";
			titles[128] = ".";
			titles[129] = ".";	
			
			//CAMPUS 130:149 :
			titles[130] = "No hi han aules"; 
			titles[131] = "Ja conté l'aula" ;
			titles[132] = "L'aula ja s'ha eliminat o no pertany al campus";
			
			
			//AULA 150:169 :
			titles[150] = "La capacitat de l'aula no pot ser 0 o negativa";
			titles[151] = "No hi ha material";
			titles[152] = "L'aula ha de formar part d'un campus";
			titles[153] = "Ja conte el material";
			titles[154] = "No conte el material";
			
			//HORARI 170:189 :
			
			//RESTRICCIONS 190:...
			
		}
	}
	
	/**
	 * Retorna el significat d'un codi d'excepció
	 * @param index Codi d'excepció
	 * @return Retorna un String que identifica el codi d'excepció.
	 */
	static public String getException(int index) {
		ExceptionManagerInitializer();
		return titles[index%titles.length]; // "%titles.length" és necessari per evitar cridar posicions fora de rang.
	}
	
	/**
	 * Llança l'excepció resultant a la decodificació del index (codi d'excepció) entrat.
	 * @param index Identifica l'excepció; n'és el codi.
	 * @throws Exception
	 */
	static public void thrower(int index) throws Exception {
		ExceptionManagerInitializer();
		if(index != 0) throw new Exception(getException(index));
	}
}