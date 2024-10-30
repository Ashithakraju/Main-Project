package Gentlemenpkg;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ForgotPassPage 
{
	ChromeDriver driver;
	WebDriverWait wait;
	SoftAssert sa;
	
	private void forgotpasswordlogin(String email)
	{

		WebElement user = driver.findElement(By.name("email"));
		user = wait.until(ExpectedConditions.visibilityOf(user));
		user.sendKeys(email);

		
		WebElement Submit = driver.findElement(By.xpath("/html/body/div/div[1]/form/center/input"));
		Submit = wait.until(ExpectedConditions.visibilityOf(Submit));
		Submit.click();

	}
	@Test(priority = 1)
	public void verifyurl() 
	{
		try
		{
		String expected = "https://www.wahylab.com/gendemo/login/forgot_pass";
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
  @Test(priority = 2)
  public void validlogin() 
  {
	  forgotpasswordlogin("Gentleman@gmail.com");  
  }
  @Test(priority = 3)
  public void invalidlogin() throws InterruptedException 
  {
	  forgotpasswordlogin("Ashitha@gmail.com");
	  Thread.sleep(2000);
		try
		{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/div[3]/h2")));
		Assert.assertTrue(error.isDisplayed());
		
		String expectedErrorMessage = "Invalid Email ID !";
		String actualErrorMessage = error.getText();
      	Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message text is incorrect!");
      
		}
		catch(NoSuchElementException | TimeoutException | AssertionError e)


		{
		        System.out.println("DEFECT : No error message displayed for invalid Email. Error encountered: " + e.getMessage());
		    }
		}
		
  
  @BeforeMethod
  public void beforeMethod() 
  {
	  driver = new ChromeDriver();
	  driver.get("https://www.wahylab.com/gendemo/login/forgot_pass");
	  driver.manage().window().maximize();
	  wait = new WebDriverWait(driver, Duration.ofSeconds(10));

  }

  @AfterMethod
  public void afterMethod() 
  {
	  driver.close();
  }

  @BeforeClass
  public void beforeClass() 
  {
  }

  @AfterClass
  public void afterClass() 
  {
  }

}
