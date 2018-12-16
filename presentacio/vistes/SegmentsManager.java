package presentacio.vistes;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.input.MouseEvent;

public class SegmentsManager {
	
	static private SegmentsManager current;
	
	@FXML private ListView<String> sessions;
	@FXML private Label plaEstudis, campus, dia, hora;
	
	public static SegmentsManager getInstance() {
		return SegmentsManager.current;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////  PRIVADES /////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////  PÚBLIQUES  /////////////////////////////////////
	
	public SegmentsManager() {
		current = this;
	}
	
	public void setPath(String plaEstudis, String campus, String dia, String hora) {
		this.plaEstudis.setText(plaEstudis);
		this.campus.setText(campus);
		this.dia.setText(dia);
		this.hora.setText(hora);
		
		this.update();
	}
	
	public void update() {
		this.sessions.getItems().clear();
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////// ON ITEM CLICKED ////////////////////////////////////
	
	public void onSessioItemClicked(MouseEvent click) {
		
	}
	
	public void onMove() {
		
	}
}
