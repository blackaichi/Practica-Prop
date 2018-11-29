package presentacio.vistes;

import domini.ControladorDomini;
	
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.awt.Event;
import javafx.fxml.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage Window;
	
	private GridPane horari_container;
	private ListView<String> plansEstudis, campus;
	
	private TextField searcher_pl, searcher_c, nhoraris;
	private Label selected_pl, selected_c;
	
	private CheckBox purge,
					 D_LECTIU, H_LECTIU,
					 ASSIG_SOLAP, ASSIG_HAPTES,
					 G_SOLAP, G_HAPTES, G_FRANJA,
					 S_ALIGN, S_NSESSIONS;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Window = primaryStage;
		Parent root = FXMLLoader.load(getClass().getResource("Activity_main.fxml"));
		
		primaryStage.setTitle("Generador d'horaris");
		primaryStage.setScene(new Scene(root, 1080, 720));
		primaryStage.show();
		
		this.iniAllObjects(root);
	}
	
	private void iniAllObjects(Parent root) {
		this.horari_container = (GridPane) root.lookup("#horari_container");
		
		this.plansEstudis = (ListView<String>) root.lookup("#list_plaEstudis");
		this.campus = (ListView<String>) root.lookup("#list_campus");
		
		this.searcher_pl = (TextField) root.lookup("#plaestudis_cercador");
		this.searcher_c = (TextField) root.lookup("#campus_cercador");
		this.nhoraris = (TextField) root.lookup("#nhoraris");
		
		this.selected_pl = (Label) root.lookup("#selected_plaEstudis");
		this.selected_pl = (Label) root.lookup("#selected_campus");
		
		this.purge = (CheckBox) root.lookup("#purge");
		this.D_LECTIU = (CheckBox) root.lookup("#d_lectiu");
		this.H_LECTIU = (CheckBox) root.lookup("#h_lectiu");
		this.ASSIG_SOLAP = (CheckBox) root.lookup("#assig_solap");
		this.ASSIG_HAPTES = (CheckBox) root.lookup("#assig_haptes");
		this.G_SOLAP = (CheckBox) root.lookup("#g_solap");
		this.G_HAPTES = (CheckBox) root.lookup("#g_haptes");
		this.G_FRANJA = (CheckBox) root.lookup("#g_franja");
		this.S_ALIGN = (CheckBox) root.lookup("#s_align");
		this.S_NSESSIONS = (CheckBox) root.lookup("#s_nsessions");
		
		this.updateListViews();
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////// ACTIONS ///////////////////////////////////////
	
	public void updateListViews() {
		//Carrega tots els noms del campus i els plans d'estudis al listview corresponent
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////// ON CLICK ACTIONS ////////////////////////////////////
	
	@FXML
	public void onCreateCampus() {
		System.out.println("onCrateCampus");
	}
	
	@FXML
	public void onCreatePlaEstudis() {
		System.out.println("onCreatePlaEstudis");
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
		System.out.println("onGenerarHorari");
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
