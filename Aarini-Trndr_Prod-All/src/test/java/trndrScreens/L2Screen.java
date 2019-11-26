package trndrScreens;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class L2Screen {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[3]/div[1]/span")
	static	WebElement L2popupbtn;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"0\"]")
	static	WebElement L2card1src1;
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div[1]")
	static	WebElement L2card1tar1;

	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div[3]/a/span")
	static WebElement viewtoptrndbtn;
	
    @FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/span/div[2]/div")
    static WebElement nexttrendbtn;

  public static WebElement level2(WebDriver driver) throws InterruptedException {
	
	Actions act=new Actions(driver);
	JavascriptExecutor js = (JavascriptExecutor) driver;

	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement L2popupbtn1 = wait.until(ExpectedConditions.elementToBeClickable(L2popupbtn));
	L2popupbtn.click();
	
	        act.clickAndHold(L2card1src1).build().perform();		
			act.moveToElement(L2card1tar1).build().perform();			
			act.release(L2card1tar1).build().perform();		
			System.out.println("released l2 src-tar card");
			
			WebElement viewtoptrndbtn1 = wait.until(ExpectedConditions.elementToBeClickable(viewtoptrndbtn));
			viewtoptrndbtn1.click();
			
			js.executeScript("arguments[0].scrollIntoView();",nexttrendbtn );
			Thread.sleep(1000);
			WebElement nexttrendbtn1 = wait.until(ExpectedConditions.elementToBeClickable(nexttrendbtn));
			return(nexttrendbtn1);
			
	
}
  
  
  


}