package logic.controller;

import logic.util.enumeration.Views;

/**
 * Astrazione del concetto di Sessione applicativa.
 * E' stato scelto di porre tale classe nel package {@link logic.controller}
 * poiché i metodi setters degli attributi hanno visibilità di package e quindi
 * possono essere invocati soltanto dai controller e non dalla view
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Session {
	
	private static Session instance = null;

	private String currUserID;
	private Views currView;
	
	private Session() {
		currView = Views.LOGIN;
		currUserID = null;
	}
	
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
	
	void setCurrUser(String currUser) {
		this.currUserID = currUser;
	}

	void setCurrView(Views currView) {
		this.currView = currView;
	}

}
