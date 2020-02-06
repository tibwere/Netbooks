package logic;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.util.AppProperties;
import logic.util.GraphicalElements;
import logic.util.ImageDispenser;
import logic.util.enumeration.Views;

/**
 * Entry point dell'applicazione stand-alone 
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class DesktopLauncher extends Application {
	
	@Override
	public void start(Stage stage) {
			
		Scene scene = GraphicalElements.switchTo(Views.LOGIN, null);
		
 		initStage(stage, scene);
		stage.show();	
	}
	
	private void initStage(Stage stage, Scene scene) {
		stage.setTitle(AppProperties.getInstance().getProperty("title"));
		
		stage.setWidth(Integer.valueOf(AppProperties.getInstance().getProperty("width")));
		stage.setHeight(Integer.valueOf(AppProperties.getInstance().getProperty("height")));
		stage.setResizable(Boolean.valueOf(AppProperties.getInstance().getProperty("resize")));		
		stage.centerOnScreen();
		stage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
		stage.setScene(scene);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
