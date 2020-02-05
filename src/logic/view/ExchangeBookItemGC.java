package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * controller grafico collegato alla grafica "exchange_book_item"
 * @author Cristiano Cuffaro (M. 0258093)
 */

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import logic.bean.BookBean;

public class ExchangeBookItemGC implements Initializable {

	@FXML
	private VBox bookItem;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private Label label;
	
	private BookBean bean;
	
	public ExchangeBookItemGC (BookBean bean) {
		
		this.bean = bean;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		label.setText(bean.getTitle());
		imageView.setImage(bean.getImage());
				
	}
	
}
