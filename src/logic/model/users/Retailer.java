package logic.model.users;

import java.util.ArrayList;
import java.util.List;

import logic.dao.BookDao;
import logic.model.Book;

/**
 * Entita' del dominio di interesse: Rivenditore
 * @author Alessandro Calomino (M. 0258841)
 *
 */
public class Retailer extends User {

	private String societ�;
	private int posizione; //per ora int-->fare con cordinate
	
	public Retailer(String username, String email, String societ�) {
		
		super(username, email);
		this.societ� = societ�;		
	}
	
	
	public String getsociet�() {
		return societ�;
	}
	
	public int getPosizione() {
		
		return posizione;
	}
	
	
	//metodo che interroga la BookDao
	public List<Book> getBookFromPosition() {
		
		List<Book> books = new ArrayList<>();
		books  = BookDao.getInstance().findBookForChart(posizione);
		
		return books;
		
	}
	
	}