package logic;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.db.DBManager;
import logic.exception.NotAccesibleConfigurationException;
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
		
 		try {
			initStage(stage, scene);
		} catch (NotAccesibleConfigurationException e) {
			GraphicalElements.showDialog(AlertType.ERROR, e.getMessage());
		}
		stage.show();	
	}
	
	private void initStage(Stage stage, Scene scene) throws NotAccesibleConfigurationException {
		stage.setTitle(AppProperties.getInstance().getProperty("title"));
		
		stage.setWidth(Integer.valueOf(AppProperties.getInstance().getProperty("width")));
		stage.setHeight(Integer.valueOf(AppProperties.getInstance().getProperty("height")));
		stage.setResizable(Boolean.valueOf(AppProperties.getInstance().getProperty("resize")));		
		stage.centerOnScreen();
		stage.getIcons().add(new Image(ImageDispenser.getImage(ImageDispenser.ICON)));
		stage.setScene(scene);
	}
	
	@Override
	public void stop() {
		try {
			DBManager.closeConnection();
		} catch (SQLException e) {
			GraphicalElements.showDialog(AlertType.ERROR, "Unable to close connection to DB");
		}
	}

	public static void main(String[] args) {
		launch();
	}

}
