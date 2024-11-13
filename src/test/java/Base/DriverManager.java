package Base;

import browserstack.shaded.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Map;


public class DriverManager {

    private static final ThreadLocal<AppiumDriver> appiumDriverThreadLocal = new ThreadLocal<>();
    public static AndroidDriver driver; // Declare the driver variable

    protected static AppiumDriver getDriver() {
        return appiumDriverThreadLocal.get();
    }

    public void setDriver(AndroidDriver driver) {
        appiumDriverThreadLocal.set(driver); // Set the ThreadLocal variable
        DriverManager.driver = driver; // Assign to the static driver variable
    }


    @BeforeMethod
    public void initializeDriver() throws Exception {
        String Username = "santhoshk_VZPbNn";
        String accessKey = "zyXyvqNw85jUwxxAnNKu";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Samsung Galaxy S10");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", "bs://9dcacc503f8abbfdcba9a0a247d108b6d744a6fd");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "com.moai.android");

        String urlString = String.format("https://%s:%s@hub-cloud.browserstack.com/wd/hub", Username, accessKey);
        URL url = new URL(urlString);
        AndroidDriver driver = new AndroidDriver(url, capabilities);
        setDriver(driver);
    }

    @AfterMethod
    public void quitDriver() {
        AppiumDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            appiumDriverThreadLocal.remove();
        }
    }

     /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>iOS>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


   /* private static final ThreadLocal<AppiumDriver> appiumDriverThreadLocal = new ThreadLocal<>();
    public static IOSDriver driver; // Declare the driver variable for iOS

    protected static AppiumDriver getDriver() {
        return appiumDriverThreadLocal.get();
    }

    public void setDriver(IOSDriver driver) {
        appiumDriverThreadLocal.set(driver); // Set the ThreadLocal variable
        DriverManager.driver = driver; // Assign to the static driver variable
    }

    @BeforeMethod
    public void initializeDriver() throws Exception {
        String Username = "santhoshk_VZPbNn";
        String accessKey = "zyXyvqNw85jUwxxAnNKu";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "iPhone 14 Pro Max");
        capabilities.setCapability("platformVersion", "16.0"); // Ensure this matches your app's requirements
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("app", "bs://2dc8f54d5cdfc638c305084357e85736c2a0b50e");
        capabilities.setCapability("automationName", "XCUITest"); // Set your app's bundle ID

        String urlString = String.format("https://%s:%s@hub-cloud.browserstack.com/wd/hub", Username, accessKey);
        URL url = new URL(urlString);
        IOSDriver driver = new IOSDriver(url, capabilities); // Use IOSDriver instead of AndroidDriver
        setDriver(driver);
    }

    @AfterMethod
    public void quitDriver() {
        AppiumDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            appiumDriverThreadLocal.remove();
        }
    }*/

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>iOS_End>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/


    /*private static final ThreadLocal<AppiumDriver> appiumDriverThreadLocal = new ThreadLocal<>();
    public static AppiumDriver driver;

    protected static AppiumDriver getDriver() {
        return appiumDriverThreadLocal.get();
    }

    public void setDriver(AppiumDriver driver) {
        appiumDriverThreadLocal.set(driver);
        DriverManager.driver = driver;
    }

    @BeforeMethod
    @Parameters("deviceType")
    public void initializeDriver(ITestContext context) throws Exception
    {
        String deviceType = context.getCurrentXmlTest().getParameter("deviceType");

        String Username = "santhoshk_VZPbNn";
        String accessKey = "zyXyvqNw85jUwxxAnNKu";

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Map<String, Object>> config = objectMapper.readValue(new File("/home/santhosh/RealProjects/AppiumAndroidTesting (1)/AppiumAndroidTesting/src/test/java/Resourse/device_config.json"), Map.class);

        // Ensure deviceConfig is retrieved as a Map<String, Object>
        Map<String, Object> deviceConfig = (Map<String, Object>) config.get("devices").get(deviceType);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        for (Map.Entry<String, Object> entry : deviceConfig.entrySet()) {
            capabilities.setCapability(entry.getKey(), entry.getValue()); // No casting needed
        }

        String urlString = String.format("https://%s:%s@hub-cloud.browserstack.com/wd/hub", Username, accessKey);
        URL url = new URL(urlString);

        AppiumDriver driver;
        if ("iOS".equalsIgnoreCase(deviceType)) {
            driver = new IOSDriver(url, capabilities);
        } else if ("Android".equalsIgnoreCase(deviceType)) {
            driver = new AndroidDriver(url, capabilities);
        } else {
            throw new IllegalArgumentException("Invalid device type: " + deviceType);
        }

        setDriver(driver);
    }

    @AfterMethod
    public void quitDriver() {
        AppiumDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            appiumDriverThreadLocal.remove();
        }
    }*/

    /*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>End>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

    @Test
    public void BaseLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Clicking the Get started button
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/txtGetStart"))).click();

        //clicking the mobile number input field and send the keys to it.
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id
                ("com.moai.android:id/edtMobileNumber"))).sendKeys("0000000000");

        //Clicking the continue button
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.moai.android:id/txtContinue"))).click();

        //Fill the OTP into input field.
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/editTextOTP1"))).sendKeys("1");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/editTextOTP2"))).sendKeys("2");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/editTextOTP3"))).sendKeys("3");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/editTextOTP4"))).sendKeys("4");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/editTextOTP5"))).sendKeys("5");
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/editTextOTP6"))).sendKeys("6");

        //Verify the OTP
        wait.until(ExpectedConditions.elementToBeClickable(AppiumBy.id("com.moai.android:id/txtVerify"))).click();
    }
}
