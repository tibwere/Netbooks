package logic.util;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class NotificationButton {
	
	private NotificationButton() {
		
	}
	
	public static Button chooseButton() {
		Button btn = NotificationButton.initializeButton();
		btn.setText("CHOOSE A BOOK");
		btn.setTextFill(Color.WHITE);
		btn.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		
		return btn;
	}
	
	public static Button acceptButton() {
		Button btn = NotificationButton.initializeButton();
		btn.setText("ACCEPT");
		btn.setTextFill(Color.WHITE);
		btn.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		
		return btn;
	}
	
	private static Button initializeButton() {
		Button btn = new Button();
		btn.setPrefWidth(140);
		btn.setStyle("-fx-font-weight: bold;");
		btn.setCursor(Cursor.HAND);
		
		return btn;
	}
}
