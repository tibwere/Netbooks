package test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import logic.bean.BookEvaluationBean;
import logic.exception.WrongSyntaxException;

public class TestWrongInputEvaluation {
	
	@Test
	public void testWrongTitle() {
		
		assertThrows(WrongSyntaxException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				BookEvaluationBean bean = new BookEvaluationBean();
				bean.setTitle(TestUtilities.getWrongTitle());
			}
			
		});
	}

}
