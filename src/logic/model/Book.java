package logic.model;

import javafx.scene.image.Image;

/**
 * Entità del dominio di interesse: Libro
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Book {
	
	private String isbn;
	private String title;
	private String author;
	private Image image;
	
	public Book(String isbn, String title, String author, Image image) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.image = image;
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

	public Image getImage() {
		return image;
	}
}
