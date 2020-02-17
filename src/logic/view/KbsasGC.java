package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.Chart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.controller.KbsasController;
import logic.exception.PersistencyException;
import logic.util.DiagramFactory;
import logic.util.GraphicalElements;
import logic.util.Session;
import logic.util.enumeration.DiagramTypes;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.Views;

/**
 * Controller grafico collegato al file "kbsas.fxml"
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class KbsasGC implements Initializable{

	@FXML
    private Button buttonVBS;

    @FXML
    private Slider slider;

    @FXML
    private MenuButton menuButton;

    @FXML
    private Line line;

    @FXML
    private Label km;
    
    @FXML
    private Label labelRes;
    
    @FXML
    private Button logoutBtn;
    
    @FXML
    private BorderPane borderPane;
    
    @FXML
    private VBox vboxMain;
    
    @FXML
    private MenuItem barChart;
    
    @FXML
    private MenuItem pieChart;
    
    @FXML
    private Label lblUser; 
    
    private Chart chart;
    private Map<BookBean , Integer> bookInChart;

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			lblUser.setText("user : " + Session.getSession().getCurrUser());
			lblUser.setTextFill(Color.WHITE);
			KbsasController controller;
			controller = new KbsasController();
			bookInChart = controller.getBooksForRetailer((int)Math.round(slider.getValue()));
			appendBooksOnPane(bookInChart);
		} catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		} catch (IOException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load graphics for chart");
			Platform.exit();
		}
		
	}
	
    
	@FXML
	public void showBestsellers() throws PersistencyException {
		vboxMain.getChildren().clear();
		try {
			KbsasController controller;
			controller = new KbsasController();
			bookInChart = controller.getBooksForRetailer((int)Math.round(slider.getValue()));
			appendBooksOnPane(bookInChart);
		} catch (IOException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load chart");
			Platform.exit();
		}
	}
	
	private void appendBooksOnPane(Map<BookBean , Integer> books) throws IOException {
		
		List<HBox> list = new ArrayList<>();
		int rank = 1;

		for (Map.Entry<BookBean, Integer> entry : books.entrySet()) {
			BookInChartGC gc = new BookInChartGC( rank , entry.getKey(), entry.getValue() );
			rank++;
			FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.BOOK_IN_CHART);
			loader.setController(gc);
			HBox bookItem = loader.load();
			
			
			list.add(bookItem);
		}
		
		vboxMain.getChildren().addAll(list);
	}
	
	@FXML
	public void onSliderChanged() {
	    int sliderValue = (int)Math.round(slider.getValue());
	    km.setText("Selected value : "+ sliderValue +"KM");
	}
	

	 @FXML
	 public void barChartAction()  {
		 DiagramFactory factory  = new DiagramFactory();
		 chart = factory.createChart(DiagramTypes.BAR_CHART, bookInChart);
		 chart.setTitle("Top 5 book (radius selected = " + (int)Math.round(slider.getValue()) +"KM)");
		 
		 Stage stage = (Stage)borderPane.getScene().getWindow();
		 DiagramGC gc = new DiagramGC(chart);
		 stage.setScene(GraphicalElements.switchTo(Views.DIAGRAM, gc));
	 }
	    
	 @FXML
	 public void pieChartAction() {
	    DiagramFactory factory  = new DiagramFactory();
		chart = factory.createChart(DiagramTypes.PIE_CHART, bookInChart);
		chart.setTitle("Top 5 book (radius selected = " + (int)Math.round(slider.getValue()) +"KM)");

		Stage stage = (Stage)borderPane.getScene().getWindow();
		DiagramGC gc = new DiagramGC(chart);
		stage.setScene(GraphicalElements.switchTo(Views.DIAGRAM, gc));
	 }
	 
	 @FXML
	 public void logout() {
		 Optional<ButtonType> result = GraphicalElements.showDialog(AlertType.CONFIRMATION, "Are you sure do you want to exit?");
		 
		 if (result.get().equals(ButtonType.OK)) {
			 Stage stage = (Stage)borderPane.getScene().getWindow();
			 stage.setScene(GraphicalElements.switchTo(Views.LOGIN, null));
		 }
	 }

}