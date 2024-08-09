package com.mercedes_benz.qa.ui.pom.components.form;

import com.mercedes_benz.qa.ui.data.Form;
import com.mercedes_benz.qa.ui.data.Purpose;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class FormPom {


    public static final String MAIN_LOCATOR = "[data-test-id='modal-popup__location']";
    private static final By STATE_SELECT = By.cssSelector("select");
    private static final By POSTAL_CODE_INPUT = By.cssSelector("input");
    private static final By MODAL_DIALOG_WRAPPER = By.cssSelector(".wb-modal-dialog-wrapper");
    private static final By CONFIRM_BUTTON = By.cssSelector("[data-test-id='state-selected-modal__close']");

    private final WebDriver driver;
    private final WebDriverWait wait;
    private final WebElement mainElement;
    private WebElement selectElement;
    private WebElement postalCodeElement;
    private WebElement privateCheckbox;
    private WebElement businessCheckbox;
    private WebElement confirmButton;

    public FormPom(WebElement element, WebDriverWait wait, WebDriver driver) {
        mainElement = element;
        this.driver = driver;
        this.wait = wait;
        getElements();
    }

    private void getElements() {
        selectElement = mainElement.findElement(STATE_SELECT);
        postalCodeElement = mainElement.findElement(POSTAL_CODE_INPUT);
        privateCheckbox = mainElement.findElement(By.cssSelector(String.format("[value='%s']", Purpose.PRIVATE.getValue())));
        businessCheckbox = mainElement.findElement(By.cssSelector(String.format("[value='%s']", Purpose.BUSINESS.getValue())));
        confirmButton = mainElement.findElement(CONFIRM_BUTTON);
    }

    public void fillForm(Form form) {
        stateSelect(form.getStateLoc());
        postalCodeFill(form.getPostalCode());
        purposeCheck(form.getPurposeValue());
        confirmFill();
    }

    private void stateSelect(final String state) {
        Select select = new Select(selectElement);
        select.selectByVisibleText(state);
    }

    private void postalCodeFill(final String postalCode) {
        postalCodeElement.sendKeys(postalCode);
    }

    private void purposeCheck(final Purpose purpose) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", purpose.equals(Purpose.PRIVATE) ? privateCheckbox : businessCheckbox);
    }

    private void confirmFill() {
        confirmButton.click();
    }

    public boolean isFormClosed() {
       wait.until(ExpectedConditions.not(ExpectedConditions.attributeContains(MODAL_DIALOG_WRAPPER, "style", "display: visible;")));
       return true;
    }
}
