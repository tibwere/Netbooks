package test.st;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.exception.PersistencyException;
import logic.exception.UserAlreadySignedException;
import logic.model.users.Reader;
import test.TestUtilities;

/**
 * <b>JUnit</b> test che utilizza Selenium Web Driver per verificare il corretto 
 * funzionamento della navbar (deve mostrare l'username dell'utente loggato) 
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class TestNavbarSelenium {
	
	private static final String ROOT_WEB_URL = "http://localhost:8080/Netbooks/";
	
	@Test
	public void testShowProperlyUsername() throws UserAlreadySignedException, PersistencyException {
				
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		
		driver.get(ROOT_WEB_URL + "login.jsp");	
		driver.findElement(By.xpath("//*[@id=\"usernameTxt\"]")).sendKeys(Reader.TESTER_USERNAME);
		driver.findElement(By.xpath("//*[@id=\"passwordTxt\"]")).sendKeys(TestUtilities.getTesterPasswd(false));
		driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();
		String generality = driver.findElement(By.xpath("//*[@id=\"generality\"]")).getText();
		
		String [] words = generality.split(" "); 
		String usernameInBrackets = words[words.length - 1];
		String username = usernameInBrackets.substring(1, usernameInBrackets.length() - 1);
				
		assertEquals(Reader.TESTER_USERNAME, username);
		driver.close();
	}	
}
