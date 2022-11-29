package test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.UserEditProfilePage;
import page.UserLogInPage;

public class UserEditInformationPageTest extends TestCase {

	@BeforeMethod(description = "Log In")
	public void logIn() {
		UserLogInPage ulp = new UserLogInPage(driver);
		driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/login.php");
		sendKeys(ulp.txtMobileNumber, "1234567890");
		sendKeys(ulp.txtPassword, "Test@123");
		clickOnElement(ulp.btnSignIn);
	}

	
	  @DataProvider(name = "User Edit Information Successfully Data") public
	  String[][] userEditInformationSuccessfully() throws IOException { Excelutils
	  excelutils= new Excelutils(); String[][] data=
	  excelutils.getDataFromExcel(dataFilePath, "UserEditInformationSuccessfully");
	  return data;
	  
	  }
	  
	  @Test(description = "Validate Edit Information Successfully", dataProvider =
	  "User Edit Information Successfully Data", groups="main") public void
	  editSuccessfully(String fname, String lname, String address, String
	  expectedMessage) { UserEditProfilePage uepp= new UserEditProfilePage(driver);
	  clickOnElement(uepp.dropdownProfile);
	  clickOnElement(uepp.linkTextEditProfile);
	  sendKeysWithAttributeAvailable(uepp.txtFname, fname);
	  sendKeysWithAttributeAvailable(uepp.txtLname, lname);
	  sendKeysWithAttributeAvailable(uepp.txtAddress, address);
	  clickOnElement(uepp.btnUpdate); String actualMessage= getLocalMessage();
	  assertEquals(expectedMessage, actualMessage); }
	  
	  @DataProvider(name =
	  "User Edit Information Fail When Leaving Field Blank Data", indices = {0, 1,
	  2}) public String[][] userEditInformationWithFieldBlank() throws IOException
	  { Excelutils excelutils= new Excelutils(); String[][] data=
	  excelutils.getDataFromExcel(dataFilePath, "UserEditInformationFail"); return
	  data;
	  
	  }
	  
	  @Test(description =
	  "Validate User Edit Information Fail When Leaving Field Blank", dataProvider
	  = "User Edit Information Fail When Leaving Field Blank Data", groups="validation") public void
	  editFailWithFieldBlank(String fname, String lname, String address, String
	  expectedMessage) { UserEditProfilePage uepp= new UserEditProfilePage(driver);
	  clickOnElement(uepp.dropdownProfile);
	  clickOnElement(uepp.linkTextEditProfile);
	  sendKeysWithAttributeAvailable(uepp.txtFname, fname);
	  sendKeysWithAttributeAvailable(uepp.txtLname, lname);
	  sendKeysWithAttributeAvailable(uepp.txtAddress, address);
	  clickOnElement(uepp.btnUpdate); String actualMessage=null;
	  if(fname.equals("")) { actualMessage=
	  getHtml5ValidationMessage(uepp.txtFname); } else if(lname.equals("")) {
	  actualMessage= getHtml5ValidationMessage(uepp.txtLname); } else
	  if(address.equals("")) { actualMessage=
	  getHtml5ValidationMessage(uepp.txtAddress); } assertEquals(expectedMessage,
	  actualMessage); }
	  
	  
	  @DataProvider(name = "User Edit Information Fail Invalid Data", indices = {3,
	  4}) public String[][] userEditInformationWithInvalidData() throws IOException
	  { Excelutils excelutils= new Excelutils(); String[][] data=
	  excelutils.getDataFromExcel(dataFilePath, "UserEditInformationFail"); return
	  data;
	  
	  }
	  
	  @Test(description = "Validate User Edit Information Fail When Using Invalid",
	  dataProvider = "User Edit Information Fail Invalid Data", groups="valiadtion") public void
	  editFailWithInvalidData(String fname, String lname, String address, String
	  expectedMessage) { UserEditProfilePage uepp= new UserEditProfilePage(driver);
	  clickOnElement(uepp.dropdownProfile);
	  clickOnElement(uepp.linkTextEditProfile);
	  sendKeysWithAttributeAvailable(uepp.txtFname, fname);
	  sendKeysWithAttributeAvailable(uepp.txtLname, lname);
	  sendKeysWithAttributeAvailable(uepp.txtAddress, address);
	  clickOnElement(uepp.btnUpdate); String actualMessage=getLocalMessage();
	  assertEquals(actualMessage, expectedMessage); }
	 
	@Test(description = "Validate Phone Number and Registration Date Is Uneditable", groups="validaiton")
	public void validateUneditableFields() {
		UserEditProfilePage uepp = new UserEditProfilePage(driver);
		clickOnElement(uepp.dropdownProfile);
		clickOnElement(uepp.linkTextEditProfile);
		sendKeys(uepp.txtMobNo, "0123456788");
		sendKeys(uepp.txtRegistrationDate, "08/08/2022");
		String actualMobNoValue = driver.findElement(uepp.txtMobNo).getAttribute("value");
		String actualRegistrationDate = driver.findElement(uepp.txtRegistrationDate).getAttribute("value");
		assertEquals(actualMobNoValue, "1234567890");
		assertEquals(actualRegistrationDate, "2020-05-02 17:46:08");

	}
}
