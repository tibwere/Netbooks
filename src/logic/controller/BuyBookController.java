package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookBean;
import logic.dao.BookDao;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.Session;
import logic.util.enumeration.ImageSizes;

/**
 * Controller del caso d'uso "Buy book"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class BuyBookController {
	
	private ManageEvaluationsController manageEvaluationsController;

	public BuyBookController(ManageEvaluationsController secondaryController) {
		this.manageEvaluationsController = secondaryController;
	}

	public List<BookBean> getBooksForHomepage() throws PersistencyException {
		List<Book> books = BookDao.findBooksForHomepage(Session.getSession().getCurrUser());
		ArrayList<BookBean> beans = new ArrayList<>();
		
		for (Book b : books) {
			BookBean bean = new BookBean(b.getTitle(), b.getAuthor());
			bean.setSingleImage(b.getSmallImage(), ImageSizes.SMALL);
			bean.setSingleImage(b.getLargeImage(), ImageSizes.LARGE);
			bean.setIsbn(b.getIsbn());
			bean.setYearOfPublication(b.getYearOfPublication());
			bean.setPublisher(b.getPublisher());
			bean.setLanguage(b.getLanguage());
			beans.add(bean);		
		}
		
		return beans;	
	}
	
	public ManageEvaluationsController getManageEvaluationsController() {
		return manageEvaluationsController;
	}
}
