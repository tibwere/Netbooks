package logic.model.proposalstate;

import logic.model.Book;
import logic.model.Proposal;
import logic.model.ProposalNotification;
import logic.model.users.Reader;
import logic.util.enumeration.NotificationTypes;
import logic.util.enumeration.ProposalEvents;

public class StateMachineImpl implements ProposalStateMachine {
	
	private Reader source;
	private Reader target;
	private Book sourceBook;
	private Book targetBook;
	private ProposalState current;
	private Proposal proposal;
	
	public StateMachineImpl(Reader src, Reader tgt, Book tgtBook, Proposal proposal) {
		this.source = src;
		this.target = tgt;
		this.targetBook = tgtBook;
		this.proposal = proposal;
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
		ProposalNotification notification = new ProposalNotification(proposal, source, NotificationTypes.INITIAL_PROPOSAL);
		notification.setDestBook(targetBook);
		target.addNotification(notification);
	}
	
	public void notifySourceToAnswer() {
		ProposalNotification notification = new ProposalNotification(proposal, target, NotificationTypes.INTERMEDIATE_PROPOSAL);
		notification.setSrcBook(targetBook);
		notification.setDestBook(sourceBook);
		source.addNotification(notification);
	}
	
	public void notifyOfSuccess() {
		source.addNotification(new ProposalNotification(proposal, target, NotificationTypes.FINAL_PROPOSAL));
		target.addNotification(new ProposalNotification(proposal, source, NotificationTypes.FINAL_PROPOSAL));
	}
	
	public void notifyOfFailure() {
		source.addNotification(new ProposalNotification(proposal, target, NotificationTypes.REJECTED_PROPOSAL));
		target.addNotification(new ProposalNotification(proposal, source, NotificationTypes.REJECTED_PROPOSAL));
	}
}
