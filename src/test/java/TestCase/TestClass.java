package TestCase;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.BaseClass.BaseClass;
import com.crm.FileUtility.ReadFormPropertes;
import com.crm.POM.ProductPOM;
import com.crm.POM.classPOM;
import com.crm.POM.homePOM;

public class TestClass extends BaseClass  {

	//@Parameters("search")
	@Test
	public void InvalidSearch() throws IOException {
		Reporter.log("Invalid Search");
		homePOM  home = new homePOM(driver);
		home.serch(ReadFormPropertes.readproperty("InvalidProd"));
		//home.serch(search);
		home.searchButton();
		WebElement serchResult = home.Invalid();
		assertTrue(serchResult.isDisplayed(),"Product is found");
		Reporter.log("No such product",true);
		driver.navigate().back();
	}
	
	//@Test
	public void ValidSearch() throws IOException {
		Reporter.log("projuct secarh",true);
		homePOM home = new homePOM(driver);
		home.serch(ReadFormPropertes.readproperty("Valid"));
      //  home.serch(search);
		home.searchButton();
	    WebElement value = home.laptopText();
	    assertTrue(value.isDisplayed(),"the product not found ");
	    Reporter.log("List of laptop",true);
		
		}
	
	//@Test
	public void addToCartStep() throws IOException {
		Reporter.log("find product and Add it to cart",true);
		homePOM add = new homePOM(driver);
		add.serch(ReadFormPropertes.readproperty("Valid"));
		add.searchButton();
		add.clickFourthProduct();
		
		 ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		 driver.switchTo().window(tabs.get(1));
		 
		 ProductPOM pro = new ProductPOM(driver);
		 pro.addClick();
		 
		 driver.get("https://www.amazon.in/gp/cart/view.html");
		 
		 classPOM crt = new classPOM(driver);
		 assertTrue(crt.isProductAdded(), "Product was not added to cart correctly");
		  Reporter.log("Product added to cart successfully", true);

		    
	}
	
	
	//@Test
	public void updateCart() throws IOException {
		Reporter.log("find product and Add it to cart",true);
		homePOM add = new homePOM(driver);
		add.serch(ReadFormPropertes.readproperty("Valid"));
		add.searchButton();
		add.clickFourthProduct();
		
		 ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		 driver.switchTo().window(tabs.get(1));
		 
		 ProductPOM pro = new ProductPOM(driver);
		 pro.addClick();
		 
		 driver.get("https://www.amazon.in/gp/cart/view.html");
		 
		 classPOM crt = new classPOM(driver);
		 assertTrue(crt.isProductAdded(), "Product was not added to cart correctly");
		  Reporter.log("Product added to cart successfully", true);
		
		  crt.increase();
		  
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.textToBePresentInElement(crt.getQuantityValueElement(), "2"));

		  assertTrue(crt.isProductAdded(), "Product not found after quantity update");
		  assertTrue(crt.isQuantityUpdatedToTwo(), "Quantity was not updated to 2");

		    
	}
	
	//@Test
	public void removeCartTest() throws IOException {
	    Reporter.log("Search and add product to cart", true);
	    homePOM home = new homePOM(driver);
	    home.serch(ReadFormPropertes.readproperty("Valid")); // e.g. "Laptop"
	    home.searchButton();
	    home.clickFourthProduct();

	    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));

	    ProductPOM productPage = new ProductPOM(driver);
	    productPage.addClick();

	    driver.get("https://www.amazon.in/gp/cart/view.html");

	    classPOM cart = new classPOM(driver);
	    assertTrue(cart.isProductAdded(), "Product was not added to cart correctly");

	    cart.deleteProductFromCart();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.or(
	        ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("div.sc-your-amazon-cart-is-empty"), "empty"),
	        ExpectedConditions.invisibilityOf(cart.getQuantityValueElement())
	    ));

	    assertTrue(cart.isCartEmpty(), "Cart is not empty after deletion");
	    Reporter.log("Product removed and cart is now empty", true);
	}

	
}
