package test.ac;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.model.users.Retailer;
import test.TestUtilities;
/**
 * test che verifica il corretto funzionamento del logout
 * @author Alessandro Calomino (M. 0258841)
 */
public class TestLogOutSelenium {

	@Test
	public void testLogOut() {

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/Netbooks/login.jsp");

		driver.findElement(By.xpath("//*[@id=\"usernameTxt\"]")).sendKeys(Retailer.TESTER_USERNAME);
		driver.findElement(By.xpath("//*[@id=\"passwordTxt\"]")).sendKeys(TestUtilities.getTesterPasswd(false));
		driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();

		
		driver.findElement(By.xpath("//*[@id=\"logOutBtn\"]")).click();
		
		assertEquals("http://localhost:8080/Netbooks/login.jsp" , driver.getCurrentUrl());


		driver.close();
	}
}
