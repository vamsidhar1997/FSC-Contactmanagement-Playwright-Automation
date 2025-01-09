package base;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import org.apache.log4j.*;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.gson.*;
import com.microsoft.playwright.*;
import com.microsoft.playwright.BrowserType.*;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class BaseClass {

	public static Logger log;
	public static FileInputStream fi;
	public static Properties config;
	public static Properties or;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static Playwright playwright;
	public static Browser browser;
	public static Page page;
	public static Page newTab;
	public static LaunchOptions lp;
	public static BrowserContext context;

	public BaseClass() {

		try {
			log = Logger.getLogger(BaseClass.class);

			// Log4j file is loading...!!!!!
			PropertyConfigurator.configure(".\\src\\test\\resources\\properties\\log4j.properties");

			// OR.properties file is loading...!!!!!
			fi = new FileInputStream(".\\src\\test\\resources\\properties\\OR.properties");
			or = new Properties();
			or.load(fi);

			// config.properties file is loading...!!!!!
			fi = new FileInputStream(".\\src\\test\\resources\\properties\\config.properties");
			config = new Properties();
			config.load(fi);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public void setup() {

		try {
			startPlayWright();
//			page.navigate(config.getProperty("connectUrl"));
//			page.waitForLoadState(LoadState.LOAD);
//			log.info("URL is Launched !!!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void createExtentReport() {

		try {
			htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//Reports//TestReport.html");
			htmlReporter.config().setDocumentTitle("Automation Test Report");
			htmlReporter.config().setReportName("Test Results");
			htmlReporter.config().setTheme(Theme.STANDARD);
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.setAppendExisting(false);
			extent = new ExtentReports();
			extent.attachReporter(htmlReporter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void setReport(ITestResult result) {

		try {

			context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 657));
			page = context.newPage();
			page.navigate(config.getProperty("connectUrl"));
			page.waitForLoadState(LoadState.LOAD);
			log.info("URL is Launched !!!");
			test = extent.createTest(" @ TestCase : " + result.getMethod().getMethodName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod
	public void addResult(ITestResult result) {

		String methodName;
		String logText;
		Markup markup;

		try {
			switch (result.getStatus()) {
			case ITestResult.SUCCESS:
				methodName = result.getMethod().getMethodName();
				logText = "Test Case :  " + methodName + " is Passed";
				markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
				test.log(Status.PASS, markup);
				break;

			case ITestResult.FAILURE:
				methodName = result.getMethod().getMethodName();
				logText = "Test Case :  " + methodName + " is Failed";
				markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
				test.log(Status.FAIL, markup);
				break;

			case ITestResult.SKIP:
				methodName = result.getMethod().getMethodName();
				logText = "Test Case :  " + methodName + " is Skipped";
				markup = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
				test.log(Status.SKIP, markup);
				break;

			default:
				break;

			}
			page.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterTest
	public void tearDown() {

		try {
			extent.flush();
	        context.close();
	        browser.close();
			playwright.close();
			log.info("Test Execution is completed !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Page startPlayWright() {

		try {
			playwright = Playwright.create();
			lp = new LaunchOptions();
			lp.setHeadless(false);

			switch (config.getProperty("browser")) {

			case "chrome":
				lp.setChannel("chrome");
				browser = playwright.chromium().launch(lp);
				break;

			case "firefox":
				lp.setChannel("firefox");
				browser = playwright.firefox().launch(lp);
				break;

			case "edge":
				lp.setChannel("msedge");
				browser = playwright.chromium().launch(lp);
				break;

			default:
				lp.setChannel("chrome");
				browser = playwright.chromium().launch(lp);
				break;
			}

//			context = browser.newContext(new Browser.NewContextOptions().setViewportSize(1366, 657));
//			context.tracing()
//					.start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
//			page = context.newPage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	public void waitForElement(int num) {

		try {
			page.waitForTimeout(1000 * num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void click(String locator) {

		try {
			page.locator(or.getProperty(locator)).click();
			log.info("'" + locator + "' is clicked");
		} catch (Exception e) {
			log.error("Failed to click on '" + locator + "'");
			e.printStackTrace();
		}
	}

	public void fillText(String locator, String value) {

		try {
			page.locator(or.getProperty(locator)).fill(value);
			log.info("'" + value + "' input is passed to the '" + locator + "'");
		} catch (Exception e) {
			log.error("'" + value + "' input is not passed to the '" + locator + "'");
			e.printStackTrace();
		}
	}

	public void doubleClick(String locator) {

		try {
			page.locator(or.getProperty(locator)).dblclick();
			log.info("'" + locator + "' is double clicked");
		} catch (Exception e) {
			log.error("Failed to double click on '" + locator + "'");
			e.printStackTrace();
		}
	}

	public void hover(String locator) {

		page.locator(or.getProperty(locator)).hover();
		;
	}

	public void waitForVisibilityOf(String locator) {

		page.waitForSelector(locator, new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
	}

	public void clickOption(String option) {

		page.getByText(option).click();
	}

	public void keyPress(String key) {
		page.keyboard().press(key);
	}

	public void keyType(String key) {
		page.keyboard().type(key);
	}

	public Locator locator(String locator) {
		Locator l = page.locator(or.getProperty(locator));
		return l;
	}

	public void switchToTab(int num) {

		page.context().pages().get(num).bringToFront();
	}

	public void selectOption(String locator, String value) {

		try {
			page.locator(or.getProperty(locator)).selectOption(value);
			log.info("'" + value + "' input is selected from the '" + locator + "'");
		} catch (Exception e) {
			log.error("'" + value + "' input is selected from the '" + locator + "'");
			e.printStackTrace();
		}
	}

	public String getDataFromJson(String filePath, String question) {

		String answer = null;
		try (FileReader fileReader = new FileReader(filePath)) {
			JsonElement jsonElement = JsonParser.parseReader(fileReader);
			JsonObject json = jsonElement.getAsJsonObject();
			JsonObject policyForAssociation = json.getAsJsonObject("policyForAssociation");

			if (policyForAssociation.has(question)) {
				answer = policyForAssociation.get(question).getAsString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return answer;
	}

	public String[] getQuestionPreferencesFromJson(String path) {

		String[] keywords = null;
		try {
			FileReader reader = new FileReader(path);
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
			keywords = gson.fromJson(jsonObject.get("preferences"), String[].class);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keywords;
	}

}
