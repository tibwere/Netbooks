package logic.bean;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import logic.util.enumeration.ImageSize;

/**
 * Bean per la migrazione dei dati relativi ai libri
 * fra il layer di view ed il layer di controller
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0258093)
 */
public class BookBean {
	
	private String isbn;
	private String title;
	private String author;
	private int yearOfPublication;
	private String publisher;
	private String language;
	private String owner;

	private Map<ImageSize, Image> images;
	
	public BookBean() {
		this.images = new HashMap<>();
	}
	
	public BookBean(String title, String author) {
		this.images = new HashMap<>();
		this.title = title;
		this.author = author;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getYearOfPublication() {
		return yearOfPublication;
	}

	public void setYearOfPublication(int yearOfPublication) {
		this.yearOfPublication = yearOfPublication;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Image getSmallImage() {
		return images.get(ImageSize.SMALL);
	}

	public void setSmallImage(Image smallImage) {
		this.images.put(ImageSize.SMALL, smallImage);
	}

	public Image getMediumImage() {
		return images.get(ImageSize.MEDIUM);
	}

	public void setMediumImage(Image mediumImage) {
		this.images.put(ImageSize.MEDIUM, mediumImage);
	}

	public Image getLargeImage() {
		return images.get(ImageSize.LARGE);
	}

	public void setLargeImage(Image largeImage) {
		this.images.put(ImageSize.LARGE, largeImage);
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		return "[" + isbn + " " + title + " "+ author + " " + yearOfPublication + " " + publisher + " " + language + "]"; 
	}
}
