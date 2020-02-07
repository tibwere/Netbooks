package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.AbstractUserBean;
import logic.controller.LoginController;
import logic.exception.WrongSyntaxException;
import logic.util.GraphicalElements;
import logic.util.enumeration.UserType;
import logic.util.enumeration.Views;

/**
 * Controller grafico collegato al file "login.fxml" 
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
	
	private LoginController controller;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		controller = new LoginController();
	}
	
	@FXML
	public void tryLogin() throws IOException {
		
		try {
			Stage stage;
			String username = usernameTxt.getText();
			String password = passwordTxt.getText();
			AbstractUserBean bean = new AbstractUserBean(username, password);

			UserType type = controller.loginUser(bean);
			
			if(type.equals(UserType.INVALID_USER)) {
				resultLbl.getStyleClass().add("error");
				resultLbl.setText("LOGIN FAILED");
			}
			else {
				stage = (Stage) pane.getScene().getWindow();
				if (type.equals(UserType.READER))
					stage.setScene(GraphicalElements.switchTo(Views.HOME, null));
				else
					stage.setScene(GraphicalElements.switchTo(Views.KBSAS, null));
			}
		}
		catch(WrongSyntaxException e) {
			resultLbl.getStyleClass().add("error");
			resultLbl.setText(e.getMessage().toUpperCase());	
		} 
	}
}
