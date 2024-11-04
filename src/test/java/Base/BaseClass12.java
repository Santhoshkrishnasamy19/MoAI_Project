package Base;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.testng.annotations.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class BaseClass12
{
    public AppiumDriverLocalService Service;
    public AndroidDriver driver;


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

        options.setApp(System.getProperty("user.dir") +"\\src\\test\\java\\Resourse\\Coral_9Aug2024.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
    }


    @AfterMethod
    public void StopConfigureAppium()
    {
        driver.quit();
        Service.stop();
        System.out.println("Server stops running from Base Class");
    }

}
