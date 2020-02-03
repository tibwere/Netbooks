package logic.util;

import logic.util.enumeration.Views;

public class Session {
	
	private static Session instance = null;

	private String currUserID;
	private Views currView;
	
	private Session() {
		currView = Views.LOGIN;
		currUserID = null;
	}
	
	public String getCurrUser() {
		return currUserID;
	}

	public void setCurrUser(String currUser) {
		this.currUserID = currUser;
	}

	public Views getCurrView() {
		return currView;
	}

	public void setCurrView(Views currView) {
		this.currView = currView;
	}

	public static Session getSession() {
		if (instance == null) 
			instance = new Session();
			
		return instance;
	}
	
	
	

}
