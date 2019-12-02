package regressionTestCases;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import trndrScreens.AccountScreen;
import trndrScreens.Categoryicon;
import trndrScreens.Communicatie;
import trndrScreens.Culture;
import trndrScreens.Education;
import trndrScreens.Finance;
import trndrScreens.HealthCare;
import trndrScreens.HomePage;
import trndrScreens.L1Screen;
import trndrScreens.L2Screen;
import trndrScreens.L3Screen;
import trndrScreens.L4Screen;
import trndrScreens.LandingPage;
import trndrScreens.MedalScreen;
import trndrScreens.Media;
import trndrScreens.Mobility;
import trndrScreens.Music;
import trndrScreens.Next10Cards;
import trndrScreens.OtherCategoryL4Screen;
import trndrScreens.Pensioen;
import trndrScreens.ProfileScreen;
import trndrScreens.R2R3R4R5L1Screen;
import trndrScreens.R2R3R4R5L4Screen;
import trndrScreens.R4R5L2Screen;
import trndrScreens.R4R5L3Screen;
import trndrScreens.R4R5L4Screen;
import trndrScreens.R5L1Screen;
import trndrScreens.Screenreport;
import trndrScreens.Social;
import trndrScreens.SocialDomein;
import trndrScreens.Technology;



public class TrndrRegTCs {
	ExtentReports extent;
    ExtentTest logger;
	public static WebDriver driver = null;
	

public static String gmusr =  new Random().nextInt(1000) + "gmusr@sharklasers.com";
	
	//public static String endusr1 =  new Random().nextInt(1000) + "endusr@yopmail.com";
	
	//public static String endusr2 =  new Random().nextInt(1000) + "endusr@yopmail.com";
	
	
	
@BeforeTest
public void init() {
	 extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/STMExtentReport.html", true);
	 //extent.addSystemInfo("Environment","Environment Name")
	 extent
	                .addSystemInfo("Host Name", "Trndr app")
	                .addSystemInfo("Environment", "TRNDR PROD Testing")
	                .addSystemInfo("User Name", "UMA");
	                
	                extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	 }




public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
	 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	 TakesScreenshot ts = (TakesScreenshot) driver;
	 File source = ts.getScreenshotAs(OutputType.FILE);
	                
	 String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/"+screenshotName+dateName+".png";
	 File finalDestination = new File(destination);
	 FileUtils.copyFile(source, finalDestination);
	 return destination;
	 }



@Test(priority=0)
	public void browserinit() {
	
	    logger = extent.startTest("PROD-URLVerification");
//		System.setProperty("webdriver.chrome.driver","C:\\Program Files\\Eclipse\\driver2\\chromedriver.exe");
//
//		driver=new ChromeDriver();
	    
	   // System.setProperty("webdriver.gecko.driver","C:\\Program Files\\Eclipse\\driver2\\geckodriver.exe");
	    System.setProperty("webdriver.gecko.driver","/home/ubuntu/plugins/geckodriver");
	     driver=new FirefoxDriver();
	     
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
		driver.get("https://app.trndr.co");
		  driver.manage().window().maximize();
		String text1="https://app.trndr.co/";
		
		Assert.assertEquals(driver.getCurrentUrl(), text1,"Trndr Test URl not displayed");
		 logger.log(LogStatus.PASS, "Test Case (Trndr Test URL) Status is passed");
		 		
		
	}


@AfterMethod
public void getResult(ITestResult result) throws Exception{
if(result.getStatus() == ITestResult.FAILURE){
logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());

 String screenshotPath = Screenreport.getScreenhot(driver, result.getName());

logger.log(LogStatus.FAIL, logger.addScreenCapture(screenshotPath));
}else if(result.getStatus() == ITestResult.SKIP){
logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
}

extent.endTest(logger);
}

@AfterTest
public void endReport(){

               extent.flush();
               
               extent.close();
   }

 

