package com.mercedes_benz.qa.ui.pom.components.cookie;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class represents the Cookie banner component in the Page Object Model (POM) framework.
 * It provides methods to interact with the cookie banner.
 */
public class CookiePom implements ICookie {


    /**
     * Locator for the loader element that appears while waiting for page interactions.
     */
    private static final By LOADER = By.cssSelector(".dcp-loader");

    /**
     * Web element representing the accept cookies button within the cookie banner.
     */
    @FindBy(css = "cmm-buttons-wrapper [data-test='handle-accept-all-button']")
    private WebElement acceptCookiesButtonWebElement;

    /**
     * Web element representing the entire cookie banner.
     */
    @FindBy(css = ".cmm-cookie-banner__wrapper")
    private WebElement cookieBannerWebElement;

    /**
     * WebDriverWait instance used to wait for specific conditions to be met.
     */
    private final WebDriverWait wait;

    /**
     * Constructor for CookiePom.
     *
     * @param shadowRootContext the SearchContext instance to interact with shadow DOM elements
     * @param cookieWait              the WebDriverWait instance for waiting for certain conditions
     */
    public CookiePom(final SearchContext shadowRootContext, final WebDriverWait cookieWait) {
        getElements(shadowRootContext);
        this.wait = cookieWait;
    }

    /**
     * Initializes web elements using the given SearchContext.
     *
     * @param shadowRootContext the SearchContext instance to interact with shadow DOM elements
     */
    private void getElements(final SearchContext shadowRootContext) {
        PageFactory.initElements(shadowRootContext, this);
    }

    /**
     * Clicks the accept cookies button to accept all cookies.
     * Waits for the loader to disappear before clicking the button.
     */
    @Override
    public void acceptCookies() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(LOADER));
        acceptCookiesButtonWebElement.click();
    }

    /**
     * Checks if the cookie banner is closed.
     *
     * @return true if the cookie banner is closed, false otherwise
     */
    public boolean isCookieClosed() {
        return !cookieBannerWebElement.getAttribute("class").contains("visible");
    }
}
