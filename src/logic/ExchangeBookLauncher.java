package logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.util.ImageFactory;
import logic.util.SceneFactory;
import logic.util.enumeration.Views;
public class ExchangeBookLauncher extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Scene scene = SceneFactory.switchTo(Views.EXCHANGE_BOOK);
							
		initStage(stage, scene);
		stage.show();	
	}
	
	private void initStage(Stage stage, Scene scene) {
		stage.setTitle("Netbooks v1.0");
		
		stage.setWidth(1000);
		stage.setHeight(800);
		stage.setResizable(false);		
		stage.centerOnScreen();
		stage.getIcons().add(ImageFactory.getImage(ImageFactory.ICON));
		stage.setScene(scene);
	}

	public static void main(String[] args) {
		launch(args);
	}

}