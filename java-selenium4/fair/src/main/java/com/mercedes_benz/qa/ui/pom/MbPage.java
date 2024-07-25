package com.mercedes_benz.qa.ui.pom;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class MbPage {
    private final WebDriver driver;
    private final static  String URL = "https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo";
    private final static  String SHADOW_HOST = "cmm-cookie-banner";

    Logger logger = Logger.getLogger(this.getClass().getName());


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
