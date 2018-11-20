package ecshoputils;

import static org.testng.Assert.fail;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;





public class MyUtils1 {
	public static WebDriver driver;
	
public static WebDriver openBrowser(String browser) {
		
		try {
			if (browser.equalsIgnoreCase("firefox")) {
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference(
						"security.mixed_content.block_active_content", false);
				profile.setPreference(
						"security.mixed_content.block_display_content", true);
				profile.setPreference(
						"browser.download.manager.showWhenStarting", false);
				profile
						.setPreference(
								"browser.helperApps.neverAsk.saveToDisk",
								"application/octet-stream,"
										+ "application/vnd.ms-excel,text/csv,application/zip");
				profile.setPreference("browser.download.folderList", "2");
		

				// ����Firefox
				System.setProperty("webdriver.firefox.bin",
						"F:\\Program Files\\Fire\\firefox.exe");
				driver = new FirefoxDriver(profile);

			} else if (browser.equalsIgnoreCase("ie")) {
				//IE���ļ�
				final String IE_DRIVER="E:\\selenium_Driver\\IEDriverServer.exe";
				DesiredCapabilities ieCapabilities = DesiredCapabilities
						.internetExplorer();
				ieCapabilities.setCapability("nativeEvents", true);
				ieCapabilities.setCapability("unexpectedAlertBehaviour",
						"accept");
				ieCapabilities.setCapability("ignoreProtectedModeSettings",
						true);
				ieCapabilities.setCapability("disable-popup-blocking", true);
				ieCapabilities
						.setCapability(
								InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
								true);
				ieCapabilities.setCapability("requireWindowFocus", false);
				ieCapabilities.setCapability("enablePersistentHover", false);

				System.setProperty("webdriver.id.driver", IE_DRIVER);
				driver = new InternetExplorerDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays
						.asList("--incognito"));
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--test-type");
				options.addArguments("enable-automation");
				options.addArguments("--disable-infobars");

				capabilities.setCapability(ChromeOptions.CAPABILITY, options);

				System.setProperty("webdriver.chrome.driver",
						"E:\\selenium_Driver\\chromedriver2.48.2.exe");
				driver = new ChromeDriver(capabilities);
			} else {
				System.out.println("Invalid browser bype: " + browser);
			}
			System.out.println("Browser is opened,Type is: " + browser);
			// Maximize
			driver.manage().window().maximize();
			// set Wait Time
			driver.manage().timeouts().implicitlyWait(30,
					TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(30,
					TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(30,
					TimeUnit.SECONDS);
			return driver;
		} catch (Exception e) {
			System.out.println("Unable to open browser.");
			System.out.println(e.getMessage());
			fail(e.getMessage());
			return null;
		}
	}

	
	/**
	 * �ж�ҳ��Ԫ���Ƿ����
	 * @param by ����Ԫ�ض���
	 * @return
	 *  1��true���Ԫ�ش���
	 *  2��false��?����
	 */
	public static boolean isElementPresent(By by){
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
//			e.printStackTrace();
			return false;
			
		}
		
	}
	/**
	 * �ж���ʾ���Ƿ����
	 * @return
	 */
	public static boolean isAlertPresent(){
		  try {
			  driver.switchTo().alert();
			  return true;
		} catch (NoAlertPresentException e) {
//			  e.printStackTrace();
			  return false;
		}
		  
	 }
	
}
