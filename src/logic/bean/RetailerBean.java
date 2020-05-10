package logic.bean;

import logic.exception.NotAccesibleConfigurationException;
import logic.exception.WrongSyntaxException;

/**
 * Bean per la migrazione delle info sul retailer
 * fra layer di controller e quello di view.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class RetailerBean extends UserBean {
	
	private String companyName;
	private String vat;
	
	public RetailerBean() {
		super();
	}
	
	public RetailerBean(String username) throws NotAccesibleConfigurationException {
		try {
			this.setUsername(username);
		} catch (WrongSyntaxException e) {
			throw new IllegalStateException("Already logged user must have correct username");
		}
	}	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) throws WrongSyntaxException {
		if (companyName.length() >= 32)
			throw new WrongSyntaxException("Max length for company: 32 chars!");
		this.companyName = companyName;
	}
	public String getVat() {
		return vat;
	}
	public void setVat(String vat) throws WrongSyntaxException {
		if (vat.length() != 11)
			throw new WrongSyntaxException("Invalid VAT inserted!");
		this.vat = vat;
	}
}
