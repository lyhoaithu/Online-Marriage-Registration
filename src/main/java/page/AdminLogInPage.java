package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLogInPage extends Page{

	public AdminLogInPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By linkTextHome= By.xpath("//a[contains(@href,'index.php')]]");
public By txtUserName= By.name("username");
public By txtPassword= By.name("password");
public By chbRememberMe= By.name("remember");
public By btnSignIn= By.name("login");


}
