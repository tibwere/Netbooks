package test.cc;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import logic.dao.BookDao;
import logic.dao.ReaderDao;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.BookNotOwnedException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.model.users.Reader;

public class TestBookDao {

	/* il seguente isbn corrisponde al libro "Il grande Gatsby" presente nel db */
	private static final String TEST_ISBN = "8807900238";
	
	@Test
	public void testFindUserBooksWithOnlyOne() throws PersistencyException, AlreadyOwnedBookException, BookNotOwnedException {
		
		Reader testerReader = ReaderDao.getEmailAndGenre(Reader.TESTER_USERNAME);
		ReaderDao.insertNewBookInOwnedList(TEST_ISBN, testerReader.getUsername());
		
		List<Book> output = BookDao.findUserBooks(testerReader.getUsername());
		
		assertEquals(1, output.size());
		
		ReaderDao.removeBookFromOwnedList(TEST_ISBN, testerReader.getUsername());
	}
	
	
}
