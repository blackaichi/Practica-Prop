package persistencia.imports;

import java.io.*;
import java.util.*;
import domini.classes.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class ImportaPlaEstudis extends Importa {
	
	private String nom;
	/**
	 * Enregistra el nom de l'autor d'aquest pla d'estudis
	 */
	private String autor;
		
	/**
	 * Horari lectiu per dia del Pla d'Estudis
	 */
	private Map<Integer, boolean[] > lectiu;
		
	/**
	 * Assignatures que pertanyen al Pla d'Estudis
	 */
	private HashSet<Assignatura> assignatures;
	
	/**
	 * Rang del Pla d'estudis
	 */
	private int[] rangDia;

	private static ImportaPlaEstudis ipe;
	
	public static ImportaPlaEstudis getInstancia() {
		ipe = new ImportaPlaEstudis();
		return ipe;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public Map<Integer, boolean[]> getLectiu() {
		return lectiu;
	}
	
	public int[] getRangDia() {
		return rangDia;
	}
	
	public HashSet<Assignatura> getAssignatures() {
		return assignatures;
	}
	
	public String importaPlaEstudis(String path) {
		try {
			File file = new File(path); 
			BufferedReader br = new BufferedReader(new FileReader(file)); 
			String s; 
			Map<Integer, boolean[]> lectiu = new HashMap<Integer, boolean[]>();
			HashSet<Assignatura> assignatures = new HashSet<Assignatura>();
			int[] rangDia = new int[24];
			s = br.readLine();
			if (!s.equals("Data")) {
				br.close();
				return "No es un fitxer amb un PlaEstudis";
			}
			nom = br.readLine();
			autor = br.readLine();
			char[] c = br.readLine().toCharArray();
			boolean[] franja = new boolean[24];
			int dia = 0;
			int i;
			for (i = 0; i < c.length;) {
				if (c[i] == 'n') {
					franja = null;
					lectiu.put(dia, franja);
					++i;
				}
				else {
					if (i+23 > c.length) {
						br.close();
						return "error falten dades a la franja";
					}
					for (int j = 0; j < 24; ++j) {
						if (c[i+j] == 't') franja[j] = true;
						else if (c[i+j] == 'f') franja[j] = false;
						else {
							br.close();
							return "error no es ni una t, ni una f, ni una n";
						}
					}
					lectiu.put(dia, franja);
					i += 24;
				}
				++dia;
			}
			if (dia != 7) {
				br.close();
				return "No estan tots els dies de la franja de l'horari lectiu";
			}
			if (c.length != i+1) {
				br.close();
				return "Hi ha més lletres de les que toquen";
			}
			s = br.readLine();
			String[] tokens = s.split(" ");
			i = 0;
			if (tokens.length > 6 || tokens.length < 4) {
				br.close();
				return "Rang del dia incorrecte";
			}
			if (tokens[i] != "mati:") {
				br.close();
				return "Sintaxi incorrecta a rangDia";
			}
			++i;
			if (tokens[i] == "null") {
				rangDia[0] = -1;
				rangDia[1] = -1;
			}
			else if (tokens[i].chars().allMatch(Character::isDigit) && tokens[i+1].chars().allMatch(Character::isDigit)) {
				rangDia[0] = Integer.valueOf(rangDia[0]);
				rangDia[1] = Integer.valueOf(rangDia[1]);
				++i;
			}
			else {
				br.close();
				return "Sintaxi incorrecta a rangDia";
			}
			++i;
			if (tokens[i] != "tarda:") {
				br.close();
				return "Sintaxi incorrecta a rangDia";
			}
			++i;
			if (tokens[i] == "null") {
				rangDia[2] = -1;
				rangDia[3] = -1;
			}
			else if (tokens[i].chars().allMatch(Character::isDigit) && tokens[i+1].chars().allMatch(Character::isDigit)) {
				rangDia[2] = Integer.valueOf(rangDia[0]);
				rangDia[3] = Integer.valueOf(rangDia[1]);
				++i;
			}
			else {
				br.close();
				return "Sintaxi incorrecta a rangDia";
			}
			int nassig = Integer.valueOf(br.readLine());
			ImportaAssignatura ia = ImportaAssignatura.getInstancia();
			assignatures = ia.importaAssignatura(path, nassig);
			if (assignatures == null) {
				br.close();
				return "error important les assignatures del pla";
			}
			br.close();
			return null;
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}

}
