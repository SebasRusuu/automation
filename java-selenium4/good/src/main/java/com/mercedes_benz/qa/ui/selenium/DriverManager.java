package com.mercedes_benz.qa.ui.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import java.time.Duration;

import static org.openqa.selenium.remote.Browser.CHROME;
import static org.openqa.selenium.remote.Browser.FIREFOX;

public class DriverManager {
    private WebDriver driver;
    private Browser driverType;
    private ChromeOptions options;

    public DriverManager(Browser browser) {
        this.driverType = browser;
    }

    public WebDriver getDriver() {
        if(driver == null) driver = createDriver();
        return driver;
    }

    private WebDriver createDriver() {

        return createLocalDriver();
    }


    private WebDriver createLocalDriver() {
        if (driverType.equals(FIREFOX)) {
            driver = new FirefoxDriver();
        } else if (driverType.equals(CHROME)) {
            options = new ChromeOptions();
            options.addArguments("--disable-search-engine-choice-screen");
            driver = new ChromeDriver(options);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
