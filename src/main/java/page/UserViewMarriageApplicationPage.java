package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserViewMarriageApplicationPage extends Page {

	public UserViewMarriageApplicationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public By linkTextSNo = By.xpath("//td[@tabindex='0']");
	public By btnSort = By.xpath("//th[@class='wd-15p sorting_asc']");
	public By txtSearch = By.xpath("//input[@type='search']");
	public By linktextView = By.xpath("//a[contains(@href,'status-marriage-application')]");
	public By textRegNo = By.xpath("//tbody//tr[@role='row']//td[02]");
	public By rowTitle = By.xpath("//tbody//tr[@class='child']//td//li//span[@class='dtr-title']");
	public By rowData = By.xpath("//tbody//tr[@class='child']//td//li//span[@class='dtr-data']");
	public By iconViewApplication = By.xpath("//li[@data-dtr-index='5']//span[@class='dtr-data']");
	public By iconUnviewable = By.xpath("//td[@colspan='2']//i[@class='fa fa-exclamation-circle']");
	public By errorMessage = By.xpath("//td[@class='dataTables_empty']");
}
