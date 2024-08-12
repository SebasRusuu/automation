package com.mercedes_benz.qa.ui.pom.components.form;

import com.mercedes_benz.qa.ui.data.Form;
import com.mercedes_benz.qa.ui.data.Purpose;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * This class represents the Form component in the Page Object Model (POM) framework.
 * It provides methods to interact with and fill out the form.
 */
public class FormPom {
    /**
     * Web element representing the state select dropdown.
     */
    @FindBy(css = "select")
    private WebElement stateSelect;

    /**
     * Web element representing the postal code input field.
     */
    @FindBy(css = "input")
    private WebElement postalCodeInput;

    /**
     * Web element representing the modal dialog wrapper.
     */
    @FindBy(css = ".wb-modal-dialog-wrapper")
    private WebElement modalDialogWrapper;

    /**
     * Web element representing the confirm button within the modal dialog.
     */
    @FindBy(css = "[data-test-id='state-selected-modal__close']")
    private WebElement confirmButton;

    /**
     * Web element representing the private purpose checkbox.
     */
    @FindBy(css = "[value='P']")
    private WebElement privateCheckbox;

    /**
     * The WebDriver instance used to interact with the web page.
     */
    private final WebDriver formDriver;

    /**
     * Web element representing the business purpose checkbox.
     */
    @FindBy(css = "[value='B']")
    private WebElement businessCheckbox;

    /**
     * Constructor for FormPom.
     *
     * @param driver the WebDriver instance to interact with the web page
     */
    public FormPom(final WebDriver driver) {
        this.formDriver = driver;
        getElements();
    }

    /**
     * Initializes web elements using the given WebDriver.
     */
    private void getElements() {
        PageFactory.initElements(formDriver, this);
    }

    /**
     * Fills out the form using the data from the provided Form object.
     *
     * @param form the Form object containing data to fill out the form
     */
    public void fillForm(final Form form) {
        stateSelect(form.getStateLoc());
        postalCodeFill(form.getPostalCode());
        purposeCheck(form.getPurposeValue());
        confirmFill();
    }

    /**
     * Selects a state from the dropdown.
     *
     * @param state the state to select
     */
    private void stateSelect(final String state) {
        Select select = new Select(stateSelect);
        select.selectByVisibleText(state);
    }

    /**
     * Fills the postal code input field.
     *
     * @param postalCode the postal code to enter
     */
    private void postalCodeFill(final String postalCode) {
        postalCodeInput.sendKeys(postalCode);
    }

    /**
     * Checks the appropriate purpose checkbox based on the provided purpose.
     *
     * @param purpose the purpose to select
     */
    private void purposeCheck(final Purpose purpose) {
        JavascriptExecutor js = (JavascriptExecutor) formDriver;
        js.executeScript("arguments[0].click();", purpose.equals(Purpose.PRIVATE) ? privateCheckbox : businessCheckbox);
    }

    /**
     * Clicks the confirm button to submit the form.
     */
    private void confirmFill() {
        confirmButton.click();
    }

    /**
     * Checks if the form modal dialog is closed.
     *
     * @return true if the modal dialog is closed, false otherwise
     */
    public boolean isFormClosed() {
        return modalDialogWrapper.getAttribute("style").contains("display: none;");
    }
}
