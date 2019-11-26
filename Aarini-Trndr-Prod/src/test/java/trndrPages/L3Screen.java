package trndrPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class L3Screen {
	
	
	//*[@id="root"]/div/div/div[1]/span/div[1]

	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[3]/div[1]")
	static	WebElement l3popupbtn;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"cards\"]/div/div/div/div[2]")
	static	WebElement L3card1src1;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")
	static	WebElement L3card1tar1;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[3]/div[1]")
	static WebElement l4popupbtn;

	
	public static WebElement level3(WebDriver driver) {
		
		Actions act=new Actions(driver);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
		//WebElement l3popupbtn1=wait.until(ExpectedConditions.);
		
		WebElement l3popupbtn1 = wait.until(ExpectedConditions.elementToBeClickable(l3popupbtn));
		l3popupbtn1.click();

		 act.clickAndHold(L3card1src1).build().perform();		
			act.moveToElement(L3card1tar1).build().perform();			
			act.release(L3card1tar1).build().perform();		
					
		return(l4popupbtn);
		
	
	
}

}
