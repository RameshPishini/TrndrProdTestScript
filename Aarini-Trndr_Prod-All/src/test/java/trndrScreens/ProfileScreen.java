package trndrScreens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

public class ProfileScreen {
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div/div/div/div/div/form/div[1]/select")
	
	                            
	static	WebElement dropdown1;
			
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div/div/div/div/div/form/div[2]/input")
	static	WebElement Nxtbtn;
		
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div/div/div/div/div/form/div[1]/select")
	static WebElement dropdown2;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div/div/div/div/div/form/div[2]/input")
	static WebElement nextbtn2;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div/div/div/div/div/form/div[1]/input")
	static WebElement Companyname;
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]/div/div/div/div/div/form/div[2]/input")
	static WebElement nextbtn3;
	
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div/div[1]/a/img")
	static WebElement caticon;
	
	
	
	public static WebElement questions(WebDriver driver) throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 25);
		dropdown1.click();
		Select sdropdown=new Select(dropdown1);
	    sdropdown.selectByIndex(3);
	    Nxtbtn.click();
	    
	    
	    dropdown2.click();
		Select sdropdown2=new Select(dropdown2);
	    sdropdown2.selectByIndex(3);
	    nextbtn2.click();
	    
	    Companyname.sendKeys("Aarini BV");
	  //  nextbtn3.click();
	      
	      
	      
	    
		
     WebElement caticon1=wait.until(ExpectedConditions.elementToBeClickable(caticon));
		
		/*
		WebElement dropdown1=this.driver.findElement(By.xpath("//div[1]/div/div/div[1]/div/div/div/div/div/form/div[1]/select"));
		Select sdropdown=new Select(dropdown1);
		sdropdown.selectByIndex(3);
		
		driver.findElement(By.xpath(".//*[@id='root']/div/div/div[1]/div/div/div/div/div/form/div[2]/input")).click();
		
		
		
		WebElement dropdown2=driver.findElement(By.xpath(".//*[@id='root']/div/div/div[1]/div/div/div/div/div/form/div[1]/select"));
		Select sdropdown2=new Select(dropdown2);
		sdropdown2.selectByIndex(3);
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='root']/div/div/div[1]/div/div/div/div/div/form/div[2]/input")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//*[@id='root']/div/div/div[1]/div/div/div/div/div/form/div[2]/a")).click();
		Thread.sleep(2000);
		((JavascriptExecutor)driver).executeScript("scroll(0, 2000)");
		
		driver.findElement(By.xpath(".//*[@id='root']/div/div/div[1]/div[4]/div")).click();		 
		  
		 */
		
		return (nextbtn3);
		
		
		
	
		//return(Nxtbtn);
		
}
}
