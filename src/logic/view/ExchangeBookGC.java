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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.bean.UserBean;
import logic.controller.ExchangeBookController;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.Views;

/**
 * Controller grafico collegato al file "exchange_book.fxml" 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookGC implements Initializable {
	
	public static final String POS_LEFT = "left";
	public static final String POS_RIGHT = "right";
	
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
	
	@FXML
	private ImageView notification;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			ExchangeBookController controller = new ExchangeBookController();
			notification.setVisible(controller.findNotifications());
						
			Map<BookBean, UserBean> beans = controller.getAllExchangeableBooks();
			
			int i = 0;
			int j = 0;
			
			for (Map.Entry<BookBean, UserBean> entry : beans.entrySet()) {
				String pos = j < 3 ? POS_LEFT : POS_RIGHT;
				ExchangeBookItemGC gc = new ExchangeBookItemGC(entry.getKey(), entry.getValue(), this, pos);
				FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.EXCHANGE_BOOK_ITEM);
				loader.setController(gc);
				VBox bookItem = loader.load();
				gridPane.add(bookItem, j, i);
				j = (j + 1) % 6;
				if (j == 0)
					i ++;
			}
		}
					
		catch (IOException | IllegalStateException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load exchangeable books.");
			Platform.exit();
		} catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
		}		
	}
	
	@FXML
	public void clickOnManageProposals() {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.MANAGE_PROPOSALS, null));
	}
	
	@FXML
	public void clickOnYourBooks() {
		Stage stage = (Stage) borderPane.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.YOUR_BOOKS, null));
	}
	
	public void bringToFront(VBox obj) {
		obj.toFront();
	}
}