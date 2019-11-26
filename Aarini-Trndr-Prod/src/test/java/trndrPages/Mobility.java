package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Mobility {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[9]/div[1]/div[1]/div")
	static	WebElement MobilityCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement Mobilitypopupbtn;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
	static WebElement caticon;


public static WebElement MobilityCat(WebDriver driver){
//caticon.click();
WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement Mobilitycatlink = wait.until(ExpectedConditions.elementToBeClickable(MobilityCategory));

((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",Mobilitycatlink);
Mobilitycatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(Mobilitypopupbtn));
return popupbtn1;

}

}
