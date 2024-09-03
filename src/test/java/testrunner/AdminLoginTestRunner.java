package testrunner;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import setup.SetUp;

public class AdminLoginTestRunner extends SetUp {

    LoginPage loginPage;

    @Test(description = " Login as a admin")
    public void doLogin() {
        loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
        //loginPage.doLogin(System.getProperty("username"), System.getProperty("password"));
        Assert.assertTrue(loginPage.btnProfileImage.isDisplayed());
    }


    @AfterTest
    public void doLogout() {
        loginPage = new LoginPage(driver);
        loginPage.doLogout();
        Assert.assertTrue(loginPage.loginForgot.isDisplayed());

    }
}


