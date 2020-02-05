package logic.controller;

import java.util.ArrayList;
import java.util.List;
import logic.bean.BookBean;
import logic.dao.BookDao;
import logic.model.Book;

/**
 * Controller del caso d'uso "Exchange Book"
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookController {

	public List<BookBean> getExchangeableBooks() {
		
		List<Book> books = BookDao.getInstance().findExchangeableBooks();
		List<BookBean> beans = new ArrayList<>();
		
		for (Book book : books) {
			
			BookBean bean = new BookBean();
			
			bean.setTitle(book.getTitle());
			bean.setImage(book.getMediumImage());
			beans.add(bean);
		}
		
		return beans;
	}
}
