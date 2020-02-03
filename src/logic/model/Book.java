package logic.model;

import javafx.scene.image.Image;

public class Book {
	
	private String ISBN;
	private String title;
	private String author;
	private Image image;
	
	public Book(String ISBN, String title, String author, Image image) {
		this.ISBN = ISBN;
		this.title = title;
		this.author = author;
		this.image = image;
	}

	public String getISBN() {
		return ISBN;
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
