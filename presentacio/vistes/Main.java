package presentacio.vistes;

import domini.ControladorDomini;
	
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.awt.Event;
import javafx.fxml.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	static private Main current;
	
	@FXML private GridPane horari_container;
	@FXML private ListView<String> plansEstudis, campus;
	
	@FXML private TextField searcher_pl, searcher_c, nhoraris;
	@FXML private Label selected_pl, selected_c, quants_horaris;
	
	@FXML private CheckBox purge, D_LECTIU, H_LECTIU, ASSIG_SOLAP, ASSIG_HAPTES, G_SOLAP, G_HAPTES, G_FRANJA, S_ALIGN, S_NSESSIONS;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		current = this;
		Parent root = FXMLLoader.load(getClass().getResource("Main_view.fxml"));
		
		primaryStage.setTitle("Generador d'horaris");
		primaryStage.setScene(new Scene(root, 1080, 720));
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				//TODO: tancar totes les finestres obertes
			}
		});
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
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	public void updateListViews() {
		//Carrega tots els noms del campus i els plans d'estudis al listview corresponent
	}
	
	public void showWarning(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		
		alert.show();
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////// INSTANCIADORA /////////////////////////////////////
	/**
	 * Retorna la instancia corrent de la classe main.
	 * @return Un objecte de tipus Main, null o no.
	 */
	static public Main getInstance() {
		return Main.current;
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////// ON CLICK ACTIONS ////////////////////////////////////
	
	@FXML
	public void onCreateCampus() {
		this.newWindows("Campus_view.fxml", "Campus", 400, 720);
	}
	
	@FXML
	public void onCreatePlaEstudis() {
		this.newWindows("PlaEstudis_view.fxml", "Pla d'estudis", 1050, 720);
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
		this.plansEstudis.getItems().add("PenisEnVinagre");
		this.showWarning("En construcció", "Per desgracia la funcionalitat encara no està disponnible. Hauras d'esperar a que els nostres programadors facin alguna cosa decent. :-) ");
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
	
}
