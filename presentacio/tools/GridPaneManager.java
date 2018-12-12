package presentacio.tools;

import utils.*;
import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import presentacio.ControladorPresentacio;

public class GridPaneManager {
	
	static private GridPaneManager current;
	
	static public GridPaneManager getInstance() {
		if(current == null) current = new GridPaneManager();
		return current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private String getDayByIndex(int index) {
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
	
	private void configure(GridPane container, int minCol, int maxCol, int minRow, int maxRow) {
		container.setAlignment(Pos.CENTER);
		
		container.getColumnConstraints().clear();
		container.getRowConstraints().clear();
		
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
		
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  UTILS //////////////////////////////////////
	
	public void buildGridPane(GridPane container) {
		configure(container, 0, 7, 0, 24);
	}

	public void buildGridPane(GridPane container, String plaEstudis, String assignatura, int grup, int subgrup) {
		Pair<Pair<Integer, Integer>, Pair<Integer, Integer>> horizon = getHorizon(plaEstudis, assignatura, grup, subgrup);
		configure(container, horizon.first.first, horizon.first.second, horizon.second.first, horizon.second.second);
		enable(container, plaEstudis, assignatura, grup, subgrup);
	}
	
	public Map<Integer, boolean[]> scannGridPane(GridPane container) {
		Map<Integer, boolean[]> scan = new HashMap<>();
		for(Node node : container.getChildren()) {
			 if(GridPane.getColumnIndex(node).intValue() > 0 && GridPane.getRowIndex(node).intValue() > 0) {
				 if(!scan.containsKey(GridPane.getColumnIndex(node).intValue()-1))
					 scan.put(GridPane.getColumnIndex(node).intValue()-1, new boolean[24]);
				 
				 try {
					 scan.get(GridPane.getColumnIndex(node).intValue()-1)[GridPane.getRowIndex(node).intValue()-1] = ((CheckBox) node).isSelected();
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
			for(int hora : useful) {
				candidats[index] = hora;
				index++;
			}
			
			if(!scaned.containsKey(key)) scaned.put(key, candidats);
		}
		
		return scaned;
	}
	
	public void updateGridPane(GridPane container, String plaEstudis, String assignatura, int grup, int subgrup) {
		Map<Integer, boolean[]> state = ControladorPresentacio.getInstance().getHorizon(plaEstudis, assignatura, grup, subgrup);
				
		for(Node node: container.getChildren()) {
			if(GridPane.getColumnIndex(node).intValue() > 0 && GridPane.getRowIndex(node).intValue() > 0) {
				try {
					((CheckBox) node).setSelected(state.get(GridPane.getColumnIndex(node).intValue()-1)[GridPane.getRowIndex(node).intValue()-1]);
				}
				catch(Exception e) {
					continue;
				}
			}
		}
	}
}
