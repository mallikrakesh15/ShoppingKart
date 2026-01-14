package RakeshProject.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RakeshProject.AbstractComponents.AbstractComponents;

public class HomePage extends AbstractComponents{
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this); 
	} 

//	List<WebElement> productList = driver.findElements(By.cssSelector(".container .ng-star-inserted"));

	@FindBy(css=".mb-3")
	List<WebElement> productList;
	@FindBy(css="#toast-container [aria-label='Product Added To Cart']")
	WebElement actCartMsg;
	@FindBy(xpath="//div[@aria-label='Login Successfully']")
	WebElement loinMsg;
	@FindBy(css=".mb-3")
	WebElement pList;
	
	By search = By.cssSelector("b");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	By addCartMsg = By.cssSelector("#toast-container [aria-label='Product Added To Cart']");
	
	
	
	public List<WebElement> getProductList()
	{
		return productList;
	}

	public WebElement getProductByName(String productName)
	{
		WebElement selProduct = getProductList().stream().filter(product->
		product.findElement(search).getText().equals(productName)).findFirst().orElse(null); 
		return selProduct;
		
	}
	
	public String addProductToCart(String productName) throws InterruptedException
	{
		waitForElementToInvisible(loinMsg);
		waitForElementToVisible(pList);
		WebElement selProduct=getProductByName(productName);
		byElementToBeClickable(addToCartBy);
		selProduct.findElement(addToCartBy).click();
		String actCartMsgText = actCartMsg.getText();
		waitForByElementToDisapper(addCartMsg);
		return actCartMsgText;
	}

}
