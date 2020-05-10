package logic.model;

/**
 * Classe di ingegnerizzazione del sistema che sintetizza
 * ed astrae il concetto di geolocalizzazione, in questo modo è
 * possibile manipolare questi dettagli (lat. e long.) in fase di registrazione
 * in maniera atomica.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Geolocalization {
	
	/**
	 * Valore che non appartiene al range corretto ne' dei valori di latitudine [-90 to 90]
	 * ne' dei valori di logitudine [-180 to 80]
	 */
	public static final float INVALID_VALUE = 1000;
	
	private float latitude;
	private float longitude;
	
	public Geolocalization(float latitude, float longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Geolocalization() {}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
}
