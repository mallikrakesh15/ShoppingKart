package RakeshProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RakeshProject.AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents {
	
	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement selCountry;
	
	@FindBy(css=".ta-results button:nth-child(2)")
	WebElement clickCountry;
	
	@FindBy(css=".details__user a")
	WebElement placeOrder;
	
	public void selCountry(String country)
	{
		selCountry.sendKeys(country);
		clickCountry.click();	
	}
	
	public ConfirmationPage placeOrder(String country)
	{
		selCountry(country);
		placeOrder.click();	
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;

	}
	

}
