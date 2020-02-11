package logic.model.proposalstate;

public class StateIntermediateProposal extends ProposalState {

	public StateIntermediateProposal(StateMachineImpl sm) {
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

}
