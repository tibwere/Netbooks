package logic.bean;

import javafx.scene.image.Image;

/**
 * Bean per la migrazione dei dati relativi ai libri
 * fra il layer di view ed il layer di controller
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class BookBean {
	
	private Image image;
	private String title;
	private String author;
	
	public BookBean() {}
	
	public BookBean(String title, String author, Image image) {
		this.title = title;
		this.author = author;
		this.image = image;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
