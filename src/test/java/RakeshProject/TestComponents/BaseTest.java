package RakeshProject.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import RakeshProject.PageObject.LandingPage;

public class BaseTest {
	
	public WebDriver driver; 
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		
		Properties prop = new Properties();
		String file = System.getProperty("user.dir")+"\\src\\main\\java\\RakeshProject\\Resources\\GlobalData.properties";
		FileInputStream fis = new FileInputStream(file);
		prop.load(fis);
		String browserName = prop.getProperty("browser");
	
		
		if (browserName.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", "E:\\TESTING\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			prefs.put("profile.password_manager_leak_detection", false);
			options.setExperimentalOption("prefs", prefs);
			
			driver = new ChromeDriver(options);
			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			// Firefox  
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			// Edge
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	
	@BeforeMethod
	public LandingPage launchApp() throws IOException
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod
	public void closeApp() throws IOException
	{
		driver.close();
	}
	
}
