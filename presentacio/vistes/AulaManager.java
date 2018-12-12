package presentacio.vistes;

import domini.ControladorDomini;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import presentacio.ControladorPresentacio;

import java.util.*;

public class AulaManager {

	private static AulaManager current;
	private static String path;
	
	@FXML private TextField nom, places, equip;
	@FXML private Label title;
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	private boolean paramChecker() {
		try {
			Integer.parseUnsignedInt(places.getText());
			return true;
		}
		catch(NumberFormatException e) {
			Main.getInstance().showWarning("Format incorrecte", "Les places no estan definides en el format correcte.");
			return false;
		}
	}
	
	private HashSet<String> getEquipSet(){
		HashSet<String> equip = new HashSet<String>();
		StringTokenizer token = new StringTokenizer(this.equip.getText(), ";");
		while(token.hasMoreTokens()) equip.add(token.nextToken());
		
		return equip;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public AulaManager() {
		path = null;
		current = this;
	}
	
	public static AulaManager getInstance() {
		return current;
	}
	
	public static void setPath(String path) {
		AulaManager.getInstance().nom.setText(path);
		AulaManager.getInstance().update();
		
		ArrayList<String> data = ControladorPresentacio.getInstance().GetMainAulaData(CampusManager.getPath(), path);
		for(int it = 0; it < data.size(); it++) {
			if(it == 0) AulaManager.getInstance().places.setText(data.get(it));
			else AulaManager.getInstance().equip.setText(AulaManager.getInstance().equip.getText().concat(data.get(it)).concat(";"));
		}
	}
	
	public static String getPath() {
		return path;
	}
		
	public void update() {
		path = nom.getText();
		title.setText("Aula: ".concat(nom.getText()));
		
		CampusManager.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		if(paramChecker()) { //Si tots els parametres estan ben escrits:
			//En cas de ser una nova aula
			if(isNew()) ControladorPresentacio.getInstance().CrearAula(CampusManager.getPath(), nom.getText(), Integer.valueOf(places.getText()));
			
			if(!Main.onError(false)) ControladorPresentacio.getInstance().ModificarAula(CampusManager.getPath(), isNew()? nom.getText() : path, isNew()? null : nom.getText(), Integer.valueOf(places.getText()), getEquipSet());	
			if(!Main.onError(true)) update();
		}
	}
}
