package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.NotificationTypes;
import logic.util.enumeration.ProposalStates;
/**
 * Classe <b>Concrete State</b> del pattern <i>State</i> dei GoF.<br>
 * Implementa il comportamento della state machine {@link StateMachineImpl}
 * quando si trova nello stato <i>Intermediate Proposal</i>
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class StateIntermediateProposal extends AbstractState {
	
	private StateMachineImpl sm;

	public StateIntermediateProposal(StateMachineImpl sm, ProposalStates state) throws PersistencyException {
		this.sm = sm;
		
		if (state != ProposalStates.INTERMEDIATE_STATE)
			this.sm.notifySourceToAnswer();
	}
	@Override
	protected void toAccept() throws PersistencyException {
		this.sm.changeToState(new StateClosedProposal());
		sm.finalNotification(NotificationTypes.ENDED_PROPOSAL);
	}

	@Override
	protected void toReject() throws PersistencyException {
		this.sm.changeToState(new StateClosedProposal());
		this.sm.finalNotification(NotificationTypes.REJECTED_PROPOSAL);
	}
	@Override
	protected void acquire(Book book) throws NoStateTransitionException {
		throw new NoStateTransitionException("This operation does not cause state transition in this state.");
	}
	
	@Override
	public ProposalStates getState() {
		return ProposalStates.INTERMEDIATE_STATE;
	}

}