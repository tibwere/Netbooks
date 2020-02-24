package logic.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import logic.db.DBManager;
import logic.db.DBOperation;
import logic.db.Query;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.model.Proposal;
import logic.model.users.Reader;
import logic.util.enumeration.ProposalStates;
/**
 * DAO per l'interazione con lo strato di persistenza 
 * dei dati realtivi all'entity {@link Proposal}
 * @author Cristiano Cuffaro (M. 0252795)
 *
 */

public class ProposalDao {

	private ProposalDao() {
		/* non istanziabile */
	}
	
	public static Proposal buildProposalFromResultSet(ResultSet res) throws PersistencyException {
		String book;
		Book tgtBook = null;
		Book srcBook = null;
		
		try {
			int proposalId = res.getInt("proposal_number");
			ProposalStates initialState = ProposalStates.valueOf(res.getString("status"));
			Reader src = ReaderDao.getEmailAndGenre(res.getString("source"));
			Reader tgt = ReaderDao.getEmailAndGenre(res.getString("target"));
			book = res.getString("book_dest");
			if (book != null)
				tgtBook = BookDao.getBook(res.getString("book_dest"));
			book = res.getString("book_src");
			if (book != null)
				srcBook = BookDao.getBook(res.getString("book_src"));
							
			return new Proposal(src, tgt, tgtBook, srcBook, proposalId, initialState);
			
		} catch(SQLException e) {
			throw new PersistencyException("Unable to build the exchange proposal");
		}
	}
	
	public static Boolean findOpenProposals(String userSrc, String userDest) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet results = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.FIND_OPEN_PROPOSALS_SP);
			results = DBOperation.bindParametersAndExec(stmt, userSrc, userDest);
			
			return results.first();
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to find exchange proposals");
		}
		finally {
			DBManager.closeRs(results);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static int insertNewProposal(String userSrc, String userDest, String status) throws PersistencyException {
		
		CallableStatement stmt = null;
		ResultSet result = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.INSERT_NEW_PROPOSAL_SP);
			result = DBOperation.bindParametersAndExec(stmt, userSrc, userDest, status);
			if (!result.first())
				throw new IllegalStateException("Unexpected application behavior has occurred.");
			
			return result.getInt("number");
			
		} catch (SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to build the exchange proposal");
		}
		finally {
			DBManager.closeRs(result);
			DBManager.closeStmt(stmt);
		}
	}
	
	public static void updateProposalStatus(Proposal proposal, ProposalStates newStatus) throws PersistencyException {
		CallableStatement stmt = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.UPDATE_PROPOSAL_STATUS_SP);
			DBOperation.bindParametersAndExec(stmt, proposal.getProposalId(), newStatus.toString());
			
		} catch(SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to update the state of exchange proposal");
		}
		finally {
			DBManager.closeStmt(stmt);
		}
	}

	public static Proposal getProposal(int proposalId, String destBook, String srcBook) throws PersistencyException {
		CallableStatement stmt = null;
		ResultSet result = null;
		
		try {
			Connection conn = DBManager.getConnection();
			stmt = conn.prepareCall(Query.GET_PROPOSAL_SP);
			result = DBOperation.bindParametersAndExec(stmt, proposalId);
			
			if (!result.first())
				throw new IllegalStateException("Unexpected application behavior has occurred.");
			
			ProposalStates initialState = ProposalStates.valueOf(result.getString("status"));
			Reader src = ReaderDao.getEmailAndGenre(result.getString("reader_src"));
			Reader tgt = ReaderDao.getEmailAndGenre(result.getString("reader_dest"));
			Book targetBook = null;
			Book sourceBook = null;
			if (destBook != null)
				targetBook = BookDao.getBook(destBook);
			if (srcBook != null)
				sourceBook = BookDao.getBook(srcBook);
			
			return new Proposal(src, tgt, targetBook, sourceBook, proposalId, initialState);
						
		} catch(SQLException | ClassNotFoundException e) {
			throw new PersistencyException("Unable to load the exchange proposal");
		}
		finally {
			DBManager.closeStmt(stmt);
			DBManager.closeRs(result);
		}
	}
}
