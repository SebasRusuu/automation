package com.mercedes_benz.qa.hook;

import com.mercedes_benz.qa.ui.selenium.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.TmsLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestWatcherHook implements TestWatcher {

    protected File screenshotFile;
    protected DriverManager driverManager;
    @RegisterExtension
    public final TestWatcher testWatcher = new TestWatch();

    protected static final Logger logger = Logger.getLogger(TestWatcherHook.class.getName());
    protected static File logFile;
    String browserType;

    static {
        try {
            logFile = new File("test-log.log");
            FileHandler fileHandler = new FileHandler(logFile.getAbsolutePath(), false); // Overwrite mode
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void log(String message) {
        logger.info(message);
    }

    @TmsLink("Sebastião Rusu")
    @DisplayName("Setup Test")
    @Description("Web Test - Fill Form")
    @BeforeEach
    void setUp() throws IOException {
        driverManager = new DriverManager();
        browserType = driverManager.getBrowserType();
        System.out.println("Setup Test");
    }

    class TestWatch implements TestWatcher {

        @Override
        public void testSuccessful(final ExtensionContext context) {
            if (driverManager.getDriver() != null) {
                System.out.println("Test successful: " + context.getDisplayName());
            }
            driverManager.close();
        }

        @Override
        public void testFailed(final ExtensionContext context, final Throwable cause) {
            try {
                takeScreenshot();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Test failed: " + context.getDisplayName() + " - " + cause.getMessage());
            driverManager.close();
        }

        @Override
        public void testAborted(final ExtensionContext context, final Throwable cause) {
            System.out.println("Test aborted: " + context.getDisplayName() + " - " + cause.getMessage());
            driverManager.close();
        }

        @Override
        public void testDisabled(final ExtensionContext context, final Optional<String> reason) {
            System.out.println("Test disabled: " + context.getDisplayName() + " - " + reason.orElse("No reason"));
            driverManager.close();
        }

        public void takeScreenshot() throws IOException {
            if (Objects.nonNull(driverManager)) {
                var takeScreenshot = (TakesScreenshot) driverManager.getDriver();
                screenshotFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
                Allure.addAttachment("Screenshot on fail(" + browserType + ")", new FileInputStream(screenshotFile));
            }
        }

    }
}
