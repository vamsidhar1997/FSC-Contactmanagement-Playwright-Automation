package testRunner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pojo.TestDataRetriever;
import testEvents.FscConnectEvents;

public class RunnerClass extends BaseClass {

	private TestDataRetriever data = new TestDataRetriever(".\\src\\test\\resources\\data\\testData.json");
	private String singleUserName = data.getSingleUsername();
	private String singlePassword = data.getSinglePassword();
	private String singleContactEmail = data.getSingleContactEmail();
	private String groupUsername = data.getGroupUsername();
	private String groupPassword = data.getGroupPassword();
	private String groupContactEmail = data.getGroupContactEmail();
	private String singleLastName = data.getSingleLastName();
	private String groupLastName = data.getgroupLastName();
	private String groupSiteUserName = data.groupSiteUser();

	@Test(priority = 0)
	public void singleUser() {

		try {
			FscConnectEvents fsc = new FscConnectEvents();
			fsc.loginWebsite(singleUserName, singlePassword);
			fsc.createContact(singleLastName, singleContactEmail);
			fsc.editContact();
			fsc.deleteContact();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 1)
	public void groupUser() {

		try {
			FscConnectEvents fsc = new FscConnectEvents();
			fsc.loginWebsite(groupUsername, groupPassword);
			fsc.createContact(groupLastName, groupContactEmail);
			fsc.editContact();
			fsc.deleteContact();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test(priority = 2)
	public void groupSiteUser(String username, String password, String lastName,String mail)
	{
		FscConnectEvents site = new FscConnectEvents();
		site.loginWebsite(username, password);
		site.manageSiteContact(lastName, mail);
		
	}

	/*
	 * @DataProvider public Object[][] singleUserData() {
	 * 
	 * return new Object[][] {
	 * 
	 * { singleUserName, singlePassword, singleLastName, singleContactEmail } }; }
	 * 
	 * @DataProvider public Object[][] groupUserData() {
	 * 
	 * return new Object[][] {
	 * 
	 * { groupUsername, groupPassword,groupLastName, groupContactEmail } }; }
	 * 
	 * @DataProvider public Object[][] GroupSiteUserData() {
	 * 
	 * return new Object[][] {
	 * 
	 * { groupSiteUserName, groupPassword,groupLastName, groupContactEmail } }; }
	 */

}
