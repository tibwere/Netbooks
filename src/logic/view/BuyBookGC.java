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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.bean.BookBean;
import logic.exception.NotAccesibleConfigurationException;
import logic.util.GraphicalElements;
import logic.util.ShowPanelTask;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.ImageSizes;
import logic.util.enumeration.Vendors;
import logic.util.enumeration.Views;
import logic.view.evaluationdecorator.EmptyBox;
import logic.view.evaluationdecorator.InAppRatingsBox;
import logic.view.evaluationdecorator.InAppReviewsBox;
import logic.view.evaluationdecorator.OnlineRatingsBox;
import logic.view.evaluationdecorator.Showable;

/**
 * Controller grafico relativo alla schermata di acquisto libro
 * [file fxml associato: buy_book.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
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
				
		bookImg.setImage(new Image(bookToLoad.getSingleImage(ImageSizes.LARGE)));
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
	
	private void buyBook(Vendors vendor) {
		try {
			FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.WEBVIEW);
			loader.setController(new WebViewGC(bookToLoad, vendor, this));
			Scene scene = new Scene(loader.load());
			Stage secondStage = GraphicalElements.createModalWindow(scene, (Stage) inAppRatingsChk.getScene().getWindow());
			secondStage.show();
		} catch (IOException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load modal window");
			Platform.exit();
		} catch (NotAccesibleConfigurationException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		}
	}
	
	@FXML
	private void buyOnAmazon() {
		buyBook(Vendors.AMAZON);		
	}
	
	@FXML
	private void buyOnMondadori() {
		buyBook(Vendors.MONDADORI);		
	}
	
	@FXML
	private void buyOnGooglePlay() {
		buyBook(Vendors.PLAY_BOOKS);		
	}

	@FXML
	private void showPopupDialogForEval() {	
		
		if (!hasCheckedSomething()) {	
			if (!moreInfoBox.getChildren().contains(errLbl))
				moreInfoBox.getChildren().add(errLbl);
		}
		else {
			Showable element = new EmptyBox();
		
			if (inAppRatingsChk.isSelected()) 
				element = new InAppRatingsBox(element);
			if (inAppReviewsChk.isSelected())
				element = new InAppReviewsBox(element);
			if (googleRatingsChk.isSelected())
				element = new OnlineRatingsBox(element);
			
			try {
				showPopupFrame(element);
			} catch (IllegalStateException | IOException e) {	
				GraphicalElements.showDialog(AlertType.ERROR, "Unable to load modal window");
				Platform.exit();
			} catch (NotAccesibleConfigurationException e) {
				GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
				Platform.exit();
			}			
		}
	}
	
	private void showPopupFrame(Showable element) throws IOException, NotAccesibleConfigurationException {
		ShowPanelTask task = new ShowPanelTask(element, bookToLoad);
		Stage parent = (Stage) showBtn.getScene().getWindow();
		Stage loadingStage = createStage(parent);

		task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent event) {
				try { 
					VBox box = task.get();
					Stage secondaryStage = GraphicalElements.createModalWindow(new Scene(box), parent);
					loadingStage.hide();
					secondaryStage.show();
				} catch (InterruptedException | ExecutionException e) {
					GraphicalElements.showDialog(AlertType.ERROR, "Unable to load google reviews ...");
					Thread.currentThread().interrupt();
				} catch (NotAccesibleConfigurationException e) {
					GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
					Thread.currentThread().interrupt();
				}					
			}
		});
		
		executeTask(task);
		loadingStage.show();		
	}
	
	private Stage createStage(Stage parent) throws IOException, NotAccesibleConfigurationException {
		Scene loadingScene = new Scene(GraphicalElements.loadFXML(DynamicElements.LOADING_MODAL).load());
		Stage loadingStage = GraphicalElements.createModalWindow(loadingScene, parent);
		loadingStage.initStyle(StageStyle.TRANSPARENT);
		loadingScene.setFill(Color.TRANSPARENT);
		
		return loadingStage;
	}
	

	private boolean hasCheckedSomething() {
		return (inAppRatingsChk.isSelected() || inAppReviewsChk.isSelected() || googleRatingsChk.isSelected());
	}
	
    private void executeTask(Task<?> task) {
        Thread t = new Thread(task, "create-new-window");
        t.setDaemon(true);
        t.start();
    }
	
    @FXML
	private void resetCheckBoxes()  {
		
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
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to reset checkboxes");
			Platform.exit();
		}
	}
	
	public void getBackToHome() {
		Stage stage = (Stage) authorLbl.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.HOME, null));
	}

	
}