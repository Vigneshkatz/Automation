package base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.SignUpPage;
import org.smytten.util.Utility;
import org.smytten.util.driver.AndroidHelper;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.testng.AssertJUnit.*;

public class BaseTest {

    private static final Logger LOGGER = Logger.getLogger(BaseTest.class.getName());
    public AndroidDriver driver;
    public TouchAction touchAction;
    public AndroidHelper androidHelper;

    @BeforeSuite
    public void setUp() {
        LOGGER.info("Setting up test environment...");
        startAppiumServer();

        try {
            driver = initializeDriver();
            assertNotNull("Driver initialization failed", driver);
            long implicitWaitTime = 15;
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
            touchAction = new TouchAction<>(driver);
            androidHelper = new AndroidHelper(driver);
        } catch (MalformedURLException e) {
            LOGGER.log(Level.SEVERE, "Invalid Appium server URL: " + e.getMessage());
        }
    }

    @AfterSuite
    public void tearDown() {
        LOGGER.info("Tearing down test environment...");
        if (driver != null) {
            driver.quit();
            LOGGER.info("Driver quit successfully");
        }

        stopAppiumServer();
    }

    private AndroidDriver initializeDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "OnePlus LE2111");
        capabilities.setCapability("platformVersion", "14");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        capabilities.setCapability("enforceAppInstall", true);
        capabilities.setCapability("app", "/Users/Vignesh/Desktop/Automation/src/main/resources/Smytten-169-debug (1).apk");
        return new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    private void startAppiumServer() {
        try {
            Runtime.getRuntime().exec("appium");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to start Appium server: " + e.getMessage());
        }
    }

    private void stopAppiumServer() {
        try {
            ProcessBuilder builder = new ProcessBuilder("pkill", "-9", "node");
            Process process = builder.start();

            boolean completed = process.waitFor(5, TimeUnit.SECONDS);

            if (!completed) {
                process.destroy();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to stop Appium server: " + e.getMessage());
        }
    }

    public void signOut() {
        try {
            checkPopUp();
            gotoAccountPage();
            androidHelper.scrollToBottom();
            WebElement signOut = AccountPage.getSignOut(driver);
            assertNotNull("Sign out button not found", signOut);
            signOut.click();

            WebElement yesCta = driver.findElement(AppiumBy.id("android:id/button1"));
            assertNotNull("Yes button not found", yesCta);
            yesCta.click();
        } catch (AssertionError | Exception e) {
            fail("signOut " + e.getMessage());
        }
    }

    public void checkPopUp() {
        try {
            if (VerifyElementHelper.isPopupPresent(driver)) {
                WebElement popUpClose = PopUp.getPopUpClose(driver);
                assertNotNull("Popup close button not found", popUpClose);
                popUpClose.click();
                assertTrue("Popup successfully closed", true);
            } else {
                assertTrue("No popUp present", true);
            }
        } catch (AssertionError | Exception e) {
            fail("checkPopUp " + e.getMessage());
        }
    }

    public void gotoAccountPage() {
        try {
            WebElement profileHomeTab = Navigation.getProfileHomeTab(driver);
            assertNotNull("Profile home tab not found", profileHomeTab);
            profileHomeTab.click();
        } catch (AssertionError e) {
            fail("gotoAccountPage " + e.getMessage());
        } catch (Exception e) {
            fail("gotoAccountPage " + e.getMessage());
        }
    }

    public void enterOTP(String otp) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec("adb shell input text " + otp);
        process.waitFor();
        System.out.println("OTP typed successfully: " + otp);
    }

    public void signUpHelper() throws NoSuchElementException {
        WebElement maleElement = SignUpPage.getMaleGenderOption(driver);
        WebElement femaleElement = SignUpPage.getFemaleGenderOption(driver);
        WebElement chooseGender = (Utility.RANDOMNUMBER == 0) ? maleElement : femaleElement;
        chooseGender.click();
        SignUpPage.getMonthSpinner(driver).click();
        SignUpPage.getMarchMonthOption(driver).click();
        SignUpPage.getYearSpinner(driver).click();
        SignUpPage.getYear2009Option(driver).click();
        WebElement referralInput = SignUpPage.getReferralInput(driver);
        referralInput.click();
        referralInput.sendKeys(SignUpPage.GROUP_INVITE_CODE);
        SignUpPage.getReferralApplyBtn(driver).click();
        WebElement referralSuccessTitle = SignUpPage.getReferralSuccessTitle(driver);
        assertNotNull("Referral success title not found", referralSuccessTitle);
        System.out.println(referralSuccessTitle.getText());
        WebElement referralSuccessPaymentTitle = SignUpPage.getReferralSuccessPaymentTitle(driver);
        System.out.println(referralSuccessPaymentTitle.getText());
        SignUpPage.getConfirmBtn(driver).click();
    }
}
