package trndrScreens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class R2R3R4R5L4Screen {
	@FindBy(how=How.XPATH,using="//*[@id=\"cards\"]/div/div/div/div[2]")
	static	WebElement L4card1src1;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/span/div[1]")
	static	WebElement L4card1tar1;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div[4]/div")
	static	WebElement Next10Cards;
	
//	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div/div/div/div[2]/div/div")
//	static WebElement ansquestbtn;

	
	public static WebElement level4(WebDriver driver) {
		
		Actions act=new Actions(driver);
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//WebElement l3popupbtn1=wait.until(ExpectedConditions.);
		
		

		 act.clickAndHold(L4card1src1).build().perform();		
			act.moveToElement(L4card1tar1).build().perform();			
			act.release(L4card1tar1).build().perform();		
			
			
			WebElement Next10Cardsbtn1 = wait.until(ExpectedConditions.elementToBeClickable(Next10Cards));
			//ansquestbtn.click();
					
		return(Next10Cards);
		
	}

}
