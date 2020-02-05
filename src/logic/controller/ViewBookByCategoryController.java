package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookBean;
import logic.dao.BookDao;
import logic.model.Book;
import logic.util.enumeration.FXMLElements;

/**
 * Controller del caso d'uso "View book by category"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ViewBookByCategoryController {

	public List<BookBean> getBooksForHome() {
		List<Book> books = BookDao.getInstance().findBooksForHomepage();
		ArrayList<BookBean> beans = new ArrayList<>();
		
		for (Book b : books) {
			BookBean bean = new BookBean(b.getTitle(), b.getAuthor());
			bean.setSmallImage(b.getSmallImage());
			bean.setLargeImage(b.getLargeImage());
			bean.setIsbn(b.getIsbn());
			bean.setYearOfPublication(b.getYearOfPublication());
			bean.setPublisher(b.getPublisher());
			bean.setLanguage(b.getLanguage());
			beans.add(bean);		
		}
		return beans;	
	}	
	
	/**
	 * Metodo di classe per l'aggiornamento della {@link Session} applicativa
	 * E' stato scelto tale controller come responsabile di tale operazione poich�
	 * � il controller applicativo in cui pi� volte risulta necessario invocare tale operazione 
	 * @param view Nuova view da settare
	 */
	public static void prepareToUpdateView(FXMLElements view) {
		Session.getSession().setCurrView(view);
	}

	public static void logoutUser() {
		Session.getSession().setCurrUser("");
		Session.getSession().setCurrView(FXMLElements.LOGIN);
	}
}
