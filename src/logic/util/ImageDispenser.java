package logic.util;

import javafx.scene.image.Image;
import logic.util.enumeration.ImageSizes;

/**
 * Classe di ingegnerizzazione del sistema che permette
 * di istanziare {@link Image} a partire da un path che e' esprimibile
 * mediante costanti pubbliche (non vengono utilizzati quindi in altri punti
 * del codice i riferimenti ai file effettivi)
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ImageDispenser {
	
	private static final String PATH = "../view/resources/images/";
	
	public static final String BOOK1 = "dante";
	public static final String BOOK2 = "gatsby";
	public static final String BOOK_TEST_THUMBNAIL = "book_test_thumbnail";
	public static final String BOOK_TEST = "book_test";
	public static final String ICON = "icon";
	public static final String LIKE = "like";
	public static final String LOADING = "loading";
		
	private ImageDispenser() {/*nothing to do here*/}
	
	public static String getImage(String name) {
		if (name.equals(LOADING))
			return ImageDispenser.class.getResource(PATH + "loading.gif").toExternalForm();
		
		return ImageDispenser.class.getResource(PATH + name + ".png").toExternalForm();
	}
	
	public static String getCovers(String name, ImageSizes type) {
		
		StringBuilder path = new StringBuilder(AppProperties.getInstance().getProperty("coverspath"));
		path.append(name.replace(' ', '_').toLowerCase());
		
		switch(type) {
		case SMALL: path.append("/s.jpg"); break;
		case MEDIUM: path.append("/m.jpg"); break;
		default: path.append("/l.jpg"); break; /* LARGE */
		}
		
		return path.toString();
	}
	
}
