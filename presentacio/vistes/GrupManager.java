package presentacio.vistes;

import domini.ControladorDomini;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GrupManager {

	@FXML private TextField nom, places;
	@FXML private ListView<String> subgrups;
	@FXML private GridPane solap_container, aptes_container;
	@FXML private Label title, path;
	@FXML private MenuButton menu;
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void onCreateSubGrup() {
		Main.getInstance().newWindows("Subgrup_view.fxml", "Subgrup", 500, 719);
	}
	
	@FXML
	public void apply() {
		Main.getInstance().showWarning("Welcome to Carthage", "Penis");
	}
}
