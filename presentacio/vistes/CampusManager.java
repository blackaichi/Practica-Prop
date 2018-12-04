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

public class CampusManager {
	
	@FXML private TextField nom_id, autor_id;
	@FXML private ListView<String> aules;
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	private void handleButton1Action(ActionEvent event) {
	    if (nom_id.getText() == null || nom_id.getText().trim().isEmpty()) Main.getInstance().showWarning("Error nou_Campus", "El nom del nou Campus no pot ser null");
	    if (autor_id.getText() == null || autor_id.getText().trim().isEmpty()) Main.getInstance().showWarning("Error nou_Campus", "El nom del autor no pot ser null");
		System.out.println(nom_id.getText());
		System.out.println(autor_id.getText());
	}
	
	@FXML
	public void onCreateAula() {
		Main.getInstance().newWindows("Aula_view.fxml", "Aula", 500, 240);
	}
	
	@FXML
	public void apply() {
		Main.getInstance().showWarning("Welcome to Carthage", "Penis");
	}
	
}
