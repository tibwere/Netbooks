package logic.dao;

import java.util.ArrayList;
import java.util.List;
import logic.model.Book;
import logic.util.ImageDispenser;
import logic.util.enumeration.BookGenre;

/**
 * Versione singleton del DAO per l'interazione
 * con lo strato di persistenza per l'entity {@link Book}
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0252795)
 *
 */
public class BookDao {
	
	private static BookDao instance;
	
	private BookDao() {
		/* gestione connessione db */
	}
	
	public static BookDao getInstance() {
		if (instance == null)
			instance = new BookDao();
		
		return instance;
	}
	
	public List<Book> findBooksForHomepage() {
		ArrayList<Book> books = new ArrayList<>();
		books.add(new Book("001122", "Simone 2", "Bello Mio", ImageDispenser.getImage(ImageDispenser.BOOK_TEST_THUMBNAIL), null, null));
		
		return books;
	}
	
	public List<Book> findExchangeableBooks() {
		List<Book> books = new ArrayList<>();
		books.add(new Book("001122", "La Divina Commedia", "Dante", null, ImageDispenser.getImage(ImageDispenser.BOOK1), null));
		books.add(new Book("001122", "The Great Gatsby", "F. Fitzgerhald", null, ImageDispenser.getImage(ImageDispenser.BOOK2), null));
		
		
		return books;
	}
	
	public List<Book> findPersonalBooks (String username) {
		List<Book> books = new ArrayList<>();
		books.add(new Book("001122", "La Divina Commedia", "Dante", null, ImageDispenser.getImage(ImageDispenser.BOOK1), null));
		books.add(new Book("001122", "The Great Gatsby", "F. Fitzgerhald", null, ImageDispenser.getImage(ImageDispenser.BOOK2), null));
		
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
