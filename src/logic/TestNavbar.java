package logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.view.navbar.Navbar;

public class TestNavbar extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Navbar bar = new Navbar(primaryStage);
		VBox box = new VBox(bar);
		
		Scene scene = new Scene(box);
		
		primaryStage.setWidth(1000);
		primaryStage.setResizable(false);		
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		
		primaryStage.show();		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
