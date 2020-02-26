package logic.model.users;

import logic.dao.RetailerDao;
import logic.exception.UserAlreadySignedException;
import logic.model.Geolocalization;

/**
 * Entita'  del dominio di interesse: Rivenditore
 * @author Simone Tiberi (M. 0252795)
 * @author Alessandro Calomino (M. 0258841)
 *
 */
public class Retailer extends User {
	
	private String vat;
	private String company;
	
	public Retailer(String username, String email) {
		super(username, email);		
	}
	
	public Retailer(String username) {
		super(username);
	}
	
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
	
	@Override
	public void store(String password) throws UserAlreadySignedException {
		RetailerDao.saveRetailerInDB(this, password, !(this.getLatitude() == Geolocalization.INVALID_VALUE || this.getLongitude() == Geolocalization.INVALID_VALUE));
	}
}