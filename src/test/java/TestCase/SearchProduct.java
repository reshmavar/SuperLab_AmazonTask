package TestCase;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.crm.BaseClass.BaseClass;
import com.crm.FileUtility.ReadFormPropertes;
import com.crm.POM.homePOM;

@Listeners(com.crm.Listeners.WtihExtentReport.class)
public class SearchProduct extends BaseClass {


	@Parameters("search2")
	@Test
	public void ValidSearch(String search) throws IOException {
		Reporter.log("projuct secarh",true);
		homePOM home = new homePOM(driver);
		//home.serch(ReadFormPropertes.readproperty("Valid"));
        home.serch(search);
		home.searchButton();
	    WebElement value = home.laptopText();
	    assertTrue(value.isDisplayed(),"the product not found ");
	    Reporter.log("List of laptop",true);
		
		}

}
