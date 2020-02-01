package logic.dao;

import logic.util.enumeration.UserType;

/**
 * Versione singleton del DAO per l'interazione
 * con lo strato di persistenza per l'entity {@link AbstractUser}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class AbstractUserDao {
	
	private static AbstractUserDao instance = null;
	
	private AbstractUserDao() {
		/* da aggiungere la gestione della connessione al db */
	}
	
	public static AbstractUserDao getInstance() {
		if (instance == null)
			return new AbstractUserDao();
		else
			return instance;
	}
	
	public UserType findUserByUsernameAndPassword(String user, String passwd) {
		if (user.equals("acs") && passwd.equals("acs"))
			return UserType.READER;
		else if (user.equals("ret") && passwd.equals("ret"))
			return UserType.RETAILER;
		else
			return UserType.INVALID_USER;
	}
}
