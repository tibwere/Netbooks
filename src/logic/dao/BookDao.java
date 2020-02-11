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
	
	private Book book1 = new Book("000001", "La Divina Commedia", "Dante");
	private Book book2 = new Book("000002", "The Great Gatsby", "F. Fitzgerhald");
	
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
		Book tmp = new Book("112233", "Another Test", "Boh Boh");
		tmp.setSmallImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST_THUMBNAIL));
		tmp.setLargeImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST));
		tmp.setYearOfPublication(1998);
		tmp.setPublisher("Feltrinelli");
		tmp.setLanguage("Aramaico");
		books.add(tmp);
		
		Book tmp2 = new Book("001122", "Simone 2", "Bello Mio");
		tmp2.setSmallImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST_THUMBNAIL));
		tmp2.setLargeImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST));
		tmp2.setYearOfPublication(1998);
		tmp2.setPublisher("Mondadori");
		tmp2.setLanguage("Italiano");
		books.add(tmp2);

		return books;
	}
	
	public List<Book> findExchangeableBooks(String username) {
		List<Book> books = new ArrayList<>();
		
		if (username.equals("")) {
			for (int i = 0; i < 9; i ++) {
				book1.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK1));
				book1.setLargeImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST));
				books.add(book1);
				book2.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
				books.add(book2);
			}
		}
		
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
		
		Book tmp2 = new Book("343434" , "Fra" , "� bella ");
		tmp2.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
		books.add(tmp2);	
		
		return books;		
	}

}
