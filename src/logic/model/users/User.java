package logic.model.users;

import logic.model.Geolocalization;
import logic.model.Storable;

/**
 * Entita'  del dominio di interesse: Utente
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public abstract class User implements Storable{
	
	protected String username;
	protected String email;
	protected Geolocalization position;

	protected User (String username, String email) {
		this.position = new Geolocalization();
		this.username = username;
		this.email = email;
	}
	
	protected User (String username) {
		this.position = new Geolocalization();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public float getLatitude() {
		return position.getLatitude();
	}

	public void setLatitude(float latitude) {
		this.position.setLatitude(latitude);
	}

	public float getLongitude() {
		return position.getLongitude();
	}

	public void setLongitude(float longitude) {
		this.position.setLongitude(longitude);
	}
	
	public void setPosition(Geolocalization g) {
		this.position = g;
	}
}
