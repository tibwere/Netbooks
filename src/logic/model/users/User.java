package logic.model.users;

/**
 * Entita'  del dominio di interesse: Utente
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public abstract class User {
	
	protected String username;
	protected String email;
	protected double latitude;
	protected double longitude;

	protected User (String username, String email) {
		this.username = username;
		this.email = email;
	}
	
	protected User (String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}	
}