@Test(priority=1)
public void createbtntrndrtc() throws InterruptedException {
	logger = extent.startTest("HomePage");
	HomePage signup=PageFactory.initElements(driver, HomePage.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	HomePage.createbtntrndr(driver).click();
	String name="Choose a trend category";
	String ChooseCategory=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span")).getText();
	
	 Assert.assertEquals(ChooseCategory,name ,"Home page");
	 logger.log(LogStatus.PASS, "Test Case (createbtntrndrtc) Status is passed");
	
	
}


@Test(priority=2)
public void nllangtc() throws InterruptedException {
	logger = extent.startTest("NLCategoryPage");
	LandingPage nllangsel=PageFactory.initElements(driver, LandingPage.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	LandingPage.selection(driver).click();
    String NLEnabled=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[3]/div[2]")).getText();

	String NLenabledExp="NL";
	Assert.assertEquals(NLEnabled,NLenabledExp ,"NL CategoryPage");
	logger.log(LogStatus.PASS, "Test Case (NLCategory) Status is passed");
}


@Test(priority=3)
public void socialcattc() throws InterruptedException {
	logger = extent.startTest("SocialPOPup");
	Social socialcat=PageFactory.initElements(driver, Social.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Social.socialcat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
}


@Test(priority=4)
public void R1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");
	
	
}



@Test(priority=5)
public void R1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");
    
		
}


@Test(priority=6)
public void R1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();
}


@Test(priority=7)
public void R1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	L4Screen l4screen=PageFactory.initElements(driver, L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
	L4Screen.level4(driver).click();
	
	
}



@Test(priority=8)
public void profileScreenQ() throws InterruptedException {
	logger = extent.startTest("ProfileScreenVerfication");
	ProfileScreen profile=PageFactory.initElements(driver, ProfileScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	
	ProfileScreen.questions(driver).click();
	String actprofile=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
	String expecprofile="Impactanalyse";
	Assert.assertEquals(actprofile, expecprofile);
	
	logger.log(LogStatus.PASS, "Test Case (Profile) Status is passed");
	
}


@Test(priority=9)
public void R2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");
	
	
	
	
}


@Test(priority=10)
public void R2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");
}




@Test(priority=11)
public void R2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");
    
		
}


@Test(priority=12)
public void R2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();
}


@Test(priority=13)
public void R2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	
}


@Test(priority=14)
public void R3screen() throws InterruptedException {
	
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");
	
	
	
	
}






@Test(priority=15)
public void R3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");
}




@Test(priority=16)
public void R3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");
    
		
}


@Test(priority=17)
public void R3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();
}


@Test(priority=18)
public void R3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	
}


@Test(priority=19)
public void R4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");
	
	
}







@Test(priority=20)
public void R4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");
}




@Test(priority=21)
public void R4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");
    
		
}


@Test(priority=22)
public void R4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();
}


@Test(priority=23)
public void R4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	
	
}


@Test(priority=24)
public void R5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");
	
	
}






@Test(priority=25)
public void R5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");
}




@Test(priority=26)
public void R5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");
    
		
}


@Test(priority=27)
public void R5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();
}


@Test(priority=28)
public void R5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	
	
}


@Test(priority=29)
public void Medalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");
	
	
}



@Test(priority=30)
public void Accountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[12]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	System.out.println("value in medal"+Medalimg);
	
	 if(Medalimg==true)
 {	    
	 System.out.println("Medal in profile page is enabled");
	 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
	 }
	 else
	 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
	 
	
}



@Test(priority=31)
public void CategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Categoryicon.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");
	
}




@Test(priority=32)
public void Techcattc() throws InterruptedException {
	logger = extent.startTest("Technology Screen POPup");
	Technology technologycat=PageFactory.initElements(driver, Technology.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Technology.techcat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Technology popup) Status is passed");
	
}


@Test(priority=33)
public void TechR1L1() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");


}



@Test(priority=34)
public void TecR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");


    
		
}



@Test(priority=35)
public void TechcattcR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=36)
public void TechcattcR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}



@Test(priority=37)
public void TechcattcR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");
	

	
	
	
	
}


@Test(priority=38)
public void TechcattcR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=40)
public void TechcattcR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=41)
public void TechcattcR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=42)
public void TechcattcR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	

	
	
}


@Test(priority=43)
public void TechcattcR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
	
}






@Test(priority=44)
public void TechcattcR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=45)
public void TechcattcR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=46)
public void TechcattcR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=47)
public void TechcattcR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=48)
public void TechcattcR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
}







@Test(priority=49)
public void TechcattcR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=50)
public void TechcattcR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=51)
public void TechcattcR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=52)
public void TechcattcR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=53)
public void TechcattcR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");
	

	
	
}






@Test(priority=54)
public void TechcattcR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=55)
public void TechcattcR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=56)
public void TechcattcR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=57)
public void TechcattcR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	

	
	
}


@Test(priority=58)
public void TechcattcMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

	
	
}



@Test(priority=59)
public void TechcattcAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[4]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 
	 

	
}



@Test(priority=60)
public void TechcattcCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");

}


