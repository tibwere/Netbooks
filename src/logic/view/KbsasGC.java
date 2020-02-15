package logic.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.controller.KbsasController;
import logic.util.DiagramFactory;
import logic.util.GraphicalElements;
import logic.util.enumeration.DiagramTypes;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.Views;;


/**
 * Controller grafico collegato al file "kbsas.fxml"
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class KbsasGC {

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
    private VBox vbox;
    
    @FXML
    private MenuItem barChart;
    
    @FXML
    private MenuItem pieChart;
    
    private Chart chart;
	private List<BookBean> books;
    
	@FXML
	public void showBestsellers() {
		
		labelRes.setVisible(true);
		menuButton.setVisible(true);
		line.setVisible(true);
	
		try {
			KbsasController controller;
			controller = new KbsasController();
			books = controller.getBooksForRetailer();
			appendBooksOnPane(books);
		} catch (Exception e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load chart");
			Platform.exit();
		}
	}
	
	
	@FXML
	public void onSliderChanged() {
	    int sliderValue = Math.round((int)slider.getValue());
	    km.setText("Selected value : "+ sliderValue +"KM");
	}

	private void appendBooksOnPane(List<BookBean> books) throws Exception {
		
		List<HBox> list = new ArrayList<>();

		for (BookBean b : books) {
			BookInChartGC gc = new BookInChartGC(b);
			FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.BOOK_IN_CHART);
			loader.setController(gc);
			HBox bookItem = loader.load();
			
			list.add(bookItem);
		}
		
		vbox.getChildren().addAll(list);
	}
	

	 @FXML
	 public void barChartAction()  {
		 DiagramFactory factory  = new DiagramFactory();
		 chart = factory.createChart(DiagramTypes.BAR_CHART, books);
		 chart.setTitle("Top 5 book (radius selected = " + Math.round((int)slider.getValue()) +"KM)");
		 
		 Stage stage = (Stage)borderPane.getScene().getWindow();
		 DiagramGC gc = new DiagramGC(chart);
		 stage.setScene(GraphicalElements.switchTo(Views.DIAGRAM, gc));
	 }
	    
	 @FXML
	 public void pieChartAction() {
	    DiagramFactory factory  = new DiagramFactory();
		chart = factory.createChart(DiagramTypes.PIE_CHART, books);
		chart.setTitle("Top 5 book (radius selected = " + Math.round((int)slider.getValue()) +"KM)");

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