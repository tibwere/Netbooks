package logic.util;

import javafx.scene.image.Image;

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
	
	public static Image getImage(String name) {
		if (name.equals(LOADING))
			return new Image(ImageDispenser.class.getResourceAsStream(PATH + "loading.gif"));
		
		return new Image(ImageDispenser.class.getResourceAsStream(PATH + name + ".png"));
	}	
	
}
