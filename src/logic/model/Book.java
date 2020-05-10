package logic.model;

import java.util.HashMap;
import java.util.Map;

import logic.util.enumeration.ImageSizes;
import logic.util.enumeration.Vendors;

/**
 * Entita' del dominio di interesse: Libro
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class Book {
	
	private String isbn;
	private String title;
	private String author;
	private int yearOfPublication;
	private String publisher;
	private String language;
	
	private Map<ImageSizes, String> images;
	private Map<Vendors, String> links;
	
	public Book() {
		images = new HashMap<>();
		links = new HashMap<>();
	}
	
	public Book(String isbn, String title, String author) {
		this();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
	
	public String getIsbn() {
		return isbn;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
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

	public String getSmallImage() {
		return images.get(ImageSizes.SMALL);
	}

	public void setSmallImage(String smallImage) {
		this.images.put(ImageSizes.SMALL, smallImage);
	}

	public String getMediumImage() {
		return images.get(ImageSizes.MEDIUM);
	}

	public void setMediumImage(String mediumImage) {
		this.images.put(ImageSizes.MEDIUM, mediumImage);
	}

	public String getLargeImage() {
		return images.get(ImageSizes.LARGE);
	}

	public void setLargeImage(String largeImage) {
		this.images.put(ImageSizes.LARGE, largeImage);
	}
	
	public String getAmazonLink() {
		return links.get(Vendors.AMAZON);
	}
	
	public String getMondadoriLink() {
		return links.get(Vendors.MONDADORI);
	}
	
	public String getPlayLink() {
		return links.get(Vendors.PLAY_BOOKS);
	}
	
	public void setAmazonLink(String link) {
		links.put(Vendors.AMAZON, link);
	}
	
	public void setMondadoriLink(String link) {
		links.put(Vendors.MONDADORI, link);
	}
	
	public void setPlayLink(String link) {
		links.put(Vendors.PLAY_BOOKS, link);
	}
}
