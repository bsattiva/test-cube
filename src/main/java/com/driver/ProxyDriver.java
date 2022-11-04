package com.driver;

//import com.util.Helper;
//import io.github.bonigarcia.wdm.WebDriverManager;
//import net.lightbody.bmp.BrowserMobProxyServer;
//import net.lightbody.bmp.client.ClientUtil;
//import net.lightbody.bmp.proxy.CaptureType;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.CapabilityType;
//import com.util.ContextManager;
//import org.openqa.selenium.remote.RemoteWebDriver;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//final class ProxyDriver {
//    private static final int FIVE = 5;
//    private ProxyDriver() {
//
//    }
//
//    public static WebDriver getRemoteProxyDriver() throws MalformedURLException {
//        var proxy = new BrowserMobProxyServer();
//        proxy.setHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_HEADERS);
//        proxy.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_HEADERS);
//        System.setProperty("bmp.allowNativeDnsFallback", "true");
//        System.setProperty("java.net.preferIPv4Stack", "true");
//
//        proxy.start();
//
//        var seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
//        seleniumProxy.setSslProxy("trustAllSSLCertificates");
//
//        seleniumProxy.setHttpProxy("localhost:" + proxy.getPort());
//        seleniumProxy.setSslProxy("localhost:" + proxy.getPort());
//
//        WebDriverManager.chromedriver().setup();
//
//        var options = new ChromeOptions();
//        options.addArguments("--ignore-certificate-errors");
//        options.addArguments("--headless");
//        options.addArguments("--no-sandbox");
//        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        options.setCapability(CapabilityType.PROXY, seleniumProxy);
//        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
//
//        var webDriver = new RemoteWebDriver(new URL(Helper.getRemoteUrl("remote:4444")), options);
//
//        var id = Helper.generateRandom(FIVE);
//        proxy.newHar(id);
//        System.setProperty("proxyId", id);
//
//
//        var har = proxy.getHar();
//        ContextManager.setProxy(proxy);
//
//        ContextManager.setHar(har);
//        return webDriver;
//    }
//
//    public static WebDriver getProxyDriver() {
//        var proxy = new BrowserMobProxyServer();
//        proxy.setHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_HEADERS);
//        proxy.enableHarCaptureTypes(CaptureType.REQUEST_HEADERS, CaptureType.RESPONSE_HEADERS);
//        System.setProperty("bmp.allowNativeDnsFallback", "true");
//        System.setProperty("java.net.preferIPv4Stack", "true");
//
//        proxy.start();
//
//        var seleniumProxy = ClientUtil.createSeleniumProxy(proxy);
//        seleniumProxy.setSslProxy("trustAllSSLCertificates");
//
//        seleniumProxy.setHttpProxy("localhost:" + proxy.getPort());
//        seleniumProxy.setSslProxy("localhost:" + proxy.getPort());
//
//        WebDriverManager.chromedriver().setup();
//
//        var options = new ChromeOptions();
//        options.addArguments("--ignore-certificate-errors");
//        options.addArguments("--headless");
//        options.addArguments("--no-sandbox");
//        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//        options.setCapability(CapabilityType.PROXY, seleniumProxy);
//        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
//
//        var webDriver = new ChromeDriver(options);
//
//        var id = Helper.generateRandom(FIVE);
//        proxy.newHar(id);
//        System.setProperty("proxyId", id);
//
//
//        var har = proxy.getHar();
//        ContextManager.setProxy(proxy);
//
//        ContextManager.setHar(har);
//        return webDriver;
//    }
//}
