package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Communicatie {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[3]/div[2]/div")
	static	WebElement CommunicatieCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement commnicatiepopupbtn;
	
	//@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
	//static WebElement caticon;


public static WebElement communicatiecat(WebDriver driver){
//caticon.click();
WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement commcatlink = wait.until(ExpectedConditions.elementToBeClickable(CommunicatieCategory));
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",commcatlink);
commcatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(commnicatiepopupbtn));
return popupbtn1;

}
}