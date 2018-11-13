package classes;
import java.util.*;
/**
 * 
 * @author adria.manero@est.fib.upc.edu
 *
 */
public class Assignatura {
		
	/////////////////////////////////////////////////////////////
	////////////////////////Variables //////////////////////////
		
	/**
	 * Nom  de l'Assignatura
	 */
	private String nom;
	
	/**
	 * Hores de teoria  de l'Assignatura
	 */
	private int hteo;
		
	/**
	 * Hores de laboratori  de l'Assignatura
	 */
	private int hlab;
		
	/**
	 * Plans d'Estudis que pertanyen a l'Assignatura
	 */
	PlaEstudis plaEstudis;
		
	/**
	 * SessionsGrup que pertanyen a l'Assignatura
	 */
	HashSet<SessioGrup> sessionsG;
	
	/**
	 * SessionsSubGrup que pertanyen a l'Assignatura
	 */
	HashSet<SessioSubGrup> sessionsSG;
		
	/**
	 * Grups que pertanyen a l'Assignatura
	 */
	HashSet<Grup> grups;
		
	/////////////////////////////////////////////////////////////
	//////////////////////// Privades //////////////////////////
		
	
	/////////////////////////////////////////////////////////////
	//////////////////////  Constructora  ///////////////////////
		
	/**
	 * Creadora de Assignatura amb parametres.
	 * @param nom: nom de l'assignatura.
	 * @param hteo: hores de teoria de l'assignatura.
	 * @param hlab: hores de laboratori de l'assignatura.
	 */
	public Assignatura(PlaEstudis plaEst, String nom, int hteo, int hlab) throws Exception {
		ExceptionManager.thrower(this.setNom(plaEst,nom));
		ExceptionManager.thrower(this.setHTeo(hteo));
		ExceptionManager.thrower(this.setHLab(hlab));
		ExceptionManager.thrower(this.setPlaEstudis(plaEst));
		this.sessionsG = new HashSet<SessioGrup>();
		this.sessionsSG = new HashSet<SessioSubGrup>();
		this.grups = new HashSet<Grup>();


	}
		
	/**
	 * Creadora de Assignatura sense parametres.
	 * @param plaEstudis: Pla d'Estudis que pertany l'Assignatura.
	 */
	public Assignatura(PlaEstudis plaEst, String nom) throws Exception {
		ExceptionManager.thrower(this.setNom(plaEst,nom));
		this.hteo = 0;
		this.hlab = 0;
		this.plaEstudis = plaEst;
		this.sessionsG = new HashSet<SessioGrup>();
		this.sessionsSG = new HashSet<SessioSubGrup>();
		this.grups = new HashSet<Grup>();
	}
		
	/////////////////////////////////////////////////////////////
	//////////////////////// Setters  //////////////////////////
		
	/**
	 * Assigna el nom de l'Assignatura.
	 * @param nom: nom de l'Assignatura que entra l'usuari.
	 * @throws Exception si nom no cambia o nom ja existeix en el Pla d'Estudis.
	 */
	public int setNom(PlaEstudis plaEst, String nom) throws Exception {
		if (nom == null) return 30;
		else if(this.nom != null && this.nom.equals(nom)) return 1;
		else if (plaEst == null) return 31;
		else if(plaEst.checkAssignatura(nom)) return 32;
		this.nom = nom;
		return 0;
	}
		
	/**
	 * Assigna quantes hores de teoria te l'Assignatura.
	 * @param hteo: nombre d'hores de teoria de l'Assignatura que entra l'usuari.
	 * @throws Exception si hteo < 0 o hteo no cambia.
	 */
	public int setHTeo(int hteo) {
		if (this.hteo == hteo) return 1;
		else if (hteo < 0) return 33;
		this.hteo = hteo;
		return 0;
	}
		
