package persistencia.exports;

import java.util.*;
import domini.classes.*;
import domini.restriccions.*;
import utils.*;

/**
 * 
 * @author eric.casanovas@est.fib.upc.edu
 *
 */

public class DriverProvadorExports {
	
	/**
	 * Totes les instancies dels exports
	 */
	private static ExportaPlaEstudis plaEstudis = ExportaPlaEstudis.getInstancia();
	private static ExportaCampus campus = ExportaCampus.getInstancia();
	private static ExportaAula aula = ExportaAula.getInstancia();
	private static ExportaAssignatura assignatura = ExportaAssignatura.getInstancia();
	private static ExportaData data = ExportaData.getInstancia();
	private static ExportaGrup grup = ExportaGrup.getInstancia();
	private static ExportaHorari horari = ExportaHorari.getInstancia();
	private static ExportaHoresAptes horesAptes = ExportaHoresAptes.getInstancia();
	private static ExportaSessioGAssignada sessioGAssignada = ExportaSessioGAssignada.getInstancia();
	private static ExportaSessioGrup sessioGrup = ExportaSessioGrup.getInstancia();
	private static ExportaSessioSGAssignada sessioSGAssignada = ExportaSessioSGAssignada.getInstancia();
	private static ExportaSessioSubGrup sessioSubGrup = ExportaSessioSubGrup.getInstancia();
	private static ExportaSolapaments solapaments = ExportaSolapaments.getInstancia();
	private static ExportaSubGrup subGrup = ExportaSubGrup.getInstancia();
	
	/**
	 * Comprova l'Export que es descomenti
	 * @param args no entra res
	 * @throws Exception
	 */
	public static void main (String [] args) throws Exception {
		//provaPlaEstudis();
		//provaCampus();
		//provaAula();
		//provaAssignatura();
		//provaData();
		//provaGrup();
		//provaHorari();
		//provaHoresAptes();
		//provaSessioGAssignada();
		//provaSessioGrup(); 
		//provaSessioSGAssignada();
		//provaSessioSubGrup(); 
		//provaSolapaments(); // TODO
		//provaSubGrup(); 
	}
	
	/**
	 * Comprova l'Export de SubGrup
	 * @throws Exception
	 */
	private static void provaSubGrup() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");	
		p.altaAssignatura("PROP");
		p.altaAssignatura("IES");
		Assignatura a = p.getAssignatura("PROP");
		Assignatura b = p.getAssignatura("IES");
		a.altaGrup(10, 20, "M");		
		Grup g = a.getGrup(10);
		g.altaSubGrup(11, 20, false);
		
