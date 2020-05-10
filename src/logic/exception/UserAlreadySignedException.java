package logic.exception;

/**
 * Ecceizone ad hoc realizzata per gestire eccezioni 
 * dovute al tentativo di registrazione di un utente già presente
 * nel sistema.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class UserAlreadySignedException extends Exception {

	private static final long serialVersionUID = 6002172352138372931L;
	
	public UserAlreadySignedException(String message) {
		super(message);
	}

}
