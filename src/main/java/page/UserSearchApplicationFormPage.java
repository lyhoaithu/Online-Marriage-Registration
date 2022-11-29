package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserSearchApplicationFormPage extends Page{

	public UserSearchApplicationFormPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By txtSearch= By.name("searchdata");
public By btnSubmit= By.id("search");
public By linktextSearch= By.xpath("//a[contains(@href,'search')]");
public By messageNoResult= By.xpath("//td[@colspan='8']");
public By textDetails= By.xpath("//tr[@class='odd']//td");
}
