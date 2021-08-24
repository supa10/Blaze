package basecode;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import gherkin.formatter.Formatter;
import gherkin.formatter.Reporter;
import gherkin.formatter.model.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.ResourceBundle;


public class SeleniumCode implements Formatter, Reporter {
    private static WebDriver driver;
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
    private final Logger logger = LoggerFactory.getLogger(SeleniumCode.class);
    private final String browser = System.getProperty("browser") == null || System.getProperty("browser").isEmpty() ? "chrome" : System.getProperty("browser");
    private Select select;
    private WebDriverWait webDriverWait;

    @Before
    public void setup(Scenario scenario) {
        logger.info("Browser passed -- " + browser.toUpperCase());
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src//main//java//driver//chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "//src//main//java//driver//msedgedriver.exe");
                driver = new EdgeDriver();
                break;
        }

        driver.get(resourceBundle.getString("url"));
        driver.manage().window().maximize();
        logger.info("URL launched -- " + resourceBundle.getString("url").toUpperCase());
    }

    @After
    public void teardown(Scenario scenario) {
        logger.info(scenario.getName());
        logger.info("Closing Browser session");
        driver.quit();

    }

    protected String verifyText(By homePageText) {
        waitForElementToDisplay(homePageText);
        return driver.findElement(homePageText).getText();
    }

    protected String GetPageTitle() {
        return driver.getTitle();
    }


    protected void selectOptionFromDropdown(By webloctor, String cityName) {
        select = new Select(driver.findElement(webloctor));
        select.selectByVisibleText(cityName);
    }

    protected void buttonClick(By locator) {
        waitForElementToDisplay(locator);
        driver.findElement(locator).click();
    }

    private void waitForElementToDisplay(By locator) {
        webDriverWait = new WebDriverWait(driver, 50);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> getAllElementsMatching(By locator) {
        return driver.findElements(locator);
    }


    protected void enterText(By locator, String s) {
        waitForElementToDisplay(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(s);
    }


    protected List<WebElement> getAllOptionInDropDown(By locator) {
        select = new Select(driver.findElement(locator));
        return select.getOptions();
    }


    public void syntaxError(String state, String event, List<String> legalEvents, String uri, Integer line) {
        logger.info("syntaxError-- " + state);
    }

    public void uri(String uri) {
    }

    public void feature(Feature feature) {
        logger.info("feature -- " + feature.getName());
    }

    public void scenarioOutline(ScenarioOutline scenarioOutline) {
        logger.info("scenarioOutline-- " + scenarioOutline.getName());
    }

    public void examples(Examples examples) {
        logger.info("examples-- " + examples);
    }

    public void startOfScenarioLifeCycle(gherkin.formatter.model.Scenario scenario) {
        logger.info("start Of Scenario -- " + scenario.getName());
    }


    public void background(Background background) {
        logger.info("background-- " + background);
    }

    public void scenario(gherkin.formatter.model.Scenario scenario) {
        logger.info("scenario Name -- " + scenario.getName());
    }

    public void scenario(Scenario scenario) {
        logger.info("scenario-- " + scenario.getName());
    }

    public void step(Step step) {
        logger.info("step -- " + step.getName());
    }

    public void endOfScenarioLifeCycle(gherkin.formatter.model.Scenario scenario) {
        logger.info("end Of Scenario -- " + scenario.getName());
    }


    public void done() {
    }

    public void close() {
    }

    public void eof() {

    }

    public void before(Match match, Result result) {

    }

    public void result(Result result) {

    }

    public void after(Match match, Result result) {

    }

    public void match(Match match) {
        
    }

    public void embedding(String mimeType, byte[] data) {
        logger.info("embedding-- " + mimeType);
    }

    public void write(String text) {
        logger.info("write-- " + text);
    }
}
