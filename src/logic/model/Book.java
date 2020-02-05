package logic.model;

import javafx.scene.image.Image;

/**
 * Entità del dominio di interesse: Libro
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0258093)
 */
public class Book {
	
	private String isbn;
	private String title;
	private String author;
	private Image smallImage;
	private Image mediumImage;
	private Image largeImage;
	
	public Book(String isbn, String title, String author, Image smallImg, Image mediumImg, Image largeImg) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.smallImage = smallImg;
		this.mediumImage = mediumImg;
		this.largeImage = largeImg;
	}

	public String getISBN() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public Image getSmallImage() {
		return smallImage;
	}
	
	public Image getMediumImage() {
		return mediumImage;
	}
	
	public Image getLargeImage() {
		return largeImage;
	}
}
