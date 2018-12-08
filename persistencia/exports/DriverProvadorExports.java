package persistencia.exports;

import java.util.*;
import domini.classes.*;
import domini.restriccions.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverProvadorExports {
	
	private static String path = "/home/blackaichi/Desktop/codificar_classes/export";
	
	/**
	 * Totes les instancies dels exports
	 */
	private static ExportaPlaEstudis plaEstudis = ExportaPlaEstudis.getInstancia();
	private static ExportaCampus campus = ExportaCampus.getInstancia();
	private static ExportaAula aula = ExportaAula.getInstancia();
	private static ExportaAssignatura assignatura = ExportaAssignatura.getInstancia();
	private static ExportaData data = ExportaData.getInstancia();
	private static ExportaGrup grup = ExportaGrup.getInstancia();
	private static ExportaHorari horari = ExportaHorari.getInstancia();
	private static ExportaHoresAptes horesAptes = ExportaHoresAptes.getInstancia();
	private static ExportaSessioGAssignada sessioGAssignada = ExportaSessioGAssignada.getInstancia();
	private static ExportaSessioGrup sessioGrup = ExportaSessioGrup.getInstancia();
	private static ExportaSessioSGAssignada sessioSGAssignada = ExportaSessioSGAssignada.getInstancia();
	private static ExportaSessioSubGrup sessioSubGrup = ExportaSessioSubGrup.getInstancia();
	private static ExportaSolapaments solapaments = ExportaSolapaments.getInstancia();
	private static ExportaSubGrup subGrup = ExportaSubGrup.getInstancia();
	
	/**
	 * Comprova l'Export que es descomenti
	 * @param args no entra res
	 * @throws Exception
	 */
	public static void main (String [] args) throws Exception {
		//provaPlaEstudis();
		provaCampus();
		//provaAula();
		//provaAssignatura();
		//provaData();
		//provaGrup();
		//provaHorari();
		//provaHoresAptes();
		//provaSessioGAssignada();
		//provaSessioGrup(); 
		//provaSessioSGAssignada();
		//provaSessioSubGrup(); 
		//provaSolapaments(); // TODO
		//provaSubGrup(); 
	}
	
	/**
	 * Comprova l'Export de SubGrup
	 * @throws Exception
	 */
	private static void provaSubGrup() throws Exception {
		
	}

	/**
	 * Comprova l'Export de Solapaments
	 * @throws Exception
	 */
	private static void provaSolapaments() throws Exception {
		
	}

	/**
	 * Comprova l'Export de SessioSubGrup
	 * @throws Exception
	 */
	private static void provaSessioSubGrup() throws Exception {
		
	}

	/**
	 * Comprova l'Export de SessioSGAssignada
	 * @throws Exception
	 */
	private static void provaSessioSGAssignada() throws Exception {
		
	}

	/**
	 * Comprova l'Export de SessioGrup
	 * @throws Exception
	 */
	private static void provaSessioGrup() throws Exception {
		
	}

	/**
	 * Comprova l'Export de SessioGAssignada
	 * @throws Exception
	 */
	private static void provaSessioGAssignada() throws Exception {
		
	}

	/**
	 * Comprova l'Export d'HoresAptes
	 * @throws Exception
	 */
	private static void provaHoresAptes() throws Exception{
		
	}
	
	/**
	 * Comprova l'Export d'Horari
	 * @throws Exception
	 */
	private static void provaHorari() throws Exception {
		
	}

	/**
	 * Comprova l'Export de Grup
	 * @throws Exception
	 */
	private static void provaGrup() throws Exception {
		
	}

	/**
	 * Comprova l'Export de Data
	 * @throws Exception
	 */
	private static void provaData() throws Exception {
		
	}
	
	/**
	 * Comprova l'Export d'Assignatura
	 * @throws Exception
	 */
	private static void provaAssignatura() throws Exception {
		
	}
	
	/**
	 * Comprova l'Export d'Aula
	 * @throws Exception
	 */
	private static void provaAula() throws Exception {
		HashSet<String> equip = new HashSet<String>();
		equip.add("PCs");
		equip.add("projector");
		aula.exportaAula(path, "A5102", "eric", 20, equip, true);
	}
	
	/**
	 * Comprova l'Export de Campus
	 * @throws Exception
	 */
	private static void provaCampus() throws Exception {
		String[] aules = new String[2];
		aules[0] = "a5102";
		aules[1] = "a6102";
		campus.exportaCampus(path, "campus nord", "eric", aules, true);
	}
	
	/**
	 * Comprova l'Export de PlaEstudis
	 * @throws Exception
	 */
	private static void provaPlaEstudis() throws Exception {
		
	}
}
