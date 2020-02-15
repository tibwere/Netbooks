package logic.model.users;

import java.util.List;

import logic.dao.BookDao;
import logic.dao.RetailerDao;
import logic.exception.UserAlreadySignedException;
import logic.model.Book;

/**
 * 
 * @author Alessandro Calomino (M. 0258841)
 *
 */
public class Retailer extends User {
	
	private String vat;
	private String company;
	
	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Retailer(String username, String email, String company) {
		super(username, email);		
	}
	
	public Retailer(String username) {
		super(username);
	}
	
	@Override
	public void store(String password) throws UserAlreadySignedException {
		RetailerDao.saveRetailerInDB(this, password);
	}
	
	//metodo che interroga la BookDao
	public List<Book> getBookFromPosition() {
		List<Book> books = BookDao.findBookForChart(position.getLatitude(), position.getLongitude());
		return books;
	}	
}