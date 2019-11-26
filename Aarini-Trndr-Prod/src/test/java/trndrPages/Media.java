package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Media {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[8]/div[1]/div[1]/div")
	static	WebElement MediaCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement Mediapopupbtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
	static WebElement caticon;


public static WebElement MediaCat(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement Mediacatlink = wait.until(ExpectedConditions.elementToBeClickable(MediaCategory));
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Mediacatlink);

Mediacatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(Mediapopupbtn));
return popupbtn1;

}


}
