package TestCase;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.crm.BaseClass.BaseClass;
import com.crm.FileUtility.ReadFormPropertes;
import com.crm.POM.ProductPOM;
import com.crm.POM.classPOM;
import com.crm.POM.homePOM;

public class updateCart extends BaseClass {

	@Test
	public void updateToCart() throws IOException {
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
}
