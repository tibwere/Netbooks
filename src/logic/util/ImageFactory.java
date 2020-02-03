package logic.util;

import javafx.scene.image.Image;

public class ImageFactory {
	
	private static final String PATH = "../view/resources/images/";
	
	public static final String AMAZON_BTN = "amazonbtn";
	public static final String BOOK_TEST_THUMBNAIL = "book_test_thumbnail";
	public static final String BOOK_TEST = "book_test";
	public static final String BUY = "buy";
	public static final String FIND_STORE = "find_store";
	public static final String FORUM = "forum";
	public static final String HELP = "help";
	public static final String HOME = "home";
	public static final String ICON = "icon";
	public static final String LOGO = "logo";
	public static final String LOGOUT = "logout";
	public static final String MONDADORI_BTN = "mondadoribtn";
	public static final String PLAY_BTN = "playbtn";
	public static final String PROFILE = "profile";
	public static final String RATE = "rate";
	public static final String EXCHANGE = "transfer";
	public static final String WALLPAPER = "wallpaper";
		
	private ImageFactory() {/*nothing to do here*/}
	
	public static Image getImage(String name) {
		return new Image(ImageFactory.class.getResourceAsStream(PATH + name + ".png"));
	}	
	
}
