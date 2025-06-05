package BaseClass;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Reporter;


import fileUtility.ReadFormPropertes;

public class BaseClass {

	public static WebDriver driver;
	
	 
		public static void preCondition() throws IOException {
			String browser = ReadFormPropertes.readproperty("browser");
			String url = ReadFormPropertes.readproperty("url");
			
			
			if(browser.equalsIgnoreCase("chrome")) {
				driver = new ChromeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox")) {
				driver = new FirefoxDriver();
			}
			else if(browser.equalsIgnoreCase("Safari")) {
				driver = new SafariDriver();
			}
			else if(browser.equalsIgnoreCase("Edge")) {
				driver = new EdgeDriver();
			}
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			driver.get(url);
			Reporter.log("Enter into the Amazon ",true);
    }
	 
	 
	 public static void postCondition() {
		 driver.close();
	 }

}
