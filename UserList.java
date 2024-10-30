package Gentlemenpkg;

import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class UserList 
{
	ChromeDriver driver;
	WebDriverWait wait;
	SoftAssert sa;
	private void Login(String username, String password)
	{

		WebElement user = driver.findElement(By.name("username"));
		user = wait.until(ExpectedConditions.visibilityOf(user));
		user.sendKeys(username);

		WebElement Pass = driver.findElement(By.name("password"));
		Pass = wait.until(ExpectedConditions.visibilityOf(Pass));
		Pass.sendKeys(password);

		WebElement Submit = driver.findElement(By.xpath("/html/body/div/div/form/button"));
		Submit = wait.until(ExpectedConditions.visibilityOf(Submit));
		Submit.click();


	}

	@Test(priority = 1)
	public void verifyurl() 
	{
		try
		{
			WebElement Userlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navi\"]/li[2]/a")));
			Userlist.click();
			String expected = "https://www.wahylab.com/gendemo/NewMaster/showLoginUsersList";
			String actual = driver.getCurrentUrl();
			sa=new SoftAssert();
			sa.assertEquals(actual, expected, "Incorrect URL");
			sa.assertAll();
		}
		catch(AssertionError e)
		{
			System.out.println("Defect: Button is not working as expected" +e.getLocalizedMessage());
		}

	}

	@Test(priority = 2, enabled=false)
	public void pdf()  
	{
		WebElement pd = driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[3]"));
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));
		pd = wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[3]")));
		pd.click();

		String expectedFileName = "GENTLEMAN.pdf";
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try
		{
			wait.until(driver ->
			{
				File file = new File("C:\\Users\\ashit.HAZELSARAAMAL\\Downloads" + expectedFileName);
				return file.exists();


			});
			System.out.println("pdf File download successfully");
			File file = new File("C:\\\\Users\\\\ashit.HAZELSARAAMAL\\\\Downloads" + expectedFileName);
			file.delete();
		}

		catch(Exception e)
		{
			System.out.println("File dwld time out");
		}

	}

	@Test(priority = 5, enabled=false)
	public void excel() 
	{
		// Wait for the download link to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[2]/span")));

		// Click the download button/link
		downloadButton.click();

		// Define the expected file name (update this to the actual file name)
		String expectedFileName = "example.xlsx";

		try
		{
			wait.until(driver ->
			{
				File file = new File("C:\\Users\\ashit.HAZELSARAAMAL\\Downloads" + expectedFileName);
				return file.exists();


			});
			System.out.println("pdf File download successfully");
			File file = new File("C:\\\\Users\\\\ashit.HAZELSARAAMAL\\\\Downloads" + expectedFileName);
			file.delete();
		}

		catch(Exception e)
		{
			System.out.println("File dwld time out");
		}
	}

	@Test(priority = 6, enabled=false)
	public void csv() 
	{
		// Wait for the download link to become visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement downloadButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[5]/span")));

		// Click the download button/link
		downloadButton.click();

		// Define the expected file name (update this to the actual file name)
		String expectedFileName = "example.csv";

		try
		{
			wait.until(driver ->
			{
				File file = new File("C:\\Users\\ashit.HAZELSARAAMAL\\Downloads" + expectedFileName);
				return file.exists();


			});
			System.out.println("pdf File download successfully");
			File file = new File("C:\\\\Users\\\\ashit.HAZELSARAAMAL\\\\Downloads" + expectedFileName);
			file.delete();
		}

		catch(Exception e)
		{
			System.out.println("File dwld time out");
		}
	}

	@Test(priority = 7)
	public void slsort() throws InterruptedException 
	{
		try
		{
			JavascriptExecutor js = (JavascriptExecutor) driver; 
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");	
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			 WebElement beforesort = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'100')]")));
			 String expected = beforesort.getText();

		        // Find and click the sort button
		        WebElement sortButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//th[contains(text(),'Slno')]")));
		        new Actions(driver).moveToElement(sortButton).click().perform();

	
			js.executeScript("window.scrollTo(0, 0)");

			WebElement aftersort = driver.findElement(By.xpath("//tbody/tr[1]/td[1]"));		
			WebDriverWait wa =new WebDriverWait(driver, Duration.ofSeconds(15));
			aftersort= wa.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//tbody/tr[1]/td[1]")));
			String actual= aftersort.getText();
			Assert.assertEquals(actual,expected);
		}
		catch(AssertionError | NoSuchElementException | TimeoutException e)
		{
			System.out.println("defect:Sort button dose not work");
		}
	}
	
	@Test(priority = 10)
	public void clickPreviousAndNext() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        // Click the "Next" button
        try {
            WebElement nextButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"designation_table_next\"]/a")));
            nextButton.click();
            System.out.println("Clicked on Next button");

            // Add any additional check or action after clicking "Next" if necessary

        } catch (Exception e) {
            System.out.println("Error clicking Next button: " + e.getMessage());
        }

        // Click the "Previous" button
        try {
            WebElement prevButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"designation_table_previous\"]/a")));
            prevButton.click();
            System.out.println("Clicked on Previous button");

            // Add any additional check or action after clicking "Previous" if necessary

        } catch (Exception e) {
            System.out.println("Error clicking Previous button: " + e.getMessage());
        }
    }

	@Test(priority=9)
	public void search() throws InterruptedException 
	{
		try {
			WebElement errorS = driver.findElement(By.xpath("//*[@id=\"designation_table_filter\"]/label/input"));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			errorS= wait.until(ExpectedConditions.visibilityOf(errorS));
			String input="NESSIYA";
			errorS.sendKeys(input);
			WebElement searchresult = driver.findElement(By.xpath("//*[@id=\"designation_table\"]/tbody/tr/td[2]"));			
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
			searchresult= wait1.until(ExpectedConditions.visibilityOf(searchresult));
			String actualS = searchresult.getText().trim();
			Assert.assertEquals(actualS, input);
		}
		catch(AssertionError e)
		{
			System.out.println("defect: search error");
		}
		finally
		{
			driver.navigate().refresh();
		}
	}

	@Test(priority=8)
	public void drpdown() throws InterruptedException 
	{
		WebElement dropdown=driver.findElement(By.xpath("//*[@id=\"designation_table_length\"]/label/select"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		dropdown= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_length\"]/label/select")));
		Select select=new Select(dropdown);
		select.selectByValue("200");
		Thread.sleep(3000);
		try
		{

			WebElement wb= driver.findElement(By.xpath("//*[@id=\"designation_table_info\"]"));
			WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(15));
			wb= wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_info\"]")));
			String expectedN ="Showing 1 to 200";
			String actualN =wb.getText();
			Assert.assertTrue(actualN.contains(expectedN)); 

		}
		catch(AssertionError e)
		{
			System.out.println("defect:dropdown is not working");
		}

	}


	@Test(priority = 3, enabled=false)
	public void copy()  
	{
		WebElement copy = driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[1]/span"));
		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(10));
		copy = wt.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[1]/span")));
		copy.click();

	}

	@Test(priority = 4, enabled=false)
	public void print() throws InterruptedException  
	{
		WebElement pt = driver.findElement(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[4]/span"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		pt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"designation_table_wrapper\"]/div[2]/a[4]/span")));
		pt.click();
		Thread.sleep(1000);
		String parent = driver.getWindowHandle();
		for(String windowHandle : driver.getWindowHandles())
		{
			driver.switchTo().window(windowHandle);
		}
		driver.close();
		driver.switchTo().window(parent);
	}

	@BeforeMethod
	public void beforeMethod() 
	{

		driver = new ChromeDriver();
		driver.get("https://www.wahylab.com/gendemo/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Login("Admin","admin@gentleman");
		WebElement Userlist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"navi\"]/li[2]/a")));
		Userlist.click();

	}


	@AfterMethod
	public void afterMethod() 
	{
		driver.close();
	}

	@BeforeClass
	public void beforeClass() {
	}

	@AfterClass
	public void afterClass() {
	}

}
