package logic.model;

import logic.model.proposalstate.ProposalStateMachine;
import logic.model.proposalstate.StateMachineImpl;
import logic.model.users.Reader;
import logic.util.enumeration.ProposalEvents;

public class Proposal {
	
	private String proposalId;
	private ProposalStateMachine state;
	
	public Proposal (Reader src, Reader tgt, Book tgtBook, String proposalId) {
		this.proposalId = proposalId;
		state = new StateMachineImpl(src, tgt, tgtBook, this);
	}
	
	public void acceptProposal() {
		state.manageProposal(ProposalEvents.PROPOSAL_ACCEPTED);
	}
	
	public void rejectProposal() {
		state.manageProposal(ProposalEvents.PROPOSAL_REJECTED);
	}

	public void selectBook(Book book) {
		state.acquireBook(book);
		acceptProposal();
	}

	public String getProposalId() {
		return proposalId;
	}

}
