package logic.exception;

/**
 * Ecceizone ad hoc realizzata per gestire eccezioni 
 * dovute a malfunzionamento della connessione al db
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class PersistencyException extends Exception {
	
	private static final long serialVersionUID = -5489708313527275386L;

	public PersistencyException(String message) {
		super(message);
	}

}
