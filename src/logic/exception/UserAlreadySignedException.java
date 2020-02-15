package logic.exception;

public class UserAlreadySignedException extends Exception {

	private static final long serialVersionUID = 6002172352138372931L;
	
	public UserAlreadySignedException(String message) {
		super(message);
	}

}
