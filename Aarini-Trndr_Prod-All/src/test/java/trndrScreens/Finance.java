package trndrScreens;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Finance {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[6]/div[1]/div[1]/div")
	static	WebElement FinanceCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement Finopupbtn;
	
//	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
//	static WebElement caticon;


public static WebElement Financecat(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement fincatlink = wait.until(ExpectedConditions.elementToBeClickable(FinanceCategory));
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",fincatlink);


fincatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(Finopupbtn));
return popupbtn1;

}

}


