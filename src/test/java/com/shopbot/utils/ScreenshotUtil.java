package com.shopbot.utils;

import com.shopbot.driver.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ScreenshotUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

    private ScreenshotUtil() {
    }

    public static String capture(String testName) {
        File source = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
        Path screenshotDirectory = Path.of("screenshots");
        String safeTestName = testName.replaceAll("[^a-zA-Z0-9._-]", "_");
        Path destination = screenshotDirectory.resolve(safeTestName + "_" + LocalDateTime.now().format(FORMATTER) + ".png");

        try {
            Files.createDirectories(screenshotDirectory);
            Files.copy(source.toPath(), destination);
            return destination.toAbsolutePath().toString();
        } catch (IOException exception) {
            throw new RuntimeException("Unable to save screenshot", exception);
        }
    }
}
