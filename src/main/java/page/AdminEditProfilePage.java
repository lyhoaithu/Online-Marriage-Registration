package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminEditProfilePage extends Page {

	public AdminEditProfilePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public By dropdownAdmin = By.xpath("//div[@class='dropdown dropdown-profile']//a");
	public By iconEditProfile = By.xpath("//a[contains(@href,'admin-profile')]");
	public By txtName = By.name("adminname");
	public By txtUserName = By.name("username");
	public By phoneNo = By.name("mobilenumber");
	public By email = By.name("email");
	public By txtRegistionDate = By.xpath("//div[5]//input");
	public By btnSubmit = By.id("submit");
}
