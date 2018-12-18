package presentacio.vistes;

import utils.*;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import presentacio.ControladorPresentacio;
import presentacio.tools.GridPaneManager;
import javafx.scene.input.MouseEvent;

public class SegmentsManager {
	
	static private SegmentsManager current;
	
	@FXML private ListView<String> sessions;
	@FXML private Label plaEstudis, campus, dia, hora, horaIni, durada;
	@FXML private TextField newHoraIni;
	@FXML private Button commit;
	@FXML private MenuButton menu;
	
	private int iteration;
	private Map<String, Pair<String, String>> timeData;
	
	public static SegmentsManager getInstance() {
		return SegmentsManager.current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private String minimize(String dia) {
		switch(dia) {
			case "Dilluns": return "DL";
			case "Dimarts": return "DM";
			case "Dimecres": return "DC";
			case "Dijous": return "DJ";
			case "Divendres": return "DV";
			case "Dissabte": return "DS";
			case "Dimenge": return "DG";
			default: return dia;
		}
	}
	
	private String expand(String dia) {
		switch(dia) {
			case "DL": return "Dilluns";
			case "DM": return "Dimarts";
			case "DC": return "Dimecres";
			case "DJ": return "Dijous";
			case "DV": return "Divendres";
			case "DS": return "Dissabte";
			case "DG": return "Dimenge";
			default: return dia;
		}
	}
	
	private boolean paremChecker() {
		try {
			int hora = Integer.parseInt(newHoraIni.getText());
			if(hora < 0 || hora > 23)
				Main.getInstance().showWarning("Hora incorrecte", "L'hora d'inici ha d'estar entre les 0:00 i les 23:00.");
			else if(GridPaneManager.getInstance().getIndexByDay(minimize(menu.getText())) < 0)
				Main.getInstance().showWarning("Dia incorrecte", "Selecciona un dia de la setmana.");
			else return true;
			
		}
		catch(NumberFormatException e) {
			Main.getInstance().showWarning("Hora incorrecte", "L'hora d'inici a la qual desplaçar la sessio no està escrita correctament.");
		}
		
		return false;
	}
	
	private ArrayList<String> depureDades(String str) {
		ArrayList<String> dades = new ArrayList<>();
		
		String[] depurat = str.split("\n");
		dades.add(depurat[0].split("\t")[1]);
		dades.add(depurat[1].split("\t")[1].isEmpty()? depurat[1].split("\t\t")[1] : depurat[1].split("\t")[1]);
		return dades;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public SegmentsManager() {
		current = this;
		this.timeData = new HashMap<>();
	}
	
	public void setPath(String plaEstudis, String campus, String dia, String hora, int iter) {
		this.plaEstudis.setText(plaEstudis);
		this.campus.setText(campus);
		this.dia.setText(expand(dia));
		this.hora.setText(hora);
		
		this.iteration = iter;
		this.update();
	}
	
	public void update() {
		timeData.clear();
		sessions.getItems().clear();
		for(ArrayList<String> dades : ControladorPresentacio.getInstance().getSegments(plaEstudis.getText(),
																					   campus.getText(),
																					   GridPaneManager.getInstance().getIndexByDay(minimize(dia.getText())),
																					   Integer.parseInt(hora.getText()),
																					   iteration)) {
			String page = "Assignatura: \t".concat(dades.get(0));
			page = page.concat("\n").concat("Nº ").concat(dades.get(1).equals("G")? "Grup: \t\t" : "Subgrup: \t").concat(dades.get(2));
			page = page.concat("\n").concat("Sessio: \t\t").concat(dades.get(3));
			page = page.concat("\n").concat("Aula: \t\t").concat(dades.get(5));
			
			sessions.getItems().add(page);
			
			String key = dades.get(0).concat("::").concat(dades.get(2));
			timeData.put(key, new Pair<String, String>(dades.get(4), dades.get(6)));
		}
		
		Main.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void onTryToCommit() {
		if(paremChecker()) {
			if(commit.getText().equals("Provar")) {
				HashSet<String> warning = ControladorPresentacio.getInstance().ModificarHorari(plaEstudis.getText(),
																							   campus.getText(),
																							   iteration,
																							   depureDades(sessions.getSelectionModel().getSelectedItem()).get(0),
																							   Integer.parseInt(depureDades(sessions.getSelectionModel().getSelectedItem()).get(1)),
																							   GridPaneManager.getInstance().getIndexByDay(minimize(dia.getText())),
																							   Integer.parseInt(hora.getText()),
																							   GridPaneManager.getInstance().getIndexByDay(minimize(menu.getText())),
																							   Integer.parseInt(newHoraIni.getText()),
																							   false,
																							   false);
				
				if(!warning.isEmpty()) {
					String message = "Desplaçar aquesta sessió a la franja seleccionada viola les següents restriccions configurades a l'horari:\n\n";
					for(String str : warning) message = message.concat("\t").concat(" - ").concat(str).concat("\n");
					Main.getInstance().showWarning("Incongruencies", message);
					commit.setText("Forçar");
				}
				else commit.setText("Aplicar");
			}
			else {
				ControladorPresentacio.getInstance().ModificarHorari(plaEstudis.getText(),
													   			      campus.getText(),
																      iteration,
																      depureDades(sessions.getSelectionModel().getSelectedItem()).get(0),
																      Integer.parseInt(depureDades(sessions.getSelectionModel().getSelectedItem()).get(1)),
																      GridPaneManager.getInstance().getIndexByDay(minimize(dia.getText())),
																      Integer.parseInt(hora.getText()),
																      GridPaneManager.getInstance().getIndexByDay(minimize(menu.getText())),
																      Integer.parseInt(newHoraIni.getText()),
																      true,
																      true);
				
				commit.setText("Provar");
				commit.setDisable(true);
				
				this.horaIni.setText("");
				this.durada.setText("");
				
				this.update();
			}
		}
	}
	
	@FXML
	public void onMenuAction(ActionEvent event) {
		MenuItem item = (MenuItem) event.getSource();
		menu.setText(item.getText());
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	@FXML
	public void onSessioItemClicked(MouseEvent click) {
		if(sessions.getSelectionModel().getSelectedIndex() > -1) {
			String assig = depureDades(sessions.getSelectionModel().getSelectedItem()).get(0);
			String numero = depureDades(sessions.getSelectionModel().getSelectedItem()).get(1);
						
			horaIni.setText(this.timeData.get(assig.concat("::").concat(numero)).first);
			durada.setText(this.timeData.get(assig.concat("::").concat(numero)).second);
			
			commit.setText("Provar");
			commit.setDisable(false);
		}
	}
}
