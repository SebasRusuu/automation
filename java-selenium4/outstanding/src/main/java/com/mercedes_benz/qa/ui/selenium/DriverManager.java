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
 * Manages the WebDriver instance and browser configurations.
 */
public class DriverManager {
    /**
     * The WebDriver instance.
     */
    private WebDriver driver;

    /**
     * The SeleniumReader instance to read configuration data.
     */
    private final SeleniumReader seleniumReader;

    /**
     * Constructor to initialize the SeleniumReader.
     *
     * @throws IOException if an I/O error occurs
     */
    public DriverManager() throws IOException {
        this.seleniumReader = new SeleniumReader();
    }

    /**
     * Returns the WebDriver instance, creating it if necessary.
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
     * Creates a new WebDriver instance.
     *
     * @return the created WebDriver instance
     */
    private WebDriver createDriver() {
        return createLocalDriver();
    }

    /**
     * Creates a local WebDriver instance based on the browser type.
     *
     * @return the created WebDriver instance
     */
    private WebDriver createLocalDriver() {
        driver = initBrowser();
        setWindowMaximize();
        driver.manage().timeouts().implicitlyWait(seleniumReader.getImplicitWait());
        return driver;
    }

    /**
     * Maximizes the browser window if the configuration specifies full screen.
     */
    private void setWindowMaximize() {
        if (seleniumReader.getBrowserFullScreen()) {
            driver.manage().window().maximize();
        }
    }

    /**
     * Initializes the browser based on the configuration.
     *
     * @return the initialized WebDriver instance
     */
    public WebDriver initBrowser() {
        Browser browser = seleniumReader.getBrowserType();
        return switch (browser.browserName()) {
            case "chrome" -> new ChromeDriver((ChromeOptions) getCapabilities(browser));
            case "firefox" -> new FirefoxDriver((FirefoxOptions) getCapabilities(browser));
            default -> throw new IllegalStateException("Unexpected value: " + browser.browserName());
        };
    }

    /**
     * Returns the browser type as a string.
     *
     * @return the browser type
     */
    public String getBrowserType() {
        Browser browser = seleniumReader.getBrowserType();
        return browser.browserName();
    }

    /**
     * Returns the capabilities for the specified browser.
     *
     * @param browser the browser type
     * @return the capabilities for the browser
     */
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
     * Returns the headless mode status.
     *
     * @return the headless mode status
     */
    public boolean getHeadless() {
        return seleniumReader.isBrowserHeadless();
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
