package presentacio.vistes;

import domini.ControladorDomini;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class SubGrupManager {

	@FXML private TextField nom, places;
	@FXML private GridPane solap_container, aptes_container;
	@FXML private Label title, path, franja;
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		Main.getInstance().showWarning("Welcome to Carthage", "Penis");
	}
}
