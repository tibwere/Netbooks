package logic.model.users;

import java.util.ArrayList;
import java.util.List;

import logic.dao.ReaderDao;
import logic.exception.UserAlreadySignedException;
import logic.model.Geolocalization;
import logic.model.ProposalNotification;

/**
 * Entita' del dominio di interesse: Lettore
 * @author Simone Tiberi (M. 0252795)
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */
public class Reader extends User {
	
	/**
	 * Username dell'account di test [READER] dell'applicazione
	 */
	public static final String TESTER_USERNAME = "tester-reader";
	
	private String firstName;
	private String secondName;

	/**
	 * Mapping con la base di dati:
	 * 0 -> MALE
	 * 1 -> FEMALE
	 */
	private boolean gender; 
	
	public Reader (String username, String email, boolean gender) {
		super(username, email);
		this.gender = gender;
	}
	
	public Reader(String username) {
		super(username);
	}
 
	public List<ProposalNotification> getNotifications() {
		return new ArrayList<>();
	}

	public void addNotification(ProposalNotification notification) {
		 /* la responsabilita resta all'utente perche le notifiche non vengono inviate solo al currUser*/
	}

	public boolean isFemale() {
		return gender;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public void setFemale(boolean gender) {
		this.gender = gender;
	}

	@Override
	public void store(String password) throws UserAlreadySignedException {
		ReaderDao.saveReaderInDB(this, password, !(this.getLatitude() == Geolocalization.INVALID_VALUE || this.getLongitude() == Geolocalization.INVALID_VALUE));
	}
}

