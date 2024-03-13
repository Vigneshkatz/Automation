package accountsection;

import base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.smytten.pof.account.AccountPage;
import org.smytten.pof.common.Navigation;
import org.smytten.pof.common.PopUp;
import org.smytten.pof.common.VerifyElementHelper;
import org.smytten.pof.entry.LoginPage;
import org.smytten.pof.entry.OtpPage;
import org.smytten.pof.luxe.LuxeLandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

public class AccountRedirectionTest extends BaseTest {

    private static final String MOBILE_NUMBER = null;


    @Test(priority = 1)
    public void login() throws InterruptedException {
        WebElement mobileInput = null;
        WebElement proceedBtn = null;
        WebElement otpContainer = null;
        WebElement otpLabel = null;
        WebElement mobileNumberLabel = null;
        WebElement mobileNumberEditCta = null;
        WebElement otpEnterInput = null;

        try {
            mobileInput = LoginPage.getMobileNumberInput(driver);
            assertNotNull(mobileInput);
            mobileInput.click();
            mobileInput.sendKeys(LoginPage.getMOBILE_NUMBER(1));
            proceedBtn = LoginPage.getSendOtpButton(driver);
            proceedBtn.click();
            otpContainer = OtpPage.getOtpContainer(driver);
            assertNotNull(otpContainer);
            //  otpLabel = OtpPage.getOtpLabel(driver);
//            mobileNumberLabel = OtpPage.getMobileNumberLabel(driver);
//            mobileNumberEditCta = OtpPage.getMobileNumberEditCta(driver);
            otpEnterInput = OtpPage.getOtpEnterInput(driver);
//            assertTrue(OtpPage.OTP_NOT_RECEIVED_LABEL_TEXT.equalsIgnoreCase(otpLabel.getText().trim()));
//            assertTrue(OtpPage.CONTACT_US_EMAIL_LABEL_TEXT.equalsIgnoreCase(mobileNumberLabel.getText().trim()));
//            assertTrue(OtpPage.MOBILE_NUMBER_EDIT_CTA_TEXT.equalsIgnoreCase(mobileNumberEditCta.getText().trim()));
            otpEnterInput.click();
            // enter otp
            Process process = Runtime.getRuntime().exec("adb shell input text " + OtpPage.VALID_OTP);
            process.waitFor();
            System.out.println("OTP typed successfully." + OtpPage.VALID_OTP);
            recordResult("loginWithCrtOTP", "Pass");

        } catch (AssertionError e) {
            recordResult("loginWithCrtOTP", "Fail " + e.getMessage());
            Assert.fail("loginWithCrtOTP assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signUp", "Fail " + e.getMessage());
            Assert.fail("loginWithCrtOTP failed: " + e.getMessage());
        }
    }

    @Test(priority = 2)
    public void checkPopUp() {
        WebElement popUpClose = null;
        if(VerifyElementHelper.isPopupPresent(driver)){
            popUpClose = PopUp.getPopUpClose(driver);
            popUpClose.click();
            assertTrue("popup successfully closed", true);
            recordResult("checkPopUp", "Pass");

        }
        recordResult("CheckPopUp", "Pass");
    }

