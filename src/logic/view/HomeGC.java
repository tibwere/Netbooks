package logic.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.controller.BuyBookController;
import logic.controller.ViewBookByCategoryController;
import logic.view.bookpreview.BookListItem;

public class HomeGC implements Initializable {
	
	@FXML
	private VBox bookListView;
	
	@FXML
	private BorderPane pane;
	
	private BuyBookController buyBookController;
	private ArrayList<BookListItem> list; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		buyBookController = new BuyBookController(new ViewBookByCategoryController());
		list = new ArrayList<BookListItem>();
		
		fillPanel(buyBookController.getVbbcController().getBooks());
	}
	
	private void fillPanel(ArrayList<BookBean> books) {
		for (BookBean b : books) {
			list.add(new BookListItem(b));
		}
		
		bookListView.getChildren().addAll(list);
	}

}
