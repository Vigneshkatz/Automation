import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

public class SignUpTest {
    private static final long LIMIT = 10000000000L;
    private static final int RANDOMNUMBER = ThreadLocalRandom.current().nextInt(0, 2);

    private static final String GROUPINVITECODE = "CHcHXt";
    private static  long last = 0;
    private AndroidDriver driver;
    private WebDriverWait wait;

    @Before
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

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void SignUp() throws InterruptedException {
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
        mobileInput.sendKeys(String.valueOf(getId()));
        Thread.sleep(1000);
        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        Assert.assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
        Thread.sleep(2000);
//        select male
        String maleElementId = "com.app.smytten.debug:id/tv_male";
        String femaleElementId = "com.app.smytten.debug:id/tv_female";
        String selectedGenderId = (RANDOMNUMBER == 0) ? maleElementId : femaleElementId;
        WebElement chooseGender = driver.findElement(AppiumBy.id(selectedGenderId));
        chooseGender.click();
        Thread.sleep(1000);
//        choose gender

        WebElement chooseMonth = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/month_spinner"));
        chooseMonth.click();
        Thread.sleep(1000);
        WebElement selectMonth = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='March']"));
        selectMonth.click();
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
//        Choose year
        WebElement chooseYear = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/year_spinner"));
        chooseYear.click();
        Thread.sleep(1000);
        WebElement selectYear = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='2009']"));
        selectYear.click();
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
//        apply referal code
        WebElement referralInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_referral"));
        referralInput.click();
        referralInput.sendKeys(GROUPINVITECODE);
        WebElement referralApply = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_referral_apply"));
        referralApply.click();
        WebElement referralSuccessTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_refer_success"));
        System.out.println(referralSuccessTitle.getText());
        WebElement referralSuccessPaymentTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_payment_title"));
        System.out.println(referralSuccessPaymentTitle);
        Thread.sleep(1000);
        WebElement confirmBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/signup_manual"));
        confirmBtn.click();
        Thread.sleep(5000);
    }

    private static long getId(){

        long id = System.currentTimeMillis() % LIMIT;
        if ( id <= last ) {
            id = (last + 1) % LIMIT;
        }
        return last = id;
    }
}
