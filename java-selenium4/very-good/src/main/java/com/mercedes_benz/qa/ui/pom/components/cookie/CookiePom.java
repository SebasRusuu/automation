package com.mercedes_benz.qa.ui.pom.components.cookie;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CookiePom implements ICookie {
    private final WebDriver driver;

    private WebElement mainWebElement;

    private static final By ACCEPT_COOKIES = By.cssSelector("cmm-buttons-wrapper [data-test='handle-accept-all-button']");
    private static final By SHADOW_HOST = By.cssSelector("cmm-cookie-banner");
    private static final By COOKIE_BODY = By.cssSelector(".cmm-cookie-banner__wrapper");

    public CookiePom(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Wait for the shadow host to be present
            wait.until(ExpectedConditions.presenceOfElementLocated(SHADOW_HOST));
            WebElement shadowHostElement = driver.findElement(SHADOW_HOST);

            // Access the shadow root
            JavascriptExecutor js = (JavascriptExecutor) driver;
            SearchContext shadowRoot = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowHostElement);

            // Wait for the cookie body to be present within the shadow root
            wait.until(ExpectedConditions.presenceOfElementLocated(COOKIE_BODY));
            mainWebElement = shadowRoot.findElement(COOKIE_BODY);

            // Click the accept cookies button
            wait.until(ExpectedConditions.elementToBeClickable(mainWebElement.findElement(ACCEPT_COOKIES))).click();
        } catch (Exception e) {
            throw new RuntimeException("Failed to accept cookies: " + e.getMessage(), e);
        }
    }

    public boolean isCookieClosed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(mainWebElement, "class", "visible")));
        return !mainWebElement.getAttribute("class").contains("visible");
    }
}