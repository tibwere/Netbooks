package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import logic.bean.BookBean;

public class BuyBookGC implements Initializable{

    @FXML
    private ImageView amazonBtn;

    @FXML
    private ImageView mondadoriBtn;

    @FXML
    private ImageView playBtn;

    @FXML
    private ImageView bookImg;

    @FXML
    private HBox infoBox;

    @FXML
    private Label isbn10Lbl;

    @FXML
    private Label isbn13Lbl;

    @FXML
    private Label titleLbl;

    @FXML
    private Label authorLbl;

    @FXML
    private Label yearLbl;

    @FXML
    private Label publisherLbl;

    @FXML
    private Label languageLbl;

    private BookBean bookToLoad;
    
    public BuyBookGC(BookBean bookBean) {
    	bookToLoad = bookBean;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bookImg.setImage(bookToLoad.getLargeImage());
		isbn10Lbl.setText(bookToLoad.getIsbn());
		isbn13Lbl.setText("978-" + bookToLoad.getIsbn());
		titleLbl.setText(bookToLoad.getTitle());
		authorLbl.setText(bookToLoad.getAuthor());
		yearLbl.setText(String.valueOf(bookToLoad.getYearOfPublication()));
		publisherLbl.setText(bookToLoad.getPublisher());
		languageLbl.setText(bookToLoad.getLanguage());
		
	}

	@FXML
    public void viewReviews() {

    }
    
    public void test() {
    	System.out.println("test");
    }

}
