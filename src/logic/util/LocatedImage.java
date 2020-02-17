package logic.util;

import javafx.scene.image.Image;

public class LocatedImage extends Image {
	
	private String path;

	public LocatedImage(String url) {
		super(url);
		this.path = url;
	}
	
	public String getPath() {
		return path;
	}
}
