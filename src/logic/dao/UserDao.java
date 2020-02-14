package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.NoUserFoundException;
import logic.exception.PersistencyException;
import logic.model.users.Reader;
import logic.model.users.User;
import logic.util.enumeration.UserTypes;

/**
 * DAO per l'interazione con lo strato di persistenza 
 * dei dati realtivi all'entity {@link User}
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0252795)
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
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Comunication with DB has failed");
		} finally {
			DBManager.closeRs(readerResults);
			DBManager.closeRs(retailerResults);
			DBManager.closeStmt(stmt);
		}
	}

	public static Reader getNameAndSurname(String user) throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet results = null;
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_FIRST_AND_SECOND_NAME_SP);
			results = DBOperation.bindParametersAndExec(stmt, user);
			
			if (!results.first())
				throw new IllegalStateException("Session must be consistent");
			
			results.first();
			Reader reader = new Reader(user);
			reader.setFirstName(results.getString("first_name"));
			reader.setSecondName(results.getString("second_name"));
			
			return reader;
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Comunication with DB has failed");
		} finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}

	public static void insertNewBookInOwnedList(String isbn, String user) throws AlreadyOwnedBookException, PersistencyException {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.INSERT_NEW_BOOK_TO_OWNEDLIST);
			DBOperation.bindParametersAndExec(stmt, user, isbn);
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new AlreadyOwnedBookException("You already own this book. Nothing has changed");
		} finally {
			DBManager.closeStmt(stmt);
		}
		
	}
}