@Test(priority=61)
public void Communicatiecattc() throws InterruptedException {
	logger = extent.startTest("Technology Screen POPup");
	Communicatie Communicatiecat=PageFactory.initElements(driver, Communicatie.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Communicatie.communicatiecat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
}


@Test(priority=62)
public void CommunicatieR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");

	
	
}



@Test(priority=63)
public void CommunicatieR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=64)
public void CommunicatieR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=65)
public void CommunicatieR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}






@Test(priority=66)
public void CommunicatieR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");
	
	

	
	
	
	
}


@Test(priority=67)
public void CommunicatieR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=68)
public void CommunicatieR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=69)
public void CommunicatieR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=70)
public void CommunicatieR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}


@Test(priority=71)
public void CommunicatieR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
	
}






@Test(priority=72)
public void CommunicatieR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=73)
public void CommunicatieR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=74)
public void CommunicatieR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=75)
public void CommunicatieR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
}


@Test(priority=76)
public void CommunicatieR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}







@Test(priority=77)
public void CommunicatieR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");
}




@Test(priority=78)
public void CommunicatieR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=79)
public void CommunicatieR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=80)
public void CommunicatieR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=81)
public void CommunicatieR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}






@Test(priority=82)
public void CommunicatieR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=83)
public void CommunicatieR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=84)
public void CommunicatieR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=85)
public void CommunicatieR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=86)
public void CommunicatieMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

	
	
}



@Test(priority=87)
public void CommunicatieAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[7]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 
	 
}



@Test(priority=88)
public void CommunicatieCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");

	
}

@Test(priority=89)
public void Culturetc() throws InterruptedException {
	logger = extent.startTest("Culture Screen POPup");
	Culture Culturecat1=PageFactory.initElements(driver, Culture.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Culture.Culturecat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Culture popup) Status is passed");
	
}


@Test(priority=90)
public void CultureR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");

	
	
}



@Test(priority=91)
public void CultureR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=92)
public void CultureR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=93)
public void CultureR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}






@Test(priority=94)
public void CultureR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");
	
	

	
	
	
	
}


@Test(priority=95)
public void CultureR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=96)
public void CultureR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=97)
public void CultureR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=98)
public void CultureR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}


@Test(priority=99)
public void CultureR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
	
}






@Test(priority=100)
public void CultureR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=101)
public void CultureR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=102)
public void CultureR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=103)
public void CultureR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=104)
public void CultureR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");
	

	
}







@Test(priority=105)
public void CultureR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=106)
public void CultureR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=107)
public void CultureR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=108)
public void CultureR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=109)
public void CultureR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");
	

	
}






@Test(priority=110)
public void CultureR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=111)
public void CultureR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=112)
public void CultureR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=113)
public void CultureR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=114)
public void CultureMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");
	
	
	
}



@Test(priority=115)
public void CultureAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[5]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 
	
}



@Test(priority=116)
public void CultureCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");
	

}



@Test(priority=117)
public void Educationtc() throws InterruptedException {
	logger = extent.startTest("Technology Screen POPup");
	Education Educationcat1=PageFactory.initElements(driver, Education.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Education.Educationcat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
}


@Test(priority=118)
public void EducationR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");

	
}



@Test(priority=119)
public void EducationR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=120)
public void EducationR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=121)
public void EducationR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}






@Test(priority=122)
public void EducationR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");
	
	

	
	
	
	
}


@Test(priority=123)
public void EducationR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=124)
public void EducationR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=125)
public void EducationR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=126)
public void EducationR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
}


@Test(priority=127)
public void EducationR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
	
}






@Test(priority=128)
public void EducationR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=129)
public void EducationR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");


    
		
}


@Test(priority=130)
public void EducationR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=131)
public void EducationR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=132)
public void EducationR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}







@Test(priority=133)
public void EducationR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=134)
public void EducationR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=135)
public void EducationR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=136)
public void EducationR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
}


@Test(priority=137)
public void EducationR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");
	

	
	
}






@Test(priority=138)
public void EducationR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=139)
public void EducationR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=140)
public void EducationR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=141)
public void EducationR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=142)
public void EducationMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");
	

	
}



@Test(priority=143)
public void EducationAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[9]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 

	 
	
}



@Test(priority=144)
public void EducationCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");


	
}



@Test(priority=145)
public void Financetc() throws InterruptedException {
	logger = extent.startTest("Finance Screen POPup");
	Finance Financecat1=PageFactory.initElements(driver, Finance.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Finance.Financecat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Finance popup) Status is passed");
	
}


@Test(priority=146)
public void FinanceR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");


	
	
}



@Test(priority=147)
public void FinanceR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");
    


    
		
}


