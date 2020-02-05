package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.controller.Session;
import logic.controller.ViewBookByCategoryController;
import logic.util.GraphicalElements;
import logic.util.enumeration.FXMLElements;

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
		thumbnail.setImage(bean.getSmallImage());
		titleLbl.setText(bean.getTitle());
		authorLbl.setText(bean.getAuthor());
	}
	
	@FXML
	public void goToBuyBook() {
		ViewBookByCategoryController.prepareToUpdateView(FXMLElements.BUY_BOOK);
		BuyBookGC gc = new BuyBookGC(bean);
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Session.getSession().getCurrView(), gc));
	}	


}
