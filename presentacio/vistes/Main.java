package presentacio.vistes;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	private GridPane horari_container;
	private ListView<String> plansEstudis, campus;
	
	private TextField searcher_pl, searcher_c, nhoraris;
	private Label selected_pl, selected_c;
	
	private CheckBox purge, D_LECTIU, H_LECTIU, ASSIG_SOLAP, ASSIG_HAPTES, G_SOLAP, G_HAPTES, G_FRANJA, S_ALIGN, S_NSESSIONS;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {	
		Parent root = FXMLLoader.load(getClass().getResource("Main_view.fxml"));
		
		primaryStage.setTitle("Generador d'horaris");
		primaryStage.setScene(new Scene(root, 1080, 720));
		primaryStage.show();
		
		this.iniAllObjects(root);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				//TODO: tancar totes les finestres obertes
			}
		});
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
		
	private FXMLLoader newWindows(String fxml, String title, int x, int y) {
		try {
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Parent root = FXMLLoader.load(getClass().getResource(fxml));
			
			stage.setTitle(title);
			stage.setScene(new Scene(root, x, y));

			stage.initModality(Modality.APPLICATION_MODAL);

			stage.show();
			
			return loader;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	public void updateListViews() {
		//Carrega tots els noms del campus i els plans d'estudis al listview corresponent
	}
	
	public static void showWarning(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////// ON CLICK ACTIONS ////////////////////////////////////
	
	@FXML
	public void onCreateCampus() {
		this.newWindows("Campus_view.fxml", "Campus", 400, 720);
	}
	
	@FXML
	public void onCreatePlaEstudis() {
		this.newWindows("PlaEstudis_view.fxml", "PlaEstudis", 1050, 720);
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
		Main.showWarning("En construcció", "Per desgracia la funcionalitat encara no està disponnible. Hauras d'esperar a que els nostres programadors facin alguna cosa decent. :-) ");
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
