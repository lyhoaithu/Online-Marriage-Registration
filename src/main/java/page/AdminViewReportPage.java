package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminViewReportPage extends Page {

	public AdminViewReportPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public By txtFDate= By.id("fromdate");
	public By txtToDate= By.id("todate");
	public By btnSubmit= By.id("submit");
	public By iconReport= By.xpath("//a[contains(@href,'report')]");
	public By textNoResultMessage=By.xpath("//td[@colspan='8']");
	public By textTitle= By.xpath("//tr[@role='row']//th");
	public By textRegNo= By.xpath("//tbody//tr//td[2]");
	public By textHName= By.xpath("//tbody//tr//td[3]");
	public By textDOM= By.xpath("//tbody//tr//td[4]");
	public By textStatus= By.xpath("//tbody//tr//td[5]");
	public By txtSearch= By.xpath("//input[@type='search']");
}
