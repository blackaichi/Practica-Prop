package persistencia.exports;

import domini.classes.SessioGAssignada;
import domini.classes.SessioSGAssignada;
import utils.*;

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
	public String exportaSegment(Segment s, boolean crea) {
		String endl = "\n";
		String str = "Segment".concat(endl);
		ExportaData ed = ExportaData.getInstancia();
		ExportaAula ea = ExportaAula.getInstancia();
		ExportaSessioGAssignada esga = ExportaSessioGAssignada.getInstancia();
		ExportaSessioSGAssignada essga = ExportaSessioSGAssignada.getInstancia();
		str = str.concat(ed.exportaData(s.getData(), false)).concat(endl);
		str = str.concat(ea.exportaAula(s.getAula(), false)).concat(endl);
		Pair<SessioGAssignada, SessioSGAssignada> sessio = s.getSessio();
		if (sessio.snull()) {
			str = str.concat("g").concat(endl);
			str = str.concat(esga.exportaSessioGAssignada(sessio.first, false));
		}
		else {
			str = str.concat("sg").concat(endl);
			str = str.concat(essga.exportaSessioSGAssignada(sessio.second, false));
		}
		str = str.concat(endl).concat("END").concat(endl);
		if (crea) Exporta.exporta(str);
		return str;
	}
}
