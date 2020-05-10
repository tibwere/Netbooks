package logic.bean;

import java.io.Serializable;

/**
 * Bean utilizzata per il trasferimento dei dati relativi
 * ad una istanza di "ProposalNotification"
 * @author Cristiano Cuffaro (M. 0258093)
 */

import logic.util.enumeration.NotificationTypes;
/**
 * Bean per la migrazione dei dati relativi alle notifiche
 * fra il layer di view ed il layer di controller.
 * 
 * La classe realizza l'interfaccia {@link Serializable} poiche'
 * nella versione WEB vengono salvate sue istanze nella sessione 
 * o nella request.
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class NotificationBean implements Serializable {
	
	private static final long serialVersionUID = 8175365724295732162L;
	
	private String sourceId;
	private String destBook = null;
	private String srcBook = null;
	private int proposalId;
	private String message;
	private NotificationTypes type;
	
	public NotificationBean(String source, NotificationTypes type, int proposal) {
		this.sourceId = source;
		this.type = type;
		this.proposalId = proposal;
	}
	
	public NotificationBean(String source, String message, NotificationTypes type, int proposal) {
		this.sourceId = source;
		this.message = message;
		this.type = type;
		this.proposalId = proposal;
	}

	public String getSourceId() {
		return sourceId;
	}

	public String getSrcBook() {
		return srcBook;
	}

	public void setSrcBook(String srcBook) {
		this.srcBook = srcBook;
	}

	public String getDestBook() {
		return destBook;
	}
	
	public void setDestBook(String destBook) {
		this.destBook = destBook;
	}

	public int getProposalId() {
		return proposalId;
	}

	public String getMessage() {
		return message;
	}

	public NotificationTypes getType() {
		return type;
	}
}
