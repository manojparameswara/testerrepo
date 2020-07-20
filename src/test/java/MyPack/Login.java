package MyPack;


import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class Login {
	
	WebDriver driver;
	static String locFilePath = "C:\\Users\\Lavmanu\\eclipse-workspace\\ProjectTest\\src\\test\\java\\DataInput\\Locators.properties";
	static Properties loc;
	static {
		try {
			loc  = PropertyFileRead.initializeProper(locFilePath) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Login(WebDriver driver) {
		this.driver = driver;
	}
	
	By username_Xpath = By.xpath(loc.getProperty("salesforce.username.xpath"));
	By password_Xpath = By.xpath(loc.getProperty("salesforce.password.xpath"));
	By login_Btn_Xpath = By.xpath(loc.getProperty("salesforce.loginBtn.xpath"));
	By Link_Text = By.linkText(loc.getProperty("salesforce.homepage.linktext"));
	By Header_Text = By.xpath(loc.getProperty("salesforce.homepage.headerText"));
	
	public void signIn(String username, String password, String action) throws InterruptedException {
		driver.findElement(username_Xpath).sendKeys(username);	
		driver.findElement(password_Xpath).sendKeys(password);		
		driver.findElement(login_Btn_Xpath).click();
		Thread.sleep(10000);
		if(action.equals("homeclick")) {
			driver.findElement(Link_Text).click();
		}
		
		if(action.equals("verifyText")) {
			String expectedText = "Real-time Collaborative Docs";
			String actualText = driver.findElement(Header_Text).getText();
			//Assert.assertEquals(actualText, expectedText);
			SoftAssert sa = new SoftAssert();
			sa.assertEquals(actualText, expectedText);
			System.out.println("Text is = " + actualText);
			highLightText(Header_Text);
			Thread.sleep(5000);
			sa.assertAll();
		}
	}
	
	public void highLightText(By locator) {
		WebElement element = driver.findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;		
		js. executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

}
