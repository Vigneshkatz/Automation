import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;

public class AccountPageTest {
    private AndroidDriver driver;
    private WebDriverWait wait;
    @Before
    public void setUp() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
//        oneplus
        caps.setCapability("deviceName", "OnePlus LE2111");
        caps.setCapability("platformVersion", "14");
        caps.setCapability("platformName", "Android");
//        oneplus

        caps.setCapability("deviceName", "realme RMX1971");
        caps.setCapability("platformVersion", "11");
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("autoGrantPermissions", true);
        caps.setCapability("enforceAppInstall", true);
        caps.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-debug (1).apk");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
        Assert.assertNotEquals(driver, null);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Assert.assertNotEquals(wait, null);
        Thread.sleep(5000);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyBasicFields() throws InterruptedException {
        login();
        gotoAccountPage();
//        verifyBasicFieldsText();
//        openEditPage();
    }

    @Test
    public void openEditPage() throws InterruptedException {
        login();
        gotoAccountPage();
        WebElement editIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
        editIcon.click();
        updateProfile();
    }

    @Test
    public void verifyBasicFieldsText() throws InterruptedException {
        WebElement profileName = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
        WebElement editText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
        WebElement editIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
        WebElement trialPoints = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial_point"));
        WebElement walletBalance = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_shop_wallet_title"));
        WebElement bucksBalance = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_reward_title"));
        WebElement myOrders = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_header_1"));
        WebElement helpSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_header_2"));
        String userName = profileName.getText();
        System.out.println(userName);
        assertTrue(userName.equalsIgnoreCase("katziio"));
        String editTextValue = editText.getText();
        String trialPointsValue = trialPoints.getText();
        String walletBalanceValue = walletBalance.getText();
        String bucksBalanceValue = bucksBalance.getText();
        String myOrderValue = myOrders.getText();
        String helpSectionValue = helpSection.getText();
        assertTrue("Edit Profile Text", editTextValue.equalsIgnoreCase("edit profile"));
        assertTrue("Default Trial Balance",trialPointsValue.equalsIgnoreCase("6"));
        assertTrue("Default Wallet Balance",walletBalanceValue.equalsIgnoreCase("0"));
        assertTrue("Default Bucks Balance",bucksBalanceValue.equalsIgnoreCase("0"));
        assertTrue("My Order",myOrderValue.equalsIgnoreCase("my orders"));
        assertTrue("Help Section",helpSectionValue.equalsIgnoreCase("help"));
    }


    public void gotoAccountPage() throws InterruptedException {
        WebElement popUp = null;
        popUp = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_image"));
        if(popUp!= null)
        {
            TouchAction touchAction = new TouchAction(driver);
            Assert.assertNotEquals(touchAction, null);

            int xCoordinate = 355;
            int yCoordinate = 565;
            touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
            Thread.sleep(2000);
        }
       Thread.sleep(2000);
       WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/profile_home_tab"));
       mobileInput.click();
       Thread.sleep(2000);
    }

    @Test
    public void login() throws InterruptedException {
        TouchAction touchAction = new TouchAction(driver);
        Assert.assertNotEquals(touchAction, null);

        int xCoordinate = 355;
        int yCoordinate = 565;
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        Thread.sleep(2000);
        WebElement mobileInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_mobile"));
        Assert.assertNotEquals(mobileInput, null);
        System.out.println("Mobile input text: " + mobileInput.getText());
        mobileInput.click();
        Thread.sleep(2000);
        touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
        mobileInput.sendKeys("8610496028");
        Thread.sleep(1000);

        WebElement proceedBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/proceed"));
        Assert.assertNotEquals(proceedBtn, null);
        System.out.println("Proceed button text: " + proceedBtn.getText());
        proceedBtn.click();
    }

    @Test
    public void updateProfile() throws InterruptedException {
            updateName();
            updateEmail();
            updateGender();
            updateDOB();
            updatePincode();
            WebElement saveBtn = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/btn_proceed"));
            saveBtn.click();

    }

    private void updatePincode() {
        WebElement pincodeInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_pincode"));
        pincodeInput.click();
        pincodeInput.sendKeys("635115");

    }

    private void updateDOB() throws InterruptedException {
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
    }

    private void updateGender() {
        WebElement otherGender = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_others"));
        otherGender.click();
    }

    private void updateEmail() throws InterruptedException {
        String email =generateRandomEmail();
        WebElement emailInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_name"));
        emailInput.click();
        Thread.sleep(1000);
        emailInput.sendKeys(email);
    }

    private void updateName() throws InterruptedException {
        String name = "Not Guest User";
        WebElement nameInput = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/et_name"));
        nameInput.click();
        Thread.sleep(1000);
        nameInput.sendKeys(name);
    }

    public static String generateRandomEmail() {
        String uuid = UUID.randomUUID().toString();
        return "smytten" + uuid.substring(0, 8) + "@katziio.com";
    }

    @Test
    public void copyOtp() throws InterruptedException {
        String decodedOtp = null;
        Thread.sleep(2000);
        driver.openNotifications ();
        Thread.sleep(2000);
        WebElement clearNotification = null;
        try {
            clearNotification = driver.findElement(AppiumBy.id("com.android.systemui:id/clear_all_port"));
            clearNotification.click();
        }catch (Exception e)
        {
            TouchAction touchAction = new TouchAction(driver);
            int xCoordinate = 627;
            int yCoordinate = 1608;
            touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
            System.out.println("no new notification");
            Thread.sleep(2000);
        }
        login();
        try {
            Thread.sleep(20000);
            driver.openNotifications();
            Thread.sleep(2000);
            WebElement actionContainer = driver.findElement(AppiumBy.id("android:id/actions_container_layout"));
            WebElement copyAction  = driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Copy Verification Code\"]"));
            copyAction.click();
            System.out.println("copied otp");
            String otp = ((AndroidDriver) driver).getClipboard(ClipboardContentType.PLAINTEXT);
            System.out.println(otp);
            byte[] decodedBytes = Base64.getDecoder().decode(otp);
            String decodedText = new String(decodedBytes);
            System.out.println(decodedOtp);
            try {
                clearNotification = driver.findElement(AppiumBy.id("com.android.systemui:id/clear_all_port"));
                clearNotification.click();
            }catch (Exception e)
            {
                TouchAction touchAction = new TouchAction(driver);
                int xCoordinate = 627;
                int yCoordinate = 1608;
                touchAction.tap(PointOption.point(xCoordinate, yCoordinate)).perform();
                System.out.println("no new notification");
            }
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
