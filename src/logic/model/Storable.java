package logic.model;

import logic.exception.UserAlreadySignedException;

/**
 * Interfaccia che definisce una operazione il cui compito
 * è salvare in persistenza l'entità che la realizza specificandone la password
 * con cui essa deve essere memorizzata.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public interface Storable {
	
	public void store(String password) throws UserAlreadySignedException;

}
