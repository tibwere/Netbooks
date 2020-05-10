package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.bean.ReaderBean;
import logic.controller.buybooksystem.BuyBookFacade;
import logic.controller.buybooksystem.BuyBookSystem;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.Session;
import logic.util.enumeration.Views;
import logic.view.bpobserver.impl.BookPreviewPanel;
import logic.view.bpobserver.impl.ObservableBookList;

/**
 * Controller grafico relativo alla homepage dell'applicazione
 * [file fxml associato: home.fxml]
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class HomeGC implements Initializable {
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private BorderPane pane;
	
	@FXML
	private TextField searchTxt;
	
	@FXML
	private CheckBox ownedBooksChk;
	
	private BookPreviewPanel bookPanel;
	private ObservableBookList obs;
	private BuyBookFacade system;
	
	public HomeGC() {
		try {
			this.system = BuyBookSystem.getInstance();
			this.obs = new ObservableBookList(system.getNotOwnedBooks(new ReaderBean(Session.getSession().getCurrUser())));
			this.bookPanel = new BookPreviewPanel(obs, this);
			this.obs.attach(bookPanel);
		} catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		} 

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			scrollPane.setContent(bookPanel);
			obs.notifyObservers();
		} catch (IOException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load book for homepaage");
			Platform.exit();
		}
		
		handleChangeListeners();
	}
	
	private void handleChangeListeners() {
		ownedBooksChk.selectedProperty().addListener(new ChangeListener<Boolean>() {
			
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					if (Boolean.TRUE.equals(newValue)) {
							obs.setBooks(system.getNotOwnedBooks(new ReaderBean(Session.getSession().getCurrUser())));
					} 
					else {
						obs.setBooks(system.getAllBooks());
					}
				}catch (PersistencyException e) {
					GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
					Platform.exit();
				}
			}
		
		});
	}

	@FXML
	private void searchBook() {
		try {
			if (!searchTxt.getText().equals("")) {
				this.obs.setBooks(system.getSearchedBooks(searchTxt.getText()));
				this.obs.notifyObservers();
			}	
		} catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		} catch (IOException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load results of search");
			Platform.exit();
		}	
	}
	
	@FXML
	private void reloadPage() {
		try {
			obs.notifyObservers();
		} catch (IOException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to reload homepage and apply selected filters");
			Platform.exit();
		}
	}
	
	public void refresh() {
		Stage stage = (Stage) pane.getScene().getWindow();
		stage.setScene(GraphicalElements.switchTo(Views.HOME, null));
	}
	
	
	@FXML
	private void notImplementedFunctions() {
		GraphicalElements.showDialog(AlertType.INFORMATION, "Disabled element will be implemented in future releases");
	}

}
