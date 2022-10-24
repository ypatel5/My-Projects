package StepDefinitions;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class LoginSteps {

	WebDriver driver;

	@Given("^User is on Application Home Page$")
	public void user_is_on_Application_Home_Page() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://freecrm.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@When("^Application Page Title FREE CRM$")
	public void application_Page_Title_FREE_CRM() {
		String actualTitle = driver.getTitle();
		String expectedTitle = "Free CRM software for any business with sales, support and customer relationship management";
		Assert.assertEquals(expectedTitle, actualTitle);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Then("^user enters \"(.*)\" and \"(.*)\"$")
	public void user_enters_username_and_password(String uname, String pwd) {
		driver.findElement(By.xpath("//*[@id=\"navbar-collapse\"]/ul/li[1]/a")).click();
		Assert.assertEquals("Cogmento CRM", driver.getTitle());
		driver.findElement(By.name("email")).sendKeys(uname);
		driver.findElement(By.name("password")).sendKeys(pwd);
	}

	@And("^user clicks on Login Button$")
	public void user_clicks_on_Login_Butoon() {
		driver.findElement(By.xpath("//*[@id=\'ui\']/div/div/form/div/div[3]")).click();
	}

	@Then("^User navigate to user Profile page$")
	public void User_navigate_to_user_profile_page() {
		Assert.assertEquals("Cogmento CRM", driver.getTitle());
		
	}
	
	@Then("^Go to contacts$")
	public void go_to_contacts() {
		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/a"))).build().perform();
		
		//driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div[3]/a")).click();
	}
	@Then("^open contact form$")
	public void open_contact_form() {
		driver.findElement(By.xpath("//*[@id=\'main-nav\']/div[3]")).click();
	    driver.findElement(By.xpath("//*[@id=\'dashboard-toolbar\']/div[2]/div/a/button")).click();
	    try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Then("user puts details {string} and {string} and {string} and {string}")
	public void user_puts_details_and_and_and(String firstName, String lastName, String Companyname, String position) {
		driver.findElement(By.name("first_name")).sendKeys(firstName);
		driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[2]/form/div[1]/div[2]/div/div/input")).sendKeys(lastName);
		driver.findElement(By.xpath("//*[@id=\'main-content\']/div/div[2]/form/div[2]/div[2]/div/div/input")).sendKeys(Companyname);
		driver.findElement(By.xpath("//*[@id=\"main-content\"]/div/div[2]/form/div[8]/div[1]/div/div/input")).sendKeys(position);
	}
	  
	 
	
	@Then("^Save the contact$")
	public void save_the_contact() {
	    driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div/div[1]/div[2]/div/button[2]")).click();
	}
	@Then("^close the browser$")
	public void close_the_browser() {
		driver.close();
		driver.quit();
	}
}
