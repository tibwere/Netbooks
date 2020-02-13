package logic.model.proposalstate;

import logic.model.Book;
import logic.util.enumeration.ProposalEvents;

public interface ProposalStateMachine {
	
	public void manageProposal(ProposalEvents e);
	public void acquireBook(Book book);
	public void getCurrentState();
}
