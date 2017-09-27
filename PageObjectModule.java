package com.fool.pageobject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.internal.seleniumemulation.WaitForCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.fool.input.InputRead;

public class PageObjectModule extends InputRead {

	public static WebDriver driver;
	protected String browser = null;
	public String title;
	public InputRead read = new InputRead();
	public WebElement myDynamicElement;

	public static DesiredCapabilities caps;	
	public static String URL;

	public void connect(String name) throws MalformedURLException {

		try {			
			URL = "http://" + read.readData("Login", 1, 0) + ":" + read.readData("Login", 1, 2) + "@ondemand.saucelabs.com:80/wd/hub";

			browser = read.readData("Env", 1, 1);
			if (browser.equals("Firefox")) {
				caps = DesiredCapabilities.firefox();
			} else if (browser.equals("Chrome")) {
				caps = DesiredCapabilities.chrome();
			} else if (browser.equals("IE")) {
				caps = DesiredCapabilities.internetExplorer();
			}
			String OS = read.readData("Env", 1, 0);
			caps.setCapability("platform", OS);
			String ver = read.readData("Env", 1, 2);
			caps.setCapability("version", ver);
			caps.setCapability("name", name);
			driver = new RemoteWebDriver(new URL(URL), caps);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void browser() {
		try {
			browser = read.readData("Env", 1, 1);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (browser.equals("Firefox")) {

		    //ProfilesIni ini = new ProfilesIni();
		    //FirefoxProfile prof = ini.getProfile("default");
			//driver = new FirefoxDriver(prof);
			driver = new FirefoxDriver();
		} else if (browser.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "BrowserExe\\IEDriverServer.exe");
			driver= new InternetExplorerDriver();

		} else if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "BrowserExe\\chromedriver.exe");
			driver = new ChromeDriver();
		}
	}

	public void getUrl(String url) {
		driver.manage().window().maximize();
		driver.get(url);
	}

	public void enterText(String xpathKey, String text) throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath(xpathKey)).sendKeys(text);
	}

	public void clickButton(String xpathKey) throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath(xpathKey)).click();
	}

	public String returnText(String xpathKey) throws InterruptedException {
		Thread.sleep(2000);
		String value = driver.findElement(By.xpath(xpathKey)).getText();
		return value;
	}

	public void sync(String xpathKey) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathKey)));
	}

}