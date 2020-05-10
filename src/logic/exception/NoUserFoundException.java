package logic.exception;

/**
 * Ecceizone ad hoc realizzata per gestire il tentativo
 * di login fallito a causa di mancata registrazione
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class NoUserFoundException extends Exception {

	private static final long serialVersionUID = -8469208299360993393L;
	
	public NoUserFoundException(String message) {
		super(message);		
	}

}
