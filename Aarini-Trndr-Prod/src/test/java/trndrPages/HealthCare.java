package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HealthCare {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[7]/div[1]/div[1]/div")
	static	WebElement HCCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement HCpopupbtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
	static WebElement caticon;


public static WebElement HealthCarecat(WebDriver driver){
//caticon.click();
WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement hccatlink = wait.until(ExpectedConditions.elementToBeClickable(HCCategory));

((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",hccatlink);

hccatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(HCpopupbtn));
return popupbtn1;

}

}
