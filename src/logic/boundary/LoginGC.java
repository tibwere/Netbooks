package logic.boundary;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import logic.bean.AbstractUserBean;
import logic.control.LoginController;
import logic.enumeration.UserType;
import logic.exception.WrongSyntaxException;

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
	
	private LoginController controller;
	
	public LoginGC(LoginController controller) {
		this.controller = controller;
	}
	
	@FXML
	public void tryLogin() {
		
		try {
			String username = usernameTxt.getText();
			String password = passwordTxt.getText();
			AbstractUserBean bean = new AbstractUserBean(username, password);

			UserType type = controller.loginUser(bean);
			
			switch (type) {
			case READER:
				resultLbl.getStyleClass().clear();
				resultLbl.getStyleClass().add("success");
				resultLbl.setText("SUCCESFULLY LOGGED [READER]");	
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/*nothing to do here */
		
	}
}
