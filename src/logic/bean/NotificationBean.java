package logic.bean;

public class NotificationBean {
	
	private String sourceId;
	private String destBook;
	private String srcBook;
	private String proposalId;
	private String message;
	
	public NotificationBean(String source, String destBook, String message, String proposal) {
		this.sourceId = source;
		this.destBook = destBook;
		this.message = message;
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

	public String getProposalId() {
		return proposalId;
	}

	public String getMessage() {
		return message;
	}
}
