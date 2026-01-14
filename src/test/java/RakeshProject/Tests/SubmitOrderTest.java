package RakeshProject.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import RakeshProject.PageObject.CartPage;
import RakeshProject.PageObject.CheckOutPage;
import RakeshProject.PageObject.ConfirmationPage;
import RakeshProject.PageObject.HomePage;
import RakeshProject.PageObject.LandingPage;
import RakeshProject.PageObject.OrderPage;
import RakeshProject.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String userName= "rakesh1@gmail.com";
	String password= "Rakesh@1";
	String productName = "ADIDAS ORIGINAL";
	String country="ind";

	@Test
	public void submitOrderTest() throws InterruptedException, IOException{
		
		//LogIn
		HomePage homePage = landingPage.loginApplicatin(userName,password);
		
		// Search the product from the list of products and add the product to cart
		String actCartMsg = homePage.addProductToCart(productName);
		String expCartMsg = "Product Added To Cart";
		Assert.assertEquals(actCartMsg, expCartMsg);
		
		// Search the product in the cart page and checkout the product
		CartPage cartPage =homePage.goToCartPage();
		boolean cartMatch = cartPage.verifyProductDislay(productName);
		Assert.assertTrue(cartMatch);
		CheckOutPage checkOutPage = cartPage.checkOut();
		ConfirmationPage confirmationPage = checkOutPage.placeOrder(country);
		
		// Checkout page and Place Order
		String actOrderMsg = confirmationPage.verifyConfirmatioMeggage();
		String expOrderMsg = "THANKYOU FOR THE ORDER.";
		Assert.assertEquals(actOrderMsg, expOrderMsg);
		

	}
	
	@Test(dependsOnMethods= {"submitOrderTest"})
	public void orderHistoryTest() {
		HomePage homePage = landingPage.loginApplicatin(userName,password);
		OrderPage orderPage = homePage.goToOrderPage();
		boolean orderStatus = orderPage.verifyOrderDisplay(productName);	
		Assert.assertTrue(orderStatus);
	}

}
