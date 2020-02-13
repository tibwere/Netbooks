package logic.model.proposalstate;

import logic.model.Book;
import logic.model.Proposal;
import logic.model.ProposalNotification;
import logic.model.users.Reader;
import logic.util.enumeration.NotificationTypes;
import logic.util.enumeration.ProposalEvents;
import logic.util.enumeration.ProposalStates;

public class StateMachineImpl implements ProposalStateMachine {
	
	private Reader source;
	private Reader target;
	private Book sourceBook;
	private Book targetBook;
	private AbstractState current;
	private Proposal proposal;
	
	private StateMachineImpl(Reader src, Reader tgt, Book tgtBook, Book srcBook, Proposal proposal) {
		this.source = src;
		this.target = tgt;
		this.targetBook = tgtBook;
		this.sourceBook = srcBook;
		this.proposal = proposal;
	}

	public StateMachineImpl(Reader src, Reader tgt, Book tgtBook, Book srcBook, Proposal proposal, ProposalStates initialState) {
		this(src, tgt, tgtBook, srcBook, proposal);
		current = AbstractState.getInitialState(this, initialState);
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
		current.acquire(this, book);
	}
	
	@Override
	public void getCurrentState() {
		current.getState();		
	}
	
	public Book getSourceBook() {
		return sourceBook;
	}
	
	public void setSourceBook(Book sourceBook) {
		this.sourceBook = sourceBook;
	}
	
	public void changeToState(AbstractState state) {
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
