package com.fool.login;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;

//import com.fool.input.InputCountry;
import com.fool.pageobject.PageObjectModule;

public class LoginToFool extends PageObjectModule {

    @BeforeClass
    //Login
    public void loginFool() throws Throwable {
        super.browser();
        try {
            super.getUrl(read.readData("Browser&URL", 1, 1));
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            super.enterText(read.getObject("Edit_Username"), read.readData("Login", 2, 0));
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.assertFalse(true);
        }
        
        try {
            super.enterText(read.getObject("Edit_Password"), read.readData("Login", 2, 1));
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.assertFalse(true);
        }
        
        try {
            super.clickButton(read.getObject("Button_Login"));
        } catch (Throwable e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Assert.assertFalse(true);
        }

    }
}

    