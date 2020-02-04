package logic.controller;

import java.util.ArrayList;
import java.util.List;

import logic.bean.BookBean;
import logic.dao.BookDao;
import logic.model.Book;
import logic.util.enumeration.Views;

/**
 * Controller del caso d'uso "View book by category"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ViewBookByCategoryController {

	public List<BookBean> getBooks() {
		List<Book> books = BookDao.getInstance().findBooksForHomepage();
		ArrayList<BookBean> bean = new ArrayList<>();
		
		for (Book b : books) 
			bean.add(new BookBean(b.getTitle(), b.getAuthor(), b.getImage()));
		
		return bean;	
	}	
	
	/**
	 * Metodo di classe per l'aggiornamento della {@link Session} applicativa
	 * E' stato scelto tale controller come responsabile di tale operazione poichè
	 * è il controller applicativo in cui più volte risulta necessario invocare tale operazione 
	 * @param view Nuova view da settare
	 */
	public static void prepareToUpdateView(Views view) {
		Session.getSession().setCurrView(view);
	}

	public static void logoutUser() {
		Session.getSession().setCurrUser("");
		Session.getSession().setCurrView(Views.LOGIN);
	}
}
