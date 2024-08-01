package com.mercedes_benz.qa.ui.selenium;

import com.mercedes_benz.qa.ui.data_reader.SeleniumReader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;

import java.io.IOException;

import static org.openqa.selenium.remote.Browser.CHROME;
import static org.openqa.selenium.remote.Browser.FIREFOX;

/**
 * This class manages the WebDriver instances for different browser types.
 */
public class DriverManager {
    /**
     * The WebDriver instance.
     */
    private WebDriver driver;
    /**
     * The browser type to be used.
     */

    /**
     * The SeleniumReader instance to read the Selenium configuration properties.
     */
    private final SeleniumReader seleniumReader;

    /**
     * Constructor to initialize the DriverManager with a specific browser type.
     *
     * @param browser the browser type to be used (e.g., CHROME, FIREFOX)
     */
    public DriverManager(final Browser browser) throws IOException {
        this.seleniumReader = new SeleniumReader();
    }

    /**
     * Returns the WebDriver instance. If the driver is not initialized, it creates a new instance.
     *
     * @return the WebDriver instance
     */
    public WebDriver getDriver() {
        if (driver == null) {
            driver = createDriver();
        }
        return driver;
    }

    /**
     * Creates a new WebDriver instance based on the specified browser type.
     *
     * @return the WebDriver instance
     */
    private WebDriver createDriver() {
        return createLocalDriver();
    }

    /**
     * Creates a local WebDriver instance for the specified browser type.
     *
     * @return the WebDriver instance
     */
    private WebDriver createLocalDriver() {

        setWindowMaximize();
        driver = initBrowser();
        getDriver().manage().timeouts().implicitlyWait(seleniumReader.getImplicitWait());
        return driver;
    }


    /**
     * Sets the window to maximize if the browserFullScreen property is set to true.
     */
    private void setWindowMaximize() {
        if (seleniumReader.getBrowserFullScreen()) {
            driver.manage().window().maximize();
        }
    }

    private WebDriver initBrowser (){
        Browser browser = seleniumReader.getBrowserType();
        return switch (browser.browserName()) {
            case "chrome" -> new ChromeDriver((ChromeOptions) getCapabilities(browser));
            case "firefox" -> new FirefoxDriver((FirefoxOptions) getCapabilities(browser));
            default -> throw new IllegalStateException("Unexpected value: " + browser.browserName());
        };
    }

    private Capabilities getCapabilities(final Browser browser) {
        var isHeadless = seleniumReader.isBrowserHeadless();
        if (browser.equals(CHROME)) {
            var options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-search-engine-choice-screen");
            if (isHeadless) {
                options.addArguments("--headless");
            }
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            return options;
        } else if (browser.equals(FIREFOX)) {
            var options = new FirefoxOptions();
            if (isHeadless) {
                options.addArguments("--headless");
            }
            options.setPageLoadStrategy(PageLoadStrategy.EAGER);
            return options;
        }
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }

    /**
     * Closes the WebDriver instance if it is not null.
     */
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