	/**
	 * Assigna quantes hores de laboratori te l'Assignatura.
	 * @param hlab: nombre d'hores de laboratori de l'Assignatura que entra l'usuari.
	 * @throws Exception si hlab < 0 o hlab no cambia.
	 */
	public int setHLab(int hlab) {
		if (this.hlab == hlab) return 1;
		else if (hlab < 0) return 34;// error: les hores de lab són negatives
		this.hlab = hlab;
		return 0;
	}
	public int setPlaEstudis(PlaEstudis plaEst) {
		if (plaEst == null) return 31;
		if (plaEst == this.plaEstudis) return 1;
		this.plaEstudis = plaEst;
		return 0;
	}
	/////////////////////////////////////////////////////////////
	////////////////////////Getters  //////////////////////////
		
	/**
	 * Retorna el Nom de l'Assignatura.
	 * @return Nom de l'Assignatura.
	 */
	public String getNom() {
		return this.nom;
	}
		
	/**
	 * Retorna les hores de teoria de l'Assignatura.
	 * @return Hores de teoria de l'Assignatura.
	 */
	public int getHTeo() {
		return this.hteo;
	}
		
	/**
	 * Retorna les hores de laboratori de l'Assignatura.
	 * @return Hores de laboratori de l'Assignatura.
	 */
	public int getHLab() {
		return this.hlab;
	}
		
	/**
	 * Retorna la sessioG = Tipus + Hores de l'Assignatura.
	 * @return SessioG de l'Assignatura.
	 */
	public SessioGrup getSessioG(String tipus, int hores) throws Exception {
		for(SessioGrup s: sessionsG)
			if (s.getHores() == hores && s.getTipus().equals(tipus) && s.getAssignatura().getNom().equals(this.nom) && s.getAssignatura().getPlaEstudis().getNom().equals(this.getPlaEstudis().getNom())) return s;
		return null;
	}
		
	/**
	 * Retorna la sessioSG = Tipus + Hores de l'Assignatura.
	 * @return SessioSG de l'Assignatura.
	 */
	public SessioSubGrup getSessioSG(String tipus, int hores) throws Exception {
		if (tipus == null) ExceptionManager.thrower(38);
		for(SessioSubGrup s: sessionsSG)
			if (s.getHores() == hores && s.getTipus().equals(tipus) && s.getAssignatura().getNom().equals(this.nom) && s.getAssignatura().getPlaEstudis().getNom().equals(this.getPlaEstudis().getNom())) return s;
		return null;
	}
		
	/**
	 * Retorna el Grup = idGrup de l'Assignatura.
	 * @return Grup de l'Assignatura.
	 */
	public Grup getGrup(int idgrup) {
		for(Grup g: grups)
			if(g.getNumero() == idgrup && g.getAssignatura().getNom().equals(this.getNom())) return g;
		return null;
	}	
		
	
	public PlaEstudis getPlaEstudis() {
		return this.plaEstudis;
	}
	/////////////////////////////////////////////////////////////
	//////////////////////// Modificadores  ////////////////////
	
	/** 
	 * Crea un nou Grup dins l'Assignatura corresponent sempre i quant els atributs entrats
	 * compleixin amb les especificacions. Altrament llança una excepció.
	 * @param numero: Indica el numero que identificarà al Grup.
	 * @param capacitat: Indica quina capacitat ha de tenir el Grup.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int altaGrup(int numero, int capacitat, String franja) throws Exception{
		if (franja == null) return 35;
		if (this.checkGrup(numero)) return 36;
		if (capacitat < 0) return 37;
		grups.add(new Grup(this,numero,capacitat,franja));
		return 0;
	}
		
	/**
	 * Dona de baixa el grup = numero sempre i quant existeixi en aquesta Assignatura.
	 * @param numero: numero de grup que volem donar de baixa.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int baixaGrup(int numero) {
		if (checkGrup(numero)) {
			grups.removeIf(item -> item.getNumero() == numero);
			return 0;
		}
		else return 36;
	}
			
	/** 
	 * Crea una nova Sessio dins l'Assignatura corresponent sempre i quant els atributs entrats
	 * compleixin amb les especificacions. Altrament llança una excepció.
	 * @param hores: Indica el numero d'hores de la Sessio.
	 * @param tipus: Indica el tipus de la Sessio
	 * @return Excepció codificada en forma d'enter.
	 * @throws Exception 
	 */
	public int altaSessioG(String tipus, int hores) throws Exception {
		if (tipus == null) return 38;
		if (this.checkSessioG(tipus,hores)) return 39;
		else if (hores < 0) return 36;
		sessionsG.add(new SessioGrup(this,hores,tipus));
		return 0;
	}
	
