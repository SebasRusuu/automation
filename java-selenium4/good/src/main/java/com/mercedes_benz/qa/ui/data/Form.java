package com.mercedes_benz.qa.ui.data;

public class Form {
    private final String postalCode;
    private final String stateLoc;
    private final Purpose purposeValue;

    public Form(String postalCode, String stateLoc, Purpose purposeValue) {
        this.postalCode = postalCode;
        this.stateLoc = stateLoc;
        this.purposeValue = purposeValue;
    }

    public Purpose getPurposeValue() {
        return purposeValue;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getStateLoc() {
        return stateLoc;
    }

}
