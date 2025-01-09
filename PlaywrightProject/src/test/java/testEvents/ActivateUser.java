package testEvents;

import com.microsoft.playwright.Browser;

import base.BaseClass;
import pojo.TestDataRetriever;

public class ActivateUser extends BaseClass
{
	
	TestDataRetriever info1 = new TestDataRetriever(".\\src\\test\\resources\\data\\testData.json");
	
	
	public String USERID = info1.getEmail();
	public String SETPASSWORD = info1.getPasswordNew();
	//public String CONFIRMPASSWORD = info1.getPasswordReEnter();
	public String ActivationLInk = Mailinator.getACTIVATELINK();
	
	public void signIn() throws InterruptedException
	{
		context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 657));
		page = context.newPage();
		page.navigate(ActivationLInk);
		click("ActivateContinueButton_id");
		fillText("NewPassword_id", SETPASSWORD);
	//	fillText("ConfirmPassword_id", CONFIRMPASSWORD);
		click("ContinueButton_id");
		waitForVisibilityOf(or.getProperty("Login_xpath"));
		 fillText("username_xpath", USERID);
		 fillText("password_xpath", SETPASSWORD);
		 click("Login_xpath");
		 click("TermsndConditions_xpath");
			click("TermsContinueButton_xpath");
			/*
			 * click("Apppalette_xpath");
			 * 
			 * click("FSCTraceInApppalette_xpath");
			 */
			Thread.sleep(5000);
		
	}
}
