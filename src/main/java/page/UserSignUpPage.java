package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserSignUpPage extends Page{

	public UserSignUpPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By linkTextHome= By.xpath("//a[contains(@href,'index')]");
public By txtFirstName= By.name("fname");
public By txtLastName= By.name("lname");
public By txtMobileNumber= By.name("mobno");
public By txtAddress= By.name("address");
public By txtPassword= By.name("password");
public By btnSignUp= By.name("submit");
public By linkTextSignIn= By.xpath("//a[contains(@href,'login')]");

}
