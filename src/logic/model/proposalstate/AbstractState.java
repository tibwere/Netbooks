package logic.model.proposalstate;

import logic.exception.NoStateTransitionException;
import logic.exception.PersistencyException;
import logic.model.Book;
import logic.util.enumeration.ProposalStates;
/**
 * Classe <b>Abstract State</b> del pattern <i>State</i> dei GoF.<br>
 * Incapsula la logica del comportamento associato ad un determinato 
 * stato della {@link StateMachineImpl} 
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public abstract class AbstractState {
	protected static final String NO_ACTION_EXCEPTION = "This operation does not cause effects in this state";

	public static AbstractState getInitialState(StateMachineImpl sm, ProposalStates initialState) throws PersistencyException {
		switch (initialState) {
		case INTERMEDIATE_STATE:
			return new StateIntermediateProposal(sm, initialState);
		case CLOSED_STATE:
			return new StateClosedProposal();
		default:
			return new StateInitialProposal(sm, initialState);
		}
	}

	protected abstract void toAccept(StateMachineImpl sm) throws PersistencyException, NoStateTransitionException;

	protected abstract void toReject(StateMachineImpl sm) throws PersistencyException, NoStateTransitionException;
	
	protected abstract void acquire(StateMachineImpl sm, Book book) throws NoStateTransitionException;

	protected abstract ProposalStates getState();
}
