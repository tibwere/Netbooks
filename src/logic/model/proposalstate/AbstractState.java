package logic.model.proposalstate;

import logic.model.Book;
import logic.util.enumeration.ProposalStates;

public abstract class AbstractState {

	public static AbstractState getInitialState(StateMachineImpl sm, ProposalStates initialState) {
		if (initialState == ProposalStates.INTERMEDIATE_STATE)
			return new StateIntermediateProposal(sm, initialState);
		return new StateInitialProposal(sm, initialState);
	}

	protected abstract void toAccept(StateMachineImpl sm);

	protected abstract void toReject(StateMachineImpl sm);
	
	protected abstract void acquire(StateMachineImpl sm, Book book);

	protected abstract ProposalStates getState();
}
