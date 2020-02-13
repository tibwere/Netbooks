package logic.model.users;

import java.util.ArrayList;
import java.util.List;

import logic.model.Book;
import logic.model.ProposalNotification;

/**
 * 
 * @author Cristiano Cuffaro
 *
 */

public class Reader extends User {
	
	private String firstName;
	private String secondName;

	/**
	 * Mapping con la base di dati:
	 * 0 -> MALE
	 * 1 -> FEMALE
	 */
	private char gender; 
	private List<Book> ownedBooks;
	
	public Reader (String username, String email, char gender) {
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
		return;
	}

	public char getGender() {
		return gender;
	}
	
	public List<Book> getOwnedBooks() {
		return ownedBooks;
	}

	public void setOwnedBooks(List<Book> ownedBooks) {
		this.ownedBooks = ownedBooks;
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

	public void setGender(char gender) {
		this.gender = gender;
	}
}

