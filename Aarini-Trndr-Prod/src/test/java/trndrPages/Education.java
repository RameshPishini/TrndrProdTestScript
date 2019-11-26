package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Education {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[5]/div[1]/div[1]/div")
	static	WebElement EducationCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement Edupopupbtn;
	
//	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
//	static WebElement caticon;


public static WebElement Educationcat(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, 25);
WebElement educatlink = wait.until(ExpectedConditions.elementToBeClickable(EducationCategory));
((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",educatlink);
educatlink.click();

WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(Edupopupbtn));
return popupbtn1;

}

}
