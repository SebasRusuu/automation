package com.mercedes_benz.qa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercedes_benz.qa.ui.data.Form;
import com.mercedes_benz.qa.ui.pom.MbPage;
import com.mercedes_benz.qa.ui.pom.components.cookie.CookiePom;
import com.mercedes_benz.qa.ui.pom.components.form.FormPom;
import com.mercedes_benz.qa.ui.selenium.DriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.Browser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


import static com.mercedes_benz.qa.ui.selenium.BrowserType.parseBrowser;

class VeryGoodTest {

    private DriverManager driverManager;
    private Form form;

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
            browserName = System.getProperty("BROWSER_NAME");
        }
        if (browserName == null) {
            throw new IllegalArgumentException("Browser type cannot be null");
        }
        Browser browser = parseBrowser(browserName);
        driverManager = new DriverManager(browser);
    }

    @Test
    void testHomePage(){
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
