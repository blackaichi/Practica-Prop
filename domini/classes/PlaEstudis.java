package domini.classes;
import java.util.*;

import domini.restriccions.*;
import utils.*;
/**
 * 
 * @author adria.maneros@est.fib.upc.edu
 *
 */
public class PlaEstudis {
			
	/////////////////////////////////////////////////////////////
	////////////////////////Variables //////////////////////////
	/**
	 * Enregistra TOTS els plans d'estudis creats.
	 */
	private static HashSet<PlaEstudis> plansEstudis;
	
	/**
	 * Nom  del Pla d'estudis
	 */
	private String nom;
	/**
	 * Enregistra el nom de l'autor d'aquest pla d'estudis
	 */
	private String autor;
		
	/**
	 * Horari lectiu per dia del Pla d'Estudis
	 */
	Map<Integer, boolean[] > lectiu;
		
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
	public boolean checkDiaLectiu(int dia) {
		return this.lectiu.containsKey(dia);
	}
		
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
			
	/**
	 * Creadora de Pla d'Estudis amb parametres.
	 * @param nom: nom del Pla d'Estudis. 
	 */
	private PlaEstudis(String nom) throws Exception {
		ExceptionManager.thrower(this.setNom(nom));
		this.lectiu = new HashMap<Integer,boolean[] >();
		this.rangDia = new int[4];
		Arrays.fill(this.rangDia, -1);
		this.assignatures = new HashSet<Assignatura>();
		this.autor = new String("Desconegut");
	}
	
	/**
	 * Dona d'alta un pla d'estudis.
	 * @param nom Identifica el pla d'estudis.
	 * @throws Exception
	 */
	public static void newPlaEstudis(String nom) throws Exception {
		if(plansEstudis == null) plansEstudis = new HashSet<>();
		
		PlaEstudis toAdd = new PlaEstudis(nom);
		plansEstudis.add(toAdd);
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
		else if(this.nom == null) this.nom = nom;
		else if(this.nom != null && this.nom.equals(nom)) return 1;
		else if(this.nom == null && plansEstudis.contains(nom)) return 10;
		
		this.nom = nom;
		return 0;
	}
		
	/**
	 * Assigna un autor al pla d'estudis.
	 * @param autor Indica quin es l'autor del pla d'estudis.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setAutor(String autor) {
		if(autor == null || autor.isEmpty()) return 18;
		else if(autor.equals(this.autor)) return 1;
		
		this.autor = autor;
		return 0;
	}
	
	public int setLectiu(Map<Integer, boolean[]> lectiu) {
		if(lectiu == null) return -1; // TODO
		this.lectiu = lectiu;
		return 0;
	}
	
	/**
	 * Assigna la franja [iniciFranjaM,finalFranjaM,iniciFranjaT,finalFranjaT] al Pla d'Estudis.
	 * @param dia: dia de la franja que entra l'usuari.
	 * @param franja: Franja que entra l'usuari.
	 */
	public int setLectiu(int dia, int[] franja) throws Exception {
		if (dia < 0 || dia > 6) return 11;
		else if (franja == null) return 12;
		else if (franja[0] > franja[1]) return 12;
		else if (franja.length != 2) return 19;
		else {
			boolean[] valor = this.lectiu.get(dia);
			if (valor == null) {
				valor = new boolean[24];
			}

			for (int i = franja[0]; i < franja[1]; i++) {
				if(!valor[i]) {
					valor[i] = true;
				}
			}
			this.lectiu.put(dia,valor);
		}
		return 0;
	}

	/**
	 * Assigna la franja [iniciFranjaM,finalFranjaM,iniciFranjaT,finalFranjaT] al Pla d'Estudis.
	 * @param dia: dia de la franja que entra l'usuari.
	 * @param franja: Franja que entra l'usuari.
	 */
	public int setLectiu(int dia, boolean[] franja) throws Exception {
		if (dia < 0 || dia > 6) return 11;
		else if (franja == null) return 12;
		else this.lectiu.put(dia,franja);
		return 0;
	}
	
	/**
	 * Posa el rang corresponent al dia indicat.
	 * @param dia: Dia que volem posar el rang.
	 * @param rang: rang que volem assignar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setRangDia(int[] rang) {
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
	 * Retorna l'autor d'aquest pla d'estudis.
	 * @return String mai buit.
	 */
	public String getAutor() {
		return this.autor;
	}
	
	/**
	 * Retorna la franja del dia indicat del Pla d'Estudis.
	 * @return Franja del dia en cas que el dia tingui una franja associada. Altrament retorna null.
	 */
	public boolean[] getLectiuDia(int dia) {
		if(checkDiaLectiu(dia))	return this.lectiu.get(dia);
		else return null;
	}
		
	/**
	 * Retorna la franja la setmana del Pla d'Estudis.
	 * @return Franja de la setmana.
	 */
	public Map<Integer, boolean[]> getLectiuSetmana() {
		return this.lectiu;
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
			
	public Assignatura getAssignatura(String nom) {
		if(nom == null) return null;
		for(Assignatura assig: this.assignatures)
			if(assig.getNom().equals(nom)) return assig;
		
		return null;
	}
	
	static public PlaEstudis getPlaEstudis(String nom) {
		if(nom == null) return null;
		for(PlaEstudis pla: plansEstudis)
			if(pla.getNom().equals(nom)) return pla;
		
		return null;
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
		
		return Solapaments.kill(this.nom, nom, 0);
	}
		
	/**
	 * Elimina la franja [iniciFranjaM,finalFranjaM,iniciFranjaT,finalFranjaT] sempre i quan aquest dia tingui franjes assignades.
	 * @param dia: Dia que volem eliminar la franja.
	 * @param iniciFranja: Hora d'inici de la franja a eliminar.
	 * @param finalFranja: Hora final de la franja a eliminar.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int delLectiu(int dia, int[] franja) throws Exception {
		if (dia < 0 || dia > 6) return 11;
		else if (franja == null) return 12;
		else if (franja[0] > franja[1]) return 12;
		else {
			boolean[] valor = this.lectiu.get(dia);
			if (valor == null) return 17;
			for (int i = franja[0]; i < franja[1]; i++) {
				if(valor[i] = true) {
					valor[i] = false;
				}
			}
			this.lectiu.put(dia,valor);
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

	static public void eliminaPlaEstudis(String nom) {
		plansEstudis.removeIf(item -> item.getNom().equals(nom));
	}
	
	static public HashSet<String> getKeys(){
		HashSet<String> keys = new HashSet<>();
		if(PlaEstudis.plansEstudis != null)
			for(PlaEstudis pla: PlaEstudis.plansEstudis)
				keys.add(pla.getNom());
		
		return keys;
	}
}