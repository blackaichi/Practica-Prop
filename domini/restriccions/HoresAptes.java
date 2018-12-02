package domini.restriccions;

import java.util.*;
import domini.classes.*;
import utils.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.ipc.edu
 *
 */
public class HoresAptes {
	/**
	 * En definitiva, conté les hores lectives del pla
	 * d'estudis. Es evident que no es pot ni prohibir ni
	 * assigna hores a un grup fora de les hores lectives.
	 */
	private Map<Integer, boolean[]> mascara;
	/**
	 * Marca amb bool aquelles hores en les que el grup
	 * té "permís" per fer hores.
	 */
	private Map<Integer, boolean[]> horesDisponibles;
	
	/**
	 * Referencia al pla d'estudis al qual pertany la restricció.
	 */
	private PlaEstudis plaEstudis;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Assigna el grup o subgrup del qual es excepció.
	 * @param linker Referencia el Grup o subGrup a linkar.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int linker(Assignatura assig, Grup grup, SubGrup subGrup) {
		if(assig == null && grup == null && subGrup == null) return 216;
		else if(assig != null && grup != null && subGrup != null) return 217;
		else if(assig != null) this.plaEstudis = assig.getPlaEstudis();
		else if(grup != null) this.plaEstudis = grup.getAssignatura().getPlaEstudis();
		else this.plaEstudis = subGrup.getGrup().getAssignatura().getPlaEstudis();
		
		return 0;
	}
	
	/**
	 * Vigila que cap de les hores entradas no sigui incongruent.
	 * @param hores Conjunt d'hores a revisar.
	 * @return True si no violen les hores, false altrament.
	 */
	private int checkDiaIHores(int dia, int[] hores) {
		if(hores == null) return 212;
		else if(dia < 0 && dia > 6) return 213;
		else for(int hora: hores)
			if(hora < 0 || hora > 23) return 214;
		
		return 0;
	}
	
	/**
	 * Permet o nega fer classes per un grup en unes hores i dia concrets.
	 * @param permet Indica si l'ha de permetre, o be negarla.
	 * @param force Permet que, en cas d'entrar una franja parcialment 
	 * incorrecte, la part correcta si que s'apliqui; obviant la resta.
	 * @param dia Indica sobre quin dia aplicar la restricció.
	 * @param hores Conjunt d'hores a les que aplicar la restricció.
	 * @return Execpció en forma d'enter.
	 */
	private int assigna(boolean permet, boolean force, int dia, int ...hores) {
		int checker = this.checkDiaIHores(dia, hores);
		if(checker != 0) return checker;
		
		Map<Integer, boolean[]> reboke = clone(this.horesDisponibles);
		for(int hora: hores)
			if(!this.mascara.get(dia)[hora] && !force) {
				this.horesDisponibles = clone(reboke);
				return 215;
			}
			else if(this.mascara.get(dia)[hora]) this.horesDisponibles.get(dia)[hora] = permet;
		
		return 0;
	}
		
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la restricció:
	 * Hores on no es pot fer classe per grup
	 * @param assig Referencia a la assignatura a la qual es vol restringir les hores lectives.
	 */
	public HoresAptes(Assignatura assig) throws Exception {
		ExceptionManager.thrower(this.linker(assig, null, null));
		ExceptionManager.thrower(this.setMascara(true));
	}
	
	/**
	 * Constructora de la restricció:
	 * Hores on no es pot fer classe per grup
	 * @param grup Referencia al Grup al qual es vol restringir les hores lectives.
	 */
	public HoresAptes(Grup grup) throws Exception {
		ExceptionManager.thrower(this.linker(null, grup, null));
		ExceptionManager.thrower(this.setMascara(true));
	}
	
