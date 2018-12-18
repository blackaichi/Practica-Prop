package presentacio.vistes;

import utils.*;
import java.util.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentacio.ControladorPresentacio;

public class IOActionManager {

	static private IOActionManager current;
	
	@FXML private Label title, objecte;
	@FXML private TextField path, file;
	
	public static IOActionManager getInstance() {
		return current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
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
	
	private String getExtension(String path) {
		StringTokenizer token = new StringTokenizer(path, ".");
		do {
			String depurat = token.nextToken();
			if(!token.hasMoreTokens()) return depurat;
		}while(token.hasMoreTokens());
		
		return null;
	}
	
	private Pair<String, Boolean> getAction() {
		String[] depurat = this.title.getText().split(" ");
		
		Pair<String, Boolean> action = new Pair<String, Boolean>("", null);
		action.second = depurat[0].equals("Exportar");
		action.first = depurat[1];
		for(int iter = 2; iter < depurat.length; iter++)
			action.first = action.first.concat(" ").concat(depurat[iter]);
		
		return action;
	}
	
	private void onExport() {
		String completePath = path.getText();
		completePath = completePath.replace("$HOME", "~");
		if(completePath.charAt(completePath.length()-1) != '/') completePath = completePath.concat("/");
		completePath = completePath.concat(file.getText().replaceAll(" ", "_"));
		
		switch(getAction().first) {
			case "horari":
				completePath = completePath.concat(".horari");
				ControladorPresentacio.getInstance().exportaHorari(completePath, Main.getInstance().computeFlags(), Main.getInstance().getSelection().first, Main.getInstance().getSelection().second, Main.getInstance().getIteration());
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
				ControladorPresentacio.getInstance().exportaSessioSubGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), AssignaturaManager.getInstance().getTipusSessio(objecte.getText()), AssignaturaManager.getInstance().getDuradaSessio(objecte.getText()), 0);
				break;
				
			default: break;
		}
	}
	
	private void onImport() {
		String completePath = path.getText();
		completePath = completePath.replace("$HOME", "~");
		if(completePath.charAt(completePath.length()-1) != '/') completePath = completePath.concat("/");
		
		String[] fitxers = file.getText().split(";");
		for(int iter = 0; iter < fitxers.length; iter++) {
			completePath = completePath.concat(fitxers[iter].split(" ")[0]);
			
			switch(getExtension(file.getText())) {
				case "horari":
					Main.getInstance().update();
					break;
			
				case "plaest":
					ControladorPresentacio.getInstance().importaPlaEstudis(completePath);
					Main.getInstance().update();
					break;
					
				case "campus":
					ControladorPresentacio.getInstance().importaCampus(completePath);
					Main.getInstance().update();
					break;
					
				case "aula":
					ControladorPresentacio.getInstance().importaAula(completePath, CampusManager.getPath());
					CampusManager.getInstance().update();
					break;
					
				case "assig":
					ControladorPresentacio.getInstance().importaAssignatura(completePath, PlaEstudisManager.getPath());
					PlaEstudisManager.getInstance().update();
					break;
					
				case "grup":
					ControladorPresentacio.getInstance().importaGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath());
					AssignaturaManager.getInstance().update();
					break;
					
				case "subgrup":
					ControladorPresentacio.getInstance().importaSubGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath(), Integer.parseInt(GrupManager.getPath()));
					GrupManager.getInstance().update();
					break;
					
				case "sessg":
					ControladorPresentacio.getInstance().importaSessioGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath());
					AssignaturaManager.getInstance().update();
					break;
					
				case "sesssubg":
					ControladorPresentacio.getInstance().importaSessioSubGrup(completePath, PlaEstudisManager.getPath(), AssignaturaManager.getPath());
					AssignaturaManager.getInstance().update();
					break;
					
				default: break;
			}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public IOActionManager() {
		IOActionManager.current = this;
	}
	
	public void setPath(String title, String objecte, String defaultPath) {
		this.title.setText(title);
		if(!objecte.isEmpty()) {
			if(getAction().second) file.setText(objecte);
			this.objecte.setText(objecte);
		}
		
		if(defaultPath != null && !defaultPath.isEmpty())
			this.path.setText(defaultPath);
		
		if(!getAction().second) file.setPromptText("'TOTS' o bé el nom dels fitxers a importar (separats per ';')");
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		if(paramChecker()) {
			if(getAction().second) this.onExport();
			else this.onImport();
		}
	}
}
