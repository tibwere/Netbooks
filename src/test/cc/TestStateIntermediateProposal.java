package test.cc;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.model.Proposal;
import logic.model.users.Reader;
import logic.util.enumeration.ProposalStates;

public class TestStateIntermediateProposal {

	@Test
	public void testAcquireAnyBook() throws PersistencyException {
		
		Reader src = new Reader("usernameSrc");
		Reader tgt = new Reader("usernameTgt");
		Book srcBook = new Book();
		Book tgtBook = new Book();
		int proposalId = 123456789;
		ProposalStates initialState = ProposalStates.INTERMEDIATE_STATE;
		
		Proposal proposal = new Proposal(src, tgt, tgtBook, srcBook, proposalId, initialState);
		
		assertThrows(NoStateTransitionException.class, new Executable() {
			
			@Override
			public void execute() throws Throwable {
				proposal.selectBook(new Book());
			}
		});
	}
}
