package logic.model;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.proposalstate.ProposalStateMachine;
import logic.model.proposalstate.StateMachineImpl;
import logic.model.users.Reader;
import logic.util.enumeration.ProposalEvents;
import logic.util.enumeration.ProposalStates;

public class Proposal {
	
	private int proposalId;
	private ProposalStateMachine state;
	
	public Proposal (Reader src, Reader tgt, Book tgtBook, Book srcBook, int proposalId, ProposalStates initialState) throws PersistencyException {
		this.proposalId = proposalId;
		state = new StateMachineImpl(src, tgt, tgtBook, srcBook, this, initialState);
	}
	
	public void acceptProposal() throws PersistencyException, NoStateTransitionException {
		state.manageProposal(ProposalEvents.PROPOSAL_ACCEPTED);
	}
	
	public void rejectProposal() throws PersistencyException, NoStateTransitionException {
		state.manageProposal(ProposalEvents.PROPOSAL_REJECTED);
	}

	public void selectBook(Book book) throws PersistencyException, NoStateTransitionException {
		state.acquireBook(book);
		acceptProposal();
	}

	public int getProposalId() {
		return proposalId;
	}
	
	public ProposalStates getCurrState() {
		return state.getCurrentState();
	}

}
