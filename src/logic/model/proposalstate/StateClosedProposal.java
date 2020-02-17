package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.ProposalStates;

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
