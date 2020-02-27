package logic.exception;
/**
 * Eccezione ad hoc realizzata per gestire il tentativo
 * fallito di rimuovere un libro non presente tra i libri
 * posseduti dall'utente
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class BookNotOwnedException extends Exception {

	private static final long serialVersionUID = -7765313166702498299L;

	public BookNotOwnedException(String message) {
		super(message);
	}
}
