package trndrScreens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.awt.*;

public class R5L1Screen {
	
	

	
//	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div[1]/div[2]/span")
//	static	WebElement TopTrends;
//	
//	@FindBy(how=How.XPATH,using=".//*[@id='root']/div/div/div[2]/div/div[3]/div[2]/div[3]/div[1]")
//	static WebElement l1popup;
	
	@FindBy(how=How.XPATH,using=".//*[@id='cards']/div[10]/div/div")   
	static	 WebElement card1src1;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]") 
	static   WebElement card1tar1;
	
	
	@FindBy(how=How.XPATH,using=".//*[@id='cards']/div[9]/div/div")
	static	 WebElement card2src2;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[1]")
	static   WebElement card2tar2;
	
	
	@FindBy(how=How.XPATH,using=".//*[@id='cards']/div[8]/div/div")
	static	 WebElement card3src3;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[2]")//moved down
	static   WebElement card3tar3;

	
	@FindBy(how=How.XPATH,using=".//*[@id='cards']/div[7]/div/div")
	static	 WebElement card4src4;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[2]")
	static   WebElement card4tar4;

	
	
	@FindBy(how=How.XPATH,using=".//*[@id='cards']/div[6]/div/div")
	static	 WebElement card5src5;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[2]")
	static   WebElement card5tar5;
	
	
	
	@FindBy(how=How.XPATH,using=".//*[@id='cards']/div[5]/div/div")
	static	 WebElement card6src6;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[2]")
	static   WebElement card6tar6;
	
	
	@FindBy(how=How.XPATH,using=".//*[@id='cards']/div[4]/div/div")      
	static	 WebElement card7src7;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[2]")
	static   WebElement card7tar7;
	
	
	@FindBy(how=How.XPATH,using=".//*[@id='cards']/div[3]/div/div")
	static	 WebElement card8src8;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[2]")
	static   WebElement card8tar8;
		
	
	@FindBy(how=How.XPATH,using="//*[@id=\"cards\"]/div[2]/div/div/div[2]")   
	static	 WebElement card9src9;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[2]")
	static   WebElement card9tar9;
	
	
	
	@FindBy(how=How.XPATH,using="//*[@id=\"cards\"]/div/div/div/div[2]")   
	static	 WebElement card10src10;	
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[2]/div/div[2]")
	static   WebElement card10tar10;
	
	                            
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[4]/div[2]/div[4]/div[1]")
	static WebElement choicepopupbtn;
	                                 
	@FindBy(how=How.XPATH,using="//*[@id=\"root\"]/div/div/div[3]/div/div/span") 
	static WebElement GotoNxtroundbtn;
	
	
	
	public static WebElement card(WebDriver driver) throws InterruptedException{
              
		Actions act=new Actions(driver);

		WebDriverWait wait = new WebDriverWait(driver, 25);
		/*
		WebElement toptrndbtn = wait.until(ExpectedConditions.elementToBeClickable(TopTrends));
		toptrndbtn.click();
		
		
		//WebDriverWait wait = new WebDriverWait(driver, 25);
		WebElement l1popupbtn = wait.until(ExpectedConditions.elementToBeClickable(l1popup));
		l1popupbtn.click();
		*/
		//return l1popupbtn;
	
		//TopTrends.click();
		Thread.sleep(500);
	    act.clickAndHold(card1src1).build().perform();		
		act.moveToElement(card1tar1).build().perform();			
		act.release(card1tar1).build().perform();		
		System.out.println("released src-tar card");

		Thread.sleep(500);
		
		act.clickAndHold(card2src2).build().perform();		
		act.moveToElement(card2tar2).build().perform();		
		act.release(card2tar2).build().perform();		
		System.out.println("released 2src-tar card");

		Thread.sleep(500);
		
		act.clickAndHold(card3src3).build().perform();		
		act.moveToElement(card3tar3).build().perform();		
		act.release(card3tar3).build().perform();		
		System.out.println("released 2src-tar card");

		Thread.sleep(500);
		act.clickAndHold(card4src4).build().perform();		
		act.moveToElement(card4tar4).build().perform();		
		act.release(card4tar4).build().perform();		
		System.out.println("released 2src-tar card");

		Thread.sleep(500);
		act.clickAndHold(card5src5).build().perform();		
		act.moveToElement(card5tar5).build().perform();		
		act.release(card5tar5).build().perform();		
		System.out.println("released 2src-tar card");
		
		Thread.sleep(500);
		
		
		act.clickAndHold(card6src6).build().perform();		
		act.moveToElement(card6tar6).build().perform();		
		act.release(card6tar6).build().perform();		
		System.out.println("released 2src-tar card");
		
		Thread.sleep(500);
		
		act.clickAndHold(card7src7).build().perform();		
		act.moveToElement(card7tar7).build().perform();		
		act.release(card7tar7).build().perform();		
		System.out.println("released 2src-tar card");
		Thread.sleep(500);
		
		
		act.clickAndHold(card8src8).build().perform();		
		act.moveToElement(card8tar8).build().perform();		
		act.release(card8tar8).build().perform();		
		System.out.println("released 2src-tar card");
		Thread.sleep(500);
		
		//WebElement card9src91 = wait.until(ExpectedConditions.elementToBeClickable(card9src9));
		System.out.println("begin 9src-tar card");
		act.clickAndHold(card9src9).build().perform();		
		act.moveToElement(card9tar9).build().perform();		
		act.release(card9tar9).build().perform();		
		System.out.println("released 9src-tar card");
		
		
		Thread.sleep(500);
		
		
		//WebElement card10src101 = wait.until(ExpectedConditions.elementToBeClickable(card10src10));
		System.out.println("begin 10src-tar card");
		
		act.clickAndHold(card10src10).build().perform();		
		act.moveToElement(card10tar10).build().perform();		
		act.release(card10tar10).build().perform();		
		
		
		 //act.dragAndDrop(card10src10,card10tar10).perform(); 
		System.out.println("released 10src-tar card");
		
		Thread.sleep(500);
		WebElement choicepopupbtn1 = wait.until(ExpectedConditions.elementToBeClickable(choicepopupbtn));	
		choicepopupbtn1.click();
		WebElement GotoNxtroundbtn1 = wait.until(ExpectedConditions.elementToBeClickable(GotoNxtroundbtn));
		
		
		return(GotoNxtroundbtn1);
		
	}	
	
	}	
		
	

	
	
	