@Test(priority=148)
public void FinanceR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=149)
public void FinanceR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}






@Test(priority=150)
public void FinanceR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");
	

	
	
	
	
}


@Test(priority=151)
public void FinanceR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=152)
public void FinanceR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=153)
public void FinanceR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=154)
public void FinanceR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	

	
	
}


@Test(priority=155)
public void FinanceR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
	
}






@Test(priority=156)
public void FinanceR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=157)
public void FinanceR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=158)
public void FinanceR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=159)
public void FinanceR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=160)
public void FinanceR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");
	

	
	
}







@Test(priority=161)
public void FinanceR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=162)
public void FinanceR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=163)
public void FinanceR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=164)
public void FinanceR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=165)
public void FinanceR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}






@Test(priority=166)
public void FinanceR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=167)
public void FinanceR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=168)
public void FinanceR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=169)
public void FinanceR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=170)
public void FinanceMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

	
	
}



@Test(priority=171)
public void FinanceAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[1]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 
	 

	
}



@Test(priority=172)
public void FinanceCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");

}



@Test(priority=173)
public void HealthCaretc() throws InterruptedException {
	logger = extent.startTest("HealthCare Screen POPup");
	HealthCare HealthCarecat1=PageFactory.initElements(driver, HealthCare.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	HealthCare.HealthCarecat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (HealthCare popup) Status is passed");
	
}


@Test(priority=174)
public void HealthCareR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");

	
	
}



@Test(priority=175)
public void HealthCareR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=176)
public void HealthCareR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=177)
public void HealthCareR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}






@Test(priority=178)
public void HealthCareR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");
	

	
	
	
}


@Test(priority=179)
public void HealthCareR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=180)
public void HealthCareR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=181)
public void HealthCareR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=182)
public void HealthCareR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}


@Test(priority=183)
public void HealthCareR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");
	

	
}






@Test(priority=184)
public void HealthCareR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=185)
public void HealthCareR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=186)
public void HealthCareR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=187)
public void HealthCareR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=188)
public void HealthCareR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}







@Test(priority=189)
public void HealthCareR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=190)
public void HealthCareR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=191)
public void HealthCareR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=192)
public void HealthCareR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	

	
	
}


@Test(priority=193)
public void HealthCareR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}






@Test(priority=194)
public void HealthCareR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=195)
public void HealthCareR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=196)
public void HealthCareR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=197)
public void HealthCareR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	

	
	
}


@Test(priority=198)
public void HealthCareMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

	
	
}



@Test(priority=199)
public void HealthCareAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[10]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 
	 
	
}



@Test(priority=200)
public void HealthCareCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");

	
}



@Test(priority=201)
public void Mediatc() throws InterruptedException {
	logger = extent.startTest("Media Screen POPup");
	Media Mediacat1=PageFactory.initElements(driver, Media.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Media.MediaCat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Media popup) Status is passed");
	
}


@Test(priority=202)
public void MediaR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");

	
	
}



@Test(priority=203)
public void MediaR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=204)
public void MediaR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=205)
public void MediaR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}






@Test(priority=206)
public void MediaR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");

	
	
	
	
}


@Test(priority=207)
public void MediaR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=208)
public void MediaR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=209)
public void MediaR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=210)
public void MediaR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	

	
	
}


@Test(priority=211)
public void MediaR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
	
}






@Test(priority=212)
public void MediaR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=213)
public void MediaR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=214)
public void MediaR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=215)
public void MediaR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=216)
public void MediaR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}







@Test(priority=217)
public void MediaR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=218)
public void MediaR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=219)
public void MediaR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=220)
public void MediaR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	

	
	
}


@Test(priority=221)
public void MediaR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}






@Test(priority=222)
public void MediaR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=223)
public void MediaR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=224)
public void MediaR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=225)
public void MediaR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	

	
	
}


@Test(priority=226)
public void MediaMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

	
	
}



@Test(priority=227)
public void MediaAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[11]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 
	
}



@Test(priority=228)
public void MediaCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");

}


@Test(priority=228)
public void Mobilitettc() throws InterruptedException {
	logger = extent.startTest("Mobilitet Screen POPup");
	Mobility Mobilitetcat1=PageFactory.initElements(driver, Mobility.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Mobility.MobilityCat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Mobilitet popup) Status is passed");
	
}


@Test(priority=229)
public void MobilitetR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");

	
	
}



@Test(priority=230)
public void MobilitetR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=231)
public void MobilitetR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=232)
public void MobilitetR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}






@Test(priority=233)
public void MobilitetR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");

	
	
	
	
}


