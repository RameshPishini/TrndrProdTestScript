package trndrScreens;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MedalScreen {
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div[4]/div/div")
	static	WebElement MedalBtn;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div/div/div[3]/a")
	static	WebElement Nextbtn;
	

	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/span/div[2]/div")
	static	WebElement NextTrendbtn;
	
	
	public static WebElement Medal(WebDriver driver) throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 25);
		Actions act=new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		js.executeScript("arguments[0].scrollIntoView();",MedalBtn );
		MedalBtn.click();
		WebElement Nextbtnbtn1 = wait.until(ExpectedConditions.elementToBeClickable(Nextbtn));
		Nextbtnbtn1.click();
		js.executeScript("arguments[0].scrollIntoView();",NextTrendbtn );
		WebElement NextTrendbtn1 = wait.until(ExpectedConditions.elementToBeClickable(NextTrendbtn));
		//NextTrendbtn1.click();
		
		
		
		
		
		
		
		
		
		return(NextTrendbtn1);

	
	}

}
