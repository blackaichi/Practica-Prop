package persistencia.exports;

import java.util.*;
import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaAssignatura extends Exporta {
	
	private static ExportaAssignatura instancia = new ExportaAssignatura();
	
	private ExportaAssignatura() {};
	
	public static ExportaAssignatura getInstancia() {
		return instancia;
	}
	
	public String exportaAssignatura(Assignatura a, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Assignatura".concat(endl);
		ExportaSessioGrup esg = ExportaSessioGrup.getInstancia();
		ExportaSessioSubGrup essg = ExportaSessioSubGrup.getInstancia();
		ExportaGrup eg = ExportaGrup.getInstancia();
		ExportaHoresAptes eha = ExportaHoresAptes.getInstancia();
		ExportaSolapaments es = ExportaSolapaments.getInstancia();
		str = str.concat(a.getNom().concat(endl));
		HashSet<SessioGrup> sessionsG = a.getSessionsG();
		str = str.concat(String.valueOf(sessionsG.size()).concat(endl).concat("{").concat(endl));
		for (SessioGrup sg : sessionsG) str = str.concat(esg.exportaSessioGrup(sg, false)).concat(endl);
		str = str.concat("}").concat(endl);
		HashSet<SessioSubGrup> sessionsSG = a.getSessionsSG();
		str = str.concat(String.valueOf(sessionsSG.size()).concat(endl).concat("{").concat(endl));
		for (SessioSubGrup ssg : sessionsSG) str = str.concat(essg.exportaSessioSubGrup(ssg, false)).concat(endl);
		str = str.concat("}").concat(endl);
		HashSet<Grup> grup = a.getGrups();
		str = str.concat(String.valueOf(grup.size()).concat(endl).concat("{").concat(endl));
		for (Grup g : grup) str = str.concat(eg.exportaGrup(g, false)).concat(endl);
		str = str.concat("}").concat(endl);
		str = str.concat(eha.exportaHoresAptes(a.getHoresAptes(), false)).concat(endl);
		str = str.concat(es.exportaSolapaments(a.getSolapaments(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
	
}
