package logic.bean;

/**
 * Bean utilizzata per il trasferimento dei dati relativi
 * ad una istanza di "ProposalNotification"
 * @author Cristiano Cuffaro (M. 0258093)
 */

import logic.util.enumeration.NotificationTypes;

public class NotificationBean {
	
	private String sourceId;
	private String destBook;
	private String srcBook;
	private String proposalId;
	private String message;
	private NotificationTypes type;
	
	public NotificationBean(String source, String message, NotificationTypes type, String proposal) {
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

	public String getProposalId() {
		return proposalId;
	}

	public String getMessage() {
		return message;
	}

	public NotificationTypes getType() {
		return type;
	}
}
