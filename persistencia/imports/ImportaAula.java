package persistencia.imports;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaAula extends Importa {
	
	private static ImportaAula instancia = new ImportaAula();
	
	private ImportaAula() {};
	
	public static ImportaAula getInstancia() {
		return instancia;
	}

	public String importaAula(String path, String nomC) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
		    String s;
			if (br.readLine() != "Aula") {
				br.close();
				return "no es un aula el fitxer";
			}
			String nomA = br.readLine();
			int capacitat = Integer.parseInt(br.readLine());
			s = br.readLine();
			String[] equipament = s.split(",");
			HashSet<String> equip = new HashSet<String>();
			for (int j = 0; j < equipament.length; ++j) {
				equip.add(equipament[j]);
			}
			if (s != "END") {
				br.close();
				return "No finalitza correctament";
			}
			cp.creaAulaImportada(nomC, nomA, capacitat, equip);
			br.close();
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
	
	public String importaAula(Vector<String> s, HashSet<String> nomaules, HashSet<Integer> capacitat,
			HashSet<HashSet<String>> equip) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			List<String> lines = Collections.emptyList(); 
		    lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
		    String s;
		    int i = lines.indexOf("Aula");
			while (lines.get(i) != "Aula") {
				nomaules.add(lines.get(i++));
				capacitat.add(Integer.parseInt(lines.get(i++)));
				s = lines.get(i++);
				String[] equipament = s.split(",");
				HashSet<String> aux = new HashSet<String>();
				for (int j = 0; j < equipament.length; ++j) {
					aux.add(equipament[j]);
				}
				equip.add(aux);
			}
			if (lines.get(i) != "END") {
				br.close();
				return "No finalitza correctament";
			}
			br.close();
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}

}
