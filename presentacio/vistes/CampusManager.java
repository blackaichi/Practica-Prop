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
	/**
	 * Indica si l'objecte corrent es nou pel que fa al sistema o, si per contra, s'està duent a terme una modificació.
	 * @return True si, i només si, es nou. Altrament retorna false.
	 */
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe.
	 */
	public CampusManager() {
		path = null;
		CampusManager.current = this;
	}
	
	/**
	 * Retorna la instancia corrent de la classe.
	 * @return Instancia de la classe.
	 */
	public static CampusManager getInstance() {
		return CampusManager.current;
	}
	
	/**
	 * Comprova l'estat de les seleccions.
	 * @return True sempre i quan estiguin tot seleccionat correstament.
	 */
	private boolean checkSelection() {
		if(aules.getSelectionModel().getSelectedIndex() == -1 || aules.getSelectionModel().getSelectedItem().isEmpty()) {
			Main.getInstance().showWarning("Acció incorrecte", "Cal seleccionar una aula per poder procedir.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Assigna l'objecte a la pantalla.
	 * @param path Identificador de l'objecte.
	 */
	public static void setPath(String path) {
		CampusManager.getInstance().nom_id.setText(path);
		CampusManager.getInstance().update();
		
		CampusManager.getInstance().autor_id.setText(ControladorPresentacio.getInstance().GetMainCampusData(path));
	}
	
	/**
	 * Retorna el path actual.
	 * @return String no null.
	 */
	public static String getPath() {
		return path;
	}
	
	/**
	 * Actualitza tots els objectes de la pantalla.
	 */
	public void update() {
		path = nom_id.getText();
		title.setText("Campus: ".concat(nom_id.getText()));
		
		this.aules.getItems().clear();
		this.aules.getItems().addAll(ControladorPresentacio.getInstance().getAllAules(path));
		
		Main.getInstance().update();
	}
	
	/**
	 * Acció en cas d'exportar.
	 */
	@FXML
	public void onExportAction() {
		if(checkSelection()) {
			Main.getInstance().newWindows("IOAction_view.fxml", "Exportar objecte", 500, 227);
			IOActionManager.getInstance().setPath("Exportar aula", aules.getSelectionModel().getSelectedItem(), "$HOME");
		}
	}
	
	/**
	 * Acció en cas d'importar.
	 */
	@FXML
	public void onImportarAction() {
		Main.getInstance().newWindows("IOAction_view.fxml", "Importar objecte", 500, 227);
		IOActionManager.getInstance().setPath("Importar aula", "Qualsevol", "$HOME");
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	/**
	 * Acció en cas de voler conservar els canvis fets.
	 */
	@FXML
	public void apply() {
		 //En cas de ser un nou campus:
		if(isNew()) ControladorPresentacio.getInstance().CrearCampus(nom_id.getText());

		if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarCampus(isNew()? nom_id.getText() : path, isNew()? null : nom_id.getText(), autor_id.getText());
		if(!Main.onError(true)) this.update();
	}

	/**
	 * En cas de voler donar d'alta una nova aula.
	 */
	@FXML
	public void onCreateAula() {
		if(isNew()) this.apply();
		Main.getInstance().newWindows("Aula_view.fxml", "Aula", 500, 240);
	}

	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	/**
	 * Acció en cas de clicar sobre una aula.
	 * @param click Proporcionat pel sistema.
	 */
	@FXML
	public void onAulaItemClicked(MouseEvent click) {
		if(click == null || (click.getClickCount() == 2 && this.aules.getSelectionModel().getSelectedIndex() > -1)){
			Main.getInstance().newWindows("Aula_view.fxml", "Aula", 500, 240);
			AulaManager.setPath(this.aules.getSelectionModel().getSelectedItem());
		}
	}

	/**
	 * Acció en cas de modificació.
	 */
	@FXML
	public void onModify() {
		if(checkSelection()) onAulaItemClicked(null);
		this.update();
	}
	
	/**
	 * Acció en cas d'eliminació.
	 */
	@FXML
	public void onDelete() {
		if(checkSelection()) ControladorPresentacio.getInstance().EliminarAula(path, aules.getSelectionModel().getSelectedItem());
		this.update();
	}
}
