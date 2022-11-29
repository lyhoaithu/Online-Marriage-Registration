package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserRegistrationFormPage extends Page {

	public UserRegistrationFormPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

//Husband
	public By linkTextRegForm = By.xpath("//a[contains(@href,'marriage-reg-form')]");
	public By pickerDate = By.name("dom");
	public By txtHusbandName = By.name("nofhusband");
	public By txtHPhoto = By.name("husimage");
	public By txtHReligion = By.name("hreligion");
	public By pickerHDOB = By.id("hdob");
	public By dropdownHStatus = By.name("hsbmarriage");
	public By txtHAddress = By.name("haddress");
	public By txtHZipcode = By.name("hzipcode");
	public By txtHState = By.name("hstate");
	public By txtHAdaharNo = By.name("hadharno");

//Wife
	public By txtWifeName = By.name("nofwife");
	public By txtWPhoto = By.name("wifeimage");
	public By txtWReligion = By.name("wreligion");
	public By pickerWDOB = By.id("wdob");
	public By dropdownWStatus = By.name("wsbmarriage");
	public By txtWAddress = By.name("waddress");
	public By txtWZipcode = By.name("wzipcode");
	public By txtWState = By.name("wstate");
	public By txtWAdaharNo = By.name("wadharno");

//Witness
	public By txtWitness1Name = By.name("witnessnamef");
	public By txtAddress1 = By.name("waddressfirst");
	public By txtWitness2Name = By.name("witnessnames");
	public By txtAddress2 = By.name("waddresssec");
	public By txtWitness3Name = By.name("witnessnamet");
	public By txtAddress3 = By.name("waddressthird");

	//Date
	public By date=By.xpath("//table/tbody/tr/td/a");
	public By btnAdd = By.id("submit");
	public By[] elements =  {pickerDate, txtHusbandName, txtHPhoto, txtHReligion, pickerHDOB,
			txtHAddress, txtHZipcode, txtHState, txtHAdaharNo, txtWifeName, txtWPhoto, txtWReligion, pickerWDOB,
			 txtWAddress, txtWZipcode, txtWState, txtWAdaharNo, txtWitness1Name, txtAddress1,
			txtWitness2Name, txtAddress2, txtWitness3Name, txtAddress3 };
}
