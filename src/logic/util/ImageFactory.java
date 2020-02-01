package logic.util;

import javafx.scene.image.Image;
import logic.util.enumeration.ImageSet;

public class ImageFactory {
	
	private static final String PATH = "../view/resources/images/";
		
	private ImageFactory() {/*nothing to do here*/}
	
	public static Image getImage(ImageSet icon) {
		
		switch(icon) {
		case ICON: 
			return new Image(ImageFactory.class.getResourceAsStream(PATH + "icon.png"));
		case FIND_STORE:
			return new Image(ImageFactory.class.getResourceAsStream(PATH + "find.png"));
		case FORUM:
			return new Image(ImageFactory.class.getResourceAsStream(PATH + "forum.png"));
		case HOME: 
			return new Image(ImageFactory.class.getResourceAsStream(PATH + "home.png"));
		case HELP:
			return new Image(ImageFactory.class.getResourceAsStream(PATH + "help.png"));
		case PROFILE:
			return new Image(ImageFactory.class.getResourceAsStream(PATH + "profile.png"));
		case LOGOUT:
			return new Image(ImageFactory.class.getResourceAsStream(PATH + "logout.png"));
		default:
			return null;
		}
	}

}
