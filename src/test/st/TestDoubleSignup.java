package test.st;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import logic.bean.UserBean;
import logic.exception.PersistencyException;
import logic.exception.UserAlreadySignedException;
import logic.model.users.Reader;
import logic.model.users.User;
import test.TestUtilities;

/**
 * <b>JUnit</b> test utilizzato per verificare la robustezza
 * del sistema al tentativo di registrazione multipla dello stesso utente
 * Per far ciò e' stato utilizzato un utente ad hoc ovvero l'utente di test
 * il cui username è vietato in fase di registrazione tramite un opportuno filtraggio
 * in {@link UserBean#setUsername(String)} ma che è di default presente nella base di dati
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class TestDoubleSignup {
	
	@Test
	public void testDoubleSignupSameUsername() throws PersistencyException {
		
		Reader reader = getTesterReader();
		
		assertThrows(UserAlreadySignedException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				/* e' sufficiente un inserimento perche' l'utente è gia presente */
				reader.store(TestUtilities.getTesterPasswd(true));
			}
		});
	}
	
	private Reader getTesterReader() {
		
		Reader reader = new Reader(User.TESTER_USERNAME, "test@reader.it", false);
		reader.setFirstName("Test");
		reader.setSecondName("Reader");
		
		return reader;
	}

}
