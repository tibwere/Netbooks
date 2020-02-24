package logic.exception;
/**
 * Eccezione ad hoc realizzata per gestire il tentativo
 * di lanciare un evento che non comporta alcun effetto
 * nello stato corrente
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class NoStateTransitionException extends Exception{

	private static final long serialVersionUID = 7987971393064792812L;

	public NoStateTransitionException(String message) {
		super(message);
	}
}
