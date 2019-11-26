package trndrScreens;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountScreen {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[3]/a/img")
	static	WebElement Profileicon;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[12]/div[1]/img")
	static	WebElement SocialMedalImg;
	
	

	
	
	
	public static WebElement Account(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		Actions act=new Actions(driver);
		Profileicon.click();
		//WebDriverWait wait = new WebDriverWait(driver, 25);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		return(SocialMedalImg);
		
		
		
	}
	

}
