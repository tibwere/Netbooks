package logic.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import logic.bean.NotificationBean;

public class NotificationItemGC implements Initializable{
	
	@FXML
	private Label label;
	
	private NotificationBean bean;
	
	public NotificationItemGC(NotificationBean bean) {
		this.bean = bean;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.setText(bean.getMessage());
	}

}
