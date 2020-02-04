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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.controller.BuyBookController;
import logic.controller.ViewBookByCategoryController;
import logic.util.ImageDispenser;
import logic.view.bookpreview.BookPreviewGC;

public class HomeGC implements Initializable {
	
	@FXML
	private VBox bookListView;
	
	@FXML
	private BorderPane pane;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			BuyBookController buyBookController = new BuyBookController(new ViewBookByCategoryController());
			fillPanel(buyBookController.getVbbcController().getBooks());
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			
			Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
			alertStage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
			
			alert.setTitle("Netbooks v1.0");
			alert.setHeaderText("Ops something went wrong");
			alert.setContentText("Error loading book list items");
			alert.showAndWait();
			
			/* rimane da gestire in maniera più efficace la chiusura */
			System.exit(0); 
		}
	}
	
	private void fillPanel(List<BookBean> books) throws Exception {
		List<HBox> list = new ArrayList<>();

		for (BookBean b : books) {
			BookPreviewGC gc = new BookPreviewGC(b);
			FXMLLoader loader = new FXMLLoader(HomeGC.class.getResource("resources/fxml/hp_book_preview.fxml"));
			loader.setController(gc);
			HBox bookItem = loader.load();
			
			list.add(bookItem);
		}
		
		bookListView.getChildren().addAll(list);
	}



}
