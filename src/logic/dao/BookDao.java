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
import logic.util.enumeration.ImageSizes;

/**
 * DAO per l'interazione con lo strato di persistenza 
 * dei dati realtivi all'entity {@link Book}
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0252795)
 *
 */
public class BookDao {

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
	
	public static List<Book> findNotOwnedBooks(String user) throws PersistencyException  {
		
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
	
	public static List<Book> findBookForTitle(String title) throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			List<Book> books = new ArrayList<>();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_SEARCHED_BOOK_SP);
			StringBuilder searchBuild = new StringBuilder("%");
			searchBuild.append(title);
			searchBuild.append("%");
			results = DBOperation.bindParametersAndExec(stmt, searchBuild.toString());
			
			while (results.next()) {
				Book tmp = BookDao.buildBookFromResultSet(results);
				books.add(tmp);	
			}
			 
			return books;
			
		} catch(ClassNotFoundException | SQLException e) {
			throw new PersistencyException("Unable to search selected book");
		}
		finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	

	public static List<Book> findAllBooks() throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			List<Book> books = new ArrayList<>();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_ALL_BOOKS_SP);
			results = DBOperation.bindParametersAndExec(stmt);
			
			while (results.next()) {
				Book tmp = BookDao.buildBookFromResultSet(results);
				books.add(tmp);	
			}
			 
			return books;
			
		} catch(ClassNotFoundException | SQLException e) {
			throw new PersistencyException("Unable to retrive all books from DB.");
		}
		finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
public static List<Book> findUserBooks(String username) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			List<Book> books = new ArrayList<>();
			
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_EXCHANGEABLE_BOOKS_SP);
			results = DBOperation.bindParametersAndExec(stmt, username);
						
			while (results.next()) {
				Book b = BookDao.buildBookFromResultSet(results);
				books.add(b);	
			} 
			
			return books;
			
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistencyException("Unable to load exchangeable books");
		}
		finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static Book getBook(String isbn) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet result = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_BOOK_SP);
			result = DBOperation.bindParametersAndExec(stmt, isbn);
			
			if (!result.first())
				throw new IllegalStateException("Unexpected application behavior has occurred.");
			
			return BookDao.buildBookFromResultSet(result);

		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to load book");
		}
		finally {
			DBManager.closeStmt(stmt);
			DBManager.closeRs(result);
		}
	}

}
