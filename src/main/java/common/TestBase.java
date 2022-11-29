package common;

import static org.testng.Assert.assertEquals;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
public WebDriver driver;
public void openBroswer(String browser) {
	if(browser.equalsIgnoreCase("edge")) {
		System.setProperty("webdriver.edge.driver", "D:\\AutomationTest\\02Projects\\OnlineMarriageRegistration\\driver\\msedgedriver.exe");
	driver=new EdgeDriver();
	}
	else if (browser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "D:\\AutomationTest\\02Projects\\OnlineMarriageRegistration\\driver\\chromedriver.exe");
	driver= new ChromeDriver();
	}
driver.manage().window().maximize();
}

public void clickOnElement(By locator) {
	
	//Wait for the element to appear
	WebDriverWait wait= new WebDriverWait(driver, 5);
	wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	
	//Click on element
	driver.findElement(locator).click();
}

public void hoverMouse(By locator) {	
	//Hover thuộc actions
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(locator)).perform();
}

public void sendKeys(By locator, String value) {
	WebElement e = driver.findElement(locator);
	e.sendKeys(value);

}

public void sendKeysWithAttributeAvailable(By locator, String value) {
	WebElement e = driver.findElement(locator);
	if (!e.getAttribute("value").isEmpty()) {
		e.clear();
	}
	e.sendKeys(value);

}
public String getHtml5ValidationMessage(By locator) {
	JavascriptExecutor jsExecutor= (JavascriptExecutor)driver;
	String message= jsExecutor.executeScript("return arguments[0].validationMessage;", driver.findElement(locator)).toString();
	return message;
}

public String getLocalMessage() {
	String message=driver.switchTo().alert().getText();
return message;
}

public void acceptAlertMessage() {
	driver.switchTo().alert().accept();
}

public void rejectAlertMessage() {
	driver.switchTo().alert().dismiss();
}

public boolean checkElementPresence(By locator) {
	boolean checkVisibility=false;
	int count=0;
	while (!driver.findElement(locator).isDisplayed()|| count <10) {
		WebDriverWait w = new WebDriverWait(driver, 3);
	      // presenceOfElementLocated condition
	      w.until(ExpectedConditions.presenceOfElementLocated(locator));	
	      count+=1;
	      checkVisibility= false;
	}
	checkVisibility= true;
	return checkVisibility;
}

public boolean checkHtml5ValidationMessagePresence(By locator) {
		JavascriptExecutor jsExcecutor= (JavascriptExecutor) driver;
	boolean presence=(boolean) jsExcecutor.executeScript("return arguments[0].validationMessage", driver.findElement(locator).isDisplayed());
		return presence;
	}

public void selectDropDownBox(By locator, int index) {
	Select dropdown= new Select(driver.findElement(locator));
	dropdown.selectByIndex(index);;

}

public  String getValueFromDatabase(String query, int columnIndex) throws ClassNotFoundException, SQLException {
	DataBaseConnection con= new DataBaseConnection();
	ResultSet rs= con.verifyData(query);
	
	//Tạo Arrays List để chứa kq lấy từ DB
	ArrayList<String> result= new ArrayList<String>();
	
	//ĐỌc kq từ db r ghi vào array
	while(rs.next()){
		 result.add(rs.getString(columnIndex));
		}
	
	 Object[] resultArr= new String[result.size()];
	 resultArr=result.toArray();
	 String resultString=Arrays.deepToString(resultArr);
return resultString;
}

public void getHtml5ValidationFromASeriesOfFields(By[]elements, String[]data, String message) {
	for (int j = 0; j < elements.length; j++) {
		sendKeys(elements[j], data[j]);
	}
	String actualMessage = null;
	for (int i=0; i<elements.length; i++) {
		if(data[i].equals("")) {
			actualMessage= getHtml5ValidationMessage(elements[i]);
		}
	}
	assertEquals(actualMessage, message);
}

public void waitForElementPresence(By locator) {
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
}

public void webWait(By locator) {
	WebDriverWait wait= new WebDriverWait(driver, 5);

}
}

