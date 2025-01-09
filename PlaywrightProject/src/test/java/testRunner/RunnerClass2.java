package testRunner;

import org.testng.annotations.*;

import base.BaseClass;
import testEvents.*;

public class RunnerClass2 extends BaseClass {

//	@Test(priority = 0)
//	public void createNewUser() {
//
//		try {
//			CrmEvents crm = new CrmEvents();
//
//			crm.loginWebsite();
//			crm.contactManagement();
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//
//	}
//
//	@Test(priority = 1)
//	public void mailinatorEvents() {
//		try {
//			Mailinator mail = new Mailinator();
//			mail.mailinator();
//			mail.activateUser();
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//	}
//
//	@Test(priority = 2)
//	public void ActivateUser() {
//		try {
//			ActivateUser activate = new ActivateUser();
//			activate.signIn();
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//	}

	@Test(priority = 0)
	public void singleCertificateUser() {
		
		// SIngleUserevents single = new SIngleUserevents();
		// single.createUser();
		// single.editUser();
		// single.deleteUser();	
		
	}
	
	@Test(priority = 1)
	public void groupCertificateUserManageContact() {
		
		// SIngleUserevents single = new SIngleUserevents();
		// single.createUser();
		// single.editUser();
		// single.deleteUser();	
		
	}
	
	@Test(priority = 2)
	public void groupCertificateUserManageSiteContact() {
		
		// SIngleUserevents single = new SIngleUserevents();
		// single.createUser();
		// single.editUser();
		// single.deleteUser();	
		
	}
	@Test(priority = 3)
	public void multiCertificateUserManageContact() {
		
		// SIngleUserevents single = new SIngleUserevents();
		// single.createUser();
		// single.editUser();
		// single.deleteUser();	
		
	}
	@Test(priority = 4)
	public void multiCertificateUserManageSiteContact() {
		
		// SIngleUserevents single = new SIngleUserevents();
		// single.createUser();
		// single.editUser();
		// single.deleteUser();	
		
	}
	
}
