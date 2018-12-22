package presentacio.vistes;

import presentacio.ControladorPresentacio;
import presentacio.tools.GridPaneManager;

import utils.Pair;
import java.util.*;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.awt.Event;
import javafx.fxml.*;
import javafx.util.*;
import javafx.event.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	static private Main current;
	static private boolean errorOcurred;
	
	private Pair<String, Boolean> lastSelected;
	
	@FXML private GridPane horari_container;
	@FXML private ListView<String> plansEstudis, campus;
	
	@FXML private TextField searcher_pl, searcher_c, nhoraris;
	@FXML private Label selected_pl, selected_c, quants_horaris;
	
	@FXML private CheckBox purge, D_LECTIU, H_LECTIU, ASSIG_SOLAP, ASSIG_HAPTES, G_SOLAP, G_HAPTES, G_FRANJA, S_ALIGN, S_NSESSIONS;
	@FXML private Button next, previous;
	
	/**
	 * Inicialitza la interficie.
	 * @param args Arguments proposats pel sistema.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Retorna la instancia corrent del controlador de la pantalla principal.
	 * @return Accés a la instancia corrent del Main
	 */
	public static Main getInstance() {
		return Main.current;
	}
	
	/**
	 * Informa de l'ocurrencia d'algun error que ha requerit de l'obertura
	 * d'una finestra de diagnostic concreta.
	 * @param restore Indica si es vol, despres de consultar l'estat, cancelar l'error.
	 * @return True si i només si s'ha produit algun error; altrament retorna false.
	 */
	public static boolean onError(boolean restore) {
		if(errorOcurred) {
			if(restore) errorOcurred = false;
			return true;
		}
		else return false;
	}
	
	/**
	 * Inicialitza la pantalla principal.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Main_view.fxml"));
		
		primaryStage.setTitle("Generador d'horaris");
		primaryStage.setScene(new Scene(root, 1080, 790));
		primaryStage.show();
	}
	
	/**
	 * Permet l'obertura d'una nova pantalla concreta.
	 * @param fxml Nom de l'arxiu on es troba el codi font del diseny de la pantalla.
	 * @param title Titol desitjat per la finestra.
	 * @param x Tamany horitzontal de la finestra.
	 * @param y Tamany vertical de la finestra.
	 */
	public void newWindows(String fxml, String title, int x, int y) {
		try {
			Stage stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource(fxml));
			
			stage.setTitle(title);
			stage.setScene(new Scene(root, x, y));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
		
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	/**
	 * Comprova que els parametres necessaris per dur a terme una acció estiguin correctament configurats.
	 * @param numberCheck Indica si es vol checkejar l'estat dels numeros.
	 * @return True si i només si tots els parametres estan correctes; false altrament.
	 */
	private boolean paramChecker(boolean numberCheck){
		if(selected_pl.getText().isEmpty() || selected_c.getText().isEmpty()) {
			this.showWarning("Falta informació", "Cal seleccionar una parella de PlaEstudis i Campus.");
			return false;
		}
		else if(numberCheck) try {
			Integer.parseUnsignedInt(nhoraris.getText());
		}
		catch(NumberFormatException e) {
			Main.getInstance().showWarning("Format incorrecte", "La quantitat d'horaris a generar no està definida en el format correcte.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Comprova si hi ha tants atributs seleccionats com siguin necessaris.
	 * @return True si està tot correcte, false altrament.
	 */
	private boolean checkSelection() {
		if(this.lastSelected.first == null) {
			this.showWarning("Acció incorrecte", "Cal seleccionar un pla d'estudis o un campus per poder procedir.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Configura l'estat dels botons de navegació entre horaris.
	 */
	private void setButtonsState() {
		int nhoraris = ControladorPresentacio.getInstance().getNHoraris(selected_pl.getText(), selected_c.getText());
		quants_horaris.setText("Horari ".concat(nhoraris <= 0? "0" : String.valueOf(getIteration())).concat(" de ").concat(String.valueOf(nhoraris)));
		if(this.getIteration() > this.getNHoraris()) quants_horaris.setText("Horari ".concat(String.valueOf(getNHoraris())).concat(" de ").concat(String.valueOf(getNHoraris())));
		next.setDisable(this.getIteration() >= this.getNHoraris());
		previous.setDisable(this.getIteration() <= 1);
	}
	
	/**
	 * Configura l'estat de l'horari segons toqui.
	 */
	private void updateBySelection() {
		if(selected_pl.getText() != null && !selected_pl.getText().isEmpty() &&
		   selected_c.getText() != null && !selected_c.getText().isEmpty()) {
			
			GridPaneManager.getInstance().buildHorari(horari_container, selected_pl.getText(), selected_c.getText(), getIteration());
			if(this.getNHoraris() > 0) GridPaneManager.getInstance().updateHorari(horari_container, selected_pl.getText(), selected_c.getText(), getIteration());
			this.setButtonsState();
		}
	}
	
	/**
	 * Carrega la configuració de l'horari que toqui i en genera els candidats.
	 * @param nhoraris Total d'horaris a generar.
	 * @param purge Indica si es volen esborrar els horaris antics.
	 */
	private void loadSelected(int nhoraris, boolean purge) {
		if(plansEstudis.getSelectionModel().getSelectedIndex() > -1 && !selected_pl.getText().isEmpty() &&
		   campus.getSelectionModel().getSelectedIndex() > -1 && !selected_c.getText().isEmpty()) {
			int result = ControladorPresentacio.getInstance().generarHorari(selected_pl.getText(),
																			selected_c.getText(),
																			nhoraris,
																			computeFlags(),
																			purge);

			if(result > 0) quants_horaris.setText("Horari 1 de ".concat(String.valueOf(result)));
			this.updateBySelection();
		}
		else {
			quants_horaris.setText("Horari 0 de 0");
			GridPaneManager.getInstance().reset(horari_container, false);
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe Main
	 */
	public Main() {
		Main.current = this;
		Main.errorOcurred = false;
		
		lastSelected = new Pair<String, Boolean>(null, null);
	}
		
	/**
	 * Actualitza tots els components de la pantalla princiapal.
	 */
	public void update() {
		this.onSearchCampus();
		this.onSearchPlaEstudis();
		
		this.updateBySelection();
	}
		
	/**
	 * Retorna la iteració corrent.
	 * @return Un enter igual o superior a 0.
	 */
	public int getIteration() {
		String[] depurat = quants_horaris.getText().split(" ");
		return Integer.parseInt(depurat[1]);
	}
	
	/**
	 * Retorna el total d'horaris candidats de la selecció actual.
	 * @return Un enter igual o superior a 0.
	 */
	public int getNHoraris() {
		String[] depurat = quants_horaris.getText().split(" ");
		return Integer.parseInt(depurat[3]);
	}
	
	/**
	 * Genera el set de flags necessaris per a la generació dels horaris.
	 * @return El set de flags seleccionats.
	 */
	public HashSet<String> computeFlags(){
		HashSet<String> flags = new HashSet<>();
		if(D_LECTIU.isSelected()) flags.add("D_LECTIU");
		if(H_LECTIU.isSelected()) flags.add("H_LECTIU");
		if(ASSIG_SOLAP.isSelected()) flags.add("ASSIG_SOLAP");
		if(ASSIG_HAPTES.isSelected()) flags.add("ASSIG_HAPTES");
		if(G_SOLAP.isSelected()) flags.add("G_SOLAP");
		if(G_HAPTES.isSelected()) flags.add("G_HAPTES");
		if(G_FRANJA.isSelected()) flags.add("G_FRANJA");
		if(S_ALIGN.isSelected()) flags.add("S_ALIGN");
		if(S_NSESSIONS.isSelected()) flags.add("S_NSESSIONS");
		
		return flags;
	}
	
	/**
	 * Retorna el conjunt pla d'estudis i campus seleecionats.
	 * @return Un pair amb contingut o sense.
	 */
	public Pair<String, String> getSelection(){
		Pair<String, String> selection = new Pair<String, String>();
		selection.first = selected_pl.getText();
		selection.second = selected_c.getText();
		
		return selection;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	/**
	 * Acció en cas de seleccionar un campus.
	 */
	public void onCampusSelected() {
		this.selected_c.setText(campus.getSelectionModel().getSelectedItem());
		
		this.lastSelected.first = campus.getSelectionModel().getSelectedItem();
		this.lastSelected.second = true;
		
		this.updateBySelection();
	}
	
	/**
	 * Acció en cas de seleccionar un pla d'estudis.
	 */
	public void onPlaEstudisSelected() {
		this.selected_pl.setText(plansEstudis.getSelectionModel().getSelectedItem());
		
		this.lastSelected.first = plansEstudis.getSelectionModel().getSelectedItem();
		this.lastSelected.second = false;
		
		this.updateBySelection();
	}
	
	/**
	 * Genera un tipus de pantalla especial per errors. 
	 * @param title Titol de la finestra.
	 * @param message Missatge a mostrar.
	 */
	public void showWarning(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		
		alert.show();
		Main.errorOcurred = true;
	}
	
	/**
	 * Acció en cas de donar d'alta un campus.
	 */
	@FXML
	public void onCreateCampus() {
		this.newWindows("Campus_view.fxml", "Campus", 400, 720);
	}
	
	/**
	 * Acció en cas de donar d'alta un pla d'estudis.
	 */
	@FXML
	public void onCreatePlaEstudis() {
		this.newWindows("PlaEstudis_view.fxml", "Pla d'estudis", 1050, 733);
		PlaEstudisManager.getInstance().setGradPane();
	}
	
	/**
	 * Acció en cas de voler mostrar un horari anterior a l'actual.
	 */
	@FXML
	public void showPreviousHorari() {
		if(paramChecker(true)) {
			quants_horaris.setText("Horari ".concat(String.valueOf(getIteration()-1)).concat(" de ").concat(String.valueOf(getNHoraris())));
			GridPaneManager.getInstance().updateHorari(horari_container, selected_pl.getText(), selected_c.getText(), getIteration());
			this.setButtonsState();
		}
	}

	/**
	 * Acció en cas de voler mostrar un horari posterior a l'actual.
	 */
	@FXML
	public void showNextHorari() {
		if(paramChecker(true)) {
			quants_horaris.setText("Horari ".concat(String.valueOf(getIteration()+1)).concat(" de ").concat(String.valueOf(getNHoraris())));
			GridPaneManager.getInstance().updateHorari(horari_container, selected_pl.getText(), selected_c.getText(), getIteration());
			this.setButtonsState();
		}
	}
	
	/**
	 * Acció en cas de voler generar un horari.
	 */
	@FXML
	public void onGenerarHorari(){
		if(paramChecker(true)) this.loadSelected(Integer.parseInt(nhoraris.getText()), purge.isSelected());
	}
	
	/**
	 * Acció en cas de voler exportar un horari.
	 */
	@FXML
	public void onExportarHorari() {
		if(paramChecker(false)) {
			Main.getInstance().newWindows("IOAction_view.fxml", "Exportar objecte", 500, 227);
			IOActionManager.getInstance().setPath("Exportar horari", "Seleccionat", "$HOME");
		}
	}
	
	/**
	 * En cas de voler exportar.
	 */
	@FXML
	public void onExportAction() {
		if(checkSelection()) {
			Main.getInstance().newWindows("IOAction_view.fxml", "Exportar objecte", 500, 227);
			if(lastSelected.second) IOActionManager.getInstance().setPath("Exportar campus", lastSelected.first, "$HOME");
			else IOActionManager.getInstance().setPath("Exportar pla d'estudis", lastSelected.first, "$HOME");
		}
	}
	
	/**
	 * En cas de voler importar.
	 */
	@FXML
	public void onImportarAction() {
		Main.getInstance().newWindows("IOAction_view.fxml", "Importar objecte", 500, 227);
		IOActionManager.getInstance().setPath("Importar objecte", "Qualsevol", "$HOME");
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON TEXT ACTIONS ////////////////////////////////////
	/**
	 * En cas de voler cercar un pla d'estudis concret.
	 */
	@FXML
	public void onSearchPlaEstudis() {
		HashSet<String> total = ControladorPresentacio.getInstance().getAllPlaEstudis();
		if(searcher_pl.getText() != null && !searcher_pl.getText().isEmpty())
			total.removeIf(item -> !item.contains(searcher_pl.getText()));
		
		this.plansEstudis.getItems().clear();
		this.plansEstudis.getItems().addAll(total);
	}
	
	/**
	 * En cas de voler cercar un campus concret.
	 */
	@FXML
	public void onSearchCampus() {
		HashSet<String> total = ControladorPresentacio.getInstance().getAllCampus();
		if(searcher_c.getText() != null && !searcher_c.getText().isEmpty())
			total.removeIf(item -> !item.contains(searcher_c.getText()));
		
		this.campus.getItems().clear();
		this.campus.getItems().addAll(total);
	}
		
	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	/**
	 * Acció en cas de clicar sobre un campus.
	 * @param click Aportat pel sistema.
	 */
	@FXML
	public void onCampusItemClicked(MouseEvent click) {
		if(click != null && click.getClickCount() == 1) this.onCampusSelected();
		else if(campus.getSelectionModel().getSelectedIndex() > -1) {
			this.newWindows("Campus_view.fxml", "Campus", 400, 720);
			CampusManager.setPath(campus.getSelectionModel().getSelectedItem());
		}
	}
	
	/**
	 * Acció en cas de clicar sobre un pla d'estudis.
	 * @param click Aportat pel sistema.
	 */
	@FXML
	public void onPlaEstudisItemClicked(MouseEvent click) {
		if(click != null && click.getClickCount() == 1) this.onPlaEstudisSelected();
		else if(plansEstudis.getSelectionModel().getSelectedIndex() > -1){
			this.newWindows("PlaEstudis_view.fxml", "Pla d'estudis", 1050, 733);
			PlaEstudisManager.setPath(plansEstudis.getSelectionModel().getSelectedItem());
		}
	}

	/**
	 * Acció en cas de clicar sobre una hora apte de l'horari corrent.
	 * @param source Aportat pel sistema.
	 */
	public void onHorariButtonPressed(Node source) {
		Pair<String, Integer> select = GridPaneManager.getInstance().scannButtonPressed(horari_container, source);
		if(!select.isNull()) {
			this.newWindows("Segments_view.fxml", "Sessions", 450, 720);
			SegmentsManager.getInstance().setPath(selected_pl.getText(), selected_c.getText(), select.first, String.valueOf(select.second), getIteration());
		}
	}
	
	/**
	 * En cas de voler dur a terme una modificació.
	 */
	@FXML
	public void onModify() {
		if(checkSelection()) {
			if(lastSelected.second) onCampusItemClicked(null);
			else onPlaEstudisItemClicked(null);
		}
	}
	
	/**
	 * En cas de voler dur a terme una eliminació.
	 */
	@FXML
	public void onDelete() {
		if(checkSelection()) {
			if(lastSelected.second) {
				ControladorPresentacio.getInstance().EliminarCampus(campus.getSelectionModel().getSelectedItem());
				this.selected_c.setText("");
			}
			else {
				ControladorPresentacio.getInstance().EliminaPlaEstudis(plansEstudis.getSelectionModel().getSelectedItem());
				this.selected_pl.setText("");
			}
			this.update();
		}
	}
	
	/**
	 * En cas de voler eliminar un horari.
	 */
	@FXML
	public void onDeleteHorari() {
		if(getIteration() == 0) showWarning("Acció impossible", "No hi ha cap horari per esborrar.");
		else {
			ControladorPresentacio.getInstance().EliminaHorari(selected_pl.getText(),
															   selected_c.getText(),
															   getIteration());
			this.update();
		}
	}
}
