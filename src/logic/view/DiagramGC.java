package logic.view;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.util.DiagramFactory;
import logic.util.GraphicalElements;
import logic.util.enumeration.DiagramTypes;
import logic.util.enumeration.Views;


public class DiagramGC implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private VBox box;

    @FXML
    private Label titleLbl;

    @FXML
    private VBox chartPanel;

    @FXML
    private Button backBtn;
    
    private int radius;
    private DiagramTypes typeOfDiagram;
    private Map<BookBean, Integer> books;
    
    public DiagramGC(int radius, DiagramTypes typeOfDiagram, Map<BookBean, Integer> books) {
    	this.radius = radius;
    	this.typeOfDiagram = typeOfDiagram;
    	this.books = books;
    }
    
    @Override
   	public void initialize(URL location, ResourceBundle resources) {
    	titleLbl.setText("Top 5 book (radius selected = " + radius +"km)");
    	DiagramFactory factory  = new DiagramFactory();
		Chart chart = factory.createChart(typeOfDiagram, books);
		chart.setTitle("Top 5 book");
		
		this.chartPanel.getChildren().add(chart);
   	}
    
    @FXML
	public void backBtnAction() {
    	Stage stage = (Stage)borderPane.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.KBSAS, null));
	}
    
}