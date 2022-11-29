package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserLogInPage extends Page {

	public UserLogInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
public By linkTextBackHome= By.xpath("//a[contains(@href,'index.php')]");
public By txtMobileNumber= By.name("mobno");
public By txtPassword = By.name("password");
public By btnSignIn= By.name("login");
public By linkTextResetPassword= By.xpath("//a[contains(@href,'forgot-password.php')]");
public By linkTextRegister= By.xpath("//a[contains(@href,'signup.php')]");

}
