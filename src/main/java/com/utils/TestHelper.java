package com.utils;

import org.json.JSONObject;
import org.openqa.selenium.By;

public class TestHelper {
    private static final String SELECTOR = "selector";

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
}
