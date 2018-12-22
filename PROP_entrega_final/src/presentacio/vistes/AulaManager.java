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
	/**
	 * Indica si l'objecte corrent es nou pel que fa al sistema o, si per contra, s'està duent a terme una modificació.
	 * @return True si, i només si, es nou. Altrament retorna false.
	 */
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	/**
	 * Comprova que els parametres necessaris per dur a terme una acció estiguin correctament configurats.
	 * @return True si i només si tots els parametres estan correctes; false altrament.
	 */
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
	
	/**
	 * Passa d'un string amb una configuració concreta, al set d'equip.
	 * @return Un set no null.
	 */
	private HashSet<String> getEquipSet(){
		HashSet<String> equip = new HashSet<String>();
		StringTokenizer token = new StringTokenizer(this.equip.getText(), ";");
		while(token.hasMoreTokens()) equip.add(token.nextToken());
		
		return equip;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	/**
	 * Constructora de la classe AulaManager.
	 */
	public AulaManager() {
		path = null;
		current = this;
	}
	
	/**
	 * Retorna la instancia corrent de la classe.
	 * @return Instancia de la classe.
	 */
	public static AulaManager getInstance() {
		return current;
	}
	
	/**
	 * Assigna l'objecte a la pantalla.
	 * @param path Identificador de l'objecte.
	 */
	public static void setPath(String path) {
		AulaManager.getInstance().nom.setText(path);
		AulaManager.getInstance().update();
		
		ArrayList<String> data = ControladorPresentacio.getInstance().GetMainAulaData(CampusManager.getPath(), path);
		for(int it = 0; it < data.size(); it++) {
			if(it == 0) AulaManager.getInstance().places.setText(data.get(it));
			else AulaManager.getInstance().equip.setText(AulaManager.getInstance().equip.getText().concat(data.get(it)).concat(";"));
		}
	}
	
	/**
	 * Retorna el path actual.
	 * @return String no null.
	 */
	public static String getPath() {
		return path;
	}
		
	/**
	 * Acutalitza tots els objectes de la pantalla.
	 */
	public void update() {
		path = nom.getText();
		title.setText("Aula: ".concat(nom.getText()));
		
		CampusManager.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	/**
	 * Acció en cas de voler conservar els canvis fets.
	 */
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
