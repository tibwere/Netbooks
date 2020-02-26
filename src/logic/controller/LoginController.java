package logic.controller;

import java.io.IOException;

import org.json.JSONException;

import logic.bean.ReaderBean;
import logic.bean.RetailerBean;
import logic.bean.UserBean;
import logic.dao.UserDao;
import logic.exception.NoUserFoundException;
import logic.exception.PersistencyException;
import logic.exception.UserAlreadySignedException;
import logic.exception.WrongSyntaxException;
import logic.model.Geolocalization;
import logic.model.users.Reader;
import logic.model.users.Retailer;
import logic.util.Parser;
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
		
		return UserDao.findUserByUsernameAndPassword(user, passwd);
	}

	public void signup(ReaderBean bean) throws IOException, UserAlreadySignedException {
		Reader reader = new Reader(bean.getUsername(), bean.getEmail(), bean.isFemale());
		reader.setFirstName(bean.getFirstName());
		reader.setSecondName(bean.getSecondName());
		
		try {
			Geolocalization position = Parser.getMapsLocation(formatAddress(bean.getAddress(), bean.getCity(), bean.getZip(), bean.getCountry()));
			reader.setLatitude(position.getLatitude());
			reader.setLongitude(position.getLongitude());
		} catch(JSONException e) { /* RuntimeException causata dall'impossiblitaà di convertire il JSON (indirizzo malformato) */			
			reader.setLatitude(Geolocalization.INVALID_VALUE);
			reader.setLongitude(Geolocalization.INVALID_VALUE);
		}
		
		reader.store(bean.getPassword());
	}

	private String formatAddress(String address, String city, String zip, String country) {
		String addressFormatted = address.replace(",", ""); 
		StringBuilder builder = new StringBuilder(addressFormatted);
		builder.append(", " + zip);
		builder.append(", " + city);
		builder.append(", " + country);
		
		return builder.toString();
	}

	public void signup(RetailerBean bean) throws IOException, UserAlreadySignedException, WrongSyntaxException {
		Retailer retailer = new Retailer(bean.getUsername(), bean.getEmail());
		retailer.setCompany(bean.getCompanyName());
		retailer.setVat(bean.getVat());
		
		try {
			Geolocalization position = Parser.getMapsLocation(formatAddress(bean.getAddress(), bean.getCity(), bean.getZip(), bean.getCountry()));
			retailer.setLatitude(position.getLatitude());
			retailer.setLongitude(position.getLongitude());
		} catch(JSONException e) { /* RuntimeException causata dall'impossiblita di convertire il JSON (indirizzo malformato) */			
			throw new WrongSyntaxException("Please, give us a valid address");
		} 
		
		retailer.store(bean.getPassword());
	}
}