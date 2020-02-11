package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.util.GraphicalElements;
import logic.util.enumeration.ImageSize;
import logic.util.enumeration.Views;
import logic.view.ratings.RatingModal;

public class BookPreviewGC implements Initializable{
	
	@FXML
	private ImageView thumbnail;
	
	@FXML
	private Label titleLbl;
	
	@FXML
	private Label authorLbl;
	
	@FXML
	private HBox pane;
	
	private BookBean bean;
	
	public BookPreviewGC(BookBean bean) {
		this.bean = bean;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		thumbnail.setImage(bean.getSingleImage(ImageSize.SMALL));
		titleLbl.setText(bean.getTitle());
		authorLbl.setText(bean.getAuthor());
	}
	
	@FXML
	public void goToBuyBook() {
		BuyBookGC gc = new BuyBookGC(bean);
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.BUY_BOOK, gc));
	}	
	
	@FXML
	public void rateBook() {
		Stage parent = (Stage) thumbnail.getScene().getWindow();
		Stage modal = GraphicalElements.createModalWindow(new Scene(new RatingModal(bean)), parent);
		modal.show();
	}

}
