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
import logic.util.Session;
import logic.util.enumeration.Views;

/**
 * Controller grafico relativo alla schermata di signup del retailer
 * [file fxml associato: signupretalier.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
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
    			RetailerBean retailer = new RetailerBean();
    			retailer.setCompanyName(companyTxt.getText());
    			retailer.setVat(vatTxt.getText());
    			retailer.setUsername(usernameTxt.getText());
    			retailer.setEmail(emailTxt.getText());
    			retailer.setPassword(passwordTxt.getText());
    			retailer.setAddress(addressTxt.getText());
    			retailer.setCity(cityTxt.getText());
    			retailer.setCountry(countryTxt.getText());
    			retailer.setZip(zipTxt.getText());
    			
    			LoginController ctrl = new LoginController();
    			ctrl.signup(retailer);
    			Session.getSession().setCurrUser(retailer.getUsername());
    			
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
