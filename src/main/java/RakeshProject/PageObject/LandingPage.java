package RakeshProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RakeshProject.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
//	WebElement userName = driver.findElement(By.id("userEmail"));

	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(xpath="//button[text()=' Sign Out ']")
	WebElement signOut;
	
	@FindBy(xpath="//div[@aria-label='Logout Successfully']")  		
	WebElement signOutMsg;
	
	@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")  		
	WebElement loginErrorMsg;
	
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/#/auth/login"); 
	}

	public HomePage loginApplicatin(String uname, String pwd)
	{
		userName.sendKeys(uname);
		password.sendKeys(pwd);
		login.click();
		HomePage homePage = new HomePage(driver);
		return homePage;
	}

	public String sighOut()
	{
		
		String actloginErrorMsg = signOutMsg.getText();
		return actloginErrorMsg;	

	}
	
	public String loginErrorMessage()
	{
		waitForElementToVisible(loginErrorMsg);
		String actLogOutMsg = loginErrorMsg.getText();
		return actLogOutMsg;	

	}
	
}
