package logic.view.navbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import logic.util.ImageFactory;
import logic.util.enumeration.ImageSet;

/**
 * Navbar secondaria dell'applicazione.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class TrayNavbar extends MenuBar { 
	
	private Menu helpMenu;
	
	private Menu logoutMenu;
	
	private Menu profileMenu;

	public TrayNavbar() {
		initMenus();
	}

	private void initMenus() {
		helpMenu = new Menu("");
		helpMenu.setGraphic(new ImageView(ImageFactory.getImage(ImageSet.HELP)));
		
		profileMenu = new Menu("");
		profileMenu.setGraphic(new ImageView(ImageFactory.getImage(ImageSet.PROFILE)));
		
		logoutMenu = new Menu("");
		logoutMenu.setGraphic(new ImageView(ImageFactory.getImage(ImageSet.LOGOUT)));
		
		this.getMenus().addAll(helpMenu, profileMenu, logoutMenu);
	}

}
