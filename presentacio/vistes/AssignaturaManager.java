package presentacio.vistes;

import java.util.*;
import presentacio.ControladorPresentacio;
import presentacio.tools.GridPaneManager;
import utils.Pair;
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
	
	private boolean panic;
	private Pair<String, Boolean> lastSelected;
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	private boolean checkSelection() {
		if(this.lastSelected.first == null) {
			Main.getInstance().showWarning("Acció incorrecte", "Cal seleccionar un grup o una sessió per poder procedir.");
			return false;
		}
		
		return true;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public AssignaturaManager() {
		path = null;
		AssignaturaManager.current = this;
		this.lastSelected = new Pair<String, Boolean>(null, null);
	}
	
	public static AssignaturaManager getInstance() {
		return AssignaturaManager.current;
	}
	
	public void setGradPane() {
		GridPaneManager.getInstance().buildGridPane(aptes_container, PlaEstudisManager.getPath(), null, 0, 0);
		GridPaneManager.getInstance().buildSolapaments(solap_container, ControladorPresentacio.getInstance().getConjunts(PlaEstudisManager.getPath()), true);
		if(!isNew()) {
			GridPaneManager.getInstance().updateGridPane(aptes_container, PlaEstudisManager.getPath(), path, 0, 0);
			GridPaneManager.getInstance().updateSolapaments(solap_container, PlaEstudisManager.getPath(), path, 0, 0);
		}
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
	
	public void onGrupSelected() {
		this.lastSelected.first = grups.getSelectionModel().getSelectedItem();
		this.lastSelected.second = false;
	}
	
	public void onSessioSelected() {
		this.lastSelected.first = sessions.getSelectionModel().getSelectedItem();
		this.lastSelected.second = true;
	}
	
	public boolean isSessioGrup(String sessio) {
		String[] depurat = sessio.split(" ");
		return depurat[0].equals("[G]");
	}
	
	public String getTipusSessio(String sessio) {
		String[] depurat = sessio.split(" ");
		return depurat[1];
	}
	
	public int getDuradaSessio(String sessio) {
		String[] depurat = sessio.split(" ");
		return Integer.parseInt(depurat[3]);
	}
	
	@FXML
	public void onExportAction() {
		if(checkSelection()) {
			Main.getInstance().newWindows("IOAction_view.fxml", "Exportar objecte", 500, 227);
			if(lastSelected.second) {
				if(this.isSessioGrup(lastSelected.first))
					IOActionManager.getInstance().setPath("Exportar sessió de grup", lastSelected.first, "$HOME");
				else IOActionManager.getInstance().setPath("Exportar sessió de subgrup", lastSelected.first, "$HOME");
			}
			else IOActionManager.getInstance().setPath("Exportar grup", lastSelected.first, "$HOME");
		}
	}
	
	@FXML
	public void onImportarAction() {
		Main.getInstance().newWindows("IOAction_view.fxml", "Importar objecte", 500, 227);
		IOActionManager.getInstance().setPath("Importar objecte", "Qualsevol", "$HOME");
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		 //En cas de ser un nou campus:
		if(isNew()) ControladorPresentacio.getInstance().CrearAssignatura(PlaEstudisManager.getPath(), nom.getText());

		if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(), isNew()? nom.getText() : path, 0, 0, GridPaneManager.getInstance().scannForState(aptes_container, true), true, true);
		if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(), isNew()? nom.getText() : path, 0, 0, GridPaneManager.getInstance().scannForState(aptes_container, false), false, true);
		
		for(Map.Entry<String, HashSet<Integer>> iter : GridPaneManager.getInstance().scannForState(solap_container, true, true).entrySet())
			ControladorPresentacio.getInstance().SetSolapamentAssig(PlaEstudisManager.getPath(), isNew()? nom.getText() : path, iter.getKey(), false);
		
		for(Map.Entry<String, HashSet<Integer>> iter : GridPaneManager.getInstance().scannForState(solap_container, false, true).entrySet())
			ControladorPresentacio.getInstance().SetSolapamentAssig(PlaEstudisManager.getPath(), isNew()? nom.getText() : path, iter.getKey(), true);
		
		if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarAssginatura(PlaEstudisManager.getPath(), isNew()? nom.getText() : path, isNew()? null : nom.getText());
		
		this.panic = Main.onError(false);
		if(!Main.onError(true)) this.update();
	}
	
	@FXML
	public void onCreateGrup() {
		if(isNew()) this.apply();
		if(!panic) {
			Main.getInstance().newWindows("Grup_view.fxml", "Grup", 500, 770);
			GrupManager.getInstance().setGradPane();
		}
	}
	
	@FXML
	public void onCreateSessio() {
		if(isNew()) this.apply();
		if(!panic) {
			Main.getInstance().newWindows("Sessio_view.fxml", "Sessio", 500, 719);
			SessioManager.getInstance().setGradPane();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onGrupItemClicked(MouseEvent click) {
		if(click != null && click.getClickCount() == 1) this.onGrupSelected();
		else if(grups.getSelectionModel().getSelectedIndex() > -1){
			Main.getInstance().newWindows("Grup_view.fxml", "Grup", 500, 770);
			GrupManager.setPath(grups.getSelectionModel().getSelectedItem());
		}
	}
	
	@FXML
	public void onSessioItemClicked(MouseEvent click) {
		if(click != null && click.getClickCount() == 1) this.onSessioSelected();
		else if(sessions.getSelectionModel().getSelectedIndex() > -1){
			Main.getInstance().newWindows("Sessio_view.fxml", "Sessio", 500, 719);
			SessioManager.setPath(sessions.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	public void onModify() {
		if(checkSelection()) {
			if(lastSelected.second) onSessioItemClicked(null);
			else onGrupItemClicked(null);
		}
	}
	
	@FXML
	public void onDelete() {
		if(checkSelection()) {
			if(!lastSelected.second) ControladorPresentacio.getInstance().EliminarGrup(PlaEstudisManager.getPath(), path, Integer.parseInt(lastSelected.first));
			else if(isSessioGrup(sessions.getSelectionModel().getSelectedItem()))
				ControladorPresentacio.getInstance().EliminaSessioGrup(PlaEstudisManager.getPath(), path, getTipusSessio(lastSelected.first), getDuradaSessio(lastSelected.first));
			else
				ControladorPresentacio.getInstance().EliminaSessioSubGrup(PlaEstudisManager.getPath(), path, getTipusSessio(lastSelected.first), getDuradaSessio(lastSelected.first));
				
			this.update();
		}
	}
}
