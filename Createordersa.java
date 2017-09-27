package com.fool.uk.shareadvisor;

import java.util.Random;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.fool.pageobject.PageObjectModule;

public class Createorder extends PageObjectModule {

	String str1;
	String actUName;
	String expUName;

	@Test(priority = 1)
	public void HWordercreate() throws Throwable {
		System.setProperty("webdriver.firefox.marionette", "C:\\selenium-java-3.4.0\\geckodriver.exe");

		try {
			String name = read.readData("URL", 2, 2);
			//super.connect(name);
			 super.browser();
			String teur = read.readData("URL", 2, 1);
			super.getUrl(teur);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertFalse(true);
		}
		super.clickButton(read.getObject("test_Checkbox"));
		Thread.sleep(2000);
		super.clickButton(read.getObject("Btn_Continue"));
		Thread.sleep(38000);
		String LS = read.getObject("Ribbon");
		super.sync(LS);
		try {
			Random rg = new Random();
			for (int idx = 1; idx <= 1; ++idx) {
				int randomInt = rg.nextInt(100000);
				str1 = "test" + randomInt;

				super.enterText(read.getObject("Input_UserName"), str1);
			}
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertFalse(true);
		}
		try {
			Thread.sleep(3000);
			String xpath = read.readData("Login", 2, 1);
			super.enterText(read.getObject("Input_Password"), xpath);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertFalse(true);
		}
		try {
			String xpath = read.readData("Login", 2, 1);
			super.enterText(read.getObject("Input_ConfirmPassword"), xpath);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertFalse(true);
		}
		Thread.sleep(3000);
		super.clickButton(read.getObject("Btn_SignUp"));
		Thread.sleep(2000);

		expUName = "Welcome " + str1;
		// actUName=super.returnText(read.getObject("UserName"));

		actUName = driver.findElement(By.xpath("//div[@id='tophat']/div/nav/section/p/span")).getText();

		if (actUName.equals(expUName)) {
			Reporter.log("Motley Fool Share Advisor Account Creation Test Passed. " + "Account created with Username: "
					+ str1);
			Thread.sleep(2000);
			driver.quit();

		} else {
			Reporter.log("Login not happend for Website");
			Assert.assertFalse(true);
		}

	}

}
