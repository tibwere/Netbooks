package logic.model.users;

import java.util.List;

import logic.dao.BookDao;
import logic.model.Book;

/**
 * 
 * @author Cristiano Cuffaro
 *
 */

public class Reader extends User {
	
	public Reader (String username, String email) {
		super(username, email);
	}
	
	public List<Book> getExchangeList() {
		
		return BookDao.getInstance().findExchangeableBooks(username);
		
	}
}

