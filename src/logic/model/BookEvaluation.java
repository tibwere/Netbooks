package logic.model;

import logic.model.users.Reader;

/**
 * Entita' del dominio di interesse: Valutazione
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class BookEvaluation {
	
	private int rating;
	private String title;
	private String body;
	
	private Reader author;
	private Book relatedBook;
	
	public BookEvaluation() {}
	
	public BookEvaluation(Reader author, Book relatedBook, int rating) {
		this.author = author;
		this.relatedBook = relatedBook;
		this.rating = rating;
	}

	public int getRating() {
		return rating;
	}
	
	public Book getRelatedBook() {
		return relatedBook;
	}

	public Reader getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
