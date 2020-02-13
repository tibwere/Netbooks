package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.bean.UserBean;
import logic.controller.ExchangeBookController;
import logic.exception.WrongSyntaxException;
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
	private ScrollPane scrollPane;
	
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
			Map<BookBean, UserBean> beans = controller.getExchangeableBooks();
			
			int i = 0;
			int j = 0;
			
			for (Map.Entry<BookBean, UserBean> entry : beans.entrySet()) {
				ExchangeBookItemGC gc = new ExchangeBookItemGC(entry.getKey(), entry.getValue(), this);
				FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.EXCHANGE_BOOK_ITEM);
				loader.setController(gc);
				VBox bookItem = loader.load();
				gridPane.add(bookItem, j, i);
				j = (j + 1) % 6;
				if (j == 0)
					i ++;
			}
		}
					
		catch (IOException | IllegalStateException | WrongSyntaxException e) {
			
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