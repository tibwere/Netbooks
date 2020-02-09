package logic.view;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.util.AppProperties;
import logic.util.GraphicalElements;
import logic.util.ImageDispenser;
import logic.util.enumeration.ImageSize;
import logic.view.ratings.EmptyBox;
import logic.view.ratings.InAppRatingsBox;
import logic.view.ratings.InAppReviewsBox;
import logic.view.ratings.Showable;

public class BuyBookGC implements Initializable{
	
	@FXML
    private VBox mainPanel;

    @FXML
    private ImageView bookImg;

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
    private ImageView mondadoriBtn;

    @FXML
    private ImageView playBtn;

    @FXML
    private CheckBox inAppRatingsChk;

    @FXML
    private CheckBox inAppReviewsChk;

    @FXML
    private CheckBox googleRatingsChk;
    
    @FXML
    private Button showBtn;
    
    @FXML
    private VBox moreInfoBox;
    
    private Label errLbl;

    private BookBean bookToLoad;
    
    public BuyBookGC(BookBean bookBean) {
    	this.bookToLoad = bookBean;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		errLbl = new Label("SELECT AT LEAST\nONE ELEMENT");
		errLbl.setTextAlignment(TextAlignment.CENTER);
		errLbl.setTextFill(Color.RED);
		errLbl.setFont(Font.font("System", FontWeight.BOLD, 12));
		
		bookImg.setImage(bookToLoad.getSingleImage(ImageSize.LARGE));
		isbn10Lbl.setText(bookToLoad.getIsbn());
		isbn13Lbl.setText("978-" + bookToLoad.getIsbn());
		titleLbl.setText(bookToLoad.getTitle());
		authorLbl.setText(bookToLoad.getAuthor());
		yearLbl.setText(String.valueOf(bookToLoad.getYearOfPublication()));
		publisherLbl.setText(bookToLoad.getPublisher());
		languageLbl.setText(bookToLoad.getLanguage());
		
		ChangeListener<Boolean> hideErr = new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (moreInfoBox.getChildren().contains(errLbl))
					moreInfoBox.getChildren().remove(errLbl);
			}
		};
		
		inAppRatingsChk.selectedProperty().addListener(hideErr);
		inAppReviewsChk.selectedProperty().addListener(hideErr);
		googleRatingsChk.selectedProperty().addListener(hideErr);
		
	}
	
	@FXML
	public void showResults() {
		
		Showable element = new EmptyBox();
		
		if (!(inAppRatingsChk.isSelected() || inAppReviewsChk.isSelected() || googleRatingsChk.isSelected())) {	
			if (!moreInfoBox.getChildren().contains(errLbl))
				moreInfoBox.getChildren().add(errLbl);
		}
		
		else {
		
			if (inAppRatingsChk.isSelected()) 
				element = new InAppRatingsBox(element);
			if (inAppReviewsChk.isSelected())
				element = new InAppReviewsBox(element);
			
			VBox body = element.show(bookToLoad);
		
			Stage secondaryStage = new Stage();
			secondaryStage.initModality(Modality.APPLICATION_MODAL);
			secondaryStage.setTitle(AppProperties.getInstance().getProperty("title"));
			secondaryStage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
			secondaryStage.setScene(new Scene(body));
			secondaryStage.show();	
		}

	}
	
	
	public void resetCheckBoxes()  {
		
		try {
			Field [] fields = BuyBookGC.class.getDeclaredFields();
		
			for (Field field : fields) {
				if (field.getType().equals(CheckBox.class)) {
					Method method = CheckBox.class.getDeclaredMethod("setSelected", Boolean.TYPE);
					
					CheckBox chk = (CheckBox) field.get(this);
					method.invoke(chk, false);
				}
			}
		} 
		catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Ops, something went wrong", "Unable to reset checkboxes");
			Platform.exit();
		}
	}

	
}