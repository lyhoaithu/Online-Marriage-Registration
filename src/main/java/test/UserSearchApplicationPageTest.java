package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.UserLogInPage;
import page.UserSearchApplicationFormPage;

public class UserSearchApplicationPageTest extends TestCase {

	public UserSearchApplicationFormPage usafp = new UserSearchApplicationFormPage(driver);

	@BeforeMethod(description = "Log In")
	public void logIn() {
		UserLogInPage ulp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulp.txtMobileNumber, "1234567890");
		sendKeys(ulp.txtPassword, "Test@123");
		clickOnElement(ulp.btnSignIn);
	}

	@Test(description = "Validate navigate to search page", groups = "main")
	public void navigateToSearchPage() {
		clickOnElement(usafp.linktextSearch);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("user/search"));

	}

	@DataProvider(name = "Successful Data")
	public String[][] successfulData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "SearchSuccessfully");
		return data;

	}

	@Test(description = "Validate Search Successfully", dataProvider = "Successful Data", groups="main")
	public void searchSuccessfully(String data, String query) throws ClassNotFoundException, SQLException {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/search.php");
		sendKeys(usafp.txtSearch, data);
		clickOnElement(usafp.btnSubmit);
		List<WebElement> elements = driver.findElements(usafp.textDetails);
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
		String[][] data = excel.getDataFromExcel(dataFilePath, "SearchFail");
		return data;

	}

	@Test(description = "Validate Search Fail", dataProvider = "Invalid Data", groups="validation")
	public void searchFail(String data, String result) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/search.php");
		sendKeys(usafp.txtSearch, data);
		clickOnElement(usafp.btnSubmit);
		String actualWarningMessage= driver.findElement(usafp.messageNoResult).getText();
		assertEquals(actualWarningMessage, result);
	}
	
	@DataProvider(name = "Leave Field Blank Data", indices = {4})
	public String[][] fieldBlankData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "SearchFail");
		return data;

	}

	@Test(description = "Validate Search Fail When Leaving Field Blank", dataProvider = "Leave Field Blank Data", groups="validation")
	public void searchFailWithBlankField(String data, String result) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/search.php");
		sendKeys(usafp.txtSearch, data);
		clickOnElement(usafp.btnSubmit);
		String actualWarningMessage= getHtml5ValidationMessage(usafp.txtSearch);
		assertEquals(actualWarningMessage, result);
	}
}
