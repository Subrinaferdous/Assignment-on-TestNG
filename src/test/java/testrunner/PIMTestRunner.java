package testrunner;

import com.github.javafaker.Faker;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PIMPage;
import setup.EmployeeModel;
import setup.SetUp;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static utils.Utils.readJSONData;

public class PIMTestRunner extends SetUp {
    //WebDriver driver;
    LoginPage loginPage;
    PIMPage pimPage;

    @BeforeTest
    public void doLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("Admin", "admin123");
    }


    @Test(priority = 1, description = "PIM menu")
    public void checkEmployeeList() throws InterruptedException {

        PIMPage pimPage = new PIMPage(driver);
        pimPage.leftMenuBar.get(1).click();
        Thread.sleep(5000);
        String messageActual = driver.findElements(By.className("oxd-text--span")).get(12).getText();
        String messageExpected = "Records Found";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }

    @Test(priority = 2, description = "create a new employee & save info" )
    public void addEmployee() throws IOException, ParseException {
        pimPage = new PIMPage(driver);
        pimPage.button.get(2).click();
        driver.findElement(By.className("oxd-switch-input")).click();

        Faker faker = new Faker(); //randomly generate
        String firstName = faker.name().firstName();
        String middleName = faker.lorem().word();
        String lastName = faker.name().lastName();
        String username = faker.name().username();
        String password = "Password@123";

// for model
        EmployeeModel model = new EmployeeModel();
        model.setFirstName(firstName);
        model.setMiddleName(middleName);
        model.setLastName(lastName);
        model.setUsername(username);
        model.setPassword(password);

        pimPage.createNewEmployee(model);

        WebElement headerTitleElem = driver.findElement(By.xpath("//h6[text()=\"Personal Details\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.visibilityOf(headerTitleElem));
        String textTitle = headerTitleElem.getText();
        Assert.assertTrue(textTitle.contains("Personal Details"));

        //Utils.saveUsers(firstName,lastName,username,password);
        Utils.saveUsers(model);

    }

    @Test(priority = 3,description = " dashboard and search by employee id")
    public void dashboardSearch() throws IOException, ParseException, InterruptedException {
        List<WebElement> dasboard = driver.findElements(By.tagName("a"));
        Actions actions = new Actions(driver);
        actions.click(dasboard.get(8)).perform();

        List<WebElement> admin = driver.findElements(By.tagName("a"));
        actions.click(admin.get(1)).perform();

        JSONArray empArray = readJSONData();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size() - 1);
        String username = empObj.get("username").toString();

        List<WebElement> input = driver.findElements(By.cssSelector("input"));
        actions.sendKeys(input.get(1), username).perform();

        List<WebElement> buttonElm = driver.findElements(By.cssSelector("button"));
        actions.click(buttonElm.get(5)).perform();

        Thread.sleep(5000);

        String titleActual = driver.findElements(By.tagName("span")).get(19).getText();
        String titleexpected = "(1) Record Found";
        Assert.assertTrue(titleActual.contains(titleexpected));

    }

    @Test(priority = 4,description = "Directory menu and search by employee name")
    public void directorySearch() throws IOException, ParseException, InterruptedException {
        List<WebElement> directory = driver.findElements(By.tagName("a"));
        Actions actions = new Actions(driver);
        actions.click(directory.get(9)).perform();

        JSONArray empArray = readJSONData();
        JSONObject empObj = (JSONObject) empArray.get(empArray.size() - 1);
//value show
        String firstName = empObj.get("firstName").toString();

        List<WebElement> input = driver.findElements(By.cssSelector("input[placeholder]"));
        actions.sendKeys(input.get(1), firstName).perform();

        Thread.sleep(5000);
        input.get(1).sendKeys(Keys.ARROW_DOWN);
        input.get(1).sendKeys(Keys.ENTER);

        // Button click
        List<WebElement> buttonElm = driver.findElements(By.cssSelector("button"));
        actions.click(buttonElm.get(5)).perform();

        Thread.sleep(5000);
        WebElement headTitle= driver.findElements(By.className("oxd-text--span")).get(12);
        System.out.println(headTitle.getText());
        String textTitle=headTitle.getText();
        Assert.assertTrue((textTitle.contains("Record Found")));
    }

    @AfterTest(description = " logout the session")
    public void doLogout() {
        loginPage = new LoginPage(driver);
        loginPage.doLogout();
        Assert.assertTrue(loginPage.loginForgot.isDisplayed());

    }

}
