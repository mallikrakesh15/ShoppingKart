package RakeshProject.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RakeshProject.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	}
	
//	List<WebElement> cartProducts= driver.findElements(By.cssSelector(".infoWrap h3"));
	@FindBy(css=".infoWrap h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".subtotal  button")
	WebElement loadingcartProducts;
	
	@FindBy(css=".subtotal  button")
	WebElement checkOut;
	
	
	public boolean verifyProductDislay(String productName) {
//		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".infoWrap"))));
		waitForElementToVisible(loadingcartProducts);
		boolean cartMatch = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return cartMatch;
	}
	
	public CheckOutPage checkOut() {
		
		checkOut.click();
		CheckOutPage checkOutPage= new CheckOutPage(driver);
		return checkOutPage;
	}
	
}
