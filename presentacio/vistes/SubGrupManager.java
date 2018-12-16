package presentacio.vistes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import domini.ControladorDomini;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import presentacio.ControladorPresentacio;
import presentacio.tools.GridPaneManager;

public class SubGrupManager {

	static private SubGrupManager current;
	static private String path;
	
	@FXML private TextField nom, places;
	@FXML private GridPane solap_container, aptes_container;
	@FXML private Label title, franja;
	@FXML private CheckBox force;
	
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
			Main.getInstance().showWarning("Parametres incorrectes", "El numero i les places del subgrup han de ser numeros superiors de 0.");
			return false;
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public SubGrupManager() {
		path = null;
		current = this;
	}
	
	public static SubGrupManager getInstance() {
		return current;
	}
	
	public void setGradPane() {
		GridPaneManager.getInstance().buildGridPane(aptes_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()), 0);
		GridPaneManager.getInstance().buildSolapaments(solap_container, ControladorPresentacio.getInstance().getConjunts(PlaEstudisManager.getPath()), false);
		if(!isNew()) {
			GridPaneManager.getInstance().updateGridPane(aptes_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()), Integer.parseInt(path));
			GridPaneManager.getInstance().updateSolapaments(solap_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()), Integer.parseInt(path));
		}
	}
	
	public static void setPath(String path) {
		SubGrupManager.getInstance().nom.setText(path);
		SubGrupManager.getInstance().update();
		
		ArrayList<String> data = ControladorPresentacio.getInstance().GetMainSubGrupData(PlaEstudisManager.getPath(),
																					  	 AssignaturaManager.getPath(),
																					  	 Integer.parseInt(GrupManager.getPath()),
																					  	 Integer.parseInt(path));
		
		SubGrupManager.getInstance().places.setText(data.get(0));
		SubGrupManager.getInstance().franja.setText(GrupManager.getInstance().getFranja());
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = nom.getText();
		title.setText("Subgrup: ".concat(nom.getText()));
		
		setGradPane();
		GrupManager.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		if(paramChecker()) {
			if(isNew()) ControladorPresentacio.getInstance().CrearSubGrup(PlaEstudisManager.getPath(),
																		  AssignaturaManager.getPath(),
																		  Integer.parseInt(GrupManager.getPath()),
																		  Integer.parseInt(nom.getText()),
																		  Integer.parseInt(places.getText()),
																		  force.isSelected());
			
			if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(),
																					 AssignaturaManager.getPath(),
																					 Integer.parseInt(GrupManager.getPath()),
																					 Integer.parseInt(isNew()? nom.getText() : path),
																					 GridPaneManager.getInstance().scannForState(aptes_container, true), true, true);

			if(!Main.onError(false)) ControladorPresentacio.getInstance().HoresAptes(PlaEstudisManager.getPath(),
																					 AssignaturaManager.getPath(),
																					 Integer.parseInt(GrupManager.getPath()),
																					 Integer.parseInt(isNew()? nom.getText() : path),
																					 GridPaneManager.getInstance().scannForState(aptes_container, false), false, true);
			
			for(Map.Entry<String, HashSet<Integer>> iter : GridPaneManager.getInstance().scannForState(solap_container, true, false).entrySet()) {
				for(int numero : iter.getValue()) {
					ControladorPresentacio.getInstance().SetSolapamentSubGrup(PlaEstudisManager.getPath(),
																		   AssignaturaManager.getPath(),
																		   Integer.parseInt(GrupManager.getPath()),
																		   Integer.parseInt(isNew()? nom.getText() : path),
																		   iter.getKey(), numero, false);
					}
			}
			
			for(Map.Entry<String, HashSet<Integer>> iter : GridPaneManager.getInstance().scannForState(solap_container, false, false).entrySet()) {
				for(int numero : iter.getValue()) {
					ControladorPresentacio.getInstance().SetSolapamentSubGrup(PlaEstudisManager.getPath(),
																			  AssignaturaManager.getPath(),
																			  Integer.parseInt(GrupManager.getPath()),
																			  Integer.parseInt(isNew()? nom.getText() : path),
																			  iter.getKey(), numero, true);
				}
			}
			
			if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarSubGrup(PlaEstudisManager.getPath(),
																		 				   AssignaturaManager.getPath(),
																						   Integer.parseInt(GrupManager.getPath()),
																						   Integer.parseInt(isNew()? nom.getText() : path),
																						   isNew()? 0 : Integer.parseInt(nom.getText()),
																						   isNew()? 0 : Integer.parseInt(places.getText()),
																						   force.isSelected());
			
			if(!Main.onError(true)) update();
		}
	}
}
