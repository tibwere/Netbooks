package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.NotificationTypes;
import logic.util.enumeration.ProposalStates;
/**
 * Classe <b>Concrete State</b> del pattern <i>State</i> dei GoF.<br>
 * Implementa il comportamento della state machine {@link StateMachineImpl}
 * quando si trova nello stato <i>Initial Proposal</i>
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class StateInitialProposal extends AbstractState {
	
	private StateMachineImpl sm;
	
	public StateInitialProposal(StateMachineImpl sm, ProposalStates state) throws PersistencyException {
		this.sm = sm;
		
		if (state == ProposalStates.DEFAULT)
			this.sm.notifyTargetToProposal();
	}

	@Override
	protected void toAccept() throws PersistencyException, NoStateTransitionException {
		if (this.sm.getSourceBook() != null)
			this.sm.changeToState(new StateIntermediateProposal(this.sm, getState()));
		else
			throw new NoStateTransitionException("This operation does not cause state transition in this state.");
	}

	@Override
	protected void toReject() throws PersistencyException {
		this.sm.changeToState(new StateClosedProposal());
		this.sm.finalNotification(NotificationTypes.REJECTED_PROPOSAL);
	}

	@Override
	protected void acquire(Book book) throws NoStateTransitionException {
		if (this.sm.getSourceBook() == null)
			this.sm.setSourceBook(book);
		else
			throw new NoStateTransitionException("This operation does not cause state transition in this state.");
	}

	@Override
	public ProposalStates getState() {
		return ProposalStates.INITIAL_STATE;
	}
}
