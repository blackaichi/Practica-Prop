package presentacio.vistes;

import domini.ControladorDomini;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import presentacio.ControladorPresentacio;
import presentacio.tools.GridPaneManager;

public class GrupManager {

	static private GrupManager current;
	static private String path;
	
	@FXML private TextField nom, places;
	@FXML private ListView<String> subgrups;
	@FXML private GridPane solap_container, aptes_container;
	@FXML private Label title, franja;
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	private boolean paramChecker() {
		try {
			Integer.parseUnsignedInt(nom.getText());
			Integer.parseUnsignedInt(places.getText());
			return true;
		}
		catch(NumberFormatException e) {
			Main.getInstance().showWarning("Parametres incorrectes", "El numero i les places del grup han de ser numeros superiors de 0.");
			return false;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public GrupManager() {
		path = null;
		current = this;
	}
	
	public static GrupManager getInstance() {
		return current;
	}
	
	public static void setPath(String path) {
		GrupManager.getInstance().nom.setText(path);
		GrupManager.getInstance().update();
	}
	
	public void setGradPane() {
		GridPaneManager.getInstance().buildGridPane(aptes_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), 0, 0);
		if(!isNew()) GridPaneManager.getInstance().updateGridPane(aptes_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(path), 0);
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = nom.getText();
		title.setText("Grup: ".concat(nom.getText()));
		
		this.subgrups.getItems().clear();
		this.subgrups.getItems().addAll(ControladorPresentacio.getInstance().getAllSubGrups(PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(path)));
		
		setGradPane();
		AssignaturaManager.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		if(paramChecker()) {
			if(isNew()) ControladorPresentacio.getInstance().CrearGrup(PlaEstudisManager.getPath(),
																	   AssignaturaManager.getPath(),
																	   Integer.parseInt(nom.getText()),
																	   Integer.parseInt(places.getText()));
			
			if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarGrup(PlaEstudisManager.getPath(),
																				   AssignaturaManager.getPath(),
																				   Integer.parseInt(isNew()? nom.getText() : path),
																				   isNew()? 0 : Integer.parseInt(nom.getText()),
																				   isNew()? 0 : Integer.parseInt(places.getText()),
																				   franja.getText().contains("Q")? "MT" : franja.getText().contains("M")? "M" : "T");
			
			if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(),
																				AssignaturaManager.getPath(),
																				Integer.parseInt(isNew()? nom.getText() : path),
																				0, GridPaneManager.getInstance().scannForState(aptes_container, true), true, false);
			
			if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(),
																				AssignaturaManager.getPath(),
																				Integer.parseInt(isNew()? nom.getText() : path),
																				0, GridPaneManager.getInstance().scannForState(aptes_container, false), false, false);
			
			if(!Main.onError(true)) this.update();
		}
	}
	
	@FXML
	public void onCreateSubGrup() {
		if(isNew()) this.apply();
		Main.getInstance().newWindows("Subgrup_view.fxml", "Subgrup", 500, 719);
		SubGrupManager.getInstance().setGradPane();
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onMatiItemClicked() {
		franja.setText("Matí");
	}
	
	@FXML
	public void onTardaItemClicked() {
		franja.setText("Tarda");
	}
	
	@FXML
	public void onAnyItemClicked() {
		franja.setText("Qualsevol");
	}
	
	@FXML
	public void onSubGrupItemClicked(MouseEvent click) {
		if(click.getClickCount() == 2){
			Main.getInstance().newWindows("Subgrup_view.fxml", "Subgrup", 500, 719);
			SubGrupManager.setPath(subgrups.getSelectionModel().getSelectedItem());
		}
	}
}