@Test(priority=234)
public void MobilitetR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=235)
public void MobilitetR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=236)
public void MobilitetR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=237)
public void MobilitetR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	

	
	
}


@Test(priority=238)
public void MobilitetR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
}



@Test(priority=239)
public void MobilitetR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=240)
public void MobilitetR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=241)
public void MobilitetR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=242)
public void MobilitetR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=243)
public void MobilitetR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}







@Test(priority=244)
public void MobilitetR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=245)
public void MobilitetR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=246)
public void MobilitetR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=247)
public void MobilitetR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	

	
	
}


@Test(priority=248)
public void MobilitetR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}






@Test(priority=249)
public void MobilitetR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=250)
public void MobilitetR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=251)
public void MobilitetR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=252)
public void MobilitetR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=253)
public void MobilitetMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

	
	
}



@Test(priority=254)
public void MobilitetAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[8]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 

	 
	
}



@Test(priority=255)
public void MobilitetCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");
	

	
}




@Test(priority=256)
public void Musictc() throws InterruptedException {
	logger = extent.startTest("Music Screen POPup");
	Music Musiccat1=PageFactory.initElements(driver, Music.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Music.Musiccat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Music popup) Status is passed");
	
}


@Test(priority=257)
public void MusicR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");

	
}



@Test(priority=258)
public void MusicR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

		
}


@Test(priority=259)
public void MusicR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=260)
public void MusicR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}






@Test(priority=261)
public void MusicR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");

	
	
	
}


@Test(priority=262)
public void MusicR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=263)
public void MusicR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=264)
public void MusicR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=265)
public void MusicR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	

	
	
}


@Test(priority=266)
public void MusicR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
	
}






@Test(priority=267)
public void MusicR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=268)
public void MusicR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=269)
public void MusicR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=270)
public void MusicR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=271)
public void MusicR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}







@Test(priority=272)
public void MusicR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=273)
public void MusicR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=274)
public void MusicR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=275)
public void MusicR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	

	
}


@Test(priority=276)
public void MusicR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}






@Test(priority=277)
public void MusicR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=278)
public void MusicR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=279)
public void MusicR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=280)
public void MusicR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	

	
	
}


@Test(priority=281)
public void MusicMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

	
	
}



@Test(priority=282)
public void MusicAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[12]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 

	 
	
}



@Test(priority=283)
public void MusicCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");

	
}


@Test(priority=284)
public void Pensiontc() throws InterruptedException {
	logger = extent.startTest("Pension Screen POPup");
	Pensioen Pensioncat1=PageFactory.initElements(driver, Pensioen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Pensioen.Pensioncat(driver).click();
	
	logger.log(LogStatus.PASS, "Test Case (Pension popup) Status is passed");
	
}


@Test(priority=285)
public void PensionR1L1screen() throws InterruptedException {
	logger = extent.startTest("R1L1Verfication");
	L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L1Screen.card(driver).click();	
	
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");
	
}



@Test(priority=286)
public void PensionR1L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

		
}


@Test(priority=287)
public void PensionR1L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=288)
public void PensionR1L4screen() {
	logger = extent.startTest("R1L4Verfication");
	OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    OtherCategoryL4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


	
	
}






@Test(priority=289)
public void PensionR2screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");

	
	
	
}


@Test(priority=290)
public void PensionR2L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=291)
public void PensionR2L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=292)
public void PensionR2L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=293)
public void PensionR2L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	

	
	
}


@Test(priority=294)
public void PensionR3screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

	
	
}






@Test(priority=295)
public void PensionR3L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=296)
public void PensionR3L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=297)
public void PensionR3L3screen() {
	logger = extent.startTest("R1L3Verfication");
	L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    L3Screen.level3(driver).click();

}


@Test(priority=298)
public void PensionR3L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R2R3R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

	
	
}


@Test(priority=299)
public void PensionR4screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}







@Test(priority=300)
public void PensionR4L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R2R3R4R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=301)
public void PensionR4L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=302)
public void PensionR4L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=303)
public void PensionR4L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	

	
}


