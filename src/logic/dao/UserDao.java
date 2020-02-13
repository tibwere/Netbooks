package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.NoUserFoundException;
import logic.exception.PersistencyException;
import logic.util.enumeration.UserTypes;

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
	
	public static UserTypes findUserByUsernameAndPassword(String user, String passwd) throws NoUserFoundException, PersistencyException {
		CallableStatement stmt = null;
		ResultSet results = null;
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.LOGIN_SP);
			results = DBOperation.bindParameters(stmt, user, passwd);
			
			if (!results.first()) 
				throw new NoUserFoundException("Selected user not exists");
			
			results.first();
			return results.getBoolean("type") ? UserTypes.READER : UserTypes.RETAILER;
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Comunication with DB has failed");
		} finally {
			DBManager.closeDBUtilities(results, stmt);
		}
	}
}
