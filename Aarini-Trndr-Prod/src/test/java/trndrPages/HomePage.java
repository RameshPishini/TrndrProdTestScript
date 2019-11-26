package trndrPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import regressionProdTCs.regressionTCS;

public class HomePage {
	
	
	@FindBy(how=How.XPATH,using="//div[1]/div/div/div[1]/div[3]/div/div[2]/span")
	static	WebElement createTrndrbtn;
	
	@FindBy(how=How.XPATH,using=".//*[@id='Email-2']")
	static	WebElement emailtxt;

	@FindBy(how=How.XPATH,using=".//*[@id='Password']")
	static	WebElement passwordtxt;

	@FindBy(how=How.XPATH,using=".//*[@id='root']/div/div/div[1]/div[2]/div[2]/a/span")
	static	WebElement signupbtn;

	
	
	
	public static WebElement createbtntrndr(WebDriver driver){


		WebDriverWait wait = new WebDriverWait(driver, 25);
		WebElement loginbtnele = wait.until(ExpectedConditions.elementToBeClickable(createTrndrbtn));
		loginbtnele.click();
		
		WebElement emiltxt = wait.until(ExpectedConditions.elementToBeClickable(emailtxt));
		emiltxt.sendKeys(regressionTCS.gmusr);
		
		WebElement paswrdtxt = wait.until(ExpectedConditions.elementToBeClickable(passwordtxt));
		paswrdtxt.sendKeys("12345");
		
		WebElement signup1 = wait.until(ExpectedConditions.elementToBeClickable(signupbtn));
	
		
		return signup1;
		 
		 
}
	
	
	
	
	
	
	
	
	
	
	
}
