import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.testng.assertNotEquals;

public class SignUpTest {
    private static final long LIMIT = 10000000000L;
    private static final int RANDOMNUMBER = ThreadLocalRandom.current().nextInt(0, 2);

    private static final String GROUPINVITECODE = "CHcHXt";
    private static  long last = 0;
    private AndroidDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() throws MalformedURLException, InterruptedException {
        startAppiumServer();
        DesiredCapabilities caps = new DesiredCapabilities();
//        oneplus
        caps.setCapability("deviceName", "OnePlus LE2111");
        caps.setCapability("platformVersion", "14");
        caps.setCapability("platformName", "Android");
//        realme
//        caps.setCapability("deviceName", "realme RMX1971");
//        caps.setCapability("platformVersion", "11");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("enforceAppInstall", true);
        caps.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-debug (1).apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        assertNotEquals(driver, null);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        assertNotEquals(wait, null);
        Thread.sleep(5000);
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        stopAppiumServer();
    }
    private void startAppiumServer() {
        try {
            Runtime.getRuntime().exec("appium");
            TimeUnit.SECONDS.sleep(10); // Wait for Appium server to start (adjust as necessary)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopAppiumServer() {
        try {
            Runtime.getRuntime().exec("pkill -9 node");
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void signUp() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        mobileInput.sendKeys(String.valueOf(getNumber()));
        Thread.sleep(1000);
        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
        Thread.sleep(5000);
//        select gender
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

        WebElement chooseYear = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/year_spinner"));
        chooseYear.click();
        Thread.sleep(1000);
        WebElement selectYear = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='2009']"));
        selectYear.click();

        WebElement referralInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_referral"));
        referralInput.click();
        referralInput.sendKeys(GROUPINVITECODE);

        WebElement referralApply = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_referral_apply"));
        referralApply.click();
        WebElement referralSuccessTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_refer_success"));

        System.out.println(referralSuccessTitle.getText());
        WebElement referralSuccessPaymentTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_payment_title"));

        System.out.println(referralSuccessPaymentTitle.getText());
        Thread.sleep(1000);

        WebElement confirmBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/signup_manual"));
        confirmBtn.click();
        Thread.sleep(5000);
    }

    private static long getNumber(){

        long id = System.currentTimeMillis() % LIMIT;
        if ( id <= last ) {
            id = (last + 1) % LIMIT;
        }
        return last = id;
    }
}
