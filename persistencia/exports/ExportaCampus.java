package persistencia.exports;

import java.io.*;
import java.util.HashSet;

import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ExportaCampus extends Exporta {
	
	private static ExportaCampus instancia = new ExportaCampus();
	
	private ExportaCampus() {};
	
	public static ExportaCampus getInstancia() {
		return instancia;
	}
	
	public String exportaCampus(Campus c, boolean crea) throws IOException {
		String endl = "\n";
		String str = "Campus".concat(endl);
		ExportaAula ea = ExportaAula.getInstancia();
		str = str.concat(c.getNom().concat(endl));
		str = str.concat(c.getAutor().concat(endl));
		HashSet<Aula> aules = c.getAllAules();
		str = str.concat(String.valueOf(aules.size()).concat(endl).concat("{").concat(endl));
		for (Aula a : aules) str = str.concat(ea.exportaAula(a, false)).concat(endl);
		str = str.concat("}").concat(endl);
		str = str.concat("END");
		if (crea) Exporta.exporta(str);
		return str;
	}
}
