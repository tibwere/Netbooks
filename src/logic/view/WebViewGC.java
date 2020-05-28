package logic.view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.bean.ReaderBean;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.Session;
import logic.util.enumeration.Vendors;

/**
 * Controller grafico relativo alla schermata di acquisto libro (webview)
 * [file fxml associato: web_view.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class WebViewGC implements Initializable {
	
	@FXML
	private WebView webView;
	
	@FXML
	private VBox parentBox;
	
	private BookBean bean;
	private BuyBookGC buyBookGC;
	private Vendors vendor;
	
	public WebViewGC(BookBean bean, Vendors vendor, BuyBookGC buyBookGC) {
		this.bean = bean;
		this.buyBookGC = buyBookGC;
		this.vendor = vendor;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		String link = bean.getLinks().get(vendor);
		WebEngine engine = webView.getEngine();
		engine.load(link);		
	}
	
	@FXML
	private void confirmPurchase() {
		try {
			Optional<ButtonType> results = GraphicalElements.showDialog(AlertType.CONFIRMATION, "Do you wanna confim purchase?");
			if (results.get().equals(ButtonType.OK)) {
				BuyBookSystem.getInstance().addBookToOwnedList(bean, new ReaderBean(Session.getSession().getCurrUser()));
				GraphicalElements.showDialog(AlertType.INFORMATION, 
						"Congratulations!\n\"" + bean.getTitle() + "\" has been added to your list!");
				
				Stage currStage = (Stage) webView.getScene().getWindow();
				currStage.close();
				buyBookGC.getBackToHome();
			}
		} catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		} catch (AlreadyOwnedBookException e) {
			GraphicalElements.showDialog(AlertType.WARNING, e.getMessage());
			Stage currStage = (Stage) webView.getScene().getWindow();
			currStage.close();
			buyBookGC.getBackToHome();
		}
	}
	
	@FXML
	private void closeStage() {
		Optional<ButtonType> results = GraphicalElements.showDialog(AlertType.CONFIRMATION, "Do you wanna exit right now?");
		if (results.get().equals(ButtonType.OK)) {		
			Stage currStage = (Stage) webView.getScene().getWindow();
			currStage.close();
		}
	}
	
	@FXML
	private void reportLink() {
		GraphicalElements.showWorkInProgressDialog();
	}
	

}
