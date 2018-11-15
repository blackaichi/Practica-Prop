package classes;
import java.util.*;
import utils.*;
/**
 * 
 * @author adria.maneros@est.fib.upc.edu
 *
 */
public class PlaEstudis {
			
	/////////////////////////////////////////////////////////////
	////////////////////////Variables //////////////////////////
		
	private static HashSet<String> plansEstudis = new HashSet<String>();
	
	/**
	 * Nom  del Pla d'estudis
	 */
	private String nom;
		
	/**
	 * Horari lectiu per dia del Pla d'Estudis
	 */
	Map<Integer, boolean[] > franja;
		
	/**
	 * Assignatures que pertanyen al Pla d'Estudis
	 */
	HashSet<Assignatura> assignatures;
	
	/**
	 * Rang del Pla d'estudis
	 */
	int[] rangDia;
			
	//////////////////////////////////////////////////////////
	//////////////////////  Privades  ///////////////////////
	
	/**
	 * Retorna cert si ja existeix un Pla d'Estudis amb el mateix nom.
	 * @param nom: nom del Pla d'Estudis que volem comprobar.
	 * @return Cert si el Pla d'Estudis ja existeix o fals altrament.
	 *//*
	private boolean checkPlansEstudis(String nom) {
		for(PlaEstudis p : plansEstudis) {
			if (p.getNom().equals(nom)) return false;
		}
		return true;
	}*/
	
	/**
	 * Retorna cert si ja existeix una Assignatura amb el mateix nom en aquest Pla d'Estudis.
	 * @param nom: nom de l'Assignatura que volem comprobar.
	 * @return Cert si l'Assignatura ja existeix o fals altrament.
	 */
	public boolean checkAssignatura(String nom) {
		if (nom == null) return false;
		for(Assignatura a : assignatures) {
			if (a.getNom().equals(nom)) return true;
		}
	
		return false;
	}	
		
	/**
	 * Retorna cert si ja existeix el dia al Map de Franja.
	 * @param dia: Dia que volem comprobar.
	 * @return Cert si el dia ja existeix o fals altrament.
	 */
	public boolean checkDiaFranja(int dia) {
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
		this.rangDia = new int[4];
		this.assignatures = new HashSet<Assignatura>();

	}
		
	/////////////////////////////////////////////////////////////
	//////////////////////// Setters  //////////////////////////	
		
	/**
	 * Assigna el nom del Pla d'Estudis.
	 * @param nom: nom del Pla d'Estudis que entra l'usuari.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setNom(String nom) {
		if (nom == null || nom.isEmpty()) return 18;
		else if(this.nom != null && this.nom.equals(nom)) return 1;
		else if(plansEstudis.contains(nom)) return 10;
		if (this.nom != null) plansEstudis.removeIf(item -> item.equals(this.nom));
		this.nom = nom;
		plansEstudis.add(this.nom);
		return 0;
	}
			
	/**
	 * Assigna la franja [iniciFranjaM,finalFranjaM,iniciFranjaT,finalFranjaT] al Pla d'Estudis.
	 * @param dia: dia de la franja que entra l'usuari.
	 * @param franja: Franja que entra l'usuari.
	 */
	public int setFranja(int dia, int[] franja) throws Exception {
		if (dia < 0 || dia > 6) return 11;
		else if (franja == null) return 12;
		else if (franja[0] > franja[1]) return 12;
		else {
			boolean[] valor = this.franja.get(dia);
			if (valor == null) {
				valor = new boolean[24];
			}

			for (int i = franja[0]; i < franja[1]; i++) {
				if(valor[i] = false) {
					valor[i] = true;
				}
			}
			this.franja.put(dia,valor);
		}
		return 0;
	}

	/**
	 * Assigna la franja [iniciFranjaM,finalFranjaM,iniciFranjaT,finalFranjaT] al Pla d'Estudis.
	 * @param dia: dia de la franja que entra l'usuari.
	 * @param franja: Franja que entra l'usuari.
	 */
	public int setFranja(int dia, boolean[] franja) throws Exception {
		if (dia < 0 || dia > 6) return 11;
		else if (franja == null) return 12;
		else this.franja.put(dia,franja);
		return 0;
	}
	
	/**
	 * Posa el rang corresponent al dia indicat.
	 * @param dia: Dia que volem posar el rang.
	 * @param rang: rang que volem assignar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setRangDia(int[] rang) throws Exception {
		if(rang == null || rang.length != 4) return 16;
		this.rangDia = rang;
		return 0;
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////// Getters  //////////////////////////
			
	/**
	 * Retorna el Nom del Pla d'Estudis.
	 * @return Nom del Pla d'Estudis.
	 */
	public String getNom() {
		return this.nom;
	}
			
