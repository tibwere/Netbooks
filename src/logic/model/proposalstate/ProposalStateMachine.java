package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.ProposalEvents;
import logic.util.enumeration.ProposalStates;
/**
 * Interfaccia che definisce gli eventi attivabili 
 * sulla <b>State Machine</b> del pattern <i>State</i> dei GoF.<br>
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public interface ProposalStateMachine {
	
	public void manageProposal(ProposalEvents e) throws PersistencyException, NoStateTransitionException;
	public void acquireBook(Book book);
	public ProposalStates getCurrentState();
}
