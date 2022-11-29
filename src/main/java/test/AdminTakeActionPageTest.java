package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import page.AdminLogInPage;
import page.AdminTakeActionPage;

public class AdminTakeActionPageTest extends TestCase {

	@BeforeMethod(description = "Log In")
	public void logIn() {
		AdminLogInPage alp = new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(alp.txtUserName, "admin");
		sendKeys(alp.txtPassword, "Test@123");
		clickOnElement(alp.btnSignIn);
	}

	public AdminTakeActionPage atap = new AdminTakeActionPage(driver);

	@Test(description = "Take Action Successfully", groups = "main")
	public void takeActionSuccessfully() {
		driver.navigate()
				.to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/new-marriage-application.php");
		String regNumber = driver.findElement(atap.textRegNumber).getText();
		clickOnElement(atap.iconEye);
		clickOnElement(atap.btnTakeAction);
		waitForElementPresence(atap.popupTakeAction);
		sendKeys(atap.txtRemark,
				"qwertyuio  p{}\\asdfghjkl;zxcvbn234567890qwertyuiop{}\\asdfghjkl;zxcvbnm, .1234567890qwertyuiop{}\\asdfghjkl;zxcvbnm,.12345");
		clickOnElement(atap.dropdownSelect);
		clickOnElement(atap.selectVerified);
		clickOnElement(atap.btnSubmit);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, "Remark has been updated");
		acceptAlertMessage();

//Check bên mục Verified xem có hiển thị application vừa đổi ko
		driver.navigate()
				.to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/verified-marriage-application.php");
		List<WebElement> regNos = driver.findElements(atap.textRegNumber);

//Lấy số regno của application trước khi được đổi trjang thái
		String regNumberBefore = driver.findElement(atap.textRegNumber).getText();
		String[] regNosArr = new String[regNos.size()];

//Lấy hết giá trị Regno ở cột regno bên verified
		for (int i = 0; i < regNos.size(); i++) {
			regNosArr[i] = regNos.get(i).getText();
		}
		String regNosStr = Arrays.deepToString(regNosArr);
		assertTrue(regNosStr.contains(regNumberBefore));
	}

	@Test(description = "Take Action Fail When Clicking On The Close Button", groups = "validation")
	public void takeActionFailWhenClickOnTheCloseButton() {
		driver.navigate()
				.to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/new-marriage-application.php");
		clickOnElement(atap.iconEye);
		clickOnElement(atap.btnTakeAction);
		waitForElementPresence(atap.popupTakeAction);
		sendKeys(atap.txtRemark,
				"qwertyuio  p{}\\asdfghjkl;zxcvbn234567890qwertyuiop{}\\asdfghjkl;zxcvbnm, .1234567890qwertyuiop{}\\asdfghjkl;zxcvbnm,.12345");
		clickOnElement(atap.dropdownSelect);
		clickOnElement(atap.selectVerified);
		clickOnElement(atap.btnClose);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.attributeContains(atap.popUp, "style", "display: none"));
		String actualResult = driver.findElement(atap.popUp).getAttribute("style");
		assertEquals(actualResult, "display: none;");

	}

	@Test(description = "Take Action Fail When Clicking On The Close Icon", groups = "validation")
	public void takeActionFailWhenClickOnTheCloseIcon() {
		driver.navigate()
				.to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/new-marriage-application.php");
		clickOnElement(atap.iconEye);
		clickOnElement(atap.btnTakeAction);
		waitForElementPresence(atap.popupTakeAction);
		sendKeys(atap.txtRemark,
				"qwertyuio  p{}\\asdfghjkl;zxcvbn234567890qwertyuiop{}\\asdfghjkl;zxcvbnm, .1234567890qwertyuiop{}\\asdfghjkl;zxcvbnm,.12345");
		clickOnElement(atap.dropdownSelect);
		clickOnElement(atap.selectVerified);
		clickOnElement(atap.iconClose);
		waitForElementPresence(atap.popupTakeAction);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.attributeContains(atap.popUp, "style", "display: none"));
		String actualResult = driver.findElement(atap.popUp).getAttribute("style");
		assertEquals(actualResult, "display: none;");
	}

	@Test(description = "Take Action Fail When Enter Too Long Remark", groups = "validation")
	public void takeActionFailWhenEnterTooLongRemark() {
		driver.navigate()
				.to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/new-marriage-application.php");
		clickOnElement(atap.iconEye);
		clickOnElement(atap.btnTakeAction);
		waitForElementPresence(atap.popupTakeAction);
		sendKeys(atap.txtRemark,
				"qwertyuio  p{}\\asdfghjkl;zxcvbn234567890qwertyuiop{}\\asdfghjkl;zxcvbnm, .12134567890qwertyuiop{}\\asdfghjkl;zxcvbnm,.123455");
		clickOnElement(atap.dropdownSelect);
		clickOnElement(atap.selectVerified);
		clickOnElement(atap.btnSubmit);
		String actualResult = getLocalMessage();
		assertEquals(actualResult, "Remark is too long");
	}

	@Test(description = "Take Action Fail When The Status Is Verified", groups = "validation")
	public void takeActionFailWhenStatusIsVerified() {
		driver.navigate()
				.to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/verified-marriage-application.php");
		clickOnElement(atap.iconEye);
		List<WebElement> elements = driver.findElements(atap.btnTakeAction);
		assertEquals(elements.size(), 0);
	}

	@Test(description = "Take Action Fail When The Status Is Rejected", groups = "validation")
	public void takeActionFailWhenStatusIsRejected() {
		driver.navigate()
				.to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/rejected-marriage-application.php");
		clickOnElement(atap.iconEye);
		List<WebElement> elements = driver.findElements(atap.btnTakeAction);
		assertEquals(elements.size(), 0);
	}
}
