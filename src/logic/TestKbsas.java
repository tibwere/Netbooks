package logic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.util.ImageDispenser;



public class TestKbsas extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(TestKbsas.class.getResource("view/resources/fxml/kbsas.fxml"));
		
		BorderPane borderPane = loader.load();
		
		
		
		
//		da aggiungere navbar
		
		
		Scene scene = new Scene(borderPane);

		setupStage(primaryStage, scene);
		primaryStage.show();	
	}
	
	private void setupStage(Stage stage, Scene scene) {
		
		stage.setTitle("Netbooks v1.0");
		stage.setResizable(false);		
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.getIcons().add(ImageDispenser.getImage(ImageDispenser.ICON));
	}

	public static void main(String[] args) {
		launch(args);
	}

}

