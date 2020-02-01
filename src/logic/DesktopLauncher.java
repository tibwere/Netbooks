package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.boundary.LoginGC;
import logic.control.LoginController;

/**
 * Entry point dell'applicazione stand-alone 
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class DesktopLauncher extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(DesktopLauncher.class.getResource("resources/fxml/login.fxml"));
		
		LoginController controller = new LoginController();
		LoginGC gc = new LoginGC(controller);
		loader.setController(gc);
		Parent root = loader.load();
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Netbooks v1.0");
		primaryStage.setResizable(false);		
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.getIcons().add(new Image(DesktopLauncher.class.getResourceAsStream("resources/images/icon.png")));
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
