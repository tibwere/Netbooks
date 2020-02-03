package logic.view.navbar;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import logic.util.SceneFactory;
import logic.util.Session;
import logic.util.enumeration.Views;

public class MainNavbarGC {
	
	@FXML
	private HBox main;

	@FXML
	private Button homeBtn;
	
	@FXML
	private Button findBtn;
	
	@FXML
	private Button forumBtn;
	
	@FXML
	public void goToHome() {
		Session.getSession().setCurrView(Views.HOME);
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(SceneFactory.switchTo(Session.getSession().getCurrView()));
	}

}
