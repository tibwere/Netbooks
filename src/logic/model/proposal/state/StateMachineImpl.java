package logic.model.proposal.state;

import logic.model.Book;
import logic.model.proposal.ProposalNotification;
import logic.model.users.Reader;
import logic.model.users.User;
import logic.util.enumeration.NotificationTypes;
import logic.util.enumeration.ProposalEvents;

public class StateMachineImpl implements ProposalStateMachine {
	
	private User source;
	private User target;
	private Book sourceBook;
	private Book targetBook;
	private ProposalState current;
	private String proposalId;
	
	public StateMachineImpl(Reader src, Reader tgt, Book tgtBook, String proposalId) {
		this.source = src;
		this.target = tgt;
		this.targetBook = tgtBook;
		this.proposalId = proposalId;
		current = ProposalState.getInitialState(this);
	}

	@Override
	public void manageProposal(ProposalEvents e) {
		if (e == ProposalEvents.PROPOSAL_ACCEPTED)
			current.toAccept(this);
		else
			current.toReject(this);
	}
	
	@Override
	public void acquireBook(Book book) {
		this.sourceBook = book;
	}
	
	public void changeToState(ProposalState state) {
		current = state;
	}
	
	public void exchangeBooks() {
//		scambiare fisicamente i libri degli utenti
	}
	
	public void notifyTargetToProposal() {
		ProposalNotification notification = new ProposalNotification(proposalId, (Reader) source, NotificationTypes.INITIAL_PROPOSAL);
		notification.setDestBook(targetBook);
		((Reader) target).addNotification(notification);
	}
	
	public void notifySourceToAnswer() {
		ProposalNotification notification = new ProposalNotification(proposalId, (Reader) target, NotificationTypes.INTERMEDIATE_PROPOSAL);
		notification.setSrcBook(targetBook);
		notification.setDestBook(sourceBook);
		((Reader) source).addNotification(notification);
	}
	
	public void notifyOfSuccess() {
		((Reader) source).addNotification(new ProposalNotification(proposalId, (Reader) target, NotificationTypes.FINAL_PROPOSAL));
		((Reader) target).addNotification(new ProposalNotification(proposalId, (Reader) source, NotificationTypes.FINAL_PROPOSAL));
	}
	
	public void notifyOfFailure() {
		((Reader) source).addNotification(new ProposalNotification(proposalId, (Reader) target, NotificationTypes.REJECTED_PROPOSAL));
		((Reader) target).addNotification(new ProposalNotification(proposalId, (Reader) source, NotificationTypes.REJECTED_PROPOSAL));
	}
}
