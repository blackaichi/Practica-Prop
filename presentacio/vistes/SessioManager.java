package presentacio.vistes;

import domini.ControladorDomini;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SessioManager {

	@FXML private TextField tipus, durada, nsessions, equip;
	@FXML private ListView<String> subgrups;
	@FXML private GridPane assignats, solap_container, aptes_container;
	@FXML private Label title, path, franja;
	@FXML private MenuButton menu;
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void onCreateSubGrup() {
		Main.getInstance().newWindows("SubGrup_view.fxml", "Subgrup", 500, 719);
	}
	
	@FXML
	public void apply() {
		Main.getInstance().showWarning("Welcome to Carthage", "Penis");
	}
}
