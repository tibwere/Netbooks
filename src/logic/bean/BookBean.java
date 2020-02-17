package logic.bean;

import java.util.HashMap;
import java.util.Map;

import logic.util.enumeration.ImageSizes;
import logic.util.enumeration.Vendors;

/**
 * Bean per la migrazione dei dati relativi ai libri
 * fra il layer di view ed il layer di controller
 * @author Simone Tiberi (M. 0252795)
 * 
 */
public class BookBean {
	
	private String isbn;
	private String title;
	private String author;
	private int yearOfPublication;
	private String publisher;
	private String language;

	private Map<ImageSizes, String> images;
	private Map<Vendors, String> links;
	
	public BookBean() {
		this.images = new HashMap<>();
		this.links = new HashMap<>();
	}
	
	public BookBean(String title, String author) {
		this();
		this.title = title;
		this.author = author;
	}
	
	public BookBean(String title, String author, String singleImage, ImageSizes size) {
		this();
		this.setTitle(title);
		this.setAuthor(author);
		this.setSingleImage(singleImage, size);		
	}
	
	public Map<Vendors, String> getLinks() {
		return links;
	}

	public void setLinks(Map<Vendors, String> links) {
		this.links = links;
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
	
	public Map<ImageSizes, String> getAllImages() {
		return this.images;
	}
	
	public void setAllImages(Map<ImageSizes, String> images) {
		this.images = images;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSingleImage(ImageSizes size) {
		return images.get(size);
	}
	
	public void setSingleImage(String image, ImageSizes size) {
		this.images.put(size, image);
	}
}
