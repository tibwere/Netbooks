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
	
	private static String TESTER_PASSWD_NOT_HASHED = "testtesttesttesttesttesttesttest";
	private static String TESTER_PASSWD_HASHED = "25fb34f99389a5c7d0cd8d0c1ef1a0ad";
	
	private TestUtilities() {}
	
	public static Reader getTesterReader() {
		
		Reader reader = new Reader(User.DENIED_USERNAME, "test@reader.it", false);
		reader.setFirstName("Test");
		reader.setSecondName("Reader");
		
		return reader;
	}
	
	public static String getTesterPasswd(boolean isHashed) {
		return (isHashed) ? TESTER_PASSWD_HASHED : TESTER_PASSWD_NOT_HASHED;
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
