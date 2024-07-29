package com.mercedes_benz.qa.ui.pom.components;

import com.mercedes_benz.qa.ui.data.Form;
import com.mercedes_benz.qa.ui.data.Purpose;
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

    private static final String MAIN_LOCATOR = "[data-test-id='modal-popup__location']";
    private static final  By STATE_SELECT = By.cssSelector(MAIN_LOCATOR +" select");
    private static final  By POSTAL_CODE_INPUT = By.cssSelector(MAIN_LOCATOR + " input");
    private static final  By MODAL_DIALOG_WRAPPER = By.cssSelector(MAIN_LOCATOR + " .wb-modal-dialog-wrapper");
    private static final  By CONFIRM_BUTTON = By.cssSelector("button[data-test-id='state-selected-modal__close']");

    public FormPom(WebDriver driver) {
        this.driver = driver;
    }

    public void fillForm(Form form){
        stateSelect(form.getStateLoc());
        postalCodeFill(form.getPostalCode());
        purposeCheck(form.getPurposeValue());
        confirmFill();
    }

    private void stateSelect(final String state) {
        WebElement selectElement = driver.findElement(STATE_SELECT);
        Select select = new Select(selectElement);
        select.selectByVisibleText(state);
    }

    private void postalCodeFill(final String postalCode) {
        WebElement keysElement = driver.findElement(POSTAL_CODE_INPUT);
        keysElement.sendKeys(postalCode);
    }

    private void purposeCheck(final Purpose purpose) {
        var by  = By.cssSelector(String.format("[value='%s']", purpose.value()));
        WebElement checkboxElement = driver.findElement(by);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkboxElement);
    }

    private void confirmFill() {
        WebElement confirmElement = driver.findElement(CONFIRM_BUTTON);
        confirmElement.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(MODAL_DIALOG_WRAPPER, "style", "display: none;"));
    }

    public boolean isFormClosed() {
        return driver.findElement(MODAL_DIALOG_WRAPPER).getAttribute("style").equals("display: none;");
    }
}
