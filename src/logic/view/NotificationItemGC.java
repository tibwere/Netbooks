package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.bean.BookBean;
import logic.bean.NotificationBean;
import logic.bean.ReaderBean;
import logic.controller.ExchangeBookController;
import logic.exception.NoStateTransitionException;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.NotificationButton;
import logic.util.Session;
import logic.util.enumeration.DynamicElements;
/**
 * Controller grafico relativo ad una notifica di scambio
 * nella schermata di gestione delle proposte
 * [file fxml associato: notification_item.fxml] 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class NotificationItemGC implements Initializable{
	
	@FXML
	private HBox mainHB;
	
	@FXML
	private Label label;
	
	@FXML
	private HBox hbox;
	
	@FXML
	private Button rejectBtn;
	
	private Button dynamicBtn;
	
	private NotificationBean bean;
	
	private ReaderBean currReader;
	
	private ExchangeBookController controller = new ExchangeBookController();
	
	public NotificationItemGC(NotificationBean bean) throws NotAccesibleConfigurationException {
		this.bean = bean;
		this.currReader = new ReaderBean(Session.getSession().getCurrUser());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.setText(bean.getMessage());
		switch (bean.getType()) {
		case INITIAL_PROPOSAL:
			setChooseButton();			
			hbox.getChildren().add(0, dynamicBtn);
			break;
		case INTERMEDIATE_PROPOSAL:
			setAcceptButton();
			hbox.getChildren().add(0, dynamicBtn);
			break;
		default:
			break;
		}
	}
	
	public void chooseBook (BookBean b) {
		try {
			controller.acceptProposal(bean, b, currReader);
		
			dynamicBtn.setDisable(true);
			rejectBtn.setDisable(true);
			rejectBtn.setVisible(false);
		
			controller.removeNotification(bean, currReader);
		} catch (PersistencyException | NoStateTransitionException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		}
	}
	
	@FXML
	private void clickOnReject() {
		
		try {
			ExchangeBookController c = new ExchangeBookController();
			c.failureNotification(bean);
			
			rejectBtn.setDisable(true);
			dynamicBtn.setDisable(true);
			dynamicBtn.setVisible(false);
			
			controller.removeNotification(bean, currReader);
		} catch (PersistencyException | NoStateTransitionException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		}
	}
	
	private void setChooseButton() {
		rejectBtn.setDisable(false);
		rejectBtn.setVisible(true);
		dynamicBtn = NotificationButton.chooseButton();
		NotificationItemGC me = this;
		dynamicBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					ExchangeBookController ctrl = new ExchangeBookController();
					Stage parent = (Stage) mainHB.getScene().getWindow();
					
					ScrollPane root = new ScrollPane();
					root.getStylesheets().add(getClass().getResource("resources/css/style.css").toExternalForm());
					
					VBox vbox = new VBox();
					vbox.setSpacing(10);
					vbox.setMinHeight(400);
					vbox.setAlignment(Pos.TOP_CENTER);
					
					List<BookBean> bookBeanList = ctrl.getUserBooks(new ReaderBean(bean.getSourceId()));
									
					for (BookBean bookBean : bookBeanList) {
						ExchangeBookPopUpItemGC gc = new ExchangeBookPopUpItemGC(bookBean, me);
						FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.EXCHANGE_BOOK_POPUP_ITEM);
						loader.setController(gc);
						HBox bookItem = loader.load();
						vbox.getChildren().add(bookItem);						
					}
					vbox.getStylesheets().add(getClass().getResource("resources/css/style.css").toExternalForm());
					vbox.getStyleClass().add("exBookPanebg");
					root.setContent(vbox);
					
					Scene popupScene = new Scene(root, 360, 400);
					Stage popupStage = GraphicalElements.createModalWindow(popupScene, parent);
					popupStage.show();
				}
				catch (PersistencyException | NotAccesibleConfigurationException e) {
					GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
					Platform.exit();
				} catch (IOException | IllegalStateException e) {
					GraphicalElements.showDialog(AlertType.ERROR, "Unable to load graphics for notification");
					Platform.exit();
				}
			}
		});
	}
	
	private void setAcceptButton() {
		rejectBtn.setDisable(false);
		rejectBtn.setVisible(true);
		dynamicBtn = NotificationButton.acceptButton();
		dynamicBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				try {
					if (!controller.acceptProposal(bean, null, currReader))
						controller.failureNotification(bean);
									
					dynamicBtn.setDisable(true);
					rejectBtn.setDisable(true);
					rejectBtn.setVisible(false);
					
					controller.removeNotification(bean, currReader);
				} catch (PersistencyException | NoStateTransitionException e) {
					GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
					Platform.exit();
				}
			}
		});
	}

}
