package presentacio.vistes;

import utils.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentacio.ControladorPresentacio;

/**
 * 
 * @author hector.morales.carnice@est.fib.upc.edu
 *
 */
public class IOActionManager {

	static private IOActionManager current;
	
	@FXML private Label title, objecte;
	@FXML private TextField path, file;
	
	/**
	 * Retorna la instancia corrent de la classe.
	 * @return Instancia de la classe.
	 */
	public static IOActionManager getInstance() {
		return current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	/**
	 * Indica si un fitxer existeix o no.
	 * @param completePath
	 * @return True si, i només si, el fitxer/carpeta existeix.
	 */
	private boolean checkIfExists(String completePath) {
		int index = completePath.lastIndexOf('/');
		return ControladorPresentacio.getInstance().fitxersAt(completePath.substring(0, index)).contains(completePath.substring(index+1, completePath.length()));
	}
	
	/**
	 * Comprova que els parametres necessaris per dur a terme una acció estiguin correctament configurats.
	 * @return True si i només si tots els parametres estan correctes; false altrament.
	 */
	private boolean paramChecker() {
		try {
			if(!path.getText().isEmpty() && !file.getText().isEmpty()) return true;
			else {
				Main.getInstance().showWarning("Parametres incorrectes", "Asseguret d'introduir una direcció i nom/s de fitxer/s.");
				return false;
			}
		}
		catch(Exception e) {
			Main.getInstance().showWarning("Parametres incorrectes", "Asseguret d'introduir una direcció i nom/s de fitxer/s.");
			return false;
		}
	}
	
	/**
	 * Proporciona la extensió donat el nom d'un arxiu.
	 * @param path Nom / direcció de l'arxiu.
	 * @return Un string amb valor diferent a null.
	 */
	private String getExtension(String path) {
		StringTokenizer token = new StringTokenizer(path, ".");
		boolean hasExtension = false;
		
		do {
			String depurat = token.nextToken();
			if(!token.hasMoreTokens()) {
				return hasExtension? depurat : "horari";
			}
			
			hasExtension = true;
		}while(token.hasMoreTokens());
		
		return null;
	}
	
	/**
	 * Proporciona l'acció que s'està duent a terme.
	 * @return Un pair identificador de l'acció.
	 */
	private Pair<String, Boolean> getAction() {
		String[] depurat = this.title.getText().split(" ");
		
		Pair<String, Boolean> action = new Pair<String, Boolean>("", null);
		action.second = depurat[0].equals("Exportar");
		action.first = depurat[1];
		for(int iter = 2; iter < depurat.length; iter++)
			action.first = action.first.concat(" ").concat(depurat[iter]);
		
		return action;
	}
	
	/**
	 * Acció en cas de voler exportar.
	 */
	private void onExport() {
		String completePath = path.getText();
		completePath = completePath.replace("$HOME", "~");
		if(completePath.charAt(completePath.length()-1) != '/') completePath = completePath.concat("/");
		completePath = completePath.concat(file.getText().replaceAll(" ", "_"));
		
		boolean onAction = true;
		switch(getAction().first) {
			case "horari":
				ControladorPresentacio.getInstance().exportaHorari(completePath, Main.getInstance().getSelection().first, Main.getInstance().getSelection().second, Main.getInstance().getIteration());
				break;
		
			case "pla d'estudis":
				completePath = completePath.concat(".plaest");
				ControladorPresentacio.getInstance().exportaPlaEstudis(completePath, objecte.getText());
				break;
				
			case "campus":
				completePath = completePath.concat(".campus");
				ControladorPresentacio.getInstance().exportaCampus(completePath, objecte.getText());
				break;
				
			case "aula":
				completePath = completePath.concat(".aula");
				ControladorPresentacio.getInstance().exportaAula(completePath, CampusManager.getPath(), objecte.getText());
				break;
				
			case "assignatura":
				completePath = completePath.concat(".assig");
				ControladorPresentacio.getInstance().exportaAssignatura(completePath, PlaEstudisManager.getPath(), objecte.getText());
				break;
				
			case "grup":
				completePath = completePath.concat(".grup");
				ControladorPresentacio.getInstance().exportaGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(objecte.getText()));
				break;
				
			case "subgrup":
				completePath = completePath.concat(".subgrup");
				ControladorPresentacio.getInstance().exportaSubGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()), Integer.parseInt(objecte.getText()));
				break;
				
