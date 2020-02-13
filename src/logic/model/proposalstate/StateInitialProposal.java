package logic.model.proposalstate;

import logic.model.Book;
import logic.util.enumeration.ProposalStates;

public class StateInitialProposal extends AbstractState {
	
	public StateInitialProposal(StateMachineImpl sm, ProposalStates state) {
		if (state == ProposalStates.DEFAULT)
			sm.notifyTargetToProposal();
	}

	@Override
	protected void toAccept(StateMachineImpl sm) {
		if (sm.getSourceBook() != null)
			sm.changeToState(new StateIntermediateProposal(sm, this.getState()));
	}

	@Override
	protected void toReject(StateMachineImpl sm) {
		sm.notifyOfFailure();
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
