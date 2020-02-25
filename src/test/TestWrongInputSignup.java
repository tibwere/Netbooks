package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import logic.bean.UserBean;
import logic.exception.WrongSyntaxException;

public class TestWrongInputSignup {
	
	@Test
	public void testWrongEmail() {
		
		UserBean bean = new UserBean();
		String result = "";
		
		try {
			bean.setEmail("emaimalformata.it");
			result = "Success";
		} catch (WrongSyntaxException e) {
			result = "Fail";
		}
		
		assertEquals("Fail", result);
	}

}