@Test(priority=304)
public void PensionR5screen() throws InterruptedException {
	logger = extent.startTest("Impactanalysis");
	Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	nxt10screen.NextRound(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
    String expl1text="Heeft deze trend impact op uw organisatie?";
    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

	
	
}






@Test(priority=305)
public void PensionR5L1screen() throws InterruptedException {
	logger = extent.startTest("R2L1Verfication");
	R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R5L1Screen.card(driver).click();			
	String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
    String expl2text="Level 2";
    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

}




@Test(priority=306)
public void PensionR5L2screen() throws InterruptedException {
	logger = extent.startTest("R1L2Verfication");
	R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	R4R5L2Screen.level2(driver).click();
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

    
		
}


@Test(priority=307)
public void PensionR5L3screen() {
	logger = extent.startTest("R1L3Verfication");
	R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	//R1L1Screen.r1screen(driver).click();
	
	
	String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
    String expl3text="Level 3";
    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
    R4R5L3Screen.level3(driver).click();

}


@Test(priority=308)
public void PensionR5L4screen() {
	logger = extent.startTest("R1L4Verfication");
	R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
    String expl4text="Is deze trend een kans of bedreiging?";
	
	
//	String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//    String expl4text="Impactanalyse";R4R5L4Screen
    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
	
    R4R5L4Screen.level4(driver);
	//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
	//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
	
	

	
	
}


@Test(priority=309)
public void PensionMedalscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Impactanalysis");
	MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	MedalScreen.Medal(driver).click();
	String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
    String expl1text="Impactanalyse";
    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
	
	//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
	logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

	
	
}



@Test(priority=310)
public void PensionAccountscreen() throws InterruptedException {
	logger = extent.startTest("Medal-Profile page screen");
	AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	accscreen.Account(driver);
	
	
	WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[3]/div[1]/img"));
	
			
	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
	WebDriverWait wait = new WebDriverWait(driver, 25);
	boolean Medalimg=catimg.isEnabled();
	
	 if(Medalimg==true)
	 {	    
		 System.out.println("Medal in profile page is enabled");
		 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
		 }
		 else
		 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
		 

	 
	
}



@Test(priority=311)
public void PensionCategoryiconNL() throws InterruptedException {
	logger = extent.startTest("Category screen");
	Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	catscreen.categoryall(driver).click();
	WebDriverWait wait = new WebDriverWait(driver, 25);
	WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
	if(title.isDisplayed()) 
	{System.out.println("Landing page displayed");
	logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
	
	}
	else
	logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");

	
}


