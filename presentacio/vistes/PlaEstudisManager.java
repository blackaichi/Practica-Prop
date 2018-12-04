package presentacio.vistes;

import domini.ControladorDomini;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import presentacio.ControladorPresentacio;

public class PlaEstudisManager {
	
	private static PlaEstudisManager current;
	private static String path;
	
	@FXML private TextField nom_id, autor_id, rang1, rang2, rang3, rang4;
	@FXML private GridPane lectiu_container;
	@FXML private ListView<String> assignatures;
	@FXML private Label title;
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public PlaEstudisManager() {
		path = null;
		PlaEstudisManager.current = this;
	}
	
	public static PlaEstudisManager getInstance() {
		return PlaEstudisManager.current;
	}
	
	public static void setPath(String path) {
		PlaEstudisManager.path = path;
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = nom_id.getText();
		title.setText("Pla d'estudis: ".concat(nom_id.getText()));
		
		this.assignatures.getItems().clear();
		this.assignatures.getItems().addAll(ControladorPresentacio.getInstance().getAllAssignatures(path));
	}
	
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		 //En cas de ser un nou campus:
		if(isNew()) ControladorPresentacio.getInstance().CrearPlaEstudis(nom_id.getText());

		ControladorPresentacio.getInstance().ModificarPlaEstudis(isNew()? nom_id.getText() : path, isNew()? null : nom_id.getText(), autor_id.getText(), null, null);
		this.update();
	}
	
	@FXML
	public void onCreateAssignatura() {
		Main.getInstance().newWindows("Assignatura_view.fxml", "Assignatura", 590, 720);
	}

}
