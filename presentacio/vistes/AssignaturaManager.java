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
	/**
	 * Indica si l'objecte corrent es nou pel que fa al sistema o, si per contra, s'està duent a terme una modificació.
	 * @return True si, i només si, es nou. Altrament retorna false.
	 */
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	/**
	 * Comprova l'estat de les seleccions.
	 * @return True sempre i quan estiguin tot seleccionat correstament.
	 */
	private boolean checkSelection() {
		if(this.lastSelected.first == null) {
			Main.getInstance().showWarning("Acció incorrecte", "Cal seleccionar un grup o una sessió per poder procedir.");
			return false;
		}
		
		return true;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe AssignaturaManager. 
	 */
	public AssignaturaManager() {
		path = null;
		AssignaturaManager.current = this;
		this.lastSelected = new Pair<String, Boolean>(null, null);
	}
	
	/**
	 * Retorna la instancia corrent de la classe.
	 * @return Instancia de la classe.
	 */
	public static AssignaturaManager getInstance() {
		return AssignaturaManager.current;
	}
	
	/**
	 * Configura el GradPane de la pantalla.
	 */
	public void setGradPane() {
		GridPaneManager.getInstance().buildGridPane(aptes_container, PlaEstudisManager.getPath(), null, 0, 0);
		GridPaneManager.getInstance().buildSolapaments(solap_container, ControladorPresentacio.getInstance().getConjunts(PlaEstudisManager.getPath()), true);
		if(!isNew()) {
			GridPaneManager.getInstance().updateGridPane(aptes_container, PlaEstudisManager.getPath(), path, 0, 0);
			GridPaneManager.getInstance().updateSolapaments(solap_container, PlaEstudisManager.getPath(), path, 0, 0);
		}
	}
	
	/**
	 * Assigna l'objecte a la pantalla.
	 * @param path Identificador de l'objecte.
	 */
	public static void setPath(String path) {
		AssignaturaManager.getInstance().nom.setText(path);
		AssignaturaManager.getInstance().update();
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
		path = nom.getText();
		title.setText("Assignatura: ".concat(nom.getText()));
		
		this.grups.getItems().clear();
		this.grups.getItems().addAll(ControladorPresentacio.getInstance().getAllGrups(PlaEstudisManager.getPath(), path));
		
		this.sessions.getItems().clear();
		this.sessions.getItems().addAll(ControladorPresentacio.getInstance().getAllSessions(PlaEstudisManager.getPath(), path));
		
		setGradPane();
		PlaEstudisManager.getInstance().update();
	}
	
	/**
	 * Acció en cas de seleccionar un grup.
	 */
	public void onGrupSelected() {
		this.lastSelected.first = grups.getSelectionModel().getSelectedItem();
		this.lastSelected.second = false;
	}
	
	/**
	 * Acció en cas de seleccionar una sessió.
	 */
	public void onSessioSelected() {
		this.lastSelected.first = sessions.getSelectionModel().getSelectedItem();
		this.lastSelected.second = true;
	}
	
	/**
	 * Inidica si una sessio es de grup o subgrup.
	 * @param sessio Nom de la sessio a comprovar.
	 * @return True si es de grup, false altrament.
	 */
	public boolean isSessioGrup(String sessio) {
		String[] depurat = sessio.split(" ");
		return depurat[0].equals("[G]");
	}
	
	/**
	 * Retorna el tipus de la sessio. 
	 * @param sessio Identificació de la sessio.
	 * @return El tipus de la sessio.
	 */
	public String getTipusSessio(String sessio) {
		String[] depurat = sessio.split(" ");
		return depurat[1];
	}
	
	/**
	 * Retorna la durada d'una sessió.
	 * @param sessio Identificació de la sessio.
	 * @return Un enter igual o superior a 1.
	 */
	public int getDuradaSessio(String sessio) {
		String[] depurat = sessio.split(" ");
		return Integer.parseInt(depurat[3]);
	}
	
	/**
	 * Acció en cas d'exportar.
	 */
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
	
	/**
	 * Acció en cas d'importar.
	 */
	@FXML
	public void onImportarAction() {
		Main.getInstance().newWindows("IOAction_view.fxml", "Importar objecte", 500, 227);
		IOActionManager.getInstance().setPath("Importar objecte", "Qualsevol", "$HOME");
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	/**
	 * Acció en cas de voler conservar els canvis fets.
	 */
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
	
	/**
	 * Acció en cas de voler donar d'alta un nou grup.
	 */
	@FXML
	public void onCreateGrup() {
		if(isNew()) this.apply();
		if(!panic) {
			Main.getInstance().newWindows("Grup_view.fxml", "Grup", 500, 770);
			GrupManager.getInstance().setGradPane();
		}
	}
	
	/**
	 * Acció en cas de voler donar d'alta una nova sessio.
	 */
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
	/**
	 * Acció en cas de clicar sobre un grup.
	 * @param click Proporcionat pel sistema.
	 */
	@FXML
	public void onGrupItemClicked(MouseEvent click) {
		if(click != null && click.getClickCount() == 1) this.onGrupSelected();
		else if(grups.getSelectionModel().getSelectedIndex() > -1){
			Main.getInstance().newWindows("Grup_view.fxml", "Grup", 500, 770);
			GrupManager.setPath(grups.getSelectionModel().getSelectedItem());
		}
	}
	
	/**
	 * Acció en cas de clicar sobre una sessio
	 * @param click Proporcionat pel sistema.
	 */
	@FXML
	public void onSessioItemClicked(MouseEvent click) {
		if(click != null && click.getClickCount() == 1) this.onSessioSelected();
		else if(sessions.getSelectionModel().getSelectedIndex() > -1){
			Main.getInstance().newWindows("Sessio_view.fxml", "Sessio", 500, 719);
			SessioManager.setPath(sessions.getSelectionModel().getSelectedItem());
		}
	}

	/**
	 * Acció en cas de modificació.
	 */
	@FXML
	public void onModify() {
		if(checkSelection()) {
			if(lastSelected.second) onSessioItemClicked(null);
			else onGrupItemClicked(null);
		}
	}
	
	/**
	 * Acció en cas d'eliminació.
	 */
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
