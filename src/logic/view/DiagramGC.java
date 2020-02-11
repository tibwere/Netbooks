package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.util.GraphicalElements;
import logic.util.enumeration.Views;


public class DiagramGC implements Initializable {

    @FXML
    private BorderPane borderPane;
 
    @FXML
    private Button backBtn;
    
    @FXML
    private VBox box;
    
    private Chart chart;
    
    
    public DiagramGC(Chart chart) {
    	this.chart = chart;
    }
    
    @Override
   	public void initialize(URL location, ResourceBundle resources) {
       box.getChildren().add(chart);	 	
   	}
    
    @FXML
	public void backBtnAction() {
    	Stage stage = (Stage)borderPane.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.KBSAS, null));
	}
    
}