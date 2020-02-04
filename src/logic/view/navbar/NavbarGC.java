package logic.view.navbar;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.controller.Session;
import logic.controller.ViewBookByCategoryController;
import logic.util.ImageDispenser;
import logic.util.Scenes;
import logic.util.enumeration.Views;

public class NavbarGC implements Initializable{
	
	@FXML
	private HBox main;

	@FXML
	private Button homeBtn;
	
	@FXML
	private Button findBtn;
	
	@FXML
	private Button forumBtn;
	
	@FXML
	private Button helpBtn;
	
	@FXML
	private Button profileBtn;
	
	@FXML
	private Button logoutBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (Session.getSession().getCurrView().equals(Views.HOME))
			homeBtn.setDisable(true);
	}	
	
	@FXML
	public void goToHome() {		
		ViewBookByCategoryController.prepareToUpdateView(Views.HOME);
		contextSwitch();

	}
	
	public void doLogout() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
		
		alert.setTitle("Netbooks v1.0");
		alert.setHeaderText("Netbooks asks ...");
		alert.setContentText("Are you sure do you want to exit?");
				
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get().equals(ButtonType.OK)) {
			ViewBookByCategoryController.logoutUser();
			contextSwitch();
		}
	}
	
	private void contextSwitch() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(Scenes.switchTo(Session.getSession().getCurrView()));
	}

}
