package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.NoUserFoundException;
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.model.users.User;
import logic.util.enumeration.UserTypes;

/**
 * DAO per l'interazione con lo strato di persistenza 
 * dei dati realtivi all'entity {@link User}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class UserDao {
		
	private UserDao() {
		/* non instanziabile */
	}
	
	public static UserTypes findUserByUsernameAndPassword(String user, String passwd) throws NoUserFoundException, PersistencyException {
		CallableStatement stmt = null;
		ResultSet readerResults = null;
		ResultSet retailerResults = null;
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.FIND_READER_SP);
			readerResults = DBOperation.bindParametersAndExec(stmt, user, passwd);
			
			if (!readerResults.first()) {
				stmt = conn.prepareCall(Query.FIND_RETAILER_SP);
				retailerResults = DBOperation.bindParametersAndExec(stmt, user, passwd);
				
				if (!retailerResults.first())
					throw new NoUserFoundException("Selected user does not exists");
				else 
					return UserTypes.RETAILER;
			}
			
			return UserTypes.READER;
			
		} catch (SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Comunication with DB has failed");
		} finally {
			DBManager.closeRs(readerResults);
			DBManager.closeRs(retailerResults);
			DBManager.closeStmt(stmt);
		}
	}
}
