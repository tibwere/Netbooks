package logic.view.navbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import logic.util.ImageFactory;
import logic.util.enumeration.ImageSet;

/**
 * Navbar principale dell'applicazione.
 * Contiene alcuni link alle funzionalità base dell'app
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class MainNavbar extends MenuBar {
	
	private Menu homeMenu;
	
	private Menu findStoreMenu;
	
	private Menu forumMenu;
		
	public MainNavbar() {
		initMenus();
	}
	
	private void initMenus() {
		
		homeMenu = new Menu("Home");
		homeMenu.setGraphic(new ImageView(ImageFactory.getImage(ImageSet.HOME)));
		
		findStoreMenu = new Menu("Find Store");
		findStoreMenu.setGraphic(new ImageView(ImageFactory.getImage(ImageSet.FIND_STORE)));
		
		forumMenu = new Menu("Forum");
		forumMenu.setGraphic(new ImageView(ImageFactory.getImage(ImageSet.FORUM)));
		forumMenu.setGraphic(new ImageView(ImageFactory.getImage(ImageSet.HELP)));		

		
		this.getMenus().addAll(homeMenu, findStoreMenu, forumMenu);		
	}
}
