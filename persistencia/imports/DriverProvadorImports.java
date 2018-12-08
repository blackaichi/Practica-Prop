package persistencia.imports;

import java.util.Map;

import domini.classes.*;
import persistencia.exports.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverProvadorImports {
	
	private static String path = "/home/blackaichi/Desktop/codificar_classes/export";
	
	private static String error;
	
	private static ExportaPlaEstudis epe = ExportaPlaEstudis.getInstancia();
	private static ExportaCampus ec = ExportaCampus.getInstancia();
	private static ExportaAula ea = ExportaAula.getInstancia();
	private static ExportaAssignatura eas = ExportaAssignatura.getInstancia();
	private static ExportaData ed = ExportaData.getInstancia();
	private static ExportaGrup eg = ExportaGrup.getInstancia();
	private static ExportaHorari eh = ExportaHorari.getInstancia();
	private static ExportaHoresAptes eha = ExportaHoresAptes.getInstancia();
	private static ExportaSessioGAssignada esga = ExportaSessioGAssignada.getInstancia();
	private static ExportaSessioGrup esg = ExportaSessioGrup.getInstancia();
	private static ExportaSessioSGAssignada essga = ExportaSessioSGAssignada.getInstancia();
	private static ExportaSessioSubGrup essg = ExportaSessioSubGrup.getInstancia();
	private static ExportaSolapaments es = ExportaSolapaments.getInstancia();
	private static ExportaSubGrup esubg = ExportaSubGrup.getInstancia();
	private static ImportaPlaEstudis ipe = ImportaPlaEstudis.getInstancia();
	private static ImportaCampus ic = ImportaCampus.getInstancia();
	private static ImportaAula ia = ImportaAula.getInstancia();
	private static ImportaAssignatura ias = ImportaAssignatura.getInstancia();
	private static ImportaData id = ImportaData.getInstancia();
	private static ImportaGrup ig = ImportaGrup.getInstancia();
	private static ImportaHorari ih = ImportaHorari.getInstancia();
	private static ImportaHoresAptes iha = ImportaHoresAptes.getInstancia();
	private static ImportaSessioGAssignada isga = ImportaSessioGAssignada.getInstancia();
	private static ImportaSessioGrup isg = ImportaSessioGrup.getInstancia();
	private static ImportaSessioSGAssignada issga = ImportaSessioSGAssignada.getInstancia();
	private static ImportaSessioSubGrup issg = ImportaSessioSubGrup.getInstancia();
	private static ImportaSolapaments is = ImportaSolapaments.getInstancia();
	private static ImportaSubGrup isubg = ImportaSubGrup.getInstancia();
	
	public static void main (String [] args) throws Exception {
		provaPlaEstudis(); // TODO
		//provaCampus(); // TODO
		//provaAula(); // TODO
		//provaAssignatura(); // TODO
		//provaData(); // TODO
		//provaGrup(); // TODO
		//provaHorari(); // TODO
		//provaHoresAptes(); // TODO
		//provaSessioGAssignada(); // TODO
		//provaSessioGrup(); // TODO
		//provaSessioSGAssignada(); // TODO
		//provaSessioSubGrup(); // TODO
		//provaSolapaments(); // TODO
		//provaSubGrup(); // TODO
	}
	
	private static void provaSubGrup() throws Exception {
		
	}

	private static void provaSolapaments() throws Exception {
		
	}

	private static void provaSessioSubGrup() throws Exception {
		
	}

	private static void provaSessioSGAssignada() throws Exception {
		
	}

	private static void provaSessioGrup() throws Exception {
		
	}

	private static void provaSessioGAssignada() throws Exception {
		
	}

	private static void provaHoresAptes() throws Exception {
		
	}

	private static void provaHorari() throws Exception {
		
	}

	private static void provaGrup() throws Exception {
		
	}

	private static void provaData() throws Exception {
		
	}
	
	private static void provaAssignatura() throws Exception {
		
	}
	
	private static void provaAula() throws Exception {
		
	}
	
	private static void provaCampus() throws Exception {
		
	}
	
	private static void provaPlaEstudis() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis pe = PlaEstudis.getPlaEstudis("fib");
		epe.exportaPlaEstudis(pe, true);
		ipe.importaPlaEstudis(path);
		System.out.println("nom: "+ipe.getNom());
		System.out.println("autor: "+ipe.getAutor());
		Map<Integer, boolean[]> franja = ipe.getLectiu();
		String str = "";
		if (franja == null) System.out.println("lectiu: null");
		else {
			boolean[] b;
			for (int i = 0; i < 7; ++i) {
				b = franja.get(i);
				if (b == null) str = str.concat("n");
				else {
					for (boolean p : b) {
						if (p) str = str.concat("t");
						else str = str.concat("f");
					}
				}
			}
			System.out.println("lectiu: "+str);
		}
		str = "";
		int[] rang = ipe.getRangDia();
		if (rang == null) str = str.concat("mati: null tarda: null");
		else {
			str = str.concat("mati: ");
			if (rang[0] == -1 || rang[1] == -1) str = str.concat("null ");
			else {
				str = str.concat(String.valueOf(rang[0]).concat(" "));
				str = str.concat(String.valueOf(rang[1]).concat(" "));
			}
			str = str.concat("tarda: ");
			if (rang[2] == -1 || rang[3] == -1) str = str.concat("null").concat("\n");
			else {
				str = str.concat(String.valueOf(rang[0]).concat(" "));
				str = str.concat(String.valueOf(rang[1]).concat("\n"));
			}
		}
		System.out.println("rangDia: "+str);

	}
}
