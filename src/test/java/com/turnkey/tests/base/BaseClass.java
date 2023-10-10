package com.turnkey.tests.base;

import com.google.common.collect.ImmutableMap;
import com.turnkey.tests.utilities.ExtentManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.Test;
import turnkey.bbb;

public class BaseClass {

    public static AndroidDriver driver;
    public static Properties prop;
    public static URL url;
    public static DesiredCapabilities capabilities;

    //loadConfig method is to load the configuration
//    @BeforeSuite
    public void loadConfig() throws IOException {
        ExtentManager.setExtent();
        DOMConfigurator.configure("log4j2.xml");
//			PropertyConfigurator.configure("log4j.properties");

        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "/Configuration/Config.properties");
            prop.load(ip);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //app-turnkeyStaging.apk

//    @BeforeMethod
    @Test
    public void androidLaunchTest() throws InterruptedException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
       /*
        options.setPlatformName("Android");
        options.setPlatformVersion("13");
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);
        */
        options.setDeviceName("Pixel_XL");
        options.setApp(System.getProperty(("user.dir")+"/apps/app-turnkeyStaging.apk"));

        //driver = new AndroidDriver(new URL("http://192.168.0.108:4723/wd/hub"), options);
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), options);

        //local driver
	    //driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), options);
        Thread.sleep(10000);

    }

    @Test
    public void setupDriver() throws MalformedURLException {
        final String URL_STRING = "http://127.0.0.1:4723/wd/hub";
        url = new URL(URL_STRING);
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel_XL");
        final String dir = System.getProperty("user.dir");
        capabilities.setCapability(MobileCapabilityType.APP, dir + "/apps/app-turnkeyStaging.apk");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
        driver = new AndroidDriver(url, capabilities);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

    }

    public void scrollToEnd() {
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                    "left", 100, "top", 100, "width", 200, "height", 200,
                    "direction", "down",
                    "percent", 3.0
            ));

        } while(canScrollMore);
    }

    public void scrollToElement(String ele) {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"ele\"));"));
    }

    //perform swipe action
    public void swipeAction(WebElement ele, String swipeDirection) {

        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) ele).getId(),
                "direction", swipeDirection,
                "percent", 0.75
        ));
    }


//    @AfterMethod
    public void tearDown() {

        driver.quit();
        //service.stop();
    }


    @Test
    public void tata(){
       bbb b = new bbb();
        System.out.println(b);

    }

}
