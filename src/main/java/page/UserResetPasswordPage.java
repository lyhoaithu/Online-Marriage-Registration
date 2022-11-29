package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserResetPasswordPage extends Page{

	public UserResetPasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtMobileNumber= By.name("mobile");
public By txtNewPassword= By.name("newpassword");
public By txtConfirmPassword= By.name("confirmpassword");
public By btnReset= By.name("submit");
public By linkTextSignIn= By.xpath("//a[contains(@href,'login')]");
}
