package testclasses.interfaceandinheritance;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import interfaceandinheritance.Browser;
import interfaceandinheritance.GoogleHomePage;
import interfaceandinheritance.GoogleSearchResultPage;
import interfaceandinheritance.Page;

/**
 * @author  tbhadauria <tarun.kumar.bhadauria@zalando.de>
 */
public class TestClass {

    private WebDriver webDriver;

    /**
     * initialize driver.
     *
     * @param  browser
     */
    public void initDriver(final Browser browser) {

        switch (browser) {

            case Chrome :

                // assign object to type interface and not specific implementation
                webDriver = new ChromeDriver();
                break;

            case FF :
                webDriver = new FirefoxDriver();
                break;

            case IE :
                webDriver = new InternetExplorerDriver();
                break;

            default :
                System.out.println("Invalid Browser!!!");
        }

        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    /**
     * launch web application.
     */
    public void launchApp() {
        webDriver.get("http://www.google.com/");
    }

    /**
     * shut down driver.
     */
    public void shutDownDriver() {
        webDriver.close();
        webDriver.quit();
    }

    public void searchOnGoogleHomePage(final String testData) {

        // assign object to type interface and not specific implementation
        Page googleHomePage = new GoogleHomePage(webDriver);

        // search on home page
        googleHomePage.search(testData);
    }

    public void searchOnSearchResultPage(final String testData) {

        // search on search result page
        Page googleSearchResultPage = new GoogleSearchResultPage(webDriver);
        googleSearchResultPage.search(testData);
    }

    public String getSearchResultStatistics() {
        System.out.println("########################################");
        System.out.println("########################################");
        System.out.println("calling method specific to GoogleSearchResultPage");

        Page googleSearchResultPage = new GoogleSearchResultPage(webDriver);
        return ((GoogleSearchResultPage) (googleSearchResultPage)).getResultSet();
    }

    public static void main(final String[] args) {

        TestClass testClass = new TestClass();
        Browser browser = Browser.FF;
        testClass.initDriver(browser);
        testClass.launchApp();

        testClass.searchOnGoogleHomePage("black box testing");
        testClass.searchOnSearchResultPage("white box testing");
        System.out.println("Result set is: " + testClass.getSearchResultStatistics());

        testClass.shutDownDriver();
    }

}
