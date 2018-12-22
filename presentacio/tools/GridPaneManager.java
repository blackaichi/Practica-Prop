package presentacio.tools;

import utils.*;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import presentacio.ControladorPresentacio;
import presentacio.vistes.Main;

public class GridPaneManager {
	
	static private GridPaneManager current;
	
	static public GridPaneManager getInstance() {
		if(current == null) current = new GridPaneManager();
		return current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private void configure(GridPane container, int minCol, int maxCol, int minRow, int maxRow) {
		reset(container, true);
		
		//Construccio de totes les columnes (Columns):
		for(int dia = minCol; dia <= maxCol; dia++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100f/(dia==0? 6 : 9));
			container.getColumnConstraints().add(colConst);
		}
		//Construccio de totes les files (Rows):
		for(int hora = minRow; hora <= maxRow; hora++) {
			RowConstraints rowConst = new RowConstraints();
			rowConst.setPercentHeight(100f/25);
			container.getRowConstraints().add(rowConst);
		}
		
		//Agregració del contingut corresponent:
		for(int dia = minCol; dia <= maxCol; dia++) {
			for(int hora = minRow; hora <= maxRow; hora++) {
				if(dia == minCol && hora == minRow) container.add(new Label(), hora, dia);
				else if(hora == minRow) container.add(new Label(getDayByIndex(dia-1)), dia, hora);
				else if(dia == minCol) container.add(new Label(String.valueOf(hora-1).concat(":00 - ").concat(String.valueOf(hora == 24? 0 : hora)).concat(":00")), dia, hora);
				else {
					CheckBox selector = new CheckBox();
					if(dia < 6 && hora > 8 && hora < 21) selector.setSelected(true);
					container.add(selector, dia, hora);
				}
			}
		}
	}
	
	private void enable(GridPane container, String plaEstudis, String assignatura, int grup, int subgrup) {
		Map<Integer, boolean[]> state = ControladorPresentacio.getInstance().getHorizon(plaEstudis, assignatura, grup, subgrup);
		
		for(Node node: container.getChildren()) {
			if(GridPane.getColumnIndex(node).intValue() > 0 && GridPane.getRowIndex(node).intValue() > 0) {
				try {
					((CheckBox) node).setDisable(!state.get(GridPane.getColumnIndex(node).intValue()-1)[GridPane.getRowIndex(node).intValue()-1]);
					((CheckBox) node).setSelected(!((CheckBox) node).isDisabled());
				}
				catch(Exception e) {
					continue;
				}
			}
		}
	}
	
	private Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> getHorizon(String plaEstudis, String assignatura, int grup, int subgrup){
		Map<Integer, boolean[]> view = ControladorPresentacio.getInstance().getHorizon(plaEstudis, assignatura, grup, subgrup);
		
		int minRow = 99, maxRow = -1;
		int minCol = 99, maxCol = -1;
		
		for(Integer key: view.keySet()) {
			int nhora = 0;
			for(boolean hora: view.get(key.intValue())) {
				if(hora) {
					minRow = Math.min(minRow, nhora);
					maxRow = Math.max(maxRow, nhora);
					
					minCol = Math.min(minCol, key);
					maxCol = Math.max(maxCol, key);
				}
				
				nhora++;
			}
		}
		
		/*System.out.println("minCol: ".concat(String.valueOf(minCol)));
		System.out.println("maxCol: ".concat(String.valueOf(maxCol+1)));
		System.out.println("minRow: ".concat(String.valueOf(minRow)));
		System.out.println("maxRow: ".concat(String.valueOf(maxRow+1)));*/
		return new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(new Pair<Integer, Integer>(minCol, maxCol+1), new Pair<Integer, Integer>(minRow, maxRow+1));
	}
		
	private Pair<Integer, Integer> getDiaIHora(GridPane container, Node node){
		Pair<Integer, Integer> diaIHora = new Pair<Integer, Integer>();

		for(Node nodex: container.getChildren()) {
			if(GridPane.getColumnIndex(nodex) == GridPane.getColumnIndex(node) && diaIHora.fnull())
				diaIHora.first = getIndexByDay(((Label) nodex).getText());
			
			if(GridPane.getRowIndex(nodex) == GridPane.getRowIndex(node) && GridPane.getColumnIndex(nodex) == 0) {
				String[] depurat = ((Label) nodex).getText().split(":");
				diaIHora.second = Integer.valueOf(depurat[0]);
			}
			
			if(!diaIHora.fnull() && !diaIHora.snull()) break;
		}
		
		return diaIHora;
	}
	
