package logic.model.proposalstate;

import logic.model.Book;
import logic.util.enumeration.ProposalStates;

public class StateIntermediateProposal extends AbstractState {

	public StateIntermediateProposal(StateMachineImpl sm, ProposalStates state) {
		if (state != ProposalStates.INTERMEDIATE_STATE)
			sm.notifySourceToAnswer();
	}
	@Override
	protected void toAccept(StateMachineImpl sm) {
		sm.notifyOfSuccess();
	}

	@Override
	protected void toReject(StateMachineImpl sm) {
		sm.notifyOfFailure();
	}
	@Override
	protected void acquire(StateMachineImpl sm, Book book) {
		
	}
	
	@Override
	public ProposalStates getState() {
		return ProposalStates.INTERMEDIATE_STATE;
	}

}