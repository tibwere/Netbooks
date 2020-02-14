package logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.bean.BookBean;
import logic.bean.UserBean;
import logic.dao.BookDao;
import logic.dao.UserDao;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.model.users.Reader;
import logic.util.Session;
import logic.util.enumeration.ImageSizes;
import logic.util.enumeration.Vendors;

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
	
	public ManageEvaluationsController getManageEvaluationsController() {
		return manageEvaluationsController;
	}
	
	private BookBean fillBeanFromEntity(Book book) {
		BookBean bean = new BookBean(book.getTitle(), book.getAuthor());
		bean.setSingleImage(book.getSmallImage(), ImageSizes.SMALL);
		bean.setSingleImage(book.getLargeImage(), ImageSizes.LARGE);
		bean.setIsbn(book.getIsbn());
		bean.setYearOfPublication(book.getYearOfPublication());
		bean.setPublisher(book.getPublisher());
		bean.setLanguage(book.getLanguage());
		
		Map<Vendors, String> links = new HashMap<>();
		links.put(Vendors.AMAZON, book.getAmazonLink());
		links.put(Vendors.MONDADORI, book.getMondadoriLink());
		links.put(Vendors.PLAY_BOOKS, book.getPlayLink());
		
		bean.setLinks(links);
		
		return bean;
	}
	
	private List<BookBean> getListOfBeans(List<Book> books) {
		ArrayList<BookBean> beans = new ArrayList<>();
		
		for (Book b : books) {
			BookBean bean = fillBeanFromEntity(b);
			beans.add(bean);
		}
		
		return beans;
	}

	public List<BookBean> getNotOwnedBooks() throws PersistencyException {
		List<Book> books = BookDao.findNotOwnedBooks(Session.getSession().getCurrUser());
		return getListOfBeans(books);
	}

	public UserBean getUserGenerality() throws PersistencyException {
		Reader reader = UserDao.getNameAndSurname(Session.getSession().getCurrUser());
		UserBean bean = new UserBean();
		bean.setFirstName(reader.getFirstName());
		bean.setSecondName(reader.getSecondName());
		
		return bean;
	}

	public void addBookToOwnedList(BookBean bean) throws AlreadyOwnedBookException, PersistencyException {
		UserDao.insertNewBookInOwnedList(bean.getIsbn(), Session.getSession().getCurrUser());
	}

	public List<BookBean> getSearchedBook(String text) throws PersistencyException {
		List<Book> selectedBooks = BookDao.findBookForTitle(text);
		return getListOfBeans(selectedBooks);
	}

	public List<BookBean> getAllBooks() throws PersistencyException {
		List<Book> selectedBooks = BookDao.findAllBooks();
		return getListOfBeans(selectedBooks);
	}
}
