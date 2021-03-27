import POM.GoogleSearchPage;
import POM.GoogleSearchPage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

@Listeners(Utilities.ListenerTest.class)

public class GoogleSearch extends GoogleSearchPage {
    public GoogleSearchPage googleSearchPage;
    boolean result;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
        googleSearchPage = new GoogleSearchPage();
        Reporter.log("Driver Launched");

    }

    @Test(description = "Navigation to google.com" , priority = 0)
    public void verifyHomeSearchPage() throws InterruptedException, IOException, InvalidFormatException {
        driver.get("https://www.google.com/");
        Reporter.log("Navigated to google.com");
        result = googleSearchPage.googleHomePage();
        Assert.assertTrue(result, "search box is available on home page");
        Reporter.log("search box is available");
        result = result && googleSearchPage.verifyAutosuggestion("Tesla");
        Assert.assertTrue(result, "autosuggestion verified");
        Reporter.log("autosuggestions are displayed");
    }

    @Test(description = "Verify search result" , priority = 1)
    public void verifySearchingContent() throws InterruptedException, IOException, InvalidFormatException {
        driver.get("https://www.google.com/");
        Reporter.log("Navigated to google.com");
        googleSearchPage.searchContent("Tesla");
        Reporter.log("Searched tesla");
        result = googleSearchPage.resultCount(5);
        Assert.assertTrue(result, "result count verified");
        Reporter.log("verified result count");
        googleSearchPage.clickOnSearchLink("Tesla: Electric Cars, Solar & Clean Energy");
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.tesla.com/");
        Reporter.log("landed on tesla site");
    }

    @Test(description = "Verify pagination" , priority = 2)
    public void verifyPaginationCheck() throws InterruptedException, IOException, InvalidFormatException {
        driver.get("https://www.google.com/");
        googleSearchPage.searchContent("Tesla");
        Reporter.log("searched content");
        result = googleSearchPage.verifyPagination();
        Assert.assertTrue(result,"pagination verified");
        Reporter.log("pagination is available");
    }

    @Test(description = "Verify language change option" , priority = 3)
    public void verifyLanguageChange() throws InterruptedException, IOException, InvalidFormatException {
        driver.get("https://www.google.com/");
        googleSearchPage.searchContent("Tesla");
        Reporter.log("searched content");
        googleSearchPage.verifyLanguageChangeOption();
        Reporter.log("Language is changed to dutch");
    }

    @AfterMethod
    public void closeDriver() {

    }
}