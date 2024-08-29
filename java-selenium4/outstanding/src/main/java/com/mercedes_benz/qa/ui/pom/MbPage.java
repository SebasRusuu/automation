package com.mercedes_benz.qa.ui.pom;

import com.mercedes_benz.qa.ui.data_reader.SeleniumReader;
import com.mercedes_benz.qa.ui.pom.components.cookie.CookiePom;
import com.mercedes_benz.qa.ui.pom.components.form.FormPom;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

/**
 * Page Object Model for the Mercedes-Benz page.
 */
public class MbPage extends LoadableComponent<MbPage> {
    /**
     * The WebDriver instance to use.
     */
    private final WebDriver driver;
    /**
     * The WebDriverWait instance to use.
     */
    private final WebDriverWait wait;
    /**
     * The SeleniumReader instance to read the Selenium configuration properties.
     */
    private final SeleniumReader seleniumReader;
    /**
     * The shadow host element locator.
     */
    private static final By SHADOW_HOST = By.cssSelector("cmm-cookie-banner");
    /**
     * Constructor for MbPage.
     *
     * @param mbDriver the WebDriver instance to use
     */
    public MbPage(final WebDriver mbDriver) throws RuntimeException {
        this.driver = mbDriver;
        try {
            this.seleniumReader = new SeleniumReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.wait = new WebDriverWait(mbDriver, Duration.ofSeconds(seleniumReader.getWaitTime()));
    }

    /**
     * Loads the Mercedes-Benz page.
     */
    @Override
    protected void load() {
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");
    }

    /**
     * Ensures the Mercedes-Benz page is loaded.
     *
     * @throws Error if the page is not loaded correctly
     */
    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        if (!url.contains("/shop/vehicle/srp/demo")) {
            throw new Error("The page is not loaded. Current url:" +url); // this method throws Exception
        }
    }

    /**
     * Gets the CookiePom component from the shadow DOM.
     *
     * @return an instance of CookiePom
     */
    public CookiePom getCookie() {
        WebElement shadowHostElement = driver.findElement(SHADOW_HOST);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        SearchContext shadowRootContext =
                (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowHostElement);
        return new CookiePom(shadowRootContext, wait);
    }

    /**
     * Gets the FormPom component.
     *
     * @return an instance of FormPom
     */
    public FormPom getForm() {
        return new FormPom(driver);
    }
}
