package com.mercedes_benz.qa.ui.pom.components;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPom {
    private final WebDriver driver;

    private final static String STATE_SELECT = "[data-test-id='modal-popup__location'] select";
    private final static String POSTAL_CODE_INPUT = "[data-test-id='modal-popup__location'] input";
    private final static String PURPOSE_CHECKBOX = "[value='%s']";
    private final static String CONFIRM_BUTTON = "button[data-test-id='state-selected-modal__close']";
    private final static String MODAL_DIALOG_WRAPPER = "[data-test-id='modal-popup__location'] .wb-modal-dialog-wrapper";

    public FormPom(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(){
        stateSelect("New South Wales");
        postalCodeFill("2007");
        purposeCheck("P");
        confirmFill();
    }

    private void stateSelect(final String state) {
        WebElement selectElement = driver.findElement(By.cssSelector(STATE_SELECT));
        Select select = new Select(selectElement);
        select.selectByVisibleText(state);
    }

    private void postalCodeFill(final String postalCode) {
        WebElement keysElement = driver.findElement(By.cssSelector(POSTAL_CODE_INPUT));
        keysElement.sendKeys(postalCode);
    }

    private void purposeCheck(final String purpose) {
        WebElement checkboxElement = driver.findElement(By.cssSelector(String.format(PURPOSE_CHECKBOX, purpose)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkboxElement);
    }

    private void confirmFill() {
        WebElement confirmElement = driver.findElement(By.cssSelector(CONFIRM_BUTTON));
        confirmElement.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(By.cssSelector(MODAL_DIALOG_WRAPPER), "style", "display: none;"));
    }

    public boolean isFormClosed() {
        return driver.findElement(By.cssSelector(MODAL_DIALOG_WRAPPER)).getDomAttribute("style").equals("display: none;");
    }
}
