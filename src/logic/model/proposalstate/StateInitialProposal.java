package logic.model.proposalstate;

public class StateInitialProposal extends ProposalState {
	
	public StateInitialProposal(StateMachineImpl sm) {
		sm.notifyTargetToProposal();
	}

	@Override
	protected void toAccept(StateMachineImpl sm) {
		sm.changeToState(new StateIntermediateProposal(sm));
	}

	@Override
	protected void toReject(StateMachineImpl sm) {
		sm.notifyOfFailure();
	}

	
}
