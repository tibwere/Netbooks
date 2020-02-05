package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.controller.ExchangeBookController;

/**
 * Controller grafico collegato al file "exchange_book.fxml" 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookGC implements Initializable {
	
	@FXML 
	private BorderPane borderPane;
	
	@FXML
	private Button manageProposalsBtn;
	
	@FXML
	private Button exchangeableListBtn;
	
	@FXML
	private GridPane gridPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
		
			ExchangeBookController controller = new ExchangeBookController();
		
			List<BookBean> beans = new ArrayList<>();
		
			beans = controller.getExchangeableBooks();
		
			int i;
			int j;
		
			for (i = 0; i < 1; i ++) {
			
				for (j = 0; j < 2; j ++) {
							
					ExchangeBookItemGC gc = new ExchangeBookItemGC(beans.get(i*6 + j));
				
					FXMLLoader loader = new FXMLLoader(ExchangeBookGC.class.getResource("resources/fxml/exchange_book_item.fxml"));
			
					loader.setController(gc);
				
					VBox bookItem = loader.load();
					
					gridPane.add(bookItem, j, i);
				}
			}
		}
					
		catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}