package presentacio.vistes;

import java.util.*;
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
	
	private boolean panic;
	
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
	
	private boolean checkSelection() {
		if(subgrups.getSelectionModel().getSelectedIndex() == -1 || subgrups.getSelectionModel().getSelectedItem().isEmpty()) {
			Main.getInstance().showWarning("Acció incorrecte", "Cal seleccionar un subgrup per poder procedir.");
			return false;
		}
		
		return true;
	}
	
	private void setMainData() {
		ArrayList<String> data = ControladorPresentacio.getInstance().GetMainGrupData(PlaEstudisManager.getPath(),
				  AssignaturaManager.getPath(),
				  Integer.parseInt(path));

		for(int it = 0; it < data.size(); it++) {
			if(it == 0) GrupManager.getInstance().places.setText(data.get(it));
			else if(it == 1) {
				if(data.get(it).equals("M")) GrupManager.getInstance().onMatiItemClicked();
				else if(data.get(it).equals("T")) GrupManager.getInstance().onTardaItemClicked();
				else GrupManager.getInstance().onAnyItemClicked();
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public GrupManager() {
		path = null;
		current = this;
		panic = false;
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
		GridPaneManager.getInstance().buildSolapaments(solap_container, ControladorPresentacio.getInstance().getConjunts(PlaEstudisManager.getPath()), false);
		if(!isNew()) {
			GridPaneManager.getInstance().updateGridPane(aptes_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(path), 0);
			GridPaneManager.getInstance().updateSolapaments(solap_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(path), 0);
		}
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = nom.getText();
		title.setText("Grup: ".concat(nom.getText()));
		
		this.subgrups.getItems().clear();
		this.subgrups.getItems().addAll(ControladorPresentacio.getInstance().getAllSubGrups(PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(path)));
		this.setMainData();
		
		setGradPane();
		AssignaturaManager.getInstance().update();
	}
	
	public String getFranja() {
		return this.franja.getText();
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
			
			if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(),
																				     AssignaturaManager.getPath(),
																					 Integer.parseInt(isNew()? nom.getText() : path),
																					 0,
																					 GridPaneManager.getInstance().scannForState(aptes_container, true), true, true);
				
			if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(),
																					 AssignaturaManager.getPath(),
																					 Integer.parseInt(isNew()? nom.getText() : path),
																					 0,
																					 GridPaneManager.getInstance().scannForState(aptes_container, false), false, true);
			
			for(Map.Entry<String, HashSet<Integer>> iter : GridPaneManager.getInstance().scannForState(solap_container, true, false).entrySet()) {
				for(int numero : iter.getValue()) {
					ControladorPresentacio.getInstance().SetSolapamentGrup(PlaEstudisManager.getPath(),
																		   AssignaturaManager.getPath(),
																		   Integer.parseInt(isNew()? nom.getText() : path),
																		   iter.getKey(), numero, false);
					}
			}
			
			for(Map.Entry<String, HashSet<Integer>> iter : GridPaneManager.getInstance().scannForState(solap_container, false, false).entrySet()) {
				for(int numero : iter.getValue()) {
					ControladorPresentacio.getInstance().SetSolapamentGrup(PlaEstudisManager.getPath(),
																		   AssignaturaManager.getPath(),
																		   Integer.parseInt(isNew()? nom.getText() : path),
																		   iter.getKey(), numero, true);
				}
			}
			
			if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarGrup(PlaEstudisManager.getPath(),
																					    AssignaturaManager.getPath(),
																					    Integer.parseInt(isNew()? nom.getText() : path),
																					    isNew()? 0 : Integer.parseInt(nom.getText()),
																					    isNew()? 0 : Integer.parseInt(places.getText()),
																					    franja.getText().contains("Q")? "MT" : franja.getText().contains("M")? "M" : "T");
			
			this.panic = Main.onError(false);
			if(!Main.onError(true)) this.update();
		}
	}
	
	@FXML
	public void onCreateSubGrup() {
		if(isNew()) this.apply();
		if(!panic) {
			Main.getInstance().newWindows("Subgrup_view.fxml", "Subgrup", 500, 770);
			SubGrupManager.getInstance().setGradPane();
		}
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
		if(click == null || (click.getClickCount() == 2 && subgrups.getSelectionModel().getSelectedIndex() > -1)){
			Main.getInstance().newWindows("Subgrup_view.fxml", "Subgrup", 500, 770);
			SubGrupManager.setPath(subgrups.getSelectionModel().getSelectedItem());
		}
	}

	@FXML
	public void onModify() {
		if(checkSelection()) onSubGrupItemClicked(null);
		this.update();
	}
	
	@FXML
	public void onDelete() {
		if(checkSelection()) ControladorPresentacio.getInstance().EliminaSubGrup(PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(path), Integer.parseInt(subgrups.getSelectionModel().getSelectedItem()));
		this.update();
	}
}
