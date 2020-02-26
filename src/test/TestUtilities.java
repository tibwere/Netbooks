package test;

/**
 * Classe di utility per quanto riguarda la stesura dei test
 * Fornisce operazioni utili in vari test effettuati.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class TestUtilities {
	
	private static final String TESTER_PASSWD_NOT_HASHED = "qwerty12345";
	private static final String TESTER_PASSWD_HASHED = "85064efb60a9601805dcea56ec5402f7";
	
	private TestUtilities() {}
	
	public static String getTesterPasswd(boolean isHashed) {
		return (isHashed) ? TESTER_PASSWD_HASHED : TESTER_PASSWD_NOT_HASHED;
	}

}
