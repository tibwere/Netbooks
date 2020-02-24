package logic.model;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.proposalstate.ProposalStateMachine;
import logic.model.proposalstate.StateMachineImpl;
import logic.model.users.Reader;
import logic.util.enumeration.ProposalEvents;
import logic.util.enumeration.ProposalStates;
/**
 * Classe <b>Client</b> del pattern <i>State</i> dei GoF.<br>
 * Rappresenta l'entita' del dominio di interesse alla quale 
 * si vuole associare un comportamento per mezzo di una state machine.
 * Mantiene un riferimento a {@link StateMachineImpl} attraverso la
 * sua interfaccia {@link ProposalStateMachine}
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
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
