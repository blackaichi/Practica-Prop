package presentacio.vistes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PlaEstudisManager {
	@FXML private TextField nom_id;
	@FXML private TextField autor_id;
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	private void handleButton1Action(ActionEvent event) {

	    if (nom_id.getText() == null || nom_id.getText().trim().isEmpty()) Main.showWarning("Error nou_Campus", "El nom del nou Campus no pot ser null");
	    if (autor_id.getText() == null || autor_id.getText().trim().isEmpty()) Main.showWarning("Error nou_Campus", "El nom del autor no pot ser null");
	    

	}
}
