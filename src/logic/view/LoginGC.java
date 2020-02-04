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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.bean.AbstractUserBean;
import logic.controller.LoginController;
import logic.controller.Session;
import logic.exception.WrongSyntaxException;
import logic.util.Scenes;
import logic.util.enumeration.UserType;

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
	private AnchorPane pane;
	
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
			
			switch (type) {
			case READER:
				stage = (Stage) pane.getScene().getWindow();
				stage.setScene(Scenes.switchTo(Session.getSession().getCurrView()));
				break;
				
			case RETAILER: 		
				resultLbl.getStyleClass().clear();
				resultLbl.getStyleClass().add("success");
				resultLbl.setText("SUCCESFULLY LOGGED [RETAILER]");	
				break;
				
			default:
				resultLbl.getStyleClass().add("error");
				resultLbl.setText("LOGIN FAILED");
				break;
			}
		}
		catch(WrongSyntaxException e) {
			resultLbl.getStyleClass().add("error");
			resultLbl.setText(e.getMessage().toUpperCase());	
		} 
	}
}
