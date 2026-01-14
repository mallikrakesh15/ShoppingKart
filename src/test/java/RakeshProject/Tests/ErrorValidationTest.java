package RakeshProject.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import RakeshProject.PageObject.HomePage;
import RakeshProject.TestComponents.BaseTest;

public class ErrorValidationTest extends BaseTest{

	@Test
	public void submitOrder() throws InterruptedException, IOException{
		
		String userName= "rakesh1@gmail.com";
		String password= "Rakesh1";
		
		
		//LogIn
		landingPage.loginApplicatin(userName,password);
		String actloginErrorMsg = landingPage.loginErrorMessage();
		String exploginErrorMsg = "Incorrect email or password.";
		Assert.assertEquals(actloginErrorMsg, exploginErrorMsg);
		
		



	}

}
