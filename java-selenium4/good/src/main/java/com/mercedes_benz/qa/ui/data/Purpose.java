package com.mercedes_benz.qa.ui.data;

public enum Purpose {
    PRIVATE("P")
    , BUSINESS("B");

    private final String value;

    Purpose(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
