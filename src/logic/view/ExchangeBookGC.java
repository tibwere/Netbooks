package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.controller.ExchangeBookController;
import logic.util.GraphicalElements;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.Views;

/**
 * Controller grafico collegato al file "exchange_book.fxml" 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookGC implements Initializable {
	
	@FXML 
	private BorderPane borderPane;
	
	@FXML
	private HBox spacing;
	
	@FXML
	private Button manageProposals;
	
	@FXML
	private Button yourBooks;
	
	@FXML
	private GridPane gridPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
		
			ExchangeBookController controller = new ExchangeBookController();
			List<BookBean> beans = controller.getExchangeableBooks();
		
			int i;
			int j;

			for (i = 0; i < 3; i ++) {
				for (j = 0; j < 6; j ++) {
					ExchangeBookItemGC gc = new ExchangeBookItemGC(beans.get(i*6 + j), this);
					FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.EXCHANGE_BOOK_ITEM);
					loader.setController(gc);
					VBox bookItem = loader.load();
					gridPane.add(bookItem, j, i);
				}
			}
		}
					
		catch (IOException | IllegalStateException e) {
			
			GraphicalElements.showDialog(AlertType.ERROR, "Error!", "Unable to load exchangeable books.");
			Platform.exit();
		}
		
	}
	
	@FXML
	public void clickOnManageProposals() {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.MANAGE_PROPOSALS, null));
	}
	
	public void bringToFront(VBox obj) {
		obj.toFront();
	}
}