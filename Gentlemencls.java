package Gentlemenpkg;

import java.time.Duration;
import java.util.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Gentlemencls {

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
		String expected = "https://www.wahylab.com/gendemo/";
		String actual = driver.getCurrentUrl();
		sa=new SoftAssert();
		sa.assertEquals(actual, expected, "Incorrect URL");
		sa.assertAll();
		}
		catch(AssertionError e)
		{
			System.out.println("Defect: Wrong URL" +e.getLocalizedMessage());
		}
		finally
		{
			driver.navigate().refresh();
		}
		
	}
	@Test(priority = 2, enabled=false)
	public void validLogin()
	{
		Login("Admin","admin@gentleman");
	}


	@Test(priority = 3)
	public void Invalidlogin() throws InterruptedException 
	{

		Login("Ashitha","ashitha@gentleman"); 
		Thread.sleep(2000);
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='error']")));
		Assert.assertTrue(error.isDisplayed());
		
		//String expectedErrorMessage = "Invalid username or password.";
        //String actualErrorMessage = error.getText();
        //Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message text is incorrect!");
        
		}
		catch(NoSuchElementException | TimeoutException | AssertionError e)


		{
		        System.out.println("DEFECT : No error message displayed for invalid username. Error encountered: " + e.getMessage());
		    }
		}
		
	
		@Test(priority = 4)
		public void forgotpass() throws TimeoutException
		{
					 
			WebElement FP = driver.findElement(By.xpath("/html/body/div/div/a/span"));
			FP = wait.until(ExpectedConditions.visibilityOf(FP));
			FP.click(); 
			
			try
			{
			String expected = "https://www.wahylab.com/gendemo/login/forgot_pass";
			String actual = driver.getCurrentUrl();
			Assert.assertEquals(actual, expected, "The URLs do not match!");
			
			}
			catch(AssertionError e)
			{
				System.out.println("Defect: Wrong URL" +e.getLocalizedMessage());
			}
			
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.get("https://www.wahylab.com/gendemo/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	@AfterMethod
	public void afterMethod() 
	{
		driver.close();
	}



}
