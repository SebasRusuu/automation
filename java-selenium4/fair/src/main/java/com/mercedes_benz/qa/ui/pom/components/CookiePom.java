package com.mercedes_benz.qa.ui.pom.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class CookiePom implements ICookie {
    private final WebDriver driver;

    private WebElement mainWebElement;

    private static final String ACCEPT_COOKIES = "[data-test='handle-accept-all-button']";
    private static final String SHADOW_HOST = "cmm-cookie-banner";
    private static final String COOKIE_BODY = "cmm-cookie-banner__wrapper";

    public CookiePom(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void acceptCookies() throws InterruptedException {
        // Wait for the shadow host to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SHADOW_HOST)));

        WebElement shadowHostElement = driver.findElement(By.cssSelector(SHADOW_HOST));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        SearchContext shadowRoot = (SearchContext) js.executeScript("return arguments[0].shadowRoot", shadowHostElement);
        while (shadowRoot.findElements(By.className(COOKIE_BODY)).isEmpty())
            ;
        Thread.sleep(500);

        mainWebElement = shadowRoot.findElement(By.className(COOKIE_BODY));
        wait.until(ExpectedConditions.elementToBeClickable(mainWebElement.findElement(By.cssSelector(ACCEPT_COOKIES)))).click();
    }

    public boolean isCookieClosed(){
        return !mainWebElement.getAttribute("class").contains("visible");
    }



}