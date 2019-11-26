package trndrScreens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Categoryicon {
	
	
	@FindBy(how=How.XPATH,using="/html/body/div[1]/div/div/div[3]/div/div/div[1]/a/img")
	static	WebElement Categoryicon;
	
	
	
	public static WebElement categoryall(WebDriver driver) throws InterruptedException {
		
		return(Categoryicon);
	}

}
