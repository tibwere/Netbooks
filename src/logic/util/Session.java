package logic.util;

import logic.util.enumeration.Views;

/**
 * Astrazione del concetto di Sessione applicativa.
 * E' stato scelto di porre tale classe nel package {@link logic.controller}
 * poiche' i metodi setters degli attributi hanno visibilita'  di package e quindi
 * possono essere invocati soltanto dai controller e non dalla view
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Session {
	
	private static Session instance = null;

	private String currUserID;
	private Views currView;
	
	private Session() {/* nothing to do here */}
	
	public static Session getSession() {
		if (instance == null) 
			instance = new Session();
			
		return instance;
	}
	
	public String getCurrUser() {
		return currUserID;
	}

	public Views getCurrView() {
		return currView;
	}
	
	public void setCurrUser(String currUser) {
		this.currUserID = currUser;
	}

	public void setCurrView(Views nextView) {
		this.currView = nextView;
	}

}
