package persistencia;

import java.util.*;

public class DadesSegment extends ExportaImporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static DadesSegment instancia = new DadesSegment();
	
	/**
	 * Creadora buida
	 */
	private DadesSegment() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static DadesSegment getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un segment
	 * @param path 
	 * @param nomAula
	 * @param nomAssig
	 * @param tipus
	 * @param numg
	 * @param numsg
	 * @param grup
	 */
	public void exportaSegment(String path, String nomAula,	String nomAssig, String tipus, int hores,
			int numg, int numsg, boolean grup) {
		String str = "";
		System.out.println(nomAula);
		if (nomAula == null) {
			exporta(path, "Segment\nbuit\nEND SEGM\n", false);
		}
		else {
			str = str.concat("Segment").concat(endl);
			str = str.concat(nomAula).concat(endl);
			str = str.concat(nomAssig.concat(" ")).concat(tipus.concat(" ")).concat(String.valueOf(hores).concat(" "));
			str = str.concat(String.valueOf(numg));
			if (!grup) str = str.concat(" ".concat(String.valueOf(numsg)));
			str = str.concat(endl).concat("END SEGM").concat(endl);
			exporta(path, str, false);
		}
	}
	
	public String importaSegment(String plaEst, String nomC, int dia, int hora, List<String> f,int id) {
		try {
			int i = 0;
			if (!f.get(i).equals("Segment")) return "no conte un segment el fitxer";
			while (i < f.size() && f.get(i++).equals("Segment")) {
					String aula,nomA,tipus,error;
					int hores,numg = -1, numsg = -1;
					aula = f.get(i++);
					String s = f.get(i++);
					String[] splited = s.split("\\s+");
					if (splited.length < 4 || splited.length > 5) System.out.println("el segment esta mal");
					nomA = splited[0];
					tipus = splited[1];
					hores = Integer.parseInt(splited[2]);
					numg = Integer.parseInt(splited[3]);
					if (splited.length == 5) numsg = Integer.parseInt(splited[4]);
					if (!f.get(i++).equals("END SEGM")) System.out.println("error en acabar fitxer segment");
					if ((error = cp.creaSegmentImportat(plaEst,nomC,dia,hora,aula,nomA,tipus,hores,numg,numsg,id)) != null) System.out.println(error);
			}
			return null;			
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}

	

