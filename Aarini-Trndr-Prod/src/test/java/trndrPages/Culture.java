package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Culture {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[4]/div[1]/div[1]/div")
	static	WebElement CultureCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement Culturepopupbtn;
	
//	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
//	static WebElement caticon;


public static WebElement Culturecat(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement Culturecatlink = wait.until(ExpectedConditions.elementToBeClickable(CultureCategory));
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Culturecatlink);
Culturecatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(Culturepopupbtn));
return popupbtn1;

}

}
