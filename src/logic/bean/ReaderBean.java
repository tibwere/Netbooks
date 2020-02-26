package logic.bean;

import java.io.Serializable;

import logic.exception.WrongSyntaxException;

/**
 * Bean per la migrazione delle info sul reader
 * fra layer di controller e quello di view.
 * 
 * La classe realizza l'interfaccia {@link Serializable} poiche'
 * nella versione WEB vengono salvate sue istanze nella sessione 
 * o nella request.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class ReaderBean extends UserBean implements Serializable {
	
	private static final long serialVersionUID = 7904187792406751070L;
	
	private String firstName;
	private String secondName;
	private boolean gender;
	
	public ReaderBean() {
		super();
	}
	
	public ReaderBean(String username) {
		try {
			this.setUsername(username);
		} catch (WrongSyntaxException e) {
			throw new IllegalStateException("Already logged user must have correct username");
		}
	}
	
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
