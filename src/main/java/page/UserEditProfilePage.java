package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserEditProfilePage extends Page {

	public UserEditProfilePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public By dropdownProfile = By.xpath("//a[contains(@href,'user-profile')]");
	public By linkTextEditProfile= By.xpath("//ul//li//a");
	public By txtFname= By.name("fname");
	public By txtLname= By.name("lname");
	public By txtMobNo= By.xpath("//input[@value='1234567890']");
	public By txtAddress= By.name("add");
	public By txtRegistrationDate= By.xpath("//input[@value='2020-05-02 17:46:08']");
	public By btnUpdate= By.id("submit");
}
