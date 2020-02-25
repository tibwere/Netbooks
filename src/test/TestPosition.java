package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.junit.Test;

import logic.model.Geolocalization;
import logic.model.users.Reader;
import logic.model.users.User;
import logic.util.Parser;

public class TestPosition {
	
	@Test
	public void testInvalidCoordOnEmptyInput() throws IOException {
		
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
