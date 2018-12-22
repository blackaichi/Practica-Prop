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
	/**
	 * Indica si l'objecte corrent es nou pel que fa al sistema o, si per contra, s'està duent a terme una modificació.
	 * @return True si, i només si, es nou. Altrament retorna false.
	 */
 	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
 	/**
	 * Comprova que els parametres necessaris per dur a terme una acció estiguin correctament configurats.
	 * @param numberCheck Indica si es vol checkejar l'estat dels numeros.
	 * @return True si i només si tots els parametres estan correctes; false altrament.
	 */
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
	/*
	 * Constructora de la classe.
	 */
	public SubGrupManager() {
		path = null;
		current = this;
	}
	
	/**
	 * Retorna la instancia corrent de la classe.
	 * @return Instancia de la classe.
	 */
	public static SubGrupManager getInstance() {
		return current;
	}
	
	/**
	 * Configura el GradPane de la pantalla.
	 */
	public void setGradPane() {
		GridPaneManager.getInstance().buildGridPane(aptes_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()), 0);
		GridPaneManager.getInstance().buildSolapaments(solap_container, ControladorPresentacio.getInstance().getConjunts(PlaEstudisManager.getPath()), false);
		if(!isNew()) {
			GridPaneManager.getInstance().updateGridPane(aptes_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()), Integer.parseInt(path));
			GridPaneManager.getInstance().updateSolapaments(solap_container, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()), Integer.parseInt(path));
		}
	}
	
	/**
	 * Assigna l'objecte a la pantalla.
	 * @param path Identificador de l'objecte.
	 */
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
		title.setText("Subgrup: ".concat(nom.getText()));
		
		setGradPane();
		GrupManager.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	/**
	 * Acció en cas de voler conservar els canvis fets.
	 */
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
