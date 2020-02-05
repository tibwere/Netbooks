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
		Book tmp = new Book("001122", "Simone 2", "Bello Mio");
		tmp.setSmallImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST_THUMBNAIL));
		tmp.setLargeImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST));
		tmp.setYearOfPublication(1998);
		tmp.setPublisher("Mondadori");
		tmp.setLanguage("Italiano");
		books.add(tmp);

		return books;
	}
	
	public List<Book> findExchangeableBooks() {
		List<Book> books = new ArrayList<>();
		Book tmp1 = new Book("112233", "La Divina Commedia", "Dante");
		tmp1.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK1));
		books.add(tmp1);
		
		Book tmp2 = new Book("001122", "The Great Gatsby", "F. Fitzgerhald");
		tmp2.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
		books.add(tmp2);	
		
		return books;
	}
	
	public List<Book> findPersonalBooks (String username) {
		List<Book> books = new ArrayList<>();
		Book tmp1 = new Book("112233", "La Divina Commedia", "Dante");
		tmp1.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK1));
		books.add(tmp1);
		
		Book tmp2 = new Book("001122", "The Great Gatsby", "F. Fitzgerhald");
		tmp2.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
		books.add(tmp2);	
		
		return books;
	}
	
	public BookGenre findBookByGenre(String genre) {
		if (genre.equals("thr"))
			return BookGenre.THRILLER;
		else if (genre.equals("rom"))
			return BookGenre.ROMANCE;
		else return BookGenre.UNDEFINED;
	}
	
//	 fare per posizione con cordinate del reader
	public List<Book> findBookForChart(int position) {
		List<Book> books = new ArrayList<>();
		Book tmp1 = new Book("323233" , "Ale" , "La vita");
		tmp1.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK1));
		books.add(tmp1);
		
		Book tmp2 = new Book("343434" , "Fra" , "è bella ");
		tmp2.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
		books.add(tmp2);	
		
		return books;		
	}

}
