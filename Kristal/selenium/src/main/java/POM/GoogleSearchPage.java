package POM;

import Utilities.Utilities;
import io.cucumber.java.sl.In;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GoogleSearchPage extends Utilities {

    public GoogleSearchPage() {
        driver = getDriver();
        PageFactory.initElements(driver, this);
    }

    public static String amount;
    public static String publicLink;


    @FindBy(xpath = ".//input[@title='Search']")
    public WebElement _searchBoxHomePage;

    @FindBy(xpath = ".//ul[@role='listbox']")
    public WebElement _autoSuggestionsHomePage;

    @FindBy(xpath = ".//h1[text()='Search Results']/parent::div/div/div")
    public List<WebElement> _searchResultsCount;

    @FindBy(xpath = ".//h1[text()='Search modes']/parent::div/div//div/a[contains(text(),'Shopping')]")
    public WebElement _extraOptions;

    @FindBy(xpath = ".//div/a/h3[text()='Nikola Tesla - Wikipedia']")
    public WebElement _s;

    @FindBy(xpath = ".//h1[text()='Page navigation']/parent::span//table//tr/td")
    public List<WebElement> _pagination;

    @FindBy(xpath = ".//h1[text()='Page navigation']/parent::span//table//tr/td//span[text()='Next']")
    public WebElement _nextOption;

    @FindBy(xpath = "//div/a[text()='Settings']")
    public WebElement _settingsOption;

    @FindBy(xpath = "//div/a/span[text()='Languages']")
    public WebElement _chooseLanguageSetting;

    @FindBy(xpath = "//div[@id='langtde']//span[text()='Deutsch']")
    public WebElement _dutchLanguage;

    @FindBy(xpath = ".//div[text()='Save']")
    public WebElement _saveButton;

    @FindBy(xpath = " .//img[@src='/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png']")
    public WebElement _homePageIcon;

    public boolean googleHomePage(){
        return _searchBoxHomePage.isDisplayed() && _homePageIcon.isDisplayed();
    }

    public boolean verifyAutosuggestion(String text) throws InterruptedException {
        _searchBoxHomePage.sendKeys(text);
        Thread.sleep(2000);
        return _autoSuggestionsHomePage.isDisplayed();
    }

    public void searchContent(String text){
        _searchBoxHomePage.sendKeys(text);
        _searchBoxHomePage.sendKeys(Keys.ENTER);
    }
    public boolean resultCount(Integer count){
        return _searchResultsCount.size()>count;
    }

    public void clickOnSearchLink(String content){
         driver.findElement(By.xpath(String.format(".//div/a/h3[text()='%s']",content))).click();
    }

    public boolean verifyPagination() throws InterruptedException {
        Thread.sleep(3000);
        return _pagination.size()>1 && _nextOption.isDisplayed();
    }

    public void verifyLanguageChangeOption() throws InterruptedException {
        Thread.sleep(3000);
        _settingsOption.click();
        _chooseLanguageSetting.click();
        _dutchLanguage.click();
        _saveButton.click();
    }
}
