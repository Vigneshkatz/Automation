import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LoginTest {
    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "OnePlus LE2111");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "13");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("enforceAppInstall", true);
        caps.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-debug (1).apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        Assert.assertNotEquals(driver, null);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertNotEquals(wait, null);

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void loginWithCrtOTP() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        Assert.assertNotEquals(touchAction, null);
        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        Assert.assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys("9500752205");
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        Assert.assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
    }

    @Test
    public void loginWithWrongOTP() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        Assert.assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        Assert.assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys("9500752205");
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        Assert.assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
    }
}
