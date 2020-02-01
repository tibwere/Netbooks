package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.controller.LoginController;
import logic.util.ImageFactory;
import logic.util.enumeration.ImageSet;
import logic.view.LoginGC;

/**
 * Entry point dell'applicazione stand-alone 
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class DesktopLauncher extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(DesktopLauncher.class.getResource("view/resources/fxml/login.fxml"));
		
		LoginController controller = new LoginController();
		LoginGC gc = new LoginGC(controller);
		loader.setController(gc);
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		
		setupStage(primaryStage, scene);
		primaryStage.show();	
	}
	
	private void setupStage(Stage stage, Scene scene) {
		
		stage.setTitle("Netbooks v1.0");
		stage.setResizable(false);		
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.getIcons().add(ImageFactory.getImage(ImageSet.ICON));
	}

	public static void main(String[] args) {
		launch(args);
	}

}
