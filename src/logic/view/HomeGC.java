package logic.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import logic.controller.BuyBookController;
import logic.controller.ManageEvaluationsController;
import logic.util.GraphicalElements;
import logic.util.enumeration.DynamicElements;

/**
 * Controller grafico relativo alla homepage dell'applicazione
 * [file fxml associato: home.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class HomeGC implements Initializable {
	
	@FXML
	private VBox bookListView;
	
	@FXML
	private BorderPane pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			BuyBookController buyBookController = new BuyBookController(new ManageEvaluationsController());
			fillPanel(buyBookController.getBooksForHomepage());
		} catch (IllegalStateException | IOException | ClassNotFoundException | SQLException e) {	
			GraphicalElements.showDialog(AlertType.ERROR, "Ops, something went wrong ...", "Unable to load book list elements");
			Platform.exit();
		} 
	}
	
	private void fillPanel(List<BookBean> books) throws IOException {
		List<HBox> list = new ArrayList<>();

		for (BookBean b : books) {
			BookPreviewGC gc = new BookPreviewGC(b);
			FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.HP_BOOK_PREVIEW);
			loader.setController(gc);
			HBox bookItem = loader.load();
			
			list.add(bookItem);
		}
		
		bookListView.getChildren().addAll(list);
	}



}
