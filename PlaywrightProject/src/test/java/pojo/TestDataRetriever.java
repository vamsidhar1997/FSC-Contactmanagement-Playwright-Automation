package pojo;

import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TestDataRetriever {

	public String path;
	public String singleUsername;
	public String singlePassword;
	public String groupPassword;
	public String groupContactEmail;
	public String group_Username;
	public String singleContactEmail;
	public String singleLastName;
	public String groupLastName;
	public String MailinatorUrl;
	public String PasswordNew;
	public String PasswordReEnter;
	public String GroupSiteUser;

	public TestDataRetriever(String path) {
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

	public String getSingleUsername() {
		return singleUsername = readJson("singleUser", "username");
	}

	public String getSinglePassword() {
		return singlePassword = readJson("singleUser", "password");
	}

	public String getGroupPassword() {
		return groupPassword = readJson("groupUser", "password");
	}
	
	public String getGroupContactEmail() {
		return groupContactEmail = readJson("groupUser", "Email");
	}

	public String getGroupUsername() {
		return group_Username = readJson("groupUser", "username");
	}

	public String getSingleContactEmail() {
		return singleContactEmail = readJson("singleUser", "Email");
	}

	public String getSingleLastName() {
		return singleLastName = readJson("singleUser", "lastName");
	}

	public String getgroupLastName() {
		return groupLastName = readJson("groupUser", "lastName");
	}

	
	public String getMailinatorUrl() {
		return MailinatorUrl = readJson("LoginInfo", "MailinatorUrl");
	}

	public String getPasswordNew() {
		return PasswordNew = readJson("LoginInfo", "PasswordNew");
	}

	public String getEmail() {
		return PasswordReEnter = readJson("LoginInfo", "PasswordReEnter");
	}
	public String groupSiteUser()
	{
		return GroupSiteUser = readJson("groupSiteUser", "username");
	}

}
