package com.mercedes_benz.qa.ui.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Browser;

/**
 * This enum provides a method to parse a string into a {@link Browser} enum type.
 */
@Getter
public enum BrowserType {
    FIREFOX(Browser.FIREFOX),
    CHROME(Browser.CHROME);

    private final Browser browser;

    BrowserType(Browser browser) {
        this.browser = browser;
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