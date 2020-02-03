package logic.dao;

import java.util.ArrayList;

import logic.model.Book;
import logic.util.ImageFactory;

public class AbstractBookDao {
	
	private static AbstractBookDao instance;
	
	private AbstractBookDao() {
		/* gestione connessione db */
	}
	
	public static AbstractBookDao getInsance() {
		if (instance == null)
			instance = new AbstractBookDao();
		
		return instance;
	}
	
	public static ArrayList<Book> findBooksForHomepage() {
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(new Book("001122", "Simone 2", "Bello Mio", ImageFactory.getImage(ImageFactory.BOOK_TEST_THUMBNAIL)));
		
		return books;
	}

}
