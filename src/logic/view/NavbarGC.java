package logic.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import logic.util.GraphicalElements;
import logic.util.Session;
import logic.util.enumeration.Views;

public class NavbarGC implements Initializable{
	
	@FXML
	private BorderPane main;

	@FXML
	private Button homeBtn;
	
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
	
	@FXML
	private HBox spacer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		HBox.setHgrow(spacer, Priority.SOMETIMES);

		switch(Session.getSession().getCurrView()) {
		case HOME:
			homeBtn.setDisable(true);
			break;
		case EXCHANGE_BOOK:
			homeBtn.setDisable(true);
			break;
		default:
			break;
		}	
	}	
	
	@FXML
	public void goToHome() {
		contextSwitch(Views.HOME);
	}
	
	@FXML
	public void goToExchangeBook() {		
		contextSwitch(Views.EXCHANGE_BOOK);
	}
	
	public void doLogout() {
						
		Optional<ButtonType> result = GraphicalElements.showDialog(AlertType.CONFIRMATION, "Netbooks asks ...", "Are you sure do you want to exit?");
		
		if (result.get().equals(ButtonType.OK)) {
			contextSwitch(Views.LOGIN);
		}
	}
	
	private void contextSwitch(Views nextView) {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(nextView, null));
	}

}
