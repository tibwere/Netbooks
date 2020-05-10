package logic.view;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import logic.bean.ReaderBean;
import logic.controller.LoginController;
import logic.exception.UserAlreadySignedException;
import logic.exception.WrongSyntaxException;
import logic.util.GraphicalElements;
import logic.util.Session;
import logic.util.enumeration.Views;

/**
 * Controller grafico relativo alla schermata di signup del reader
 * [file fxml associato: signupreader.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class SignupReaderGC implements Initializable {

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField surnametxt;

    @FXML
    private TextField usernameTxt;

    @FXML
	private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    private PasswordField confPasswordTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField cityTxt;

    @FXML
    private TextField zipTxt;

    @FXML
    private TextField countryTxt;

    @FXML
    private RadioButton maleRadio;

    @FXML
    private RadioButton femaleRadio;
    
    @FXML
    private Label errLbl;
    
    private ToggleGroup group;
    private Stage parentStage;
    
	public SignupReaderGC(Stage s) {
		this.parentStage = s;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		group = new ToggleGroup();
		maleRadio.setToggleGroup(group);
		femaleRadio.setToggleGroup(group);
		maleRadio.setSelected(true);
	}
	
	@FXML
	public void closeModal() {
		Stage currStage = (Stage) nameTxt.getScene().getWindow();
		currStage.close();
	}

    @FXML
    void trySignupReader() {
    	
    	if (!passwordTxt.getText().equals(confPasswordTxt.getText()))
    		errLbl.setText("MISMATCH PASSWORD");
    	
    	else {
    		try {
    			ReaderBean reader = new ReaderBean();
    			reader.setFirstName(nameTxt.getText());
    			reader.setSecondName(surnametxt.getText());
    			reader.setUsername(usernameTxt.getText());
    			reader.setEmail(emailTxt.getText());
    			reader.setPassword(passwordTxt.getText());
    			reader.setAddress(addressTxt.getText());
    			reader.setCity(cityTxt.getText());
    			reader.setCountry(countryTxt.getText());
    			reader.setZip(zipTxt.getText());
    			reader.setFemale(group.getSelectedToggle().equals(femaleRadio));    				
    			
    			LoginController ctrl = new LoginController();
    			ctrl.signup(reader);
    			Session.getSession().setCurrUser(reader.getUsername());
    			
    			GraphicalElements.showDialog(AlertType.INFORMATION, "Reader succesfully signed up!");
    			parentStage.setScene(GraphicalElements.switchTo(Views.HOME, null));
    			
    			Stage curr = (Stage) addressTxt.getScene().getWindow();
    			curr.hide();
   				
    		} catch (UserAlreadySignedException | WrongSyntaxException | NoSuchAlgorithmException e) {
    			errLbl.setText(e.getMessage().toUpperCase());
    		} catch (IOException e) {
				errLbl.setText("UNABLE TO CONNECT TO MAPS SERVICE");
			}
    	}
    }
    
    @FXML
    public void resetLbl() {
    	errLbl.setText("");
    }
}



