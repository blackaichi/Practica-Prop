package presentacio.vistes;

import presentacio.ControladorPresentacio;	
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AssignaturaManager {

	@FXML private TextField nom;
	@FXML private ListView<String> grups, sessions;
	@FXML private GridPane solap_container, aptes_container;
	@FXML private Label title;
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void onCreateGrup() {
		Main.getInstance().newWindows("Grup_view.fxml", "Grup", 500, 719);
	}
	
	@FXML
	public void onCreateSessio() {
		Main.getInstance().newWindows("Sessio_view.fxml", "Sessio", 500, 719);
	}
	
	@FXML
	public void apply() {
		Main.getInstance().showWarning("Welcome to Carthage", "Penis");
	}
}
