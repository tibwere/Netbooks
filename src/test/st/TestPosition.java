package test.st;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import logic.exception.NotAccesibleConfigurationException;
import logic.model.Geolocalization;
import logic.model.Storable;
import logic.model.users.Reader;
import logic.model.users.User;
import logic.util.Parser;

/**
 * <b>JUnit</b> test utilizzato per verificare la robustezza
 * del sistema al tentativo di registrazione di un lettore che non vuole
 * inserire la propria posizione.
 * In questo caso la classe {@link Parser} che ha la responsabilità di effettuare
 * il parsing di richieste HTTP solleva un eccezione ({@link JSONException}) che viene 
 * gestita settando il valore della latitudine e della longitudine ad {@link Geolocalization#INVALID_VALUE}
 * in questo modo in fase di salvataggio nel DB (mediante l'operazione {@link Storable#store(String)}) viene memorizzato
 * il valore null.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class TestPosition {
	
	@Test
	public void testInvalidCoordOnEmptyInput() throws IOException, NotAccesibleConfigurationException {
		
		Reader reader = new Reader(User.DENIED_USERNAME);		
		
		try {
			Geolocalization position = Parser.getMapsLocation("");
			reader.setLatitude(position.getLatitude());
			reader.setLongitude(position.getLongitude());
		} catch(JSONException e) { 		
			reader.setLatitude(Geolocalization.INVALID_VALUE);
			reader.setLongitude(Geolocalization.INVALID_VALUE);
		}
		
		assertEquals(Geolocalization.INVALID_VALUE, reader.getLatitude());
	}

}
