package logic.view.bookpreview;

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
import logic.util.Scenes;
import logic.util.enumeration.Views;

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
		thumbnail.setImage(bean.getImage());
		titleLbl.setText(bean.getTitle());
		authorLbl.setText(bean.getAuthor());
	}
	
	@FXML
	public void goToBuyBook() {
		ViewBookByCategoryController.prepareToUpdateView(Views.BUY_BOOK);
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.setScene(Scenes.switchTo(Session.getSession().getCurrView()));
	}	


}
