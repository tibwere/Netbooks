package logic.model.proposal;

import logic.model.Book;
import logic.model.users.Reader;
import logic.model.users.User;
import logic.util.enumeration.NotificationTypes;

public class ProposalNotification {
	
	private Reader src;
	private NotificationTypes type;
	private Book destBook;
	private Book srcBook;
	private String proposalId;
	
	public ProposalNotification(String proposalId, Reader src, NotificationTypes type) {
		this.src = src;
		this.type = type;
		this.proposalId = proposalId;
	}

	public User getSrc() {
		return src;
	}

	public Book getDestBook() {
		return destBook;
	}

	public void setDestBook(Book destBook) {
		this.destBook = destBook;
	}

	public Book getSrcBook() {
		return srcBook;
	}

	public void setSrcBook(Book srcBook) {
		this.srcBook = srcBook;
	}

	public String getMessage() {
		switch (this.type) {
		case INITIAL_PROPOSAL:
			return "The user '" + src.getUsername() + "' would like to exchange your book '" + destBook.getTitle() + "'. You can choose one of his books in return, or reject the proposal.";
		case INTERMEDIATE_PROPOSAL:
			return "The user '" + src.getUsername() + "' would like to accept your proposal and exchange: '" + srcBook.getTitle() + "' for '" + destBook.getTitle() + "'. Do you want to end the exchange?";
		case FINAL_PROPOSAL:
			String pron;
			if (src.getGender().equals("F"))
				pron = "her";
			else
				pron = "him";
			return "The exchange with '" + src.getUsername() + "' was successful. Contact " + pron + " at the e-mail address: " + src.getEmail();
		default:
			return "The exchange with '" + src.getUsername() + "' was unsuccessful.";
		}
	}

	public String getProposalId() {
		return proposalId;
	}
}
