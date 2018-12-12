package presentacio.vistes;

import presentacio.ControladorPresentacio;	
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
	
	@FXML private GridPane horari_container;
	@FXML private ListView<String> plansEstudis, campus;
	
	@FXML private TextField searcher_pl, searcher_c, nhoraris;
	@FXML private Label selected_pl, selected_c, quants_horaris;
	
	@FXML private CheckBox purge, D_LECTIU, H_LECTIU, ASSIG_SOLAP, ASSIG_HAPTES, G_SOLAP, G_HAPTES, G_FRANJA, S_ALIGN, S_NSESSIONS;
	
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
		primaryStage.setScene(new Scene(root, 1080, 720));
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
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public Main() {
		Main.current = this;
		Main.errorOcurred = false;
	}
		
	public void update() {
		this.campus.getItems().clear();
		this.campus.getItems().addAll(ControladorPresentacio.getInstance().getAllCampus());
		
		this.plansEstudis.getItems().clear();
		this.plansEstudis.getItems().addAll(ControladorPresentacio.getInstance().getAllPlaEstudis());
	}
		
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	public void onCampusSelected() {
		System.out.println(String.valueOf(campus.getSelectionModel().getSelectedItem()));
	}
	
	public void onPlaEstudisSelected() {
		System.out.println(String.valueOf(plansEstudis.getSelectionModel().getSelectedItem()));
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
		System.out.println("showPreviousHorari");
	}
	
	@FXML
	public void showNextHorari() {
		System.out.println("showNextHorari");
	}
	
	@FXML
	public void onGenerarHorari(){
		if(paramChecker()) {
			
		}
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
	
	@FXML
	public void onNHoraris() {
		System.out.println("onNHoraris");
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onCampusItemClicked(MouseEvent click) {
		if(click.getClickCount() == 1) this.selected_c.setText(campus.getSelectionModel().getSelectedItem());
		else {
			this.newWindows("Campus_view.fxml", "Campus", 400, 720);
			CampusManager.setPath(campus.getSelectionModel().getSelectedItem());
		}
	}
	
	@FXML
	public void onPlaEstudisItemClicked(MouseEvent click) {
		if(click.getClickCount() == 1) this.selected_pl.setText(plansEstudis.getSelectionModel().getSelectedItem());
		else {
			this.newWindows("PlaEstudis_view.fxml", "Pla d'estudis", 1050, 733);
			PlaEstudisManager.setPath(plansEstudis.getSelectionModel().getSelectedItem());
		}
	}
}
