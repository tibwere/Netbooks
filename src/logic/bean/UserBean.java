package logic.bean;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import logic.exception.WrongSyntaxException;


/**
 * Bean per la migrazione dei dati dell'utente in fase di login
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class UserBean {
	
	private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:"
			+ "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	
	private String username;
	private String password;
	private String email;
	private String address;
	private String country;
	private String zip;
	private String city;
	
	public UserBean(String username, String password) throws WrongSyntaxException, NoSuchAlgorithmException {
		this.setUsername(username);
		this.setPassword(password);
	}

	public UserBean() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws WrongSyntaxException {
		
		if (username.equals(""))
			throw new WrongSyntaxException("Username must be not empty!");
		else if (username.length() >= 32)
			throw new WrongSyntaxException("Max length for username: 32 chars");
		else if (username.contains(" "))
			throw new WrongSyntaxException("Username must not contains spaces!");
		else
			this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws WrongSyntaxException, NoSuchAlgorithmException {
		
		if (password.equals(""))
			throw new WrongSyntaxException("Password must be not empty !");
		if (password.length() >= 32)
			throw new WrongSyntaxException("Max length for password: 32 chars");
		if (password.length() < 8)
			throw new WrongSyntaxException("Min length for password: 8 chars");
		else if (password.matches(".*\\[0-9].*"))
			throw new WrongSyntaxException("Password must contain a digit!");
		else
			this.password = md5hash(password);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) throws WrongSyntaxException {
		if (email.matches(EMAIL_REGEX) && email.length() < 256)
			this.email = email;
		else
			throw new WrongSyntaxException("Email address is not valid!");
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) throws WrongSyntaxException {
		if (zip.length() != 5 && zip.length() != 0)
			throw new WrongSyntaxException("ZIP code is invalid!");
		
		this.zip = zip;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private String md5hash(String input) throws NoSuchAlgorithmException { 
		MessageDigest md = MessageDigest.getInstance("MD5");  
        byte[] messageDigest = md.digest(input.getBytes());  
        BigInteger no = new BigInteger(1, messageDigest);
        
        StringBuilder hashtext = new StringBuilder(no.toString(16)); 
        while (hashtext.length() < 32) 
        	hashtext.insert(0, '0');
       
        return hashtext.toString(); 
    } 
	
	
}