		SubGrup sg = g.getSubGrup(11);
		sg.setNumero(12);
		sg.setPlaces(10, false);
		Map<Integer, int[]> franja = new HashMap<Integer, int[]>();
		franja.put(0, null);
		a.setHoresAptes(franja, true, false);
		a.setSolapament(b, true);
		subGrup.exportaSubGrup(sg, true);
	}

	/**
	 * Comprova l'Export de Solapaments
	 * @throws Exception
	 */
	private static void provaSolapaments() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");	
		p.altaAssignatura("PROP");
		p.altaAssignatura("IES");
		Assignatura a = p.getAssignatura("PROP");
		Assignatura b = p.getAssignatura("IES");
		a.setSolapament(b, true);
		Solapaments s = a.getSolapaments();
		solapaments.exportaSolapaments(s, true);
	}

	/**
	 * Comprova l'Export de SessioSubGrup
	 * @throws Exception
	 */
	private static void provaSessioSubGrup() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");	
		p.altaAssignatura("prop"); 
		Assignatura a = p.getAssignatura("prop");
		a.altaSessioSG("lab", 2);
		SessioSubGrup ssg = a.getSessioSG("lab", 2);
		ssg.setHores(2);
		ssg.addMaterial("PCs");
		ssg.setTipus("practica");
		ssg.setnsessions(2);
		sessioSubGrup.exportaSessioSubGrup(ssg, true);
	}

	/**
	 * Comprova l'Export de SessioSGAssignada
	 * @throws Exception
	 */
	private static void provaSessioSGAssignada() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");	
		p.altaAssignatura("prop"); 
		Assignatura a = p.getAssignatura("prop");
		a.altaGrup(10, 20, "M");
		Grup g = a.getGrup(10);
		g.altaSubGrup(11, 20, false);
		a.altaSessioSG("problemes", 2);
		SessioSubGrup ssg = a.getSessioSG("problemes", 2);
		ssg.assignaSessio(10, 11);
		
		SessioSGAssignada ssga = ssg.getSessioSGA(11);
		sessioSGAssignada.exportaSessioSGAssignada(ssga, true);
	}

	/**
	 * Comprova l'Export de SessioGrup
	 * @throws Exception
	 */
	private static void provaSessioGrup() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");	
		p.altaAssignatura("prop"); 
		Assignatura a = p.getAssignatura("prop");
		a.altaSessioG("teoria", 2);
		SessioGrup sg = a.getSessioG("teoria", 2);
		sg.setHores(2);
		sg.addMaterial("PCs");
		sg.setTipus("practica");
		sg.setnsessions(2);
		sessioGrup.exportaSessioGrup(sg, true);
	}

	/**
	 * Comprova l'Export de SessioGAssignada
	 * @throws Exception
	 */
	private static void provaSessioGAssignada() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");	
		p.altaAssignatura("prop"); 
		Assignatura a = p.getAssignatura("prop");
		a.altaGrup(10, 20, "M");
		a.altaSessioG("teoria", 2);
		SessioGrup sg = a.getSessioG("teoria", 2);
		sg.assignaSessio(10);
		
		SessioGAssignada sga = sg.getSessioGA(10);
		sessioGAssignada.exportaSessioGAssignada(sga, true);
	}

	/**
	 * Comprova l'Export d'HoresAptes
	 * @throws Exception
	 */
	private static void provaHoresAptes() throws Exception{
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");	
		int[] i = new int[] {8,20};
		p.setLectiu(0, i);
		p.setLectiu(1, i);
		p.setLectiu(2, i);
		p.setLectiu(3, i);
		p.setLectiu(4, i);
		p.setLectiu(5, i);
		p.setLectiu(6, i);
		p.altaAssignatura("prop"); 
		Assignatura a = p.getAssignatura("prop");
		Map<Integer, int[]> franja = new HashMap<Integer, int[]>(); 
		franja.put(0, i);
		franja.put(1, i);
		franja.put(2, i);
		franja.put(3, i);
		franja.put(4, i);
		franja.put(5, i);
		franja.put(6, i);
		a.setHoresAptes(franja, true, true);
		
		HoresAptes ha = new HoresAptes(a);
		horesAptes.exportaHoresAptes(ha, true);
	}
	
	/**
	 * Comprova l'Export d'Horari
	 * @throws Exception
	 */
	private static void provaHorari() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis pe = PlaEstudis.getPlaEstudis("fib");
		boolean[] franja = new boolean[24];
		for(int i = 0; i < 24; i++) franja[i] = true;
		pe.setLectiu(0, franja);
		pe.altaAssignatura("prop"); 
		pe.getAssignatura("prop").altaSessioG("teo", 2);
		pe.getAssignatura("prop").altaSessioSG("lab", 2);
		
		pe.getAssignatura("prop").altaGrup(20, 30, "MT");
		pe.getAssignatura("prop").altaGrup(10, 25, "MT");
		pe.getAssignatura("prop").getGrup(10).altaSubGrup(11, 5, false);
		
		pe.getAssignatura("prop").getSessioG("teo", 2).assignaSessio(10);
		pe.getAssignatura("prop").getSessioSG("lab", 2).assignaSessio(10, 11);
		pe.getAssignatura("prop").getSessioG("teo", 2).assignaSessio(20);

		Campus.newCampus("campus nord");
		Campus c = Campus.getCampus("campus nord");
		c.altaAula("A1E01", 50);
		c.altaAula("A5001", 100);
		c.altaAula("A4201", 60);
		HashSet<String> f = new HashSet<>();
		f.add("HORES_APTES");
		f.add("SOLAPAMENTS");
		Horari h = Horari.getInstance();
		h.GENERADOR(pe, c, f, 2, true);
		HashSet<Estructura> horaris;
		horaris = h.getHoraris(pe.getNom(), c.getNom());
		horari.exportaHoraris(horaris, true);
	}

	/**
	 * Comprova l'Export de Grup
	 * @throws Exception
	 */
	private static void provaGrup() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");
		p.altaAssignatura("PROP");
		p.altaAssignatura("IES");
		Assignatura a = p.getAssignatura("PROP");
		Assignatura b = p.getAssignatura("IES");
		a.altaGrup(10, 20, "M");
		
		Grup g = a.getGrup(10);
		g.setPlaces(10);
		g.setFranja("T");
		g.altaSubGrup(11, 10, false);
		Map<Integer, int[]> franja = new HashMap<Integer, int[]>();
		franja.put(0, null);
		a.setHoresAptes(franja, true, false);
		a.setSolapament(b, true);
		grup.exportaGrup(g, true);
	}

	/**
	 * Comprova l'Export de Data
	 * @throws Exception
	 */
	private static void provaData() throws Exception {
		Data d = new Data(0, 0);
		d.setDia(3);
		d.setHora(8);
		data.exportaData(d, true);
	}
	
	/**
	 * Comprova l'Export d'Assignatura
	 * @throws Exception
	 */
	private static void provaAssignatura() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");
		p.altaAssignatura("PROP");
		p.altaAssignatura("IES");
		Assignatura b = p.getAssignatura("IES");
		
		Assignatura a = p.getAssignatura("PROP");
		a.altaSessioG("teoria", 2);
		a.altaSessioSG("practica", 2);
		a.altaGrup(10, 15, "M");
		Map<Integer, int[]> franja = new HashMap<Integer, int[]>();
		franja.put(0, null);
		a.setHoresAptes(franja, true, false);
		a.setSolapament(b, true);
		assignatura.exportaAssignatura(a, true);
	}
	
	/**
	 * Comprova l'Export d'Aula
	 * @throws Exception
	 */
	private static void provaAula() throws Exception {
		Campus.newCampus("campus nord");
		Campus c = Campus.getCampus("campus nord");
		c.altaAula("A6103", 20);
		
		Aula a = c.getAula("A6103");
		a.setCapacitat(30);
		a.setNom("A5103");
		a.afegirEquip("pcs");
		a.afegirEquip("projector");
		aula.exportaAula(a, true);
	}
	
	/**
	 * Comprova l'Export de Campus
	 * @throws Exception
	 */
	private static void provaCampus() throws Exception {
		Campus.newCampus("campus nord");
		Campus c = Campus.getCampus("campus nord");
		c.setNom("campus sud");
		c.setAutor("El Meu Penis");
		c.altaAula("A6103", 20);
		campus.exportaCampus(c, true);
	}
	
	/**
	 * Comprova l'Export de PlaEstudis
	 * @throws Exception
	 */
	private static void provaPlaEstudis() throws Exception {
		PlaEstudis.newPlaEstudis("fib");
		PlaEstudis p = PlaEstudis.getPlaEstudis("fib");
		p.setNom("etseib");
		p.altaAssignatura("PROP");
		p.altaAssignatura("IES");
		p.setAutor("El Meu Penis");
		p.setLectiu(0, new int[] {5, 16});
		p.setRangDia(new int[] {5, 10, 13, 16});
		plaEstudis.exportaPlaEstudis(p, true);
	}
}
