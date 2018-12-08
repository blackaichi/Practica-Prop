package persistencia.exports;

public class ExportaSegment extends Exporta {
	
	/**
	 * Instancia de la propia classe seguint el patró de disseny singleton
	 */
	private static ExportaSegment instancia = new ExportaSegment();
	
	/**
	 * Creadora buida
	 */
	private ExportaSegment() {};
	
	/**
	 * Retorna l'única instancia de la classe
	 * @return una instancia de la classe 
	 */
	public static ExportaSegment getInstancia() {
		return instancia;
	}
	
	/**
	 * Exporta un Segment
	 * @param s segment que volem exportar
	 * @param crea true si volem que escrigui al fitxer, false si només volem retornar la codificació
	 * @return la codificació del Segment
	 */
	public String exportaSegment(String path, int dia, int hora, String nomC, String nomAula, String nomPE,
			String nomAssig, String tipus, int hores, int numGrup, int numsg, boolean sessiogrup, boolean crea) {
		String endl = "\n";
		String str = "Segment".concat(endl);
		ExportaData ed = ExportaData.getInstancia();
		str = str.concat(ed.exportaData(path, dia, hora, false)).concat(endl);
		str = str.concat(cp.getAula(path, nomC, nomAula)).concat(endl);
		if (sessiogrup) {
			str = str.concat("g").concat(endl);
			//str = str.concat();// TODO
		}
		else {
			str = str.concat("sg").concat(endl);
			//str = str.concat(); // TODO
		}
		str = str.concat(endl).concat("END").concat(endl);
		if (crea) Exporta.exporta(path, str);
		return str;
	}
}
