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

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import common.Excelutils;
import page.AdminLogInPage;
import page.AdminViewReportPage;

public class AdminViewReportPageTest extends TestCase {
	public Excelutils excel = new Excelutils();
	public AdminViewReportPage avrp = new AdminViewReportPage(driver);

	@BeforeMethod(description = "Log In")
	public void logIn() {
		AdminLogInPage alp = new AdminLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/login.php");
		sendKeys(alp.txtUserName, "admin");
		sendKeys(alp.txtPassword, "Test@123");
		clickOnElement(alp.btnSignIn);
	}

	
	@Test(description = "Validate To Report Page", groups="main")
	public void validateToReportPage() {
		clickOnElement(avrp.iconReport);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("between-dates-application-report"));
	}
	
	@DataProvider(name = "Search Successful Data")
	public String[][] successfulData() throws IOException {
		String[][] data = excel.getDataFromExcel(dataFilePath, "ViewReportSuccessfully");
		return data;
	}

	@Test(description = "Validate Search Report Successfully", groups="main")
	public void searchReportSuccessfully(String fDate, String toDate) throws ClassNotFoundException, SQLException {
		driver.navigate()
				.to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/between-dates-application-report.php");
		sendKeys(avrp.txtFDate, fDate);
		sendKeys(avrp.txtToDate, toDate);
		clickOnElement(avrp.btnSubmit);

		// Gộp tất cả các elements cần tìm list vào 1 arrays
		By[] elements = { avrp.textRegNo, avrp.textHName, avrp.textDOM, avrp.textStatus };
		String[] expectedResult = new String[elements.length];

		// Tạo mảng 2 chiều để lưu dữ liệu của mỗi cột. Mỗi cột là 1 mảng con.
		String[][] actualResult = new String[elements.length][driver.findElements(avrp.textDOM).size()];

		for (int i = 0; i < elements.length; i++) {

			// Chạy vòng for đầu để lấy được các elements của 1 cột
			List<WebElement> elementsList = driver.findElements(elements[i]);

			// Chạy vòng for 2 để lấy được text từ các elements nhỏ ở các cột
			for (int j = 0; j < elementsList.size(); j++) {

				// Lưu vào 1 mảng 2 chiều to. i là thứ tự cột, j là thứ tự text
				actualResult[i][j] = elementsList.get(j).getText();
			}
			expectedResult[i] = getValueFromDatabase(
					"SELECT RegistrationNumber, HusbandName, DateofMarriage, Status from tblregistration;", i + 1);
		}
		String expectedResultString = Arrays.deepToString(expectedResult).replace("[", "").replace("]", "")
				.replace("null", "Still Pending");
		String actualResultString = Arrays.deepToString(actualResult).replace("[", "").replace("]", "");
		assertEquals(actualResultString, expectedResultString);
	}
	
	@DataProvider(name="Search Fail Data", indices = {0,1,2,3,4})
	public String[][] failData() throws IOException {
		String[][] data= excel.getDataFromExcel(dataFilePath, "ViewReportFail");
		return data;
	}
	
	@Test(description = "View Report Using Invalid Date", dataProvider = "Search Fail Data", groups= "validation")
	public void noReportFound(String fDate, String toDate, String message) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/between-dates-application-report.php");
		sendKeys(avrp.txtFDate, fDate);
		sendKeys(avrp.txtToDate, toDate);
		clickOnElement(avrp.btnSubmit);
		String actualResult=null;
		if(fDate.equals("abcdabcd")){
			actualResult= getHtml5ValidationMessage(avrp.txtFDate);
		}
		else if(toDate.equals("abcdabcd")) {
			actualResult= getHtml5ValidationMessage(avrp.txtToDate);
		}
		else {
			actualResult=driver.findElement(avrp.textNoResultMessage).getText();
		}	 
		assertEquals(message, actualResult);
		}
	
	@DataProvider(name="Leave Field Blank Data", indices = {5,6})
	public String[][] blankFieldData() throws IOException {
		String[][] data= excel.getDataFromExcel(dataFilePath, "ViewReportFail");
		return data;
	}
	
	@Test(description = "View Report Using Invalid Date", dataProvider = "Leave Field Blank Data", groups= "validation")
	public void leaveFieldBlank(String fDate, String toDate, String message) {
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/admin/between-dates-application-report.php");
		sendKeys(avrp.txtFDate, fDate);
		sendKeys(avrp.txtToDate, toDate);
		clickOnElement(avrp.btnSubmit);
		By[] elements= {avrp.txtFDate, avrp.txtToDate};
		String[] data= {fDate, toDate};
		getHtml5ValidationFromASeriesOfFields(elements, data, message);
		}
}
