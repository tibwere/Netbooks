package test;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import logic.dao.ReaderDao;
import logic.exception.PersistencyException;
import logic.exception.UserAlreadySignedException;
import logic.model.users.Reader;

public class TestDoubleSignup {
	
	@Test
	public void testDoubleSignupSameUsername() throws PersistencyException {
		
		Reader reader = TestUtilities.getTesterReader();
		
		assertThrows(UserAlreadySignedException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				reader.store(TestUtilities.getTesterPasswd());
				reader.store(TestUtilities.getTesterPasswd());
			}
		});
		
		ReaderDao.deleteReaderForTest();		
	}

}
