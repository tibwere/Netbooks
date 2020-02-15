package logic.view;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.bean.UserBean;
import logic.controller.LoginController;
import logic.exception.NoUserFoundException;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.util.GraphicalElements;
import logic.util.ImageDispenser;
import logic.util.enumeration.DynamicElements;
import logic.util.enumeration.UserTypes;
import logic.util.enumeration.Views;

/**
 * Controller grafico relativo alla schermata di login
 * [file fxml associato: login.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class LoginGC implements Initializable{
	
	@FXML
	private TextField usernameTxt;
	
	@FXML
	private PasswordField passwordTxt;
	
	@FXML
	private Button loginBtn;
	
	@FXML
	private Label resultLbl;
	
	@FXML
	private ImageView logo;
	
	@FXML
	private VBox pane;
	
	@FXML
	private CheckBox showPswChk;
	
	@FXML
	private VBox textFieldsBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		TextField passwordVisibileTxt = new TextField();
		passwordVisibileTxt.setVisible(false);
		passwordVisibileTxt.setManaged(false);
		textFieldsBox.getChildren().add(passwordVisibileTxt);

	    passwordVisibileTxt.managedProperty().bind(showPswChk.selectedProperty());
	    passwordVisibileTxt.visibleProperty().bind(showPswChk.selectedProperty());

	    passwordTxt.managedProperty().bind(showPswChk.selectedProperty().not());
	    passwordTxt.visibleProperty().bind(showPswChk.selectedProperty().not());

	    passwordVisibileTxt.textProperty().bindBidirectional(passwordTxt.textProperty());
		
		pane.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
		    	if (event.getCode().equals(KeyCode.ENTER))
		    		tryLogin();				
			}
		});
		
		showPswChk.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if (Boolean.TRUE.equals(newValue)) 
					showPswChk.setText("Hide Password");
				else
					showPswChk.setText("Show Password");
			}
		});
	}
	
	@FXML
	public void tryLogin() {
		
		try {
			String username = usernameTxt.getText();
			String password = passwordTxt.getText();
			LoginController controller = new LoginController();
			UserBean bean = new UserBean(username, password);

			UserTypes type = controller.loginUser(bean);
			
			Stage stage = (Stage) pane.getScene().getWindow();
			if (type.equals(UserTypes.READER))
				stage.setScene(GraphicalElements.switchTo(Views.HOME, null));
			else
				stage.setScene(GraphicalElements.switchTo(Views.KBSAS, null));
		} catch(NoUserFoundException | PersistencyException e) {
			resultLbl.setText(e.getMessage().toUpperCase());
		} catch (NoSuchAlgorithmException e) {
			resultLbl.setText("UNABLE TO ENCRYPT YOUR PASSWORD");
		} catch (WrongSyntaxException e) {
			resultLbl.setText("LOGIN FAILED");
		}
	}
	
	@FXML
	public void openSignupModal() {	
		
		Alert chooseTypeOfRegistrationAlert = createCustomAlert();
		Optional<ButtonType> result = chooseTypeOfRegistrationAlert.showAndWait();
		
		if (!result.get().getText().equals("DISMISS")) {
			try {
				Stage signStage = null;
				
				if (result.get().getText().equals("READER"))
					signStage = createTransparentModalStage(DynamicElements.SIGNUP_READER);
				else if (result.get().getText().equals("RETAILER"))
					signStage = createTransparentModalStage(DynamicElements.SIGNUP_RETAILER);
				
				if (signStage != null)
					signStage.show();
				
			} catch (IOException e) {
				GraphicalElements.showDialog(AlertType.ERROR, "Unable to load signup modal");
				Thread.currentThread().interrupt();
			}
		}
	}
	
	private Stage createTransparentModalStage(DynamicElements elem) throws IOException {
		Stage parentStage = (Stage) loginBtn.getScene().getWindow();
		FXMLLoader loader = GraphicalElements.loadFXML(elem);
		
		if (elem.equals(DynamicElements.SIGNUP_READER))
			loader.setController(new SignupReaderGC(parentStage));
		else
			loader.setController(new SignupRetailerGC(parentStage));
		
		Scene signupScene = new Scene(loader.load());
		Stage newStage = GraphicalElements.createModalWindow(signupScene, parentStage);
		newStage.initStyle(StageStyle.TRANSPARENT);
		signupScene.setFill(Color.TRANSPARENT);

		return newStage;
	}
	
	private Alert createCustomAlert() {
		Alert al = new Alert(AlertType.INFORMATION);
		Stage alertStage = (Stage) al.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
		al.setTitle("Netbooks v1.0");
		al.setHeaderText("Netbooks asks ...");
		al.setContentText("Do you want to signup as reader or retailer?");
		
		al.getButtonTypes().clear();
		al.getButtonTypes().add(new ButtonType("READER"));
		al.getButtonTypes().add(new ButtonType("RETAILER"));
		al.getButtonTypes().add(new ButtonType("DISMISS"));
		
		return al;
	}
}
