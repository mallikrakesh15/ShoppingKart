package RakeshProject.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import RakeshProject.PageObject.CartPage;
import RakeshProject.PageObject.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponents(WebDriver driver)
	{
		this.driver = driver;
		this.wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		PageFactory.initElements(driver,this );
	} 
	
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement clickOnCart;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement clickOnOrder;
	
	
	public void waitForByElementToApper(By findBy)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForByElementToDisapper(By findBy)
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void byElementToBeClickable(By findBy)
	{
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}
	
	public void waitForElementToVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitForElementToInvisible(WebElement element)
	{
		wait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	
	public CartPage goToCartPage()
	{
		clickOnCart.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrderPage()
	{
		clickOnOrder.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
	

}
