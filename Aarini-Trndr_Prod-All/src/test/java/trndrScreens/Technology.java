package trndrScreens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Technology {

	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[2]/div[2]/div[1]/div[1]/div/span")
	static	WebElement TechnologyCategory;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div[2]/div[3]/div")
	static WebElement tecpopupbtn;
	
//	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
//	static WebElement caticon;
	
	public static WebElement techcat(WebDriver driver){

		//caticon.click();
		WebDriverWait wait = new WebDriverWait(driver, 25);
		WebElement teccatlink = wait.until(ExpectedConditions.elementToBeClickable(TechnologyCategory));
		teccatlink.click();
		
		WebElement popupbtn1=wait.until(ExpectedConditions.elementToBeClickable(tecpopupbtn));
		return popupbtn1;
	
	
}
}