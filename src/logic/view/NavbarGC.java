package logic.view;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.ReaderBean;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.Session;
import logic.util.enumeration.Views;

/**
 * Controller grafico relativo alla navbar dell'applicazione (user: READER)
 * [file fxml associato: navbar.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class NavbarGC {
	
	@FXML
	private BorderPane main;

	@FXML
	private Button homeBtn;
	
	@FXML
	private Button forumBtn;
	
	@FXML
	private Button exchangeBtn;
	
	@FXML
	private Button profileBtn;
	
	@FXML
	private Button logoutBtn;
	
	public void updateGenerality() {
		try {
			ReaderBean bean = new BuyBookSystem().getReaderGenerality(new ReaderBean(Session.getSession().getCurrUser()));
	
			if (bean.getFirstName().length() + bean.getSecondName().length() < 20)
				profileBtn.setText(bean.getFirstName() + " " + bean.getSecondName());
			else
				profileBtn.setText(bean.getFirstName().charAt(0) + ". " + bean.getSecondName());
		} catch (PersistencyException | NotAccesibleConfigurationException e) {
			profileBtn.setText("");
		}
	}

	public void disableBtns() {
		switch(Session.getSession().getCurrView()) {
		case HOME:
			homeBtn.setDisable(true);
			exchangeBtn.setDisable(false);
			break;
		case EXCHANGE_BOOK:
			exchangeBtn.setDisable(true);
			homeBtn.setDisable(false);
			break;
		default:
			exchangeBtn.setDisable(false);
			homeBtn.setDisable(false);
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
	
	@FXML
	public void goToForum() {
		GraphicalElements.showWorkInProgressDialog();
	}
	
	@FXML
	public void goToProfile() {
		GraphicalElements.showWorkInProgressDialog();
	}
	
	public void doLogout() {
						
		Optional<ButtonType> result = GraphicalElements.showDialog(AlertType.CONFIRMATION, "Are you sure do you want to exit?");
		
		if (result.get().equals(ButtonType.OK)) {
			contextSwitch(Views.LOGIN);
		}
	}
	
	private void contextSwitch(Views nextView) {
		Stage stage = (Stage) main.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(nextView, null));
	}

}
