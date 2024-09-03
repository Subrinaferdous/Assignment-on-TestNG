package testrunner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import pages.InfoPage;
import pages.LoginPage;
import pages.PIMPage;
import setup.SetUp;
import utils.Utils;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static pages.InfoPage.*;
import static utils.Utils.readJSONData;

public class UserLoginTestRunner extends SetUp {

    @Test(priority = 1, description = "login with the newly created employee and assertion and My info update")

    public void userLogin() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage=new LoginPage(driver);
        JSONArray empArray=readJSONData();
        JSONObject empObj= (JSONObject) empArray.get(empArray.size()-1);

        //users.json value show
        String username=empObj.get("username").toString();
        String password=empObj.get("password").toString();
        loginPage.doLogin(username,password);

        String firstName = empObj.get("firstName").toString();
        String lastName = empObj.get("lastName").toString();
        String fullname = firstName + " " + lastName;

        String titleActual = driver.findElement(By.className("oxd-userdropdown-name")).getText();
        String titleexpected = fullname;
        Assert.assertTrue(titleActual.contains(titleexpected));
        Thread.sleep(5000);

        //click My Info
        List<WebElement> myInfo = driver.findElements(By.tagName("a"));
        Actions actions = new Actions(driver);
        actions.click(myInfo.get(3)).perform();

        InfoPage infoPage = new InfoPage(driver);
        Thread.sleep(3000);

        gender.get(9).click();
        Thread.sleep(3000);

        Utils.scroll(driver, 550);

        button.get(0).click();
        Thread.sleep(3000);

        bloodgroup.get(2).click();
        Thread.sleep(5000);

        bloodgroup.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        bloodgroup.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        bloodgroup.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        bloodgroup.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        bloodgroup.get(2).sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(500);
        bloodgroup.get(2).sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        button.get(1).click();

    }

    @AfterTest(description = " logout the session")
    public void doLogout() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogout();
        Assert.assertTrue(loginPage.loginForgot.isDisplayed());

    }
}
