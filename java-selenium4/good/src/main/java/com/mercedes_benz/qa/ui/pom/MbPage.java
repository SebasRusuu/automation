package com.mercedes_benz.qa.ui.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MbPage extends LoadableComponent<MbPage> {
    private final WebDriver driver;
    private static final By SHADOW_HOST = By.cssSelector("cmm-cookie-banner");

    public MbPage(WebDriver driver) {
        this.driver = driver;
    }

    //opens the page
    public void openBrowser() {
        isLoaded();
        driver.manage().window().maximize();
        WebDriverWait waitShadowHost = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitShadowHost.until(ExpectedConditions.presenceOfElementLocated(SHADOW_HOST));
    }

    @Override
    protected void load() {
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");
    }

    @Override
    protected void isLoaded() throws Error {
        String url = driver.getCurrentUrl();
        if (!url.equals("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo")) {
            throw new Error("The page is not loaded");
        }
    }

}
