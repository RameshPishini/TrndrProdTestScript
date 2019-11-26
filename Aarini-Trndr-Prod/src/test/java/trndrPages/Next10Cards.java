package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Next10Cards {
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div[4]/div")      
	static	WebElement Next10Cards;
	
	
	                             
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[3]/div[2]/div[3]/div[1]")
	static	WebElement R2L1OKbtn;
	
	
	
	public static WebElement NextRound(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		Actions act=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();",Next10Cards );
		WebElement nextroundbtn = wait.until(ExpectedConditions.elementToBeClickable(Next10Cards));
		nextroundbtn.click();
		
		return(R2L1OKbtn);

}
}