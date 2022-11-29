package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.UserChangePasswordPage;
import page.UserLogInPage;

public class UserChangePasswordPageTest extends TestCase {
	@BeforeMethod(description = "Log In")
	public void logIn() {
		UserLogInPage ulp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulp.txtMobileNumber, "1234567890");
		sendKeys(ulp.txtPassword, "Test@123");
		clickOnElement(ulp.btnSignIn);
	}
@DataProvider(name = "Change Password Successfully Data")
public String[][] successfulData() throws IOException {
	Excelutils excel= new Excelutils();
	String[][]data=  excel.getDataFromExcel(dataFilePath, "UserChangePasswordSuccessfully");
	return data;

}
	@Test(description = "Validate Change Password Successfully", dataProvider = "Change Password Successfully Data", priority = 3)
public void changePasswordSuccessfully(String currentPass, String newPass, String confirmPass, String expectedMessage) {
	UserChangePasswordPage ucpp= new UserChangePasswordPage(driver);
	clickOnElement(ucpp.dropdownProfile);
	clickOnElement(ucpp.linkTextSetting);
	sendKeys(ucpp.txtCurrentPass, currentPass);
	sendKeys(ucpp.txtNewPass, newPass);
	sendKeys(ucpp.txtConfirmPass, confirmPass);
	clickOnElement(ucpp.btnChange);
	String actualMessage= getLocalMessage();
	assertEquals(actualMessage, expectedMessage);
}
	
	@DataProvider(name = "Change Password Fail Data", indices = {3,4,5})
	public String[][] failData() throws IOException {
		Excelutils excel= new Excelutils();
		String[][]data=  excel.getDataFromExcel(dataFilePath, "UserChangePasswordFail");
		return data;

	}
		@Test(description = "Validate Change Password Fail Using Invalid Data", dataProvider = "Change Password Fail Data", priority = 2)
	public void changePasswordFailUsingInvalidData(String currentPass, String newPass, String confirmPass, String expectedMessage) {
		UserChangePasswordPage ucpp= new UserChangePasswordPage(driver);
		clickOnElement(ucpp.dropdownProfile);
		clickOnElement(ucpp.linkTextSetting);
		sendKeys(ucpp.txtCurrentPass, currentPass);
		sendKeys(ucpp.txtNewPass, newPass);
		sendKeys(ucpp.txtConfirmPass, confirmPass);
		clickOnElement(ucpp.btnChange);
		String actualMessage= getLocalMessage();
		assertEquals(actualMessage, expectedMessage);
	}	
		
		@DataProvider(name = "Change Password Fail Blank Field Data", indices = {0,1,2})
		public String[][] blankFieldData() throws IOException {
			Excelutils excel= new Excelutils();
			String[][]data=  excel.getDataFromExcel(dataFilePath, "UserChangePasswordFail");
			return data;

		}
			@Test(description = "Validate Change Password Fail When Leaving Field Blank", dataProvider = "Change Password Fail Blank Field Data", priority = 1)
		public void changePasswordFailWhenLeavingFieldBlank(String currentPass, String newPass, String confirmPass, String expectedMessage) {
			UserChangePasswordPage ucpp= new UserChangePasswordPage(driver);
			clickOnElement(ucpp.dropdownProfile);
			clickOnElement(ucpp.linkTextSetting);
			sendKeys(ucpp.txtCurrentPass, currentPass);
			sendKeys(ucpp.txtNewPass, newPass);
			sendKeys(ucpp.txtConfirmPass, confirmPass);
			clickOnElement(ucpp.btnChange);
			String actualMessage= null;
			if(currentPass.equals("")) {
				actualMessage= getHtml5ValidationMessage(ucpp.txtCurrentPass);
			}
			else if(newPass.equals("")) {
				actualMessage= getHtml5ValidationMessage(ucpp.txtNewPass);
			}
			else if(confirmPass.equals("")) {
				actualMessage= getHtml5ValidationMessage(ucpp.txtConfirmPass);
			}
			assertEquals(actualMessage, expectedMessage);
		}	
}
