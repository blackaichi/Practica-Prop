package presentacio.vistes;

import java.util.*;
import presentacio.ControladorPresentacio;
import presentacio.tools.GridPaneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class AssignaturaManager {

	private static AssignaturaManager current;
	private static String path;
	
	@FXML private TextField nom;
	@FXML private ListView<String> grups, sessions;
	@FXML private GridPane solap_container, aptes_container;
	@FXML private Label title;
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public AssignaturaManager() {
		path = null;
		AssignaturaManager.current = this;
	}
	
	public static AssignaturaManager getInstance() {
		return AssignaturaManager.current;
	}
	
	public void setGradPane() {
		GridPaneManager.getInstance().buildGridPane(aptes_container, PlaEstudisManager.getPath(), null, 0, 0);
		if(!isNew()) GridPaneManager.getInstance().updateGridPane(aptes_container, PlaEstudisManager.getPath(), path, 0, 0);
	}
	
	public static void setPath(String path) {
		AssignaturaManager.getInstance().nom.setText(path);
		AssignaturaManager.getInstance().update();
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = nom.getText();
		title.setText("Assignatura: ".concat(nom.getText()));
		
		this.grups.getItems().clear();
		this.grups.getItems().addAll(ControladorPresentacio.getInstance().getAllGrups(PlaEstudisManager.getPath(), path));
		
		this.sessions.getItems().clear();
		this.sessions.getItems().addAll(ControladorPresentacio.getInstance().getAllSessions(PlaEstudisManager.getPath(), path));
		
		setGradPane();
		PlaEstudisManager.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		 //En cas de ser un nou campus:
		if(isNew()) ControladorPresentacio.getInstance().CrearAssignatura(PlaEstudisManager.getPath(), nom.getText());

		if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(), isNew()? nom.getText() : path, 0, 0, GridPaneManager.getInstance().scannForState(aptes_container, true), true, true);
		if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(), isNew()? nom.getText() : path, 0, 0, GridPaneManager.getInstance().scannForState(aptes_container, false), false, true);
		if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarAssginatura(PlaEstudisManager.getPath(), isNew()? nom.getText() : path, isNew()? null : nom.getText());
		
		if(!Main.onError(true)) this.update();
	}
	
	@FXML
	public void onCreateGrup() {
		if(isNew()) this.apply();
		Main.getInstance().newWindows("Grup_view.fxml", "Grup", 500, 719);
		GrupManager.getInstance().setGradPane();
	}
	
	@FXML
	public void onCreateSessio() {
		if(isNew()) this.apply();
		Main.getInstance().newWindows("Sessio_view.fxml", "Sessio", 500, 719);
	}

	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onGrupItemClicked(MouseEvent click) {
		if(click.getClickCount() == 2){
			Main.getInstance().newWindows("Grup_view.fxml", "Grup", 500, 719);
			GrupManager.setPath(grups.getSelectionModel().getSelectedItem());
		}
	}
	
	@FXML
	public void onSessioItemClicked(MouseEvent click) {
		if(click.getClickCount() == 2){
			Main.getInstance().newWindows("Sessio_view.fxml", "Sessio", 500, 719);
			SessioManager.setPath(sessions.getSelectionModel().getSelectedItem());
		}
	}
}
