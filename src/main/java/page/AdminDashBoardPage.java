package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminDashBoardPage extends Page {

	public AdminDashBoardPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public By iconSeeMoreNew = By.xpath("//div[@class='d-flex justify-content-between']//a[1]");
	public By iconSeeMoreVerified = By.xpath(
			"//div[@class='d-flex justify-content-between']//a[contains(@href,'verified-marriage-application')]");
	public By iconSeeMoreRejected = By.xpath(
			"//div[@class='d-flex justify-content-between']//a[contains(@href,'rejected-marriage-application')]");
	public By iconSeeMoreAll = By
			.xpath("//div[@class='d-flex justify-content-between']//a[contains(@href,'all-marriage-application')]");
	public By textNumbers = By.xpath("//h2[@class='mg-b-5 tx-inverse tx-lato']");
	public By iconDashboard = By.xpath("//a[contains(@href,'dashboard')]");
}
