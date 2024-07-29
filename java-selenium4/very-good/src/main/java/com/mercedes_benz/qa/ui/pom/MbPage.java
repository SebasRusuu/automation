package com.mercedes_benz.qa.ui.pom;


import com.mercedes_benz.qa.ui.pom.components.cookie.CookiePom;
import com.mercedes_benz.qa.ui.pom.components.form.FormPom;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.mercedes_benz.qa.ui.pom.components.form.FormPom.MAIN_LOCATOR;

public class MbPage extends LoadableComponent<MbPage> {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final By SHADOW_HOST = By.cssSelector("cmm-cookie-banner");


    public MbPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

    public CookiePom getCookie() {
        return new CookiePom(driver);
    }

    public FormPom getForm() {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(MAIN_LOCATOR)));
        wait.until(ExpectedConditions.visibilityOf(element));
        return new FormPom(element,wait,driver);
    }

}
