package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Music {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[10]/div[1]/div[1]/div")
	static	WebElement MusicCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement Musicpopupbtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
	static WebElement caticon;


public static WebElement Musiccat(WebDriver driver){
//caticon.click();
WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement musiccatlink = wait.until(ExpectedConditions.elementToBeClickable(MusicCategory));

((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",musiccatlink);
musiccatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(Musicpopupbtn));
return popupbtn1;

}

}
