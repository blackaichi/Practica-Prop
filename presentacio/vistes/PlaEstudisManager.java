package presentacio.vistes;

import presentacio.tools.*;
import java.util.*;
import domini.ControladorDomini;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
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
	
	private int[] scannRang() {
		try {
			int[] scan = new int[4];
			scan[0] = Integer.parseUnsignedInt(rang1.getText());
			scan[1] = Integer.parseUnsignedInt(rang2.getText());
			scan[2] = Integer.parseUnsignedInt(rang3.getText());
			scan[3] = Integer.parseUnsignedInt(rang4.getText());
			return scan;
		}
		catch(NumberFormatException e) {
			Main.getInstance().showWarning("Format incorrecte", "Els rangs han d'estar formats per hores valides.");
			return null;
		}
	}
	
	private boolean paramChecker() {
		return scannRang() != null;
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
	
	public void setGradPane() {
		GridPaneManager.getInstance().buildGridPane(lectiu_container);
		if(!isNew()) GridPaneManager.getInstance().updateGridPane(lectiu_container, path, null, 0, 0);
	}
	
	public static void setPath(String path) {
		PlaEstudisManager.getInstance().nom_id.setText(path);
		PlaEstudisManager.getInstance().update();
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = nom_id.getText();
		title.setText("Pla d'estudis: ".concat(nom_id.getText()));
		
		this.assignatures.getItems().clear();
		this.assignatures.getItems().addAll(ControladorPresentacio.getInstance().getAllAssignatures(path));
		
		setGradPane();
		Main.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		if(paramChecker()) {
			 //En cas de ser un nou campus:
			if(isNew()) ControladorPresentacio.getInstance().CrearPlaEstudis(nom_id.getText());
	
			if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarPlaEstudis(isNew()? nom_id.getText() : path,
																						 isNew()? null : nom_id.getText(),
																						 autor_id.getText(),
																						 GridPaneManager.getInstance().scannGridPane(lectiu_container),
																						 this.scannRang());
			
			if(!Main.onError(true)) this.update();
		}
	}
	
	@FXML
	public void onCreateAssignatura() {
		if(isNew()) this.apply();
		Main.getInstance().newWindows("Assignatura_view.fxml", "Assignatura", 590, 720);
		AssignaturaManager.getInstance().setGradPane();
	}

	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onAssignaturaItemClicked(MouseEvent click) {
		if(click.getClickCount() == 2){
			Main.getInstance().newWindows("Assignatura_view.fxml", "Assignatura", 590, 720);
			AssignaturaManager.setPath(assignatures.getSelectionModel().getSelectedItem());
		}
	}
}
