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
import logic.bean.UserBean;
import logic.util.GraphicalElements;
import logic.util.enumeration.ImageSizes;
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
	private Label ownerOfBook;
	
	private BookBean bookBean;
	
	private UserBean ownerBean;
	
	private ExchangeBookGC mainGC;
	
	public ExchangeBookItemGC(BookBean bookBean, UserBean ownerBean, ExchangeBookGC mainGC) {
		
		this.bookBean = bookBean;
		this.ownerBean = ownerBean;
		this.mainGC = mainGC;
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		label.setText(bookBean.getTitle());
		label.setTextFill(Color.WHITE);
		imageView.setImage(bookBean.getSingleImage(ImageSizes.MEDIUM));
		titleOfBook.setText(bookBean.getTitle());
		titleOfBook.setTextFill(Color.BLACK);
		ownerOfBook.setText(ownerBean.getUsername());
		ownerOfBook.setTextFill(Color.BLACK);
				
	}
	
	@FXML
	private void extendLabel() {
		mainGC.bringToFront(bookItem);
		vBoxInfo.setVisible(true);
	}
	
	@FXML
	private void hideLabel() {
		vBoxInfo.setVisible(false);
	}
	
	@FXML
	public void selectedBook() {
		MakeProposalGC gc = new MakeProposalGC(bookBean, ownerBean);
		Stage stage = (Stage) bookItem.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.MAKE_PROPOSAL, gc));
	}
}
