package logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.bean.BookBean;
import logic.bean.ReaderBean;
import logic.dao.BookDao;
import logic.dao.ReaderDao;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.PersistencyException;
import logic.exception.WrongSyntaxException;
import logic.model.Book;
import logic.model.users.Reader;
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

	public List<BookBean> getNotOwnedBooks(ReaderBean currUser) throws PersistencyException {
		List<Book> books = BookDao.findNotOwnedBooks(currUser.getUsername());
		return getListOfBeans(books);
	}

	public ReaderBean getUserGenerality(ReaderBean currUser) throws PersistencyException {
		Reader reader = ReaderDao.getNameAndSurname(currUser.getUsername());
		ReaderBean bean = new ReaderBean();
		try {
			bean.setFirstName(reader.getFirstName());
			bean.setSecondName(reader.getSecondName());

		} catch (WrongSyntaxException e) {
			throw new IllegalStateException("DB must be consistent");
		}
		
		return bean;
	}

	public void addBookToOwnedList(BookBean bean, ReaderBean currUser) throws AlreadyOwnedBookException, PersistencyException {
		ReaderDao.insertNewBookInOwnedList(bean.getIsbn(), currUser.getUsername());
	}

	public List<BookBean> getSearchedBook(String text) throws PersistencyException {
		List<Book> selectedBooks = BookDao.findBookForTitle(text);
		return getListOfBeans(selectedBooks);
	}

	public List<BookBean> getAllBooks() throws PersistencyException {
		List<Book> selectedBooks = BookDao.findAllBooks();
		return getListOfBeans(selectedBooks);
	}

	public boolean bookIsOwned(BookBean bean, ReaderBean currUser) throws PersistencyException {
		return ReaderDao.checkIfCurrReaderOwnBook(currUser.getUsername(), bean.getIsbn());
	}
}
