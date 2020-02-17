package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
	
public static List<Reader> findOwners(String currUser) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			List<Reader> owners = new ArrayList<>();
			
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_OWNERS_SP);
			results = DBOperation.bindParametersAndExec(stmt, currUser);
						
			while (results.next()) {
				Reader r = new Reader(results.getString("reader"));
				owners.add(r);
			}
			
			return owners;
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to get owners of books");
		}
		finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static Reader getEmailAndGenre(String username) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet result = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_EMAIL_AND_GENRE_SP);
			result = DBOperation.bindParametersAndExec(stmt, username);
			
			if (!result.first())
				throw new IllegalStateException("Unexpected application behavior has occurred.");
			
			return new Reader(username, result.getString("email"), result.getBoolean("gender"));
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to load the reader");
		} finally {
			DBManager.closeRs(result);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static Boolean checkOwnership(String user, String isbn) throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet result = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.CHECK_OWNERSHIP_SP);
			result = DBOperation.bindParametersAndExec(stmt, user, isbn);
			
			return result.first();
			
		} catch(SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to check book ownership");
		}
		finally {
			DBManager.closeStmt(stmt);
			DBManager.closeRs(result);
		}
	}

	public static void swapOwnership(String sourceId, String srcBook, String targetId, String tgtBook) throws PersistencyException {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.SWAP_OWNERSHIP_SP);
			DBOperation.bindParametersAndExec(stmt, sourceId, srcBook, targetId, tgtBook);
			
		} catch(SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to swap books");
		}
		finally {
			DBManager.closeStmt(stmt);
		}
	}
}
