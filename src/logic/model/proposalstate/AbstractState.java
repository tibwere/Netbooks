package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.ProposalStates;

public abstract class AbstractState {
	protected static final String NO_ACTION_EXCEPTION = "This operation does not cause state transition in this state";

	public static AbstractState getInitialState(StateMachineImpl sm, ProposalStates initialState) throws PersistencyException {
		if (initialState == ProposalStates.INTERMEDIATE_STATE)
			return new StateIntermediateProposal(sm, initialState);
		return new StateInitialProposal(sm, initialState);
	}

	protected abstract void toAccept(StateMachineImpl sm) throws PersistencyException, NoStateTransitionException;

	protected abstract void toReject(StateMachineImpl sm) throws PersistencyException, NoStateTransitionException;
	
	protected abstract void acquire(StateMachineImpl sm, Book book) throws NoStateTransitionException;

	protected abstract ProposalStates getState();
}
