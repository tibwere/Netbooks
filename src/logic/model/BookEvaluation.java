package logic.model;

import logic.model.users.User;

public class BookEvaluation {
	
	private double rating;
	private String title;
	private String body;
	
	private User author;
	private Book relatedBook;
	
	public BookEvaluation(User author, Book relatedBook, double rating) {
		this.author = author;
		this.relatedBook = relatedBook;
		this.rating = rating;
	}

	public double getRating() {
		return rating;
	}
	
	public Book getRelatedBook() {
		return relatedBook;
	}

	public User getAuthor() {
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
