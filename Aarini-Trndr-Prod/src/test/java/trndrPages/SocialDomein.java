package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SocialDomein {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[12]/div[1]/div[1]/div")
	static	WebElement SDCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement SDpopupbtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
	static WebElement caticon;


public static WebElement SDcat(WebDriver driver){
//caticon.click();
WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement SDcatlink = wait.until(ExpectedConditions.elementToBeClickable(SDCategory));

((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",SDcatlink);
SDcatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(SDpopupbtn));
return popupbtn1;

}

}
