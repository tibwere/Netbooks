package logic.model;

import logic.model.users.Reader;
import logic.model.users.User;
import logic.util.enumeration.NotificationTypes;
/**
 * Entita' del dominio di interesse: Notifica
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class ProposalNotification {
	
	private Reader src;
	private NotificationTypes type;
	private Book destBook = null;
	private Book srcBook = null;
	private Proposal proposal;
	
	public ProposalNotification(Proposal proposal, Reader src, NotificationTypes type) {
		this.src = src;
		this.type = type;
		this.proposal = proposal;
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
	
	public NotificationTypes getType() {
		return type;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public String getMessage() {
		String pron;
		switch (this.getType()) {
		case INITIAL_PROPOSAL:
			pron = src.isFemale() ? "her" : "his";
			return "The user '" + src.getUsername() + "' would like to exchange your book '" + destBook.getTitle() + "'. You can choose one of " + pron + " books in return, or reject the proposal.";
		case INTERMEDIATE_PROPOSAL:
			return "The user '" + src.getUsername() + "' would like to accept your proposal and exchange: '" + srcBook.getTitle() + "' for '" + destBook.getTitle() + "'. Do you want to end the exchange?";
		case ENDED_PROPOSAL:
			pron = src.isFemale() ? "her" : "him";
			return "The exchange with '" + src.getUsername() + "' was successful. Contact " + pron + " at the e-mail address: " + src.getEmail();
		default:
			return "The exchange with '" + src.getUsername() + "' was unsuccessful.";
		}
	}
}
