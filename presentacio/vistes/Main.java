package presentacio.vistes;

import presentacio.ControladorPresentacio;
import presentacio.tools.GridPaneManager;

import utils.Pair;
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.awt.Event;
import javafx.fxml.*;
import javafx.util.*;
import javafx.event.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	static private Main current;
	static private boolean errorOcurred;
	
	private Pair<String, Boolean> lastSelected;
	
	@FXML private GridPane horari_container;
	@FXML private ListView<String> plansEstudis, campus;
	
	@FXML private TextField searcher_pl, searcher_c, nhoraris;
	@FXML private Label selected_pl, selected_c, quants_horaris;
	
	@FXML private CheckBox purge, D_LECTIU, H_LECTIU, ASSIG_SOLAP, ASSIG_HAPTES, G_SOLAP, G_HAPTES, G_FRANJA, S_ALIGN, S_NSESSIONS;
	@FXML private Button next, previous;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Main getInstance() {
		return Main.current;
	}
	
	public static boolean onError(boolean restore) {
		if(errorOcurred) {
			if(restore) errorOcurred = false;
			return true;
		}
		else return false;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Main_view.fxml"));
		
		primaryStage.setTitle("Generador d'horaris");
		primaryStage.setScene(new Scene(root, 1080, 790));
		primaryStage.show();
	}
	
	public void newWindows(String fxml, String title, int x, int y) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource(fxml));
			
			stage.setTitle(title);
			stage.setScene(new Scene(root, x, y));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private HashSet<String> computeFlags(){
		HashSet<String> flags = new HashSet<>();
		if(D_LECTIU.isSelected()) flags.add("D_LECTIU");
		if(H_LECTIU.isSelected()) flags.add("H_LECTIU");
		if(ASSIG_SOLAP.isSelected()) flags.add("ASSIG_SOLAP");
		if(ASSIG_HAPTES.isSelected()) flags.add("ASSIG_HAPTES");
		if(G_SOLAP.isSelected()) flags.add("G_SOLAP");
		if(G_HAPTES.isSelected()) flags.add("G_HAPTES");
		if(G_FRANJA.isSelected()) flags.add("G_FRANJA");
		if(S_ALIGN.isSelected()) flags.add("S_ALIGN");
		if(S_NSESSIONS.isSelected()) flags.add("S_NSESSIONS");
		
		return flags;
	}
	
	private boolean paramChecker(){
		if(selected_pl.getText().isEmpty() || selected_c.getText().isEmpty()) {
			this.showWarning("Falta informació", "Cal seleccionar una parella de PlaEstudis i Campus.");
			return false;
		}
		else try {
			Integer.parseUnsignedInt(nhoraris.getText());
			return true;
		}
		catch(NumberFormatException e) {
			Main.getInstance().showWarning("Format incorrecte", "La quantitat d'horaris a generar no està definida en el format correcte.");
			return false;
		}
	}
	
	private boolean checkSelection() {
		if(this.lastSelected.first == null) {
			this.showWarning("Acció incorrecte", "Cal seleccionar un pla d'estudis o un campus per poder procedir.");
			return false;
		}
		
		return true;
	}
	
	private int getIteration() {
		String[] depurat = quants_horaris.getText().split(" ");
		return Integer.parseInt(depurat[1]);
	}
	
	private int getNHoraris() {
		String[] depurat = quants_horaris.getText().split(" ");
		return Integer.parseInt(depurat[3]);
	}
	
	private void setButtonsState() {
		next.setDisable(this.getIteration() == this.getNHoraris());
		previous.setDisable(this.getIteration() <= 1);
	}
	
	private void loadSelected(int nhoraris, boolean purge) {
		if(plansEstudis.getSelectionModel().getSelectedIndex() > -1 && !selected_pl.getText().isEmpty() &&
		   campus.getSelectionModel().getSelectedIndex() > -1 && !selected_c.getText().isEmpty()) {
			int result = ControladorPresentacio.getInstance().generarHorari(selected_pl.getText(),
																			selected_c.getText(),
																			nhoraris,
																			computeFlags(),
																			purge);

			quants_horaris.setText("Horari ".concat(result == 0? "0" : "1").concat(" de ").concat(String.valueOf(result)));
			GridPaneManager.getInstance().buildHorari(horari_container, selected_pl.getText(), selected_c.getText());
			
			if(this.getNHoraris() > 0)
				GridPaneManager.getInstance().updateHorari(horari_container, selected_pl.getText(), selected_c.getText(), getIteration());
			
			this.setButtonsState();
		}
		else {
			quants_horaris.setText("Horari 0 de 0");
			GridPaneManager.getInstance().reset(horari_container, false);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public Main() {
		Main.current = this;
		Main.errorOcurred = false;
		
		lastSelected = new Pair<String, Boolean>(null, null);
	}
		
	public void update() {
		this.campus.getItems().clear();
		this.campus.getItems().addAll(ControladorPresentacio.getInstance().getAllCampus());
		
		this.plansEstudis.getItems().clear();
		this.plansEstudis.getItems().addAll(ControladorPresentacio.getInstance().getAllPlaEstudis());
		this.loadSelected(getIteration(), false);
	}
		
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	public void onCampusSelected() {
		this.selected_c.setText(campus.getSelectionModel().getSelectedItem());
		
		this.lastSelected.first = campus.getSelectionModel().getSelectedItem();
		this.lastSelected.second = true;
		
		this.loadSelected(0, false);
	}
	
	public void onPlaEstudisSelected() {
		this.selected_pl.setText(plansEstudis.getSelectionModel().getSelectedItem());
		
		this.lastSelected.first = plansEstudis.getSelectionModel().getSelectedItem();
		this.lastSelected.second = false;
		
		this.loadSelected(0, false);
	}
	
	public void showWarning(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		
		alert.show();
		Main.errorOcurred = true;
	}
	
	@FXML
	public void onCreateCampus() {
		this.newWindows("Campus_view.fxml", "Campus", 400, 720);
	}
	
	@FXML
	public void onCreatePlaEstudis() {
		this.newWindows("PlaEstudis_view.fxml", "Pla d'estudis", 1050, 733);
		PlaEstudisManager.getInstance().setGradPane();
	}
	
	@FXML
	public void showPreviousHorari() {
		if(paramChecker()) {
			quants_horaris.setText("Horari ".concat(String.valueOf(getIteration()-1)).concat(" de ").concat(String.valueOf(getNHoraris())));
			GridPaneManager.getInstance().updateHorari(horari_container, selected_pl.getText(), selected_c.getText(), getIteration());
			this.setButtonsState();
		}
	}
	
	@FXML
	public void showNextHorari() {
		if(paramChecker()) {
			quants_horaris.setText("Horari ".concat(String.valueOf(getIteration()+1)).concat(" de ").concat(String.valueOf(getNHoraris())));
			GridPaneManager.getInstance().updateHorari(horari_container, selected_pl.getText(), selected_c.getText(), getIteration());
			this.setButtonsState();
		}
	}
	
	@FXML
	public void onGenerarHorari(){
		if(paramChecker()) this.loadSelected(Integer.parseInt(nhoraris.getText()), purge.isSelected());
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON TEXT ACTIONS ////////////////////////////////////
	
	@FXML
	public void onSearchPlaEstudis() {
		System.out.println("onSearchPlaEstudis");
	}
	
	@FXML
	public void onSearchCampus() {
		System.out.println("onSearchCampus");
	}
		
	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onCampusItemClicked(MouseEvent click) {
		if(click != null && click.getClickCount() == 1) this.onCampusSelected();
		else if(campus.getSelectionModel().getSelectedIndex() > -1) {
			this.newWindows("Campus_view.fxml", "Campus", 400, 720);
			CampusManager.setPath(campus.getSelectionModel().getSelectedItem());
		}
	}
	
	@FXML
	public void onPlaEstudisItemClicked(MouseEvent click) {
		if(click != null && click.getClickCount() == 1) this.onPlaEstudisSelected();
		else if(plansEstudis.getSelectionModel().getSelectedIndex() > -1){
			this.newWindows("PlaEstudis_view.fxml", "Pla d'estudis", 1050, 733);
			PlaEstudisManager.setPath(plansEstudis.getSelectionModel().getSelectedItem());
		}
	}

	public void onHorariButtonPressed(MouseEvent click) {
		Pair<String, Integer> select = GridPaneManager.getInstance().scannButtonPressed(horari_container);
		if(!select.isNull()) {
			this.newWindows("Segments_view.fxml", "Sessions", 450, 629);
			SegmentsManager.getInstance().setPath(selected_pl.getText(), selected_c.getText(), select.first, String.valueOf(select.second));
		}
	}
	
	@FXML
	public void onModify() {
		if(checkSelection()) {
			if(lastSelected.second) onCampusItemClicked(null);
			else onPlaEstudisItemClicked(null);
		}
	}
	
	@FXML
	public void onDelete() {
		if(checkSelection()) {
			if(lastSelected.second) {
				ControladorPresentacio.getInstance().EliminarCampus(campus.getSelectionModel().getSelectedItem());
				this.selected_c.setText("");
			}
			else {
				ControladorPresentacio.getInstance().EliminaPlaEstudis(plansEstudis.getSelectionModel().getSelectedItem());
				this.selected_pl.setText("");
			}
			this.update();
		}
	}
	
	@FXML
	public void onDeleteHorari() {
		if(getIteration() == 0) showWarning("Acció impossible", "No hi ha cap horari per esborrar.");
		else {
			ControladorPresentacio.getInstance().EliminaHorari(plansEstudis.getSelectionModel().getSelectedItem(),
															   campus.getSelectionModel().getSelectedItem(),
															   getIteration());
			this.update();
		}
	}
}
