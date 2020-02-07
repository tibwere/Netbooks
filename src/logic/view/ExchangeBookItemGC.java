package logic.view;

/**
 * controller grafico collegato alla grafica "exchange_book_item"
 * @author Cristiano Cuffaro (M. 0258093)
 */

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.util.GraphicalElements;
import logic.util.enumeration.ImageSize;
import logic.util.enumeration.Views;

public class ExchangeBookItemGC implements Initializable {

	@FXML
	private VBox bookItem;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private Label label;
	
	@FXML
	private VBox vBoxInfo;
	
	@FXML
	private Label titleOfBook;
	
	@FXML
	private Label authorOfBook;
	
	private BookBean bean;
	
	public ExchangeBookItemGC(BookBean bean) {
		
		this.bean = bean;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		label.setText(bean.getTitle());
		label.setTextFill(Color.WHITE);
		imageView.setImage(bean.getSingleImage(ImageSize.MEDIUM));
		titleOfBook.setText(bean.getTitle());
		authorOfBook.setText(bean.getAuthor());
				
	}
	
	@FXML
	private void extendLabel() {
		vBoxInfo.setVisible(true);
	}
	
	@FXML
	private void hideLabel() {
		vBoxInfo.setVisible(false);
	}
	
	@FXML
	public void selectedBook() {
		MakeProposalGC gc = new MakeProposalGC(bean);
		Stage stage = (Stage) bookItem.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.MAKE_PROPOSAL, gc));

	}
}
