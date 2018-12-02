package domini.drivers;

import java.util.*;
import domini.classes.*;
import utils.*;

public class DriverHorari {

	/**
	 * Funció main del driver, introdueixes un número i et comprova unes quantes funcions
	 * @throws Exception
	 */
	public static void main (String [ ] args) throws Exception {
		System.out.println("Benvingut a l'eina de comprovació de la classe Horari, agarrense que vienen curvas");
		
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis pe = PlaEstudis.getPlaEstudis("fib");
		boolean[] franja = new boolean[24];
		for(int i = 0; i < 24; i++) franja[i] = true;
		pe.setLectiu(0, franja);
		
		pe.altaAssignatura("prop"); 
		pe.getAssignatura("prop").altaSessioG("teo", 2);
		pe.getAssignatura("prop").altaSessioSG("lab", 2);
		
		pe.getAssignatura("prop").altaGrup(20, 30, "MT");
		pe.getAssignatura("prop").altaGrup(10, 25, "MT");
		pe.getAssignatura("prop").getGrup(10).altaSubGrup(11, 5, false);
		
		pe.getAssignatura("prop").getSessioG("teo", 2).assignaSessio(10);
		pe.getAssignatura("prop").getSessioSG("lab", 2).assignaSessio(10, 11);
		pe.getAssignatura("prop").getSessioG("teo", 2).assignaSessio(20);

		Campus.newCampus("campus nord");
		Campus c = Campus.getCampus("campus nord");
		c.altaAula("A1E01", 50);
		c.altaAula("A5001", 100);
		c.altaAula("A4201", 60);
		
		HashSet<String> f = new HashSet<>();
		f.add("D_LECTIU"); f.add("H_LECTIU"); //respectar horari lectiu del pla d'estudis
		f.add("G_HAPTES"); f.add("ASSIG_HAPTES"); //respectar hores aptes
		f.add("G_SOLAP"); f.add("ASSIG_SOLAP"); //respecta solapaments
		f.add("S_ALIGN"); //Alinear sessions
		
		int n =  2;
		Horari.GENERADOR(pe, c, f, n, true);
		for(Estructura struct : Horari.getHoraris(pe.getNom(), c.getNom()))
			Estructura.printHorari(struct);
		
		System.out.println("Madre mia willy compañero ha funcionat");
	}
}
