package logic.exception;

public class AlreadyOwnedBookException extends Exception {

	private static final long serialVersionUID = 7070883892846476589L;
	
	public AlreadyOwnedBookException(String message) {
		super(message);
	}

}
