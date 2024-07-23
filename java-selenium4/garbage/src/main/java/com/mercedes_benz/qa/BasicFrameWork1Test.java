package com.mercedes_benz.qa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


class BasicFrameWork1Test {

    @Test
    void testHomePage() throws InterruptedException {
        // Setup WebDriver
        WebDriver driver = new ChromeDriver();

        // Navigate to the specified URL
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");
        Thread.sleep(7000); // Wait for the page to load

        //Google
        var shadowHost = driver.findElement(By.cssSelector("cmm-cookie-banner")).getShadowRoot();
        shadowHost.findElement(By.cssSelector("[data-test='handle-accept-all-button']")).click();

        var selectElementSelect = driver.findElement(By.cssSelector("[data-test-id='modal-popup__location'] select"));
        Select select = new Select(selectElementSelect);
        select.selectByVisibleText("New South Wales");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("[data-test-id='modal-popup__location'] input")).sendKeys("2007");


        WebElement checkbox = driver.findElement(By.cssSelector("[value='P']"));
        // Using JavaScript Executor to click the checkbox
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkbox);

        var selectElementButton = driver.findElement(By.cssSelector("button[data-test-id='state-selected-modal__close']"));
        selectElementButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("[data-test-id='modal-popup__location'] .wb-modal-dialog-wrapper"), "style", "display: none;"));

        //display popup none
        String attributeValue = driver.findElement(By.cssSelector("[data-test-id='modal-popup__location'] .wb-modal-dialog-wrapper")).getDomAttribute("style");
        Assertions.assertEquals("display: none;", attributeValue);

        // Quit the driver

        driver.quit();


    }
}
