package com.mercedes_benz.qa.ui.selenium;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.Browser;
import static org.openqa.selenium.remote.Browser.CHROME;
import static org.openqa.selenium.remote.Browser.FIREFOX;

public class BrowserType {
    //switch case
    public static Browser parseBrowser(String s) {
        return switch (s.toLowerCase()) {
            case "firefox", "ff" -> FIREFOX;
            case "googlechrome", "chrome", "gc" -> CHROME;
            default -> throw new WebDriverException("Unknown browser as: " + s);
        };
    }

}
