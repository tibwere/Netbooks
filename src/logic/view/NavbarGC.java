package logic.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.controller.Session;
import logic.controller.ViewBookByCategoryController;
import logic.util.GraphicalElements;
import logic.util.enumeration.FXMLElements;

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
	private Button exchangeBtn;
	
	@FXML
	private Button helpBtn;
	
	@FXML
	private Button profileBtn;
	
	@FXML
	private Button logoutBtn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (Session.getSession().getCurrView().equals(FXMLElements.HOME))
			homeBtn.setDisable(true);
	}	
	
	@FXML
	public void goToHome() {		
		ViewBookByCategoryController.prepareToUpdateView(FXMLElements.HOME);
		contextSwitch();

	}
	
	@FXML
	public void goToExchangeBook() {		
		ViewBookByCategoryController.prepareToUpdateView(FXMLElements.EXCHANGE_BOOK);
		contextSwitch();

	}
	
	public void doLogout() {
						
		Optional<ButtonType> result = GraphicalElements.showDialog(AlertType.CONFIRMATION, "Netbooks asks ...", "Are you sure do you want to exit?");
		
		if (result.get().equals(ButtonType.OK)) {
			ViewBookByCategoryController.logoutUser();
			contextSwitch();
		}
	}
	
	private void contextSwitch() {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Session.getSession().getCurrView(), null));
	}

}
