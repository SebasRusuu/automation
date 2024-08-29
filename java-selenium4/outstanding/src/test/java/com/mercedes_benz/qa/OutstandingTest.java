package com.mercedes_benz.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.mercedes_benz.qa.hook.TestWatcherHook;
import com.mercedes_benz.qa.ui.data.Form;
import com.mercedes_benz.qa.ui.pom.MbPage;
import com.mercedes_benz.qa.ui.pom.components.cookie.CookiePom;
import com.mercedes_benz.qa.ui.pom.components.form.FormPom;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;

@Owner("Sebastião Rusu")
@Epic("Automation")
@Feature("Web")
@Story("This test is to fill the form")
@DisplayName("Outstanding Test Allure")
class OutstandingTest extends TestWatcherHook {

    private Form form;
    private String countryCode;

    @TmsLink("Sebastião Rusu")
    @DisplayName("Setup Test")
    @BeforeEach
    void setUp() throws IOException {
        // Get countryCode from system properties, which is set by Maven command line
        countryCode = System.getProperty("countryCode", "en-au");
        log("Setting up the test for country: " + countryCode);
        RestAssured.baseURI = String.format("https://shop.mercedes-benz.com/%s/shop/vehicle/srp/demo", countryCode);
        given().
                when().get().
                then().statusCode(SC_OK);

        InputStream is = this.getClass().getClassLoader().getResourceAsStream("Data.json");
        StringBuilder jsonString = new StringBuilder();

        try (InputStreamReader streamReader = new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
        } catch (IOException e) {
            log("Error reading JSON file: " + e.getMessage());
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        form = objectMapper.readValue(jsonString.toString(), Form.class);
        log("Test setup complete");
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", driverManager.getBrowserType())
                        .put("Operation System", String.valueOf(((RemoteWebDriver) driverManager.getDriver()).getCapabilities().getPlatformName()))
                        .put("URL", RestAssured.baseURI)
                        .put("Country Code", countryCode)
                        .put("headless", String.valueOf(driverManager.getHeadless()))
                        .build(), System.getProperty("user.dir") + "/target/allure-results/"
        );

    }

    @Test
    @DisplayName("Web Test - Fill Form")
    @Description("Test accept cookies, fill the form and confirm it")
    @Step("StartUp")
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
}