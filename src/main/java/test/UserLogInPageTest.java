package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.UserLogInPage;

public class UserLogInPageTest extends TestCase {
	@DataProvider(name = "LogIn Successfully Data")
	public String[][] logInSuccessfullyData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(
				"D:\\AutomationTest\\02Projects\\OnlineMarriageRegistration\\TestData\\OMR_TestData.xlsx",
				"UserLogInSuccessfully");
		return data;
	}

	@Test(description = "Validate User LogIn Successfully When Provide Correct Data", groups = "main", dataProvider = "LogIn Successfully Data")
	public void logInSuccessfully(String mobileNumber, String password) {
		UserLogInPage ulgp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulgp.txtMobileNumber, mobileNumber);
		sendKeys(ulgp.txtPassword, password);
		clickOnElement(ulgp.btnSignIn);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("omrs/user/dashboard"));
	}

	@DataProvider(name = "LogIn Fail Invalid Data", indices = { 0, 1 })
	public String[][] logInUsingInvalidData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(
				"D:\\AutomationTest\\02Projects\\OnlineMarriageRegistration\\TestData\\OMR_TestData.xlsx",
				"UserLogInFail");
		return data;
	}

	@Test(description = "Validate User LogIn Fail When Provide Invalid Data", dataProvider = "LogIn Fail Invalid Data", groups = "validation")
	public void logInFailUsingInvalidData(String mobileNumber, String password, String expectedMessage) {
		UserLogInPage ulgp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulgp.txtMobileNumber, mobileNumber);
		sendKeys(ulgp.txtPassword, password);
		clickOnElement(ulgp.btnSignIn);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider(name = "LogIn Fail Blank Field Data", indices = { 3, 4 })
	public String[][] logInMissingSomeData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(
				"D:\\AutomationTest\\02Projects\\OnlineMarriageRegistration\\TestData\\OMR_TestData.xlsx",
				"UserLogInFail");
		return data;
	}

	@Test(description = "Validate User LogIn Fail When Leaving Fields Blank", dataProvider = "LogIn Fail Blank Field Data", groups = "validation")
	public void logInFailMissingSomeData(String mobileNumber, String password, String expectedMessage) {
		UserLogInPage ulgp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulgp.txtMobileNumber, mobileNumber);
		sendKeys(ulgp.txtPassword, password);
		clickOnElement(ulgp.btnSignIn);
		String actualMessage;
		if (mobileNumber.equals("")) {
			actualMessage = getHtml5ValidationMessage(ulgp.txtMobileNumber);
		} else {
			actualMessage = getHtml5ValidationMessage(ulgp.txtPassword);
		}
		assertEquals(actualMessage, expectedMessage);
	}

	@DataProvider(name = "LogIn Fail Invalid Data Form", indices = { 2 })
	public String[][] logInUsingInvalidDataForm() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(
				"D:\\AutomationTest\\02Projects\\OnlineMarriageRegistration\\TestData\\OMR_TestData.xlsx",
				"UserLogInFail");
		return data;
	}

	@Test(description = "Validate User LogIn Fail When Provide Invalid Data Form", dataProvider = "LogIn Fail Invalid Data Form", groups = "validation")
	public void logInFailUsingInvalidDataForm(String mobileNumber, String password, String expectedMessage) {
		UserLogInPage ulgp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulgp.txtMobileNumber, mobileNumber);
		sendKeys(ulgp.txtPassword, password);
		clickOnElement(ulgp.btnSignIn);
		String actualMessage = getHtml5ValidationMessage(ulgp.txtMobileNumber);
		assertEquals(actualMessage, expectedMessage);
	}

	@Test(description = "Validate Navigate To Reset Password Page", groups = "main")
	public void navigateToResetPasswordpage() {
		UserLogInPage ulgp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		clickOnElement(ulgp.linkTextResetPassword);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("omrs/user/forgot-password"));
	}

	@Test(description = "Validate Navigate To Register Page", groups = "main")
	public void navigateToRegisterPage() {
		UserLogInPage ulgp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		clickOnElement(ulgp.linkTextRegister);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("omrs/user/signup"));
	}

	@Test(description = "Validate Navigate To Home Page", groups = "main")
	public void navigateToHomePage() {
		UserLogInPage ulgp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		clickOnElement(ulgp.linkTextBackHome);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("omrs/index"));
	}
}
