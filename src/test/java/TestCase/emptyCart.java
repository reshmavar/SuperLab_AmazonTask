package TestCase;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.BaseClass.BaseClass;
import com.crm.FileUtility.ReadFormPropertes;
import com.crm.POM.ProductPOM;
import com.crm.POM.classPOM;
import com.crm.POM.homePOM;

public class emptyCart extends BaseClass {

	@Test
	public void removeCartTest() throws IOException {
	    Reporter.log("Search and add product to cart", true);
	    homePOM home = new homePOM(driver);
	    home.serch(ReadFormPropertes.readproperty("Valid")); 
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
