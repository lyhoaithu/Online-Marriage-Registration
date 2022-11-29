package test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import common.Excelutils;
import page.UserSignUpPage;

public class UserSignUpPageTest extends TestCase{
	@DataProvider (name = "Sign Up Successfully Data")
	public String[][] signUpSuccessfullyData() throws IOException {
		Excelutils excelutils= new Excelutils();
		String [][]data= excelutils.getDataFromExcel(dataFilePath, "UserSignUpSuccessfully");
return data;
	}
@Test(description="Validate Sign Up Successfully", groups = "main", dataProvider = "Sign Up Successfully Data")
public void signUpSuccessfully(String fname, String lname, String mobno, String address, String password, String expectedMessage) {
	UserSignUpPage usup= new UserSignUpPage(driver);
	driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/signup.php");
	sendKeys(usup.txtFirstName, fname);
	sendKeys(usup.txtLastName, lname);
	sendKeys(usup.txtMobileNumber, mobno);
	sendKeys(usup.txtAddress, address);
	sendKeys(usup.txtPassword, password);
	clickOnElement(usup.btnSignUp);
	String actualMessage= getLocalMessage();
	assertEquals(actualMessage, expectedMessage);

}

@DataProvider (name = "Sign Up Fail Invalid Data", indices = {7})
public String[][] signUpFailUsingInavlidData() throws IOException {
	Excelutils excelutils= new Excelutils();
	String [][]data= excelutils.getDataFromExcel(dataFilePath, "UserSignUpFail");
return data;
}
@Test(description="Validate Sign Up Fail Using Invalid Data", groups = "validation", dataProvider = "Sign Up Fail Invalid Data")
public void signUpFailUsingInvalidData(String fname, String lname, String mobno, String address, String password, String expectedMessage) {
UserSignUpPage usup= new UserSignUpPage(driver);
driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/signup.php");
sendKeys(usup.txtFirstName, fname);
sendKeys(usup.txtLastName, lname);
sendKeys(usup.txtMobileNumber, mobno);
sendKeys(usup.txtAddress, address);
sendKeys(usup.txtPassword, password);
clickOnElement(usup.btnSignUp);
String actualMessage= getLocalMessage();
assertEquals(actualMessage, expectedMessage);
}

@DataProvider (name = "Sign Up Fail Invalid Data Form", indices = {5,6})
public String[][] signUpFailUsingInavlidDataForm() throws IOException {
	Excelutils excelutils= new Excelutils();
	String [][]data= excelutils.getDataFromExcel(dataFilePath, "UserSignUpFail");
return data;
}
@Test(description="Validate Sign Up Fail Using Invalid Data Form", groups = "validation", dataProvider = "Sign Up Fail Invalid Data Form")
public void signUpFailUsingInvalidDataForm(String fname, String lname, String mobno, String address, String password, String expectedMessage) {
UserSignUpPage usup= new UserSignUpPage(driver);
driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/signup.php");
sendKeys(usup.txtFirstName, fname);
sendKeys(usup.txtLastName, lname);
sendKeys(usup.txtMobileNumber, mobno);
sendKeys(usup.txtAddress, address);
sendKeys(usup.txtPassword, password);
clickOnElement(usup.btnSignUp);
String actualMessage= getHtml5ValidationMessage(usup.txtMobileNumber);
assertEquals(actualMessage, expectedMessage);
}

@DataProvider (name = "Sign Up Blank Field", indices = {0, 1, 2, 3, 4})
public String[][] signUpFailLeavingFieldsBlank() throws IOException {
	Excelutils excelutils= new Excelutils();
	String [][]data= excelutils.getDataFromExcel(dataFilePath, "UserSignUpFail");
return data;
}
@Test(description="Validate Sign Up Leaving Fields Blank", groups = "validation", dataProvider = "Sign Up Blank Field")
public void signUpFailLeavingFieldsBlank(String fname, String lname, String mobno, String address, String password, String expectedMessage) {
UserSignUpPage usup= new UserSignUpPage(driver);
driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/signup.php");
sendKeys(usup.txtFirstName, fname);
sendKeys(usup.txtLastName, lname);
sendKeys(usup.txtMobileNumber, mobno);
sendKeys(usup.txtAddress, address);
sendKeys(usup.txtPassword, password);
clickOnElement(usup.btnSignUp);
String actualMessage=null;
if(fname.equals("")) {
	actualMessage= getHtml5ValidationMessage(usup.txtFirstName);
}
else if(lname.equals("")) {
	actualMessage= getHtml5ValidationMessage(usup.txtLastName);
}
else if(mobno.equals("")) {
	actualMessage= getHtml5ValidationMessage(usup.txtMobileNumber);
}
else if(address.equals("")) {
	actualMessage= getHtml5ValidationMessage(usup.txtAddress);
}
else if(password.equals("")) {
	actualMessage= getHtml5ValidationMessage(usup.txtPassword);
}
assertEquals(actualMessage, expectedMessage);
}

@Test(description = "Validate Navigate To Home Page", groups="main")
public void navigateToHomePage() {
	UserSignUpPage usup= new UserSignUpPage(driver);
	driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/signup.php");
	clickOnElement(usup.linkTextHome);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("index"));

}

@Test(description = "Validate Navigate To Sign In Page", groups = "main")
public void navigateToSignInPage() {
	UserSignUpPage usup= new UserSignUpPage(driver);
	driver.navigate().to("http://localhost:8081/OnlineMarriageRegistration/omrs/user/signup.php");
	clickOnElement(usup.linkTextSignIn);
	String currentURL= driver.getCurrentUrl();
	assertTrue(currentURL.contains("user/login"));

}
}
