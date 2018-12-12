package presentacio.vistes;

import java.util.HashSet;
import java.util.StringTokenizer;

import domini.ControladorDomini;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import presentacio.ControladorPresentacio;
import presentacio.tools.GridPaneManager;

public class SessioManager {

	static private SessioManager current;
	static private String path;
	
	@FXML private TextField tipus, durada, nsessions, equip;
	@FXML private GridPane assignats;
	@FXML private Label title, conjunt;
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private static boolean isNew() {
		return path == null || path.isEmpty();
	}
	
	private boolean paramChecker() {
		try {
			Integer.parseUnsignedInt(durada.getText());
			Integer.parseUnsignedInt(nsessions.getText());
			return true;
		}
		catch(NumberFormatException e) {
			Main.getInstance().showWarning("Parametres incorrectes", "La durada i les sessions per setmana han de ser un enter superior a 0.");
			return false;
		}
	}
	
	private String getType() {
		String[] values = path.split("::");
		return values[0];
	}
	
	private int getHores() {
		String[] values = path.split("::");
		return Integer.parseInt(values[1]);
	}
	
	private HashSet<String> getEquipSet(){
		HashSet<String> equip = new HashSet<String>();
		StringTokenizer token = new StringTokenizer(this.equip.getText(), ";");
		while(token.hasMoreTokens()) equip.add(token.nextToken());
		
		return equip;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public SessioManager() {
		path = null;
		current = this;
	}
	
	public static SessioManager getInstance() {
		return current;
	}
	
	public static void setPath(String path) {
		SessioManager.getInstance().tipus.setText(path);
		SessioManager.getInstance().update();
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = tipus.getText().concat("::").concat(durada.getText());
		title.setText("Sessio: ".concat(tipus.getText()));
		
		AssignaturaManager.getInstance().update();
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////  FXML ///////////////////////////////////////
	
	@FXML
	public void apply() {
		if(paramChecker()) {
			if(isNew()) {
				if(conjunt.getText().contains("S")) ControladorPresentacio.getInstance().CrearSessioSubGrup(PlaEstudisManager.getPath(),
																											AssignaturaManager.getPath(),
																											tipus.getText(),
																											Integer.parseInt(durada.getText()));									
				else ControladorPresentacio.getInstance().CrearSessioGrup(PlaEstudisManager.getPath(),
																		  AssignaturaManager.getPath(),
																		  tipus.getText(),
																		  Integer.parseInt(durada.getText()));
			}
		
			if(!Main.onError(false)) {
				if(conjunt.getText().contains("S")) ControladorPresentacio.getInstance().ModificarSessioSubGrup(PlaEstudisManager.getPath(),
																												AssignaturaManager.getPath(),
																												isNew()? tipus.getText() : getType(),
																												isNew()? Integer.parseInt(durada.getText()) : getHores(),
																												tipus.getText(),
																												Integer.parseInt(durada.getText()),
																												Integer.parseInt(nsessions.getText()),
																												getEquipSet());
				else ControladorPresentacio.getInstance().ModificarSessioGrup(PlaEstudisManager.getPath(),
																			  AssignaturaManager.getPath(),
																		      isNew()? tipus.getText() : getType(),
																			  isNew()? Integer.parseInt(durada.getText()) : getHores(),
																			  tipus.getText(),
																			  Integer.parseInt(durada.getText()),
																			  Integer.parseInt(nsessions.getText()),
																			  getEquipSet());
			}
			
			if(!Main.onError(true)) this.update();
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onGrupItemClicked() {
		conjunt.setText("Grup");
	}
	
	@FXML
	public void onSubGrupItemClicked() {
		conjunt.setText("Subgrup");
	}
}
