package logic.model.users;

import java.util.List;

import logic.dao.BookDao;
import logic.model.Book;

/**
 * 
 * @author Alessandro Calomino (M. 0258841)
 *
 */

public class Retailer extends User {

	private String company;
	private int companyPosition; //per ora int-->fare con cordinate
	
	public Retailer(String username, String email, String company) {
		
		super(username, email);
		this.company =company;		
	}
	
	
	public String getCompany() {
		 return company;
	}
	
	public int getPosizione() {
		return companyPosition;
	}
	
	
	//metodo che interroga la BookDao
	public List<Book> getBookFromPosition() {
		
		List<Book> books = BookDao.getInstance().findBookForChart(companyPosition);
		
		return books;
		
	}
	
}