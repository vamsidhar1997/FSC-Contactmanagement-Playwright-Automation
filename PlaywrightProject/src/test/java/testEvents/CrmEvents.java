package testEvents;


import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import base.BaseClass;
import pojo.CrmEventsInfo;

public class CrmEvents extends BaseClass
{
	CrmEventsInfo info1 = new CrmEventsInfo(".\\src\\test\\resources\\data\\testData.json");
	private String USERNAME = info1.getusername();
	
//	private String USERNAME = config.getProperty("username");
	private String PASSWORD = info1.getPassword();
	private String FIRSTNAME = info1.getFirstName();
	private String LASTNAME = info1.getLastName();
	private String EMAIL = info1.getEmail();
	private String JOBTITLE = info1.getJobTitle();
	
	//Business logic
	public void loginWebsite()
	{
		
		 click("ConnectSigninButton_id");
		 waitForVisibilityOf(or.getProperty("Login_xpath"));
		 fillText("username_xpath", USERNAME);
		 fillText("password_xpath", PASSWORD);
		click("Login_xpath");
		
		click("Apppalette_xpath");
		click("ContactManagement_xpath");
	}
	public void contactManagement() throws InterruptedException
	{
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept all cookies")).click();
		
		click("CreateNewContact_xpath");
		
		fillText("ClickFirstName_xpath", FIRSTNAME);
		fillText("ClickLastName_xpath", LASTNAME);
		fillText("ClickEmail_xpath", EMAIL);
		fillText("JobTitle_xpath", JOBTITLE);
		
		click("FSCTraceRadioButton_xpath");
		click("ContinueButtonContactManagement");
	//	page.waitForSelector("a.add-new-contact create-contact button:enabled");
		click("FetchMessage_xpath");
		//capture the fetching data please wait locator and try the above one
		
	}


	
}

