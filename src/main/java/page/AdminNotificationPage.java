package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminNotificationPage extends Page{

	public AdminNotificationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
public By iconBell= By.xpath("//a[@class='nav-link pd-x-7 pos-relative']");
public By linktextShowApplication= By.xpath("//div[@class='media-list-footer']//a");
public By textNoOfNoti = By.xpath("//div[@class='dropdown-menu-header']//a");
public By textNotiRegNo= By.xpath("//strong[@class='tx-medium']");
public By textNotificationContent= By.xpath("//div[@class='media-list']//a");
}