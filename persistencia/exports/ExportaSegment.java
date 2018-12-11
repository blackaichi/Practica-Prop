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
	public void exportaSegment(String path, String nomAula,	String nomAssig, String tipus,
			int numg, int numsg, boolean grup) {
		String str = "";
		str = str.concat(nomAula).concat(endl);
		str = str.concat(nomAssig.concat(" ")).concat(tipus.concat(" "));
		if (grup) str = str.concat(String.valueOf(numg));
		else str = str.concat(String.valueOf(numsg));
		str = str.concat(endl).concat("END SEGM").concat(endl);
		Exporta.exporta(path, str, false);
	}
}
