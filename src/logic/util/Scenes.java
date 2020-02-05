package logic.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.util.enumeration.Views;

public class Scenes {
	
	private static final String PATH = "../view/resources/fxml/";
	
	private Scenes() { /*nothing to do here*/ }

	private static FXMLLoader loadFXML(Views state) throws IOException {
		
		switch(state) {
	
		case LOGIN: 
			return new FXMLLoader(Scenes.class.getResource(PATH + "login.fxml"));			
		case BUY_BOOK: 
			return new FXMLLoader(Scenes.class.getResource(PATH + "buy_book.fxml"));
		case EXCHANGE_BOOK:
			return new FXMLLoader(Scenes.class.getResource(PATH + "exchange_book.fxml"));
		case NAVBAR:
			return new FXMLLoader(Scenes.class.getResource(PATH + "navbar.fxml"));
		case KBSAS:
			return new FXMLLoader(Scenes.class.getResource(PATH + "kbsas.fxml"));
		default: /* case HOME */
			return new FXMLLoader(Scenes.class.getResource(PATH + "home.fxml"));
		}
	}
	
	public static Scene switchTo(Views state) {
		
		try {
			
			if (state.equals(Views.LOGIN))
				return new Scene(loadFXML(state).load());
			else {
				BorderPane pane = loadFXML(state).load();
				pane.setTop(loadFXML(Views.NAVBAR).load());
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
		
		box.getStylesheets().add(Scenes.class.getResource("../view/resources/css/style.css").toExternalForm());
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
}
