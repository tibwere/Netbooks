package logic.model.users;

import java.util.ArrayList;
import java.util.List;

import logic.dao.BookDao;
import logic.model.Book;
import logic.model.proposal.ProposalNotification;

/**
 * 
 * @author Cristiano Cuffaro
 *
 */

public class Reader extends User {
	
	private String gender;
	private List<ProposalNotification> noticeBoard;
	
	public Reader (String username, String email, String gender) {
		super(username, email);
		this.gender = gender;
		noticeBoard = new ArrayList<>();
	}

	public List<ProposalNotification> getNotifications() {
		return noticeBoard;
	}

	public void addNotification(ProposalNotification notification) {
		noticeBoard.add(notification);
	}

	public String getGender() {
		return gender;
	}
	
	public List<Book> getExchangeList() {
		
		return BookDao.getInstance().findExchangeableBooks(username);
		
	}
}

