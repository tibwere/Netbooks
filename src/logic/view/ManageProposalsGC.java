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
		
			int i;
			
			int len = (beans.size()) % 5;
		
			for (i = 0; i < len; i ++) {
				NotificationItemGC gc = new NotificationItemGC(beans.get(i));
				FXMLLoader loader = GraphicalElements.loadFXML(DynamicElements.NOTIFICATION_ITEM);
				loader.setController(gc);
				HBox notifItem = loader.load();
				noticeboard.getChildren().add(notifItem);
			}
		}
		catch (IOException | IllegalStateException e) {
			
			GraphicalElements.showDialog(AlertType.ERROR, "Error!", "Unable to load exchangeable books.");
			Platform.exit();
		}
	}

}
