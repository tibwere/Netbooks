package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import logic.bean.BookBean;
import logic.util.GraphicalElements;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.ImageSize;

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
    private VBox mainPanel;

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
    
    @FXML
    private Button reviewsBtn;

    private BookBean bookToLoad;
    
    private boolean areInfoShowed;
    
    private HBox infoBox;
    
    public BuyBookGC(BookBean bookBean) {
    	try {
    		this.bookToLoad = bookBean;
			this.infoBox = GraphicalElements.loadFXML(DynamicElements.MORE_INFO_PANE).load();			
		} catch (IllegalStateException | IOException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Ops, something went wrong ...", "Unable to load ratings and reviews");
			Platform.exit();
		}
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		HBox.setHgrow(infoBox, Priority.SOMETIMES);
		
		bookImg.setImage(bookToLoad.getSingleImage(ImageSize.LARGE));
		isbn10Lbl.setText(bookToLoad.getIsbn());
		isbn13Lbl.setText("978-" + bookToLoad.getIsbn());
		titleLbl.setText(bookToLoad.getTitle());
		authorLbl.setText(bookToLoad.getAuthor());
		yearLbl.setText(String.valueOf(bookToLoad.getYearOfPublication()));
		publisherLbl.setText(bookToLoad.getPublisher());
		languageLbl.setText(bookToLoad.getLanguage());
		
	}
	
	@FXML
	public void viewReviews() throws IOException {
		if(areInfoShowed) {
			reviewsBtn.setText("VIEW IN-APP REVIEWS");
			mainPanel.getChildren().remove(infoBox);
			mainPanel.setAlignment(Pos.CENTER);
		}
		else {
			reviewsBtn.setText("HIDE IN-APP REVIEWS");
			mainPanel.setAlignment(Pos.BOTTOM_CENTER);
			mainPanel.getChildren().add(infoBox);
		}
		
		areInfoShowed = !areInfoShowed;
	}

}