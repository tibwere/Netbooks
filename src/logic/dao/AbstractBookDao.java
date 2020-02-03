package logic.dao;

import java.util.ArrayList;

import javafx.scene.image.Image;
import logic.model.Book;
import logic.util.ImageFactory;
import logic.util.enumeration.BookGenre;

public class AbstractBookDao {
	
	private static AbstractBookDao instance;
	
	private AbstractBookDao() {
		/* gestione connessione db */
	}
	
	public static AbstractBookDao getInstance() {
		if (instance == null)
			instance = new AbstractBookDao();
		
		return instance;
	}
	
	public static ArrayList<Book> findBooksForHomepage() {
		ArrayList<Book> books = new ArrayList<Book>();
		books.add(new Book("001122", "Simone 2", "Bello Mio", ImageFactory.getImage(ImageFactory.BOOK_TEST_THUMBNAIL)));
		
		return books;
	}
	
	public ArrayList<Image> getExchangeableBooks() {
		ArrayList<Image> books = new ArrayList<Image>();
		books.add(ImageFactory.getImage(ImageFactory.BOOK1));
		books.add(ImageFactory.getImage(ImageFactory.BOOK2));
		
		return books;
	}
	
	public BookGenre findBookByGenre(String genre) {
		if (genre.equals("thr"))
			return BookGenre.THRILLER;
		else if (genre.equals("rom"))
			return BookGenre.ROMANCE;
		else return BookGenre.UNDEFINED;
	}

}
