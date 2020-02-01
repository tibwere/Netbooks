package logic.view.navbar;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * Ridefinizione della normale navbar dell'applicazione
 * ottenuta per affiancamento di due menubar distinte.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Navbar extends HBox {
	
	private MainNavbar main;
	private TrayNavbar tray;
	private Region spacer;
	
	public Navbar(Stage stage) {
		main = new MainNavbar();
		tray = new TrayNavbar();
        spacer = new Region();
        
		this.prefWidthProperty().bind(stage.widthProperty());
        
        spacer.getStyleClass().add("menu-bar");
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        
        this.getChildren().addAll(main, spacer, tray);
	}
	
}
