package presentacio.vistes;

import presentacio.ControladorPresentacio;
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

public class CampusManager {
	
	@FXML private TextField nom_id, autor_id;
	@FXML private ListView<String> aules;
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void onCreateAula() {
		Main.getInstance().newWindows("Aula_view.fxml", "Aula", 500, 240);
	}
	
	@FXML
	public void apply() {
		this.update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private void update() {
		ControladorPresentacio.getInstance().CrearCampus(nom_id.getText());
	}
}
