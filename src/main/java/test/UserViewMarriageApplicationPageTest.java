package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import common.Excelutils;
import page.UserLogInPage;
import page.UserViewMarriageApplicationPage;

public class UserViewMarriageApplicationPageTest extends TestCase {
	@BeforeMethod(description = "Log In")
	public void logIn() {
		UserLogInPage ulp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulp.txtMobileNumber, "1234567890");
		sendKeys(ulp.txtPassword, "Test@123");
		clickOnElement(ulp.btnSignIn);
	}
	
@Test(description = "Validate Navigate To View Marriage Application Page", groups="main")
public void navigateToViewMarriageApplicationPage() {
	UserViewMarriageApplicationPage uvmap= new UserViewMarriageApplicationPage(driver);
	clickOnElement(uvmap.linktextView);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("status-marriage-application"));
}

@DataProvider(name = "Search Successfully Data")
public String[][] validData() throws IOException{
	Excelutils excel= new Excelutils();
	String[][] data= excel.getDataFromExcel(dataFilePath, "UserSearchSuccessfully");
	return data;
}
@Test(description = "Validate Searching Marriage Application Successfully", dataProvider = "Search Successfully Data", groups="main")
public void validateSearchingSuccessfully(String searchData, String sqlCommand) throws ClassNotFoundException, SQLException {
	UserViewMarriageApplicationPage uvmap= new UserViewMarriageApplicationPage(driver);
	driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/status-marriage-application.php");
	sendKeys(uvmap.txtSearch, searchData);
	driver.findElement(uvmap.txtSearch).sendKeys(Keys.ENTER);
	String dbResult= getValueFromDatabase(sqlCommand, 1).replace("[", "").replace("]", "");
	String actualResult= driver.findElement(uvmap.textRegNo).getText();
assertEquals(dbResult, actualResult);
}

@DataProvider(name = "Search Fail Data")
public String[][] failData() throws IOException{
	Excelutils excel= new Excelutils();
	String[][] data= excel.getDataFromExcel(dataFilePath, "UserSearchFail");
	return data;
}
@Test(description = "Validate Searching Marriage Application Fail", dataProvider = "Search Fail Data", groups="validation")
public void validateSearchingFail(String searchData, String result) throws ClassNotFoundException, SQLException {
	UserViewMarriageApplicationPage uvmap= new UserViewMarriageApplicationPage(driver);
	driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/status-marriage-application.php");
	sendKeys(uvmap.txtSearch, searchData);
	driver.findElement(uvmap.txtSearch).sendKeys(Keys.ENTER);
	String actualResult= driver.findElement(uvmap.errorMessage).getText();
assertEquals(result, actualResult);
}

@Test(description = "Validate Displayed Register Number", groups="validation")
public void validateDisplayedRegisterNumber() throws ClassNotFoundException, SQLException {
	UserViewMarriageApplicationPage uvmap= new UserViewMarriageApplicationPage(driver);
	driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/status-marriage-application.php");
	List<WebElement> elements= driver.findElements(uvmap.textRegNo);
	String[] regNo= new String[elements.size()];
	for (int i=0; i<elements.size();i++) {
		regNo[i]=elements.get(i).getText();
	}
	String actualResult= Arrays.deepToString(regNo);
	String expectedResult= getValueFromDatabase("SELECT RegistrationNumber from tblregistration where UserID='2';", 1);
assertEquals(actualResult, expectedResult);
}
	
	@Test(description = "Validate Sorting S.NO", groups="validation")
	public void validateSortingSNO() throws ClassNotFoundException, SQLException {
		UserViewMarriageApplicationPage uvmap= new UserViewMarriageApplicationPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/status-marriage-application.php");
		clickOnElement(uvmap.btnSort);
		List<WebElement> elements= driver.findElements(uvmap.textRegNo);
		String[] regNo= new String[elements.size()];
		for (int i=0; i<elements.size();i++) {
			regNo[i]=elements.get(i).getText();
		}
		String actualResult= Arrays.deepToString(regNo);
		String expectedResult= getValueFromDatabase("SELECT RegistrationNumber from tblregistration where UserID='2' order BY DateOfMarriage DESC;", 1);
	assertEquals(actualResult, expectedResult);
	}
	
	
	@DataProvider(name = "Displayed General Infor Data")
	public String[][] GeneralData() throws IOException{
		Excelutils excel= new Excelutils();
		String[][] data= excel.getDataFromExcel(dataFilePath, "ValidateGeneralInfor");
		return data;
	}
	
	@Test(description = "Validate Application Displayed General Information", dataProvider = "Displayed General Infor Data", groups="validation")
	public void validateDisplayedGeneralInfor(String query, String expectedRowtitle, String index) throws ClassNotFoundException, SQLException {
		UserViewMarriageApplicationPage uvmap= new UserViewMarriageApplicationPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/status-marriage-application.php");
		clickOnElement(By.xpath("//tr[@class='"+index+"']//td"));
List<WebElement> rowTitleList= driver.findElements(uvmap.rowTitle);
List<WebElement> rowDataList= driver.findElements(uvmap.rowData);
String [] rowTitleArr= new String[rowTitleList.size()-1];
String [] rowDataArr= new String[rowDataList.size()-1];
String [] expectedRowDataArr= new String[rowTitleList.size()-1];
for (int i=0; i<rowTitleList.size()-1; i++) {
	rowTitleArr[i]= rowTitleList.get(i).getText();
	rowDataArr[i]= rowDataList.get(i).getText();
	expectedRowDataArr[i]= getValueFromDatabase(query, i+1);
}
if(rowDataArr[2].equalsIgnoreCase("Verified")) {
	clickOnElement(uvmap.iconViewApplication);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("view-marriage-application-detail"));
}
else {
	
	assertEquals(checkElementPresence(uvmap.iconUnviewable), true);
}
String actualRowTitle=  Arrays.deepToString(rowTitleArr).replace("[", "").replace("]", "");
String actualRowData= Arrays.deepToString(rowDataArr).replace("[", "").replace("]", "");
String expectedRowData= Arrays.deepToString(expectedRowDataArr).replace("[", "").replace("]", "");
assertEquals(expectedRowData, actualRowData);
assertEquals(expectedRowtitle, actualRowTitle);

	}
}
