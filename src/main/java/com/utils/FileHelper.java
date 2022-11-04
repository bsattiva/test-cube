package com.utils;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FileHelper {
   private static final Logger LOGGER = Logger.getLogger(FileHelper.class);
    private static final String MASK = "?";

    public static String getUserDir() {
        return System.getProperty("user.dir");
    }

    public static String getPropertyFromFile(final String file, final String key) {
        var properties = new Properties();
        String value = null;
        try {
            properties.load(new FileInputStream(getUserDir() + "/src/test/resources/" + file));
            value = properties.getProperty(key);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return value;
    }


    public static String getUrl(final String key) {
        return getPropertyFromFile("config.properties", key);
    }

    public static File getFileFromResources(final String fileName, final boolean test) {
        var path = getUserDir() + "/src/?/resources/" + fileName;
        var folder = (test) ? "test" : "main";
        return new File(path.replace(MASK, folder));
    }

    public static void appendPageToFile(final JSONObject object) {
        var obj = new JSONObject();
        try {
            obj = new JSONObject(FileUtils.readFileToString(getFileFromResources("pages.json", true),
                    StandardCharsets.UTF_8));
            obj.getJSONArray("pages").put(object);
            FileUtils.write(getFileFromResources("pages.json", true),
                    obj.toString(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

}
