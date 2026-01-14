package RakeshProject.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RakeshProject.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	
	@FindBy(css=".table")
	WebElement orderEle;
	
	@FindBy(xpath="//table[contains(@class,'table')]/tbody/tr")
	List<WebElement> orderList;
	
	By orderitem = By.xpath("td[2]");

	
	public boolean verifyOrderDisplay(String productName) {
		waitForElementToVisible(orderEle);
		boolean orderStatus = orderList.stream().anyMatch(orders->orders.findElement(orderitem).getText().equalsIgnoreCase(productName));
		return orderStatus; 
	}
	
}
