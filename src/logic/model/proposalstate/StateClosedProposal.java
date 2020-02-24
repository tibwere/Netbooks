package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.ProposalStates;
/**
 * Classe <b>Concrete State</b> del pattern <i>State</i> dei GoF.<br>
 * Implementa il comportamento della state machine {@link StateMachineImpl}
 * quando si trova nello stato <i>Closed Proposal</i>
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class StateClosedProposal extends AbstractState {

	@Override
	protected void toAccept(StateMachineImpl sm) throws PersistencyException, NoStateTransitionException {
		throw new NoStateTransitionException(AbstractState.NO_ACTION_EXCEPTION);		
	}

	@Override
	protected void toReject(StateMachineImpl sm) throws PersistencyException, NoStateTransitionException {
		throw new NoStateTransitionException(AbstractState.NO_ACTION_EXCEPTION);		
	}

	@Override
	protected void acquire(StateMachineImpl sm, Book book) throws NoStateTransitionException {
		throw new NoStateTransitionException(AbstractState.NO_ACTION_EXCEPTION);
	}

	@Override
	protected ProposalStates getState() {
		return ProposalStates.CLOSED_STATE;
	}

}
