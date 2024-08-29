package com.mercedes_benz.qa;

import com.mercedes_benz.qa.ui.data.Form;
import com.mercedes_benz.qa.ui.data.Purpose;
import com.mercedes_benz.qa.ui.pom.MbPage;
import com.mercedes_benz.qa.ui.pom.components.CookiePom;
import com.mercedes_benz.qa.ui.pom.components.FormPom;
import com.mercedes_benz.qa.ui.selenium.DriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Browser;

import static com.mercedes_benz.qa.ui.selenium.BrowserType.parseBrowser;

class GoodTest {
    private WebDriver driver;
    private DriverManager driverManager;

    @BeforeEach
    void setUp() {
        String browserName = System.getenv("BROWSER_NAME");
        if (browserName == null) {
            browserName = System.getProperty("BROWSER_NAME");
        }
        if (browserName == null) {
            throw new IllegalArgumentException("Browser type cannot be null");
        }
        Browser browser = parseBrowser(browserName);
        driverManager = new DriverManager(browser);
        driver = driverManager.getDriver();
    }
    @Test
    void testHomePage() throws InterruptedException {
        MbPage mbPage = new MbPage(driver);
        mbPage.get().openBrowser();

        CookiePom cookiePom = new CookiePom(driver);
        cookiePom.acceptCookies();
        Assertions.assertTrue(cookiePom.isCookieClosed());

        Form form = new Form("2007", "New South Wales", Purpose.PRIVATE);

        FormPom formPom = new FormPom(driver);
        formPom.fillForm(form);
        Assertions.assertTrue(formPom.isFormClosed());
    }

    @AfterEach
    void tearDown() {
        driverManager.close();
    }
}
