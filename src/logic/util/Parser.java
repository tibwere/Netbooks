package logic.util;

import java.io.IOException;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import logic.model.Geolocalization;

/**
 * Classe la cui responsabilità è quella di effettuare il parsing dell'HTML
 * al fine di restituire dati di interesse per l'applicazione
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Parser {
	
	private static final String RATING_GOOGLE_PREFIX = "https://www.google.com/search?q=";
	private static final String MAPS_API_PREFIX = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
	private static final String MAPS_KEY_PREFIX = "&key=";
	private static final String RATING_SELECTOR = "srBp4 Vrkhme";
	private static final int RATING_PERC_INDEX = 9;
		
	private Parser() {}
	
	public static int getAVGEvaluationFromGoogle(String title) throws IOException {
		
		String titleConverted = title.replace(" ", "%20");
	
		Document doc = Jsoup.connect(RATING_GOOGLE_PREFIX + titleConverted).get();
		Element elem = doc.getElementsByClass(RATING_SELECTOR).first();
		
		String percentageStr = elem.text().split(" ")[RATING_PERC_INDEX];
		return Integer.valueOf(percentageStr.substring(0, 2));
	}
	
	public static Geolocalization getMapsLocation(String address) throws IOException {
		
		String addressConverted = address.replace(" ", "%20");
		
		String json = Jsoup.connect(MAPS_API_PREFIX + addressConverted + 
				MAPS_KEY_PREFIX + AppProperties.getInstance().getProperty("mapskey")).ignoreContentType(true).execute().body();
		
		JSONObject document = new JSONObject(json);
		JSONObject results = document.getJSONArray("results").getJSONObject(0);
		Geolocalization position = new Geolocalization();
		position.setLatitude((float) results.getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
		position.setLongitude((float) results.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));

		return position;
	}
}
 