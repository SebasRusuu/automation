package com.mercedes_benz.qa.ui.data;

import lombok.Data;

@Data
public class Form {
    /**
     * Postal code.
     */
    private String postalCode;
    /**
     * State location.
     */
    private String stateLoc;
    /**
     * The purpose it's to choose the {@link Purpose} purpose of the form.
     */
    private Purpose purposeValue;

}
