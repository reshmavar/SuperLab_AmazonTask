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


public class RandomSerch extends BaseClass{

	@Parameters("search1")
	@Test
	public void InvalidSearch(String search) throws IOException {
		Reporter.log("Invalid Search");
		homePOM  home = new homePOM(driver);
		//home.serch(ReadFormPropertes.readproperty("InvalidProd"));
		home.serch(search);
		home.searchButton();
		WebElement serchResult = home.Invalid();
		assertTrue(serchResult.isDisplayed(),"Product is found");
		Reporter.log("No such product");
	}
}
