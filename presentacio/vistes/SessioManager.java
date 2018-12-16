package presentacio.vistes;

import java.util.*;

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
	@FXML private MenuButton options;
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	private static boolean isNew() {
		if(path == null) return true;
		
		String[] fragment = path.split("::");
		return fragment.length != 2;
	}
	
	private boolean paramChecker() {
		try {
			if(tipus.getText().isEmpty()) return false;
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
	
	private Map<Integer, HashSet<Integer>> getConjunts(){
		return ControladorPresentacio.getInstance().getConjunts(PlaEstudisManager.getPath(), AssignaturaManager.getPath());
	}
	
	private String norma(String sessio) {
		String[] depurat = sessio.split(" ");
		
		String norma = depurat[0];
		for(int it = 1; it < depurat.length; it++) norma = norma.concat("_".concat(depurat[it]));
		return norma;
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
	
	public void setGradPane() {
		GridPaneManager.getInstance().buildAssignador(assignats, getConjunts(), !conjunt.getText().contains("S"));
		if(!isNew()) GridPaneManager.getInstance().updateAssignador(assignats,
																	PlaEstudisManager.getPath(),
																	AssignaturaManager.getPath(),
																	tipus.getText(),
																	Integer.parseInt(durada.getText()),
																	!conjunt.getText().contains("S"));
	}
	
	public static void setPath(String path) {
		String[] scan = path.split(" ");
		if(scan[0].charAt(1) == 'S') SessioManager.getInstance().onSubGrupItemClicked();
		else SessioManager.getInstance().onGrupItemClicked();
		
		SessioManager.getInstance().tipus.setText(scan[1]);
		SessioManager.getInstance().durada.setText(scan[3]);
		
		SessioManager.getInstance().update();
		SessioManager.getInstance().options.setDisable(true);
		
		ArrayList<String> data = ControladorPresentacio.getInstance().GetMainSessioData(PlaEstudisManager.getPath(),
																						AssignaturaManager.getPath(),
																						SessioManager.getInstance().getType(),
																						SessioManager.getInstance().getHores(),
																						!SessioManager.getInstance().conjunt.getText().contains("S"));
		
		for(int it = 0; it < data.size(); it++) {
			if(it == 0) SessioManager.getInstance().nsessions.setText(data.get(it));
			else SessioManager.getInstance().equip.setText(SessioManager.getInstance().equip.getText().concat(data.get(it)).concat(";"));
		}
	}
	
	public static String getPath() {
		return path;
	}
	
	public void update() {
		path = norma(tipus.getText()).concat("::").concat(durada.getText());
		title.setText("Sessio: ".concat(norma(tipus.getText())));
		
		setGradPane();
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
																											norma(tipus.getText()),
																											Integer.parseInt(durada.getText()));									
				else ControladorPresentacio.getInstance().CrearSessioGrup(PlaEstudisManager.getPath(),
																		  AssignaturaManager.getPath(),
																		  norma(tipus.getText()),
																		  Integer.parseInt(durada.getText()));
			}
		
			if(!Main.onError(false)) {
				if(conjunt.getText().contains("S")) ControladorPresentacio.getInstance().ModificarSessioSubGrup(PlaEstudisManager.getPath(),
																												AssignaturaManager.getPath(),
																												isNew()? norma(tipus.getText()) : getType(),
																												isNew()? Integer.parseInt(durada.getText()) : getHores(),
																												norma(tipus.getText()),
																												Integer.parseInt(durada.getText()),
																												Integer.parseInt(nsessions.getText()),
																												getEquipSet());
				else ControladorPresentacio.getInstance().ModificarSessioGrup(PlaEstudisManager.getPath(),
																			  AssignaturaManager.getPath(),
																		      isNew()? norma(tipus.getText()) : getType(),
																			  isNew()? Integer.parseInt(durada.getText()) : getHores(),
																			  norma(tipus.getText()),
																			  Integer.parseInt(durada.getText()),
																			  Integer.parseInt(nsessions.getText()),
																			  getEquipSet());
			}

			if(!Main.onError(false)) {
				for(int grup: GridPaneManager.getInstance().scannAssignacions(assignats).keySet()) {
					
					if(conjunt.getText().contains("S")) for(int subgrup:  GridPaneManager.getInstance().scannAssignacions(assignats).get(grup))
						ControladorPresentacio.getInstance().AssignaSessioSubGrup(PlaEstudisManager.getPath(),
																				  AssignaturaManager.getPath(),
																				  isNew()? norma(tipus.getText()) : getType(),
																				  Integer.parseInt(durada.getText()),
																				  grup,
																				  subgrup);
					
					else ControladorPresentacio.getInstance().AssignaSessioGrup(PlaEstudisManager.getPath(),
																		    	AssignaturaManager.getPath(),
																			    isNew()? norma(tipus.getText()) : getType(),
																			    Integer.parseInt(durada.getText()),
																				grup);
				}
			}
			
			if(!Main.onError(false)) this.update();
			this.options.setDisable(!Main.onError(true));
		}
	}

	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	@FXML
	public void onGrupItemClicked() {
		conjunt.setText("Grup");
		this.setGradPane();
	}
	
	@FXML
	public void onSubGrupItemClicked() {
		conjunt.setText("Subgrup");
		this.setGradPane();
	}
}
