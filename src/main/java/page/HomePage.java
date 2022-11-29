package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page{

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By linkTextAdmin= By.xpath("//a[contains(@href,'admin/login')]");
public By linkTextUser= By.xpath("//a[contains(@href,'user/login')]");
}