	/** 
	 * Crea una nova Sessio dins l'Assignatura corresponent sempre i quant els atributs entrats
	 * compleixin amb les especificacions. Altrament llança una excepció.
	 * @param hores: Indica el numero d'hores de la Sessio.
	 * @param tipus: Indica el tipus de la Sessio
	 * @return Excepció codificada en forma d'enter.
	 * @throws Exception 
	 */
	public int altaSessioSG(String tipus, int hores) throws Exception {
		if (tipus == null) return 38;
		if (this.checkSessioSG(tipus,hores)) return 41;
		else if (hores < 0) return 36;
		sessionsSG.add(new SessioSubGrup(this,hores,tipus));
		return 0;
	}
		
	/**
	 * Dona de baixa la  Sessio = Hores + Tipus sempre i quant existeixi en aquesta Assignatura.
	 * @param hores: hores de la sessio que volem donar de baixa.
	 * @param tipus: tipus de la sessio que volem donar de baixa.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int baixaSessioG(String tipus, int hores) throws Exception {
		if (tipus == null) return 38;
		if (checkSessioG(tipus,hores)) sessionsG.removeIf(item -> item.getHores() == hores && item.getTipus().equals(tipus));
		else return 39;
		return 0;
	}
	
	/**
	 * Dona de baixa la  Sessio = Hores + Tipus sempre i quant existeixi en aquesta Assignatura.
	 * @param hores: hores de la sessio que volem donar de baixa.
	 * @param tipus: tipus de la sessio que volem donar de baixa.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int baixaSessioSG(String tipus, int hores) throws Exception {
		if (tipus == null) return 38;
		if (checkSessioSG(tipus,hores)) sessionsSG.removeIf(item -> item.getHores() == hores && item.getTipus().equals(tipus));
		else return 41;
		return 0;
	}
				
	/////////////////////////////////////////////////////////////
	//////////////////////// Funcions  //////////////////////////
		
		/**
	 * Retorna si ja existeix un grup amb el mateix numero en aquesta Assignatura.
	 * @param numero: numero de grup que volem comprobar.
	 * @return Cert si el grup ja existeix o fals altrament.
	 */
	public boolean checkGrup(int numero) {
		for(Grup g : grups) {
			if (g.getNumero() == numero && g.getAssignatura().getNom().equals(this.nom)) return true;
		}
		return false;
	}
		
		/**
	 * Retorna si ja existeix una SessioG igual en aquesta Assignatura.
		 * @param hores: Numero d'hores de la sessióG.
		 * @param tipus: Tipus de la sessióG.
		 * @return Cert si la sessióG existeix o fals altrament.
		 */
	public boolean checkSessioG(String tipus, int hores) throws Exception {
		if (tipus == null) ExceptionManager.thrower(38);
		for(Sessio s : sessionsG) {
			if (s.getHores() == hores && s.getTipus().equals(tipus) && s.getAssignatura().getNom().equals(this.nom) && s.getAssignatura().getPlaEstudis().getNom().equals(this.getPlaEstudis().getNom())) return true;
		}
		return false;
	}
			
		/**
	 * Retorna si ja existeix una SessioSG igual en aquesta Assignatura.
		 * @param hores: Numero d'hores de la sessióSG.
		 * @param tipus: Tipus de la sessióSG.
		 * @return Cert si la sessióSG existeix o fals altrament.
		 */
	public boolean checkSessioSG(String tipus, int hores) throws Exception {
		if (tipus == null) ExceptionManager.thrower(38);		
		for(Sessio s : sessionsSG) {
			if (s.getHores() == hores && s.getTipus().equals(tipus) && s.getAssignatura().getNom().equals(this.nom) && s.getAssignatura().getPlaEstudis().getNom().equals(this.getPlaEstudis().getNom())) return true;
		}
		return false;
	}
		
}
