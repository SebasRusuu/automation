package com.mercedes_benz.qa.ui.data;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Purpose {
    PRIVATE("P"), BUSINESS("B");
    @JsonValue
    private final String value;

    Purpose(String value) {
        this.value = value;
    }
}
