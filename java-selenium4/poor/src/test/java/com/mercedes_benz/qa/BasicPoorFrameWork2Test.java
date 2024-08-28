package com.mercedes_benz.qa;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Logger;

public class BasicPoorFrameWork2Test {

    WebDriver driver;
    ChromeOptions options;

    @BeforeEach
    void setUp() {
        options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
    }

    @Test
    void testHomePage() throws InterruptedException {
        Logger logger = Logger.getLogger(this.getClass().getName());

        // Navigate to the specified URL
        driver.get("https://shop.mercedes-benz.com/en-au/shop/vehicle/srp/demo");

        // Cookies Accept
        WebDriverWait waitShadowHost = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitShadowHost.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("cmm-cookie-banner")));
        logger.info("Shadow host found: " + (driver.findElement(By.cssSelector("cmm-cookie-banner")).getTagName()));
        var shadowHost = driver.findElement(By.cssSelector("cmm-cookie-banner")).getShadowRoot();
        while (shadowHost.findElements(By.cssSelector("[data-test='handle-accept-all-button']")).isEmpty()) {
            System.out.println("Waiting for the shadow host to load");
            Thread.sleep(500);
        }

        clickByDevil(shadowHost, "[data-test='handle-accept-all-button']");

        var selectElementSelect = driver.findElement(By.cssSelector("[data-test-id='modal-popup__location'] select"));
        Select select = new Select(selectElementSelect);
        select.selectByVisibleText("New South Wales");

        driver.findElement(By.cssSelector("[data-test-id='modal-popup__location'] input")).sendKeys("2007");

        WebElement checkbox = driver.findElement(By.cssSelector("[value='P']"));
        // Using JavaScript Executor to click the checkbox
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkbox);

        var selectElementButton = driver.findElement(By.cssSelector("button[data-test-id='state-selected-modal__close']"));
        selectElementButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector("[data-test-id='modal-popup__location'] .wb-modal-dialog-wrapper"), "style", "display: none;"));

        // display popup none
        String attributeValue = driver.findElement(By.cssSelector("[data-test-id='modal-popup__location'] .wb-modal-dialog-wrapper")).getDomAttribute("style");
        Assertions.assertEquals("display: none;", attributeValue);
    }

    void clickByDevil(SearchContext shadowHost, String cssSelector) {
        try {
            shadowHost.findElement(By.cssSelector(cssSelector)).click();
        } catch (Exception e) {
            System.out.println("Pray and try again");
            clickByDevil(shadowHost, cssSelector);
        }
    }

    @AfterEach
    void tearDown() {
        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }
}