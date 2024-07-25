package com.mercedes_benz.qa;

import com.mercedes_benz.qa.ui.pom.MbPage;
import com.mercedes_benz.qa.ui.pom.components.CookiePom;
import com.mercedes_benz.qa.ui.pom.components.FormPom;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

class FairTest {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        // Setup WebDriver
        driver = new ChromeDriver();
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
        driver.quit();
    }
}