    @Test(priority = 3)
    public void gotoAccountPage() {
        WebElement mobileInput = null;
        try {
            mobileInput = Navigation.getProfileHomeTab(driver);
            mobileInput.click();
            assertTrue(true);
            recordResult("addMultipleAddress", "Pass");

        } catch (AssertionError e) {
            recordResult("addMultipleAddress", "Fail " + e.getMessage());
            Assert.fail("addMultipleAddress assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("addMultipleAddress", "Fail " + e.getMessage());
            Assert.fail("addMultipleAddress failed: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void verifyAccountSection() {
        WebElement profileSection = null;
        WebElement profileName = null;
        WebElement editProfile = null;
        WebElement editIcon = null;
        WebElement smyttenBenefits = null;
        WebElement trialHowTo = null;
        WebElement shopHowTo = null;
        WebElement rewardHowTo = null;
        WebElement myOrders = null;
        WebElement helpSection = null;
        WebElement referAndEarn = null;
        WebElement referBanner = null;
        WebElement reviewSection = null;
        WebElement surveySection = null;
        WebElement savedAddress = null;
        WebElement wishlist = null;
        WebElement smyttenLuxe = null;
        WebElement smyttenBlog = null;
        WebElement privacyPolicy = null;
        WebElement termsAndCondition = null;
        WebElement faqs = null;
        WebElement smyttenLogo = null;
        WebElement signOut = null;
        try {
            profileSection = AccountPage.getEditProfile(driver);
            profileName = AccountPage.getProfileName(driver);
            editProfile = AccountPage.getEditProfile(driver);
            editIcon = AccountPage.getEditIcon(driver);
            smyttenBenefits = AccountPage.getSmyttenBenefits(driver);
            trialHowTo = AccountPage.getTrialHowTo(driver);
            shopHowTo = AccountPage.getShopHowTo(driver);
            rewardHowTo = AccountPage.getRewardHowTo(driver);
            myOrders = AccountPage.getMyOrders(driver);
            helpSection = AccountPage.getHelpSection(driver);
            referAndEarn = AccountPage.getReferAndEarn(driver);
            referBanner = AccountPage.getReferBanner(driver);
            reviewSection = AccountPage.getReviewSection(driver);
            surveySection = AccountPage.getSurveySection(driver);
            savedAddress = AccountPage.getSavedAddress(driver);
            wishlist = AccountPage.getWishlist(driver);
            smyttenLuxe = AccountPage.getSmyttenLuxe(driver);
            smyttenBlog = AccountPage.getSmyttenBlog(driver);
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
            privacyPolicy = AccountPage.getPrivacyPolicy(driver);
            termsAndCondition = AccountPage.getTermsAndCondition(driver);
            faqs = AccountPage.getFaqs(driver);
            smyttenLogo = AccountPage.getSmyttenLogo(driver);
            signOut = AccountPage.getSignOut(driver);
            recordResult("verifyAccountSection", "Pass");

        } catch (AssertionError e) {
            recordResult("verifyAccountSection", "Fail " + e.getMessage());
            Assert.fail("verifyAccountSection assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("verifyAccountSection", "Fail " + e.getMessage());
            Assert.fail("verifyAccountSection failed: " + e.getMessage());
        }
    }

    @Test(priority = 5)
    public void openTrialHowTo() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement trialHowTo = null;
        WebElement headerSection = null;
        WebElement content = null;
        WebElement trialTitle = null;
        WebElement trialPoint = null;
        WebElement trialVideo = null;
        WebElement trialBanner = null;
        try {
            trialHowTo = AccountPage.getTrialHowTo(driver);
            trialHowTo.click();
            headerSection = AccountPage.getHelpSection(driver);
            content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            trialTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            trialPoint = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_trial"));
            trialVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            trialBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            assertEquals(trialPoint.getText(), "8");
            assertEquals(trialTitle.getText(), "Trial Points");
            assertNotNull(headerSection);
            assertNotNull(trialVideo);
            assertNotNull(trialBanner);
            assertNotNull(content);
            driver.navigate().back();
            recordResult("openTrialHowTo", "Pass");

        } catch (AssertionError e) {
            recordResult("openTrialHowTo", "Fail " + e.getMessage());
            Assert.fail("openTrialHowTo assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openTrialHowTo", "Fail " + e.getMessage());
            Assert.fail("openTrialHowTo failed: " + e.getMessage());
        } finally {
            driver.navigate().back();

        }
    }

    @Test(priority = 6)
    public void openShopHowTo() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement shopHowTo = null;
        WebElement headerSection = null;
        WebElement content = null;
        WebElement shopTitle = null;
        WebElement walletBalance = null;
        WebElement shopVideo = null;
        WebElement shopBanner = null;
        try {
            shopHowTo = AccountPage.getShopHowTo(driver);
            shopHowTo.click();
            headerSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
            content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            shopTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            walletBalance = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_banner_amt"));
            shopVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            shopBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            assertEquals(walletBalance.getText(), "₹0");
            assertEquals(shopTitle.getText(), "Smytten Wallet");
            assertNotNull(headerSection);
            assertNotNull(shopVideo);
            assertNotNull(shopBanner);
            assertNotNull(content);
            driver.navigate().back();
            recordResult("openShopHowTo", "Pass");

        } catch (AssertionError e) {
            recordResult("openShopHowTo", "Fail " + e.getMessage());
            Assert.fail("openShopHowTo assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openShopHowTo", "Fail " + e.getMessage());
            Assert.fail("openShopHowTo failed: " + e.getMessage());
        } finally {
            driver.navigate().back();

        }
    }

    @Test(priority = 7)
    public void openRewardsHowTo() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement rewardHowTo = null;
        WebElement headerSection = null;
        WebElement content = null;
        WebElement rewardTitle = null;
        WebElement walletBalance = null;
        WebElement rewardVideo = null;
        WebElement rewardBanner = null;
        try {
            rewardHowTo = AccountPage.getRewardHowTo(driver);
            rewardHowTo.click();
            headerSection = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_wallet_banner_top"));
            content = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cl_root"));
            rewardTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            walletBalance = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_wallet_banner_amt"));
            rewardVideo = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/exo_subtitles"));
            rewardBanner = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_banner_view"));
            assertEquals(walletBalance.getText(), "0");
            assertEquals(rewardTitle.getText(), "Smytten Bucks");
            assertNotNull(headerSection);
            assertNotNull(rewardVideo);
            assertNotNull(rewardBanner);
            assertNotNull(content);
            driver.navigate().back();
            recordResult("openRewardsHowTo", "Pass");

        } catch (AssertionError e) {
            recordResult("openRewardsHowTo", "Fail " + e.getMessage());
            Assert.fail("openRewardsHowTo assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openRewardsHowTo", "Fail " + e.getMessage());
            Assert.fail("openRewardsHowTo failed: " + e.getMessage());
        } finally {
            driver.navigate().back();

        }
    }

    @Test(priority = 8)
    public void myOrders() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement myOrders = null;
        WebElement orderTitle = null;
        WebElement emptyData = null;
        WebElement emptyTitle = null;
        WebElement emptySubtitle = null;
        try {
            myOrders = AccountPage.getMyOrders(driver);
            myOrders.click();
//            orderTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tb_my_orders"));
            emptyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            emptyData = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            emptySubtitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_desc"));
            assertEquals("Order Page title", "My Orders", orderTitle.getText());
            assertEquals("Order Page empty title", "Sorry, You do not have any orders!", emptyTitle.getText());
            assertEquals("Order Page subtitle", "Discover something new, place an order today!", emptySubtitle.getText());
            assertNotNull(emptyData);
            assertNotNull(orderTitle);
            recordResult("myOrders", "Pass");

        } catch (AssertionError e) {
            recordResult("myOrders", "Fail " + e.getMessage());
            Assert.fail("myOrders asssertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("myOrders", "Fail " + e.getMessage());
            Assert.fail("myOrders failed: " + e.getMessage());
        } finally {
            driver.navigate().back();

        }


    }

    @Test(priority = 9)
    public void openHelp() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
        WebElement helpSection = null;
        WebElement contactTitle = null;
        WebElement supportDelivery = null;
        WebElement supportPayment = null;
        WebElement supportRefund = null;
        WebElement needMoreHelp = null;
        WebElement orderQueries = null;
        WebElement accountQueries = null;
        WebElement chatBot = null;
        WebElement otpFooter = null;
        try {
            helpSection = AccountPage.getHelpSection(driver);
            helpSection.click();
            contactTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_bucks_title1"));
            supportDelivery = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_1"));
            supportPayment = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_2"));
            supportRefund = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_support_3"));
            needMoreHelp = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Need More Help?\"]"));
            orderQueries = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_order"));
            accountQueries = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_mail"));
            chatBot = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/include_whatsapp"));
            otpFooter = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_header_otp"));
            assertEquals("Contact Page title", "Contact Us", contactTitle.getText());
            assertNotNull(supportDelivery);
            assertNotNull(supportPayment);
            assertNotNull(supportRefund);
            assertNotNull(accountQueries);
            assertNotNull(orderQueries);
            assertNotNull(chatBot);
            assertEquals("need more help", "Need More Help?", needMoreHelp.getText());
            assertEquals("Otp footer", "We will never ask you to share your bank details, OTP or any other sensitive information", otpFooter.getText());
            driver.navigate().back();
            recordResult("openHelp", "Pass");

        } catch (AssertionError e) {
            recordResult("openHelp", "Fail " + e.getMessage());
            Assert.fail("openHelp assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openHelp", "Fail " + e.getMessage());
            Assert.fail("openHelp failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 10)
    public void openReferAndEarn() {
        try {
            gotoAccountPage();
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToBeginning(100000)"));
            AccountPage.getReferBanner(driver);
            recordResult("openReferAndEarn", "Pass");
        } catch (AssertionError e) {
            recordResult("openReferAndEarn", "Fail " + e.getMessage());
            Assert.fail("openReferAndEarn assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openReferAndEarn", "Fail " + e.getMessage());
            Assert.fail("openReferAndEarn failed: " + e.getMessage());
        }
    }

    @Test(priority = 11)
    public void openReview() {
        gotoAccountPage();
        WebElement reviewSection = null;
        WebElement reviewTitle = null;
        WebElement emptyState = null;
        WebElement tabs = null;
        WebElement emptyText = null;
        try {
            reviewSection = AccountPage.getReviewSection(driver);
            assertNotNull(reviewSection);
            reviewTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Review Section title", "Feedbacks & Surveys", reviewTitle.getText());
            tabs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_tab1"));
            assertEquals("trial section", "Trial", tabs.getText());
            emptyState = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            assertNotNull(emptyState);
            emptyText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            assertEquals("Empty State", "You don't have any products with pending feedback. \n" + "\n" + "Find all your recently purchased products here for review.", emptyText.getText());
            recordResult("openReview", "Pass");
        } catch (AssertionError e) {
            recordResult("openReview", "Fail " + e.getMessage());
            Assert.fail("openReview assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openReview", "Fail " + e.getMessage());
            Assert.fail("openReview failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 12)
    public void openSurvey() {
        gotoAccountPage();
        WebElement surveySection = null;
        WebElement surveyTitle = null;
        WebElement emptyState = null;
        WebElement tabs = null;
        WebElement emptyText = null;
        try {
            surveySection = AccountPage.getSurveySection(driver);
            assertNotNull(surveySection);
            surveyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Review Section title", "Feedbacks & Surveys", surveyTitle.getText());
            tabs = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_tab3"));
            assertEquals("trial section", "Surveys", tabs.getText());
            emptyState = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            assertNotNull(emptyState);
            emptyText = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            assertEquals("Empty State", "Unfortunately, we do not have a survey available for you right now.", emptyText.getText());
            recordResult("openSurvey", "Pass");

        } catch (AssertionError e) {
            recordResult("openSurvey", "Fail " + e.getMessage());
            Assert.fail("openSurvey assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openSurvey", "Fail " + e.getMessage());
            Assert.fail("openSurvey failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 13)
    public void openSavedAddress() {
        WebElement addressTitle = null;
        WebElement noAddressWarning = null;
        WebElement addNewAddressCta = null;
        WebElement placeholderImage = null;
        WebElement savedAddress = null;
        try {
            savedAddress = AccountPage.getSavedAddress(driver);
            savedAddress.click();
            addressTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            noAddressWarning = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_error"));
            addNewAddressCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/ll_add_new_address"));
            placeholderImage = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/img_msg_placeholder"));
            assertNotNull(placeholderImage);
            assertEquals("Shipping Address".toLowerCase(), addressTitle.getText().toLowerCase());
            assertEquals("Add New Address +".toLowerCase(), addNewAddressCta.getText().toLowerCase());
            assertEquals("Sorry, you have no saved address.".toLowerCase(), noAddressWarning.getText().toLowerCase());
            recordResult("openSavedAddress", "Pass");

        } catch (AssertionError e) {
            recordResult("openSavedAddress", "Fail " + e.getMessage());
            Assert.fail("openSavedAddress assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openSavedAddress", "Fail " + e.getMessage());
            Assert.fail("openSavedAddress failed: " + e.getMessage());
        }
    }

    @Test(priority = 14)
    public void openWishList() {
        gotoAccountPage();
        WebElement wishlist = null;
        WebElement title = null;
        WebElement cart = null;
        WebElement emptyPlaceHolder = null;
        WebElement toggle = null;
        WebElement trialToggle = null;
//        WebElement shopToggle = null;
        WebElement emptyTitle = null;
        WebElement description = null;
        WebElement exploreCta = null;
        WebElement closeCta = null;
        try {
            driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
            wishlist = AccountPage.getWishlist(driver);
            wishlist.click();
            title = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            cart = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_cart"));
            emptyPlaceHolder = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_empty_data"));
            toggle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/cv_tabs"));
            trialToggle = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Trial\"]"));
//            shopToggle = driver.findElement(AppiumBy.id(""));
            emptyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_title"));
            description = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_empty_desc"));
            exploreCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_start_exploring"));
            closeCta = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertEquals("WishList Title", title.getText(), "My Wishlist");
            assertNotNull(cart);
            assertNotNull(emptyPlaceHolder);
            assertNotNull(toggle);
            assertNotNull(trialToggle);
            assertEquals("Empty title", emptyTitle.getText(), "Your wishlist is currently empty!");
            assertEquals("Description", description.getText(), "Tap ❤ \uFE0F on your favourite products to find them here.");
            assertEquals("Explore cta", exploreCta.getText(), "Explore Now");
            assertNotNull(closeCta);

            recordResult("openWishList", "Pass");
        } catch (AssertionError e) {
            recordResult("openWishList", "Fail " + e.getMessage());
            Assert.fail("openWishList assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openWishList", "Fail " + e.getMessage());
            Assert.fail("openWishList failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 15)
    public void openSmyttenLuxe() {
        gotoAccountPage();
        WebElement smyttenLuxe = null;
        WebElement luxeTitle = null;
        WebElement luxeProceed = null;
        try {
            smyttenLuxe = AccountPage.getSmyttenLuxe(driver);
            smyttenLuxe.click();
            luxeTitle = LuxeLandingPage.getLuxeTitle(driver);
            String luxeTitleText = luxeTitle.getText();
            assertEquals("Luxe title", "Smytten Luxe", luxeTitleText.trim());
            luxeProceed = LuxeLandingPage.getProceedButton(driver);
            assertNotNull(luxeProceed);

            recordResult("openSmyttenLuxe", "Pass");
        } catch (AssertionError e) {
            recordResult("openSmyttenLuxe", "Fail " + e.getMessage());
            Assert.fail("openSmyttenLuxe assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openSmyttenLuxe", "Fail " + e.getMessage());
            Assert.fail("openSmyttenLuxe failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }

    }

    @Test(priority = 16)
    public void openSmyttenBlog() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement smyttenBlog = null;
        WebElement smyttenBlogTile = null;
        WebElement closeIcon = null;

        try {
            smyttenBlog = AccountPage.getSmyttenBlog(driver);
            assertNotNull(smyttenBlog);
            smyttenBlog.click();
            smyttenBlogTile = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Redirection title", "Blog", smyttenBlogTile.getText());
            closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull(closeIcon);

            recordResult("openSmyttenBlog", "Pass");
        } catch (AssertionError e) {
            recordResult("openSmyttenBlog", "Fail " + e.getMessage());
            Assert.fail("openSmyttenBlog assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openSmyttenBlog", "Fail " + e.getMessage());
            Assert.fail("openSmyttenBlog failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }

    }

//    @Test(priority = 17)
//    public void openRateSmytten(){
//        // Test logic for opening rate Smytten section
//    }

    @Test(priority = 18)
    public void openTermsAndCondition() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement tnc = null;
        WebElement tncTitle = null;
        WebElement closeIcon = null;
        try {
            tnc = AccountPage.getTermsAndCondition(driver);
            tnc.click();
            tncTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Redirection title", "Terms", tncTitle.getText().trim());
            closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull(closeIcon);

            recordResult("openTermsAndCondition", "Pass");
        } catch (AssertionError e) {
            recordResult("openTermsAndCondition", "Fail " + e.getMessage());
            Assert.fail("openTermsAndCondition assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openTermsAndCondition", "Fail " + e.getMessage());
            Assert.fail("openTermsAndCondition failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 19)
    public void openPrivacyPolicy() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement privacy = null;
        WebElement privacyTitle = null;
        WebElement closeIcon = null;
        try {
            privacy = AccountPage.getPrivacyPolicy(driver);
            privacy.click();
            privacyTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Redirection title", "Privacy Policy", privacyTitle.getText());
            closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull(closeIcon);
            recordResult("openPrivacyPolicy", "Pass");

        } catch (AssertionError e) {
            recordResult("openPrivacyPolicy", "Fail " + e.getMessage());
            Assert.fail("openPrivacyPolicy assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openPrivacyPolicy", "Fail " + e.getMessage());
            Assert.fail("openPrivacyPolicy failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 20)
    public void openFaqs() {
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement faqs = null;
        WebElement faqTitle = null;
        WebElement closeIcon = null;
        try {
            faqs = AccountPage.getFaqs(driver);
            faqs.click();
            faqTitle = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/tv_title"));
            assertEquals("Redirection title", "FAQ", faqTitle.getText());
            closeIcon = driver.findElement(AppiumBy.id("com.app.smytten.debug:id/iv_close"));
            assertNotNull(closeIcon);
            recordResult("openFaqs", "Pass");

        } catch (AssertionError e) {
            recordResult("openFaqs", "Fail " + e.getMessage());
            Assert.fail("openFaqs assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("openFaqs", "Fail " + e.getMessage());
            Assert.fail("openFaqs failed: " + e.getMessage());
        } finally {
            driver.navigate().back();
        }
    }

    @Test(priority = 21)
    public void signOut() {
        checkPopUp();
        gotoAccountPage();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(100000)"));
        WebElement signOut = null;
        WebElement mobileInput = null;
        WebElement yesCta = null;
        try {
            signOut = AccountPage.getSignOut(driver);
            signOut.click();
            yesCta = driver.findElement(AppiumBy.id("android:id/button1"));
            yesCta.click();
            recordResult("signOut", "Pass");
        } catch (AssertionError e) {
            recordResult("signOut", "Fail " + e.getMessage());
            Assert.fail("signOut assertion failed: " + e.getMessage());
        } catch (Exception e) {
            recordResult("signOut", "Fail " + e.getMessage());
            Assert.fail("signOut failed: " + e.getMessage());
        }
    }
}
