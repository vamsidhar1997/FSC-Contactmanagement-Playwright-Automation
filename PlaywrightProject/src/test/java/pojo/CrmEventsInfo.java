package pojo;

import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CrmEventsInfo {

	public String path;

	
	public String username;
	public String password;
	public String FromDate;
	public String ToDate;
	public String FirstName;
	public String LastName;
	public String Email;
	public String JobTitle;
	public String MailinatorUrl;
	public String PasswordNew;
	public String PasswordReEnter;

	public CrmEventsInfo(String path) {
		this.path = path;
	}

	public String readJson(String node, String name) {

		String value = null;
		try {
			FileReader reader = new FileReader(path);
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			JsonObject questionnaireObject = jsonObject.getAsJsonObject(node);
			value = questionnaireObject.get(name).getAsString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public String getusername() {
		return username = readJson("LoginInfo", "username");
	}
	public String getPassword() {
		return password = readJson("LoginInfo", "password");
	}

	public String getFromDate() {
		return FromDate = readJson("LoginInfo", "FromDate");
	}

	public String getToDate() {
		return ToDate = readJson("LoginInfo", "ToDate");
	}
	public String getFirstName() {
		return FirstName = readJson("LoginInfo", "FirstName");
	}
	public String getLastName() {
		return LastName = readJson("LoginInfo", "LastName");
	}
	public String getEmail() {
		return Email = readJson("LoginInfo", "Email");
	}
	public String getJobTitle() {
		return JobTitle = readJson("LoginInfo", "JobTitle");
	}
	public String getMailinatorUrl() {
		return MailinatorUrl = readJson("LoginInfo", "MailinatorUrl");
	}
	public String getPasswordNew() {
		return PasswordNew = readJson("LoginInfo", "PasswordNew");
	}
	public String getPasswordReEnter() {
		return PasswordReEnter = readJson("LoginInfo", "PasswordReEnter");
	}

}
