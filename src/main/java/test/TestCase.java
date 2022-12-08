package test;

import java.io.File;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import common.TestBase;
import page.UserLogInPage;

public class TestCase extends TestBase{
@BeforeMethod
public void openBrowser() {
	String browser="edge";
	openBroswer(browser);
}

@AfterMethod
public void closeBrowser() {
	driver.quit();
}
public File data= new File("TestData/OMR_TestData.xlsx");
public String dataFilePath=data.getAbsolutePath();


}

