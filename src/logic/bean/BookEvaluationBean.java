package logic.bean;

import java.io.Serializable;

import logic.exception.WrongSyntaxException;

/**
 * Bean per la migrazione delle info su recensioni e valutazioni
 * fra layer di controller e quello di view.
 * 
 * La classe realizza l'interfaccia {@link Serializable} poichè
 * nella versione WEB vengono salvate sue istanze nella sessione 
 * o nella request.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class BookEvaluationBean implements Serializable {

	private static final long serialVersionUID = 6181828647524752179L;
	
	private String title;
	private String body;
	private int rate;
	
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) throws WrongSyntaxException {
		if (title.length() >= 32)
			throw new WrongSyntaxException("Max length for title of review: 32 chars!");
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
