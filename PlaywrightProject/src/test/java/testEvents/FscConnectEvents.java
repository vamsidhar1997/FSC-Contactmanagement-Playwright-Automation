package testEvents;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import base.BaseClass;

public class FscConnectEvents extends BaseClass {

	// Business logic
	public void loginWebsite(String username, String password) {

		click("ConnectSigninButton_id");
		waitForVisibilityOf(or.getProperty("Login_xpath"));
		fillText("username_xpath", username);
		fillText("password_xpath", password);
		click("Login_xpath");

		click("Apppalette_xpath");
		click("ContactManagement_xpath");
	}

	public void createContact(String lastName, String mail) {
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Accept all cookies")).click();

		click("CreateNewContact_xpath");

		fillText("ClickFirstName_xpath", "Bc");
		fillText("ClickLastName_xpath", lastName);
		fillText("ClickEmail_xpath", mail);
		fillText("JobTitle_xpath", "Member");

		click("FSCTraceRadioButton_xpath");
		click("ContinueButtonContactManagement");
		// page.waitForSelector("a.add-new-contact create-contact button:enabled");
		click("FetchMessage_xpath");
		// capture the fetching data please wait locator and try the above one

	}
	public void manageSiteContact(String lastName, String mail)
	{
		click("ManageSiteContactButton");
		fillText("ClickFirstName_xpath", "Bc");
		fillText("ClickLastName_xpath", lastName);
		fillText("ClickEmail_xpath", mail);
		fillText("JobTitle_xpath", "Member");
		selectOption("SelectOrganizationDropdown", "B - reg org 360");
		
	}

	public void editContact()
	{
		System.out.println("worked");
		
	}

	public void deleteContact() {

		
	}

}