	private String getAssignatura(GridPane container, Node node) {
		for(Node nodex : container.getChildren())
			if(GridPane.getRowIndex(nodex) == GridPane.getRowIndex(node) && GridPane.getColumnIndex(nodex) == 0)
				return ((Label) node).getText().substring(0, ((Label) node).getText().length()-3);
		
		return null;
	}
	
	private Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> complexHorizon(GridPane container, String plaEstudis, String campus, int iter){
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> margin = this.getHorizon(plaEstudis, null, 0, 0);
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> complex = ControladorPresentacio.getInstance().getMarginHorari(plaEstudis, campus, iter);
		
		if(!complex.first.isNull() && !complex.first.isNull()) {
			margin.first.first = Math.min(margin.first.first, complex.first.first);
			margin.first.second = Math.max(margin.first.second, complex.first.second);
			margin.second.first = Math.min(margin.second.first, complex.second.first);
			margin.second.second = Math.max(margin.second.second, complex.second.second);
		}
		
		return margin;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  UTILS //////////////////////////////////////
	
	public String getDayByIndex(int index) {
		switch(index) {
			case 0: return "DL";
			case 1: return "DM";
			case 2: return "DC";
			case 3: return "DJ";
			case 4: return "DV";
			case 5: return "DS";
			case 6: return "DG";
			
			default: return "";
		}
	}
	
	public int getIndexByDay(String day) {
		switch(day) {
			case "DL": return 0;
			case "DM": return 1;
			case "DC": return 2;
			case "DJ": return 3;
			case "DV": return 4;
			case "DS": return 5;
			case "DG": return 6;
			
			default: return -1;
		}
	}
	
	public void reset(GridPane container, boolean center) {
		container.getColumnConstraints().clear();
		container.getRowConstraints().clear();
		container.getChildren().clear();
		
		container.setAlignment(center? Pos.CENTER : Pos.TOP_LEFT);
	}
	
	public void buildGridPane(GridPane container) {
		configure(container, 0, 7, 0, 24);
	}

	public void buildGridPane(GridPane container, String plaEstudis, String assignatura, int grup, int subgrup) {
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> horizon = getHorizon(plaEstudis, assignatura, grup, subgrup);
		configure(container, horizon.first.first, horizon.first.second, horizon.second.first, horizon.second.second);
		enable(container, plaEstudis, assignatura, grup, subgrup);
	}
	
	public void buildHorari(GridPane container, String plaEstudis, String campus, int iter) {
		//Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> margin = new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(new Pair<Integer, Integer>(0,7), new Pair<Integer, Integer>(8,21));
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> margin = this.complexHorizon(container, plaEstudis, campus, iter);
		reset(container, false);
		
		//Construccio de totes les columnes (Columns):
		for(int dia = margin.first.first; dia <= margin.first.second; dia++) {
			ColumnConstraints colConst = new ColumnConstraints();
			colConst.setPercentWidth(100f/(margin.first.second - margin.first.first));
			container.getColumnConstraints().add(colConst);
		}
		//Construccio de totes les files (Rows):
		for(int hora = margin.second.first; hora <= margin.second.second; hora++) {
			RowConstraints rowConst = new RowConstraints();
			container.getRowConstraints().add(rowConst);
		}
		
		container.setMaxHeight(637);
		container.setMaxWidth(652);
	}
	
	public void buildAssignador(GridPane container, Map<Integer, HashSet<Integer>> conjunts, boolean sessioGrup) {
		reset(container, true);
		
		//Construccio de totes les files (Rows):
		for(int hora = 0; hora < conjunts.keySet().size(); hora++) {
			RowConstraints rowConst = new RowConstraints();
			container.getRowConstraints().add(rowConst);
		}
		
		int columnes = 0;
		for(int key : conjunts.keySet()) Math.max(columnes, conjunts.get(key).size());
		
		//Construccio de totes les columnes (Columns):
		for(int dia = 0; dia < columnes+1; dia++) {
			ColumnConstraints colConst = new ColumnConstraints();
			container.getColumnConstraints().add(colConst);
		}
		
		int fila = 0;
		for(int grup : conjunts.keySet()) {
			int columna = 1;
			container.add(sessioGrup? new CheckBox(String.valueOf(grup)) : new Label(String.valueOf(grup).concat(" : ")), columna-1, fila);
			
			if(!sessioGrup) for(int subgrup : conjunts.get(grup)) {
				container.add(new CheckBox(String.valueOf(subgrup)), columna, fila);
				columna++;
			}
			
			fila++;
		}
	}
		
	public void buildSolapaments(GridPane container, Map<String, HashSet<Integer>> conjunts, boolean isAssig) {
		reset(container, true);
		
		//Construccio de totes les files (Rows):
		for(int assig = 0; assig < conjunts.keySet().size(); assig++) {
			RowConstraints rowConst = new RowConstraints();
			container.getRowConstraints().add(rowConst);
		}
		
		if(!isAssig) {
			int columnes = 0;
			for(String key : conjunts.keySet()) Math.max(columnes, conjunts.get(key).size());
			
			//Construccio de totes les columnes (Columns):
			for(int dia = 0; dia < columnes+1; dia++) {
				ColumnConstraints colConst = new ColumnConstraints();
				container.getColumnConstraints().add(colConst);
			}
		}
		
		int fila = 0;
		for(String assig : conjunts.keySet()) {
			int columna = 1;
			container.add(isAssig? new CheckBox(assig) : new Label(assig.concat(" : ")), columna-1, fila);
			
			if(!isAssig) for(int numero : conjunts.get(assig)) {
				container.add(new CheckBox(String.valueOf(numero)), columna, fila);
				columna++;
			}
			
			fila++;
		}
	}
	
	public Map<Integer, boolean[]> scannGridPane(GridPane container) {
		Map<Integer, boolean[]> scan = new HashMap<>();
		
		for(Node node : container.getChildren()) {
			 if(GridPane.getColumnIndex(node).intValue() > 0 && GridPane.getRowIndex(node).intValue() > 0) {
				 if(!scan.containsKey(getDiaIHora(container, node).first))
					 scan.put(getDiaIHora(container, node).first, new boolean[24]);
				 
				 try {
					 scan.get(getDiaIHora(container, node).first)[getDiaIHora(container, node).second] = ((CheckBox) node).isSelected() && !((CheckBox) node).isDisable();
				 }
				 catch(Exception e) {
					 continue;
				 }
			 }
		}
		
		return scan;
	}

	public Map<Integer, int[]> scannForState(GridPane container, boolean selected){
		Map<Integer, boolean[]> tableState = scannGridPane(container);
		Map<Integer, int[]> scaned = new HashMap<>();
		
		for(int key: tableState.keySet()) {
			HashSet<Integer> useful = new HashSet<>();
			for(int hora = 0; hora < tableState.get(key).length; hora++)
				if(tableState.get(key)[hora] == selected) useful.add(hora);
			
			int index = 0;
			int[] candidats = new int[useful.size()];
			for(int hora : useful) candidats[index++] = hora;
			
			if(!scaned.containsKey(key)) scaned.put(key, candidats);
		}
		
		return scaned;
	}
	
	public Map<String, HashSet<Integer>> scannForState(GridPane container, boolean selected, boolean isAssig){
		Map<String, HashSet<Integer>> candidats = new HashMap<String, HashSet<Integer>>();
		
		String currentAssig = new String();
		for(Node node : container.getChildren()) {
			try {
				if(((CheckBox) node).isSelected() == selected) {
					if(isAssig && !candidats.containsKey(((CheckBox) node).getText()))
						candidats.put(((CheckBox) node).getText(), new HashSet<Integer>());
					else candidats.get(currentAssig).add(Integer.valueOf(((CheckBox) node).getText()));
				}
			}
			catch(Exception e) {
				currentAssig = ((Label) node).getText().substring(0, ((Label) node).getText().length()-3);
				if(!candidats.containsKey(currentAssig)) candidats.put(currentAssig, new HashSet<Integer>());
			}
		}
		
		return candidats;
	}
	
	public Map<Integer, HashSet<Integer>> scannAssignacions(GridPane container){
		Map<Integer, HashSet<Integer>> assignacions = new HashMap<Integer, HashSet<Integer>>();
		
		int currentGrup = 0;
		for(Node node: container.getChildren()) {
			try {
				if(GridPane.getColumnIndex(node) == 0) {
					currentGrup = Integer.parseInt(((CheckBox) node).getText());
					if(((CheckBox) node).isSelected()) assignacions.put(currentGrup, new HashSet<Integer>());
				}
				else assignacions.get(currentGrup).add(Integer.parseInt(((CheckBox) node).getText()));
								
			}
			catch(Exception e) {
				if(GridPane.getColumnIndex(node) == 0) {	
					currentGrup = Integer.parseInt(((Label) node).getText().substring(0, ((Label) node).getText().length()-3));
					if(!assignacions.containsKey(currentGrup)) assignacions.put(currentGrup, new HashSet<Integer>());
				}
				
				continue;
			}
		}
		
		return assignacions;
	}
	
	public Pair<String, Integer> scannButtonPressed(GridPane container, Node node){
		Pair<String, Integer> select = new Pair<String, Integer>(null, null);
		Pair<Integer, Integer> diaIHora = getDiaIHora(container, node);
		select.first = this.getDayByIndex(diaIHora.first);
		select.second = diaIHora.second;
		return select;
	}
	
	public void updateGridPane(GridPane container, String plaEstudis, String assignatura, int grup, int subgrup) {
		Map<Integer, boolean[]> state = ControladorPresentacio.getInstance().getHorizon(plaEstudis, assignatura, grup, subgrup);
				
		for(Node node: container.getChildren()) {
			if(GridPane.getColumnIndex(node).intValue() > 0 && GridPane.getRowIndex(node).intValue() > 0) {
				try {
					if(!((CheckBox) node).isDisable())
						((CheckBox) node).setSelected(state.get(getDiaIHora(container, node).first)[getDiaIHora(container, node).second]);
				}
				catch(Exception e) {
					continue;
				}
			}
		}
	}

	public void updateAssignador(GridPane container, String plaEstudis, String assignatura, String tipus, int hores, boolean sessioGrup) {
		HashSet<Integer> assignades = ControladorPresentacio.getInstance().getAssignades(plaEstudis, assignatura, tipus, hores, sessioGrup);
		for(Node node: container.getChildren()) {
			try {
				if(!((CheckBox) node).isDisable())
					((CheckBox) node).setSelected(assignades.contains(Integer.parseInt(((CheckBox) node).getText())));
			}
			catch(Exception e) {
				continue;
			}
		}
	}

	public void updateSolapaments(GridPane container, String plaEstudis, String assignatura, int grup, int subgrup) {
		Map<String, HashSet<Integer>> solaps = ControladorPresentacio.getInstance().getSolapaments(plaEstudis, assignatura, grup, subgrup);
		
		String currentAssignatura = new String();
		for(Node node: container.getChildren()) {
			try {
				if(!((CheckBox) node).isDisable()) {
					if(grup == 0 && subgrup == 0) ((CheckBox) node).setSelected(solaps.containsKey(((CheckBox) node).getText()) && !solaps.get(((CheckBox) node).getText()).isEmpty());
					else ((CheckBox) node).setSelected(solaps.containsKey(currentAssignatura) && solaps.get(currentAssignatura).contains(Integer.parseInt(((CheckBox) node).getText())));
				}
			}
			catch(Exception e) {
				currentAssignatura = ((Label) node).getText().substring(0, ((Label) node).getText().length()-3);
				continue;
			}
		}
	}
	
	public void updateHorari(GridPane container, String plaEstudis, String campus, int iter) {
		//Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> margin = new Pair<Pair<Integer, Integer>, Pair<Integer, Integer>>(new Pair<Integer, Integer>(0,7), new Pair<Integer, Integer>(8,21));
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> margin = this.complexHorizon(container, plaEstudis, campus, iter);
		container.getChildren().clear();
		
		//Agregració del contingut corresponent:
		for(int dia = margin.first.first; dia <= margin.first.second; dia++) {
			for(int hora = margin.second.first; hora <= margin.second.second; hora++) {
				if(dia == margin.first.first && hora == margin.second.first) container.add(new Label(), hora, dia);
				else if(hora == margin.second.first) container.add(new Label(getDayByIndex(dia-1)), dia, hora);
				else if(dia == margin.first.first) container.add(new Label(String.valueOf(hora-1).concat(":00 - ").concat(String.valueOf(hora == 24? 0 : hora)).concat(":00")), dia, hora);
				else {
					Button selector = new Button();
					selector.setOnAction(new EventHandler<ActionEvent>() {
			            @Override
			            public void handle(ActionEvent e) {
			                Main.getInstance().onHorariButtonPressed((Node)e.getSource());
			            }
			        });
					
					selector.setMaxHeight(1000);
					selector.setMaxWidth(1000);
					selector.setDisable(ControladorPresentacio.getInstance().getSegments(plaEstudis, campus, dia-1, hora-1, iter).isEmpty());
					container.add(selector, dia, hora);
					
				}
			}
		}
	}
}
