package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOutPage extends Page{

	public LogOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By dropdownProfile= By.xpath("//a[contains(@href,'user-profile')]");
public By linkTextSignOut= By.xpath("//a[contains(@href,'logout')]");

}
