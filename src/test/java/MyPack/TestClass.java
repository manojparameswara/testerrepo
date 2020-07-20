package MyPack;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import MyPack.Login.*;

public class TestClass {
	
	WebDriver driver = null;
	Login logPage = null;
	Properties credentials = null;
	String credenPath = "C:\\Users\\Lavmanu\\eclipse-workspace\\ProjectTest\\src\\test\\java\\DataInput\\mydat.properties";
    
	@BeforeMethod()
	@Parameters("browser")
    public void initializeBrowser(String browser) throws IOException {
		
		if(browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\SWSetup\\chromedriver.exe");
			// Disable notifications 
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--disable-notifications");
			//options.setHeadless(true);
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			logPage = new Login(driver);
			credentials = PropertyFileRead.initializeProper(credenPath);
		}
    	
    }
    
    @Test(priority=1, enabled= false)
    public void LoginToSaleForceAndClickOnHome() throws InterruptedException {
    	driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com");
		Thread.sleep(4000);
		logPage.signIn(credentials.getProperty("username"), credentials.getProperty("password"), "homeclick");
    }
    
    @Test(priority=2)
    public void LoginToSaleForceAndVerifyText() throws InterruptedException {
    	driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com");
		Thread.sleep(4000);
		logPage.signIn(credentials.getProperty("username"), credentials.getProperty("password"), "verifyText");
    }
	
	
	@Test(priority=3, enabled = false)
	public void login() throws InterruptedException, IOException {
		System.out.println("m1");
		
		
		/*FileReader f = new FileReader("C:\\Users\\Lavmanu\\eclipse-workspace\\ProjectTest\\src\\test\\java\\DataInput\\mydat.properties");
		Properties p = new Properties();
		p.load(f);*/
		
		
		
		/*FileReader f1 = new FileReader("C:\\Users\\Lavmanu\\eclipse-workspace\\ProjectTest\\src\\test\\java\\DataInput\\Locators.properties");
		Properties p1 = new Properties();
		p1.load(f1);*/
		
		
		
		
		
	
		int noOfLinks = driver.findElements(By.tagName("a")).size();
		List<WebElement> eles= driver.findElements(By.tagName("a"));
		
		int count = 0;
		String s="";
		for(int i=0;i<noOfLinks;i++) {
			s = eles.get(i).getAttribute("href");
			if(s.equals("")) {
				count++;
			}
		}
		
		System.out.println("Count- Missing links"+count);
		int d =noOfLinks - count;
		System.out.println("Count- no of working links "+d);
		String s1 = "Welcome to India";
		String s2 = "Hi";
		List<String> li = new ArrayList<String>();
		
		
		li.add(s1);
		li.add(s2);
		
		System.out.println("string add "+li);
		List<Integer> li1 = new ArrayList<Integer>();
		
		li1.add(10);
		li1.add(12); 
		
		//logPage.signIn(credentials.getProperty("username"), credentials.getProperty("password"));
		/*
		THisi moved to login class
		driver.findElement(By.xpath(p1.getProperty("salesforce.username.xpath"))).sendKeys(p.getProperty("username"));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@class= 'input r4 wide mb16 mt8 password']")).sendKeys(p.getProperty("password"));
		Thread.sleep(4000);
		driver.findElement(By.xpath("//input[@class= 'button r4 wide primary']")).click();
		Thread.sleep(4000);
		
		
		
		driver.findElement(By.xpath("//input[@class='headerTrigger tooltip-trigger uiTooltip'")).click();
		driver.findElement(By.xpath("//input[@class='filter-box input']")).sendKeys("object");
		
		driver.findElement(By.linkText("Home")).click();
		*/
		//driver.quit();
				
	}
	
	@AfterMethod()
	public void quitBrowser() {
		if(driver!=null) {
			driver.quit();
		}
		
	}
	


}
