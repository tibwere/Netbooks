package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.NotificationTypes;
import logic.util.enumeration.ProposalStates;

public class StateIntermediateProposal extends AbstractState {

	public StateIntermediateProposal(StateMachineImpl sm, ProposalStates state) throws PersistencyException {
		if (state != ProposalStates.INTERMEDIATE_STATE)
			sm.notifySourceToAnswer();
	}
	@Override
	protected void toAccept(StateMachineImpl sm) throws PersistencyException {
		sm.changeToState(new StateClosedProposal());
		sm.finalNotification(NotificationTypes.ENDED_PROPOSAL);
	}

	@Override
	protected void toReject(StateMachineImpl sm) throws PersistencyException {
		sm.changeToState(new StateClosedProposal());
		sm.finalNotification(NotificationTypes.REJECTED_PROPOSAL);
	}
	@Override
	protected void acquire(StateMachineImpl sm, Book book) throws NoStateTransitionException {
		throw new NoStateTransitionException("This operation does not cause state transition in this state.");
	}
	
	@Override
	public ProposalStates getState() {
		return ProposalStates.INTERMEDIATE_STATE;
	}

}