package logic.exception;

/**
 * Ecceizone ad hoc realizzata per gestire errori 
 * dovuti a errori sintattici nell'immissione dei dati nei form
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class WrongSyntaxException extends Exception {
	
	private static final long serialVersionUID = 3096916266903743094L;

	public WrongSyntaxException(String message) {
		super(message);
	}

}
