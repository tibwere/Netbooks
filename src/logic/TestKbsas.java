package logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.util.ImageDispenser;
import logic.util.Scenes;
import logic.util.enumeration.Views;

/**
 * Launcher del controller grafico KbsasGC
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class TestKbsas extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		
		Scene scene = Scenes.switchTo(Views.KBSAS);
		
		
		//		da aggiungere navbar
		
	
		initStage(stage, scene);
//		setupStage(primaryStage, scene);
		stage.show();	
	}
	
	private void initStage(Stage stage, Scene scene) {
		
		stage.setWidth(1000);
		stage.setHeight(800);
		stage.setResizable(false);		
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
	}

	public static void main(String[] args) {
		launch(args);
	}

}