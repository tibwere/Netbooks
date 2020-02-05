package logic.util;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.util.enumeration.FXMLElements;

public class GraphicalElements {
	
	private static final String PATH = "../view/resources/fxml/";
	
	private GraphicalElements() { /*nothing to do here*/ }

	public static FXMLLoader loadFXML(FXMLElements state) throws IOException {
		
		switch(state) {
	
		case LOGIN: 
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "login.fxml"));			
		case BUY_BOOK: 
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "buy_book.fxml"));
		case EXCHANGE_BOOK:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "exchange_book.fxml"));
		case NAVBAR:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "navbar.fxml"));
		case KBSAS:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "kbsas.fxml"));
		case HP_BOOK_PREVIEW:
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "hp_book_preview.fxml"));
		default: /* case HOME */
			return new FXMLLoader(GraphicalElements.class.getResource(PATH + "home.fxml"));
		}
	}
	
	public static Scene switchTo(FXMLElements state, Initializable controller) {
		
		try {
			BorderPane pane;
			
			switch(state) {
			case LOGIN:
				return new Scene(loadFXML(state).load());
			case BUY_BOOK:
				FXMLLoader loader = loadFXML(state);
				loader.setController(controller);
				pane = loader.load();
				pane.setTop(loadFXML(FXMLElements.NAVBAR).load());
				return new Scene(pane);
			default:
				pane = loadFXML(state).load();
				pane.setTop(loadFXML(FXMLElements.NAVBAR).load());
				return new Scene(pane);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return new Scene(create404Page(state.toString().toLowerCase()));			
		}
	}
	
	private static VBox create404Page(String view) {
		
		String style = "-fx-text-fill: white;";
		VBox box = new VBox();
		
		box.getStylesheets().add(GraphicalElements.class.getResource("../view/resources/css/style.css").toExternalForm());
		box.getStyleClass().add("bg");
		
		Label header = new Label("404 - Page not found");
		header.setFont(new Font(64));
		header.setStyle(style);
		
		Label content = new Label("unable to load " + view + " file");
		content.setFont(new Font(24));
		content.setStyle(style);
		
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(header, content);
		
		return box;
	}
	
	public static Optional<ButtonType> showDialog(AlertType type, String header, String content) {
		Alert alert = new Alert(type);
		
		Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
		alertStage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
		
		alert.setTitle("Netbooks v1.0");
		alert.setHeaderText(header);
		alert.setContentText(content);
		return alert.showAndWait();
	}
}
