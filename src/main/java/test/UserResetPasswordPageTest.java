package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.UserResetPasswordPage;

public class UserResetPasswordPageTest extends TestCase {
	
	@DataProvider(name = "Reset Password Successfully Data")
	public String[][] validD() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "UserResetPasswordSuccessfully");
		return data;

	}

	@Test(description = "Validate Reset Password Successfully", dataProvider = "Reset Password Successfully Data", groups = "main")
	public void resetPasswordSuccessfully(String mobileNumber, String newPassword, String confirmPassword,
			String expectedMessage) {
		UserResetPasswordPage urpp = new UserResetPasswordPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/forgot-password.php");
		sendKeys(urpp.txtMobileNumber, mobileNumber);
		sendKeys(urpp.txtNewPassword, newPassword);
		sendKeys(urpp.txtConfirmPassword, confirmPassword);
		clickOnElement(urpp.btnReset);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Reset Password Incorrect Data", indices = {0,1,2})
	public String[][] incorrectData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "UserResetPasswordFail");
		return data;

	}

	@Test(description = "Validate Reset Password Fail Using Incorrect Data", dataProvider = "Reset Password Incorrect Data", groups = "validation")
	public void resetPasswordFailUsingIncorrectData(String mobileNumber, String newPassword, String confirmPassword,
			String expectedMessage) {
		UserResetPasswordPage urpp = new UserResetPasswordPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/forgot-password.php");
		sendKeys(urpp.txtMobileNumber, mobileNumber);
		sendKeys(urpp.txtNewPassword, newPassword);
		sendKeys(urpp.txtConfirmPassword, confirmPassword);
		clickOnElement(urpp.btnReset);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
	}
	
	@DataProvider(name = "Reset Password Blank Field Data", indices = {3,4,5})
	public String[][] blankFieldData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "UserResetPasswordFail");
		return data;

	}

	@Test(description = "Validate Reset Password Fail Using Incorrect Data", dataProvider = "Reset Password Blank Field Data", groups = "validation")
	public void resetPasswordFailLeavingBlankFields(String mobileNumber, String newPassword, String confirmPassword,
			String expectedMessage) {
		UserResetPasswordPage urpp = new UserResetPasswordPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/forgot-password.php");
		sendKeys(urpp.txtMobileNumber, mobileNumber);
		sendKeys(urpp.txtNewPassword, newPassword);
		sendKeys(urpp.txtConfirmPassword, confirmPassword);
		clickOnElement(urpp.btnReset);
		String actualMessage = null;
		if(mobileNumber.equals("")) {
			actualMessage=getHtml5ValidationMessage(urpp.txtMobileNumber);
		}
		else if(newPassword.equals("")) {
			actualMessage=getHtml5ValidationMessage(urpp.txtNewPassword);
		}
		else if(confirmPassword.equals("")) {
			actualMessage=getHtml5ValidationMessage(urpp.txtConfirmPassword);
		}
		assertEquals(actualMessage, expectedMessage);
	}
	
	@Test(description = "Validate Navigate To Sign In Page", groups = "main")
	public void navigateToSignInPage() {
		UserResetPasswordPage urpp = new UserResetPasswordPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/forgot-password.php");
		clickOnElement(urpp.linkTextSignIn);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("/omrs/user/login"));
	}
}
