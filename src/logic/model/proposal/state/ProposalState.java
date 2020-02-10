package logic.model.proposal.state;

public abstract class ProposalState {

	public static ProposalState getInitialState(StateMachineImpl sm) {
		
		return new StateInitialProposal(sm);
	}

	protected abstract void toAccept(StateMachineImpl sm);

	protected abstract void toReject(StateMachineImpl sm);
}
