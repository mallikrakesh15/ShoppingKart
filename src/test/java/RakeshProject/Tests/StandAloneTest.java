package RakeshProject.Tests;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import RakeshProject.PageObject.LandingPage;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "E:\\TESTING\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.password_manager_leak_detection", false);
		options.setExperimentalOption("prefs", prefs);
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		String userName= "rakesh1@gmail.com";
		String password= "Rakesh@1";
		String productName = "ADIDAS ORIGINAL";
		String country="ind";
		
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		
		//LogIn
		driver.findElement(By.id("userEmail")).sendKeys(userName);
		driver.findElement(By.id("userPassword")).sendKeys(password);
		driver.findElement(By.id("login")).click();
		
		// Search the product from the list of products and add the product to cart
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@aria-label='Login Successfully']"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		
		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));
		WebElement selProduct = productList.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null); 
		
		selProduct.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		String actCartMsg= driver.findElement(By.cssSelector("[aria-label='Product Added To Cart']")).getText();
		String expCartMsg = "Product Added To Cart";
		Assert.assertEquals(actCartMsg, expCartMsg);
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("[aria-label='Product Added To Cart']")));
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".infoWrap"))));
		List<WebElement> cartProducts= driver.findElements(By.cssSelector(".infoWrap h3"));
		boolean cartMatch = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(cartMatch);
		
		driver.findElement(By.cssSelector(".subtotal  button")).click();
		
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys(country);
		driver.findElement(By.cssSelector(".ta-results button:nth-child(2)")).click();
		driver.findElement(By.cssSelector(".details__user a")).click();
	
		String actOrderMsg =driver.findElement(By.cssSelector(".box h1")).getText();
		String expOrderMsg = "THANKYOU FOR THE ORDER.";
		Assert.assertEquals(actOrderMsg, expOrderMsg);
		
		driver.findElement(By.cssSelector(".em-spacer-1 [routerlink='/dashboard/myorders']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".table-responsive"))));
		
		List<WebElement> orderList = driver.findElements(By.xpath("//table[contains(@class,'table')]/tbody/tr"));
		boolean orderStatus = orderList.stream().anyMatch(orders->orders.findElement(By.xpath("td[2]")).getText().equalsIgnoreCase(productName));
		Assert.assertTrue(orderStatus);
		
		driver.findElement(By.xpath("//button[text()=' Sign Out ']")).click();
		String actLogOutMsg = driver.findElement(By.xpath("//div[@aria-label='Logout Successfully']")).getText();
		String expLogOutMsg = "Logout Successfully";
		Assert.assertEquals(actLogOutMsg, expLogOutMsg);
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@aria-label='Logout Successfully']"))));
		
		
		driver.close();

	}

}
