package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InfoPage {

    @FindBy(tagName = "label")
   public static List<WebElement> gender;

    @FindBy(className = "oxd-select-text-input")
    public static List<WebElement> bloodgroup;

    @FindBy(css = "[type=submit]")
    public static List<WebElement> button;

    public InfoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
