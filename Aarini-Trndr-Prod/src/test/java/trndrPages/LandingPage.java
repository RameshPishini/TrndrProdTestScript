package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LandingPage {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div/div/div[3]/div[2]")
	static	WebElement NLlink;
	
	
	
	public static WebElement selection(WebDriver driver){


		WebDriverWait wait = new WebDriverWait(driver, 25);
          JavascriptExecutor js = (JavascriptExecutor) driver;
		
		js.executeScript("arguments[0].scrollIntoView();",NLlink );
		WebElement nlLanglink = wait.until(ExpectedConditions.elementToBeClickable(NLlink));
		return nlLanglink;
	}

}

