package logic.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import logic.exception.NotAccesibleConfigurationException;

/**
 * Classe di ingegnerizzazione del sistema che permette
 * di consultare un dizionario (key=value) di proprieta'
 * specificate su un file di configurazione.
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class AppProperties {
	
	private static final String PREFIX = "netbooks.";
	private static AppProperties instance;
	private Properties prop;

	private AppProperties() throws NotAccesibleConfigurationException {
		try (InputStream input = AppProperties.class.getResourceAsStream("../netbooks.properties")) {
			prop = new Properties();
			prop.load(input);
		}
		catch(IOException e) {
			throw new NotAccesibleConfigurationException("Unable to load config file");
		}
	}
	
	public static AppProperties getInstance() throws NotAccesibleConfigurationException {
		if (instance == null)
			instance = new AppProperties();
		
		return instance;
	}
	
	public String getProperty(String key) {
		return prop.getProperty(PREFIX + key);
	}
	
}
