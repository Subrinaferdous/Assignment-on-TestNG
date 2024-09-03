package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import setup.EmployeeModel;

import java.util.List;

public class PIMPage {

    @FindBy (className = "oxd-main-menu-item--name")
    public List<WebElement> leftMenuBar;

    @FindBy (className = "oxd-button")
    public List<WebElement> button;

    @FindBy(className = "oxd-input")
    public List<WebElement> textfield;

    @FindBy(css = "input[placeholder='Employee Id']")
    public WebElement employeeIdField;

    @FindBy(className = "input")
    public List<WebElement> inputFields;


    public PIMPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    //for model use
    public void createNewEmployee(EmployeeModel model){
        textfield.get(1).sendKeys(model.getFirstName());
        textfield.get(2).sendKeys(model.getMiddleName());
        textfield.get(3).sendKeys(model.getLastName());
        textfield.get(5).sendKeys(model.getUsername());
        textfield.get(6).sendKeys(model.getPassword());
        textfield.get(7).sendKeys(model.getPassword());
        button.get(1).click();


    }
}
