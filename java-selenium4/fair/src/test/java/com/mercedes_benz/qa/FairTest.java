package com.mercedes_benz.qa;

import com.mercedes_benz.qa.ui.pom.MbPage;
import com.mercedes_benz.qa.ui.pom.components.CookiePom;
import com.mercedes_benz.qa.ui.pom.components.FormPom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class FairTest {
    WebDriver driver;
    ChromeOptions options;

    @BeforeEach
    void setUp() {
        // Setup ChromeOptions
        options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");

        // Initialize WebDriver with options
        driver = new ChromeDriver(options);
    }

    @Test
    void testHomePage() throws InterruptedException {
        MbPage mbPage = new MbPage(driver);
        mbPage.open();
        CookiePom cookiePom = new CookiePom(driver);
        cookiePom.acceptCookies();
        Assertions.assertTrue(cookiePom.isCookieClosed());

        FormPom formPom = new FormPom(driver);
        formPom.fillForm();
        Assertions.assertTrue(formPom.isFormClosed());
    }

    @AfterEach
    void tearDown() {
        // Quit the driver
        if (driver != null) {
            driver.quit();
        }
    }
}