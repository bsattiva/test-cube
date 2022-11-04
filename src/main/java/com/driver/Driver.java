package com.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;


public class Driver {

    public static Logger LOGGER = Logger.getLogger(Driver.class);
    public static WebDriver getDriver(final String browser) {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        var chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        chromeOptions.addArguments("--lang=en_us");

        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        //chromeOptions.setCapability("goog:loggingPrefs", logPrefs);
        WebDriver driver = null;
        WebDriverManager.chromedriver().setup();
        switch (browser.toUpperCase()) {
            case "CHROME":
                driver = new ChromeDriver(chromeOptions);
                break;
            case "REMOTE":
                chromeOptions.addArguments(("--window-size=1920,1080"));
                driver = getRemoteDriver(getRemoteUrl(getHostname() + ":4444"), chromeOptions);
                break;
            case "HEADLESS":
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;

            default:
                throw new AssertionError("Unsupported browser: " + browser);
        }


        return driver;
    }

    private static String getHostname() {

        return "109.228.57.213";
//        if (Helper.isThing(System.getProperty("fake"))) {
//            return "localhost";
//        } else {
//            return "remote";
//        }
    }

    public static WebDriver getRemoteDriver(final String url, final ChromeOptions options) {

        WebDriver driver = null;

        try {
            LOGGER.info("STARTING with url: " + url);
                driver = new RemoteWebDriver(new URL(url), options);
            LOGGER.info("DRIVER STARTED");
        } catch (MalformedURLException e) {
            LOGGER.error("COULDNT CREATE REMOTE DRIVER: " + e.getLocalizedMessage());
        }

        return driver;
    }

    public static String getRemoteUrl(final String hostname) {
        return "http://?/wd/hub/".replace("?", hostname);
    }

}
