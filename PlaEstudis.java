package classes;
import java.util.*;
/**
 * 
 * @author adria.maneros@est.fib.upc.edu
 *
 */
public class PlaEstudis {
			
	/////////////////////////////////////////////////////////////
	////////////////////////Variables //////////////////////////
		
	private static HashSet<PlaEstudis> plansEstudis = new HashSet<PlaEstudis>();
	
	/**
	 * Nom  del Pla d'estudis
	 */
	private String nom;
		
	/**
	 * Hores de teoria  de l'Assignatura
	 */
	Map<Integer, boolean[] > franja;
		
	/**
	 * Assignatures que pertanyen al Pla d'Estudis
	 */
	HashSet<Assignatura> assignatures;
			
	//////////////////////////////////////////////////////////
	//////////////////////  Privades  ///////////////////////
	
	private boolean checkPlansEstudis(String nom) {
		for(PlaEstudis p : plansEstudis) {
			if (p.getNom().equals(nom)) return false;
		}
		return true;
	}
	
	/**
	 * Retorna si ja existeix una Assignatura amb el mateix nom en aquest Pla d'Estudis.
	 * @param nom: nom de l'Assignatura que volem comprobar.
	 * @return Cert si l'Assignatura ja existeix o fals altrament.
	 */
	private boolean checkAssignatura(String nom) {
		for(Assignatura a : assignatures) {
			if (a.getNom().equals(nom)) return true;
		}
		return false;
	}	
		
	/**
	 * Retorna si ja existeix el dia al Map de Franja.
	 * @param dia: Dia que volem comprobar.
	 * @return Cert si el dia ja existeix o fals altrament.
	 */
	private boolean checkDiaFranja(int dia) {
		return this.franja.containsKey(dia);
	}
		
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
			
	/**
	 * Creadora de Pla d'Estudis amb parametres.
	 * @param nom: nom del Pla d'Estudis. 
	 */
	public PlaEstudis(String nom) throws Exception {
		ExceptionManager.thrower(this.setNom(nom));

		this.franja = new HashMap<Integer,boolean[] >();
	}
			
	/**
	 * Creadora de Pla d'Estudis sense parametres.
	 */
	public PlaEstudis() throws Exception {
		nom = new String("NAN");
		this.franja = new HashMap<Integer, boolean[] >();
	}
		
	/////////////////////////////////////////////////////////////
	//////////////////////// Setters  //////////////////////////	
		
	/**
	 * Assigna el nom del Pla d'Estudis.
	 * @param nom: nom del Pla d'Estudis que entra l'usuari.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setNom(String nom) {
		if(this.nom.equals(nom)) return 1;
		else if(!checkPlansEstudis(nom)) return 30;
		this.nom = nom;
		return 0;
	}
			
	/**
	 * Assigna la franja del Pla d'Estudis.
	 * @param dia: dia de la franja que entra l'usuari.
	 * @param iniciFranja: Hora d'inici de la franja que entra l'usuari.
	 * param finalFranja: Hora final de la franja que entra l'usuari.
	 */
	public int setFranja(int dia, int iniciFranja, int finalFranja) throws Exception {
		if (dia < 0 || dia > 6) return 32;
		else if (iniciFranja < 0 || finalFranja < 0 || iniciFranja > 24 || finalFranja > 24) return 33;
		else if (iniciFranja >= finalFranja) return 34;
		else {
			boolean[] valor = this.franja.get(dia);
			if (valor == null) {
				valor = new boolean[24];
			}

			for (int i = iniciFranja; i < finalFranja; i++) {
				if(valor[i] = false) {
					valor[i] = true;
				}
			}
			this.franja.put(dia,valor);
		}
		return 0;
	}
			
		/*	public int setAssignatura(Assignatura assig) {
				if (this.assig == assig) return 1;
				else if (assig == null) return 31;
				this.assig = assig;
				
				return 0;
			}*/
			
	/////////////////////////////////////////////////////////////
	//////////////////////// Getters  //////////////////////////
			
	/**
	 * Retorna el Nom del Pla d'Estudis.
	 * @return Nom del Pla d'Estudis.
	 */
	public String getNom() {
		return nom;
	}
			
	/**
	 * Retorna la franja del dia indicat del Pla d'Estudis.
	 * @return Franja del dia en cas que el dia tingui una franja associada. Altrament retorna null.
	 */
	boolean[] getFranjaDia(int dia) {
		if(checkDiaFranja(dia))	return this.franja.get(dia);
		else return null;
	}
		
	/**
	 * Retorna la franja la setmana del Pla d'Estudis.
	 * @return Franja de la setmana.
	 */
	Map<Integer, boolean[]> getFranjaSetmana() {
		return this.franja;
	}
			
	/////////////////////////////////////////////////////////////
	//////////////////////// Modificadores /////////////////////
			
	/** 
	 * Crea una nova Assignatura dins el Pla d'estudis sempre i quant els atributs entrats
	 * compleixin amb les especificacions. Altrament llança una excepció.
	 * @param nom: Indica el nom que identificarà a l'Assignatura.
	 * @param hteo: Indica quantes hores de teoria té l'Assignatura.
	 * @param hlab: Indica quantes hores de laboratori té l'assignatura.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int altaAssignatura(String nom, int hteo, int hlab) throws Exception{
		if (this.checkAssignatura(nom)) return 32;
		assignatures.add(new Assignatura(nom,hteo,hlab));
		return 0;
	}
		
	/**
	 * Dona de baixa l'Assignatura = nom sempre i quan existeixi en aquest Pla d'Estudis.
	 * @param nom: nom de l'Assignatura que volem donar de baixa.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int baixaAssignatura(String nom) {
		if (checkAssignatura(nom)) assignatures.removeIf(item -> item.getNom().equals(nom));
		else return 35;
		return 0;
	}
		
	/**
	 * Elimina la franja [iniciFranja,finalFranja] sempre i quan aquest dia tingui franjes assignades.
	 * @param dia: Dia que volem eliminar la franja.
	 * @param iniciFranja: Hora d'inici de la franja a eliminar.
	 * @param finalFranja: Hora final de la franja a eliminar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int treuFranja(int dia, int iniciFranja, int finalFranja) {
		boolean[] valor = this.franja.get(dia);
		if (valor == null) return 36;
		else {
			for (int i = iniciFranja; i < finalFranja; i++) {
				if(valor[i] = true) {
					valor[i] = false;
				}
			}
			this.franja.put(dia,valor);
		}
		return 0;
	}
		
}