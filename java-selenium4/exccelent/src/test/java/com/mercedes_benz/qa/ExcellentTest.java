package com.mercedes_benz.qa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes_benz.qa.ui.data.Form;
import com.mercedes_benz.qa.ui.pom.MbPage;
import com.mercedes_benz.qa.ui.pom.components.cookie.CookiePom;
import com.mercedes_benz.qa.ui.pom.components.form.FormPom;
import com.mercedes_benz.qa.ui.selenium.DriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.Browser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static com.mercedes_benz.qa.ui.selenium.BrowserType.parseBrowser;

@Owner("Sebastião Rusu")
@Epic("Automation")
@Feature("Web")
@Story("This test is to fill the form")
class ExcellentTest {

    private DriverManager driverManager;
    private Form form;

    @TmsLink("Sebastião Rusu")
    @DisplayName("Setup Test")
    @Description("Setup the test")
    @BeforeEach
    void setUp() throws JsonProcessingException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("Data.json");
        StringBuilder jsonString = new StringBuilder();
        try (InputStreamReader streamReader =
                     new InputStreamReader(is, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();

        form = objectMapper.readValue(jsonString.toString(), Form.class);

        String browserName = System.getenv("BROWSER_NAME");
        if (browserName == null) {
            throw new IllegalArgumentException("Environment variable BROWSER_NAME is not set.");
        }
        Browser browser = parseBrowser(browserName);
        try {
            driverManager = new DriverManager(browser);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Web Test - Fill Form")
    @Description("Test accept cookies, fill the form and confirm it")
    void testHomePage() {
        MbPage mbPage = new MbPage(driverManager.getDriver());
        mbPage.get();
        CookiePom cookiePom = mbPage.getCookie();
        cookiePom.acceptCookies();
        Assertions.assertTrue(cookiePom.isCookieClosed());

        FormPom formPom = mbPage.getForm();
        formPom.fillForm(form);
        Assertions.assertTrue(formPom.isFormClosed());
    }

    @AfterEach
    void tearDown() {
        driverManager.close();
    }
}