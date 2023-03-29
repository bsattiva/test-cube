package com.page;

import com.context.Context;
import com.utils.FileHelper;
import com.utils.TestHelper;
import com.utils.data.HttpClient;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PageHelper {
    private static final Logger LOGGER = Logger.getLogger(PageHelper.class);

    public static JSONObject getPageObject() {
        var pages = new JSONObject();
        try {
            pages = new JSONObject(FileUtils
                    .readFileToString(new File(FileHelper.getUserDir()
                            + "/src/test/resources/pages.json"), StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pages;
    }

    protected static JSONObject getPageObject(final String name) {
        TestHelper.waitAjax();
        var html = Context.getDriver().findElement(By.tagName("body")).getAttribute("outerHTML");
        var body = new JSONObject();
        body.put("pageName", name);

        body.put("html", html);
        body.put("project", Context.getVars().getProject());
        body.put("url", Context.getDriver().getCurrentUrl());
        var url = FileHelper.getUrl("manager.url");

        if (url.startsWith("https")) {
            return HttpClient.sendHttpsPost(body, url);
        } else {
            return HttpClient.sendHttpPost(body, url);
        }

    }

}
