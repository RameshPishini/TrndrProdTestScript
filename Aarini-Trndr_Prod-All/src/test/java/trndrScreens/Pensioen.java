package trndrScreens;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Pensioen {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[11]/div[1]/div[1]/div")
	static	WebElement PensionCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement pensionpopupbtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
	static WebElement caticon;


public static WebElement Pensioncat(WebDriver driver){
//caticon.click();
WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement pensioncatlink = wait.until(ExpectedConditions.elementToBeClickable(PensionCategory));
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",pensioncatlink);

pensioncatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(pensionpopupbtn));
return popupbtn1;

}

}
