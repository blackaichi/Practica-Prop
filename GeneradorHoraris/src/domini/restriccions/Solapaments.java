package domini.restriccions;

import java.util.*;

import domini.ControladorDomini;
import domini.classes.*;
import domini.tools.Estructura;
import domini.tools.Segment;
import utils.*;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class Solapaments {
	/**
	 * Enregistra TOTS els solapaments donsts d'alta per cadascun
	 * dels plans d'estudis.
	 */
	static private HashMap<String, HashSet<Solapaments>> Solapaments;
	/**
	 * Enmagatzema totes les claus primaries dels grups/subGrup
	 * o inclus assignatures amb les que no és pot solapar.
	 */
	private HashMap<String, HashSet<Integer>> disjuntes;
	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	/**
	 * Referencia al pla d'estudis al qual pertany la restricció.
	 */
	private PlaEstudis plaEstudis;
	private String assignatura;
	private int numgrup = 0;
	private int numsubgrup = 0;
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe NoSolaparGrup.
	 */
	public Solapaments(Assignatura assig) throws Exception{
		ExceptionManager.thrower(this.linker(assig, null, null));
		this.disjuntes = new HashMap<String, HashSet<Integer>>();
		
		iniStatic();
	}
	
	/**
	 * Constructora de la classe NoSolaparGrup.
	 */
	public Solapaments(Grup grup) throws Exception{
		ExceptionManager.thrower(this.linker(null, grup, null));
		this.disjuntes = new HashMap<String, HashSet<Integer>>();
		
		iniStatic();
	}
	
	/**
	 * Constructora de la classe NoSolaparSubGrup.
	 */
	public Solapaments(SubGrup subGrup) throws Exception{
		ExceptionManager.thrower(this.linker(null, null, subGrup));
		this.disjuntes = new HashMap<String, HashSet<Integer>>();
		
		iniStatic();
	}
 	
	////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////  PRIVADES  /////////////////////////////////////
	private void iniStatic() {
		if(Solapaments == null) Solapaments = new HashMap<String, HashSet<Solapaments>>();
		if(Solapaments.get(this.plaEstudis.getNom()) == null)
			Solapaments.put(this.plaEstudis.getNom(), new HashSet<Solapaments>());
		
		Solapaments.get(plaEstudis.getNom()).add(this);
	}
	
	/**
	 * Assigna el grup o subgrup del qual es excepció.
	 * @param linker Referencia el Grup o subGrup a linkar.
	 * @return Excepció codificada en forma d'enter.
	 */
	private int linker(Assignatura assig, Grup grup, SubGrup subGrup) {
		if(assig == null && grup == null && subGrup == null) return 194;
		else if(assig != null && grup != null && subGrup != null) return 195;
		else if(assig != null) {
			this.plaEstudis = assig.getPlaEstudis();
			this.assignatura = assig.getNom();
		}
		else if(grup != null) {
			this.plaEstudis = grup.getAssignatura().getPlaEstudis();
			this.assignatura = grup.getAssignatura().getNom();
			this.numgrup = grup.getNumero();
		}
		else {
			this.plaEstudis = subGrup.getGrup().getAssignatura().getPlaEstudis();
			this.assignatura = subGrup.getGrup().getAssignatura().getNom();
			this.numgrup = subGrup.getGrup().getNumero();
			this.numsubgrup = subGrup.getNumero();
		}
		
		return 0;
	}
		
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  MODIFICADORES  /////////////////////////////////
	/**
	 * Impedeix o permet, segons l'estat d'apte, que la instancia corresponent
	 * es pugui solapar amb el grup o subGRup passat per parametre. I viceversa.
	 * @param assig Identifica l'assignatura del grup/subGrup
	 * @param numero Identifica al grup o subGrup dins l'assignatura. 
	 * @param permet Indica si s'ha de restringir o permetre.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int setSolapament(String assig, int numero, boolean permet) {
		if(assig == null) return 196;
		
		if(!this.plaEstudis.checkAssignatura(assig)) return 197;
		else if(!this.disjuntes.containsKey(assig)) this.disjuntes.put(assig, new HashSet<Integer>()); //Si l'assignatura no existeix al map, se l'afegeix:
		//numero == 0 representa TOTS els grups de l'assignatura; basicament impedeix el solapament amb l'assignatura.
		else if(numero != 0 && !this.plaEstudis.getAssignatura(assig).checkGrup(numero)) { //si numero no es id d'un grup de l'assignatura:
			boolean checker = false;
			for(Grup grup : this.plaEstudis.getAssignatura(assig).getGrups())
				checker = checker || grup.checkSubGrup(numero);
			//si numero no es id de cap subGrup d'entre tots els grups de l'assignatura:
			if(!checker) return 198;
		}
		
		//Afegir un element que ja conté es com no fer res; al igual que eliminar-ne un que no hi es:
		boolean contains = this.disjuntes.get(assig).contains(numero);
		if((!permet && contains) || (permet && !contains)) return 0;
		else if(permet) this.disjuntes.get(assig).removeIf(item -> item.intValue() == numero);
		else this.disjuntes.get(assig).add(numero);
		
		//Si A no pot solapar amb B, es evident que B no pot solapar amb A; i viceversa.
		if(numsubgrup != 0) {
			for(Grup grup : plaEstudis.getAssignatura(assig).getGrups())
				for(SubGrup subgrup : grup.getAllSubGrups())
					if(subgrup.getNumero() == numero)
						ControladorDomini.getInstance().SetSolapamentSubGrup(plaEstudis.getNom(), assig, grup.getNumero(), numero, this.assignatura, numsubgrup, permet);
		}
		else if(numgrup != 0) ControladorDomini.getInstance().SetSolapamentGrup(plaEstudis.getNom(), assig, numero, this.assignatura, numgrup, permet);
		else ControladorDomini.getInstance().SetSolapamentAssig(plaEstudis.getNom(), assig, this.assignatura, permet);
		
		return 0;
	}

	/**
	 * Substitueix el nom identificador de l'assignatura i/o el numero identificador
	 * d'un grup o subGrup per un de nou. Considerant que s'ha fet un canvi.  I
	 * l'actualitza a totes les instancies per aquest pla d'estudis.
	 * @param olAssig Identifica l'antic nom d'assignatura.
	 * @param newAssig Indica el nou nom de l'assignatura.
	 * @return Exepció codificada en forma d'enter.
	 */
	public int actualitzaAssigatura(String oldAssig, String newAssig) {
		for(Solapaments solapament : Solapaments.get(this.plaEstudis.getNom())) {
			if(!solapament.disjuntes.keySet().contains(oldAssig)) return 0;
			else if(solapament.disjuntes.keySet().contains(newAssig)) return 199;
			
			HashSet<Integer> contenidor = new HashSet<>(); //AddAll:
			for(int numero : solapament.disjuntes.get(oldAssig)) contenidor.add(numero);
			
			solapament.disjuntes.remove(oldAssig);
			solapament.disjuntes.put(newAssig, contenidor);
		}
		
		return 0;
	}
	
	/**
	 * Substitueix el nom identificador de l'assignatura i/o el numero identificador
	 * d'un grup o subGrup per un de nou. Considerant que s'ha fet un canvi. I
	 * l'actualitza a totes les instancies per aquest pla d'estudis.
	 * @param assig Identifica l'assignatura del grup/subGrup
	 * @param olNumero Identifica al grup o subgrup que es preten actualitzar.
	 * @param newNumero Indica la nova identificacio del grup o subgrup
	 * @return Exepció codificada en forma d'enter.
	 */
	public int actualitzaNumero(String assig, int oldNumero, int newNumero) {
		for(Solapaments solapament : Solapaments.get(this.plaEstudis.getNom())) {
			if(!solapament.disjuntes.keySet().contains(assig)) return 0;
			else if(!solapament.disjuntes.get(assig).contains(oldNumero)) return 0;
			else if(solapament.disjuntes.get(assig).contains(newNumero)) return 204;
			
			solapament.disjuntes.get(assig).removeIf(item -> item.intValue() == oldNumero);
			solapament.disjuntes.get(assig).add(newNumero);
		}
		
		return 0;
	}
	
	/**
	 * Esborra el registre de solapaments restringits en aquesta instancia.
	 * Donant lloc a que cap restricció anterior a la seva crida estigui ja 
	 * restringida.
	 */
	public void reset() {
		this.disjuntes.clear();
	}
	
	/**
	 * Esborra TOTES les restriccions referents al grup/subgrup identificats per
	 * numero dins de l'assignatura identificada per assig.
	 * @param assig
	 * @param numero
	 */
	static public int kill(String plaEstudis, String assig, int numero) {
		for(Solapaments solapament : Solapaments.get(plaEstudis)) {
			if(numero == 0 && solapament.disjuntes.containsKey(assig)) solapament.disjuntes.remove(assig);
			else if(solapament.disjuntes.containsKey(assig)) solapament.disjuntes.get(assig).removeIf(item -> item.intValue() == numero);
		}
		
		return 0;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  CONSULTORES  ///////////////////////////////////
	/**
	 * Retorna 0 si, i només si, l'assignatura, grup o subgrup corresponent es pot
	 * solapar amb l'assignatura i/o grup/subgrup passats per parametre; Altrament
	 * retorna 1. No obstant, qualsevol altre enter representa una excepció. 
	 * @param assig Identifica l'assignatura.
	 * @param numero Identifica al grup o subGrup dins l'assignatura.
	 * @return Excepció codificada en forma d'enter.
	 */
	public int checkPotSolapar(String assig, int numero) {
		if(!this.disjuntes.containsKey(assig)) return 0;
		else if(this.disjuntes.get(assig).contains(0)) return 1; //El valor numero = 0; representa TOTS els grups.
		else if(this.disjuntes.get(assig).contains(numero)) return 1;
		else return 0;
	}
	
	/**
	 * Retorna 0, si i només si, la sessio entrada (mai ambdues simultaneament)
	 * compleix les restriccions de solapament del seus respectius grup, subgrup
	 * i/o assignatura. Altrament retorna un enter que identifica quina
	 * restricció s'ha violat.
	 * @param horari Referencia sobre quin horari comrovar les restricicons.
	 * @param sessioG Referencia a la sessio de grup que s'ha de procesar.
	 * @param sessioSG Referencia a la sessio de subGrup que s'ha de procesar.
	 * @return Excepció codificada en forma d'enter.
	 */
	static public int checkSolapament(Estructura horari, SessioGAssignada sessioG, SessioSGAssignada sessioSG, int dia, int hora) {
		if(horari == null) return 205;
		else if(sessioG == null && sessioSG == null) return 206;
		
		//Obtencio de les restriccions de solapament del grup/subgrup pertinent a la sessio:
		Solapaments fromSessio = sessioG != null? sessioG.getGrup().getSolapaments() : 
												  sessioSG.getSubGrup().getSolapaments();
		//Obtenció de les restriccions de solapament de l'assignatura pertinent a la sessio:
		Solapaments fromAssig = sessioG != null? sessioG.getGrup().getAssignatura().getSolapaments() :
			 									 sessioSG.getSessioSubGrup().getAssignatura().getSolapaments();
		
		for(Segment segment: horari.getAllSegments(dia, hora)) {
			//Obtenció del numero de grup/subgrup:
			int numero = !segment.getSessio().fnull()? segment.getSessio().first.getGrup().getNumero() :
													   segment.getSessio().second.getSubGrup().getNumero();
			//Obtenció del nom de l'assignatura:
			String assignatura = !segment.getSessio().fnull()? segment.getSessio().first.getGrup().getAssignatura().getNom() :
															   segment.getSessio().second.getSubGrup().getGrup().getAssignatura().getNom();
			//Comprovació dels solapaments:
			if(horari.getFlagState("G_SOLAP") && fromSessio.checkPotSolapar(assignatura, numero) != 0) return 207;
			else if(horari.getFlagState("ASSIG_SOLAP") && fromAssig.checkPotSolapar(assignatura, 0) != 0) return 208;
		}
		
		return 0;
	}

	/**
	 * Retorna el set d'elements disjunts.
	 * @return Un map buit o amb contingut, amb sets de numeros de grup/subgrup per una
	 * assignatura donada.
	 */
	public HashMap<String, HashSet<Integer>> getDisjuntes() {
		return this.disjuntes;
	}
}