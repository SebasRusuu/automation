package com.mercedes_benz.qa.ui.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Browser;

/**
 * This enum represents different types of browsers and provides methods to work with them.
 */
@Getter
public enum BrowserType {
    /**
     * Represents the Firefox browser.
     */
    FIREFOX(Browser.FIREFOX),

    /**
     * Represents the Chrome browser.
     */
    CHROME(Browser.CHROME);

    /**
     * The browser type.
     */
    private final Browser browser;

    /**
     * Constructor to initialize the browser type.
     *
     * @param browserType the browser type
     */
    BrowserType(final Browser browserType) {
        this.browser = browserType;
    }

    /**
     * Parses a string representation of a browser type into a {@link Browser} enum.
     *
     * @param s the string representation of the browser type
     * @return the corresponding {@link Browser} enum type
     * @throws WebDriverException if the browser type is unknown
     */
    public static Browser parseBrowser(final String s) {
        return switch (s.toLowerCase()) {
            case "firefox", "ff" -> Browser.FIREFOX;
            case "googlechrome", "chrome", "gc" -> Browser.CHROME;
            default -> throw new WebDriverException("Unknown browser as: " + s);
        };
    }
}
