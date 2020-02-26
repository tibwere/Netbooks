package test.st;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import logic.bean.UserBean;
import logic.dao.ReaderDao;
import logic.exception.PersistencyException;
import logic.exception.UserAlreadySignedException;
import logic.model.users.Reader;
import test.TestUtilities;

/**
 * <b>JUnit</b> test utilizzato per verificare la robustezza
 * del sistema al tentativo di registrazione multipla dello stesso utente
 * Per far ci� e' stato utilizzato un utente ad hoc ovvero l'utente di test
 * il cui username � vietato in fase di registrazione tramite un opportuno filtraggio
 * in {@link UserBean#setUsername(String)}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class TestDoubleSignup {
	
	@Test
	public void testDoubleSignupSameUsername() throws PersistencyException {
		
		Reader reader = TestUtilities.getTesterReader();
		
		assertThrows(UserAlreadySignedException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				reader.store(TestUtilities.getTesterPasswd(true));
				reader.store(TestUtilities.getTesterPasswd(true));
			}
		});
		
		ReaderDao.deleteReaderForTest();		
	}

}
