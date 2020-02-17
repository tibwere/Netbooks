package logic.model.proposalstate;

import javafx.scene.control.Alert.AlertType;
import logic.controller.ExchangeBookController;
import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.model.Proposal;
import logic.model.ProposalNotification;
import logic.model.users.Reader;
import logic.util.GraphicalElements;
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
	private ExchangeBookController controller;

	public StateMachineImpl(Reader src, Reader tgt, Book tgtBook, Book srcBook, Proposal proposal, ProposalStates initialState) throws PersistencyException {
		this.source = src;
		this.target = tgt;
		this.targetBook = tgtBook;
		this.sourceBook = srcBook;
		this.proposal = proposal;
		controller = new ExchangeBookController();
		current = AbstractState.getInitialState(this, initialState);
	}
	
	@Override
	public void manageProposal(ProposalEvents e) throws PersistencyException, NoStateTransitionException {
		if (e == ProposalEvents.PROPOSAL_ACCEPTED)
			current.toAccept(this);
		else
			current.toReject(this);
	}
	
	@Override
	public void acquireBook(Book book) {
		try {
			current.acquire(this, book);
		} catch (NoStateTransitionException e) {
			GraphicalElements.showDialog(AlertType.WARNING, e.getMessage());
		}
	}
	
	@Override
	public ProposalStates getCurrentState() {
		return current.getState();		
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
	
	public void notifyTargetToProposal() throws PersistencyException {
		ProposalNotification notification = new ProposalNotification(proposal, source, NotificationTypes.INITIAL_PROPOSAL);
		notification.setDestBook(targetBook);
		controller.addNotification(target, notification);
	}
	
	public void notifySourceToAnswer() throws PersistencyException {
		ProposalNotification notification = new ProposalNotification(proposal, target, NotificationTypes.INTERMEDIATE_PROPOSAL);
		notification.setSrcBook(targetBook);
		notification.setDestBook(sourceBook);
		controller.addNotification(source, notification);
	}
	
	public void finalNotification(NotificationTypes type) throws PersistencyException {
		controller.addNotification(source, new ProposalNotification(proposal, target, type));
		controller.addNotification(target, new ProposalNotification(proposal, source, type));
	}
}
