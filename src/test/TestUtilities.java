package test;

import logic.model.users.Reader;
import logic.model.users.User;

/**
 * Classe di utility per quanto riguarda la stesura dei test
 * Fornisce una serie di operazioni utili in vari test effettuati.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class TestUtilities {
	
	private TestUtilities() {}
	
	public static Reader getTesterReader() {
		
		Reader reader = new Reader(User.DENIED_USERNAME, "test@reader.it", false);
		reader.setFirstName("Test");
		reader.setSecondName("Reader");
		
		return reader;
	}
	
	public static String getTesterPasswd() {
		StringBuilder psw = new StringBuilder();
		
		/* create a 32 digits passwd */
		for (int i = 0; i < 8; ++i) {
			psw.append("test");
		}
		
		return psw.toString();
	}
	
	public static String getWrongTitle() {
		StringBuilder title = new StringBuilder();
		
		/* create a 32 digits passwd */
		for (int i = 0; i < 4; ++i) {
			title.append("too-much-long-title");
		}
		
		return title.toString();
	}

}
