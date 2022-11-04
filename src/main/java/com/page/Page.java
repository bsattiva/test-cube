package com.page;

import com.context.Context;
import com.utils.FileHelper;
import com.utils.TestHelper;
import com.utils.data.HttpClient;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class Page {
    @Getter @Setter
    private String name;

    private static final JSONObject CONFIGURATION = PageHelper.getPageObject();
    @Getter
    private JSONObject pageObject;

    public Page(final String name) {
        this.name = name;
        populate();
    }

    public static String getPageUrl(final String page) {
        return Context.getVars().getBaseUrl();
    }

    private static JSONObject getPageObject(final String pageName) {
        for (var i = 0; i < CONFIGURATION.getJSONArray("pages").length(); i++) {
            var item = CONFIGURATION.getJSONArray("pages").getJSONObject(i);
            if (CONFIGURATION.getJSONArray("pages").getJSONObject(i).getString("pageName").equals(pageName)) {
                return CONFIGURATION.getJSONArray("pages").getJSONObject(i);
            }
        }
        return null;
    }

    private void populate() {
        if (getPageObject(name) != null) {
            pageObject = getPageObject(name);
        } else {
            var html = Context.getDriver().findElement(By.tagName("html")).getAttribute("outerHTML");
            var body = new JSONObject();
            body.put("pageName", name);

            body.put("html", html);
            body.put("project", Context.getVars().getProject());
            body.put("url", Context.getDriver().getCurrentUrl());
            var url = FileHelper.getUrl("manager.url");

            if (url.startsWith("https")) {
                pageObject = HttpClient.sendHttpsPost(body, url);
            } else {
                pageObject = HttpClient.sendHttpPost(body, url);
            }
            pageObject.put("project", Context.getVars().getProject());

        }

    }

    public static WebElement getWebElement(final By locator) {
        return new WebDriverWait(Context.getDriver(),
                Duration.ofSeconds(45)).until(ExpectedConditions.elementToBeClickable(locator));
    }

}
