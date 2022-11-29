package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminTakeActionPage extends Page {

	public AdminTakeActionPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	public By btnTakeAction=By.xpath("//button[@class='btn btn-primary waves-effect waves-light w-lg']");
	public By txtRemark= By.name("remark");
	public By dropdownSelect= By.name("status");
	public By selectVerified= By.xpath("//option[@value='Verified']");
	public By selectRejected= By.xpath("//option[@value='Rejected']");
	public By btnClose= By.xpath("//button[@class='btn btn-secondary']");
	public By btnSubmit= By.xpath("//button[@name='submit']");
	public By iconClose= By.xpath("//button[@class='close']");
	public By iconEye= By.xpath("//td[6]//a");
	public By popupTakeAction= By.xpath("//div[@class='modal-dialog']");
	public By textRegNumber= By.xpath("//tbody//tr//td[2]");
	public By popUp= By.xpath("//div[@class='modal fade']");
}
