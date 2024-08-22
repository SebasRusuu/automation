package com.mercedes_benz.qa.ui.pom;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class MbPage {
    private final WebDriver driver;
    private static final String URL = "https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo";
    private static final String SHADOW_HOST = "cmm-cookie-banner";

    private static final Logger logger = LogManager.getLogManager().getLogger(MbPage.class.getName());


    public MbPage(WebDriver driver) {
        this.driver = driver;
    }

    //opens the page
    public void open() {
        driver.get(URL);
        driver.manage().window().maximize();
        WebDriverWait waitShadowHost = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitShadowHost.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(SHADOW_HOST)));
        logger.info("Shadow host found: " + (driver.findElement(By.cssSelector(SHADOW_HOST)).getTagName()));
    }



}
