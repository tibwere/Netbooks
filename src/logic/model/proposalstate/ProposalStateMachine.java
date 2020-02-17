package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.ProposalEvents;
import logic.util.enumeration.ProposalStates;

public interface ProposalStateMachine {
	
	public void manageProposal(ProposalEvents e) throws PersistencyException, NoStateTransitionException;
	public void acquireBook(Book book);
	public ProposalStates getCurrentState();
}
