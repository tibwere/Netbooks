package logic.view.bpobserver.abstr;

import java.io.IOException;

/**
 * Interfaccia <b>Observer</b> del pattern <i>Observer</i> dei GoF.<br>
 * Fornisce un’interfaccia di notifica per gli oggetti a cui devono essere 
 * notificati i cambiamenti del {@link Subject}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public interface Observer {
	
	public void update() throws IOException;
}
