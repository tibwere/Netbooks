package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.NoUserFoundException;
import logic.util.enumeration.UserType;

/**
 * Versione singleton del DAO per l'interazione
 * con lo strato di persistenza per l'entity {@link AbstractUser}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class UserDao {
		
	private UserDao() {
		/* non instanziabile */
	}
	
	public static UserType findUserByUsernameAndPassword(String user, String passwd) throws SQLException, NoUserFoundException, ClassNotFoundException {
		Connection conn = DBManager.getConnection();
		CallableStatement stmt = conn.prepareCall(Query.LOGIN_SP);
		ResultSet results = DBOperation.doLogin(stmt, user, passwd);
		
		if (!results.first()) 
			throw new NoUserFoundException("Selected user not exists");
		
		results.first();
		if (results.getBoolean("type"))
			return UserType.READER;
		else
			return UserType.RETAILER;
	}
}
