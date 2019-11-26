package trndrScreens;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Social {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[1]/div[2]/div") 
	                            
	static	WebElement socialCategory;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement popupbtn;
	
	
	
	
	
	
	
	
	public static WebElement socialcat(WebDriver driver){


		WebDriverWait wait = new WebDriverWait(driver, 25);
		WebElement socialcatlink = wait.until(ExpectedConditions.elementToBeClickable(socialCategory));
		socialcatlink.click();
		WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(popupbtn));
		return popupbtn1;
		
	}


}
