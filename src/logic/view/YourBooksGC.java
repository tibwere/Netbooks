package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.controller.ExchangeBookController;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.enumeration.DynamicElements;

public class YourBooksGC implements Initializable{
	
	@FXML
	private BorderPane mainBP;
	
	@FXML
	private VBox vbox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		try {
			ExchangeBookController controller = new ExchangeBookController();	
			List<BookBean> beans = controller.getUserBooks("");
			
			for (BookBean bean : beans) {
				YourBookItemGC gc = new YourBookItemGC(bean);
				FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.YOUR_BOOK_ITEM);
				loader.setController(gc);
				HBox item = loader.load();
				vbox.getChildren().add(item);
			}
		}			
		catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		} catch (IOException | IllegalStateException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load graphics for your books");
			Platform.exit();
		}		
	}

}
