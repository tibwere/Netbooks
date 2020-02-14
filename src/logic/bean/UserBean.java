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
	
	private String username;
	private String password;
	private String firstName;
	private String secondName;
	
	public UserBean(String username, String password) throws WrongSyntaxException, NoSuchAlgorithmException {
		this.setUsername(username);
		this.setPassword(password);
	}

	public UserBean() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) throws WrongSyntaxException {

		if(!username.equals(""))
			this.username = username;
		else
			throw new WrongSyntaxException("Username must be not empty !");
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) throws WrongSyntaxException, NoSuchAlgorithmException {
		if(!password.equals(""))
			this.password = md5hash(password);
		else
			throw new WrongSyntaxException("Password must be not empty !");
	}
	
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
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
