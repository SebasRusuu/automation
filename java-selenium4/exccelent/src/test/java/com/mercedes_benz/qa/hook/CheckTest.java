package com.mercedes_benz.qa.hook;

import com.mercedes_benz.qa.ui.selenium.DriverManager;

public class CheckTest {
    private DriverManager driverManager;

    public CheckTest(DriverManager driverManager) {
        this.driverManager = driverManager;
    }

    public class TestWatcher {
        private DriverManager driverManager;

        public TestWatcher(DriverManager driverManager) {
            this.driverManager = driverManager;
        }

        public void testFailed() {
            driverManager.getDriver().quit();
        }
    }
}