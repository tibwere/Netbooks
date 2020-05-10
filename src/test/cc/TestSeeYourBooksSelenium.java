package test.cc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import logic.dao.ReaderDao;
import logic.exception.AlreadyOwnedBookException;
import logic.exception.BookNotOwnedException;
import logic.exception.PersistencyException;
import logic.model.users.Reader;
import test.TestUtilities;

public class TestSeeYourBooksSelenium {
	
	/* il seguente isbn corrisponde al libro "Il grande Gatsby" presente nel db */
	private static final String TEST_ISBN = "8807900238";
	
	private static final String NETBOOKS_URL = "http://localhost:8080/Netbooks/";
	
	@Test
	public void testShowOwnedBook() throws PersistencyException, AlreadyOwnedBookException, BookNotOwnedException {
		Reader testerReader = ReaderDao.getEmailAndGenre(Reader.TESTER_USERNAME);
		ReaderDao.insertNewBookInOwnedList(TEST_ISBN, testerReader.getUsername());
		
		// System.setProperty("webdriver.gecko.driver","drivers/./geckodriver");
		// WebDriver driver = new FirefoxDriver();

		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get(NETBOOKS_URL + "login.jsp");
		driver.findElement(By.xpath("//*[@id=\"usernameTxt\"]")).sendKeys(Reader.TESTER_USERNAME);
		driver.findElement(By.xpath("//*[@id=\"passwordTxt\"]")).sendKeys(TestUtilities.getTesterPasswd(false));
		driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();
		/* Click on "Exchange Books" */
		driver.findElement(By.xpath("/html/body/nav/div/div[1]/a[3]")).click();
		/* Click on "See Your Books" */
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[2]/div/div/form[2]/button")).click();
		/* Click on book title for expand collapse */
		driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/a")).click();
		/* Acquire the ISBN of book */
		String output = driver.findElement(By.xpath("/html/body/div/div/div/div/div[1]/div/div/div/table/tbody/tr[1]/td")).getText();
		
		assertEquals(TEST_ISBN, output);

		driver.close();
		
		ReaderDao.removeBookFromOwnedList(TEST_ISBN, testerReader.getUsername());
	}
}
