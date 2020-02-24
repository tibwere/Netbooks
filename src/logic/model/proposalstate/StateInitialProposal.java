package logic.model.proposalstate;

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
	
	public StateInitialProposal(StateMachineImpl sm, ProposalStates state) throws PersistencyException {
		if (state == ProposalStates.DEFAULT)
			sm.notifyTargetToProposal();
	}

	@Override
	protected void toAccept(StateMachineImpl sm) throws PersistencyException {
		if (sm.getSourceBook() != null)
			sm.changeToState(new StateIntermediateProposal(sm, getState()));
	}

	@Override
	protected void toReject(StateMachineImpl sm) throws PersistencyException {
		sm.changeToState(new StateClosedProposal());
		sm.finalNotification(NotificationTypes.REJECTED_PROPOSAL);
	}

	@Override
	protected void acquire(StateMachineImpl sm, Book book) {
		sm.setSourceBook(book);
		
	}

	@Override
	public ProposalStates getState() {
		return ProposalStates.INITIAL_STATE;
	}
}
