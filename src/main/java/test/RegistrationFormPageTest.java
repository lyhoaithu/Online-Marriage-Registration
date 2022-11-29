package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
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
import page.UserRegistrationFormPage;

public class RegistrationFormPageTest extends TestCase {
	@BeforeMethod(description = "Log In")
	public void logIn() {
		UserLogInPage ulp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulp.txtMobileNumber, "1234567890");
		sendKeys(ulp.txtPassword, "Test@123");
		clickOnElement(ulp.btnSignIn);
	}

	@DataProvider(name = "Fill In Registration Form Successful Data")
	public String[][] validData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "RegistrationFormSuccessfully");

		return data;

	}

	@Test(description = "Validate Fill In Registration Form Successfully", dataProvider = "Fill In Registration Form Successful Data", groups = {"main", "register successfully"})
	public void fillInSuccessfully(String DOM, String Hname, String HPhoto, String HReligion, String DOB,
			String HAddress, String HZipcode, String HState, String HAN, String Wname, String WPhoto, String WReligion,
			String WDOB, String WAddress, String WZipcode, String WState, String WAN, String W1Name, String W1Address,
			String W2Name, String W2Address, String W3Name, String W3Address, String message) throws IOException {
		UserRegistrationFormPage urfp = new UserRegistrationFormPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/marriage-reg-form.php");

		/*
		 * Xử lý Date picker: 1. Click vào box để hiện lên calendar 2. Tìm tất cả các
		 * element chứa ngày 3. chạy vòng for đến khi nào tìm được giá trị cần tìm thì
		 * chọn .click
		 */

		/*
		 * clickOnElement(urfp.pickerDate); List<WebElement>date=
		 * driver.findElements(urfp.date); for (int i=0; i< date.size(); i++) { String
		 * chosenDate=date.get(i).getText(); if(chosenDate.equals("2")) {
		 * date.get(i).click(); }
		 */

		String[] data = { DOM, Hname, HPhoto, HReligion, DOB, HAddress, HZipcode, HState, HAN, Wname, WPhoto, WReligion,
				WDOB, WAddress, WZipcode, WState, WAN, W1Name, W1Address, W2Name, W2Address, W3Name, W3Address,
				message };

		By[] elements = urfp.elements;
//	  //Chạy vòng for với i là index đầu của data [i][...]
//	  for (int i=0; i<data.length; i++) { 
		// chạy vòng for với j là lần lượt các element cần điền vào
		for (int j = 0; j < elements.length; j++) {
			sendKeys(elements[j], data[j]);
//			  driver.findElement(elements[j]).sendKeys(Keys.ENTER);
		}

//	  }
		selectDropDownBox(urfp.dropdownHStatus, 1);
		selectDropDownBox(urfp.dropdownWStatus, 1);
		clickOnElement(urfp.btnAdd);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, message);
		acceptAlertMessage();
	}

	@DataProvider(name = "Fill In Registration Form Invalid Data", indices = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12,
			13, 14, 15, 16, 17,18,19,20 })
	public String[][] invalidData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "RegistrationFormFail");

		return data;

	}

	@Test(description = "Registration Fail Using Invalid Data", dataProvider = "Fill In Registration Form Invalid Data", groups = "validation")
	public void registrationFailUsingInvalidData(String DOM, String Hname, String HPhoto, String HReligion, String DOB,
			String HAddress, String HZipcode, String HState, String HAN, String Wname, String WPhoto, String WReligion,
			String WDOB, String WAddress, String WZipcode, String WState, String WAN, String W1Name, String W1Address,
			String W2Name, String W2Address, String W3Name, String W3Address, String message) {
		String[] data = { DOM, Hname, HPhoto, HReligion, DOB, HAddress, HZipcode, HState, HAN, Wname, WPhoto, WReligion,
				WDOB, WAddress, WZipcode, WState, WAN, W1Name, W1Address, W2Name, W2Address, W3Name, W3Address,
				message };
		UserRegistrationFormPage urfp = new UserRegistrationFormPage(driver);
		By[] elements = urfp.elements;
		for (int j = 0; j < elements.length; j++) {
			sendKeys(elements[j], data[j]);
		}

		selectDropDownBox(urfp.dropdownHStatus, 1);
		selectDropDownBox(urfp.dropdownWStatus, 1);
		clickOnElement(urfp.btnAdd);
		String actualMessage = getLocalMessage();
		assertEquals(actualMessage, message);
		acceptAlertMessage();

	}
	
	@DataProvider(name = "Fill In Registration Form Blank Field", indices = {21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44})
	public String[][] fieldBlankData() throws IOException {
		Excelutils excel = new Excelutils();
		String[][] data = excel.getDataFromExcel(dataFilePath, "RegistrationFormFail");

		return data;

	}

	@Test(description = "Registration Fail Using Invalid Data", dataProvider = "Fill In Registration Form Blank Field", groups = "validation")
	public void registrationFailWhenLeavingFieldBlank(String DOM, String Hname, String HPhoto, String HReligion, String DOB,
			String HAddress, String HZipcode, String HState, String HAN, String Wname, String WPhoto, String WReligion,
			String WDOB, String WAddress, String WZipcode, String WState, String WAN, String W1Name, String W1Address,
			String W2Name, String W2Address, String W3Name, String W3Address, String message) {
		String[] data = { DOM, Hname, HPhoto, HReligion, DOB, HAddress, HZipcode, HState, HAN, Wname, WPhoto, WReligion,
				WDOB, WAddress, WZipcode, WState, WAN, W1Name, W1Address, W2Name, W2Address, W3Name, W3Address,
				message};
		UserRegistrationFormPage urfp = new UserRegistrationFormPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/marriage-reg-form.php");
		By[] elements = urfp.elements;
		for (int j = 0; j < elements.length; j++) {
			sendKeys(elements[j], data[j]);
		}
		selectDropDownBox(urfp.dropdownHStatus, 1);
		selectDropDownBox(urfp.dropdownWStatus, 1);
		clickOnElement(urfp.btnAdd);
		String actualMessage = null;
		for (int i=0; i<elements.length; i++) {
			if(data[i].equals("")) {
				actualMessage= getHtml5ValidationMessage(elements[i]);
			}
		}
		assertEquals(actualMessage, message);
	}
	
	@Test(description = "Validate Navigate To Registration Form Page", groups="main")
	public void navigateToRegistrationFormPage() {
		UserRegistrationFormPage urfp= new UserRegistrationFormPage(driver);
		clickOnElement(urfp.linkTextRegForm);
		String currentURL= driver.getCurrentUrl();
		assertTrue(currentURL.contains("marriage-reg-form"));

	}
}
