package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.PersistencyException;
import logic.exception.UserAlreadySignedException;
import logic.model.users.Reader;

public class ReaderDao {
	
	private ReaderDao() {
		/* non istanziabile */
	}
	
	public static Reader getNameAndSurname(String user) throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet results = null;
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_READER_SP);
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

	public static void saveReaderInDB(Reader reader, String password) throws UserAlreadySignedException {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.INSERT_NEW_READER);
			DBOperation.execInsertReader(stmt, reader, password);
		} catch (SQLException | ClassNotFoundException e) {
			throw new UserAlreadySignedException("The user you've inserted already exists");
		}
	}

	public static boolean checkIfCurrReaderOwnBook(String reader, String book) throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.CHECK_IF_OWNED);
			results = DBOperation.bindParametersAndExec(stmt, reader, book);
			
			return results.first();
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to connect to DB");
		}
	}
	

}
