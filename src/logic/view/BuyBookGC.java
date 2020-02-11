package logic.view;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.bean.BookBean;
import logic.util.GraphicalElements;
import logic.util.ShowPanelTask;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.ImageSize;
import logic.view.evaluationdecorator.EmptyBox;
import logic.view.evaluationdecorator.InAppRatingsBox;
import logic.view.evaluationdecorator.InAppReviewsBox;
import logic.view.evaluationdecorator.OnlineRatingsBox;
import logic.view.evaluationdecorator.Showable;

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
    private Showable element;

        
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
	public void showPopupDialogForRatings() {		
		element = new EmptyBox();
		
		if (!(inAppRatingsChk.isSelected() || inAppReviewsChk.isSelected() || googleRatingsChk.isSelected())) {	
			if (!moreInfoBox.getChildren().contains(errLbl))
				moreInfoBox.getChildren().add(errLbl);
		}
		
		else {
		
			if (inAppRatingsChk.isSelected()) 
				element = new InAppRatingsBox(element);
			if (inAppReviewsChk.isSelected())
				element = new InAppReviewsBox(element);
			if (googleRatingsChk.isSelected())
				element = new OnlineRatingsBox(element);
			
			ShowPanelTask task = new ShowPanelTask(element, bookToLoad);
			try {
				Scene loadingScene = new Scene(GraphicalElements.loadFXML(DynamicElements.LOADING_MODAL).load());
				Stage parent = (Stage) showBtn.getScene().getWindow();
				Stage loadingStage = GraphicalElements.createModalWindow(loadingScene, parent);
				loadingStage.initStyle(StageStyle.TRANSPARENT);
				loadingScene.setFill(Color.TRANSPARENT);
				
				task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
					
					@Override
					public void handle(WorkerStateEvent event) {
						try { 
							VBox box = task.get();
							Stage secondaryStage = GraphicalElements.createModalWindow(new Scene(box), parent);
							loadingStage.hide();
							secondaryStage.show();
						} 
						catch (InterruptedException | ExecutionException e1) {
							GraphicalElements.showDialog(AlertType.ERROR, "ops something went wrong ...", "Unable to load google reviews ...");
						}					
					}
				});
				
				executeTask(task);
				loadingStage.show();
			}
			catch (IllegalStateException | IOException e) {	
				GraphicalElements.showDialog(AlertType.ERROR, "Ops, something went wrong ...", "Unable to load modal window");
				Platform.exit();
			}			

		}
	}
	
    private void executeTask(Task<?> task) {
        Thread t = new Thread(task, "create-new-window");
        t.setDaemon(true);
        t.start();
    }
	
	
	public void resetCheckBoxes()  {
		
		if (moreInfoBox.getChildren().contains(errLbl))
			moreInfoBox.getChildren().remove(errLbl);
		
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