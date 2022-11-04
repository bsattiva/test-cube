package com.page;

import com.utils.FileHelper;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

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
}
