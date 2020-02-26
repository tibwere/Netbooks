package test.st;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import logic.bean.BookEvaluationBean;
import logic.exception.WrongSyntaxException;

/**
 * <b>JUnit</b> test utilizzato per verificare la robustezza
 * del sistema al tentativo di inserimento di una recensione il cui titolo
 * supera i constraint della base di dati.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class TestWrongInputEvaluation {
	
	private static final String TOO_MUCH_LONG_TITLE = "too-much-long-title-too-much-long-title-too-much-long-title-too-much-long-title";
	
	@Test
	public void testWrongTitle() {
		
		assertThrows(WrongSyntaxException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				BookEvaluationBean bean = new BookEvaluationBean();
				bean.setTitle(TOO_MUCH_LONG_TITLE);
			}
			
		});
	}

}
