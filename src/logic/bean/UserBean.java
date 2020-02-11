package logic.bean;

import logic.exception.WrongSyntaxException;


/**
 * Bean per la migrazione dei dati dell'utente in fase di login
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class UserBean {
	
	private String username;
	private String password;
	private boolean rememberMe;
	
	public UserBean(String username, String password, boolean rememberMe) throws WrongSyntaxException {
		this.setUsername(username);
		this.setPassword(password);
		this.setRememberMe(rememberMe);
	}
	
	public UserBean(String username, String password) throws WrongSyntaxException {
		this(username, password, false);
	}

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

	public void setPassword(String password) throws WrongSyntaxException {
		if(!password.equals(""))
			this.password = password;
		else
			throw new WrongSyntaxException("Password must be not empty !");
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	
}
