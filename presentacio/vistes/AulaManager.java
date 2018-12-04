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
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public AulaManager() {
		path = null;
	}
	
	public static AulaManager getInstance() {
		return AulaManager.current;
	}
	
	public static void setPath(String path) {
		AulaManager.path = path;
	}
	
	public static String getPath() {
		return path;
	}
		
	public void update() {
		path = nom.getText();
		title.setText("Aula: ".concat(nom.getText()));
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		//En cas de ser una nova aula
		if(isNew()) ControladorPresentacio.getInstance().CrearAula(CampusManager.getPath(), nom.getText(), Integer.valueOf(places.getText()));
		
		HashSet<String> equip = new HashSet<String>();
		StringTokenizer token = new StringTokenizer(this.equip.getText(), "; ");
		while(token.hasMoreTokens()) equip.add(token.nextToken());
		
		ControladorPresentacio.getInstance().ModificarAula(CampusManager.getPath(), isNew()? nom.getText() : path, isNew()? null : nom.getText(), Integer.valueOf(places.getText()), equip);	
		update();
	}
}
