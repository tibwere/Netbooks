package logic.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import logic.controller.ExchangeBookController;

/**
 * Controller grafico collegato al file "exchangeBook.fxml" 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookGC implements Initializable {
	
	@FXML BorderPane borderPane;
	
	@FXML
	private Button manageProposalesBtn;
	
	@FXML
	private Button exchangeableListBtn;
	
	@FXML
	private GridPane gridPane;
	
	private ExchangeBookController controller;
	
	private List<Image> books;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		controller = new ExchangeBookController();
		
		books = controller.getExchangeableBooks();
		
		int i, j;	
		
		for (i = 0; i < 1; i ++) {
			
			for (j = 0; j < 2; j ++) {
				
				VBox vbx = new VBox();
				
				Label label = new Label("LIBRO");
				
				label.setTextFill(Color.WHITE);
				
				ImageView imv = new ImageView(books.get(i*6 + j));
				
				vbx.setSpacing(5.0);
				
				vbx.getChildren().addAll(imv, label);
				
				gridPane.add(vbx, j, i);

			}
		}
	}
}