	/**
	 * Constructora de la restricció:
	 * Hores on no es pot fer classe per grup
	 * @param subGrup Referencia al subGrup al qual es vol restringir les hores lectives.
	 */
	public HoresAptes(SubGrup subGrup) throws Exception {
		ExceptionManager.thrower(this.linker(null, null, subGrup));
		ExceptionManager.thrower(this.setMascara(true));
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  SETTERS  /////////////////////////////////////
	/**
	 * Assigna la mascara segons el pla d'estudis del grup o subGrup.
	 * @param cascade Indica si, en configurar la mascara, es volen igualar
	 * les horesDisponibles amb la mascara, o bé és vol conservar la configuració
	 * present d'aquestes horesDisponibles.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setMascara(boolean cascade) {
		if(this.plaEstudis == null) return 211;
		
		for(int dia = 0; dia < 7; dia++)
			this.mascara = clone(this.plaEstudis.getLectiuSetmana());
		
		if(cascade) this.horesDisponibles = clone(mascara); //inicialment son iguals;
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  GETTERS  /////////////////////////////////////
	/**
	 * Retorna la mascara de la restricció.
	 * @return una matriu de booleans.
	 */
	public Map<Integer, boolean[]> getMascara(){
		return this.mascara;
	}
	
	/**
	 * Retorna la matriu de booleans que descriu les hores disponibles
	 * en aquesta restricció.
	 * @return Una matriu de booleans de 7x24.
	 */
	public Map<Integer, boolean[]> getHoresAptes(){
		return this.horesDisponibles;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Actualitza hores disponibles del grup per tal de restringir
	 * les hores entrades per parametre.
	 * @param force Permet que, en cas d'entrar una franja parcialment 
	 * incorrecte, la part correcta si que s'apliqui; obviant la resta.
	 * @param dia indica sobre quin dia aplicar la restricció
	 * @param hores Conjunt d'hores que es volen restringir.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int prohibirHores(boolean force, int dia, int ...hores) {
		return this.assigna(false, force, dia, hores);
	}
	
	/**
	 * Reautoritza el grup a tenir classe a les hores indicades. 
	 * @param force Permet que, en cas d'entrar una franja parcialment 
	 * incorrecte, la part correcta si que s'apliqui; obviant la resta.
	 * @param dia Indica a quin dia aplicar la franja.
	 * @param hores Conjunt d'hores que es volen restringir.
	 * @return
	 */
	public int permetHores(boolean force, int dia, int ...hores) {
		return this.assigna(true, force, dia, hores);
	}
	
	/**
	 * Actualitza la franja d'hores disponnibles pel grup novament a
	 * les hores lectives que te assignades el pla d'estudis.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int restore() {
		return setMascara(true);
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna 0 si, i només si, en aquella hora per aquell dia el grup te permés
	 * fer classe; altramnt, si retorna 1 es que no pot. Finalment si retorna un
	 * altre numero s'ha produit una excepció que cal processar.
	 * @param dia Indica el dia de la classe.
	 * @param hora Indica l'hora de la classe en el dia corresponent.
	 * @return retorna un enter superior o igual a 0.
	 */
	public int checkPotFerClasse(int dia, int hora) {
		int[] hores = new int[1];
		hores[0] = hora;
		
		int checker = this.checkDiaIHores(dia, hores);
		if(checker != 0) return checker;
		
		if(this.horesDisponibles.get(dia)[hora]) return 0;
		else return 1;
	}

	/**
	 * Clona el contingut d'un array de booleans.
	 * @param toClone Array a clonar.
	 * @return Array clonat.
	 */
	static public Map<Integer, boolean[]> clone(Map<Integer, boolean[]> toClone){
		if(toClone == null) return null;
		
		Map<Integer, boolean[]> cloned = new HashMap<>();
		for(int dia: toClone.keySet())
			cloned.put(dia, Arrays.copyOf(toClone.get(dia), toClone.get(dia).length));
		
		return cloned;
	}
	
	/**
	 * Retorna 0, si i només si, la sessio de grup o de subgrup entrades
	 * (mai ambdos a la vegada), compleix les restriccions horaries del seus
	 * respectius grup, subgrup i/o assignatura. Altrament retorna un enter
	 * que identifica quina restricció s'ha violat.
	 * @param sessioG Referencia a la sessio de grup que s'ha de procesar.
	 * @param sessioSG Referencia a la sessio de subGrup que s'ha de procesar.
	 * @param dia Indica el dia.
	 * @param horaIni Indica l'hora.
	 * @return Excepció codificada en forma d'enter.
	 */
	static public int checkHoresAptes(SessioGAssignada sessioG, SessioSGAssignada sessioSG, int dia, int hora, HashSet<String> flags) {
		if(sessioG == null && sessioSG == null) return 218;
		
		//Obtenció de la durada de la sessió:
		int durada = sessioG != null? sessioG.getSessioGrup().getHores() :
									  sessioSG.getSessioSubGrup().getHores();
		//Obtenció de les hores lectives del pla d'estudis per al dia indicat:
		boolean[] lectiu = sessioG != null? sessioG.getGrup().getAssignatura().getPlaEstudis().getLectiuDia(dia) :
											sessioSG.getSessioSubGrup().getAssignatura().getPlaEstudis().getLectiuDia(dia);
		//Obtenció de les restriccions d'hores aptes del grup/subgrup de la sessio:
		HoresAptes fromSessio = sessioG != null? sessioG.getGrup().getRestriccioHoresAptes() :
												 sessioSG.getSubGrup().getRestriccioHoresAptes();
		//Obtenció de les restriccions d'hores aptes de l'assignatura pertinent a la sessio:
		HoresAptes fromAssig = sessioG != null? sessioG.getGrup().getAssignatura().getHoresAptes() : 
												sessioSG.getSessioSubGrup().getAssignatura().getHoresAptes();
		
		for(int incr = 0; incr < durada; incr++) {
			if(lectiu == null || (hora+incr < lectiu.length && !lectiu[hora+incr])) return 219;
			else if(flags.contains("G_FRANJA")) {
				if(sessioG != null && !sessioG.getGrup().enRang(hora+incr)) return 280;
				else if(sessioSG != null && !sessioSG.getSubGrup().enRang(hora+incr)) return 281;
			}
				
			if(flags.contains("G_HAPTES") && fromSessio.checkPotFerClasse(dia, hora+incr) != 0) return 282;
			else if(flags.contains("ASSIG_HAPTES") && fromAssig.checkPotFerClasse(dia, hora+incr) != 0) return 283;
		}
		
		return 0;
	}
}
