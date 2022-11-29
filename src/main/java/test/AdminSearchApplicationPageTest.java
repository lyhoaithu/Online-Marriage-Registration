package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.AdminLogInPage;
import page.AdminSearchApplicationPage;

public class AdminSearchApplicationPageTest extends TestCase {
	public AdminSearchApplicationPage asap = new AdminSearchApplicationPage(driver);

	@BeforeMethod(description = "Log In")
	public void logIn() {
		AdminLogInPage alp = new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(alp.txtUserName, "admin");
		sendKeys(alp.txtPassword, "Test@123");
		clickOnElement(alp.btnSignIn);
	}

	@Test(description = "Validate navigate to search page", groups = "main")
	public void navigateToSearchPage() {
		clickOnElement(asap.linktextSearch);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("user/search"));

	}

	@DataProvider(name = "Successful Data")
	public String[][] successfulData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "AdminSearchSuccessfully");
		return data;

	}

	@Test(description = "Validate Search Successfully", dataProvider = "Successful Data", groups="main")
	public void searchSuccessfully(String data, String query) throws ClassNotFoundException, SQLException {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/search.php");
		sendKeys(asap.txtSearch, data);
		clickOnElement(asap.btnSubmit);
		List<WebElement> elements = driver.findElements(asap.textDetails);
		String[] actualResult = new String[elements.size() - 2];
		String[] expectedResult = new String[elements.size() - 2];
		System.out.println(elements.size());
		for (int i = 0; i < 4; i++) {
			actualResult[i] = elements.get(i + 1).getText();
		}

		for (int i = 1; i < 5; i++) {
			expectedResult[i - 1] = getValueFromDatabase(query, i);
		}
		String actualResultStr = Arrays.deepToString(actualResult).replace("[", "").replace("]", "");
		String expectedResultStr = Arrays.deepToString(expectedResult).replace("[", "").replace("]", "");
		assertEquals(actualResultStr, expectedResultStr);
	}


	@DataProvider(name = "Invalid Data", indices = {0,1,2,3})
	public String[][] invalidData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "AdminSearchFail");
		return data;

	}

	@Test(description = "Validate Search Fail", dataProvider = "Invalid Data", groups="validation")
	public void searchFail(String data, String result) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/search.php");
		sendKeys(asap.txtSearch, data);
		clickOnElement(asap.btnSubmit);
		String actualWarningMessage= driver.findElement(asap.messageNoResult).getText();
		assertEquals(actualWarningMessage, result);
	}
	
	@DataProvider(name = "Leave Field Blank Data", indices = {4})
	public String[][] fieldBlankData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "AdminSearchFail");
		return data;

	}

	@Test(description = "Validate Search Fail When Leaving Field Blank", dataProvider = "Leave Field Blank Data", groups="validation")
	public void searchFailWithBlankField(String data, String result) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/search.php");
		sendKeys(asap.txtSearch, data);
		clickOnElement(asap.btnSubmit);
		String actualWarningMessage= getHtml5ValidationMessage(asap.txtSearch);
		assertEquals(actualWarningMessage, result);
	}

}
