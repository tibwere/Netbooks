package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookBean;
import logic.dao.BookDao;
import logic.model.Book;
import logic.util.enumeration.ImageSize;

/**
 * Controller del caso d'uso "Buy book"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class BuyBookController {
	
	private ManageRatingsController rrController;

	public BuyBookController(ManageRatingsController secondaryController) {
		this.rrController = secondaryController;
	}

	public List<BookBean> getBooksForHomepage() {
		List<Book> books = BookDao.getInstance().findBooksForHomepage();
		ArrayList<BookBean> beans = new ArrayList<>();
		
		for (Book b : books) {
			BookBean bean = new BookBean(b.getTitle(), b.getAuthor());
			bean.setSingleImage(b.getSmallImage(), ImageSize.SMALL);
			bean.setSingleImage(b.getLargeImage(), ImageSize.LARGE);
			bean.setIsbn(b.getIsbn());
			bean.setYearOfPublication(b.getYearOfPublication());
			bean.setPublisher(b.getPublisher());
			bean.setLanguage(b.getLanguage());
			beans.add(bean);		
		}
		
		return beans;	
	}
	
	public ManageRatingsController getRRController() {
		return rrController;
	}
}
