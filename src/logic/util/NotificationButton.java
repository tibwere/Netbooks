package logic.util;

import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

/**
 * 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class NotificationButton {
	
	private NotificationButton() {
		/* non istanziabile */
	}
	
	public static Button chooseButton() {
		Button btn = NotificationButton.initializeButton();
		btn.setText("CHOOSE A BOOK");
		btn.setStyle("-fx-font-weight: bold;"+"-fx-background-color: blue;");
		
		return btn;
	}
	
	public static Button acceptButton() {
		Button btn = NotificationButton.initializeButton();
		btn.setText("ACCEPT");
		btn.setStyle("-fx-font-weight: bold;"+"-fx-background-color: green;");
		
		return btn;
	}
	
	private static Button initializeButton() {
		Button btn = new Button();
		btn.setPrefWidth(140);
		btn.setCursor(Cursor.HAND);
		btn.setTextFill(Color.WHITE);
		
		return btn;
	}
}
