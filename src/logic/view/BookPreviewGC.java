package logic.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.controller.BuyBookController;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.enumeration.ImageSizes;
import logic.util.enumeration.Views;

/**
 * Controller grafico relativo all'anteprima del libro mostrata nella homepage
 * [file fxml associato: hp_book_preview.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
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
		thumbnail.setImage(bean.getSingleImage(ImageSizes.SMALL));
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

		try {
			BuyBookController ctrl = new BuyBookController(null);
			if (ctrl.bookIsOwned(bean)) {
				Stage parent = (Stage) thumbnail.getScene().getWindow();
				Stage modal = GraphicalElements.createModalWindow(new Scene(new RatingModal(bean)), parent);
				modal.show();
			} 
			else {
				GraphicalElements.showDialog(AlertType.WARNING, "You cannot leave a review for a book that you don't own yet");
			}
		} catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
		}
	}
	
	@FXML
	public void addBook() {
		Optional<ButtonType> result = GraphicalElements.showDialog(AlertType.CONFIRMATION, "Are you sure do you want to add this book to your owned list?");
		
		if (result.get().equals(ButtonType.OK)) {
			try {
				BuyBookController ctrl = new BuyBookController(null);
				ctrl.addBookToOwnedList(bean);
				
				GraphicalElements.showDialog(AlertType.INFORMATION, "\"" + bean.getTitle() + "\" has succesfully added to your owned list!" );
			} catch (PersistencyException e) {
				GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
				Platform.exit();
			} catch (AlreadyOwnedBookException e) {
				GraphicalElements.showDialog(AlertType.WARNING, e.getMessage());
			}
		}
	}

}
