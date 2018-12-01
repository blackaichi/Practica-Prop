package persistencia.exports;

import java.util.*;
import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaAssignatura extends Exporta {
	public static String exportaAssignatura(Assignatura a, boolean crea) throws Exception {
		String endl = "\n";
		String str = "Assignatura".concat(endl);
		str = str.concat(a.getNom().concat(endl));
		HashSet<SessioGrup> sessionsG = a.getSessionsG();
		str = str.concat(String.valueOf(sessionsG.size()).concat(endl).concat("{").concat(endl));
		for (SessioGrup sg : sessionsG) str = str.concat(ExportaSessioGrup.exportaSessioGrup(sg, false)).concat(endl);
		str = str.concat("}").concat(endl);
		HashSet<SessioSubGrup> sessionsSG = a.getSessionsSG();
		str = str.concat(String.valueOf(sessionsSG.size()).concat(endl).concat("{").concat(endl));
		for (SessioSubGrup ssg : sessionsSG) str = str.concat(ExportaSessioSubGrup.exportaSessioSubGrup(ssg, false)).concat(endl);
		str = str.concat("}").concat(endl);
		HashSet<Grup> grup = a.getGrups();
		str = str.concat(String.valueOf(grup.size()).concat(endl).concat("{").concat(endl));
		for (Grup g : grup) str = str.concat(ExportaGrup.exportaGrup(g, false)).concat(endl);
		str = str.concat("}").concat(endl);
		str = str.concat(ExportaHoresAptes.exportaHoresAptes(a.getHoresAptes(), false)).concat(endl);
		str = str.concat(ExportaSolapaments.exportaSolapaments(a.getSolapaments(), false)).concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
	
}
