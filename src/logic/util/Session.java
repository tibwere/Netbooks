package logic.util;

import logic.util.enumeration.Views;

/**
 * Astrazione del concetto di Sessione applicativa (implementazione Singleton)
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Session {
	
	private static Session instance = null;

	private String currUsername;
	private Views currView;
	
	private Session() {
		currUsername = "";
		currView = Views.LOGIN;
	}
	
	public static Session getSession() {
		if (instance == null) 
			instance = new Session();
			
		return instance;
	}
	
	public String getCurrUser() {
		return currUsername;
	}

	public Views getCurrView() {
		return currView;
	}
	
	public void setCurrUser(String currUser) {
		this.currUsername = currUser;
	}

	public void setCurrView(Views nextView) {
		this.currView = nextView;
	}

}
