package logic.controller;

import java.util.ArrayList;
import java.util.List;
import logic.bean.BookBean;
import logic.dao.BookDao;
import logic.model.Book;
import logic.util.enumeration.ImageSize;

/**
 * Controller del caso d'uso "Exchange Book"
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookController {

	public List<BookBean> getExchangeableBooks() {
		
		List<Book> books = BookDao.getInstance().findExchangeableBooks("");
		List<BookBean> beans = new ArrayList<>();
		
		for (Book book : books) {
			
			BookBean bean = new BookBean(book.getTitle(), book.getAuthor());
			
			bean.setSingleImage(book.getMediumImage(), ImageSize.MEDIUM);
			bean.setSingleImage(book.getLargeImage(), ImageSize.LARGE);
			bean.setOwner("Pippo");
			beans.add(bean);
		}
		
		return beans;
	}
}
