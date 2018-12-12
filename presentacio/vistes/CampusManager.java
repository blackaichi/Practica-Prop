package presentacio.vistes;

import presentacio.ControladorPresentacio;
import java.util.*;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.awt.Event;
import javafx.fxml.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class CampusManager {
	
	private static CampusManager current;
	private static String path;
	
	@FXML private TextField nom_id, autor_id;
	@FXML private ListView<String> aules;
	@FXML private Label title;
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public CampusManager() {
		path = null;
		CampusManager.current = this;
	}
	
	public static CampusManager getInstance() {
		return CampusManager.current;
	}
	
	public static void setPath(String path) {
		CampusManager.getInstance().nom_id.setText(path);
		CampusManager.getInstance().update();
		
		CampusManager.getInstance().autor_id.setText(ControladorPresentacio.getInstance().GetMainCampusData(path));
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = nom_id.getText();
		title.setText("Campus: ".concat(nom_id.getText()));
		
		this.aules.getItems().clear();
		this.aules.getItems().addAll(ControladorPresentacio.getInstance().getAllAules(path));
		
		Main.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		 //En cas de ser un nou campus:
		if(isNew()) ControladorPresentacio.getInstance().CrearCampus(nom_id.getText());

		if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarCampus(isNew()? nom_id.getText() : path, isNew()? null : nom_id.getText(), autor_id.getText());
		if(!Main.onError(true)) this.update();
	}

	@FXML
	public void onCreateAula() {
		if(isNew()) this.apply();
		Main.getInstance().newWindows("Aula_view.fxml", "Aula", 500, 240);
	}

	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onAulaItemClicked(MouseEvent click) {
		if(click.getClickCount() == 2){
			Main.getInstance().newWindows("Aula_view.fxml", "Aula", 500, 240);
			AulaManager.setPath(this.aules.getSelectionModel().getSelectedItem());
		}
	}
}
