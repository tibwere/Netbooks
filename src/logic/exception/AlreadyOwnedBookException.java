package logic.exception;

/**
 * Ecceizone ad hoc realizzata per gestire il tentativo
 * fallito di inserire un libro nella propria lista 
 * (libro già presente)
 * @author Simone Tiberi (M. 0252795)
 * 
 */
public class AlreadyOwnedBookException extends Exception {

	private static final long serialVersionUID = 7070883892846476589L;
	
	public AlreadyOwnedBookException(String message) {
		super(message);
	}

}
