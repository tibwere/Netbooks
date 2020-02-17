package logic.exception;

public class NoStateTransitionException extends Exception{

	private static final long serialVersionUID = 7987971393064792812L;

	public NoStateTransitionException(String message) {
		super(message);
	}
}
