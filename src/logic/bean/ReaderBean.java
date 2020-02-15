package logic.bean;

import logic.exception.WrongSyntaxException;

/**
 * Bean per la migrazione delle info sul reader
 * fra layer di controller e quello di view.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ReaderBean extends UserBean {
	
	private String firstName;
	private String secondName;
	private boolean gender;
	
    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) throws WrongSyntaxException {
		if (firstName.length() >= 32)
			throw new WrongSyntaxException("Max length for first name: 32 chars!");
		
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) throws WrongSyntaxException {
		if (secondName.length() >= 32)
			throw new WrongSyntaxException("Max length for second name: 32 chars!");
		this.secondName = secondName;
	}

	public boolean isFemale() {
		return gender;
	}

	public void setFemale(boolean gender) {
		this.gender = gender;
	}
}
