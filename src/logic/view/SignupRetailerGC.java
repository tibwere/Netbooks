package logic.view;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.bean.RetailerBean;
import logic.controller.LoginController;
import logic.exception.UserAlreadySignedException;
import logic.exception.WrongSyntaxException;
import logic.util.GraphicalElements;
import logic.util.enumeration.Views;

public class SignupRetailerGC {
	
    @FXML
    private TextField companyTxt;

    @FXML
    private TextField vatTxt;

    @FXML
    private TextField usernameTxt;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private PasswordField confirmTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private TextField zipTxt;

    @FXML
    private TextField countryTxt;
    
    @FXML
    private Label errLbl;
	
	private Stage parentStage;
	
	public SignupRetailerGC(Stage parentStage) {
		this.parentStage = parentStage;
	}
	
	@FXML
	public void trySignupRetailer() {
    	
    	if (!passwordTxt.getText().equals(confirmTxt.getText()))
    		errLbl.setText("MISMATCH PASSWORD");
    	
    	else {
    		try {
    			RetailerBean reader = new RetailerBean();
    			reader.setCompanyName(companyTxt.getText());
    			reader.setVat(vatTxt.getText());
    			reader.setUsername(usernameTxt.getText());
    			reader.setEmail(emailTxt.getText());
    			reader.setPassword(passwordTxt.getText());
    			reader.setAddress(addressTxt.getText());
    			reader.setCity(cityTxt.getText());
    			reader.setCountry(countryTxt.getText());
    			reader.setZip(zipTxt.getText());
    			
    			LoginController ctrl = new LoginController();
    			ctrl.signup(reader);
    			
    			GraphicalElements.showDialog(AlertType.INFORMATION, "Retailer succesfully signed up!");
    			parentStage.setScene(GraphicalElements.switchTo(Views.KBSAS, null));
    			
    			Stage curr = (Stage) addressTxt.getScene().getWindow();
    			curr.hide();
   				
    		} catch (UserAlreadySignedException | WrongSyntaxException | NoSuchAlgorithmException e) {
    			errLbl.setText(e.getMessage().toUpperCase());
    		} catch (IOException e) {
				errLbl.setText("UNABLE TO CONNECT TO MAPS SERVICE TO GEOLOCALIZE YOU");
			}
    	}
	}

	@FXML
	public void closeModal() {
		Stage currStage = (Stage) companyTxt.getScene().getWindow();
		currStage.close();
	}
	
    @FXML
    public void resetLbl() {
    	errLbl.setText("");
    }

}
