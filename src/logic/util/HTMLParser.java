package logic.util;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Classe la cui responsabilità è quella di effettuare il parsing dell'HTML
 * al fine di restituire dati di interesse per l'applicazione
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class HTMLParser {
	
	private static final String GOOGLE_PREFIX = "https://www.google.com/search?q=";
	private static final String SELECTOR = "srBp4 Vrkhme";
	private static final int PERC_INDEX = 9;
	
	private HTMLParser() {/* nothing to do here */}
	
	public static int getAVGEvaluationFromGoogle(String title) throws IOException {
		
		String titleConverted = title.replace(' ', '+');
	
		Document doc = Jsoup.connect(GOOGLE_PREFIX + titleConverted).get();
		Element elem = doc.getElementsByClass(SELECTOR).first();
		
		String percentageStr = elem.text().split(" ")[PERC_INDEX];
		return Integer.valueOf(percentageStr.substring(0, 2));
	}

}
 