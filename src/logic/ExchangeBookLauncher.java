package logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.util.ImageDispenser;
import logic.util.Scenes;
import logic.util.enumeration.Views;
public class ExchangeBookLauncher extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Scene scene = Scenes.switchTo(Views.EXCHANGE_BOOK);
							
		initStage(stage, scene);
		stage.show();	
	}
	
	private void initStage(Stage stage, Scene scene) {
		stage.setTitle("Netbooks v1.0");
		
		stage.setWidth(1000);
		stage.setHeight(800);
		stage.setResizable(false);		
		stage.centerOnScreen();
		stage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
		stage.setScene(scene);
	}

	public static void main(String[] args) {
		launch(args);
	}

}