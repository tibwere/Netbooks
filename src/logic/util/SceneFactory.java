package logic.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import logic.util.enumeration.Views;

public class SceneFactory {
	
	private static final String PATH = "../view/resources/fxml/";
	
	private SceneFactory() { /*nothing to do here*/ }

	private static FXMLLoader loadFXML(Views state) throws IOException {
		
		switch(state) {
	
		case LOGIN: 
			return new FXMLLoader(SceneFactory.class.getResource(PATH + "login.fxml"));
		case HOME:
			return new FXMLLoader(SceneFactory.class.getResource(PATH + "home.fxml"));
		case BUY_BOOK: 
			return new FXMLLoader(SceneFactory.class.getResource(PATH + "buy_book.fxml"));
		case EXCHANGE_BOOK:
			return new FXMLLoader(SceneFactory.class.getResource(PATH + "exchangeBook.fxml"));
		case TRAY_NAVBAR:
			return new FXMLLoader(SceneFactory.class.getResource(PATH + "tray_navbar.fxml"));
		case MAIN_NAVBAR:
			return new FXMLLoader(SceneFactory.class.getResource(PATH + "main_navbar.fxml"));
		default: 
			return null;
		}
	}
	
	public static Scene switchTo(Views state) {
		
		try {
			switch(state) {
			case LOGIN:
				return new Scene(loadFXML(state).load());
			default:
				BorderPane pane = loadFXML(state).load();
				pane.setTop(createNavBar());
				return new Scene(pane);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private static HBox createNavBar() throws IOException {
		HBox main = loadFXML(Views.MAIN_NAVBAR).load();
		HBox tray = loadFXML(Views.TRAY_NAVBAR).load();
        Region spacer = new Region();
             
		HBox box = new HBox();
		box.setPrefWidth(1000);
        
        spacer.getStyleClass().add("menu-bar");
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        
        box.getChildren().addAll(main, spacer, tray);
        
        return box;
	}
}