@Test(priority=312)
	public void SocialDomeintc() throws InterruptedException {
		logger = extent.startTest("SocialDomein Screen POPup");
		SocialDomein SocialDomeincat1=PageFactory.initElements(driver, SocialDomein.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		SocialDomein.SDcat(driver).click();
		
		logger.log(LogStatus.PASS, "Test Case (SocialDomein popup) Status is passed");
		
	}


	@Test(priority=313)
	public void SocialDomeinR1L1screen() throws InterruptedException {
		logger = extent.startTest("R1L1Verfication");
		L1Screen r1screen=PageFactory.initElements(driver, L1Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		L1Screen.card(driver).click();	
		
		String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
	    String expl2text="Level 2";
	    Assert.assertEquals(actl2text,expl2text ,"R1L1 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (L1R1) Status is passed");

		
	}



	@Test(priority=314)
	public void SocialDomeinR1L2screen() throws InterruptedException {
		logger = extent.startTest("R1L2Verfication");
		L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		L2Screen.level2(driver).click();
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

			
	}


	@Test(priority=315)
	public void SocialDomeinR1L3screen() {
		logger = extent.startTest("R1L3Verfication");
		L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
	    L3Screen.level3(driver).click();

	}


	@Test(priority=316)
	public void SocialDomeinR1L4screen() {
		logger = extent.startTest("R1L4Verfication");
		OtherCategoryL4Screen l4screen=PageFactory.initElements(driver, OtherCategoryL4Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
	    String expl4text="Is deze trend een kans of bedreiging?";
	    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
	    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
		
	    OtherCategoryL4Screen.level4(driver);
		//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
		//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");


		
		
	}






	@Test(priority=317)
	public void SocialDomeinR2screen() throws InterruptedException {
		logger = extent.startTest("Impactanalysis");
		Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nxt10screen.NextRound(driver).click();
		String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
	    String expl1text="Heeft deze trend impact op uw organisatie?";
	    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
		
		//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
		logger.log(LogStatus.PASS, "Test Case (Next 20 Cards L1) Status is passed");

		
		
		
	}


	@Test(priority=318)
	public void SocialDomeinR2L1screen() throws InterruptedException {
		logger = extent.startTest("R2L1Verfication");
		R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		R2R3R4R5L1Screen.card(driver).click();			
		String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
	    String expl2text="Level 2";
	    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

	}




	@Test(priority=319)
	public void SocialDomeinR2L2screen() throws InterruptedException {
		logger = extent.startTest("R1L2Verfication");
		L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		L2Screen.level2(driver).click();
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

	    
			
	}


	@Test(priority=320)
	public void SocialDomeinR2L3screen() {
		logger = extent.startTest("R1L3Verfication");
		L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
	    L3Screen.level3(driver).click();

	}


	@Test(priority=321)
	public void SocialDomeinR2L4screen() {
		logger = extent.startTest("R1L4Verfication");
		R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
	    String expl4text="Is deze trend een kans of bedreiging?";
		
		
//		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//	    String expl4text="Impactanalyse";
	    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
	    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
		
	    R2R3R4R5L4Screen.level4(driver);
		//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
		//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

		

		
		
	}


	@Test(priority=322)
	public void SocialDomeinR3screen() throws InterruptedException {
		logger = extent.startTest("Impactanalysis");
		Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nxt10screen.NextRound(driver).click();
		String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
	    String expl1text="Heeft deze trend impact op uw organisatie?";
	    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
		
		//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
		logger.log(LogStatus.PASS, "Test Case (Next 30 Cards L1) Status is passed");

		
		
	}






	@Test(priority=323)
	public void SocialDomeinR3L1screen() throws InterruptedException {
		logger = extent.startTest("R2L1Verfication");
		R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		R2R3R4R5L1Screen.card(driver).click();			
		String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
	    String expl2text="Level 2";
	    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

	}




	@Test(priority=324)
	public void SocialDomeinR3L2screen() throws InterruptedException {
		logger = extent.startTest("R1L2Verfication");
		L2Screen r2screen=PageFactory.initElements(driver, L2Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		L2Screen.level2(driver).click();
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

	    
			
	}


	@Test(priority=325)
	public void SocialDomeinR3L3screen() {
		logger = extent.startTest("R1L3Verfication");
		L3Screen l3screen=PageFactory.initElements(driver, L3Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
	    L3Screen.level3(driver).click();

	}


	@Test(priority=326)
	public void SocialDomeinR3L4screen() {
		logger = extent.startTest("R1L4Verfication");
		R2R3R4R5L4Screen l4screen=PageFactory.initElements(driver, R2R3R4R5L4Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
	    String expl4text="Is deze trend een kans of bedreiging?";
		
		
//		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//	    String expl4text="Impactanalyse";
	    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
	    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
		
	    R2R3R4R5L4Screen.level4(driver);
		//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
		//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");

		
		
	}


	@Test(priority=327)
	public void SocialDomeinR4screen() throws InterruptedException {
		logger = extent.startTest("Impactanalysis");
		Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nxt10screen.NextRound(driver).click();
		String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
	    String expl1text="Heeft deze trend impact op uw organisatie?";
	    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
		
		//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
		logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

		
		
	}







	@Test(priority=328)
	public void SocialDomeinR4L1screen() throws InterruptedException {
		logger = extent.startTest("R2L1Verfication");
		R2R3R4R5L1Screen r1screen=PageFactory.initElements(driver, R2R3R4R5L1Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		R2R3R4R5L1Screen.card(driver).click();			
		String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
	    String expl2text="Level 2";
	    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

	}




	@Test(priority=329)
	public void SocialDomeinR4L2screen() throws InterruptedException {
		logger = extent.startTest("R1L2Verfication");
		R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		R4R5L2Screen.level2(driver).click();
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

	    
			
	}


	@Test(priority=330)
	public void SocialDomeinR4L3screen() {
		logger = extent.startTest("R1L3Verfication");
		R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
	    R4R5L3Screen.level3(driver).click();

	}


	@Test(priority=331)
	public void SocialDomeinR4L4screen() {
		logger = extent.startTest("R1L4Verfication");
		R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
	    String expl4text="Is deze trend een kans of bedreiging?";
		
		
//		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//	    String expl4text="Impactanalyse";R4R5L4Screen
	    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
	    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
		
	    R4R5L4Screen.level4(driver);
		//Assert.assertTrue(driver.getPageSource().contains("Which industry do you work in?"));
		//logger.log(LogStatus.PASS, "Test Case (Social popup) Status is passed");
		

		
	}


	@Test(priority=332)
	public void SocialDomeinR5screen() throws InterruptedException {
		logger = extent.startTest("Impactanalysis");
		Next10Cards nxt10screen=PageFactory.initElements(driver, Next10Cards.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		nxt10screen.NextRound(driver).click();
		String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]")).getText();
	    String expl1text="Heeft deze trend impact op uw organisatie?";
	    Assert.assertEquals(actl1text,expl1text ,"Next20 Round Fail");
		
		//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
		logger.log(LogStatus.PASS, "Test Case (Next 40 Cards L1) Status is passed");

		
		
	}






	@Test(priority=333)
	public void SocialDomeinR5L1screen() throws InterruptedException {
		logger = extent.startTest("R2L1Verfication");
		R5L1Screen r1screen=PageFactory.initElements(driver, R5L1Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		R5L1Screen.card(driver).click();			
		String actl2text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]")).getText();
	    String expl2text="Level 2";
	    Assert.assertEquals(actl2text,expl2text ,"R2L1 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R2L1) Status is passed");

	}




	@Test(priority=334)
	public void SocialDomeinR5L2screen() throws InterruptedException {
		logger = extent.startTest("R1L2Verfication");
		R4R5L2Screen r2screen=PageFactory.initElements(driver, R4R5L2Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		R4R5L2Screen.level2(driver).click();
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L2 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L2) Status is passed");

	    
			
	}


	@Test(priority=335)
	public void SocialDomeinR5L3screen() {
		logger = extent.startTest("R1L3Verfication");
		R4R5L3Screen l3screen=PageFactory.initElements(driver, R4R5L3Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//R1L1Screen.r1screen(driver).click();
		
		
		String actl3text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[5]/div[2]/div[1]")).getText();
	    String expl3text="Level 3";
	    Assert.assertEquals(actl3text,expl3text ,"R1L3 Screen Failed");
	    logger.log(LogStatus.PASS, "Test Case (R1L3) Status is passed");
	    R4R5L3Screen.level3(driver).click();

	}


	@Test(priority=336)
	public void SocialDomeinR5L4screen() {
		logger = extent.startTest("R1L4Verfication");
		R4R5L4Screen l4screen=PageFactory.initElements(driver, R4R5L4Screen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/span/div[1]/div[2]")).getText();
	    String expl4text="Is deze trend een kans of bedreiging?";
		
		
//		String actl4text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
//	    String expl4text="Impactanalyse";R4R5L4Screen
	    Assert.assertEquals(actl4text,expl4text ,"Level4 screen Fail");
	    logger.log(LogStatus.PASS, "Test Case (R1L4) Status is passed");
		
	    R4R5L4Screen.level4(driver);
		
		
		

		
		
	}


	@Test(priority=337)
	public void SocialDomeinMedalscreen() throws InterruptedException {
		logger = extent.startTest("Medal-Impactanalysis");
		MedalScreen medalscreen=PageFactory.initElements(driver, MedalScreen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		MedalScreen.Medal(driver).click();
		String actl1text=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[1]/div[2]/div/div[1]")).getText();
	    String expl1text="Impactanalyse";
	    Assert.assertEquals(actl1text,expl1text ,"Medal Screen Fail");
		
		//Assert.assertTrue(driver.getPageSource().contains("De bovenstaande ranking is gegenereerd gebaseerd op"));
		logger.log(LogStatus.PASS, "Test Case (Medal Screen) Status is passed");

		
		
	}



	@Test(priority=338)
	public void SocialDomeinAccountscreen() throws InterruptedException {
		logger = extent.startTest("Medal-Profile page screen");
		AccountScreen accscreen=PageFactory.initElements(driver, AccountScreen.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		accscreen.Account(driver);
		
		
		WebElement catimg=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div[6]/div[1]/div[2]/div[1]/img"));
		
				
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",catimg);
		WebDriverWait wait = new WebDriverWait(driver, 25);
		boolean Medalimg=catimg.isEnabled();
		
		 if(Medalimg==true)
		 {	    
			 System.out.println("Medal in profile page is enabled");
			 logger.log(LogStatus.PASS, "Test Case (Medal has been unlocked -Profile page) Status is passed");
			 }
			 else
			 logger.log(LogStatus.FAIL, "Test Case (Medal is locked-Profile page) Status is Failed");
			 

		 
		
	}



	@Test(priority=339)
	public void SocialDomeinCategoryiconNL() throws InterruptedException {
		logger = extent.startTest("Category screen");
		Categoryicon catscreen=PageFactory.initElements(driver, Categoryicon.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		catscreen.categoryall(driver).click();
		WebDriverWait wait = new WebDriverWait(driver, 25);
		WebElement title=driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div[1]/span"));
		if(title.isDisplayed()) 
		{System.out.println("Landing page displayed");
		logger.log(LogStatus.PASS, "Test Case (NL Category Screen) Status is passed");
		
		}
		else
		logger.log(LogStatus.FAIL, "Test Case (NL Category Screen) Status is passed");

		
	}


































}
