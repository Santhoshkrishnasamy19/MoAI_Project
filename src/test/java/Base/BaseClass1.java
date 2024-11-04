package Base;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseClass1
{
    public AppiumDriverLocalService Service;
    public static AndroidDriver driver;
    public static WebDriverWait WAIT;

    @BeforeMethod
    public void ConfigureAppium() throws MalformedURLException
    {
        System.out.println("Server starts running from Base Class.");

        //Run server automatically
        Service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:/Users/santhosh/AppData/Roaming/npm/node_modules/appium/build/lib/main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).build();

        Service.start();

        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName("Medium Phone API 35");

        options.setApp(System.getProperty("user.dir") +"\\src\\test\\java\\Resourse\\app-debug-aug1-changes.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
    }

    @AfterMethod
    public void StopConfigureAppium()
    {
        driver.quit();
        Service.stop();
        System.out.println("Server stops running from Base Class");
    }

    @Test
    public void BaseLogin() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Clicking the Get started button
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/txtGetStart"))).click();

        //clicking the mobile number input field and send the keys to it.
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id
                ("com.moai.android:id/edtMobileNumber"))).sendKeys("0000000000");

        //Clicking the continue button
        wait.until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.moai.android:id/txtContinue"))).click();

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
