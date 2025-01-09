package testEvents;

import base.BaseClass;
import pojo.TestDataRetriever;

public class Mailinator extends BaseClass
{
	TestDataRetriever info1 = new TestDataRetriever(".\\src\\test\\resources\\data\\testData.json");
	
	
	private String EMAIL = info1.getEmail();
	
	public static String ACTIVATELINK; 
	
	public void mailinator() throws InterruptedException
	{
		
		page = context.newPage();
		page.navigate(config.getProperty("MailinatorUrl"));
		log.info("new tab is created !!!");
		
		fillText("SearchEmailid_xpath", EMAIL);
		click("ClickGo_xpath");
		

		click("SelectInviteMail_xpath");
	}
	public void activateUser()
	{
		
		ACTIVATELINK = page.frameLocator("#html_msg_body").getByText("Activate my account").getAttribute("href");
		
	}
	public static String getACTIVATELINK() 
	{
		return ACTIVATELINK;
	}
	
	
}
