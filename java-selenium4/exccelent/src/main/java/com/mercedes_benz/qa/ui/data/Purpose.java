package com.mercedes_benz.qa.ui.data;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Purpose {
    /**
     * The purpose it's to choose the {@link Purpose} purpose of the form.
     */
    PRIVATE("P"), BUSINESS("B");
    /**
     * The value of the purpose.
     */
    @JsonValue
    private final String value;

    /**
     * Constructor.
     *
     * @param purposeValue the value of the purpose.
     */
    Purpose(final String purposeValue) {
        this.value = purposeValue;
    }

}
