package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.bean.NotificationBean;
import logic.controller.ExchangeBookController;
import logic.exception.PersistencyException;
import logic.util.GraphicalElements;
import logic.util.enumeration.DynamicElements;

public class ManageProposalsGC implements Initializable {
	
	@FXML
	private VBox noticeboard;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			ExchangeBookController controller = new ExchangeBookController();
			List<NotificationBean> beans = controller.getCurrUserNotifications();
			
			for (NotificationBean bean : beans) {
				NotificationItemGC gc = new NotificationItemGC(bean);
				FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.NOTIFICATION_ITEM);
				loader.setController(gc);
				HBox notifItem = loader.load();
				noticeboard.getChildren().add(notifItem);
			}
		}
		catch (PersistencyException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
			Platform.exit();
		} catch (IOException | IllegalStateException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to load graphics for notifications");
			Platform.exit();
		}
	}

}
