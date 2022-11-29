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
import page.AdminDashBoardPage;
import page.AdminLogInPage;

public class AdminDashboardPageTest extends TestCase {
	public AdminDashBoardPage adbp = new AdminDashBoardPage(driver);
	public Excelutils excel = new Excelutils();

	@BeforeMethod(description = "Log In")
	public void logIn() {
		AdminLogInPage ulp = new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(ulp.txtUserName, "admin");
		sendKeys(ulp.txtPassword, "Test@123");
		clickOnElement(ulp.btnSignIn);
	}

//	
	@Test(description = "Validate Navigate To Dashboard Page", groups = "main")
	public void navigateToDashboardPage() {
		clickOnElement(adbp.iconDashboard);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("dashboard"));
	}

	@DataProvider(name = "Displayed Data")
	public String[][] displayedData() throws IOException {
		String[][] data = excel.getDataFromExcel(dataFilePath, "AdminDashBoard");
		return data;
	}

	@Test(description = "Validate Displayed Information", groups = "main", dataProvider = "Displayed Data")
	public void validateDisplayedInformation(String newApp, String verifiedApp, String rejectedApp, String all)
			throws ClassNotFoundException, SQLException {
		String[] query = { newApp, verifiedApp, rejectedApp, all };
		List<WebElement> elements = driver.findElements(adbp.textNumbers);
		String[] actualResult = new String[elements.size()];
		String[] expectedResult = new String[elements.size()];
		for (int i = 0; i < elements.size(); i++) {
			actualResult[i] = elements.get(i).getText();
			expectedResult[i] = getValueFromDatabase(query[i], 1);
		}
		String actualResultStr = Arrays.deepToString(actualResult).replace("[", "").replace("]", "");
		String expectedResultStr = Arrays.deepToString(expectedResult).replace("[", "").replace("]", "");
		assertEquals(actualResultStr, expectedResultStr);
	}

	@Test(description = "Validate Navigating To New Application Page", groups = "main")
	public void navigateToNewApplicationPage() {
		clickOnElement(adbp.iconSeeMoreNew);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("new-marriage-application"));
	}

	@Test(description = "Validate Navigating To Verified Application Page", groups = "main")
	public void navigateToVerifiedApplicationPage() {
		clickOnElement(adbp.iconSeeMoreVerified);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("verified-marriage-application"));
	}

	@Test(description = "Validate Navigating To Rejected Application Page", groups = "main")
	public void navigateToRejectedApplicationPage() {
		clickOnElement(adbp.iconSeeMoreRejected);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("rejected-marriage-application"));
	}

	@Test(description = "Validate Navigating To All Application Page", groups = "main")
	public void navigateToAllApplicationPage() {
		clickOnElement(adbp.iconSeeMoreAll);
		String currentURL = driver.getCurrentUrl();
		assertTrue(currentURL.contains("all-marriage-application"));
	}
}
