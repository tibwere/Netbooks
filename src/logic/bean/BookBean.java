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
	
	public BookBean(String title, String author, Image singleImage, ImageSize size) {
		this.images = new HashMap<>();
		this.setTitle(title);
		this.setAuthor(author);
		this.setSingleImage(singleImage, size);		
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
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
	
	public Map<ImageSize, Image> getAllImages() {
		return this.images;
	}
	
	public void setAllImages(Map<ImageSize, Image> images) {
		this.images = images;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Image getSingleImage(ImageSize size) {
		return images.get(size);
	}
	
	public void setSingleImage(Image image, ImageSize size) {
		this.images.put(size, image);
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
