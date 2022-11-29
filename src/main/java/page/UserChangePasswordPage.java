package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserChangePasswordPage extends Page{

	public UserChangePasswordPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public By txtCurrentPass= By.name("currentpassword");
	public By txtNewPass= By.name("newpassword");
	public By txtConfirmPass=By.name("confirmpassword");
	public By btnChange= By.id("submit");
	public By dropdownProfile = By.xpath("//a[contains(@href,'user-profile')]");
	public By linkTextSetting= By.xpath("//a[contains(@href,'change-password')]");
}
