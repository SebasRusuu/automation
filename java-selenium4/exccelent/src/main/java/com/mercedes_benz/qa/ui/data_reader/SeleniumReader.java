package com.mercedes_benz.qa.ui.data_reader;

import com.mercedes_benz.qa.ui.selenium.BrowserType;
import org.openqa.selenium.remote.Browser;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

/**
 * A utility class to read Selenium configuration properties.
 * It loads properties from the `selenium.properties` file and provides methods
 * to access various Selenium-related settings.
 */
public class SeleniumReader {
    /**
     * The properties object that holds the Selenium configuration properties.
     */
    private final Properties seleniumProperties;

    /**
     * Constructs a {@code SeleniumReader} and loads the properties from the `selenium.properties` file.
     *
     * @throws IOException if an error occurs during loading the properties file.
     */
    public SeleniumReader() throws IOException {
        seleniumProperties = new Properties();
        try (var inputStream = getClass().getClassLoader().getResourceAsStream("selenium.properties")) {
            seleniumProperties.load(inputStream);
        }
    }

    /**
     * Retrieves a property value, checking the environment variables, system properties,
     * and the provided alternative properties in that order.
     *
     * @param propertyKey the key of the property to retrieve.
     * @param alternative the alternative properties to check if the property is not found
     *                    in environment variables or system properties.
     * @return the property value, or {@code null} if not found.
     */
    public static String getProperty(final String propertyKey, final Properties alternative) {
        var result = System.getenv(propertyKey.toUpperCase().replace(".", "_"));
        if (result == null) {
            result = System.getProperty(propertyKey);
        }
        if (result == null) {
            result = alternative.getProperty(propertyKey);
        }
        return result;
    }

    /**
     * Retrieves the browser type from the properties.
     *
     * @return the browser type as a {@code Browser} object.
     */
    public Browser getBrowserType() {
        return BrowserType.parseBrowser(getProperty("selenium.browser", this.seleniumProperties));
    }

    /**
     * Checks if the browser is set to run in headless mode.
     *
     * @return {@code true} if the browser is headless, {@code false} otherwise.
     */
    public boolean isBrowserHeadless() {
        return Boolean.parseBoolean(getProperty("selenium.browser.headless", this.seleniumProperties));
    }

    /**
     * Retrieves the implicit wait time from the properties.
     *
     * @return the implicit wait time as a {@code String}.
     */
    public Duration getImplicitWait() {
        return Duration.parse(getProperty("selenium.implicit.wait", this.seleniumProperties));
    }

    /**
     * Checks if the browser is set to run in full screen mode.
     *
     * @return {@code true} if the browser is set to full screen, {@code false} otherwise.
     */
    public boolean getBrowserFullScreen() {
        return Boolean.parseBoolean(getProperty("selenium.page.fullscreen", this.seleniumProperties));
    }

    /**
     * Retrieves the wait time from the properties.
     *
     * @return the wait time as a {@code String}.
     */
    public long getWaitTime() {
        return Duration.parse(getProperty("selenium.wait.timeout", this.seleniumProperties)).getSeconds();
    }
}
