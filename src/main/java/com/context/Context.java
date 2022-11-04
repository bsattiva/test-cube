package com.context;

import com.page.Page;
import com.utils.Vars;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class Context {

    private static final ThreadLocal<WebDriver>  DRIVER = new ThreadLocal<>();
    private static final ThreadLocal<Vars> VARS = new ThreadLocal<>();

    public static void setDriver(final WebDriver driver) {
        DRIVER.set(driver);
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void unload() {
        if (getDriver() != null) {
        getDriver().quit();
            setDriver(null);
        }
    }

    public static void setVars(final Vars vars) {
        VARS.set(vars);
    }

    public static Vars getVars() {
        if (VARS.get() ==null || !VARS.get().isInited()) {
            setVars(new Vars());
        }
        return VARS.get();
    }

}
