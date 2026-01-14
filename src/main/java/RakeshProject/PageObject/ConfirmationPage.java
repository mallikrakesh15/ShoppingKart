package RakeshProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RakeshProject.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{
	WebDriver driver;
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(css=".box h1")
	WebElement orderMsg;
	
	@FindBy(css=".box h1")
	WebElement orderHistory;
	
	public String verifyConfirmatioMeggage(){
		String actOrderMsg =orderMsg.getText();
		return actOrderMsg;

	}
	
	public OrderPage orderHistory(){
		orderHistory.click();
		OrderPage orderPage =goToOrderPage();
		return orderPage;

	}
	
	
}
