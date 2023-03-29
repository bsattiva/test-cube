package com.utils;

import com.context.Context;
import com.google.errorprone.annotations.Var;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class TestHelper {
    private static final String SELECTOR = "selector";
    private static final Logger LOGGER = Logger.getLogger(TestHelper.class);

    public static void setCurrentStepId() {
        var current = Context.getVars().getCurrentStepId();
        var id = (Helper.isThing(current))
                ? Integer.toString(Integer.parseInt(current) + 1)
                : "0";
        Context.getVars().setCurrentStepId(id);
    }

    public static By getLocator(final JSONObject field) {
        By locator = null;
        switch (field.getString("type")){
            case "selector":
                locator = By.cssSelector(field.getString(SELECTOR));
                break;
            case "xpath":
                locator = By.xpath(field.getString(SELECTOR));
                break;
            case "id":
                locator = By.id(field.getString(SELECTOR));
                break;

        }
        return locator;
    }

    public static void sleep(final int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }

    }
    public static void waitAjax() {
        var jQuery = 1;
        var countJq = 0;

        var jsQuery = "";
        var countJs = 0;

        while (jQuery > 0 && countJq < 300) {
            try {
                jQuery = (int) ((JavascriptExecutor) Context.getDriver()).executeScript("return jQuery.active");
            } catch (org.openqa.selenium.JavascriptException e) {
                LOGGER.error(e.getMessage());
                jQuery = 0;
            }
            countJq++;
            sleep(100);
        }

        while (!jsQuery.equals("complete") && countJs < 300) {
            jsQuery = ((JavascriptExecutor) Context.getDriver()).executeScript("return document.readyState")
                    .toString();
            countJs++;
            sleep(300);
        }

    }
}
