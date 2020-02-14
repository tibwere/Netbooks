package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.ImageDispenser;
import logic.util.enumeration.BookGenres;
import logic.util.enumeration.ImageSizes;

/**
 * Versione singleton del DAO per l'interazione
 * con lo strato di persistenza per l'entity {@link Book}
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0252795)
 *
 */
public class BookDao {
	
	private static Book book1 = new Book("000001", "La Divina Commedia", "Dante");
	private static Book book2 = new Book("000002", "The Great Gatsby", "F. Fitzgerhald");
	
	private BookDao() {
		/* non instanziabile */
	}
	
	private static Book buildBookFromResultSet(ResultSet res) throws SQLException {
		String isbn = res.getString("isbn");
		String title = res.getString("title");
		String author = res.getString("author");
		
		Book book = new Book(isbn, title, author);
		book.setAmazonLink(res.getString("link_amz"));
		book.setLanguage(res.getString("language"));
		book.setLargeImage(ImageDispenser.getCovers(book.getTitle(), ImageSizes.LARGE));
		book.setMediumImage(ImageDispenser.getCovers(book.getTitle(), ImageSizes.MEDIUM));
		book.setMondadoriLink(res.getString("link_mnd"));
		book.setPlayLink(res.getString("link_play"));
		book.setPublisher(res.getString("publisher"));
		book.setSmallImage(ImageDispenser.getCovers(book.getTitle(), ImageSizes.SMALL));
		book.setYearOfPublication(res.getInt("year"));
		
		return book;
		
	}
	
	public static List<Book> findBooksForHomepage(String user) throws PersistencyException  {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			List<Book> books = new ArrayList<>();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_BOOKS_FOR_HP_SP);
			results = DBOperation.bindParametersAndExec(stmt, user);
			
			while (results.next()) {
				Book tmp = BookDao.buildBookFromResultSet(results);
				books.add(tmp);	
			}
			 
			return books;
			
		} catch(ClassNotFoundException | SQLException e) {
			throw new PersistencyException("Unable to load books for homepage");
		}
		finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static List<Book> findExchangeableBooks(String username) {
		List<Book> books = new ArrayList<>();
		
		if (username.equals("")) {
			for (int i = 0; i < 18; i ++) {
				book1.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK1));
				book1.setLargeImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST));
				books.add(book1);
				book2.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
				books.add(book2);
			}
		}
		else
			for (int i = 0; i < 3; i ++) {
				book1.setSmallImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST_THUMBNAIL));
				book1.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK1));
				book1.setLargeImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST));
				books.add(book1);
				book2.setSmallImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST_THUMBNAIL));
				book2.setMediumImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
				book2.setLargeImage(ImageDispenser.getImage(ImageDispenser.BOOK_TEST));
				books.add(book2);
			}
		
		return books;
	}
	
	public BookGenres findBookByGenre(String genre) {
		if (genre.equals("thr"))
			return BookGenres.THRILLER;
		else if (genre.equals("rom"))
			return BookGenres.ROMANCE;
		else return BookGenres.UNDEFINED;
	}
	
//	 fare per posizione con cordinate del reader if(companyPositio<= valoreSlider)
	public static List<Book> findBookForChart(double latitude, double longitude) {
		ArrayList<Book> books = new ArrayList<>();
		Book tmp1 = new Book("001122" , "La vita" , "Ale");
		tmp1.setSmallImage(ImageDispenser.getImage(ImageDispenser.BOOK1));
		books.add(tmp1);
			
		Book tmp2 = new Book("001123" , "Il miracolo" , "Fra");
		tmp2.setSmallImage(ImageDispenser.getImage(ImageDispenser.BOOK2));
		books.add(tmp2);
		
		return books;		
	}

}