	/**
	 * Retorna la franja del dia indicat del Pla d'Estudis.
	 * @return Franja del dia en cas que el dia tingui una franja associada. Altrament retorna null.
	 */
	public boolean[] getFranjaDia(int dia) {
		if(checkDiaFranja(dia))	return this.franja.get(dia);
		else return null;
	}
		
	/**
	 * Retorna la franja la setmana del Pla d'Estudis.
	 * @return Franja de la setmana.
	 */
	public Map<Integer, boolean[]> getFranjaSetmana() {
		return this.franja;
	}
	
	/**
	 * Retorna el rang corresponent del Matí del dia indicat.
	 * @param dia: Dia que volem demanar el rang.
	 * @return rang de la matí del dia indicat.
	 */
	public int[] getRangMati() {
		int[] rangmati = new int[2];
		rangmati[0] = this.rangDia[0];
		rangmati[1] = this.rangDia[1];
		return rangmati;
	}
	
	/**
	 * Retorna el rang corresponent de la tarda del dia indicat.
	 * @param dia: Dia que volem demanar el rang.
	 * @return rang de la tarda del dia indicat.
	 */
	public int[] getRangTarda() {
		int[] rangmati = new int[2];
		rangmati[0] = this.rangDia[2];
		rangmati[1] = this.rangDia[3];
		return rangmati;
	}
	
	/**
	 * Retorna el set d'Assignatures que pertanyen al Pla d'Estudis.
	 * @return Set d'Assignatures del pla.
	 */
	public HashSet<Assignatura> getAssignatures() {
		return this.assignatures;
	}
	
	/**
	 * Retorna el set de SessionsGrupAssignades que pertanyen al Pla d'Estudis.
	 * @return Set d'Assignatures del pla.
	 */
	public HashSet<SessioGAssignada> getSessionsGrupA() throws Exception {
		HashSet<SessioGrup> sessionsG = new HashSet<SessioGrup>();
		HashSet<SessioGAssignada> sessionsGA = new HashSet<SessioGAssignada>();
		
		for (Assignatura a : this.assignatures) {
			sessionsG.addAll(a.getSessionsG());
		}
		for (SessioGrup sg : sessionsG) {
			sessionsGA.addAll(sg.getAllSessionsGA());
		}
		return sessionsGA;
	}
	
	/**
	 * Retorna el set de SessionsGrupAssignades que pertanyen al Pla d'Estudis.
	 * @return Set d'Assignatures del pla.
	 */
	public HashSet<SessioSGAssignada> getSessionsSubGrupA() throws Exception {
		HashSet<SessioSubGrup> sessionsSG = new HashSet<SessioSubGrup>();
		HashSet<SessioSGAssignada> sessionsSGA = new HashSet<SessioSGAssignada>();
		
		for (Assignatura a : this.assignatures) {
			sessionsSG.addAll(a.getSessionsSG());
		}
		for (SessioSubGrup ssg : sessionsSG) {
			sessionsSGA.addAll(ssg.getAllSessionsSGA());
		}
		return sessionsSGA;
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
	public int altaAssignatura(String nom) throws Exception{
		if (this.checkAssignatura(nom)) return 13;
		assignatures.add(new Assignatura(this,nom));
		return 0;
	}
		
	/**
	 * Dona de baixa l'Assignatura = nom sempre i quan existeixi en aquest Pla d'Estudis.
	 * @param nom: nom de l'Assignatura que volem donar de baixa.
	 * @return Excepció codificada en forma d'enter.
	 * @throws Exception 
	 */
	public int baixaAssignatura(String nom) throws Exception {
		if (checkAssignatura(nom)) assignatures.removeIf(item -> item.getNom().equals(nom));
		else return 14;
		return 0;
	}
		
	/**
	 * Elimina la franja [iniciFranjaM,finalFranjaM,iniciFranjaT,finalFranjaT] sempre i quan aquest dia tingui franjes assignades.
	 * @param dia: Dia que volem eliminar la franja.
	 * @param iniciFranja: Hora d'inici de la franja a eliminar.
	 * @param finalFranja: Hora final de la franja a eliminar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int delFranja(int dia, int[] franja) throws Exception {
		if (dia < 0 || dia > 6) return 11;
		else if (franja == null) return 12;
		else if (franja[0] > franja[1]) return 12;
		else {
			boolean[] valor = this.franja.get(dia);
			if (valor == null) return 17;
			for (int i = franja[0]; i < franja[1]; i++) {
				if(valor[i] = true) {
					valor[i] = false;
				}
			}
			this.franja.put(dia,valor);
		}
		return 0;
	}
	
	/**
	 * Retorna el numero d'assignatures donades d'alta en la classe PlaEstudis
	 * @return assignatures.size()
	 */
	public int quantesAssignatures() {
		return this.assignatures.size();
	}
	
	/**
	 * Retorna quants plans d'estudis hi han
	 * @return quants plans d'estudis hi han
	 */
	public static int quantsPlansEstudis() {
		return plansEstudis.size();
	}
}