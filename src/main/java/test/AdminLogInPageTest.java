package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.AdminLogInPage;

public class AdminLogInPageTest extends TestCase{
	@DataProvider(name = "Sign In Successfully Data")
	public String[][] SignInSuccessfullyData() throws IOException {
		Excelutils excelUtils= new Excelutils();
		String[][] data=excelUtils.getDataFromExcel(dataFilePath, "AdminLogInSuccessfully");
return data;
	}
	@Test(description="Validate Sign In Successfully", groups="main", dataProvider = "Sign In Successfully Data")
	public void SignInSuccessfully(String username, String password) {
		AdminLogInPage adminLogInPage= new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(adminLogInPage.txtUserName, username);
		sendKeys(adminLogInPage.txtPassword, password);
		clickOnElement(adminLogInPage.btnSignIn);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("omrs/admin/dashboard"));
	}
	
	@DataProvider(name = "Sign In Invalid Data", indices = {0,1})
	public String[][] SignInInvalidData() throws IOException {
		Excelutils excelUtils= new Excelutils();
		String[][] data=excelUtils.getDataFromExcel(dataFilePath, "AdminLogInFail");
return data;
	}
	
	@Test(description = "Validate Sign In Fail When Provide Invalid Information", groups="validation", dataProvider = "Sign In Invalid Data")
	public void SignInUsingInvalidData(String username, String password, String message) {
		AdminLogInPage adminLogInPage= new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(adminLogInPage.txtUserName, username);
		sendKeys(adminLogInPage.txtPassword, password);
		clickOnElement(adminLogInPage.btnSignIn);
		String actualMessage=getLocalMessage();
		assertEquals(actualMessage, message);
	}

	@DataProvider(name = "Sign In With Field Blank", indices = {2,3})
	public String[][] SignInWithFieldBlankData() throws IOException {
		Excelutils excelUtils= new Excelutils();
		String[][] data=excelUtils.getDataFromExcel(dataFilePath, "AdminLogInFail");
return data;
	}
	
	@Test(description = "Validate Sign In Fail When Provide Invalid Information", groups="validation", dataProvider = "Sign In With Field Blank")
	public void SignInWithFieldBlank(String username, String password, String message) {
		AdminLogInPage adminLogInPage= new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(adminLogInPage.txtUserName, username);
		sendKeys(adminLogInPage.txtPassword, password);
		clickOnElement(adminLogInPage.btnSignIn);
		String actualMessage=null;
		if(username.equals("")) {
			actualMessage=getHtml5ValidationMessage(adminLogInPage.txtUserName);
		}
		else if(password.equals("")) {
			actualMessage=getHtml5ValidationMessage(adminLogInPage.txtPassword);
		}
		assertEquals(actualMessage, message);
}
}
