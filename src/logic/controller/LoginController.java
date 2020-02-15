package logic.controller;

import java.io.IOException;

import logic.bean.ReaderBean;
import logic.bean.RetailerBean;
import logic.bean.UserBean;
import logic.dao.UserDao;
import logic.exception.NoUserFoundException;
import logic.exception.PersistencyException;
import logic.exception.UserAlreadySignedException;
import logic.model.Geolocalization;
import logic.model.users.Reader;
import logic.model.users.Retailer;
import logic.util.Parser;
import logic.util.Session;
import logic.util.enumeration.UserTypes;

/**
 * Controller del caso d'uso "Login"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class LoginController {

	public UserTypes loginUser(UserBean bean) throws NoUserFoundException, PersistencyException {
		String user = bean.getUsername();
		String passwd = bean.getPassword();
		
		UserTypes type = UserDao.findUserByUsernameAndPassword(user, passwd);
		Session.getSession().setCurrUser(user);

		return type;
	}

	public void signup(ReaderBean bean) throws IOException, UserAlreadySignedException {
		Reader reader = new Reader(bean.getUsername(), bean.getEmail(), bean.isFemale());
		reader.setFirstName(bean.getFirstName());
		reader.setSecondName(bean.getSecondName());
		
		Geolocalization position = Parser.getMapsLocation(formatAddress(bean.getAddress(), bean.getCity(), bean.getZip(), bean.getCountry()));
		reader.setLatitude(position.getLatitude());
		reader.setLongitude(position.getLongitude());
		
		reader.store(bean.getPassword());
		Session.getSession().setCurrUser(bean.getUsername());
	}

	private String formatAddress(String address, String city, String zip, String country) {
		String addressFormatted = address.replace(",", ""); 
		StringBuilder builder = new StringBuilder(addressFormatted);
		builder.append(", " + zip);
		builder.append(", " + city);
		builder.append(", " + country);
		
		return builder.toString();
	}

	public void signup(RetailerBean bean) throws IOException, UserAlreadySignedException {
		Retailer retailer = new Retailer(bean.getUsername(), bean.getEmail(), bean.getCompanyName());
		retailer.setVat(bean.getVat());
		
		Geolocalization position = Parser.getMapsLocation(formatAddress(bean.getAddress(), bean.getCity(), bean.getZip(), bean.getCountry()));
		retailer.setLatitude(position.getLatitude());
		retailer.setLongitude(position.getLongitude());
		
		retailer.store(bean.getPassword());
		Session.getSession().setCurrUser(bean.getUsername());
	}

}