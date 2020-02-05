package logic.view;



import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.controller.KbsasController;
import logic.util.ImageDispenser;
import logic.view.bookinchart.BookInChartGC;


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
	    private Label labelRes;

	    @FXML
	    private Label label1;

	    @FXML
	    private Label label2;
	    
	    @FXML
	    private BorderPane borderPane;
	    
	    @FXML
	    private VBox vbox;
	    
		
		@FXML
		public void prova() {
			
		labelRes.setVisible(true);
			menuButton.setVisible(true);
			label1.setVisible(true);
			label2.setVisible(true);
			System.out.println(slider.getValue()); //valore dello slider da gestire per query
		
		
		}

		private KbsasController controller;
	
		@Override
		public void initialize(URL location, ResourceBundle resources) {
		
			try {
				controller = new KbsasController();
				fillPanel(controller.getBooks());
			} catch (Exception e) {
				
				e.printStackTrace();
				
				Alert alert = new Alert(AlertType.ERROR);
				
				Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
				alertStage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
				
				alert.setTitle("Netbooks v1.0");
				alert.setHeaderText("Ops something went wrong");
				alert.setContentText("Error loading book list items");
				alert.showAndWait();
				
				System.exit(0);
			}
		}

		private void fillPanel(List<BookBean> books) throws Exception {
			
			List<HBox> list = new ArrayList<>();

			for (BookBean b : books) {
				BookInChartGC gc = new BookInChartGC(b);
				FXMLLoader loader = new FXMLLoader(KbsasGC.class.getResource("resources/fxml/book_in_chart.fxml"));
				loader.setController(gc);
				HBox bookItem = loader.load();
				
				list.add(bookItem);
			}
			
			vbox.getChildren().addAll(list);
		}
		
	
	}	
	




	
	


