package logic.model;

import logic.model.users.Reader;

public class ExchangeableBook extends Book {
	
	private Reader owner;
	
	public ExchangeableBook(String isbn, String title, String author, Reader owner) {
		super(isbn, title, author);
		this.owner = owner;
	}
}
