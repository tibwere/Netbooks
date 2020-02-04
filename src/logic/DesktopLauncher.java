package logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.controller.Session;
import logic.util.ImageDispenser;
import logic.util.Scenes;

/**
 * Entry point dell'applicazione stand-alone 
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class DesktopLauncher extends Application {
	
	@Override
	public void start(Stage stage) {
			
		Scene scene = Scenes.switchTo(Session.getSession().getCurrView());
		
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