			case "sessió de grup":
				completePath = completePath.concat(".sessg");
				ControladorPresentacio.getInstance().exportaSessioGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), AssignaturaManager.getInstance().getTipusSessio(objecte.getText()), AssignaturaManager.getInstance().getDuradaSessio(objecte.getText()));
				break;
				
			case "sessió de subgrup":
				completePath = completePath.concat(".sesssubg");
				ControladorPresentacio.getInstance().exportaSessioSubGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), AssignaturaManager.getInstance().getTipusSessio(objecte.getText()), AssignaturaManager.getInstance().getDuradaSessio(objecte.getText()));
				break;
				
			default:
				onAction = false;
				break;
		}
		
		if(onAction) Main.getInstance().showWarning("Importació", checkIfExists(completePath)? "L'objecte s'ha exportat correctament." : "Sembla que s'ha produit algun error!");
	}
	
	/**
	 * Acció en cas de voler importar.
	 */
	private void onImport() {
		String completePath = path.getText();
		completePath = completePath.replace("$HOME", "~");
		if(completePath.charAt(completePath.length()-1) != '/') completePath = completePath.concat("/");
		
		String[] fitxers = file.getText().split(";");
		for(int iter = 0; iter < fitxers.length; iter++)
			if(!ControladorPresentacio.getInstance().fitxersAt(completePath).contains(fitxers[iter].split(" ")[0]))
				Main.getInstance().showWarning("Fitxer inexistent", "El fitxer amb nom ".concat(fitxers[iter].split(" ")[0]).concat(" no s'ubica al path ").concat(completePath));
			else{
				String newcompletePath = completePath.concat(fitxers[iter].split(" ")[0]);
			
				switch(getExtension(file.getText())) {
					case "horari":
						ControladorPresentacio.getInstance().importaHorari(newcompletePath);
						Main.getInstance().update();
						break;
				
					case "plaest":
						ControladorPresentacio.getInstance().importaPlaEstudis(newcompletePath);
						Main.getInstance().update();
						break;
						
					case "campus":
						ControladorPresentacio.getInstance().importaCampus(newcompletePath);
						Main.getInstance().update();
						break;
						
					case "aula":
						ControladorPresentacio.getInstance().importaAula(newcompletePath, CampusManager.getPath());
						CampusManager.getInstance().update();
						break;
						
					case "assig":
						ControladorPresentacio.getInstance().importaAssignatura(newcompletePath, PlaEstudisManager.getPath());
						PlaEstudisManager.getInstance().update();
						break;
						
					case "grup":
						ControladorPresentacio.getInstance().importaGrup(newcompletePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath());
						AssignaturaManager.getInstance().update();
						break;
						
					case "subgrup":
						ControladorPresentacio.getInstance().importaSubGrup(newcompletePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()));
						GrupManager.getInstance().update();
						break;
						
					case "sessg":
						ControladorPresentacio.getInstance().importaSessioGrup(newcompletePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath());
						AssignaturaManager.getInstance().update();
						break;
						
					case "sesssubg":
						ControladorPresentacio.getInstance().importaSessioSubGrup(newcompletePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath());
						AssignaturaManager.getInstance().update();
						break;
						
					default: break;
				}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Contructora de la classe.
	 */
	public IOActionManager() {
		IOActionManager.current = this;
	}
	
	/**
	 * Assigna tota la informació necessaria per a poder procedir amb l'acció.
	 * @param title Titol de la pantalla.
	 * @param objecte Nom de l'objecte a processar.
	 * @param defaultPath Direcció d'accés per defecte.
	 */
	public void setPath(String title, String objecte, String defaultPath) {
		this.title.setText(title);
		if(!objecte.isEmpty()) {
			if(getAction().second) file.setText(objecte);
			this.objecte.setText(objecte);
		}
		
		if(defaultPath != null && !defaultPath.isEmpty())
			this.path.setText(defaultPath);
		
		if(!getAction().second) file.setPromptText("Nom dels fitxers (entre ';') o carpeta de l'horari a importar.");
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	/**
	 * Acció en cas de voler conservar els canvis fets.
	 */
	@FXML
	public void apply() {
		if(paramChecker()) {
			if(getAction().second) this.onExport();
			else this.onImport();
		}
	}
}
