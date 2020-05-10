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
import logic.exception.NotAccesibleConfigurationException;
import logic.exception.PersistencyException;
import logic.model.Proposal;
import logic.model.ProposalNotification;
import logic.model.users.Reader;
import logic.util.enumeration.NotificationTypes;
/**
 * DAO per l'interazione con lo strato di persistenza 
 * dei dati realtivi all'entity {@link ProposalNotification}
 * @author Cristiano Cuffaro (M. 0252795)
 *
 */

public class NotificationDao {

	private NotificationDao() {
		/* non istanziabile */
	}
	
	public static void insertNewNotifForUser(String userDest, ProposalNotification notif) throws PersistencyException {
		
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.INSERT_NEW_NOTIF_FOR_USER_SP);
			
			String bookSrc = notif.getSrcBook() == null ? "NULL" : notif.getSrcBook().getIsbn();
			String bookDest = notif.getDestBook() == null ? "NULL" : notif.getDestBook().getIsbn();
			
			DBOperation.bindParametersAndExec(stmt, notif.getProposal().getProposalId(), userDest, 
					notif.getType().toString(), bookSrc, bookDest);
			
		} catch(SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Unable to send notification");
		}
		finally {
			DBManager.closeStmt(stmt);
		}
	}
	
	public static List<ProposalNotification> getUserNotifications(String username) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			List<ProposalNotification> notifications = new ArrayList<>();
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_USER_NOTIFICATIONS_SP);
			results = DBOperation.bindParametersAndExec(stmt, username);
			
			while (results.next()) {
				Proposal proposal = ProposalDao.buildProposalFromResultSet(results);
				Reader src = username.equals(results.getString("source")) ? 
						ReaderDao.getEmailAndGenre(results.getString("target")) : ReaderDao.getEmailAndGenre(results.getString("source"));
	
				NotificationTypes type = NotificationTypes.valueOf(results.getString("type"));
				ProposalNotification notif = new ProposalNotification(proposal, src, type);
				if (results.getString("book_dest") != null)
					notif.setDestBook(BookDao.getBook(results.getString("book_dest")));
				if (results.getString("book_src") != null)
					notif.setSrcBook(BookDao.getBook(results.getString("book_src")));
				
				notifications.add(notif);
			}
			return notifications;
			
		} catch(SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Unable to load notifications");
		}
	}

	public static void deleteNotificationForUser(int proposalId, NotificationTypes type, String user) throws PersistencyException {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.DELETE_NOTIF_FOR_USER_SP);
			DBOperation.bindParametersAndExec(stmt, proposalId, type.toString(), user);
			
		} catch(SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Unable to delete notification");
		}
		finally {
			DBManager.closeStmt(stmt);
		}
	}

	public static boolean findUnreadNotifications(String usr) throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.FIND_UNREAD_NOTIFICATIONS_SP);
			results = DBOperation.bindParametersAndExec(stmt, usr);
			
			return results.first();
			
		} catch(SQLException | ClassNotFoundException | NotAccesibleConfigurationException e) {
			throw new PersistencyException("Unable to find unread notifications");
		}
		finally {
			DBManager.closeStmt(stmt);
			DBManager.closeRs(results);
		}
	}
}
